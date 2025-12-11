package com.hayden.multiagentide.model.mixins;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Node for human review of work.
 * Can be Reviewable, Summarizable.
 */
public record HumanReviewNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        String reviewedNodeId,
        String reviewContent,
        boolean approved,
        String reviewerFeedback,
        Instant reviewCompletedAt,
        String specFileId
) implements GraphNode, Reviewable, Summarizable, Viewable<String> {

    public HumanReviewNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (reviewedNodeId == null || reviewedNodeId.isEmpty()) throw new IllegalArgumentException("reviewedNodeId required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.HUMAN_REVIEW;
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
    public HumanReviewNode withStatus(GraphNode.NodeStatus newStatus) {
        return new HumanReviewNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                reviewedNodeId, reviewContent, approved, reviewerFeedback,
                reviewCompletedAt, specFileId
        );
    }

    /**
     * Record review decision.
     */
    public HumanReviewNode withReviewDecision(boolean approvalStatus, String feedback) {
        return new HumanReviewNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                reviewedNodeId, reviewContent, approvalStatus, feedback,
                Instant.now(), specFileId
        );
    }

    public String contentToReview() {
        return this.reviewContent;
    }
}
