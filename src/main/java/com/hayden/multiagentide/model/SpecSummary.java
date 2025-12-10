package com.hayden.multiagentide.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Summary of a spec without loading the entire content.
 * Returned by get_summary spec tool.
 */
public record SpecSummary(
        String specId,
        String title,
        String description,
        List<SpecSectionInfo> sections,
        List<String> submoduleNames,
        List<String> relatedSpecs,
        Instant summarizedAt
) {

    public SpecSummary {
        if (specId == null || specId.isEmpty()) throw new IllegalArgumentException("specId required");
        if (sections == null) sections = new ArrayList<>();
        if (submoduleNames == null) submoduleNames = new ArrayList<>();
        if (relatedSpecs == null) relatedSpecs = new ArrayList<>();
    }

    /**
     * Information about a section in the summary.
     */
    public record SpecSectionInfo(
            String headerPath,
            String headerLevel,
            int lineCount,
            String preview  // First 100 chars of section
    ) {}
}
