package com.hayden.multiagentide.model.spec;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Result of spec validation.
 */
public record SpecValidationResult(
        boolean isValid,
        String specId,
        List<String> missingRequiredSections,
        List<String> validationErrors,
        int validationVersion,
        Instant validatedAt
) {

    public SpecValidationResult {
        if (specId == null || specId.isEmpty()) throw new IllegalArgumentException("specId required");
        if (missingRequiredSections == null) missingRequiredSections = new ArrayList<>();
        if (validationErrors == null) validationErrors = new ArrayList<>();
    }
}
