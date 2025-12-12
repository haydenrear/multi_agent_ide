package com.hayden.multiagentide.model.mixins;

import java.time.Instant;
import java.util.*;

/**
 * Node that summarizes completed work.
 * Can be Summarizable, Viewable.
 */
public record DiscoveryOrchestratorNode(
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
        int totalTasksFailed,
        String specFileId
) implements GraphNode, Summarizable, Viewable<String>, Orchestrator {

    public DiscoveryOrchestratorNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.SUMMARY;
    }

    @Override
    public Set<Class<?>> getCapabilities() {
        return Set.of(Summarizable.class, Viewable.class);
    }

    @Override
    public String getView() {
        return summaryContent;
    }

    /**
     * Create an updated version with new status.
     */
    public DiscoveryOrchestratorNode withStatus(NodeStatus newStatus) {
        return new DiscoveryOrchestratorNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                summaryContent,
                totalTasksCompleted, totalTasksFailed, specFileId
        );
    }

    /**
     * Update summary content.
     */
    public DiscoveryOrchestratorNode withContent(String content) {
        return new DiscoveryOrchestratorNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                 content,
                totalTasksCompleted, totalTasksFailed, specFileId
        );
    }


}
