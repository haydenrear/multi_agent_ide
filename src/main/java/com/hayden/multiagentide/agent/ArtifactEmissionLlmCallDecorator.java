package com.hayden.multiagentide.agent;

import org.springframework.stereotype.Component;

@Component
public class ArtifactEmissionLlmCallDecorator implements LlmCallDecorator {
    @Override
    public int order() {
        return LlmCallDecorator.super.order();
    }
}
