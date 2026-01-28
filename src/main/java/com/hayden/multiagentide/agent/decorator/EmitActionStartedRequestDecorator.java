package com.hayden.multiagentide.agent.decorator;

import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentide.agent.DecoratorContext;
import com.hayden.multiagentide.artifacts.ExecutionScopeService;
import com.hayden.multiagentide.embabel.EmbabelUtil;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.BlackboardHistory;
import com.hayden.multiagentidelib.agent.WorkflowGraphState;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
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

    private final ExecutionScopeService scopeService;

    @Override
    public int order() {
        return 10_000;
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
                () -> WorkflowGraphState.initial(request.key().value()));

        String nodeId = request.key().value();

        eventBus.publish(new Events.ActionStartedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                agentName,
                actionName
        ));

        if (request instanceof AgentModels.OrchestratorRequest first) {
            scopeService.startExecution(EmbabelUtil.extractWorkflowRunId(context.operationContext()), first.key());
        }

        return request;
    }

}
