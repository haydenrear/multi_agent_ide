package com.hayden.multiagentide.model.mixins;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Sealed interface representing a node in the computation graph.
 * Nodes can implement capability mixins (Branchable, Editable, etc.) for optional behaviors.
 * This is a data-oriented interface that composes capabilities.
 */
public sealed interface GraphNode
        permits AgentReviewNode, CollectorNode, DiscoveryCollectorNode, DiscoveryNode, DiscoveryOrchestratorNode, EditorNode, HumanReviewNode, MergeNode, OrchestratorNode, PlanningCollectorNode, PlanningNode, PlanningOrchestratorNode, SkillArtifactMergeNode, SummaryNode {

    /**
     * Unique identifier for this node.
     */
    String nodeId();

    /**
     * Human-readable title/name.
     */
    String title();

    /**
     * Goal or description of work for this node.
     */
    String goal();

    /**
     * Current status of this node.
     */
    NodeStatus status();

    /**
     * ID of parent node, or null if root.
     */
    String parentNodeId();

    /**
     * Child node IDs.
     */
    List<String> childNodeIds();

    /**
     * Metadata and annotations.
     */
    Map<String, String> metadata();

    /**
     * Timestamp when node was created.
     */
    Instant createdAt();

    /**
     * Timestamp when node status last changed.
     */
    Instant lastUpdatedAt();

    /**
     * Type/kind of this node for pattern matching.
     */
    NodeType nodeType();

    /**
     * Get all capability mixins that this node implements.
     */
    Set<Class<?>> getCapabilities();

    /**
     * Check if this node implements a specific capability.
     */
    default boolean hasCapability(Class<?> capabilityClass) {
        return getCapabilities().contains(capabilityClass)
                || this.getClass().isAssignableFrom(capabilityClass)
                || capabilityClass.isAssignableFrom(this.getClass());
    }

    /**
     * Node status values.
     */
    enum NodeStatus {
        PENDING,           // Not yet ready
        READY,             // Ready to execute
        RUNNING,           // Currently executing
        WAITING_REVIEW,    // Awaiting human/agent review
        WAITING_INPUT,     // Awaiting user input
        COMPLETED,         // Successfully completed
        FAILED,            // Execution failed
        CANCELED,          // Manually canceled
        PRUNED,            // Removed from graph
    }

    /**
     * Node type for classification.
     */
    enum NodeType {
        ORCHESTRATOR,
        PLANNING,
        WORK,
        HUMAN_REVIEW,
        AGENT_REVIEW,
        SUMMARY
    }
}
