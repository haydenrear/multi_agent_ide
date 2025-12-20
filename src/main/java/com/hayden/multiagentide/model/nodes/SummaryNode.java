package com.hayden.multiagentide.model.nodes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node that summarizes completed work.
 * Can be Summarizable, Viewable.
 */
public record SummaryNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        
        // Summary-specific fields
        List<String> summarizedNodeIds,
        String summaryContent,
        int totalTasksCompleted,
        int totalTasksFailed
) implements GraphNode, Viewable<String> {

    public SummaryNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
        if (summarizedNodeIds == null) summarizedNodeIds = new ArrayList<>();
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
    public SummaryNode withStatus(GraphNode.NodeStatus newStatus) {
        return new SummaryNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                summarizedNodeIds, summaryContent,
                totalTasksCompleted, totalTasksFailed
        );
    }

    /**
     * Update summary content.
     */
    public SummaryNode withContent(String content) {
        return new SummaryNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                summarizedNodeIds, content,
                totalTasksCompleted, totalTasksFailed
        );
    }

    /**
     * Add a summarized node.
     */
    public SummaryNode addSummarizedNode(String nodeId) {
        List<String> newSummarized = new ArrayList<>(summarizedNodeIds);
        newSummarized.add(nodeId);
        return new SummaryNode(
                this.nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                newSummarized, summaryContent,
                totalTasksCompleted, totalTasksFailed
        );
    }
}
