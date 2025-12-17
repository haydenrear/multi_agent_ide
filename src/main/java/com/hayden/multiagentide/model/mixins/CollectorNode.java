package com.hayden.multiagentide.model.mixins;

import com.hayden.multiagentide.model.SubmoduleNode;

import java.time.Instant;
import java.util.*;

/**
 * Root node in the computation graph that orchestrates the overall goal.
 * Can be Branchable, Summarizable, and Viewable.
 */
public record CollectorNode(
        String nodeId,
        String title,
        String goal,
        NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        String repositoryUrl,
        String baseBranch,
        boolean hasSubmodules,
        List<String> submoduleNames,
        String mainWorktreeId,
        List<String> submoduleWorktreeIds,
        String specFileId,
        String orchestratorOutput,
        List<SubmoduleNode> submodules
) implements GraphNode, Branchable, Summarizable, Viewable<String>, Annotatable, Orchestrator {

    public CollectorNode(String nodeId, String title, String goal, NodeStatus status, String parentNodeId, List<String> childNodeIds, Map<String, String> metadata, Instant createdAt, Instant lastUpdatedAt, String repositoryUrl, String baseBranch, boolean hasSubmodules, List<String> submoduleNames, String mainWorktreeId, List<String> submoduleWorktreeIds, String specFileId, String orchestratorOutput) {
        this(nodeId, title, goal, status, parentNodeId, childNodeIds, metadata, createdAt, lastUpdatedAt,
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames, mainWorktreeId, submoduleWorktreeIds, specFileId, orchestratorOutput,
                new ArrayList<>());
    }

    public CollectorNode {
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
    public CollectorNode withStatus(NodeStatus newStatus) {
        return new CollectorNode(
                nodeId, title, goal, newStatus, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames,
                mainWorktreeId, submoduleWorktreeIds, specFileId, orchestratorOutput
        );
    }

    /**
     * Add a child node ID.
     */
    public CollectorNode addChildNode(String childNodeId) {
        List<String> newChildren = new ArrayList<>(childNodeIds);
        newChildren.add(childNodeId);
        return new CollectorNode(
                nodeId, title, goal, status, parentNodeId,
                newChildren, metadata, createdAt, Instant.now(),
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames,
                mainWorktreeId, submoduleWorktreeIds, specFileId, orchestratorOutput
        );
    }

    /**
     * Update orchestrator output.
     */
    public CollectorNode withOutput(String output) {
        return new CollectorNode(
                nodeId, title, goal, status, parentNodeId,
                childNodeIds, metadata, createdAt, Instant.now(),
                repositoryUrl, baseBranch, hasSubmodules, submoduleNames,
                mainWorktreeId, submoduleWorktreeIds, specFileId, output
        );
    }
}
