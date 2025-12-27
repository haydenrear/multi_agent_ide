package com.hayden.multiagentide.model.acp;

import dev.langchain4j.data.message.ChatMessage;
import java.util.List;

public interface ChatMemoryContext {

    List<ChatMessage> getMessages(Object memoryId);
}
