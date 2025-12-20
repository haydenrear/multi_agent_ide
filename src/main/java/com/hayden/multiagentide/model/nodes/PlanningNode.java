package com.hayden.multiagentide.model.nodes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node that breaks down goals into work tickets.
 * Can be Reviewable.
 */
public record PlanningNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
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
) implements GraphNode, Viewable<String> {

    public PlanningNode {
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
    public PlanningNode withStatus(GraphNode.NodeStatus newStatus) {
        return new PlanningNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                generatedTicketIds, planContent,
                estimatedSubtasks, completedSubtasks
        );
    }

    /**
     * Add a generated ticket.
     */
    public PlanningNode addTicket(String ticketId) {
        List<String> newTickets = new ArrayList<>(generatedTicketIds);
        newTickets.add(ticketId);
        return new PlanningNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                newTickets, planContent,
                estimatedSubtasks, completedSubtasks
        );
    }

    /**
     * Update plan content.
     */
    public PlanningNode withPlanContent(String content) {
        return new PlanningNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                generatedTicketIds, content,
                estimatedSubtasks, completedSubtasks
        );
    }

    /**
     * Update progress.
     */
    public PlanningNode withProgress(int completed, int total) {
        return new PlanningNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                generatedTicketIds, planContent,
                total, completed
        );
    }
}
