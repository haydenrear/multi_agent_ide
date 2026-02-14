package com.hayden.multiagentide.controller;

import com.hayden.acp_cdc_ai.acp.events.ArtifactKey;
import com.hayden.acp_cdc_ai.acp.events.EventBus;
import com.hayden.acp_cdc_ai.acp.events.Events;
import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import com.hayden.utilitymodule.git.RepoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoalExecutor {

    private final AgentLifecycleHandler agentLifecycleHandler;

    private EventBus eventBus;

    @Autowired
    @Lazy
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Async
    public void executeGoal(OrchestrationController.StartGoalRequest request, ArtifactKey root) {
        String nodeId = root.value();

        String baseBranch = resolveBaseBranch(request.repositoryUrl(), request.baseBranch());

        try {
            agentLifecycleHandler.initializeOrchestrator(
                    request.repositoryUrl(),
                    baseBranch,
                    request.goal(),
                    request.title(),
                    nodeId
            );
        } catch (Exception e) {
            String message = "Error when attempting to start orchestrator - %s.".formatted(e.getMessage());
            log.error(message, e);
            eventBus.publish(Events.NodeErrorEvent.err(message, root));
        }
    }

    private String resolveBaseBranch(String repositoryUrl, String requestedBaseBranch) {
        if (requestedBaseBranch != null && !requestedBaseBranch.isBlank()) {
            return requestedBaseBranch.trim();
        }
        try (var repository = RepoUtil.findRepo(Path.of(repositoryUrl))) {
            String branch = repository.getBranch();
            if (branch != null && !branch.isBlank()) {
                return branch;
            }
        } catch (Exception e) {
            log.warn("Falling back to main branch for repository {}: {}", repositoryUrl, e.getMessage());
        }
        return "main";
    }

}
