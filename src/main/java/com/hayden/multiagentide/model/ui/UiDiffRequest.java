package com.hayden.multiagentide.model.ui;

public record UiDiffRequest(
        String sessionId,
        String baseRevision,
        Object diff,
        String summary
) {
}
