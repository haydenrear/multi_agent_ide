package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.mixins.Events;
import com.hayden.multiagentide.model.mixins.GraphAgent;
import com.hayden.multiagentide.model.mixins.GraphNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;

/**
 * Abstract base class for graph agents.
 * Provides common functionality like event emission and context management.
 */
public abstract class BaseGraphAgent implements GraphAgent {

    protected final EventBus eventBus;
    protected final String agentId;

    @Autowired
    public BaseGraphAgent(EventBus eventBus) {
        this.eventBus = eventBus;
        this.agentId = UUID.randomUUID().toString();
    }

    @Override
    public String agentId() {
        return agentId;
    }

    /**
     * Helper to emit a node status change event.
     */
    protected void emitStatusChangeEvent(String nodeId, GraphNode.NodeStatus oldStatus, 
                                        GraphNode.NodeStatus newStatus, String reason,
                                        ExecutionContext context) {
        Events.NodeStatusChangedEvent event = new Events.NodeStatusChangedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                oldStatus,
                newStatus,
                reason
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }

    /**
     * Helper to emit a node added event.
     */
    protected void emitNodeAddedEvent(String nodeId, String title, GraphNode.NodeType nodeType,
                                     String parentNodeId, ExecutionContext context) {
        Events.NodeAddedEvent event = new Events.NodeAddedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                title,
                nodeType,
                parentNodeId
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }

    /**
     * Helper to emit a worktree created event.
     */
    protected void emitWorktreeCreatedEvent(String worktreeId, String nodeId, String worktreePath,
                                           String worktreeType, String submoduleName, ExecutionContext context) {
        Events.WorktreeCreatedEvent event = new Events.WorktreeCreatedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                worktreeId,
                nodeId,
                worktreePath,
                worktreeType,
                submoduleName
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }

    /**
     * Helper to emit streaming delta event.
     */
    protected void emitStreamDeltaEvent(String nodeId, String content, int tokenCount, 
                                       boolean isFinal, ExecutionContext context) {
        Events.NodeStreamDeltaEvent event = new Events.NodeStreamDeltaEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                content,
                tokenCount,
                isFinal
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }

    /**
     * Helper to emit worktree merged event.
     */
    protected void emitWorktreeMergedEvent(String childWorktreeId, String parentWorktreeId,
                                          String mergeCommitHash, boolean conflictDetected,
                                          java.util.List<String> conflictFiles, String worktreeType,
                                          ExecutionContext context) {
        Events.WorktreeMergedEvent event = new Events.WorktreeMergedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                childWorktreeId,
                parentWorktreeId,
                mergeCommitHash,
                conflictDetected,
                conflictFiles,
                worktreeType
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }

    /**
     * Helper to emit spec validated event.
     */
    protected void emitSpecValidatedEvent(String specId, boolean isValid, 
                                         java.util.List<String> errors, ExecutionContext context) {
        Events.SpecValidatedEvent event = new Events.SpecValidatedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                specId,
                isValid,
                errors
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }

    /**
     * Helper to emit spec updated event.
     */
    protected void emitSpecUpdatedEvent(String specId, String worktreeId, String updatedSections,
                                       String updatedBy, ExecutionContext context) {
        Events.SpecUpdatedEvent event = new Events.SpecUpdatedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                specId,
                worktreeId,
                updatedSections,
                updatedBy
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }
}
