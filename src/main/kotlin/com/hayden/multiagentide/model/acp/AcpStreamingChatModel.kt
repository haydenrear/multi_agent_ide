package com.hayden.multiagentide.model.acp

import dev.langchain4j.data.message.ChatMessage
import dev.langchain4j.data.message.UserMessage
import dev.langchain4j.model.chat.StreamingChatModel
import dev.langchain4j.model.chat.response.ChatResponse
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler

/**
 * ACP-backed StreamingChatModel implementation.
 */
class AcpStreamingChatModel(private val delegate: AcpChatModel) : StreamingChatModel {

    override fun chat(userMessage: String, handler: StreamingChatResponseHandler) {
        val response = delegate.chat(listOf(UserMessage.from(userMessage)))
        val content = response.aiMessage()?.text().orEmpty()
        handler.onPartialResponse(content)
        handler.onCompleteResponse(response)
    }

    override fun chat(messages: List<ChatMessage>, handler: StreamingChatResponseHandler) {
        val response = delegate.chat(messages)
        val content = response.aiMessage()?.text().orEmpty()
        handler.onPartialResponse(content)
        handler.onCompleteResponse(response)
    }
}
