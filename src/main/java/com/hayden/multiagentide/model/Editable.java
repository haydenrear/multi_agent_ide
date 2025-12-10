package com.hayden.multiagentide.model;

/**
 * Capability mixin: Nodes that can be edited (code/specs modified).
 */
public sealed interface Editable permits WorkNode {
    /**
     * Request to edit this node's code or spec.
     */
    record EditRequest(
            String content,
            String filePath
    ) {}
}
