package com.hayden.multiagentide.model.acp

import com.agentclientprotocol.client.Client
import com.agentclientprotocol.client.ClientInfo
import com.agentclientprotocol.common.ClientSessionOperations
import com.agentclientprotocol.common.SessionCreationParameters
import com.agentclientprotocol.model.*
import com.agentclientprotocol.protocol.Protocol
import com.agentclientprotocol.transport.Transport
import com.hayden.multiagentide.agent.AgentInterfaces
import com.hayden.multiagentide.agent.AgentTools
import com.hayden.multiagentide.config.AcpModelProperties
import com.hayden.multiagentide.config.McpProperties
import io.modelcontextprotocol.server.IdeMcpAsyncServer.TOOL_ALLOWLIST_HEADER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
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
import org.springframework.ai.mcp.AsyncMcpToolCallback
import org.springframework.ai.mcp.SyncMcpToolCallback
import org.springframework.ai.model.tool.ToolCallingChatOptions
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.*

/**
 * ACP-backed ChatModel implementation using the agentclientprotocol SDK.
 */

@Component
class AcpChatModel(
    private val properties: AcpModelProperties,
    private val chatMemoryContext: ChatMemoryContext?,
    private val sessionManager: AcpSessionManager,
    private val mcpProperties: McpProperties
) : org.springframework.ai.chat.model.ChatModel, StreamingChatModel {

    private val sessionLock = Any()

    private val log: Logger = LoggerFactory.getLogger(AcpChatModel::class.java)

    override fun call(prompt: Prompt): ChatResponse {
        log.info("Received request - {}.", prompt)
        val cr = doChat(prompt)
        return cr
    }

    override fun stream(prompt: Prompt): Flux<ChatResponse> {
        log.info("Received request - {}.", prompt)
        return performStream(prompt, resolveMemoryId())
    }

    fun performStream(messages: Prompt, memoryId: Any?): Flux<ChatResponse> {
        return flux {
            Flux.just(
                toChatResponse(
                    streamChat(messages, memoryId)
                        .toList(mutableListOf()),
                )
            )
        }
    }

    fun doChat(chatRequest: Prompt?): ChatResponse {
        val request = requireNotNull(chatRequest) { "chatRequest must not be null" }
        val memoryId = resolveMemoryId()
        val sessionContext = getOrCreateSession(memoryId, chatRequest)
        val messages = resolveToSendMessages(chatRequest)
        return invokeChat(Prompt.builder().messages(messages).chatOptions(chatRequest.options).build(), sessionContext, memoryId)
    }

    fun resolveToSendMessages(messages: Prompt): List<Message> {
        val memoryId = resolveMemoryId()
        val hasSession = sessionExists(memoryId)
        return if (hasSession) {
            listOf(messages.instructions.last())
        } else {
            resolveMessages(messages, memoryId)
        }
    }

    suspend fun streamChat(prompt: Prompt, memoryId: Any?): Flow<Generation>  {
        val session = getOrCreateSession(memoryId, prompt)

        val messages = resolveToSendMessages(prompt)

        val content = listOf(ContentBlock.Text(formatPromptMessages(Prompt.builder().messages(messages).chatOptions(prompt.options).build())))

        return session.prompt(content)
            .transform { event ->
                parseGenerationsFromAcpEvent(event, session, memoryId).forEach { emit(it) }
            }
            .onCompletion {
                session.flushWindows(memoryId).forEach { emit(it) }
            }
    }

    fun invokeChat(messages: Prompt, sessionContext: AcpSessionManager.AcpSessionContext, memoryId: Any?): ChatResponse = runBlocking {
        val session = getOrCreateSession(memoryId, messages)
        val generations = mutableListOf<Generation>()
        val content = listOf(ContentBlock.Text(formatPromptMessages(messages)))

        session.prompt(content)
            .transform { event ->
                parseGenerationsFromAcpEvent(event, sessionContext, memoryId).forEach { emit(it) }
            }
            .collect { generations.add(it) }

        generations.addAll(session.flushWindows(memoryId))

        toChatResponse(generations)
    }

    private fun toChatResponse(generations: List<Generation>): ChatResponse = ChatResponse.builder()
        .generations(generations.toMutableList())
        .build()

    fun createProcessStdioTransport(coroutineScope: CoroutineScope,
                                    vararg command: String): Transport {
        val pb = ProcessBuilder(*command)
            .redirectInput(ProcessBuilder.Redirect.PIPE)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)

       this.properties.envCopy()
           ?.forEach { envKey, envValue -> pb.environment()[envKey] = envValue }

        val process = pb
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
        return memoryId != null && sessionManager.sessionContexts.containsKey(memoryId)
    }

    private fun resolveMessages(chatRequest: Prompt, memoryId: Any?): List<Message> {
        if (memoryId == null) {
            return chatRequest.instructions
        }
        val history = chatMemoryContext?.getMessages(memoryId).orEmpty()
        return if (history.isNotEmpty()) history else chatRequest.instructions
    }

    private fun resolveMemoryId(): Any? {
        return AgentInterfaces.agentProcess.get()?.id
    }

    private fun getOrCreateSession(memoryId: Any?, chatRequest: Prompt?): AcpSessionManager.AcpSessionContext {
        val m = memoryId ?: "unknown"
        return sessionManager.sessionContexts.computeIfAbsent(m) {
            runBlocking { createSessionContext(it, chatRequest) }
        }
    }

    private suspend fun createSessionContext(memoryId: Any?, chatRequest: Prompt?): AcpSessionManager.AcpSessionContext {
        log.info("Creating session context for $memoryId")

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

            properties.authMethod?.let {
                val authenticationResult = client.authenticate(AuthMethodId(it))
                log.info("Authenticated with ACP {}", authenticationResult)
            }

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

            log.info("Agent info: ${initialized.implementation.toString()}")

            val toolAllowlist = mutableSetOf(
                "emitGuiEvent",
                "retrieveGui",
                "submitGuiFeedback",
                "performUiDiff"
            )

            val toolHeaders = mutableListOf(
                HttpHeader(TOOL_ALLOWLIST_HEADER, toolAllowlist.joinToString(","))
            )
            if (memoryId != null) {
                toolHeaders.add(HttpHeader(AgentTools.UI_SESSION_HEADER, memoryId.toString()))
            }

            val mcpSyncServers: MutableList<McpServer> = mutableListOf(
                McpServer.Http("agent-tools", "http://localhost:8080/mcp", toolHeaders),
                McpServer.Http("gateway", "http://localhost:8081/mcp", toolHeaders))

            if (chatRequest?.options is ToolCallingChatOptions) {
                val options = chatRequest.options as ToolCallingChatOptions
                toolAllowlist.addAll(options.toolNames)
                options.toolCallbacks
                    .mapNotNull {
                        when (it) {
                            is SyncMcpToolCallback -> Pair(it.toolDefinition, it.toolMetadata)
                            is AsyncMcpToolCallback -> Pair(it.toolDefinition, it.toolMetadata)
                            else -> null
                        }
                    }
                    .mapNotNull {
                        this.mcpProperties.retrieve(it.first, it.second)
                            .orElse(null)
                    }
                    .forEach { mcpSyncServers.add(it) }
            }

            val cwd = workingDirectory.ifBlank { System.getProperty("user.dir") }
            val sessionParams = SessionCreationParameters(cwd, mcpSyncServers)

            val session=  client.newSession(sessionParams)
                { _, _ -> AcpSessionOperations()}

            sessionManager.AcpSessionContext(scope, transport, protocol, client, session)

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

    private fun resolveRole(message: Message): String = when (message) {
        is UserMessage -> MessageType.USER.name
        is SystemMessage -> MessageType.SYSTEM.name
        is AssistantMessage -> MessageType.ASSISTANT.name
        is ToolResponseMessage -> MessageType.TOOL.name
        else -> "user"
    }

    companion object {
    }

    private class AcpSessionOperations : ClientSessionOperations {

        override suspend fun requestPermissions(
            toolCall: SessionUpdate.ToolCallUpdate,
            permissions: List<PermissionOption>,
            _meta: JsonElement?
        ): RequestPermissionResponse {
//            TODO: embabel wait for permission hook.
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
