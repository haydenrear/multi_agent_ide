package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that support viewing results of type O.
 */
public sealed interface Viewable<O>
        permits AgentReviewNode, DiscoveryNode, DiscoveryOrchestratorNode, HumanReviewNode, MergeNode,
                OrchestratorNode, PlanningNode, PlanningOrchestratorNode, SummaryNode, EditorNode {
    /**
     * Get the viewable output/result.
     */
    O getView();
}
