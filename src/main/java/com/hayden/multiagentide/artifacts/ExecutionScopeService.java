package com.hayden.multiagentide.artifacts;

import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.utilitymodule.acp.events.EventBus;
import com.hayden.utilitymodule.acp.events.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages execution scopes and root artifact creation.
 * 
 * Responsibilities:
 * - Creates execution root artifacts
 * - Tracks active execution scopes
 * - Provides artifact key generation within execution scopes
 * - Manages required child groups (ExecutionConfig, InputArtifacts, etc.)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExecutionScopeService {
    
    private final EventBus eventBus;
    private final ArtifactEventListener artifactListener;
    
    // Active execution scopes: workflowRunId -> ExecutionScope
    private final Map<String, ExecutionScope> activeScopes = new ConcurrentHashMap<>();
    
    /**
     * Represents an active execution scope with its artifact keys.
     */
    public record ExecutionScope(
            String workflowRunId,
            ArtifactKey executionKey,
            Instant startedAt,
            Map<String, ArtifactKey> groupKeys // group name -> group artifact key
    ) {
        public ExecutionScope(String workflowRunId, ArtifactKey executionKey) {
            this(workflowRunId, executionKey, Instant.now(), new ConcurrentHashMap<>());
        }
    }
    
    /**
     * Required child groups for a valid execution artifact.
     */
    public static final String GROUP_EXECUTION_CONFIG = "ExecutionConfig";
    public static final String GROUP_INPUT_ARTIFACTS = "InputArtifacts";
    public static final String GROUP_AGENT_EXECUTION = "AgentExecutionArtifacts";
    public static final String GROUP_OUTCOME_EVIDENCE = "OutcomeEvidenceArtifacts";
    
    private static final List<String> REQUIRED_GROUPS = List.of(
            GROUP_EXECUTION_CONFIG,
            GROUP_INPUT_ARTIFACTS,
            GROUP_AGENT_EXECUTION,
            GROUP_OUTCOME_EVIDENCE
    );
    
    /**
     * Starts a new execution scope and creates the root artifact.
     * 
     * @param workflowRunId Unique workflow run identifier
     * @return The execution scope
     */
    public ExecutionScope startExecution(String workflowRunId) {
        ArtifactKey executionKey = ArtifactKey.createRoot();
        ExecutionScope scope = new ExecutionScope(workflowRunId, executionKey);
        
        // Create root execution artifact
        Artifact.ExecutionArtifact rootArtifact = Artifact.ExecutionArtifact.builder()
                .artifactKey(executionKey)
                .workflowRunId(workflowRunId)
                .startedAt(scope.startedAt())
                .status(Artifact.ExecutionStatus.RUNNING)
                .metadata(Map.of())
                .children(new ArrayList<>())
                .build();
        
        // Emit artifact event
        emitArtifact(rootArtifact, null);
        
        // Create required child groups
        for (String groupName : REQUIRED_GROUPS) {
            createGroup(scope, groupName);
        }
        
        // Register with listener
        artifactListener.registerExecution(executionKey.value(), workflowRunId);
        
        activeScopes.put(workflowRunId, scope);
        log.info("Started execution scope: {} -> {}", workflowRunId, executionKey);
        
        return scope;
    }
    
    /**
     * Gets an active execution scope.
     */
    public Optional<ExecutionScope> getScope(String workflowRunId) {
        return Optional.ofNullable(activeScopes.get(workflowRunId));
    }
    
    /**
     * Gets the artifact key for a group within an execution.
     */
    public Optional<ArtifactKey> getGroupKey(String workflowRunId, String groupName) {
        return getScope(workflowRunId)
                .map(scope -> scope.groupKeys().get(groupName));
    }
    
    /**
     * Creates a new child artifact key under a group.
     */
    public ArtifactKey createChildKey(String workflowRunId, String groupName) {
        return getGroupKey(workflowRunId, groupName)
                .map(ArtifactKey::createChild)
                .orElseThrow(() -> new IllegalStateException(
                        "No active scope or group for: " + workflowRunId + "/" + groupName));
    }
    
    /**
     * Creates a new child artifact key under the execution root.
     */
    public ArtifactKey createChildKey(String workflowRunId) {
        return getScope(workflowRunId)
                .map(scope -> scope.executionKey().createChild())
                .orElseThrow(() -> new IllegalStateException(
                        "No active scope for: " + workflowRunId));
    }
    
    /**
     * Completes an execution scope with the given status.
     */
    public void completeExecution(String workflowRunId, Artifact.ExecutionStatus status) {
        ExecutionScope scope = activeScopes.remove(workflowRunId);
        if (scope == null) {
            log.warn("No active scope found for completion: {}", workflowRunId);
            return;
        }
        
        // Validate required groups exist
        validateRequiredGroups(scope);
        
        // Flush artifacts
        artifactListener.flushExecution(scope.executionKey().value());
        
        log.info("Completed execution scope: {} with status {}", workflowRunId, status);
    }
    
    /**
     * Emits an artifact event.
     */
    public void emitArtifact(Artifact artifact, ArtifactKey parentKey) {
        Events.ArtifactEvent event = new Events.ArtifactEvent(
                UUID.randomUUID().toString(),
                artifact.artifactKey().extractTimestamp(),
                extractNodeId(artifact),
                artifact.artifactType(),
                parentKey != null ? parentKey.value() : null,
                artifact
        );
        
        eventBus.publish(event);
    }
    
    /**
     * Emits an artifact under a specific group.
     */
    public void emitArtifactToGroup(String workflowRunId, String groupName, Artifact artifact) {
        ArtifactKey groupKey = getGroupKey(workflowRunId, groupName)
                .orElseThrow(() -> new IllegalStateException("Group not found: " + groupName));
        emitArtifact(artifact, groupKey);
    }
    
    // ========== Private Helpers ==========
    
    private void createGroup(ExecutionScope scope, String groupName) {
        ArtifactKey groupKey = scope.executionKey().createChild();
        
        Artifact.GroupArtifact groupArtifact = Artifact.GroupArtifact.builder()
                .artifactKey(groupKey)
                .groupName(groupName)
                .metadata(Map.of())
                .children(new ArrayList<>())
                .build();
        
        emitArtifact(groupArtifact, scope.executionKey());
        scope.groupKeys().put(groupName, groupKey);
        
        log.debug("Created group artifact: {} -> {}", groupName, groupKey);
    }
    
    private void validateRequiredGroups(ExecutionScope scope) {
        List<String> missing = new ArrayList<>();
        for (String required : REQUIRED_GROUPS) {
            if (!scope.groupKeys().containsKey(required)) {
                missing.add(required);
            }
        }
        
        if (!missing.isEmpty()) {
            log.error("Execution {} missing required groups: {}", 
                    scope.workflowRunId(), missing);
        }
    }
    
    private String extractNodeId(Artifact artifact) {
        return switch (artifact) {
            case Artifact.EventArtifact e -> e.nodeId();
            case Artifact.AgentRequestArtifact a -> a.nodeId();
            case Artifact.AgentResultArtifact a -> a.nodeId();
            default -> null;
        };
    }
}
