package com.hayden.multiagentide.model.nodes;

import com.hayden.multiagentide.agent.AgentModels;
import lombok.Builder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node representing a unit of work tied to main and submodule worktrees.
 * Can be Branchable, Editable, Reviewable, Prunable.
 */
@Builder(toBuilder = true)
public record EditorNode(
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
        int completedSubtasks,
        int totalSubtasks,
        String agentType,  // Type of agent handling this work
        String workOutput,
        boolean mergeRequired,
        int streamingTokenCount,
        AgentModels.TicketOrchestratorResult ticketOrchestratorResult,
        AgentModels.TicketAgentResult ticketAgentResult
) implements GraphNode, Viewable<String> {

    public EditorNode(String nodeId, String title, String goal, GraphNode.NodeStatus status, String parentNodeId, List<String> childNodeIds, Map<String, String> metadata, Instant createdAt, Instant lastUpdatedAt, String mainWorktreeId, List<String> submoduleWorktreeIds, int completedSubtasks, int totalSubtasks, String agentType, String workOutput, boolean mergeRequired, int streamingTokenCount) {
        this(nodeId, title, goal, status, parentNodeId, childNodeIds, metadata, createdAt, lastUpdatedAt,
                mainWorktreeId, submoduleWorktreeIds, completedSubtasks, totalSubtasks, agentType,
                workOutput, mergeRequired, streamingTokenCount, null, null);
    }

    public EditorNode {
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
    public String getView() {
        return workOutput;
    }

    /**
     * Create an updated version with new status.
     */
    public EditorNode withStatus(GraphNode.NodeStatus newStatus) {
        return toBuilder()
                .status(newStatus)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    /**
     * Add a submodule worktree.
     */
    public EditorNode addSubmoduleWorktree(String submoduleWorktreeId) {
        List<String> newSubmodules = new ArrayList<>(submoduleWorktreeIds);
        newSubmodules.add(submoduleWorktreeId);
        return toBuilder()
                .submoduleWorktreeIds(newSubmodules)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    /**
     * Update work output and streaming progress.
     */
    public EditorNode withOutput(String output, int tokens) {
        return toBuilder()
                .workOutput(output)
                .streamingTokenCount(tokens)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    /**
     * Update progress.
     */
    public EditorNode withProgress(int completed, int total) {
        return toBuilder()
                .completedSubtasks(completed)
                .totalSubtasks(total)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    /**
     * Mark that merge is required.
     */
    public EditorNode requireMerge() {
        return toBuilder()
                .mergeRequired(true)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    /**
     * Add a child node ID.
     */
    public EditorNode addChildNode(String childNodeId) {
        List<String> newChildren = new ArrayList<>(childNodeIds);
        newChildren.add(childNodeId);
        return toBuilder()
                .childNodeIds(newChildren)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    public EditorNode withTicketOrchestratorResult(AgentModels.TicketOrchestratorResult result) {
        return toBuilder()
                .ticketOrchestratorResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    public EditorNode withTicketAgentResult(AgentModels.TicketAgentResult result) {
        return toBuilder()
                .ticketAgentResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
    }
}
