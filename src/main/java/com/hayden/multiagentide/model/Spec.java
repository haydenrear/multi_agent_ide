package com.hayden.multiagentide.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spec file that describes work in a worktree.
 * Specs are stored as Markdown files and manipulated via spec tools
 * (validate, get_summary, get_section) to avoid loading entire specs into LLM context.
 */
public record Spec(
        String specId,
        String worktreeId,
        String filePath,
        String header,
        String plan,
        String status,
        List<SpecSection> sections,
        Map<String, String> submoduleSections,  // name -> section content
        List<String> links,  // Links to sub-specs or subtrees
        Instant createdAt,
        Instant lastUpdatedAt,
        int lastValidatedAt,  // Version number of last validation
        Map<String, String> metadata
) {

    public Spec {
        if (specId == null || specId.isEmpty()) throw new IllegalArgumentException("specId required");
        if (worktreeId == null || worktreeId.isEmpty()) throw new IllegalArgumentException("worktreeId required");
        if (filePath == null || filePath.isEmpty()) throw new IllegalArgumentException("filePath required");
        if (sections == null) sections = new ArrayList<>();
        if (submoduleSections == null) submoduleSections = new HashMap<>();
        if (links == null) links = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
    }

    /**
     * Create updated version with new plan.
     */
    public Spec withPlan(String newPlan) {
        return new Spec(
                specId, worktreeId, filePath, header, newPlan, status,
                sections, submoduleSections, links, createdAt, Instant.now(),
                lastValidatedAt, metadata
        );
    }

    /**
     * Create updated version with new status.
     */
    public Spec withStatus(String newStatus) {
        return new Spec(
                specId, worktreeId, filePath, header, plan, newStatus,
                sections, submoduleSections, links, createdAt, Instant.now(),
                lastValidatedAt, metadata
        );
    }

    /**
     * Add a section to the spec.
     */
    public Spec addSection(SpecSection section) {
        List<SpecSection> newSections = new ArrayList<>(sections);
        newSections.add(section);
        return new Spec(
                specId, worktreeId, filePath, header, plan, status,
                newSections, submoduleSections, links, createdAt, Instant.now(),
                lastValidatedAt, metadata
        );
    }

    /**
     * Add a submodule-specific section.
     */
    public Spec addSubmoduleSection(String submoduleName, String sectionContent) {
        Map<String, String> newSubmodules = new HashMap<>(submoduleSections);
        newSubmodules.put(submoduleName, sectionContent);
        return new Spec(
                specId, worktreeId, filePath, header, plan, status,
                sections, newSubmodules, links, createdAt, Instant.now(),
                lastValidatedAt, metadata
        );
    }

    /**
     * Mark spec as validated.
     */
    public Spec markValidated(int validationVersion) {
        return new Spec(
                specId, worktreeId, filePath, header, plan, status,
                sections, submoduleSections, links, createdAt, Instant.now(),
                validationVersion, metadata
        );
    }
}
