package com.hayden.multiagentide.config;

import com.hayden.multiagentide.model.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;

/**
 * Handles agent lifecycle events for the multi-agent system.
 * Manages node creation, updates, and removal in the computation graph
 * when agents are invoked (including when invoked by other agents).
 */
public class AgentLifecycleHandler {

    private static final Logger logger = LoggerFactory.getLogger(AgentLifecycleHandler.class);
    private final ComputationGraphOrchestrator orchestrator;
    private final ThreadLocal<String> currentNodeId = new ThreadLocal<>();

    public AgentLifecycleHandler(ComputationGraphOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
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
