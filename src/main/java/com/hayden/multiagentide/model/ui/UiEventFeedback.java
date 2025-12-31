package com.hayden.multiagentide.model.ui;

public record UiEventFeedback(
        String eventId,
        String sessionId,
        String message,
        UiStateSnapshot snapshot
) {
}
