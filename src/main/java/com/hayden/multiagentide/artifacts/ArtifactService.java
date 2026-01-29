package com.hayden.multiagentide.artifacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.multiagentide.artifacts.entity.ArtifactEntity;
import com.hayden.multiagentide.artifacts.repository.ArtifactRepository;
import com.hayden.utilitymodule.acp.events.Artifact;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service for managing artifact persistence and retrieval.
 * Provides methods for:
 * - Hash-based artifact deduplication
 * - JSON serialization/deserialization of artifacts
 * - Artifact retrieval from the repository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ArtifactService {

    private final ArtifactRepository artifactRepository;
    private final ObjectMapper objectMapper;

    /**
     * Finds an artifact by its content hash.
     * Used for deduplication - if the same content has been stored before,
     * we can retrieve it instead of creating a new artifact.
     *
     * @param contentHash SHA-256 hash of the artifact content
     * @return Optional containing the artifact if found
     */
    @Transactional(readOnly = true)
    public Optional<Artifact> findByContentHash(String contentHash) {
        if (contentHash == null || contentHash.isEmpty()) {
            return Optional.empty();
        }

        return artifactRepository.findByContentHash(contentHash)
                .flatMap(this::deserializeArtifact);
    }

    /**
     * Deserializes an ArtifactEntity's JSON content back to an Artifact instance.
     *
     * @param entity The ArtifactEntity containing JSON content
     * @return Optional containing the deserialized Artifact, or empty if deserialization fails
     */
    public Optional<Artifact> deserializeArtifact(ArtifactEntity entity) {
        if (entity == null || entity.getContentJson() == null) {
            return Optional.empty();
        }

        try {
            // Deserialize using the artifact type as a hint
            Artifact artifact = objectMapper.readValue(entity.getContentJson(), Artifact.class);
            log.debug("Successfully deserialized artifact: {} of type: {}", 
                    entity.getArtifactKey(), entity.getArtifactType());
            return Optional.of(artifact);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize artifact: {} of type: {}", 
                    entity.getArtifactKey(), entity.getArtifactType(), e);
            return Optional.empty();
        }
    }

    /**
     * Serializes an Artifact to JSON for storage.
     *
     * @param artifact The artifact to serialize
     * @return JSON string representation
     * @throws JsonProcessingException if serialization fails
     */
    public String serializeArtifact(Artifact artifact) throws JsonProcessingException {
        return objectMapper.writeValueAsString(artifact);
    }

    /**
     * Checks if an artifact with the given content hash already exists.
     *
     * @param contentHash SHA-256 hash of the artifact content
     * @return true if an artifact with this hash exists
     */
    @Transactional(readOnly = true)
    public boolean existsByContentHash(String contentHash) {
        if (contentHash == null || contentHash.isEmpty()) {
            return false;
        }

        return artifactRepository.findByContentHash(contentHash).isPresent();
    }

    /**
     * Finds an artifact by its artifact key.
     *
     * @param artifactKey The hierarchical artifact key
     * @return Optional containing the artifact if found
     */
    @Transactional(readOnly = true)
    public Optional<Artifact> findByArtifactKey(String artifactKey) {
        return artifactRepository.findByArtifactKey(artifactKey)
                .flatMap(this::deserializeArtifact);
    }

    /**
     * Saves an artifact entity to the repository.
     * This is a pass-through method that can be used for consistency.
     *
     * @param entity The artifact entity to save
     * @return The saved entity
     */
    @Transactional
    public ArtifactEntity save(ArtifactEntity entity) {
        return artifactRepository.save(entity);
    }
}
