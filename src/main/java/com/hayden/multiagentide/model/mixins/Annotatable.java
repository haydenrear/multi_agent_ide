package com.hayden.multiagentide.model.mixins;

/**
 * Capability mixin: Nodes that support annotations/metadata.
 */
public sealed interface Annotatable permits OrchestratorNode, PlanningNode, PlanningOrchestratorNode, EditorNode {
    /**
     * Add or update annotation on this node.
     */
    record Annotation(
            String key,
            String value,
            long timestamp
    ) {}
}
