package com.hayden.multiagentide.model.nodes;

import java.time.Instant;
import java.util.*;

/**
 * Node that breaks down goals into work tickets.
 * Can be Reviewable.
 */
public record PlanningCollectorNode(
        String nodeId,
        String title,
        String goal,
        NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,

        // Planning-specific fields
        List<String> generatedTicketIds,
        String planContent,
        int estimatedSubtasks,
        int completedSubtasks
) implements GraphNode, Viewable<String>, Orchestrator {

    public PlanningCollectorNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (goal == null || goal.isEmpty()) throw new IllegalArgumentException("goal required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
        if (generatedTicketIds == null) generatedTicketIds = new ArrayList<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.PLANNING;
    }

    @Override
    public String getView() {
        return planContent;
    }

    /**
     * Create an updated version with new status.
     */
    public PlanningCollectorNode withStatus(NodeStatus newStatus) {
        return new PlanningCollectorNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                generatedTicketIds, planContent,
                estimatedSubtasks, completedSubtasks
        );
    }

    /**
     * Add a generated ticket.
     */
    public PlanningCollectorNode addTicket(String ticketId) {
        List<String> newTickets = new ArrayList<>(generatedTicketIds);
        newTickets.add(ticketId);
        return new PlanningCollectorNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                newTickets, planContent,
                estimatedSubtasks, completedSubtasks
        );
    }

    /**
     * Update plan content.
     */
    public PlanningCollectorNode withPlanContent(String content) {
        return new PlanningCollectorNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                generatedTicketIds, content,
                estimatedSubtasks, completedSubtasks
        );
    }

    /**
     * Update progress.
     */
    public PlanningCollectorNode withProgress(int completed, int total) {
        return new PlanningCollectorNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                generatedTicketIds, planContent,
                total, completed
        );
    }
}
