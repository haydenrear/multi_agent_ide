package com.hayden.multiagentide.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Root node in the computation graph that orchestrates the overall goal.
 * Can be Branchable, Summarizable, and Viewable.
 */
public record OrchestratorNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        
        // Orchestrator-specific fields
        String repositoryUrl,
        String baseBranch,
        boolean hasSubmodules,
        List<String> submoduleNames,
        String mainWorktreeId,
        List<String> submoduleWorktreeIds,
        String specFileId,
        String orchestratorOutput
) implements GraphNode, Branchable, Summarizable, Viewable<String>, Annotatable {

    public OrchestratorNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (repositoryUrl == null || repositoryUrl.isEmpty()) throw new IllegalArgumentException("repositoryUrl required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
        if (submoduleNames == null) submoduleNames = new ArrayList<>();
        if (submoduleWorktreeIds == null) submoduleWorktreeIds = new ArrayList<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.ORCHESTRATOR;
    }

    @Override
    public Set<Class<?>> getCapabilities() {
        return Set.of(Branchable.class, Summarizable.class, Viewable.class);
    }

    @Override
    public String getView() {
        return orchestratorOutput;
    }

    /**
     * Create an updated version with new status.
     */
    public OrchestratorNode withStatus(GraphNode.NodeStatus newStatus) {
        return new OrchestratorNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames,
                mainWorktreeId, submoduleWorktreeIds, specFileId, orchestratorOutput
        );
    }

    /**
     * Add a child node ID.
     */
    public OrchestratorNode addChildNode(String childNodeId) {
        List<String> newChildren = new ArrayList<>(childNodeIds);
        newChildren.add(childNodeId);
        return new OrchestratorNode(
                nodeId, title, goal, status, parentNodeId,
                newChildren, metadata, createdAt, Instant.now(),
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames,
                mainWorktreeId, submoduleWorktreeIds, specFileId, orchestratorOutput
        );
    }

    /**
     * Update orchestrator output.
     */
    public OrchestratorNode withOutput(String output) {
        return new OrchestratorNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames,
                mainWorktreeId, submoduleWorktreeIds, specFileId, output
        );
    }
}
