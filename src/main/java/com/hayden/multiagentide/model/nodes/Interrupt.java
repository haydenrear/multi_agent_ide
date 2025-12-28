package com.hayden.multiagentide.model.nodes;

import com.hayden.multiagentide.agent.AgentModels;

/**
 * Marker mixin for nodes that represent an interrupt.
 */
public interface Interrupt {

    InterruptContext interruptContext();

    default AgentModels.InterruptType interruptType() {
        InterruptContext context = interruptContext();
        return context != null ? context.type() : null;
    }

    default String interruptReason() {
        InterruptContext context = interruptContext();
        return context != null ? context.reason() : null;
    }

    default String interruptOriginNodeId() {
        InterruptContext context = interruptContext();
        return context != null ? context.originNodeId() : null;
    }

    default String interruptResumeNodeId() {
        InterruptContext context = interruptContext();
        return context != null ? context.resumeNodeId() : null;
    }

    default String interruptNodeId() {
        InterruptContext context = interruptContext();
        return context != null ? context.interruptNodeId() : null;
    }
}
