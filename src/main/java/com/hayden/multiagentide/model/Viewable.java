package com.hayden.multiagentide.model;

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
