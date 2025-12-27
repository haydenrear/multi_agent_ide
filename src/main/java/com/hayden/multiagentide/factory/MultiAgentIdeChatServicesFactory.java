package com.hayden.multiagentide.factory;

import dev.langchain4j.service.AiServiceContext;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MultiAgentAcpAiServices;
import dev.langchain4j.spi.services.AiServicesFactory;

public class MultiAgentIdeChatServicesFactory implements AiServicesFactory {

    @Override
    public <T> AiServices<T> create(AiServiceContext context) {
        return new MultiAgentAcpAiServices<>(context);
    }
}
