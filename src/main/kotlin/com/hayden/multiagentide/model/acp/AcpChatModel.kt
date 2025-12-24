package com.hayden.multiagentide.model.acp

import com.agentclientprotocol.agent.AgentInfo
import com.agentclientprotocol.agent.AgentSession
import com.agentclientprotocol.agent.AgentSupport
import com.agentclientprotocol.agent.clientInfo
import com.agentclientprotocol.client.Client
import com.agentclientprotocol.client.ClientInfo
import com.agentclientprotocol.client.ClientOperationsFactory
import com.agentclientprotocol.client.ClientSession
import com.agentclientprotocol.common.ClientSessionOperations
import com.agentclientprotocol.common.Event
import com.agentclientprotocol.common.FileSystemOperations
import com.agentclientprotocol.common.SessionCreationParameters
import com.agentclientprotocol.common.SessionParameters
import com.agentclientprotocol.model.*
import com.agentclientprotocol.protocol.Protocol
import com.agentclientprotocol.transport.Transport
import com.fasterxml.jackson.databind.ObjectMapper
import com.hayden.multiagentide.config.AcpModelProperties
import dev.langchain4j.data.message.*
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.model.chat.listener.ChatModelListener
import dev.langchain4j.model.chat.request.ChatRequest
import dev.langchain4j.model.chat.response.ChatResponse
import dev.langchain4j.model.openai.internal.OpenAiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.io.asSink
import kotlinx.io.asSource
import kotlinx.io.buffered
import kotlinx.serialization.json.JsonElement
import java.util.*

/**
 * ACP-backed ChatModel implementation using the agentclientprotocol SDK.
 */
class AcpChatModel(private val properties: AcpModelProperties) : ChatModel {

    private val sessionLock = Any()

    private val listeners: List<ChatModelListener>  = mutableListOf();

    @Volatile
    private var sessionContext: AcpSessionContext? = null

    override fun listeners(): List<ChatModelListener?>? {
        return listeners;
    }

    override fun doChat(chatRequest: ChatRequest?): ChatResponse {
        return if (sessionExists(chatRequest)) {
            invokeChat(mutableListOf(chatRequest?.messages()?.last()!!))
        } else {
            invokeChat(chatRequest?.messages()!!)
        }
    }

    fun invokeChat(messages: List<ChatMessage>): ChatResponse = runBlocking {
        val session = getOrCreateSession()
        val responseText = StringBuilder()
        val content = listOf(ContentBlock.Text(formatMessages(messages)))

        session.prompt(content).collect { event ->
            if (event is Event.SessionUpdateEvent) {
                val update = event.update
                if (update is SessionUpdate.AgentMessageChunk) {
                    val block = update.content
                    if (block is ContentBlock.Text) {
                        responseText.append(block.text)
                    }
                }
            }
        }

        ChatResponse.builder()
            .aiMessage(AiMessage.from(responseText.toString()))
            .build()
    }

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

    private fun getOrCreateSession(): ClientSession {
        return getOrCreateSession(null)
    }

    private fun sessionExists(chatRequest: ChatRequest?): Boolean {
        return sessionContext != null
    }

    private fun getOrCreateSession(chatRequest: ChatRequest?): ClientSession {
        sessionContext?.let { return it.session }
        synchronized(sessionLock) {
            if (sessionContext == null) {
                sessionContext = runBlocking { createSessionContext(chatRequest) }
            }
        }
        return requireNotNull(sessionContext).session
    }

    private suspend fun createSessionContext(chatRequest: ChatRequest?): AcpSessionContext {
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
            val i = AcpMethod.AgentMethods.Initialize;
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
                    McpServer.Http("agent-tools", "http://localhost:8080/mcp", mutableListOf()),
//                    TODO: add mcp-tool-gateway as Http - Stdio takes too long to start
//                    McpServer
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

    private fun formatMessages(messages: List<ChatMessage>): String {
        if (messages.isEmpty()) {
            return ""
        }
        val builder = StringBuilder()
        messages.forEach { message ->
            val role = resolveRole(message)
            if (builder.isNotEmpty()) {
                builder.append('\n')
            }
            when(message) {
                is UserMessage -> builder.append(role + ": " + fromContents(message.contents()))
                is AiMessage -> builder.append(role + ": " + message.text())
                is SystemMessage -> builder.append(role + ": " + message.text())
                is CustomMessage -> {}
                is ToolExecutionResultMessage -> builder.append(role + ": " + message.text())
            }
        }
        return builder.toString()
    }

    private fun fromContents(contents: List<Content>): String {
        return contents.map { fromContent(it) }
            .joinToString("\n")
    }

    private fun fromContent(contents: Content): String {
        return when(contents) {
            is TextContent -> contents.text();
            is AudioContent -> "";
            is PdfFileContent -> "";
            is ImageContent -> "";
            is VideoContent -> "";

            else -> ""
        }
    }

    private fun resolveRole(message: ChatMessage): String = when (message) {
        is UserMessage -> "user"
        is SystemMessage -> "system"
        is AiMessage -> "assistant"
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
            val selection = permissions.firstOrNull()
                ?: return RequestPermissionResponse(RequestPermissionOutcome.Cancelled, _meta)
            return RequestPermissionResponse(RequestPermissionOutcome.Selected(selection.optionId), _meta)
        }

        override suspend fun notify(notification: SessionUpdate, _meta: JsonElement?) {
        }
    }
}
