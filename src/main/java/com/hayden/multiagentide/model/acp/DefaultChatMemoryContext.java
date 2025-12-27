package com.hayden.multiagentide.model.acp;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.service.memory.ChatMemoryService;
import java.util.List;

public class DefaultChatMemoryContext implements ChatMemoryContext {

    private final ChatMemoryService chatMemoryService;

    public DefaultChatMemoryContext(ChatMemoryProvider chatMemoryProvider) {
        this.chatMemoryService = new ChatMemoryService(chatMemoryProvider);
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        if (memoryId == null) {
            return List.of();
        }
        ChatMemory chatMemory = chatMemoryService.getOrCreateChatMemory(memoryId);
        return chatMemory.messages();
    }
}
