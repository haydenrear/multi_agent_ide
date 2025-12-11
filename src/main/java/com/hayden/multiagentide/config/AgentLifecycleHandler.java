package com.hayden.multiagentide.config;

import com.hayden.multiagentide.agent.GraphAgentFactory;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.MainWorktreeContext;
import com.hayden.multiagentide.model.Spec;
import com.hayden.multiagentide.model.SubmoduleNode;
import com.hayden.multiagentide.model.SubmoduleWorktreeContext;
import com.hayden.multiagentide.model.mixins.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.SpecRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.SpecService;
import com.hayden.multiagentide.service.WorktreeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * Handles agent lifecycle events for the multi-agent system.
 * Manages node creation, updates, and removal in the computation graph
 * when agents are invoked (including when invoked by other agents).
 */
@Component
@RequiredArgsConstructor
public class AgentLifecycleHandler {

    private static final Logger logger = LoggerFactory.getLogger(AgentLifecycleHandler.class);
    private final ThreadLocal<String> currentNodeId = new ThreadLocal<>();

    private final ComputationGraphOrchestrator orchestrator;
    private final GraphRepository graphRepository;
    private final WorktreeRepository worktreeRepository;
    private final SpecRepository specRepository;
    private final WorktreeService worktreeService;
    private final SpecService specService;

    public void beforeOrchestrator(String repositoryUrl, String baseBranch,
                                   String goal, String title) {
        initializeOrchestrator(repositoryUrl, baseBranch, goal, title);
    }

    public void afterOrchestrator(String s) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Initialize an orchestrator node with a goal.
     * Creates main worktree, submodule worktrees, and base spec.
     */
    public void initializeOrchestrator(String repositoryUrl, String baseBranch,
                                       String goal, String title) {
        String nodeId = UUID.randomUUID().toString();

        // Create main worktree
        MainWorktreeContext mainWorktree = worktreeService.createMainWorktree(
                repositoryUrl, baseBranch, nodeId);

        // Create orchestrator node
        OrchestratorNode orchestrator = new OrchestratorNode(
                nodeId,
                title,
                goal,
                GraphNode.NodeStatus.READY,
                null,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                repositoryUrl,
                baseBranch,
                mainWorktree.hasSubmodules(),
                worktreeService.getSubmoduleNames(mainWorktree.worktreePath()),
                mainWorktree.worktreeId(),
                new ArrayList<>(),
                null,
                null,
                new ArrayList<>()
        );

        // Create spec
        Spec baseSpec = specService.createSpec(
                mainWorktree.worktreeId(),
                mainWorktree.worktreePath().resolve("SPEC.md"),
                goal
        );

        // Update orchestrator with spec ID
        orchestrator = new OrchestratorNode(
                orchestrator.nodeId(),
                orchestrator.title(),
                orchestrator.goal(),
                orchestrator.status(),
                orchestrator.parentNodeId(),
                orchestrator.childNodeIds(),
                orchestrator.metadata(),
                orchestrator.createdAt(),
                orchestrator.lastUpdatedAt(),
                orchestrator.repositoryUrl(),
                orchestrator.baseBranch(),
                orchestrator.hasSubmodules(),
                orchestrator.submoduleNames(),
                orchestrator.mainWorktreeId(),
                orchestrator.submoduleWorktreeIds(),
                baseSpec.specId(),
                orchestrator.orchestratorOutput(),
                new ArrayList<>()
        );

        // Create submodule worktrees
        if (mainWorktree.hasSubmodules()) {
            List<String> submoduleWorktreeIds = new ArrayList<>();
            for (String submoduleName : orchestrator.submoduleNames()) {
                Path submodulePath = worktreeService.getSubmodulePath(
                        mainWorktree.worktreePath(), submoduleName);
                SubmoduleWorktreeContext subWorktree = worktreeService.createSubmoduleWorktree(
                        submoduleName, submodulePath.toString(),
                        mainWorktree.worktreeId(), mainWorktree.worktreePath(),
                        nodeId
                );
                submoduleWorktreeIds.add(subWorktree.worktreeId());
                worktreeRepository.save(subWorktree);

                // Emit worktree created event
                this.orchestrator.emitWorktreeCreatedEvent(subWorktree.worktreeId(), nodeId,
                        subWorktree.worktreePath().toString(), "submodule", submoduleName);
            }

            // Update orchestrator with submodule worktree IDs
            var submodule = new SubmoduleNode(
                    orchestrator.nodeId(),
                    orchestrator.title(),
                    orchestrator.goal(),
                    orchestrator.status(),
                    orchestrator.parentNodeId(),
                    orchestrator.childNodeIds(),
                    orchestrator.metadata(),
                    orchestrator.createdAt(),
                    orchestrator.lastUpdatedAt(),
                    orchestrator.repositoryUrl(),
                    orchestrator.baseBranch(),
                    orchestrator.hasSubmodules(),
                    orchestrator.submoduleNames(),
                    orchestrator.mainWorktreeId(),
                    submoduleWorktreeIds,
                    orchestrator.specFileId(),
                    orchestrator.orchestratorOutput()
            );

            orchestrator.submodules().add(submodule);
        }

        // Emit events
        this.orchestrator.emitNodeAddedEvent(orchestrator.nodeId(), orchestrator.title(),
                orchestrator.nodeType(), orchestrator.parentNodeId());
        this.orchestrator.emitWorktreeCreatedEvent(mainWorktree.worktreeId(), nodeId,
                mainWorktree.worktreePath().toString(), "main", null);

        // Save to repositories
        graphRepository.save(orchestrator);
        worktreeRepository.save(mainWorktree);
        specRepository.save(baseSpec);
    }

    /**
     * Handle before-agent-invocation for Planning Agent.
     * Registers a planning node in the computation graph.
     */
    public void beforePlanningAgentInvocation(String goal, String parentNodeId) {
        String nodeId = UUID.randomUUID().toString();
        currentNodeId.set(nodeId);

        PlanningNode planningNode = new PlanningNode(
                nodeId,
                "Plan & Decompose",
                goal,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                "",
                null,
                0,
                0
        );

        orchestrator.addChildNode(parentNodeId, planningNode);
        logger.info("Planning node {} registered for goal: {}", nodeId, goal);
    }

    /**
     * Handle after-agent-invocation for Planning Agent.
     * Updates planning node with results and marks as completed.
     */
    public void afterPlanningAgentInvocation(String planContent) {
        String nodeId = currentNodeId.get();
        if (nodeId == null) {
            logger.warn("No planning node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof PlanningNode) {
            PlanningNode node = (PlanningNode) nodeOpt.get();
            PlanningNode updated = new PlanningNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.generatedTicketIds(),
                    planContent,
                    node.specFileId(),
                    node.estimatedSubtasks(),
                    node.completedSubtasks()
            );
            // Save updated node to repository (would be done through orchestrator)
            logger.info("Planning node {} updated with plan content", nodeId);
        }

        currentNodeId.remove();
    }

    /**
     * Handle before-agent-invocation for Editor Agent.
     * Registers an editor node in the computation graph.
     */
    public void beforeEditorAgentInvocation(String goal, String context, String parentNodeId) {
        String nodeId = UUID.randomUUID().toString();
        currentNodeId.set(nodeId);

        WorkNode editorNode = new WorkNode(
                nodeId,
                "Code Generation",
                goal,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                null,
                new ArrayList<>(),
                null,
                0,
                1,
                "editor",
                "",
                false,
                0
        );

        orchestrator.addChildNode(parentNodeId, editorNode);
        logger.info("Editor node {} registered for goal: {}", nodeId, goal);
    }

    /**
     * Handle after-agent-invocation for Editor Agent.
     * Updates editor node with generated code and marks as completed.
     */
    public void afterEditorAgentInvocation(String generatedCode) {
        String nodeId = currentNodeId.get();
        if (nodeId == null) {
            logger.warn("No editor node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof WorkNode) {
            WorkNode node = (WorkNode) nodeOpt.get();
            WorkNode updated = new WorkNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.mainWorktreeId(),
                    node.submoduleWorktreeIds(),
                    node.specFileId(),
                    1,
                    1,
                    node.agentType(),
                    generatedCode,
                    node.mergeRequired(),
                    0
            );
            logger.info("Editor node {} updated with generated code", nodeId);
        }

        currentNodeId.remove();
    }

    /**
     * Handle before-agent-invocation for Merger Agent.
     * Registers a merger node in the computation graph.
     */
    public void beforeMergerAgentInvocation(String childGoal, String parentGoal, String parentNodeId) {
        String nodeId = UUID.randomUUID().toString();
        currentNodeId.set(nodeId);

        WorkNode mergerNode = new WorkNode(
                nodeId,
                "Code Merge",
                "Merge and integrate changes",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                null,
                new ArrayList<>(),
                null,
                0,
                1,
                "merger",
                "",
                true,
                0
        );

        orchestrator.addChildNode(parentNodeId, mergerNode);
        logger.info("Merger node {} registered for merge", nodeId);
    }

    /**
     * Handle after-agent-invocation for Merger Agent.
     * Updates merger node with merge strategy and marks as completed.
     */
    public void afterMergerAgentInvocation(String mergeStrategy) {
        String nodeId = currentNodeId.get();
        if (nodeId == null) {
            logger.warn("No merger node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof WorkNode) {
            WorkNode node = (WorkNode) nodeOpt.get();
            WorkNode updated = new WorkNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.mainWorktreeId(),
                    node.submoduleWorktreeIds(),
                    node.specFileId(),
                    1,
                    1,
                    node.agentType(),
                    mergeStrategy,
                    node.mergeRequired(),
                    0
            );
            logger.info("Merger node {} updated with merge strategy", nodeId);
        }

        currentNodeId.remove();
    }

    /**
     * Handle before-agent-invocation for Review Agent.
     * Registers a review node in the computation graph.
     */
    public void beforeReviewAgentInvocation(String content, String criteria, String parentNodeId) {
        String nodeId = UUID.randomUUID().toString();
        currentNodeId.set(nodeId);

        AgentReviewNode reviewNode = new AgentReviewNode(
                nodeId,
                "Code Review",
                "Review and evaluate work",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                nodeId,
                content,
                false,
                "",
                "agent",
                null,
                null
        );

        orchestrator.addChildNode(parentNodeId, reviewNode);
        logger.info("Review node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Review Agent.
     * Updates review node with evaluation and marks as completed.
     */
    public void afterReviewAgentInvocation(String evaluation) {
        String nodeId = currentNodeId.get();
        if (nodeId == null) {
            logger.warn("No review node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof AgentReviewNode) {
            AgentReviewNode node = (AgentReviewNode) nodeOpt.get();
            boolean approved = evaluation.toLowerCase().contains("approved") ||
                    evaluation.toLowerCase().contains("pass");
            AgentReviewNode updated = new AgentReviewNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.reviewedNodeId(),
                    node.reviewContent(),
                    approved,
                    evaluation,
                    node.reviewerAgentType(),
                    Instant.now(),
                    node.specFileId()
            );
            logger.info("Review node {} updated with evaluation", nodeId);
        }

        currentNodeId.remove();
    }

    /**
     * Clear current node context (for error cases).
     */
    public void clearContext() {
        currentNodeId.remove();
    }
}
