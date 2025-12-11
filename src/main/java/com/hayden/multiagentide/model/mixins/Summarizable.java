package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that can be summarized.
 */
public sealed interface Summarizable permits OrchestratorNode, PlanningNode, WorkNode, 
                                              HumanReviewNode, AgentReviewNode, SummaryNode {
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
