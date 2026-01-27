package com.hayden.multiagentide.agent;

import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.BlackboardHistory;
import com.hayden.multiagentidelib.agent.WorkflowGraphState;
import com.hayden.utilitymodule.acp.events.EventBus;
import com.hayden.utilitymodule.acp.events.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

/**
 * Request decorator that emits ActionStartedEvent before action execution.
 * Uses Integer.MIN_VALUE ordering to ensure it runs first.
 */
@Component
@RequiredArgsConstructor
public class EmitActionStartedRequestDecorator implements RequestDecorator, DispatchedAgentRequestDecorator {

    private final EventBus eventBus;

    @Override
    public int order() {
        return Integer.MIN_VALUE;
    }

    @Override
    public <T extends AgentModels.AgentRequest> T decorate(T request, DecoratorContext context) {
        if (eventBus == null) {
            return request;
        }

        OperationContext operationContext = context.operationContext();
        String agentName = context.agentName();
        String actionName = context.actionName();

        BlackboardHistory.ensureSubscribed(
                eventBus, operationContext,
                () -> WorkflowGraphState.initial(resolveNodeId(operationContext)));

        String nodeId = resolveNodeId(operationContext);
        eventBus.publish(new Events.ActionStartedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                agentName,
                actionName
        ));
        
        return request;
    }

    private static String resolveNodeId(OperationContext context) {
        if (context == null || context.getProcessContext() == null) {
            return "unknown";
        }
        var options = context.getProcessContext().getProcessOptions();
        if (options == null) {
            return "unknown";
        }
        String contextId = options.getContextIdString();
        return contextId != null ? contextId : "unknown";
    }
}
