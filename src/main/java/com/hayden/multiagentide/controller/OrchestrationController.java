package com.hayden.multiagentide.controller;

import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.hayden.acp_cdc_ai.acp.events.ArtifactKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for orchestration operations.
 * Provides endpoints for initializing goals, executing nodes, and checking status.
 */
@Slf4j
@RestController
@RequestMapping("/api/orchestrator")
@RequiredArgsConstructor
public class OrchestrationController {

    private final AgentLifecycleHandler agentLifecycleHandler;

//    private static final ExecutorService exec =

    @PostMapping("/start")
    public StartGoalResponse startGoal(@RequestBody StartGoalRequest request) {
        return startGoalAsync(request);
    }

    public StartGoalResponse startGoalAsync(StartGoalRequest request) {
        if (request == null || request.goal() == null || request.goal().isBlank()) {
            throw new IllegalArgumentException("goal is required");
        }
        if (request.repositoryUrl() == null || request.repositoryUrl().isBlank()) {
            throw new IllegalArgumentException("repositoryUrl is required");
        }

        String nodeId = ArtifactKey.createRoot().value();

        String baseBranch = (request.baseBranch() == null || request.baseBranch().isBlank())
                ? "main"
                : request.baseBranch();

//      TODO: run this on a special executor service (virtual ???) BUT make sure thread locals still work...
        CompletableFuture.runAsync(() -> agentLifecycleHandler.initializeOrchestrator(
                request.repositoryUrl(),
                baseBranch,
                request.goal(),
                request.title(),
                nodeId
        )).exceptionally(t -> {
            log.error("Error when attempting to start orchestrator - {}.", t.getMessage(), t);
            return null;
        });

        return new StartGoalResponse(nodeId);
    }

    public record StartGoalRequest(
            String goal,
            String repositoryUrl,
            String baseBranch,
            String title
    ) {}

    public record StartGoalResponse(String nodeId) {}
}
