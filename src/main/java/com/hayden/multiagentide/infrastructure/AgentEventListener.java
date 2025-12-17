package com.hayden.multiagentide.infrastructure;

import com.hayden.multiagentide.model.mixins.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Event-driven listener that orchestrates multi-agent workflows.
 * Listens for node completion events and triggers the next phase of the workflow.
 *
 * Workflow Orchestration Flow:
 * OrchestratorNode (READY) → DiscoveryOrchestratorNode → DiscoveryNode(s) → DiscoveryMergerNode
 *   → PlanningOrchestratorNode → PlanningNode(s) → PlanningMergerNode
 *   → TicketOrchestratorNode → EditorNode(s) → ReviewNode → MergeNode(s)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AgentEventListener implements EventListener {

    private final ComputationGraphOrchestrator orchestrator;
    private final AgentRunner agentRunner;

    @Override
    public String listenerId() {
        return "WorkflowOrchestratorListener";
    }

    /**
     * Dispatches an agent for execution with its parent and children context.
     */

    @Override
    public void onEvent(Events.GraphEvent event) {
        switch (event) {
            case Events.NodeAddedEvent nodeAddedEvent -> {
                handleNodeAdded(nodeAddedEvent);
            }
            case Events.NodeStatusChangedEvent statusChangedEvent -> {
                handleNodeStatusChanged(statusChangedEvent);
            }
            case Events.GoalCompletedEvent goalCompletedEvent -> {
                log.info("Workflow goal completed: {}", goalCompletedEvent.eventId());
            }
            case Events.NodeBranchedEvent nodeBranchedEvent -> {
                log.debug("Node branched: {}", nodeBranchedEvent.eventId());
            }
            case Events.NodeDeletedEvent nodeDeletedEvent -> {
                log.debug("Node deleted: {}", nodeDeletedEvent.eventId());
            }
            case Events.NodePrunedEvent nodePrunedEvent -> {
                log.debug("Node pruned: {}", nodePrunedEvent.eventId());
            }
            case Events.NodeReviewRequestedEvent nodeReviewRequestedEvent -> {
                log.debug("Review requested for node: {}", nodeReviewRequestedEvent.eventId());
            }
            case Events.NodeStreamDeltaEvent nodeStreamDeltaEvent -> {
                log.debug("Node stream delta: {}", nodeStreamDeltaEvent.eventId());
            }
            case Events.NodeUpdatedEvent nodeUpdatedEvent -> {
                log.debug("Node updated: {}", nodeUpdatedEvent.eventId());
            }
            case Events.SpecMergedEvent specMergedEvent -> {
                log.debug("Spec merged: {}", specMergedEvent.eventId());
            }
            case Events.SpecUpdatedEvent specUpdatedEvent -> {
                log.debug("Spec updated: {}", specUpdatedEvent.eventId());
            }
            case Events.SpecValidatedEvent specValidatedEvent -> {
                log.debug("Spec validated: {}", specValidatedEvent.eventId());
            }
            case Events.WorktreeBranchedEvent worktreeBranchedEvent -> {
                log.debug("Worktree branched: {}", worktreeBranchedEvent.eventId());
            }
            case Events.WorktreeCreatedEvent worktreeCreatedEvent -> {
                log.debug("Worktree created: {}", worktreeCreatedEvent.eventId());
            }
            case Events.WorktreeDiscardedEvent worktreeDiscardedEvent -> {
                log.debug("Worktree discarded: {}", worktreeDiscardedEvent.eventId());
            }
            case Events.WorktreeMergedEvent worktreeMergedEvent -> {
                log.debug("Worktree merged: {}", worktreeMergedEvent.eventId());
            }
            default -> log.warn("Unhandled event type: {}", event.getClass().getSimpleName());
        }
    }

    /**
     * Handles NodeAddedEvent: Dispatches agent for execution if node is in READY status.
     * This triggers the initial execution of nodes that have been registered and are ready to run.
     * 
     * Only nodes with READY status are dispatched. Other statuses (FAILED, WAITING_INPUT, etc.)
     * require different handling and are ignored here.
     */
    private void handleNodeAdded(Events.NodeAddedEvent event) {
        doAgentRunner(event);
    }


    private void doAgentRunner(Events.AgentEvent event) {
        String nodeId = event.nodeId();
        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);

        if (nodeOpt.isEmpty()) {
            log.warn("Node added event received but node not found: {}", nodeId);
            return;
        }

        GraphNode node = nodeOpt.get();

        // Only dispatch nodes that are in READY status
        if (node.status() != GraphNode.NodeStatus.READY) {
            log.debug("Node added but not in READY status: {} ({}) - status: {}",
                    node.title(), nodeId, node.status());
            return;
        }

        log.info("Node added and ready for execution: {} ({})", node.title(), nodeId);

        Optional<GraphNode> parentOpt = Optional.empty();
        if (node.parentNodeId() != null) {
            parentOpt = orchestrator.getNode(node.parentNodeId());
        }

        var children = orchestrator.getChildNodes(nodeId);
        var dispatch = new AgentRunner.AgentDispatchArgs(node, parentOpt.orElse(null), children);

        try {
            agentRunner.runAgent(dispatch);
        } catch (Exception e) {
            log.error("Failed to execute agent for node: {} ({}) during dispatch",
                    node.title(), nodeId, e);
        }
    }

    /**
     * Handles NodeStatusChangedEvent: Triggers next workflow steps when nodes complete.
     * Orchestrates transitions:
     * - DiscoveryOrchestratorNode COMPLETED → kick off DiscoveryMerger
     * - All DiscoveryNodes COMPLETED → kick off DiscoveryMerger
     * - DiscoveryMergerNode COMPLETED → kick off PlanningOrchestrator
     * - PlanningOrchestratorNode COMPLETED → kick off PlanningMerger
     * - All PlanningNodes COMPLETED → kick off PlanningMerger
     * - PlanningMergerNode COMPLETED → kick off TicketOrchestrator
     * - EditorNode (TicketAgent) COMPLETED → kick off ReviewAgent
     * - AgentReviewNode APPROVED → kick off MergerAgent
     * - AgentReviewNode NEEDS_REVISION → kick off revision cycle (re-invoke TicketAgent)
     * - MergeNode COMPLETED → proceed to next ticket or final review
     */
    private void handleNodeStatusChanged(Events.NodeStatusChangedEvent event) {
        String nodeId = event.nodeId();
        GraphNode.NodeStatus newStatus = event.newStatus();

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isEmpty()) {
            log.warn("Node status changed but node not found: {}", nodeId);
            return;
        }

        GraphNode completedNode = nodeOpt.get();
        log.info("Node completed: {} ({}), triggering next phase", completedNode.title(), nodeId);

        // Route to appropriate handler based on node type
        switch (completedNode) {
            case DiscoveryOrchestratorNode discoveryOrchestratorNode -> {
                handleDiscoveryOrchestratorCompleted(discoveryOrchestratorNode);
            }
            case DiscoveryNode discoveryNode -> {
                handleDiscoveryNodeCompleted(discoveryNode);
            }
            case SkillArtifactMergeNode mergeNode when isDiscoveryMerger(mergeNode) -> {
                handleDiscoveryMergerCompleted(mergeNode);
            }
            case PlanningOrchestratorNode planningOrchestratorNode -> {
                handlePlanningOrchestratorCompleted(planningOrchestratorNode);
            }
            case PlanningNode planningNode -> {
                handlePlanningNodeCompleted(planningNode);
            }
            case SkillArtifactMergeNode mergeNode when isPlanningMerger(mergeNode) -> {
                handlePlanningMergerCompleted(mergeNode);
            }
            case EditorNode editorNode -> {
                handleTicketAgentCompleted(editorNode);
            }
            case AgentReviewNode agentReviewNode -> {
                handleReviewAgentCompleted(agentReviewNode);
            }
            case MergeNode mergeNode -> {
                handleMergeNodeCompleted(mergeNode);
            }
            case CollectorNode collectorNode -> {
            }
            case DiscoveryCollectorNode discoveryCollectorNode -> {
            }
            case HumanReviewNode humanReviewNode -> {
            }
            case OrchestratorNode orchestratorNode -> {
            }
            case PlanningCollectorNode planningCollectorNode -> {
            }
            case SkillArtifactMergeNode skillArtifactMergeNode -> {
            }
            case SummaryNode summaryNode -> {
            }
        }
    }

    private void handleDiscoveryOrchestratorCompleted(DiscoveryOrchestratorNode node) {
        log.info("Discovery orchestrator completed, checking if all discovery agents done");
        // Check if all child DiscoveryNodes are completed
        // If yes, kick off DiscoveryMerger
    }

    private void handleDiscoveryNodeCompleted(DiscoveryNode node) {
        log.info("Discovery agent completed, checking if all discovery agents done");
        Optional<GraphNode> parentOpt = orchestrator.getNode(node.parentNodeId());
        if (parentOpt.isPresent() && parentOpt.get() instanceof DiscoveryOrchestratorNode) {
            // Check if all discovery siblings are completed
            // If yes, create and kick off DiscoveryMerger
        }
    }

    private void handleDiscoveryMergerCompleted(SkillArtifactMergeNode node) {
        log.info("Discovery merger completed, transitioning to Planning phase");
        Optional<GraphNode> orchestratorOpt = findRootOrchestrator(node);
        if (orchestratorOpt.isPresent()) {
            // Create PlanningOrchestratorNode
            // Kick off planningOrchestrator
        }
    }

    private void handlePlanningOrchestratorCompleted(PlanningOrchestratorNode node) {
        log.info("Planning orchestrator completed, checking if all planning agents done");
        // Check if all child PlanningNodes are completed
        // If yes, kick off PlanningMerger
    }

    private void handlePlanningNodeCompleted(PlanningNode node) {
        log.info("Planning agent completed, checking if all planning agents done");
        Optional<GraphNode> parentOpt = orchestrator.getNode(node.parentNodeId());
        agentRunner.runAgent(new AgentRunner.AgentDispatchArgs(node, parentOpt.orElse(null), orchestrator.getChildNodes(node.nodeId())));
    }

    private void handlePlanningMergerCompleted(SkillArtifactMergeNode node) {
        log.info("Planning merger completed, transitioning to Ticket Implementation phase");
        Optional<GraphNode> orchestratorOpt = findRootOrchestrator(node);
        if (orchestratorOpt.isPresent()) {
            // Create TicketOrchestratorNode
            // Kick off ticketOrchestrator
        }
    }

    private void handleTicketAgentCompleted(EditorNode node) {
        log.info("Ticket agent (editor) completed, kicking off review agent");
        // Create AgentReviewNode
        // Kick off reviewAgent with implementation content
    }

    private void handleReviewAgentCompleted(AgentReviewNode node) {
        if (node.approved()) {
            log.info("Review approved, kicking off merger agent");
            // Create MergeNode
            // Kick off mergerAgent
        } else {
            log.info("Review needs revision, kicking off revision cycle");
            var parentOpt = orchestrator.getNode(node.parentNodeId()).orElse(null);
            agentRunner.runAgent(new AgentRunner.AgentDispatchArgs(node, parentOpt, orchestrator.getChildNodes(node.nodeId())));
        }
    }

    private void handleMergeNodeCompleted(MergeNode node) {
        log.info("Merge completed, determining next ticket or final review");
        // Check if more tickets exist
        // If yes, kick off next TicketAgent
        // If no, kick off final ReviewAgent or complete workflow
    }

    /**
     * Finds the root OrchestratorNode from any descendant node by traversing up the parent chain.
     */
    private Optional<GraphNode> findRootOrchestrator(GraphNode node) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt = orchestrator.getNode(parentId);
            if (parentOpt.isPresent()) {
                GraphNode parent = parentOpt.get();
                if (parent instanceof OrchestratorNode) {
                    return Optional.of(parent);
                }
                parentId = parent.parentNodeId();
            } else {
                break;
            }
        }
        return Optional.empty();
    }

    private static boolean isDiscoveryMerger(SkillArtifactMergeNode node) {
        return node.title().contains("Discovery") || node.goal().contains("discovery");
    }

    private static boolean isPlanningMerger(SkillArtifactMergeNode node) {
        return node.title().contains("Planning") || node.goal().contains("planning");
    }

    @Override
    public boolean isInterestedIn(Events.GraphEvent eventType) {
        return eventType instanceof Events.NodeAddedEvent || 
               eventType instanceof Events.NodeStatusChangedEvent;
    }
}
