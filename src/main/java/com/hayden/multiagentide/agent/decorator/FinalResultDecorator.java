package com.hayden.multiagentide.agent.decorator;

import com.hayden.multiagentide.agent.DecoratorContext;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.utilitymodule.acp.events.Artifact;

public interface FinalResultDecorator extends ResultDecorator {

    record FinalResultDecoratorContext(AgentModels.AgentRequest originalRequest, DecoratorContext decoratorContext) {
    }

    default <T extends AgentModels.Routing> T decorateFinalResult(T t, FinalResultDecoratorContext context) {
        return this.decorate(t, context.decoratorContext);
    }

    default <T extends AgentModels.AgentResult> T decorateFinalResult(T t, FinalResultDecoratorContext context) {
        return this.decorate(t, context.decoratorContext);
    }

}
