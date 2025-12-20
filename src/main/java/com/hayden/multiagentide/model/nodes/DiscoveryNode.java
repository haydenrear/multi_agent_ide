package com.hayden.multiagentide.model.nodes;

import java.time.Instant;
import java.util.*;

/**
 * Node that summarizes completed work.
 * Can be Summarizable, Viewable.
 */
public record DiscoveryNode(
        String nodeId,
        String title,
        String goal,
        NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        String summaryContent,
        int totalTasksCompleted,
        int totalTasksFailed
) implements GraphNode, Viewable<String> {

    public DiscoveryNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.SUMMARY;
    }

    @Override
    public String getView() {
        return summaryContent;
    }

    /**
     * Create an updated version with new status.
     */
    public DiscoveryNode withStatus(NodeStatus newStatus) {
        return new DiscoveryNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                summaryContent,
                totalTasksCompleted, totalTasksFailed
        );
    }

    /**
     * Update summary content.
     */
    public DiscoveryNode withContent(String content) {
        return new DiscoveryNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                 content,
                totalTasksCompleted, totalTasksFailed
        );
    }


}
