package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that can be summarized.
 */
public sealed interface Summarizable
        permits
            AgentReviewNode, DiscoveryNode, DiscoveryOrchestratorNode, HumanReviewNode, MergeNode,
            OrchestratorNode, PlanningNode, PlanningOrchestratorNode, SummaryNode, EditorNode {
    /**
     * Get summary of node work and status.
     */
    record Summary(
            String title,
            String description,
            int completedSubtasks,
            int totalSubtasks,
            String status
    ) {}
}
