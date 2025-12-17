package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that support annotations/metadata.
 */
public sealed interface Annotatable permits CollectorNode, EditorNode, OrchestratorNode, PlanningCollectorNode, PlanningNode, PlanningOrchestratorNode {
    /**
     * Add or update annotation on this node.
     */
    record Annotation(
            String key,
            String value,
            long timestamp
    ) {}
}
