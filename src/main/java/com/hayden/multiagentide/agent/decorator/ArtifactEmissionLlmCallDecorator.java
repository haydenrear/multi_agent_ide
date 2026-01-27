package com.hayden.multiagentide.agent.decorator;

import org.springframework.stereotype.Component;

@Component
public class ArtifactEmissionLlmCallDecorator implements LlmCallDecorator {
    @Override
    public int order() {
        return 10_000;
    }
}
