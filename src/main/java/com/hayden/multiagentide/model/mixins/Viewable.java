package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that support viewing results of type O.
 */
public sealed interface Viewable<O> permits OrchestratorNode, PlanningNode, WorkNode,
                                            HumanReviewNode, AgentReviewNode, SummaryNode {
    /**
     * Get the viewable output/result.
     */
    O getView();
}
