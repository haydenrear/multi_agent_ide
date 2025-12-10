package com.hayden.multiagentide.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Node representing a unit of work tied to main and submodule worktrees.
 * Can be Branchable, Editable, Reviewable, Interruptable, Prunable, Summarizable.
 */
public record WorkNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,

        // Work-specific fields
        String mainWorktreeId,
        List<String> submoduleWorktreeIds,
        String specFileId,
        int completedSubtasks,
        int totalSubtasks,
        String agentType,  // Type of agent handling this work
        String workOutput,
        boolean mergeRequired,
        int streamingTokenCount
) implements GraphNode, Branchable, Editable, Reviewable,
                         Interruptable, Prunable, Summarizable, Viewable<String>, Annotatable {

    public WorkNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (goal == null || goal.isEmpty()) throw new IllegalArgumentException("goal required");
        if (mainWorktreeId == null || mainWorktreeId.isEmpty()) throw new IllegalArgumentException("mainWorktreeId required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
        if (submoduleWorktreeIds == null) submoduleWorktreeIds = new ArrayList<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.WORK;
    }

    @Override
    public Set<Class<?>> getCapabilities() {
        return Set.of(Branchable.class, Editable.class, Reviewable.class,
                     Interruptable.class, Prunable.class, Summarizable.class, Viewable.class);
    }

    @Override
    public String getView() {
        return workOutput;
    }

    /**
     * Create an updated version with new status.
     */
    public WorkNode withStatus(GraphNode.NodeStatus newStatus) {
        return new WorkNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                mainWorktreeId, submoduleWorktreeIds, specFileId,
                completedSubtasks, totalSubtasks, agentType,
                workOutput, mergeRequired, streamingTokenCount
        );
    }

    /**
     * Add a submodule worktree.
     */
    public WorkNode addSubmoduleWorktree(String submoduleWorktreeId) {
        List<String> newSubmodules = new ArrayList<>(submoduleWorktreeIds);
        newSubmodules.add(submoduleWorktreeId);
        return new WorkNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                mainWorktreeId, newSubmodules, specFileId,
                completedSubtasks, totalSubtasks, agentType,
                workOutput, mergeRequired, streamingTokenCount
        );
    }

    /**
     * Update work output and streaming progress.
     */
    public WorkNode withOutput(String output, int tokens) {
        return new WorkNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                mainWorktreeId, submoduleWorktreeIds, specFileId,
                completedSubtasks, totalSubtasks, agentType,
                output, mergeRequired, tokens
        );
    }

    /**
     * Update progress.
     */
    public WorkNode withProgress(int completed, int total) {
        return new WorkNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                mainWorktreeId, submoduleWorktreeIds, specFileId,
                completed, total, agentType,
                workOutput, mergeRequired, streamingTokenCount
        );
    }

    /**
     * Mark that merge is required.
     */
    public WorkNode requireMerge() {
        return new WorkNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                mainWorktreeId, submoduleWorktreeIds, specFileId,
                completedSubtasks, totalSubtasks, agentType,
                workOutput, true, streamingTokenCount
        );
    }

    /**
     * Add a child node ID.
     */
    public WorkNode addChildNode(String childNodeId) {
        List<String> newChildren = new ArrayList<>(childNodeIds);
        newChildren.add(childNodeId);
        return new WorkNode(
                nodeId, title, goal, status, parentNodeId,
                newChildren, metadata, createdAt, Instant.now(),
                mainWorktreeId, submoduleWorktreeIds, specFileId,
                completedSubtasks, totalSubtasks, agentType,
                workOutput, mergeRequired, streamingTokenCount
        );
    }
}
