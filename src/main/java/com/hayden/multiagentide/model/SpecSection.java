package com.hayden.multiagentide.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A section within a spec file (e.g., Plan, Status, Design, Risks, etc.).
 * Sections are referenced by header path (#/Plan, #/Status, #/Submodules/<name>, etc.)
 */
public record SpecSection(
        String sectionId,
        String headerPath,  // e.g., "#/Plan", "#/Status", "#/Submodules/auth-lib"
        String headerLevel, // "##", "###", etc.
        String content,
        List<String> subsections,
        Instant createdAt,
        Instant lastUpdatedAt
) {

    public SpecSection {
        if (sectionId == null || sectionId.isEmpty()) throw new IllegalArgumentException("sectionId required");
        if (headerPath == null || headerPath.isEmpty()) throw new IllegalArgumentException("headerPath required");
        if (subsections == null) subsections = new ArrayList<>();
    }

    /**
     * Create updated version with new content.
     */
    public SpecSection withContent(String newContent) {
        return new SpecSection(
                sectionId, headerPath, headerLevel, newContent,
                subsections, createdAt, Instant.now()
        );
    }

    /**
     * Add a subsection ID.
     */
    public SpecSection addSubsection(String subsectionId) {
        List<String> newSubsections = new ArrayList<>(subsections);
        newSubsections.add(subsectionId);
        return new SpecSection(
                sectionId, headerPath, headerLevel, content,
                newSubsections, createdAt, Instant.now()
        );
    }
}
