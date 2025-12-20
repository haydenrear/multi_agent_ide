package com.hayden.multiagentide.model.nodes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node for agent-based review of work.
 * Can be Reviewable.
 */
public record ReviewNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        
        // Review-specific fields
        String reviewedNodeId,
        String reviewContent,
        boolean approved,
        boolean humanFeedbackRequested,
        String agentFeedback,
        String reviewerAgentType,
        Instant reviewCompletedAt
) implements GraphNode, Viewable<String> {

    public ReviewNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (reviewedNodeId == null || reviewedNodeId.isEmpty()) throw new IllegalArgumentException("reviewedNodeId required");
        if (reviewerAgentType == null || reviewerAgentType.isEmpty()) throw new IllegalArgumentException("reviewerAgentType required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.AGENT_REVIEW;
    }

    @Override
    public String getView() {
        return reviewContent;
    }

    /**
     * Create an updated version with new status.
     */
    public ReviewNode withStatus(GraphNode.NodeStatus newStatus) {
        return new ReviewNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                reviewedNodeId, reviewContent, approved, humanFeedbackRequested, agentFeedback,
                reviewerAgentType, reviewCompletedAt
        );
    }

    /**
     * Record review decision.
     */
    public ReviewNode withReviewDecision(boolean approvalStatus, String feedback) {
        return new ReviewNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                reviewedNodeId, reviewContent, approvalStatus, humanFeedbackRequested, feedback,
                reviewerAgentType, Instant.now()
        );
    }

    public String contentToReview() {
        return this.reviewContent;
    }

    public String reviewerAgent() {
        return this.reviewerAgentType;
    }
}
