package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that can create parallel branches with modified goals.
 */
public sealed interface Branchable permits OrchestratorNode, EditorNode {
    /**
     * Create a new branched node with modified goal.
     */
    record BranchRequest(
            String newGoal,
            String parentNodeId
    ) {}
}
