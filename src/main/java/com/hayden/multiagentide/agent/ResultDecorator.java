package com.hayden.multiagentide.agent;

import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.model.nodes.Collector;

public interface ResultDecorator {

    default <T extends AgentModels.Routing> T decorate(T t, OperationContext context) {
        return t;
    }

    default <T extends AgentModels.AgentResult> T decorate(T t, OperationContext context) {
        return t;
    }

}
