package com.hayden.multiagentide.controller;

import com.hayden.multiagentide.model.GraphNode;
import com.hayden.multiagentide.model.OrchestratorNode;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * REST controller for orchestration operations.
 * Provides endpoints for initializing goals, executing nodes, and checking status.
 */
@RestController
@RequestMapping("/api/orchestration")
public class OrchestrationController {

    private final ComputationGraphOrchestrator orchestrator;

    public OrchestrationController(ComputationGraphOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    /**
     * Initialize a new orchestrator node with a goal.
     */
    @PostMapping("/initialize")
    public ResponseEntity<OrchestratorNode> initializeGoal(
            @RequestParam String repositoryUrl,
            @RequestParam(defaultValue = "main") String baseBranch,
            @RequestParam String goal,
            @RequestParam(defaultValue = "Goal") String title) {

        OrchestratorNode orchestratorNode = orchestrator.initializeOrchestrator(
                repositoryUrl, baseBranch, goal, title);

        return ResponseEntity.ok(orchestratorNode);
    }

}
