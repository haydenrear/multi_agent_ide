package com.hayden.multiagentide.service;

import com.hayden.multiagentide.model.Spec;
import com.hayden.multiagentide.model.SpecSection;
import com.hayden.multiagentide.model.SpecSummary;
import com.hayden.multiagentide.model.SpecValidationResult;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing spec files and their lifecycle.
 * Specs are Markdown files that describe work in worktrees.
 */
public interface SpecService {

    /**
     * Create a new spec file.
     * @param worktreeId the worktree ID
     * @param specPath the path where spec will be stored
     * @param goal the goal/description
     * @return the created spec
     */
    Spec createSpec(String worktreeId, Path specPath, String goal);

    /**
     * Get a spec by ID.
     * @param specId the spec ID
     * @return the spec if found
     */
    Optional<Spec> getSpec(String specId);

    /**
     * Validate a spec against the standard structure.
     * @param specId the spec ID
     * @return validation result
     */
    SpecValidationResult validateSpec(String specId);

    /**
     * Get a summary of a spec without loading the entire content into memory.
     * Useful for LLM context management.
     * @param specId the spec ID
     * @return the spec summary with section paths
     */
    SpecSummary getSummary(String specId);

    /**
     * Get a specific section of a spec by header path.
     * Useful for targeted reading without loading entire spec.
     * @param specId the spec ID
     * @param headerPath the header path (e.g., "#/Plan", "#/Status")
     * @return the section content if found
     */
    Optional<SpecSection> getSection(String specId, String headerPath);

    /**
     * Update a section in a spec.
     * @param specId the spec ID
     * @param headerPath the header path
     * @param content the new content
     * @return the updated spec
     */
    Spec updateSection(String specId, String headerPath, String content);

    /**
     * Update the Plan section.
     * @param specId the spec ID
     * @param planContent the plan content
     * @return the updated spec
     */
    Spec updatePlan(String specId, String planContent);

    /**
     * Update the Status section.
     * @param specId the spec ID
     * @param statusContent the status content
     * @return the updated spec
     */
    Spec updateStatus(String specId, String statusContent);

    /**
     * Add or update a submodule-specific section.
     * @param specId the spec ID
     * @param submoduleName the submodule name
     * @param content the submodule section content
     * @return the updated spec
     */
    Spec updateSubmoduleSection(String specId, String submoduleName, String content);

    /**
     * Get a submodule-specific section.
     * @param specId the spec ID
     * @param submoduleName the submodule name
     * @return the content if found
     */
    Optional<String> getSubmoduleSection(String specId, String submoduleName);

    /**
     * Merge a child spec into a parent spec.
     * @param childSpecId the child spec ID
     * @param parentSpecId the parent spec ID
     * @return the merged parent spec
     */
    Spec mergeSpecs(String childSpecId, String parentSpecId);

    /**
     * Save a spec (persist to disk).
     * @param spec the spec to save
     */
    void saveSpec(Spec spec);

    /**
     * Load a spec from disk by file path.
     * @param filePath the file path
     * @return the loaded spec if found
     */
    Optional<Spec> loadSpecFromFile(Path filePath);

    /**
     * Delete a spec.
     * @param specId the spec ID
     */
    void deleteSpec(String specId);

    /**
     * Get all specs for a worktree.
     * @param worktreeId the worktree ID
     * @return list of specs
     */
    List<Spec> getSpecsForWorktree(String worktreeId);
}
