package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that require human or agent review.
 */
public sealed interface Orchestrator permits CollectorNode, DiscoveryCollectorNode, DiscoveryOrchestratorNode, OrchestratorNode, PlanningCollectorNode, PlanningOrchestratorNode {
}
