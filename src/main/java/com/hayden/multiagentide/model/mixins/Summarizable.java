package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that can be summarized.
 */
public sealed interface Summarizable
        permits ReviewNode, CollectorNode, DiscoveryCollectorNode, DiscoveryNode, DiscoveryOrchestratorNode, EditorNode, MergeNode, OrchestratorNode, PlanningCollectorNode, PlanningNode, PlanningOrchestratorNode, SkillArtifactMergeNode, SummaryNode {
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
