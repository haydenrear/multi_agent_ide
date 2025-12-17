package com.hayden.multiagentide.infrastructure;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.model.mixins.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Executes agents and orchestrates workflow transitions.
 * Handles all agent invocations and manages the flow from one phase to the next.
 * Responsible for state management (RUNNING → COMPLETED/FAILED) and event emission.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AgentRunner {

    private final AgentInterfaces.DiscoveryAgent discoveryAgent;
    private final AgentInterfaces.DiscoveryOrchestrator discoveryOrchestrator;
    private final AgentInterfaces.DiscoveryMerger discoveryMerger;

    private final AgentInterfaces.PlanningAgent planningAgent;
    private final AgentInterfaces.PlanningOrchestrator planningOrchestrator;
    private final AgentInterfaces.PlanningMerger planningMerger;

    private final AgentInterfaces.TicketOrchestrator ticketOrchestrator;
    private final AgentInterfaces.TicketAgent ticketAgent;
    private final AgentInterfaces.ReviewAgent reviewAgent;
    private final AgentInterfaces.MergerAgent mergerAgent;

    private final AgentInterfaces.OrchestratorAgent orchestratorAgent;

    private final ComputationGraphOrchestrator computationGraphOrchestrator;
    private final GraphRepository graphRepository;

    public record AgentDispatchArgs(GraphNode self, @Nullable GraphNode parent, List<GraphNode> children) { }

    public void runAgent(AgentDispatchArgs d) {
        var parent = d.parent;
        switch (d.self) {
            case DiscoveryNode discoveryNode -> {
                this.runDiscoveryAgent(discoveryNode, parent);
            }
            case DiscoveryOrchestratorNode discoveryOrchestratorNode -> {
                this.runDiscoveryOrchestratorAgent(discoveryOrchestratorNode, parent);
            }
            case SkillArtifactMergeNode mergeNode when isDiscoveryMerger(mergeNode) -> {
                this.runDiscoveryMergerAgent(mergeNode, parent);
            }
            case SkillArtifactMergeNode mergeNode when isPlanningMerger(mergeNode) -> {
                this.runPlanningMergerAgent(mergeNode, parent);
            }
            case PlanningOrchestratorNode planningOrchestratorNode -> {
                this.runPlanningOrchestratorAgent(planningOrchestratorNode, parent);
            }
            case PlanningNode planningNode when planningNode.status() == GraphNode.NodeStatus.COMPLETED -> {
                if (parent instanceof PlanningOrchestratorNode orch
                        && computationGraphOrchestrator.getChildNodes(orch.nodeId()).stream()
                        .allMatch(s -> s.status() == GraphNode.NodeStatus.COMPLETED))
                    this.planningCollectorAgents(orch);
            }
            case PlanningNode planningNode when planningNode.status() ==  GraphNode.NodeStatus.READY -> {
                this.runPlanningAgent(planningNode, parent);
            }
            case PlanningNode planningNode -> {

            }
            case EditorNode editorNode -> {
                this.runTicketAgent(editorNode, parent);
            }
            case AgentReviewNode agentReviewNode -> {
                this.runReviewAgent(agentReviewNode, parent);
            }
            case MergeNode mergeNode -> {
                this.runMergeAgent(mergeNode, parent);
            }
            case OrchestratorNode orchestratorNode -> {
                this.runOrchestratorAgent(orchestratorNode);
            }
            case SkillArtifactMergeNode skillArtifactMergeNode -> {
            }
            case CollectorNode collectorNode -> {
            }
            case DiscoveryCollectorNode discoveryCollectorNode -> {
            }
            case HumanReviewNode humanReviewNode -> {
            }
            case PlanningCollectorNode planningCollectorNode -> {
            }
            case SummaryNode summaryNode -> {
            }
        }
    }

    private static boolean isDiscoveryMerger(SkillArtifactMergeNode node) {
        return node.title().contains("Discovery") || node.goal().contains("discovery");
    }

    private static boolean isPlanningMerger(SkillArtifactMergeNode node) {
        return node.title().contains("Planning") || node.goal().contains("planning");
    }

    // ===== ORCHESTRATOR PHASE =====

    /**
     * Runs the root OrchestratorAgent to coordinate the entire workflow.
     * Invokes: DiscoveryOrchestrator → Phase 1: Discovery
     */
    public void runOrchestratorAgent(OrchestratorNode orchestratorNode) {
        log.info("Executing OrchestratorAgent for node: {}", orchestratorNode.nodeId());
        try {
            String result = orchestratorAgent.coordinateWorkflow(
                    orchestratorNode.goal(),
                    "DISCOVERY"
            );
            log.info("OrchestratorAgent completed for goal: {}", orchestratorNode.goal());

            // Next: Kick off Discovery phase by invoking DiscoveryOrchestrator
            kickOffDiscoveryPhase(orchestratorNode);
        } catch (Exception e) {
            log.error("OrchestratorAgent failed for node: {}", orchestratorNode.nodeId(), e);
        }
    }

    // ===== PHASE 1: DISCOVERY =====

    /**
     * Initiates the Discovery phase by running DiscoveryOrchestrator.
     * Creates division strategy and kicks off multiple DiscoveryAgent(s).
     */
    private void kickOffDiscoveryPhase(OrchestratorNode orchestratorNode) {
        log.info("Starting PHASE 1: Discovery for orchestrator: {}", orchestratorNode.nodeId());
        // Create DiscoveryOrchestratorNode as child of OrchestratorNode
        // Then invoke discoveryOrchestrator
    }

    /**
     * Runs DiscoveryOrchestrator to determine division strategy.
     * Output: division strategy that determines how many DiscoveryAgent(s) to spawn.
     * Next: Kick off N DiscoveryAgent(s) based on division strategy.
     */
    public void runDiscoveryOrchestratorAgent(DiscoveryOrchestratorNode node, GraphNode parent) {
        log.info("Executing DiscoveryOrchestratorAgent for node: {}", node.nodeId());
        try {
            String divisionStrategy = discoveryOrchestrator.kickOffAnyNumberOfAgentsForCodeSearch(
                    node.goal()
            );
            log.info("DiscoveryOrchestrator determined strategy: {}", divisionStrategy);

            // Next: Kick off DiscoveryAgent(s) based on strategy
            // This would parse divisionStrategy and create child DiscoveryNodes
            // For now, create placeholder discovery agents
            kickOffDiscoveryAgents(node, divisionStrategy);
        } catch (Exception e) {
            log.error("DiscoveryOrchestratorAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off multiple DiscoveryAgent instances based on division strategy.
     */
    private void kickOffDiscoveryAgents(DiscoveryOrchestratorNode orchestratorNode, String divisionStrategy) {
        log.info("Kicking off DiscoveryAgent(s) for orchestrator: {}", orchestratorNode.nodeId());
        // Parse divisionStrategy to determine number of agents and their focus areas
        // Create DiscoveryNode(s) as children
        // Invoke each discoveryAgent with appropriate parameters
    }

    /**
     * Runs individual DiscoveryAgent to analyze a specific subdomain of the codebase.
     * Output: discovery findings for this subdomain.
     * Next: When all siblings complete, invoke DiscoveryMerger.
     */
    public void runDiscoveryAgent(DiscoveryNode node, GraphNode parent) {
        log.info("Executing DiscoveryAgent for node: {}", node.nodeId());

        // Mark node as RUNNING
        markNodeRunning(node);

        try {
            String subdomainFocus = node.title();
            String findings = discoveryAgent.discoverCodebaseSection(
                    node.goal(),
                    subdomainFocus
            );
            log.info("DiscoveryAgent completed findings for subdomain: {}", subdomainFocus);

            // Mark as completed
            markNodeCompleted(node);

            // Mark as completed and check if all siblings are done
            // If all discovery agents complete, kick off DiscoveryMerger
        } catch (Exception e) {
            log.error("DiscoveryAgent failed for node: {}", node.nodeId(), e);
            markNodeFailed(node, e);
        }
    }

    /**
     * Runs DiscoveryMerger to consolidate findings from all DiscoveryAgent(s).
     * Output: unified discovery document.
     * Next: Kick off Planning phase (PlanningOrchestrator).
     */
    public void runDiscoveryMergerAgent(SkillArtifactMergeNode node, GraphNode parent) {
        log.info("Executing DiscoveryMergerAgent for node: {}", node.nodeId());
        try {
            // Collect all discovery findings from sibling DiscoveryNodes
            String allDiscoveryFindings = collectSiblingOutputs(node, DiscoveryNode.class);

            String mergedFindings = discoveryMerger.consolidateDiscoveryFindings(
                    node.goal(),
                    allDiscoveryFindings
            );
            log.info("DiscoveryMerger consolidated findings for goal: {}", node.goal());

            // Next: Transition to Phase 2 - Planning
            kickOffPlanningPhase(node, mergedFindings);
        } catch (Exception e) {
            log.error("DiscoveryMergerAgent failed for node: {}", node.nodeId(), e);
        }
    }

    // ===== PHASE 2: PLANNING =====

    /**
     * Initiates the Planning phase by running PlanningOrchestrator.
     * Input: discovery findings from Phase 1.
     * Creates division strategy and kicks off multiple PlanningAgent(s).
     */
    private void kickOffPlanningPhase(SkillArtifactMergeNode discoveryMergerNode, String discoveryContext) {
        log.info("Starting PHASE 2: Planning with discovery context from: {}", discoveryMergerNode.nodeId());
        // Create PlanningOrchestratorNode as child of discovery merger
        // Then invoke planningOrchestrator
    }

    /**
     * Runs PlanningOrchestrator to determine division strategy for planning work.
     * Input: goal + discovery context.
     * Output: division strategy for PlanningAgent(s).
     * Next: Kick off N PlanningAgent(s) based on division strategy.
     */
    public void runPlanningOrchestratorAgent(PlanningOrchestratorNode node, GraphNode parent) {
        log.info("Executing PlanningOrchestratorAgent for node: {}", node.nodeId());
        try {
            // Get discovery context from parent chain
            String discoveryContext = extractDiscoveryContext(node);

            String divisionStrategy = planningOrchestrator.decomposePlanAndCreateWorkItems(node.goal());
            log.info("PlanningOrchestrator determined strategy: {}", divisionStrategy);

            // Next: Kick off PlanningAgent(s) based on strategy
            kickOffPlanningAgents(node, divisionStrategy, discoveryContext);

        } catch (Exception e) {
            log.error("PlanningOrchestratorAgent failed for node: {}", node.nodeId(), e);
        }
    }

    public void planningCollectorAgents(PlanningOrchestratorNode orchestratorNode) {

    }

    /**
     * Creates and kicks off multiple PlanningAgent instances based on division strategy.
     */
    private void kickOffPlanningAgents(PlanningOrchestratorNode orchestratorNode, String divisionStrategy, String discoveryContext) {
        log.info("Kicking off PlanningAgent(s) for orchestrator: {}", orchestratorNode.nodeId());
        // Parse divisionStrategy to determine number of agents
        // Create PlanningNode(s) as children
        // Invoke each planningAgent with appropriate parameters
    }

    /**
     * Runs individual PlanningAgent to decompose goal into work items.
     * Input: goal + discovery context.
     * Output: structured plan with work items.
     * Next: When all siblings complete, invoke PlanningMerger.
     */
    public void runPlanningAgent(PlanningNode node, GraphNode parent) {
        log.info("Executing PlanningAgent for node: {}", node.nodeId());
        try {
            String plan = planningAgent.decomposePlanAndCreateWorkItems(
                    node.goal()
            );
            log.info("PlanningAgent completed plan for goal: {}", node.goal());

            // Mark as completed and check if all siblings are done
            // If all planning agents complete, kick off PlanningMerger
        } catch (Exception e) {
            log.error("PlanningAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Runs PlanningMerger to consolidate plans from all PlanningAgent(s) into tickets.
     * Output: structured tickets ready for implementation.
     * Next: Kick off Ticket Implementation phase (TicketOrchestrator).
     */
    public void runPlanningMergerAgent(SkillArtifactMergeNode node, GraphNode parent) {
        log.info("Executing PlanningMergerAgent for node: {}", node.nodeId());
        try {
            // Collect all planning outputs from sibling PlanningNodes
            String allPlanningResults = collectSiblingOutputs(node, PlanningNode.class);

            String tickets = planningMerger.consolidatePlansIntoTickets(
                    node.goal(),
                    allPlanningResults
            );
            log.info("PlanningMerger consolidated tickets for goal: {}", node.goal());

            // Next: Transition to Phase 3 - Ticket Implementation
            kickOffTicketPhase(node, tickets);
        } catch (Exception e) {
            log.error("PlanningMergerAgent failed for node: {}", node.nodeId(), e);
        }
    }

    // ===== PHASE 3: TICKET-BASED IMPLEMENTATION =====

    /**
     * Initiates the Ticket Implementation phase by running TicketOrchestrator.
     * Input: tickets from Phase 2 + discovery context from Phase 1.
     */
    private void kickOffTicketPhase(SkillArtifactMergeNode planningMergerNode, String tickets) {
        log.info("Starting PHASE 3: Ticket Implementation with tickets from: {}", planningMergerNode.nodeId());
        // Get discovery context from parent chain
        String discoveryContext = extractDiscoveryContext(planningMergerNode);
        String planningContext = tickets;

        // Create TicketOrchestratorNode
        // Then invoke ticketOrchestrator
    }

    /**
     * Runs TicketOrchestrator to manage ticket-based implementation.
     * Input: tickets + discovery & planning context.
     * Output: orchestration plan.
     * Next: Kick off TicketAgent(s) in sequence, each followed by ReviewAgent and MergerAgent.
     */
    public void runTicketOrchestratorAgent(DiscoveryOrchestratorNode node, GraphNode parent) {
        log.info("Executing TicketOrchestratorAgent for node: {}", node.nodeId());
        try {
            // Get contexts from parent chain
            String discoveryContext = extractDiscoveryContext(node);
            String planningContext = extractPlanningContext(node);

            String tickets = extractTicketsFromContext(planningContext);

            String orchestrationPlan = ticketOrchestrator.orchestrateTicketExecution(
                    node.goal(),
                    tickets,
                    discoveryContext,
                    planningContext
            );
            log.info("TicketOrchestrator created orchestration plan: {}", orchestrationPlan);

            // Next: Kick off first TicketAgent
            kickOffFirstTicketAgent(node, tickets, discoveryContext, planningContext);
        } catch (Exception e) {
            log.error("TicketOrchestratorAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off the first TicketAgent for the first ticket.
     */
    private void kickOffFirstTicketAgent(DiscoveryOrchestratorNode orchestratorNode, String tickets,
                                         String discoveryContext, String planningContext) {
        log.info("Kicking off first TicketAgent for orchestrator: {}", orchestratorNode.nodeId());
        // Parse tickets to get first ticket
        // Create EditorNode as child
        // Invoke ticketAgent with first ticket details
    }

    /**
     * Runs TicketAgent to implement an individual ticket.
     * Input: ticket details + discovery & planning context + worktree.
     * Output: implementation summary.
     * Next: Invoke ReviewAgent to review the implementation.
     */
    public void runTicketAgent(EditorNode node, GraphNode parent) {
        log.info("Executing TicketAgent for node: {}", node.nodeId());
        try {
            // Get contexts from parent chain
            String discoveryContext = extractDiscoveryContext(node);
            String planningContext = extractPlanningContext(node);

            String ticketDetails = node.goal();
            String ticketDetailsFilePath = "/path/to/ticket/details.md"; // In real implementation, resolve from node

            String implementation = ticketAgent.implementTicket(
                    ticketDetails,
                    ticketDetailsFilePath,
                    discoveryContext,
                    planningContext
            );
            log.info("TicketAgent completed implementation for ticket: {}", node.nodeId());

            // Next: Invoke ReviewAgent to review this ticket's implementation
            kickOffTicketReview(node, implementation);
        } catch (Exception e) {
            log.error("TicketAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off ReviewAgent for a ticket implementation.
     */
    private void kickOffTicketReview(EditorNode ticketNode, String implementation) {
        log.info("Kicking off ReviewAgent for ticket: {}", ticketNode.nodeId());
        // Create AgentReviewNode as child
        // Invoke reviewAgent
    }

    /**
     * Runs ReviewAgent to evaluate ticket implementation or merged code.
     * Output: approval decision (APPROVED/NEEDS_REVISION) + feedback.
     * Next: If APPROVED: invoke MergerAgent to merge ticket.
     *       If NEEDS_REVISION: invoke TicketAgent again with feedback.
     */
    public void runReviewAgent(AgentReviewNode node, GraphNode parent) {
        log.info("Executing ReviewAgent for node: {}", node.nodeId());
        try {
            String content = node.reviewContent();
            String criteria = "Code quality, test coverage, spec compliance";

            String evaluation = reviewAgent.evaluateContent(
                    content,
                    criteria
            );
            log.info("ReviewAgent evaluation for node: {}: {}", node.nodeId(), evaluation);

            boolean approved = evaluation.toLowerCase().contains("approved");

            if (approved) {
                // Next: Invoke MergerAgent to merge changes
                kickOffMerge(node);
            } else {
                // Next: Re-invoke TicketAgent with revision feedback
                kickOffRevisionCycle(node, evaluation);
            }
        } catch (Exception e) {
            log.error("ReviewAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off MergerAgent for merging ticket work into orchestrator worktree.
     */
    private void kickOffMerge(AgentReviewNode reviewNode) {
        log.info("Kicking off MergerAgent after approval for review: {}", reviewNode.nodeId());
        // Create MergeNode as child
        // Invoke mergerAgent
    }

    /**
     * Runs MergerAgent to merge changes.
     * Can merge:
     * - Ticket feature branch into TicketOrchestrator worktree (per-ticket merge)
     * - TicketOrchestrator worktree into main repository (final merge)
     * Output: merge result.
     * Next: If more tickets exist, invoke next TicketAgent.
     *       If all tickets done, invoke final ReviewAgent for overall code.
     *       If final merge, workflow complete.
     */
    public void runMergeAgent(MergeNode node, GraphNode parent) {
        log.info("Executing MergerAgent for node: {}", node.nodeId());
        try {
            String conflictFiles = node.goal();
            String mergeResult = mergerAgent.performMerge(conflictFiles);
            log.info("MergerAgent completed merge for node: {}", node.nodeId());

            // Next: Determine if this is per-ticket or final merge, proceed accordingly
            proceedToNextTicketOrFinalReview(node, mergeResult);
        } catch (Exception e) {
            log.error("MergerAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Determines whether to proceed to next ticket or final review.
     */
    private void proceedToNextTicketOrFinalReview(MergeNode mergeNode, String mergeResult) {
        log.info("Determining next step after merge: {}", mergeNode.nodeId());
        // Check if more tickets exist
        // If yes, kick off next TicketAgent
        // If no, kick off final ReviewAgent
    }

    /**
     * Handles revision cycle: Re-invokes TicketAgent with review feedback.
     */
    private void kickOffRevisionCycle(AgentReviewNode reviewNode, String feedback) {
        log.info("Kicking off revision cycle with feedback for review: {}", reviewNode.nodeId());
        // Find the associated TicketAgent node
        // Re-invoke it with feedback context
    }

    // ===== HELPER METHODS =====

    /**
     * Collects output from all sibling nodes of a given type.
     */
    @SuppressWarnings("unchecked")
    private <T extends GraphNode> String collectSiblingOutputs(GraphNode node, Class<T> siblingType) {
        StringBuilder collected = new StringBuilder();
        Optional<GraphNode> parentOpt = computationGraphOrchestrator.getNode(node.parentNodeId());

        if (parentOpt.isPresent()) {
            GraphNode parent = parentOpt.get();
            for (String childId : parent.childNodeIds()) {
                Optional<GraphNode> childOpt = computationGraphOrchestrator.getNode(childId);
                if (childOpt.isPresent() && siblingType.isInstance(childOpt.get())) {
                    GraphNode child = childOpt.get();
                    if (child instanceof Viewable<?> viewable) {
                        collected.append(viewable.getView()).append("\n");
                    }
                }
            }
        }
        return collected.toString();
    }

    /**
     * Extracts discovery context from parent chain.
     */
    private String extractDiscoveryContext(GraphNode node) {
        // Traverse parent chain to find DiscoveryMergerNode or discovery output
        // Return consolidated discovery context
        return "[Discovery Context]";
    }

    /**
     * Extracts planning context from parent chain.
     */
    private String extractPlanningContext(GraphNode node) {
        // Traverse parent chain to find PlanningMergerNode or planning output
        // Return consolidated planning context with tickets
        return "[Planning Context]";
    }

    /**
     * Extracts tickets from planning context.
     */
    private String extractTicketsFromContext(String planningContext) {
        // Parse planning context to extract structured tickets
        return planningContext;
    }

    public String doPerformMerge(MergeNode mergeNode) {
        return performMerge(mergeNode);
    }

    private String performMerge(MergeNode mergeNode) {
        return mergerAgent.performMerge(mergeNode.goal());
    }

    // ===== STATE MANAGEMENT & EVENT EMISSION =====

    /**
     * Marks a node as RUNNING and emits status change event.
     * Called before agent execution begins.
     */
    private <T extends GraphNode> void markNodeRunning(T node) {
        GraphNode runningNode = updateNodeStatus(node, GraphNode.NodeStatus.RUNNING);
        graphRepository.save(runningNode);
        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                GraphNode.NodeStatus.READY,
                GraphNode.NodeStatus.RUNNING,
                "Agent execution started"
        );
        log.debug("Node marked RUNNING: {} ({})", node.title(), node.nodeId());
    }

    /**
     * Marks a node as COMPLETED and emits status change event.
     * Called after successful agent execution.
     */
    private <T extends GraphNode> void markNodeCompleted(T node) {
        GraphNode completedNode = updateNodeStatus(node, GraphNode.NodeStatus.COMPLETED);
        graphRepository.save(completedNode);
        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                GraphNode.NodeStatus.RUNNING,
                GraphNode.NodeStatus.COMPLETED,
                "Agent execution completed successfully"
        );
        log.info("Node marked COMPLETED: {} ({})", node.title(), node.nodeId());
    }

    /**
     * Marks a node as FAILED and emits error event.
     * Called when agent execution throws an exception.
     */
    private <T extends GraphNode> void markNodeFailed(T node, Exception error) {
        GraphNode failedNode = updateNodeStatus(node, GraphNode.NodeStatus.FAILED);
        failedNode.metadata().put("error_message", error.getMessage());
        failedNode.metadata().put("error_type", error.getClass().getSimpleName());
        failedNode.metadata().put("failed_at", Instant.now().toString());

        graphRepository.save(failedNode);

        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                GraphNode.NodeStatus.RUNNING,
                GraphNode.NodeStatus.FAILED,
                "Agent execution failed: " + error.getMessage()
        );

        // Emit error event to event bus
        emitErrorEvent(node, error);
        log.error("Node marked FAILED: {} ({}) - {}", node.title(), node.nodeId(), error.getMessage());
    }

    /**
     * Emits an error event to the event bus for error handling.
     * Error listeners can then decide to retry, escalate, or halt workflow.
     */
    private void emitErrorEvent(GraphNode failedNode, Exception error) {
        String errorMessage = String.format(
                "Agent failed for node: %s (%s). Parent: %s. Error: %s",
                failedNode.title(),
                failedNode.nodeId(),
                failedNode.parentNodeId(),
                error.getMessage()
        );

        // Create custom error event or use existing error handling mechanism
        // This could emit to a specific error event bus or topic
        computationGraphOrchestrator.emitErrorEvent(
                failedNode.nodeId(),
                failedNode.parentNodeId(),
                error.getClass().getSimpleName(),
                errorMessage
        );
    }

    /**
     * Updates a node's status (immutable pattern).
     * Returns a new instance of the node with updated status and timestamp.
     */
    @SuppressWarnings("unchecked")
    private <T extends GraphNode> T updateNodeStatus(T node, GraphNode.NodeStatus newStatus) {
        return switch (node) {
            case OrchestratorNode n -> (T) n.withStatus(newStatus);
            case DiscoveryOrchestratorNode n -> (T) n.withStatus(newStatus);
            case DiscoveryNode n -> (T) n.withStatus(newStatus);
            case SkillArtifactMergeNode n -> (T) n.withStatus(newStatus);
            case PlanningOrchestratorNode n -> (T) n.withStatus(newStatus);
            case PlanningNode n -> (T) n.withStatus(newStatus);
            case EditorNode n -> (T) n.withStatus(newStatus);
            case AgentReviewNode n -> (T) n.withStatus(newStatus);
            case MergeNode n -> (T) n.withStatus(newStatus);
            default -> node;
        };
    }
}
