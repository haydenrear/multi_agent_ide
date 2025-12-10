package com.hayden.multiagentide.model;

/**
 * Capability/mixin interfaces that GraphNode implementations can compose.
 * These define optional behaviors that nodes can support.
 * Using sealed interfaces to restrict implementations and ensure type safety.
 *
 * Individual capability interfaces are defined in separate files:
 * - Branchable: Nodes that can create parallel branches with modified goals
 * - Editable: Nodes that can be edited (code/specs modified)
 * - Reviewable: Nodes that require human or agent review
 * - Interruptable: Nodes that can be stopped mid-execution
 * - Prunable: Nodes that can be removed from the graph
 * - Summarizable: Nodes that can be summarized
 * - Annotatable: Nodes that support annotations/metadata
 * - Viewable: Nodes that support viewing results
 */
public final class Capabilities {
    private Capabilities() {}
}
