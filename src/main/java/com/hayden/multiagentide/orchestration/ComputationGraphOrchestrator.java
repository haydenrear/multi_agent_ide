package com.hayden.multiagentide.orchestration;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.events.Events;
import com.hayden.multiagentide.model.nodes.*;
import com.hayden.multiagentide.model.spec.Spec;
import com.hayden.multiagentide.model.worktree.WorktreeContext;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.SpecRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;

import java.time.Instant;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Main orchestrator for the computation graph.
 * Manages node execution, event emission, and worktree/spec lifecycle.
 */
@Service
@RequiredArgsConstructor
public class ComputationGraphOrchestrator {

    private final GraphRepository graphRepository;
    private final WorktreeRepository worktreeRepository;
    private final SpecRepository specRepository;
    private final EventBus eventBus;

    /**
     * Get a node from the graph.
     */
    public Optional<GraphNode> getNode(String nodeId) {
        return Optional.ofNullable(nodeId).flatMap(graphRepository::findById);
    }

    /**
     * Get all nodes in the graph.
     */
    public List<GraphNode> getAllNodes() {
        return graphRepository.findAll();
    }

    /**
     * Get child nodes of a parent.
     */
    public List<GraphNode> getChildNodes(String parentNodeId) {
        return graphRepository.findByParentId(parentNodeId);
    }

    /**
     * Add a child node to parent.
     */
    public void addChildNodeAndEmitEvent(
            String parentNodeId,
            GraphNode childNode
    ) {
        Optional<GraphNode> parentOpt = graphRepository.findById(parentNodeId);
        if (parentOpt.isEmpty()) {
            throw new RuntimeException("Parent node not found: " + parentNodeId);
        }

        GraphNode parent = parentOpt.get();
        List<String> childIds = new ArrayList<>(parent.childNodeIds());
        childIds.add(childNode.nodeId());

        // Update parent based on type
        GraphNode updatedParent = updateNodeChildren(parent, childIds);
        graphRepository.save(updatedParent);
        graphRepository.save(childNode);

        emitNodeAddedEvent(
                childNode.nodeId(),
                childNode.title(),
                childNode.nodeType(),
                parentNodeId
        );
    }

    /**
     * Get all worktrees.
     */
    public List<WorktreeContext> getAllWorktrees() {
        return worktreeRepository.findAll();
    }

    /**
     * Get worktrees for a node.
     */
    public List<WorktreeContext> getWorktreesForNode(String nodeId) {
        return worktreeRepository.findByNodeId(nodeId);
    }

    /**
     * Get all specs.
     */
    public List<Spec> getAllSpecs() {
        return specRepository.findAll();
    }

    /**
     * Detect goal completion.
     * Goal is complete when all leaf nodes are COMPLETED or PRUNED,
     * and all worktrees are merged or discarded.
     */
    public boolean isGoalComplete(String orchestratorNodeId) {
        Optional<GraphNode> orchestratorOpt = graphRepository.findById(
                orchestratorNodeId
        );
        if (orchestratorOpt.isEmpty()) {
            return false;
        }

        // Check all nodes in graph
        for (GraphNode node : graphRepository.findAll()) {
            if (
                    node.status() == GraphNode.NodeStatus.RUNNING ||
                            node.status() == GraphNode.NodeStatus.WAITING_REVIEW ||
                            node.status() == GraphNode.NodeStatus.WAITING_INPUT ||
                            node.status() == GraphNode.NodeStatus.PENDING
            ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Emit node added event.
     */
    public void emitNodeAddedEvent(
            String nodeId,
            String title,
            GraphNode.NodeType nodeType,
            String parentId
    ) {
        Events.NodeAddedEvent event = new Events.NodeAddedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                title,
                nodeType,
                parentId
        );
        eventBus.publish(event);
    }

    /**
     * Emit status changed event.
     */
    public void emitStatusChangeEvent(
            String nodeId,
            GraphNode.NodeStatus oldStatus,
            GraphNode.NodeStatus newStatus,
            String reason
    ) {
        Events.NodeStatusChangedEvent event = new Events.NodeStatusChangedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                oldStatus,
                newStatus,
                reason
        );
        eventBus.publish(event);
    }

    public void emitErrorEvent(
            String s,
            String string,
            String simpleName,
            String errorMessage
    ) {
        //        TODO: add event and implement
        //        Events.NodeStatusChangedEvent event = new Events.NodeStatusChangedEvent(
        //                UUID.randomUUID().toString(),
        //                Instant.now(),
        //                nodeId,
        //                oldStatus,
        //                newStatus,
        //                reason
        //        );
        //        eventBus.publish(event);
    }

    /**
     * Emit worktree created event.
     */
    public void emitWorktreeCreatedEvent(
            String worktreeId,
            String nodeId,
            String path,
            String type,
            String submoduleName
    ) {
        Events.WorktreeCreatedEvent event = new Events.WorktreeCreatedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                worktreeId,
                nodeId,
                path,
                type,
                submoduleName
        );
        eventBus.publish(event);
    }

    public void emitReviewRequestedEvent(
            String nodeId,
            String reviewNodeId,
            String reviewType,
            String contentToReview
    ) {
        Events.NodeReviewRequestedEvent event = new Events.NodeReviewRequestedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                reviewNodeId,
                reviewType,
                contentToReview
        );
        eventBus.publish(event);
    }

    /**
     * Helper to update node children based on type.
     */
    public GraphNode updateNodeChildren(
            GraphNode parent,
            List<String> childIds
    ) {
        return switch (parent) {
            case OrchestratorNode p ->
                    new OrchestratorNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            p.lastUpdatedAt(),
                            p.repositoryUrl(),
                            p.baseBranch(),
                            p.hasSubmodules(),
                            p.submoduleNames(),
                            p.mainWorktreeId(),
                            p.submoduleWorktreeIds(),
                            p.specFileId(),
                            p.orchestratorOutput(),
                            p.submodules()
                    );
            case PlanningNode p ->
                    new PlanningNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            p.lastUpdatedAt(),
                            p.generatedTicketIds(),
                            p.planContent(),
                            p.specFileId(),
                            p.estimatedSubtasks(),
                            p.completedSubtasks()
                    );
            case EditorNode p -> new EditorNode(
                    p.nodeId(),
                    p.title(),
                    p.goal(),
                    p.status(),
                    p.parentNodeId(),
                    childIds,
                    p.metadata(),
                    p.createdAt(),
                    p.lastUpdatedAt(),
                    p.mainWorktreeId(),
                    p.submoduleWorktreeIds(),
                    p.specFileId(),
                    p.completedSubtasks(),
                    p.totalSubtasks(),
                    p.agentType(),
                    p.workOutput(),
                    p.mergeRequired(),
                    p.streamingTokenCount()
            );
            case DiscoveryOrchestratorNode p ->
                    new DiscoveryOrchestratorNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            Instant.now(),
                            p.summaryContent(),
                            p.totalTasksCompleted(),
                            p.totalTasksFailed(),
                            p.specFileId()
                    );
            case DiscoveryNode p ->
                    new DiscoveryNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            Instant.now(),
                            p.summaryContent(),
                            p.totalTasksCompleted(),
                            p.totalTasksFailed(),
                            p.specFileId()
                    );
            case DiscoveryCollectorNode p ->
                    new DiscoveryCollectorNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            Instant.now(),
                            p.summaryContent(),
                            p.totalTasksCompleted(),
                            p.totalTasksFailed(),
                            p.specFileId()
                    );
            case PlanningOrchestratorNode p ->
                    new PlanningOrchestratorNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            Instant.now(),
                            p.generatedTicketIds(),
                            p.planContent(),
                            p.specFileId(),
                            p.estimatedSubtasks(),
                            p.completedSubtasks()
                    );
            case PlanningCollectorNode p ->
                    new PlanningCollectorNode(
                            p.nodeId(),
                            p.title(),
                            p.goal(),
                            p.status(),
                            p.parentNodeId(),
                            childIds,
                            p.metadata(),
                            p.createdAt(),
                            Instant.now(),
                            p.generatedTicketIds(),
                            p.planContent(),
                            p.specFileId(),
                            p.estimatedSubtasks(),
                            p.completedSubtasks()
                    );
            case CollectorNode p -> p.toBuilder()
                    .childNodeIds(childIds)
                    .lastUpdatedAt(Instant.now())
                    .build();
            case MergeNode p -> new MergeNode(
                    p.nodeId(),
                    p.title(),
                    p.goal(),
                    p.status(),
                    p.parentNodeId(),
                    childIds,
                    p.metadata(),
                    p.createdAt(),
                    Instant.now(),
                    p.summaryContent(),
                    p.totalTasksCompleted(),
                    p.totalTasksFailed(),
                    p.specFileId()
            );
            case ReviewNode p -> new ReviewNode(
                    p.nodeId(),
                    p.title(),
                    p.goal(),
                    p.status(),
                    p.parentNodeId(),
                    childIds,
                    p.metadata(),
                    p.createdAt(),
                    Instant.now(),
                    p.reviewedNodeId(),
                    p.reviewContent(),
                    p.approved(),
                    p.humanFeedbackRequested(),
                    p.agentFeedback(),
                    p.reviewerAgentType(),
                    p.reviewCompletedAt(),
                    p.specFileId()
            );
            case SummaryNode p -> new SummaryNode(
                    p.nodeId(),
                    p.title(),
                    p.goal(),
                    p.status(),
                    p.parentNodeId(),
                    childIds,
                    p.metadata(),
                    p.createdAt(),
                    Instant.now(),
                    p.summarizedNodeIds(),
                    p.summaryContent(),
                    p.totalTasksCompleted(),
                    p.totalTasksFailed(),
                    p.specFileId()
            );
        };
    }
}
