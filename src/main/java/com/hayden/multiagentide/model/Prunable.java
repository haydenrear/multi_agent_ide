package com.hayden.multiagentide.model;

/**
 * Capability mixin: Nodes that can be removed from the graph.
 */
public sealed interface Prunable permits WorkNode {
    /**
     * Request to prune this node and descendants.
     */
    record PruneRequest(
            String reason
    ) {}
}
