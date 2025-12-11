package com.hayden.multiagentide.model.mixins;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Node for agent-based review of work.
 * Can be Reviewable, Summarizable.
 */
public record AgentReviewNode(
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
        String agentFeedback,
        String reviewerAgentType,
        Instant reviewCompletedAt,
        String specFileId
) implements GraphNode, Reviewable, Summarizable, Viewable<String> {

    public AgentReviewNode {
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
    public Set<Class<?>> getCapabilities() {
        return Set.of(Reviewable.class, Summarizable.class, Viewable.class);
    }

    @Override
    public String getView() {
        return reviewContent;
    }

    /**
     * Create an updated version with new status.
     */
    public AgentReviewNode withStatus(GraphNode.NodeStatus newStatus) {
        return new AgentReviewNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                reviewedNodeId, reviewContent, approved, agentFeedback,
                reviewerAgentType, reviewCompletedAt, specFileId
        );
    }

    /**
     * Record review decision.
     */
    public AgentReviewNode withReviewDecision(boolean approvalStatus, String feedback) {
        return new AgentReviewNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                reviewedNodeId, reviewContent, approvalStatus, feedback,
                reviewerAgentType, Instant.now(), specFileId
        );
    }

    public String contentToReview() {
        return this.reviewContent;
    }

    public String reviewerAgent() {
        return this.reviewerAgentType;
    }
}
