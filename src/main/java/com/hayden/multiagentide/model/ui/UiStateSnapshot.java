package com.hayden.multiagentide.model.ui;

import java.time.Instant;

public record UiStateSnapshot(
        String sessionId,
        String revision,
        Instant timestamp,
        Object renderTree
) {
}
