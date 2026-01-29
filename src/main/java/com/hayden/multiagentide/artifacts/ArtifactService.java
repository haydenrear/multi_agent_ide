package com.hayden.multiagentide.artifacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.multiagentide.artifacts.entity.ArtifactEntity;
import com.hayden.multiagentide.artifacts.repository.ArtifactRepository;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.utilitymodule.acp.events.Templated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.nntp.Article;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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

    @Transactional(readOnly = true)
    public Optional<Artifact> decorateDuplicate(String contentHash, @NotNull ArtifactKey artifact) {
        if (contentHash == null || contentHash.isEmpty()) {
            return Optional.empty();
        }

        return artifactRepository.findByContentHash(contentHash)
                .map(ae -> {
                    ae.addRef(artifact);
                    return artifactRepository.save(ae);
                })
                .flatMap(this::deserializeArtifact)
                .map( a -> switch(a) {
                    case Templated t ->
//                          use a random hash for this one as it's a ref
                            new Artifact.TemplateDbRef(artifact, t.templateStaticId(), UUID.randomUUID().toString(), t);
                    case Artifact t ->
//                          use a random hash for this one as it's a ref
                            new Artifact.ArtifactDbRef(artifact, UUID.randomUUID().toString(), t);
                });
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
