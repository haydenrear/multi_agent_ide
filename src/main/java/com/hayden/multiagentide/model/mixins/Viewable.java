package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that support viewing results of type O.
 */
public sealed interface Viewable<O>
        permits AgentReviewNode, CollectorNode, DiscoveryCollectorNode, DiscoveryNode, DiscoveryOrchestratorNode, EditorNode, HumanReviewNode, MergeNode, OrchestratorNode, PlanningCollectorNode, PlanningNode, PlanningOrchestratorNode, SkillArtifactMergeNode, SummaryNode {
    /**
     * Get the viewable output/result.
     */
    O getView();
}
