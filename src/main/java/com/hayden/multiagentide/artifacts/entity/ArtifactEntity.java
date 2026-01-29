package com.hayden.multiagentide.artifacts.entity;

import com.hayden.persistence.models.JpaHibernateAuditedIded;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity for persisted artifacts.
 * 
 * Stores the artifact tree in a relational format:
 * - artifactKey is the hierarchical identifier (indexed for prefix queries)
 * - parentKey enables tree traversal
 * - contentJson stores the full artifact payload
 * - contentHash enables deduplication and integrity checks
 */
@Entity
@Table(name = "artifact", indexes = {
    @Index(name = "idx_artifact_key", columnList = "artifactKey"),
    @Index(name = "idx_parent_key", columnList = "parentKey"),
    @Index(name = "idx_execution_key", columnList = "executionKey"),
    @Index(name = "idx_artifact_type", columnList = "artifactType"),
    @Index(name = "idx_content_hash", columnList = "contentHash"),
    @Index(name = "idx_created_at", columnList = "createdAt")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ArtifactEntity extends JpaHibernateAuditedIded {
    
    /**
     * Hierarchical artifact key (ak:ulid/ulid/...).
     */
    @Column(nullable = false, unique = true, length = 512)
    private String artifactKey;
    
    /**
     * Parent artifact key (null for root).
     */
    @Column(length = 512)
    private String parentKey;
    
    /**
     * Root execution key for this artifact tree.
     */
    @Column(nullable = false, length = 128)
    private String executionKey;
    
    /**
     * Artifact type discriminator.
     */
    @Column(nullable = false, length = 64)
    private String artifactType;
    
    /**
     * SHA-256 content hash (lowercase hex, 64 chars).
     */
    @Column(length = 64, unique=true)
    private String contentHash;
    
    /**
     * Full artifact payload as canonical JSON.
     */
    @Column(columnDefinition = "TEXT")
    private String contentJson;
    
    /**
     * Node ID this artifact is associated with.
     */
    @Column(length = 128)
    private String nodeId;
    
    /**
     * Creation timestamp (derived from artifact key).
     */
    @Column(nullable = false)
    private Instant createdAt;
    
    /**
     * Depth in the artifact tree (1 = root).
     */
    @Column(nullable = false)
    private Integer depth;
    
    /**
     * For template artifacts: the stable template static ID.
     */
    @Column(length = 256)
    private String templateStaticId;
    
    /**
     * Whether this is a shared artifact (e.g., template version).
     */
    @Column(nullable = false)
    @Builder.Default
    private Boolean shared = false;

    @Column(nullable = false)
    @Builder.Default
    private String schemaVersion = "0.0.1";

    @ElementCollection
    @CollectionTable(
            name = "artifact_child_ids",
            joinColumns = @JoinColumn(name = "artifact_id", nullable = false)
    )
    @Column(name = "child_id", nullable = false, length = 128)
    @OrderColumn(name = "child_order") // only if order matters
    @Builder.Default
    private List<String> childIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "artifact_refs",
            joinColumns = @JoinColumn(name = "artifact_id", nullable = false)
    )
    @Column(name = "ref", nullable = false, length = 512)
    @OrderColumn(name = "ref_order") // optional
    @Builder.Default
    private List<String> refs = new ArrayList<>();

}
