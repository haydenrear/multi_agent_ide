package com.hayden.multiagentide.model.nodes;

import java.time.Instant;
import java.util.*;

/**
 * Node that summarizes completed work.
 * Can be Summarizable, Viewable.
 */
public record SkillArtifactMergeNode(
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
) implements GraphNode, Viewable<String> {

    public SkillArtifactMergeNode {
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
    public SkillArtifactMergeNode withStatus(NodeStatus newStatus) {
        return new SkillArtifactMergeNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                summaryContent,
                totalTasksCompleted, totalTasksFailed, specFileId
        );
    }

    /**
     * Update summary content.
     */
    public SkillArtifactMergeNode withContent(String content) {
        return new SkillArtifactMergeNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                 content,
                totalTasksCompleted, totalTasksFailed, specFileId
        );
    }


}
