package com.hayden.multiagentide.model;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface Events {

    /**
     * Base interface for all graph and worktree events.
     * Sealed to restrict implementations.
     */
    sealed interface GraphEvent {
        /**
         * Unique event ID.
         */
        String eventId();

        /**
         * Timestamp when event was created.
         */
        Instant timestamp();

        /**
         * Type of event for classification.
         */
        String eventType();
    }

    /**
     * Emitted when a new node is added to the graph.
     */
    record NodeAddedEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            String nodeTitle,
            GraphNode.NodeType nodeType,
            String parentNodeId
    ) implements GraphEvent {
        @Override
        public String eventType() { return "NODE_ADDED"; }
    }

    /**
     * Emitted when a node's status changes.
     */
    record NodeStatusChangedEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            GraphNode.NodeStatus oldStatus,
            GraphNode.NodeStatus newStatus,
            String reason
    ) implements GraphEvent {
        @Override
        public String eventType() { return "NODE_STATUS_CHANGED"; }
    }
    /**
     * Emitted when a node is branched with modified goal.
     */
    public record NodeBranchedEvent(
            String eventId,
            Instant timestamp,
            String originalNodeId,
            String branchedNodeId,
            String newGoal,
            String mainWorktreeId,
            List<String> submoduleWorktreeIds
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "NODE_BRANCHED"; }
    }

    /**
     * Emitted when a node is pruned.
     */
    public record NodePrunedEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            String reason,
            List<String> pruneWorktreeIds
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "NODE_PRUNED"; }
    }

    /**
     * Emitted when a review is requested.
     */
    public record NodeReviewRequestedEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            String reviewNodeId,
            String reviewType,  // "human", "agent", or specific agent type
            String contentToReview
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "NODE_REVIEW_REQUESTED"; }
    }

    /**
     * Emitted when overall goal is completed.
     */
    public record GoalCompletedEvent(
            String eventId,
            Instant timestamp,
            String orchestratorNodeId,
            String finalSummary,
            int totalNodesCompleted,
            int totalNodesFailed,
            long executionTimeMs
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "GOAL_COMPLETED"; }
    }

// ============ WORKTREE EVENTS ============

    /**
     * Emitted when a worktree is created (main or submodule).
     */
    public record WorktreeCreatedEvent(
            String eventId,
            Instant timestamp,
            String worktreeId,
            String associatedNodeId,
            String worktreePath,
            String worktreeType,  // "main" or "submodule"
            String submoduleName  // Only if submodule
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "WORKTREE_CREATED"; }
    }

    /**
     * Emitted when a worktree is branched.
     */
    public record WorktreeBranchedEvent(
            String eventId,
            Instant timestamp,
            String originalWorktreeId,
            String branchedWorktreeId,
            String branchName,
            String worktreeType  // "main" or "submodule"
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "WORKTREE_BRANCHED"; }
    }

    /**
     * Emitted when a child worktree is merged into parent.
     */
    public record WorktreeMergedEvent(
            String eventId,
            Instant timestamp,
            String childWorktreeId,
            String parentWorktreeId,
            String mergeCommitHash,
            boolean conflictDetected,
            List<String> conflictFiles,
            String worktreeType  // "main" or "submodule"
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "WORKTREE_MERGED"; }
    }

    /**
     * Emitted when a worktree is discarded/removed.
     */
    public record WorktreeDiscardedEvent(
            String eventId,
            Instant timestamp,
            String worktreeId,
            String reason,
            String worktreeType  // "main" or "submodule"
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "WORKTREE_DISCARDED"; }
    }

// ============ SPEC EVENTS ============

    /**
     * Emitted when a spec is validated.
     */
    public record SpecValidatedEvent(
            String eventId,
            Instant timestamp,
            String specId,
            boolean isValid,
            List<String> validationErrors
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "SPEC_VALIDATED"; }
    }

    /**
     * Emitted when a spec is updated.
     */
    public record SpecUpdatedEvent(
            String eventId,
            Instant timestamp,
            String specId,
            String worktreeId,
            String updatedSections,
            String updatedBy  // Agent type or "user"
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "SPEC_UPDATED"; }
    }

    /**
     * Emitted when a spec is merged.
     */
    public record SpecMergedEvent(
            String eventId,
            Instant timestamp,
            String childSpecId,
            String parentSpecId,
            String childWorktreeId,
            String parentWorktreeId,
            Map<String, String> mergedSections
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "SPEC_MERGED"; }
    }

// ============ GENERIC GRAPH EVENTS ============

    /**
     * Generic event for updates to nodes.
     */
    public record NodeUpdatedEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            Map<String, String> updates
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "NODE_UPDATED"; }
    }

    /**
     * Event for deletion of nodes (less common than pruning).
     */
    public record NodeDeletedEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            String reason
    ) implements Events.GraphEvent {
        @Override
        public String eventType() { return "NODE_DELETED"; }
    }

    /**
     * Emitted during streaming output from an agent (e.g., code generation).
     */
    record NodeStreamDeltaEvent(
            String eventId,
            Instant timestamp,
            String nodeId,
            String deltaContent,
            int tokenCount,
            boolean isFinal
    ) implements GraphEvent {
        @Override
        public String eventType() { return "NODE_STREAM_DELTA"; }
    }
}

// ============ NODE EVENTS ============

