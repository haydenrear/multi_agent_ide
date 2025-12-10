package com.hayden.multiagentide.model;

/**
 * Capability mixin: Nodes that can be stopped mid-execution.
 */
public sealed interface Interruptable permits WorkNode {
    /**
     * Signal to interrupt execution.
     */
    record InterruptRequest(
            String reason
    ) {}
}
