package com.hayden.multiagentide.repository;

import com.hayden.multiagentide.model.spec.Spec;
import java.util.List;
import java.util.Optional;

/**
 * Repository for storing and retrieving spec files.
 */
public interface SpecRepository {

    /**
     * Save a spec.
     * @param spec the spec to save
     */
    void save(Spec spec);

    /**
     * Get a spec by ID.
     * @param specId the spec ID
     * @return the spec if found
     */
    Optional<Spec> findById(String specId);

    /**
     * Get all specs.
     * @return list of all specs
     */
    List<Spec> findAll();

    /**
     * Get specs for a given worktree.
     * @param worktreeId the worktree ID
     * @return list of specs for that worktree
     */
    List<Spec> findByWorktreeId(String worktreeId);

    /**
     * Find spec by file path.
     * @param filePath the file path
     * @return the spec if found
     */
    Optional<Spec> findByFilePath(String filePath);

    /**
     * Delete a spec.
     * @param specId the spec ID
     */
    void delete(String specId);

    /**
     * Check if spec exists.
     * @param specId the spec ID
     * @return true if exists
     */
    boolean exists(String specId);

    /**
     * Count all specs.
     * @return count of specs
     */
    long count();

    /**
     * Clear all specs.
     */
    void clear();
}
