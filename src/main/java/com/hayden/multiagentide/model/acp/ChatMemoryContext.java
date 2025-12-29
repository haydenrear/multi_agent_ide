package com.hayden.multiagentide.model.acp;

import org.springframework.ai.chat.messages.Message;

import java.util.List;

public interface ChatMemoryContext {

    List<Message> getMessages(Object memoryId);
}
