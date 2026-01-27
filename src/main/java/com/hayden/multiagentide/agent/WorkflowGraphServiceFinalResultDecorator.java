package com.hayden.multiagentide.agent;

import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.model.nodes.DiscoveryNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkflowGraphServiceFinalResultDecorator implements FinalResultDecorator {

    private final WorkflowGraphService graphService;

    @Override
    public <T extends AgentModels.AgentResult> T decorateFinalResult(T t, FinalResultDecoratorContext context) {
        if (t == null) {
            return t;
        }

        AgentModels.AgentResult completeRes = switch (t) {
            case AgentModels.OrchestratorCollectorResult res -> {
                if (context != null && context.decoratorContext() != null) {
                    graphService.completeOrchestratorCollectorResult(
                            context.decoratorContext().operationContext(),
                            res
                    );
                }
                yield res;
            }
            default -> {
                log.error("Found unexpected decorate for decoreateFinalResult.");
                yield t;
            }
        };
        return (T) completeRes;
    }
}
