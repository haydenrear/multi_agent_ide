package com.hayden.multiagentide.model.nodes;

/**
 * Capability mixin: Nodes that support viewing results of type O.
 */
public sealed interface Viewable<O>
        permits ReviewNode, CollectorNode, DiscoveryCollectorNode, DiscoveryNode, DiscoveryOrchestratorNode, EditorNode,
        MergeNode, OrchestratorNode, PlanningCollectorNode, PlanningNode, PlanningOrchestratorNode, SummaryNode {
    /**
     * Get the viewable output/result.
     */
    O getView();
}
