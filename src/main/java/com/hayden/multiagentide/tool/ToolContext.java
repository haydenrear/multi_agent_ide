package com.hayden.multiagentide.tool;

import java.util.List;
import java.util.Objects;

/**
 * Tool container for LLM runs.
 */
public record ToolContext(List<ToolAbstraction> tools) {

    public ToolContext {
        Objects.requireNonNull(tools, "tools");
        tools = List.copyOf(tools);
    }

    public static ToolContext empty() {
        return new ToolContext(List.of());
    }

    public static ToolContext of(ToolAbstraction... tools) {
        return new ToolContext(List.of(tools));
    }
}
