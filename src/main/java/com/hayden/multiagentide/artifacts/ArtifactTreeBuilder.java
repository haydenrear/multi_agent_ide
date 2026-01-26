package com.hayden.multiagentide.artifacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.multiagentide.artifacts.entity.ArtifactEntity;
import com.hayden.multiagentide.artifacts.repository.ArtifactRepository;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.utilitymodule.stream.StreamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Builds and persists artifact trees from artifact events.
 * 
 * Uses a trie-like structure where:
 * - Artifacts are inserted at positions determined by their hierarchical key
 * - Deduplication happens automatically via content hash comparison among siblings
 * - Messages come in order (no level skipping), so no splitting required
 * - Nodes are never removed
 * 
 * Responsibilities:
 * - Maintains in-memory trie structure during execution
 * - Handles hash-based deduplication at insert time
 * - Converts Artifact objects to ArtifactEntity for persistence
 * - Handles batched persistence for performance
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ArtifactTreeBuilder {
    
    private final ArtifactRepository artifactRepository;
    private final ObjectMapper objectMapper;
    
    // In-memory artifact trie per execution (cleared after persistence)
    private final Map<String, ArtifactNode> executionTrees = new ConcurrentHashMap<>();
    
    // Pending artifacts for batch persistence

    /**
     * Adds an artifact to the tree using trie-based insertion with hash deduplication.
     * 
     * The artifact is inserted at the position determined by its hierarchical key.
     * Before insertion, siblings are checked for matching content hashes to enable
     * automatic deduplication.
     * 
     * @param executionKey The root execution key
     * @param artifact The artifact to add
     * @return true if added successfully, false if duplicate (key or hash)
     */
    public boolean addArtifact(String executionKey, Artifact artifact) {
        ArtifactKey key = artifact.artifactKey();
        
        // Get or create the execution tree
        ArtifactNode root = executionTrees.get(executionKey);
        
        if (root == null) {
            // This is the first artifact - it should be the root
            if (key.isRoot() || key.depth() == 1) {
                root = ArtifactNode.createRoot(artifact);
                executionTrees.put(executionKey, root);
                log.debug("Created execution tree root: {}", key);
                return true;
            } else {
                log.warn("First artifact for execution {} is not a root: {}", executionKey, key);
                // Create a synthetic root and add this artifact under it
                // This handles the case where we might receive artifacts without the root
                return handleOrphanArtifact(executionKey, artifact);
            }
        }
        
        // Add to the trie structure
        ArtifactNode.AddResult result = root.addArtifact(artifact);
        
        switch (result) {
            case ADDED -> {
                log.debug("Added artifact: {} (type: {})", key, artifact.artifactType());
                return true;
            }
            case DUPLICATE_KEY -> {
                log.warn("Duplicate artifact key in execution {}: {}", executionKey, key);
                return false;
            }
            case DUPLICATE_HASH -> {
                log.debug("Skipped duplicate hash for artifact: {}", key);
                return false;
            }
            case PARENT_NOT_FOUND -> {
                log.warn("Parent not found for artifact: {}", key);
                return handleOrphanArtifact(executionKey, artifact);
            }
            default -> {
                log.error("Unexpected add result: {}", result);
                return false;
            }
        }
    }
    
    /**
     * Handles artifacts whose parent is not yet in the tree.
     * Since we have the invariant that messages come in order, this should be rare.
     */
    private boolean handleOrphanArtifact(String executionKey, Artifact artifact) {
        // For now, log and reject - in production we might want to buffer these
        log.error("Orphan artifact detected (parent not found): {} - this violates ordering invariant", 
                artifact.artifactKey());
        return false;
    }
    

    /**
     * Gets an artifact from the in-memory tree.
     */
    public Optional<Artifact> getArtifact(String executionKey, String artifactKeyStr) {
        ArtifactNode root = executionTrees.get(executionKey);
        if (root == null) {
            return Optional.empty();
        }
        
        try {
            ArtifactKey artifactKey = new ArtifactKey(artifactKeyStr);
            ArtifactNode node = root.findNode(artifactKey);
            return node != null ? Optional.of(node.getArtifact()) : Optional.empty();
        } catch (IllegalArgumentException e) {
            log.warn("Invalid artifact key format: {}", artifactKeyStr);
            return Optional.empty();
        }
    }
    
    /**
     * Gets all artifacts in an execution (in-memory).
     */
    public Collection<Artifact> getExecutionArtifacts(String executionKey) {
        ArtifactNode root = executionTrees.get(executionKey);
        if (root == null) {
            return Collections.emptyList();
        }
        return root.collectAll();
    }
    
    /**
     * Gets the root node of an execution tree.
     */
    public Optional<ArtifactNode> getExecutionTree(String executionKey) {
        return Optional.ofNullable(executionTrees.get(executionKey));
    }
    
    /**
     * Builds and returns the artifact tree for an execution.
     * Returns the root artifact with all children populated recursively.
     * 
     * @param executionKey The execution key
     * @return The root artifact with children, or empty if no tree exists
     */
    public Optional<Artifact> buildArtifactTree(String executionKey) {
        ArtifactNode root = executionTrees.get(executionKey);
        if (root == null) {
            return Optional.empty();
        }
        return Optional.of(root.buildArtifactTree());
    }
    
    /**
     * Checks if a sibling with the given hash exists under a parent key.
     */
    public boolean hasSiblingWithHash(String executionKey, ArtifactKey parentKey, String contentHash) {
        ArtifactNode root = executionTrees.get(executionKey);
        if (root == null) {
            return false;
        }
        
        ArtifactNode parentNode = root.findNode(parentKey);
        if (parentNode == null) {
            return false;
        }
        
        return parentNode.hasSiblingWithHash(contentHash);
    }
    
    /**
     * Marks an execution as finished and persists the artifact tree.
     * This is the primary way to complete an execution and persist its artifacts.
     * 
     * @param executionKey The execution key
     * @return The root artifact with all children populated, or empty if no tree exists
     */
    @Transactional
    public Optional<Artifact> finished(String executionKey) {
        ArtifactNode root = executionTrees.get(executionKey);
        if (root == null) {
            log.warn("No execution tree found for key: {}", executionKey);
            return Optional.empty();
        }
        
        doPersist(executionKey, root);
        return Optional.of(root.buildArtifactTree());
    }

    /**
     * Persists all pending artifacts for an execution without clearing state.
     */
    @Transactional
    public void persistExecution(String executionKey) {
        ArtifactNode root = executionTrees.get(executionKey);
        if (root == null) {
            log.warn("No execution tree found for key: {}", executionKey);
            return;
        }

        doPersist(executionKey, root);
    }

    private void doPersist(String executionKey, ArtifactNode root) {
        artifactRepository.saveAll(
            root.collectAll()
                    .stream()
                    .map(a -> toEntity(executionKey, a))
                    .toList());
    }

    
    /**
     * Clears the in-memory state for an execution.
     * Call this only when you're done with the execution and want to free memory.
     */
    public void clearExecution(String executionKey) {
        executionTrees.remove(executionKey);
        log.debug("Cleared execution state for: {}", executionKey);
    }
    

    /**
     * Loads an execution tree from the database.
     */
    @Transactional(readOnly = true)
    public Optional<Artifact> loadExecution(String executionKey) {
        List<ArtifactEntity> entities = artifactRepository.findByExecutionKeyOrderByArtifactKey(executionKey);
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        
        return rebuildTree(entities);
    }
    
    /**
     * Checks if a shared artifact (e.g., template) already exists.
     */
    public Optional<ArtifactEntity> findSharedArtifact(String templateStaticId, String contentHash) {
        return artifactRepository.findByTemplateStaticIdAndContentHashAndSharedTrue(
                templateStaticId, contentHash);
    }
    
    /**
     * Persists a shared artifact (e.g., template version).
     */
    @Transactional
    public ArtifactEntity persistSharedArtifact(ArtifactEntity entity) {
        entity.setShared(true);
        return artifactRepository.save(entity);
    }
    
    // ========== Private Helpers ==========
    
    private ArtifactEntity toEntity(String executionKey, Artifact artifact) {
        ArtifactKey key = artifact.artifactKey();
        
        String contentJson;
        try {
            contentJson = objectMapper.writeValueAsString(artifact);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize artifact: {}", key, e);
            contentJson = "{}";
        }
        
        String parentKey = key.parent().map(ArtifactKey::value).orElse(null);
        
        return ArtifactEntity.builder()
                .artifactKey(key.value())
                .parentKey(parentKey)
                .executionKey(executionKey)
                .artifactType(artifact.artifactType())
                .contentHash(artifact.contentHash().orElse(null))
                .contentJson(contentJson)
                .nodeId(extractNodeId(artifact))
                .createdAt(key.extractTimestamp())
                .depth(key.depth())
                .shared(false)
                .childIds(
                        StreamUtil.toStream(artifact.children()).flatMap(a -> Stream.ofNullable(a.artifactKey()))
                                .flatMap(ak -> StreamUtil.toStream(ak.value()))
                                .toList())
                .build();
    }
    
    private String extractNodeId(Artifact artifact) {
        return switch (artifact) {
            case Artifact.EventArtifact e -> e.nodeId();
            case Artifact.AgentRequestArtifact a -> a.nodeId();
            case Artifact.AgentResultArtifact a -> a.nodeId();
            default -> null;
        };
    }
    
    private Optional<Artifact> rebuildTree(List<ArtifactEntity> entities) {
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        
        // Parse all artifacts
        Map<String, Artifact> artifactMap = new LinkedHashMap<>();
        for (ArtifactEntity entity : entities) {
            try {
                Artifact artifact = objectMapper.readValue(entity.getContentJson(), Artifact.class);
                artifactMap.put(entity.getArtifactKey(), artifact);
            } catch (JsonProcessingException e) {
                log.error("Failed to deserialize artifact: {}", entity.getArtifactKey(), e);
            }
        }
        
        // Find root (depth 1)
        return artifactMap.values().stream()
                .filter(a -> a.artifactKey().isRoot())
                .findFirst();
    }
}
