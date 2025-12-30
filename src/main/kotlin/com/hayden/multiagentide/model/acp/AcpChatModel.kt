package com.hayden.multiagentide.model.acp

import com.agentclientprotocol.client.Client
import com.agentclientprotocol.client.ClientInfo
import com.agentclientprotocol.client.ClientSession
import com.agentclientprotocol.common.ClientSessionOperations
import com.agentclientprotocol.common.Event
import com.agentclientprotocol.common.SessionCreationParameters
import com.agentclientprotocol.model.*
import com.agentclientprotocol.protocol.Protocol
import com.agentclientprotocol.transport.Transport
import com.hayden.multiagentide.agent.AgentInterfaces
import com.hayden.multiagentide.config.AcpModelProperties
import io.modelcontextprotocol.server.IdeMcpAsyncServer.TOOL_ALLOWLIST_HEADER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.flux
import kotlinx.coroutines.runBlocking
import kotlinx.io.asSink
import kotlinx.io.asSource
import kotlinx.io.buffered
import kotlinx.serialization.json.JsonElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.MessageType
import org.springframework.ai.chat.messages.SystemMessage
import org.springframework.ai.chat.messages.ToolResponseMessage
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.model.Generation
import org.springframework.ai.chat.model.StreamingChatModel
import org.springframework.ai.chat.prompt.Prompt
import reactor.core.publisher.Flux
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * ACP-backed ChatModel implementation using the agentclientprotocol SDK.
 */

class AcpChatModel(
    private val properties: AcpModelProperties,
    private val chatMemoryContext: ChatMemoryContext?
) : org.springframework.ai.chat.model.ChatModel, StreamingChatModel {
    
    private val log: Logger = LoggerFactory.getLogger(AcpChatModel::class.java)

    private val sessionLock = Any()

    private val sessionContexts = ConcurrentHashMap<Any, AcpSessionContext>()

    override fun call(prompt: Prompt): org.springframework.ai.chat.model.ChatResponse {
        val cr = doChat(prompt)
        return cr
    }

    override fun stream(prompt: Prompt): Flux<ChatResponse> {
        return when (val options = prompt.options) {
            is AcpChatRequestParameters -> {
                performStream(prompt, options.memoryId())
            }
            else -> {
                performStream(prompt, null)
            }
        }
    }

    fun performStream(messages: Prompt, memoryId: Any?): Flux<ChatResponse> {
        return flux {
            Flux.just(
                toChatResponse(
                    streamChat(messages, memoryId)
                        .toList(mutableListOf()),
                    messages
                )
            ) 
        }
    }

    override fun getDefaultOptions(): AcpChatRequestParameters {
        return AcpChatRequestParameters.builder()
//            .memoryId(AgentInterfaces.agentProcess.get()?.id)
            .build()
    }

    fun doChat(chatRequest: Prompt?): ChatResponse {
        val request = requireNotNull(chatRequest) { "chatRequest must not be null" }
        val memoryId = resolveMemoryId(request)
        val messages = resolveToSendMessages(chatRequest)
        return invokeChat(Prompt.builder().messages(messages).chatOptions(chatRequest.options).build(), memoryId)
    }
    
    fun resolveToSendMessages(messages: Prompt): List<Message> {
        val memoryId = resolveMemoryId(messages)
        val hasSession = sessionExists(memoryId)
        return if (hasSession) {
            listOf(messages.instructions.last())
        } else {
            resolveMessages(messages, memoryId)
        }
    }

    suspend fun streamChat(prompt: Prompt, memoryId: Any?): Flow<ContentBlock>  {
        val session = getOrCreateSession(memoryId)

        val messages = resolveToSendMessages(prompt)
        
        val content = listOf(ContentBlock.Text(formatPromptMessages(Prompt.builder().messages(messages).chatOptions(prompt.options).build())))

        return session.prompt(content)
            .mapNotNull{ event -> parseContentBlockFromAcpEvent(event) }
    }

    private fun parseContentBlockFromAcpEvent(event: Event): ContentBlock? = if (event is Event.SessionUpdateEvent) {
        val update = event.update
        if (update is SessionUpdate.AgentMessageChunk) {
            val block = update.content
            block
        } else {
            null
        }
    } else {
        null
    }

    fun toGeneration(messages: List<ContentBlock>): Generation {
        return Generation(
            AssistantMessage(
                messages.mapNotNull {
                        when (it) {
                            is ContentBlock.Text -> it.text
                            else -> {
                                log.error("Unsupported content block type: ${it::class.simpleName}")
                                null
                            }
                        }
                    }
                    .joinToString(separator = "")
            )
        )
        
    }

    fun invokeChat(messages: Prompt, memoryId: Any?): ChatResponse = runBlocking {
        val session = getOrCreateSession(memoryId)
        val contentBlocks = mutableListOf<ContentBlock>()
        val content = listOf(ContentBlock.Text(formatPromptMessages(messages)))

        session.prompt(content)
            .mapNotNull{ event -> parseContentBlockFromAcpEvent(event) }
            .collect { contentBlocks.add(it) }

        toChatResponse(contentBlocks, messages)
    }

    private fun toChatResponse(contentBlocks: List<ContentBlock>,
                               prompt: Prompt): ChatResponse = ChatResponse.builder()
        .generations(mutableListOf(toGeneration(contentBlocks)))
        .build()

    fun createProcessStdioTransport(coroutineScope: CoroutineScope, vararg command: String): Transport {
        val process = ProcessBuilder(*command)
            .redirectInput(ProcessBuilder.Redirect.PIPE)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()
        val stdin = process.outputStream.asSink().buffered()
        val stdout = process.inputStream.asSource().buffered()
        return AcpSerializerTransport(
            parentScope = coroutineScope,
            ioDispatcher = Dispatchers.IO,
            input = stdout,
            output = stdin
        )
    }

    private fun sessionExists(memoryId: Any?): Boolean {
        return memoryId != null && sessionContexts.containsKey(memoryId)
    }

    private fun resolveMessages(chatRequest: Prompt, memoryId: Any?): List<Message> {
        if (memoryId == null) {
            return chatRequest.instructions
        }
        val history = chatMemoryContext?.getMessages(memoryId).orEmpty()
        return if (history.isNotEmpty()) history else chatRequest.instructions
    }

    private fun resolveMemoryId(chatRequest: Prompt): Any? {
        val params = chatRequest.options
        return if (params is AcpChatRequestParameters) params.memoryId() else null
    }

    private fun getOrCreateSession(memoryId: Any?): ClientSession {
        if (memoryId == null) {
            return runBlocking { createSessionContext() }.session
        }
        sessionContexts[memoryId]?.let { return it.session }
        synchronized(sessionLock) {
            sessionContexts[memoryId]?.let { return it.session }
            val context = runBlocking { createSessionContext() }
            sessionContexts[memoryId] = context
            return context.session
        }
    }

    private suspend fun createSessionContext(): AcpSessionContext {
        if (!properties.transport.equals("stdio", ignoreCase = true)) {
            throw IllegalStateException("Only stdio transport is supported for ACP integration")
        }

        val command = properties.command?.trim().orEmpty()
        if (command.isBlank()) {
            throw IllegalStateException("ACP command is not configured")
        }

        val args = parseArgs(properties.args)
        val process = properties.command
        val workingDirectory = properties.workingDirectory

        return try {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
            val transport = createProcessStdioTransport(scope, process)
            val protocol = Protocol(scope, transport)
            val client = Client(protocol)

            val agentInfo = protocol.start()
            val authenticationResult = client.authenticate(AuthMethodId("chatgpt"))
            val initialized = client.initialize(
                ClientInfo(
                    capabilities = ClientCapabilities(
                        fs = FileSystemCapability(
                            readTextFile = true,
                            writeTextFile = true
                        ),
                        terminal = true
                    )
                )
            )

            println("Agent info: $initialized")

            println()

            val sessionParams = SessionCreationParameters(
                if (workingDirectory.isNotBlank()) workingDirectory else System.getProperty("user.dir"),
                mutableListOf(
                    McpServer.Http("agent-tools", "http://localhost:8080/mcp",
                        mutableListOf(HttpHeader(TOOL_ALLOWLIST_HEADER, "emitGuiEvent"))),
                    McpServer.Http("gateway", "http://localhost:8081/mcp",
                        mutableListOf(
//                            HttpHeader(TOOL_ALLOWLIST_HEADER, "emitGuiEvent")
                        )),
                )
            )

            val session=  client.newSession(sessionParams)
                { session, arg -> AcpSessionOperations()}

            AcpSessionContext(scope, transport, protocol, client, session)

        } catch (ex: Exception) {
            throw IllegalStateException("Failed to initialize ACP session", ex)
        }
    }

    private fun parseArgs(args: String?): List<String> {
        if (args.isNullOrBlank()) {
            return emptyList()
        }
        val tokenizer = StringTokenizer(args)
        val tokens = mutableListOf<String>()
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken())
        }
        return tokens
    }

    private fun formatPromptMessages(messages: Prompt): String {
        if (messages.instructions.isEmpty()) {
            return ""
        }
        val builder = StringBuilder()
        fun formatMessageRole(role: String, message: Message): String = "$role ${message.text}"

        messages.instructions.forEach { message ->
            val role = resolveRole(message)
            if (builder.isNotEmpty()) {
                builder.append('\n')
            }
            when(message) {
                is UserMessage -> builder.append(formatMessageRole(role, message))
                is AssistantMessage -> builder.append(formatMessageRole(role, message))
                is SystemMessage -> builder.append(formatMessageRole(role, message))
                is ToolResponseMessage -> {}
            }
        }
        
        return builder.toString()
    }

//    private fun fromContent(contents: Content): String {
//        return when(contents) {
//            is TextContent -> contents.text();
//            is AudioContent -> "";
//            is PdfFileContent -> "";
//            is McpSchema.ImageContent -> "";
//            is VideoContent -> "";
//
//            else -> ""
//        }
//    }

    private fun resolveRole(message: Message): String = when (message) {
        is UserMessage -> MessageType.USER.name
        is SystemMessage -> MessageType.SYSTEM.name
        is AssistantMessage -> MessageType.ASSISTANT.name
        is ToolResponseMessage -> MessageType.TOOL.name
        else -> "user"
    }

    private data class AcpSessionContext(
        val scope: CoroutineScope,
        val transport: Transport,
        val protocol: Protocol,
        val client: Client,
        val session: ClientSession
    )

    private class AcpSessionOperations : ClientSessionOperations {

        override suspend fun requestPermissions(
            toolCall: SessionUpdate.ToolCallUpdate,
            permissions: List<PermissionOption>,
            _meta: JsonElement?
        ): RequestPermissionResponse {
//            val selection = permissions.firstOrNull()
//                ?: return RequestPermissionResponse(RequestPermissionOutcome.Cancelled, _meta)
//            return RequestPermissionResponse(RequestPermissionOutcome.Selected(selection.optionId), _meta)
//
            return RequestPermissionResponse(RequestPermissionOutcome.Cancelled, _meta)
        }

        override suspend fun fsReadTextFile(
            path: String,
            line: UInt?,
            limit: UInt?,
            _meta: JsonElement?
        ): ReadTextFileResponse {
            return super.fsReadTextFile(path, line, limit, _meta)
        }

        override suspend fun fsWriteTextFile(
            path: String,
            content: String,
            _meta: JsonElement?
        ): WriteTextFileResponse {
            return super.fsWriteTextFile(path, content, _meta)
        }

        override suspend fun notify(notification: SessionUpdate, _meta: JsonElement?) {
        }
    }
}
