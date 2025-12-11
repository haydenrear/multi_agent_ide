package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that require human or agent review.
 */
public sealed interface Reviewable permits PlanningNode, WorkNode, HumanReviewNode, AgentReviewNode {
    /**
     * Request review decision.
     */
    record ReviewRequest(
            String content,
            String reviewType  // "human", "agent", or specific agent type
    ) {}

    /**
     * Review decision outcome.
     */
    record ReviewDecision(
            boolean approved,
            String feedback,
            long reviewTimeMs
    ) {}
}
