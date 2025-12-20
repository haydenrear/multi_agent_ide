package com.hayden.multiagentide.infrastructure;

import static com.hayden.multiagentide.agent.AgentInterfaces.DiscoveryAgent.DISCOVERY_AGENT_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.DiscoveryCollector.DISCOVERY_COLLECTOR_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.DiscoveryOrchestrator.DISCOVERY_ORCHESTRATOR_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.MergerAgent.MERGER_AGENT_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.OrchestratorAgent.ORCHESTRATOR_AGENT_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.PlanningAgent.PLANNING_AGENT_USER_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.PlanningCollector.PLANNING_COLLECTOR_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.PlanningOrchestrator.PLANNING_ORCHESTRATOR_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.ReviewAgent.REVIEW_AGENT_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.TicketAgent.TICKET_AGENT_START_MESSAGE;
import static com.hayden.multiagentide.agent.AgentInterfaces.TicketOrchestrator.TICKET_ORCHESTRATOR_START_MESSAGE;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.model.MergeResult;
import com.hayden.multiagentide.model.events.Events;
import com.hayden.multiagentide.model.nodes.*;
import com.hayden.multiagentide.model.worktree.WorktreeContext;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    private final AgentInterfaces.DiscoveryCollector discoveryCollector;

    private final AgentInterfaces.PlanningAgent planningAgent;
    private final AgentInterfaces.PlanningOrchestrator planningOrchestrator;
    private final AgentInterfaces.PlanningCollector planningMerger;

    private final AgentInterfaces.TicketOrchestrator ticketOrchestrator;
    private final AgentInterfaces.TicketAgent ticketAgent;
    private final AgentInterfaces.ReviewAgent reviewAgent;
    private final AgentInterfaces.MergerAgent mergerAgent;

    private final AgentInterfaces.OrchestratorAgent orchestratorAgent;

    private final ComputationGraphOrchestrator computationGraphOrchestrator;
    private final GraphRepository graphRepository;
    private final WorktreeService worktreeService;
    private final WorktreeRepository worktreeRepository;

    private static final String META_TICKET_QUEUE = "ticket_queue";
    private static final String META_TICKET_POINTER = "ticket_pointer";
    private static final String META_PARENT_WORKTREE = "parent_worktree_id";
    private static final String META_FINAL_REVIEW = "final_review_requested";

    public record AgentDispatchArgs(
        GraphNode self,
        @Nullable GraphNode parent,
        List<GraphNode> children,
        Events.AgentEvent agentEvent
    ) {}

    public void runOnAgent(AgentDispatchArgs d) {
        var parent = d.parent;

        // TODO: wrap in an exception - propagate error event
        switch (d.agentEvent) {
            case Events.AddMessageEvent addMessageEvent -> addMessageToAgent(
                d,
                addMessageEvent
            );
            case Events.InterruptAgentEvent addMessageEvent -> {
                throw new RuntimeException("Not implemented!");
            }
            case Events.NodeBranchedEvent branched -> {
                //              Implement ability to split what the agent is doing in
                //              another screen and worktree and ask it to do it just a bit differently
                //              or with just a bit of a different goal
                //              NOTE - for this to work, this node ID must be added to the parent's child ID's,
                //                     so that the parent knows to wait for it - so that list must be synchronized
                //                     in memory.
                throw new RuntimeException("Not implemented!");
            }
            case Events.GraphEvent ignored -> graphEvent(d, parent);
        }
    }

    private void graphEvent(AgentDispatchArgs d, GraphNode parent) {
        switch (d.self) {
            case OrchestratorNode orchestratorNode when isNodeReady(orchestratorNode) -> {
                this.runOrchestratorAgent(orchestratorNode);
            }
            case DiscoveryOrchestratorNode discoveryOrchestratorNode when isNodeReady(discoveryOrchestratorNode) -> {
                this.runDiscoveryOrchestratorAgent(discoveryOrchestratorNode, parent);
            }
            case DiscoveryNode discoveryNode when isNodeReady(discoveryNode) -> {
                this.runDiscoveryAgent(discoveryNode, parent);
            }
            case DiscoveryCollectorNode discoveryCollectorNode when isNodeReady(discoveryCollectorNode) -> {
                this.runDiscoveryCollector(discoveryCollectorNode, parent);
            }
            case PlanningOrchestratorNode planningOrchestratorNode when isNodeReady(planningOrchestratorNode) -> {
                this.runPlanningOrchestratorAgent(planningOrchestratorNode, parent);
            }
            case PlanningNode planningNode when isNodeReady(planningNode) -> {
                this.runPlanningAgent(planningNode, parent);
            }
            case PlanningCollectorNode planningCollectorNode when isNodeReady(planningCollectorNode) -> {
                this.runPlanningMergerAgent(planningCollectorNode, parent);
            }
            case EditorNode editorNode when isTicketOrchestrator(editorNode) && isNodeReady(editorNode) -> {
                this.runTicketOrchestratorAgent(editorNode, parent);
            }
            case EditorNode editorNode when isNodeReady(editorNode) -> {
                this.runTicketAgent(editorNode, parent);
            }
            case ReviewNode reviewNode when isNodeReady(reviewNode) ->{
                this.runReviewAgent(reviewNode, parent);
            }
            case MergeNode mergeNode when isNodeReady(mergeNode) -> {
                this.runMergeAgent(mergeNode, parent);
            }
            case CollectorNode collectorNode -> {
            }
            case DiscoveryCollectorNode discoveryCollectorNode -> {
            }
            case DiscoveryNode discoveryNode -> {
            }
            case DiscoveryOrchestratorNode discoveryOrchestratorNode -> {
            }
            case EditorNode editorNode -> {
            }
            case MergeNode mergeNode -> {
            }
            case OrchestratorNode orchestratorNode -> {
            }
            case PlanningCollectorNode planningCollectorNode -> {
            }
            case PlanningNode planningNode -> {
            }
            case PlanningOrchestratorNode planningOrchestratorNode -> {
            }
            case ReviewNode reviewNode -> {
            }
            case SummaryNode summaryNode -> {
            }
        }
    }

    public void addMessageToAgent(
            AgentDispatchArgs dispatchArgs,
            Events.AddMessageEvent addMessageEvent
    ) {
        //        TODO: do this for the rest of the node types
        switch (dispatchArgs.self) {
            case DiscoveryNode node -> {
                discoveryAgent.discoverCodebaseSection(
                        node.nodeId(),
                        addMessageEvent.toAddMessage(),
                        null,
                        null
                );
            }
            case CollectorNode node -> {

            }
            case DiscoveryCollectorNode node -> {}
            case DiscoveryOrchestratorNode node -> {}
            case EditorNode node -> {}
            case MergeNode node -> {}
            case OrchestratorNode node -> {}
            case PlanningCollectorNode node -> {}
            case PlanningNode node -> {}
            case PlanningOrchestratorNode node -> {}
            case ReviewNode node -> {}
            case SummaryNode node -> {}
        }
    }

    private static boolean isNodeReady(GraphNode planningNode) {
        return planningNode.status() == GraphNode.NodeStatus.READY;
    }

    private static boolean isNodeCompleted(GraphNode agentReviewNode) {
        return agentReviewNode.status() == GraphNode.NodeStatus.COMPLETED;
    }

    private static boolean isTicketOrchestrator(EditorNode node) {
        String agentType = Optional.ofNullable(node.agentType())
            .orElse("")
            .toLowerCase();
        return agentType.contains("ticket-orchestrator");
    }

    private static boolean isDiscoveryMerger(GraphNode node) {
        return (
            node.title().contains("Discovery") ||
            node.goal().contains("discovery")
        );
    }

    private static boolean isPlanningMerger(GraphNode node) {
        return (
            node.title().contains("Planning") ||
            node.goal().contains("planning")
        );
    }

    // ===== ORCHESTRATOR PHASE =====

    /**
     * Runs the root OrchestratorAgent to coordinate the entire workflow.
     * Invokes: DiscoveryOrchestrator → Phase 1: Discovery
     */
    public void runOrchestratorAgent(OrchestratorNode orchestratorNode) {
        log.info(
            "Executing OrchestratorAgent for node: {}",
            orchestratorNode.nodeId()
        );
        OrchestratorNode running = markNodeRunning(orchestratorNode);
        try {
            String result = orchestratorAgent.coordinateWorkflow(
                running.nodeId(),
                ORCHESTRATOR_AGENT_START_MESSAGE,
                running.goal(),
                "DISCOVERY"
            );
            log.info(
                "OrchestratorAgent completed for goal: {}",
                running.goal()
            );
            graphRepository.save(running.withOutput(result));

            // Next: Kick off Discovery phase by invoking DiscoveryOrchestrator
            kickOffDiscoveryPhase(running);
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error(
                "OrchestratorAgent failed for node: {}",
                orchestratorNode.nodeId(),
                e
            );
        }
    }

    // ===== PHASE 1: DISCOVERY =====

    /**
     * Initiates the Discovery phase by running DiscoveryOrchestrator.
     * Creates division strategy and kicks off multiple DiscoveryAgent(s).
     */
    private void kickOffDiscoveryPhase(OrchestratorNode orchestratorNode) {
        log.info(
            "Starting PHASE 1: Discovery for orchestrator: {}",
            orchestratorNode.nodeId()
        );
        DiscoveryOrchestratorNode discoveryOrchestratorNode =
            new DiscoveryOrchestratorNode(
                newNodeId(),
                "Discovery Orchestrator",
                orchestratorNode.goal(),
                GraphNode.NodeStatus.READY,
                orchestratorNode.nodeId(),
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                "",
                0,
                0
            );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            orchestratorNode.nodeId(),
            discoveryOrchestratorNode
        );
    }

    /**
     * Runs DiscoveryOrchestrator to determine division strategy.
     * Output: division strategy that determines how many DiscoveryAgent(s) to spawn.
     * Next: Kick off N DiscoveryAgent(s) based on division strategy.
     */
    public void runDiscoveryOrchestratorAgent(
        DiscoveryOrchestratorNode node,
        GraphNode parent
    ) {
        log.info(
            "Executing DiscoveryOrchestratorAgent for node: {}",
            node.nodeId()
        );
        DiscoveryOrchestratorNode running = markNodeRunning(node);
        try {
            String divisionStrategy =
                discoveryOrchestrator.kickOffAnyNumberOfAgentsForCodeSearch(
                    running.nodeId(),
                    running.goal(),
                    DISCOVERY_ORCHESTRATOR_START_MESSAGE
                );
            log.info(
                "DiscoveryOrchestrator determined strategy: {}",
                divisionStrategy
            );

            DiscoveryOrchestratorNode updated = running.withContent(
                divisionStrategy
            );
            graphRepository.save(updated);

            // Next: Kick off DiscoveryAgent(s) based on strategy
            kickOffDiscoveryAgents(updated, divisionStrategy);
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error(
                "DiscoveryOrchestratorAgent failed for node: {}",
                node.nodeId(),
                e
            );
        }
    }

    /**
     * Creates and kicks off multiple DiscoveryAgent instances based on division strategy.
     */
    private void kickOffDiscoveryAgents(
        DiscoveryOrchestratorNode orchestratorNode,
        String divisionStrategy
    ) {
        log.info(
            "Kicking off DiscoveryAgent(s) for orchestrator: {}",
            orchestratorNode.nodeId()
        );
        List<String> focusAreas = parseDivisionStrategy(divisionStrategy);
        if (focusAreas.isEmpty()) {
            focusAreas = List.of("Repository overview");
        }

        for (String focus : focusAreas) {
            DiscoveryNode discoveryNode = new DiscoveryNode(
                newNodeId(),
                "Discover: " + focus,
                orchestratorNode.goal(),
                GraphNode.NodeStatus.READY,
                orchestratorNode.nodeId(),
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                "",
                0,
                0
            );
            computationGraphOrchestrator.addChildNodeAndEmitEvent(
                orchestratorNode.nodeId(),
                discoveryNode
            );
        }
    }


    /**
     * Runs individual DiscoveryAgent to analyze a specific subdomain of the codebase.
     * Output: discovery findings for this subdomain.
     * Next: When all siblings complete, invoke DiscoveryMerger.
     */
    public void runDiscoveryAgent(DiscoveryNode node, GraphNode parent) {
        log.info("Executing DiscoveryAgent for node: {}", node.nodeId());
        DiscoveryNode running = markNodeRunning(node);
        try {
            String subdomainFocus = running.title();
            String findings = discoveryAgent.discoverCodebaseSection(
                running.nodeId(),
                DISCOVERY_AGENT_START_MESSAGE,
                running.goal(),
                subdomainFocus
            );
            log.info(
                "DiscoveryAgent completed findings for subdomain: {}",
                subdomainFocus
            );

            DiscoveryNode withContent = running.withContent(findings);
            graphRepository.save(withContent);

            DiscoveryNode completed = markNodeCompleted(withContent);

            if (
                parent instanceof DiscoveryOrchestratorNode discoveryParent &&
                allChildrenCompletedOfType(discoveryParent, DiscoveryNode.class)
            ) {
                ensureDiscoveryCollector(discoveryParent);
            }
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error("DiscoveryAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Runs DiscoveryMerger to consolidate findings from all DiscoveryAgent(s).
     * Output: unified discovery document.
     * Next: Kick off Planning phase (PlanningOrchestrator).
     */
    public void runDiscoveryCollector(
        DiscoveryCollectorNode node,
        GraphNode parent
    ) {
        log.info("Executing DiscoveryMergerAgent for node: {}", node.nodeId());
        DiscoveryCollectorNode running = markNodeRunning(node);
        try {
            // Collect all discovery findings from sibling DiscoveryNodes
            String allDiscoveryFindings = collectSiblingOutputs(
                running,
                DiscoveryNode.class
            );

            String mergedFindings =
                discoveryCollector.consolidateDiscoveryFindings(
                    running.nodeId(),
                        DISCOVERY_COLLECTOR_START_MESSAGE,
                    running.goal(),
                    allDiscoveryFindings
                );
            log.info(
                "DiscoveryMerger consolidated findings for goal: {}",
                running.goal()
            );

            DiscoveryCollectorNode withContent = running.withContent(
                mergedFindings
            );
            graphRepository.save(withContent);
            markNodeCompleted(withContent);

            if (parent instanceof DiscoveryOrchestratorNode discoveryParent) {
                DiscoveryOrchestratorNode updatedParent =
                    discoveryParent.withContent(mergedFindings);
                graphRepository.save(
                    updatedParent.withStatus(GraphNode.NodeStatus.COMPLETED)
                );
            }

            // Next: Transition to Phase 2 - Planning
            kickOffPlanningPhase(withContent, mergedFindings);
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error(
                "DiscoveryMergerAgent failed for node: {}",
                node.nodeId(),
                e
            );
        }
    }

    // ===== PHASE 2: PLANNING =====

    /**
     * Initiates the Planning phase by running PlanningOrchestrator.
     * Input: discovery findings from Phase 1.
     * Creates division strategy and kicks off multiple PlanningAgent(s).
     */
    private void kickOffPlanningPhase(
        DiscoveryCollectorNode discoveryMergerNode,
        String discoveryContext
    ) {
        log.info(
            "Starting PHASE 2: Planning with discovery context from: {}",
            discoveryMergerNode.nodeId()
        );
        PlanningOrchestratorNode planningOrchestratorNode =
            new PlanningOrchestratorNode(
                newNodeId(),
                "Planning Orchestrator",
                discoveryMergerNode.goal(),
                GraphNode.NodeStatus.READY,
                discoveryMergerNode.nodeId(),
                new ArrayList<>(),
                new HashMap<>(Map.of("discovery_context", discoveryContext)),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                discoveryContext,
                0,
                0
            );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            discoveryMergerNode.nodeId(),
            planningOrchestratorNode
        );
    }

    /**
     * Runs PlanningOrchestrator to determine division strategy for planning work.
     * Input: goal + discovery context.
     * Output: division strategy for PlanningAgent(s).
     * Next: Kick off N PlanningAgent(s) based on division strategy.
     */
    public void runPlanningOrchestratorAgent(
        PlanningOrchestratorNode node,
        GraphNode parent
    ) {
        log.info(
            "Executing PlanningOrchestratorAgent for node: {}",
            node.nodeId()
        );
        PlanningOrchestratorNode running = markNodeRunning(node);
        try {
            // Get discovery context from parent chain
            String discoveryContext = extractDiscoveryContext(node);

            String divisionStrategy =
                    planningOrchestrator.decomposePlanAndCreateWorkItems(
                            running.nodeId(),
                            PLANNING_ORCHESTRATOR_MESSAGE,
                            running.goal()
                    );
            log.info(
                "PlanningOrchestrator determined strategy: {}",
                divisionStrategy
            );

            PlanningOrchestratorNode updated = running.withPlanContent(
                divisionStrategy
            );
            graphRepository.save(updated);

            // Next: Kick off PlanningAgent(s) based on strategy
            kickOffPlanningAgents(updated, divisionStrategy, discoveryContext);
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error(
                "PlanningOrchestratorAgent failed for node: {}",
                node.nodeId(),
                e
            );
        }
    }

    public void planningCollectorAgents(
        PlanningOrchestratorNode orchestratorNode
    ) {}

    /**
     * Creates and kicks off multiple PlanningAgent instances based on division strategy.
     */
    private void kickOffPlanningAgents(
        PlanningOrchestratorNode orchestratorNode,
        String divisionStrategy,
        String discoveryContext
    ) {
        log.info(
            "Kicking off PlanningAgent(s) for orchestrator: {}",
            orchestratorNode.nodeId()
        );
        List<String> planSegments = parseDivisionStrategy(divisionStrategy);
        if (planSegments.isEmpty()) {
            planSegments = List.of("Full plan");
        }

        for (String segment : planSegments) {
            PlanningNode planningNode = new PlanningNode(
                newNodeId(),
                "Plan: " + segment,
                orchestratorNode.goal(),
                GraphNode.NodeStatus.READY,
                orchestratorNode.nodeId(),
                new ArrayList<>(),
                new HashMap<>(Map.of("discovery_context", discoveryContext)),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                "",
                0,
                0
            );
            computationGraphOrchestrator.addChildNodeAndEmitEvent(
                orchestratorNode.nodeId(),
                planningNode
            );
        }
    }

    /**
     * Runs individual PlanningAgent to decompose goal into work items.
     * Input: goal + discovery context.
     * Output: structured plan with work items.
     * Next: When all siblings complete, invoke PlanningMerger.
     */
    public void runPlanningAgent(PlanningNode node, GraphNode parent) {
        log.info("Executing PlanningAgent for node: {}", node.nodeId());
        PlanningNode running = markNodeRunning(node);
        try {
            String plan = planningAgent.decomposePlanAndCreateWorkItems(
                    running.nodeId(),
                    PLANNING_AGENT_USER_MESSAGE,
                    running.goal()
            );
            log.info("PlanningAgent completed plan for goal: {}", node.goal());

            PlanningNode withPlan = running.withPlanContent(plan);
            graphRepository.save(withPlan);
            markNodeCompleted(withPlan);

            if (
                parent instanceof PlanningOrchestratorNode planningParent &&
                allChildrenCompletedOfType(planningParent, PlanningNode.class)
            ) {
                ensurePlanningCollector(planningParent);
            }
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error("PlanningAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Runs PlanningMerger to consolidate plans from all PlanningAgent(s) into tickets.
     * Output: structured tickets ready for implementation.
     * Next: Kick off Ticket Implementation phase (TicketOrchestrator).
     */
    public void runPlanningMergerAgent(
        PlanningCollectorNode node,
        GraphNode parent
    ) {
        log.info("Executing PlanningMergerAgent for node: {}", node.nodeId());
        PlanningCollectorNode running = markNodeRunning(node);
        try {
            // Collect all planning outputs from sibling PlanningNodes
            String allPlanningResults = collectSiblingOutputs(
                running,
                PlanningNode.class
            );

            String tickets = planningMerger.consolidatePlansIntoTickets(
                    node.nodeId(),
                    PLANNING_COLLECTOR_MESSAGE,
                    running.goal(),
                    allPlanningResults
            );
            log.info(
                "PlanningMerger consolidated tickets for goal: {}",
                node.goal()
            );

            PlanningCollectorNode withPlan = running.withPlanContent(tickets);
            graphRepository.save(withPlan);
            markNodeCompleted(withPlan);

            if (parent instanceof PlanningOrchestratorNode planningParent) {
                PlanningOrchestratorNode updatedParent =
                    planningParent.withPlanContent(tickets);
                graphRepository.save(updatedParent.withStatus(GraphNode.NodeStatus.COMPLETED));
            }

            // Next: Transition to Phase 3 - Ticket Implementation
            kickOffTicketPhase(withPlan, tickets);
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error(
                "PlanningMergerAgent failed for node: {}",
                node.nodeId(),
                e
            );
        }
    }

    // ===== PHASE 3: TICKET-BASED IMPLEMENTATION =====

    /**
     * Initiates the Ticket Implementation phase by running TicketOrchestrator.
     * Input: tickets from Phase 2 + discovery context from Phase 1.
     */
    private void kickOffTicketPhase(
        PlanningCollectorNode planningMergerNode,
        String tickets
    ) {
        log.info(
            "Starting PHASE 3: Ticket Implementation with tickets from: {}",
            planningMergerNode.nodeId()
        );
        // Get discovery context from parent chain
        String discoveryContext = extractDiscoveryContext(planningMergerNode);
        String planningContext = tickets;
        String parentWorktreeId = null;

        OrchestratorNode root = findRootOrchestrator(planningMergerNode)
            .map(OrchestratorNode.class::cast)
            .orElseThrow(() ->
                new IllegalStateException(
                    "Cannot find root orchestrator for ticket phase"
                )
            );

        parentWorktreeId = root.mainWorktreeId();
        String ticketBranchName =
            "ticket-orch-" + shortId(planningMergerNode.nodeId());
        String ticketMainWorktreeId = root.mainWorktreeId();
        List<String> branchedSubmodules = new ArrayList<>(
            root.submoduleWorktreeIds()
        );

        try {
            ticketMainWorktreeId = worktreeService
                .branchWorktree(
                    root.mainWorktreeId(),
                    ticketBranchName,
                    planningMergerNode.nodeId()
                )
                .worktreeId();
            branchedSubmodules = root
                .submoduleWorktreeIds()
                .stream()
                .map(submoduleId ->
                    worktreeService
                        .branchSubmoduleWorktree(
                            submoduleId,
                            ticketBranchName,
                            planningMergerNode.nodeId()
                        )
                        .worktreeId()
                )
                .toList();
        } catch (Exception e) {
            log.warn(
                "Ticket orchestrator worktree branching failed, falling back to root worktree. reason={}",
                e.getMessage()
            );
        }

        EditorNode ticketOrchestrator = new EditorNode(
            newNodeId(),
            "Ticket Orchestrator",
            planningMergerNode.goal(),
            GraphNode.NodeStatus.READY,
            planningMergerNode.nodeId(),
            new ArrayList<>(),
            new HashMap<>(
                Map.of(
                    "discovery_context",
                    discoveryContext,
                    "planning_context",
                    planningContext,
                    META_PARENT_WORKTREE,
                    Optional.ofNullable(parentWorktreeId).orElse("")
                )
            ),
            Instant.now(),
            Instant.now(),
            ticketMainWorktreeId,
            branchedSubmodules,
            0,
            0,
            "ticket-orchestrator",
            planningContext,
            true,
            0
        );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            planningMergerNode.nodeId(),
            ticketOrchestrator
        );
    }

    /**
     * Runs TicketOrchestrator to manage ticket-based implementation.
     * Input: tickets + discovery & planning context.
     * Output: orchestration plan.
     * Next: Kick off TicketAgent(s) in sequence, each followed by ReviewAgent and MergerAgent.
     */
    public void runTicketOrchestratorAgent(EditorNode node, GraphNode parent) {
        log.info(
            "Executing TicketOrchestratorAgent for node: {}",
            node.nodeId()
        );
        try {
            EditorNode running = markNodeRunning(node);
            // Get contexts from parent chain
            String discoveryContext = extractDiscoveryContext(running);
            String planningContext = extractPlanningContext(running);

            String tickets = extractTicketsFromContext(planningContext);
            List<String> ticketList = parseTickets(tickets);

            String orchestrationPlan =
                ticketOrchestrator.orchestrateTicketExecution(
                    running.nodeId(),
                    TICKET_ORCHESTRATOR_START_MESSAGE,
                    running.goal(),
                    tickets,
                    discoveryContext,
                    planningContext
                );
            log.info(
                "TicketOrchestrator created orchestration plan: {}",
                orchestrationPlan
            );

            EditorNode withPlan = running.withOutput(orchestrationPlan, 0);
            EditorNode withQueue = persistTicketQueue(withPlan, ticketList);
            graphRepository.save(withQueue);

            if (ticketList.isEmpty()) {
                markNodeCompleted(withQueue);
                return;
            }

            // Next: Kick off first TicketAgent
            kickOffFirstTicketAgent(
                withQueue,
                ticketList,
                discoveryContext,
                planningContext
            );
        } catch (Exception e) {
            log.error(
                "TicketOrchestratorAgent failed for node: {}",
                node.nodeId(),
                e
            );
        }
    }

    /**
     * Creates and kicks off the first TicketAgent for the first ticket.
     */
    private void kickOffFirstTicketAgent(
        EditorNode orchestratorNode,
        List<String> tickets,
        String discoveryContext,
        String planningContext
    ) {
        kickOffTicketAtIndex(
            orchestratorNode,
            tickets,
            0,
            discoveryContext,
            planningContext
        );
    }

    private void kickOffTicketAtIndex(
        EditorNode orchestratorNode,
        List<String> tickets,
        int index,
        String discoveryContext,
        String planningContext
    ) {
        if (index >= tickets.size()) {
            kickOffFinalReview(
                orchestratorNode,
                discoveryContext,
                planningContext
            );
            return;
        }

        String ticketDetails = tickets.get(index);
        String ticketBranchName =
            "ticket-" + (index + 1) + "-" + shortId(orchestratorNode.nodeId());

        String mainWorktreeId = orchestratorNode.mainWorktreeId();
        List<String> submoduleWorktrees =
            orchestratorNode.submoduleWorktreeIds();

        try {
            mainWorktreeId = worktreeService
                .branchWorktree(
                    orchestratorNode.mainWorktreeId(),
                    ticketBranchName,
                    orchestratorNode.nodeId()
                )
                .worktreeId();
            submoduleWorktrees = orchestratorNode
                .submoduleWorktreeIds()
                .stream()
                .map(id ->
                    worktreeService
                        .branchSubmoduleWorktree(
                            id,
                            ticketBranchName,
                            orchestratorNode.nodeId()
                        )
                        .worktreeId()
                )
                .toList();
        } catch (Exception e) {
            log.warn(
                "Ticket worktree branch failed, reusing orchestrator worktree. reason={}",
                e.getMessage()
            );
        }

        var metadataMap = new ConcurrentHashMap<>(
                Map.of(
                        "discovery_context",
                        discoveryContext,
                        "planning_context",
                        planningContext,
                        META_TICKET_POINTER,
                        Integer.toString(index)
                )
        );
        EditorNode ticketNode = new EditorNode(
            newNodeId(),
            "Ticket " + (index + 1),
            ticketDetails,
            GraphNode.NodeStatus.READY,
            orchestratorNode.nodeId(),
            new ArrayList<>(),
            metadataMap,
            Instant.now(),
            Instant.now(),
            mainWorktreeId,
            submoduleWorktrees,
            0,
            0,
            "ticket-agent",
            "",
            true,
            0
        );

        log.info(
            "Dispatching TicketAgent for ticket {} in orchestrator {}",
            index + 1,
            orchestratorNode.nodeId()
        );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            orchestratorNode.nodeId(),
            ticketNode
        );
        updateTicketPointer(orchestratorNode, index);
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
            EditorNode running = markNodeRunning(node);
            // Get contexts from parent chain
            String discoveryContext = extractDiscoveryContext(running);
            String planningContext = extractPlanningContext(running);

            String ticketDetails = running.goal();
            String ticketDetailsFilePath = "/path/to/ticket/details.md"; // In real implementation, resolve from node

            String implementation = ticketAgent.implementTicket(
                running.nodeId(),
                TICKET_AGENT_START_MESSAGE,
                ticketDetails,
                ticketDetailsFilePath,
                discoveryContext,
                planningContext
            );
            log.info(
                "TicketAgent completed implementation for ticket: {}",
                running.nodeId()
            );

            EditorNode withOutput = running.withOutput(implementation, 0);
            graphRepository.save(withOutput);
            markNodeCompleted(withOutput);

            // Next: Invoke ReviewAgent to review this ticket's implementation
            kickOffTicketReview(withOutput, implementation);
        } catch (Exception e) {
            log.error("TicketAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off ReviewAgent for a ticket implementation.
     */
    private void kickOffTicketReview(
        EditorNode ticketNode,
        String implementation
    ) {
        log.info("Kicking off ReviewAgent for ticket: {}", ticketNode.nodeId());

        ReviewNode reviewNode = new ReviewNode(
            newNodeId(),
            "Review: " + ticketNode.title(),
            ticketNode.goal(),
            GraphNode.NodeStatus.READY,
            ticketNode.nodeId(),
            new ArrayList<>(),
            new HashMap<>(Map.of("reviewed_ticket", ticketNode.nodeId())),
            Instant.now(),
            Instant.now(),
            ticketNode.nodeId(),
            implementation,
            false,
            false,
            "",
            "agent-review",
            null
        );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            ticketNode.nodeId(),
            reviewNode
        );
    }

    /**
     * Runs ReviewAgent to evaluate ticket implementation or merged code.
     * Output: approval decision (APPROVED/NEEDS_REVISION) + feedback.
     * Next: If APPROVED: invoke MergerAgent to merge ticket.
     *       If NEEDS_REVISION: invoke TicketAgent again with feedback.
     */
    public void runReviewAgent(ReviewNode node, GraphNode parent) {
        log.info("Executing ReviewAgent for node: {}", node.nodeId());
        try {
            ReviewNode running = markNodeRunning(node);
            String content = running.reviewContent();
            String criteria = buildReviewCriteria();

            String evaluation = reviewAgent.evaluateContent(
                running.nodeId(),
                REVIEW_AGENT_START_MESSAGE,
                content,
                criteria
            );
            log.info(
                "ReviewAgent evaluation for node: {}: {}",
                running.nodeId(),
                evaluation
            );

            boolean approved = evaluation.toLowerCase().contains("approved");
            boolean humanNeeded = evaluation.toLowerCase().contains("human");

            ReviewNode withDecision = running.withReviewDecision(
                approved,
                evaluation
            );
            graphRepository.save(withDecision);

            if (humanNeeded && !approved) {
                ReviewNode waiting = updateNodeStatus(
                    withDecision,
                    GraphNode.NodeStatus.WAITING_INPUT
                );
                graphRepository.save(waiting);
                computationGraphOrchestrator.emitStatusChangeEvent(
                    withDecision.nodeId(),
                    GraphNode.NodeStatus.RUNNING,
                    GraphNode.NodeStatus.WAITING_INPUT,
                    "Human feedback requested"
                );
                computationGraphOrchestrator.emitReviewRequestedEvent(
                    waiting.nodeId(),
                    waiting.nodeId(),
                    "human",
                    waiting.reviewContent()
                );
                return;
            }

            markNodeCompleted(withDecision);

            if (approved) {
                // Next: Invoke MergerAgent to merge changes
                kickOffMerge(withDecision);
            } else {
                // Next: Re-invoke TicketAgent with revision feedback
                kickOffRevisionCycle(withDecision, evaluation);
            }
        } catch (Exception e) {
            log.error("ReviewAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off MergerAgent for merging ticket work into orchestrator worktree.
     */
    private void kickOffMerge(ReviewNode reviewNode) {
        log.info(
            "Kicking off MergerAgent after approval for review: {}",
            reviewNode.nodeId()
        );
        Optional<GraphNode> reviewedOpt = computationGraphOrchestrator.getNode(
            reviewNode.reviewedNodeId()
        );
        if (reviewedOpt.isEmpty()) {
            log.warn(
                "Cannot create merge node; reviewed node {} missing",
                reviewNode.reviewedNodeId()
            );
            return;
        }

        GraphNode reviewed = reviewedOpt.get();
        boolean isFinalReview = "true".equals(
            reviewNode.metadata().getOrDefault(META_FINAL_REVIEW, "false")
        );

        String childWorktreeId = reviewed instanceof EditorNode editor
            ? editor.mainWorktreeId()
            : "";
        String targetWorktreeId = resolveTargetWorktree(reviewNode, reviewed);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("child_worktree_id", childWorktreeId);
        metadata.put("target_worktree_id", targetWorktreeId);
        metadata.put("merge_scope", isFinalReview ? "final" : "ticket");

        MergeNode mergeNode = new MergeNode(
            newNodeId(),
            isFinalReview ? "Final Merge" : "Merge: " + reviewed.title(),
            reviewNode.goal(),
            GraphNode.NodeStatus.READY,
            reviewNode.nodeId(),
            new ArrayList<>(),
            metadata,
            Instant.now(),
            Instant.now(),
            "",
            0,
            0
        );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            reviewNode.nodeId(),
            mergeNode
        );
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
            MergeNode running = markNodeRunning(node);

            String childWorktreeId = running
                .metadata()
                .getOrDefault("child_worktree_id", "");
            String targetWorktreeId = running
                .metadata()
                .getOrDefault("target_worktree_id", "");

            MergeResult mergeResult = null;
            if (!childWorktreeId.isBlank() && !targetWorktreeId.isBlank()) {
                mergeResult = worktreeService.mergeWorktrees(
                    childWorktreeId,
                    targetWorktreeId
                );
            }

            String mergeSummary = mergeResult != null
                ? summarizeMerge(mergeResult)
                : mergerAgent.performMerge(
                      running.nodeId(),
                      MERGER_AGENT_START_MESSAGE,
                      running.goal()
                  );

            log.info(
                "MergerAgent completed merge for node: {}",
                running.nodeId()
            );

            MergeNode withContent = running.withContent(mergeSummary);
            graphRepository.save(withContent);

            if (mergeResult != null) {
                if (!mergeResult.conflicts().isEmpty()) {
                    MergeNode waiting = updateNodeStatus(
                        withContent,
                        GraphNode.NodeStatus.WAITING_INPUT
                    );
                    graphRepository.save(waiting);
                    computationGraphOrchestrator.emitStatusChangeEvent(
                        withContent.nodeId(),
                        GraphNode.NodeStatus.RUNNING,
                        GraphNode.NodeStatus.WAITING_INPUT,
                        "Merge conflicts detected"
                    );
                    return;
                }
                updateWorktreeStatus(
                    childWorktreeId,
                    WorktreeContext.WorktreeStatus.MERGED
                );
            }

            MergeNode completed = markNodeCompleted(withContent);

            // Next: Determine if this is per-ticket or final merge, proceed accordingly
            proceedToNextTicketOrFinalReview(completed, mergeSummary);
        } catch (Exception e) {
            log.error("MergerAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Determines whether to proceed to next ticket or final review.
     */
    private void proceedToNextTicketOrFinalReview(
        MergeNode mergeNode,
        String mergeResult
    ) {
        log.info("Determining next step after merge: {}", mergeNode.nodeId());
        Optional<EditorNode> orchestratorOpt = findAncestorTicketOrchestrator(
            mergeNode
        );
        if (orchestratorOpt.isEmpty()) {
            log.warn(
                "No ticket orchestrator found for merge node {}",
                mergeNode.nodeId()
            );
            return;
        }

        EditorNode orchestratorNode = orchestratorOpt.get();
        List<String> tickets = loadTicketQueue(orchestratorNode);
        int currentIndex = currentTicketPointer(orchestratorNode);
        int nextIndex = currentIndex + 1;
        updateTicketPointer(orchestratorNode, nextIndex);

        String discoveryContext = extractDiscoveryContext(orchestratorNode);
        String planningContext = extractPlanningContext(orchestratorNode);

        boolean isFinalMerge = "final".equals(
            mergeNode.metadata().get("merge_scope")
        );

        if (isFinalMerge) {
            finalizeWorkflow(orchestratorNode);
            return;
        }

        if (nextIndex < tickets.size()) {
            kickOffTicketAtIndex(
                orchestratorNode,
                tickets,
                nextIndex,
                discoveryContext,
                planningContext
            );
        } else {
            kickOffFinalReview(
                orchestratorNode,
                discoveryContext,
                planningContext
            );
        }
    }

    /**
     * Handles revision cycle: Re-invokes TicketAgent with review feedback.
     */
    private void kickOffRevisionCycle(ReviewNode reviewNode, String feedback) {
        log.info(
            "Kicking off revision cycle with feedback for review: {}",
            reviewNode.nodeId()
        );
        Optional<GraphNode> reviewedOpt = computationGraphOrchestrator.getNode(
            reviewNode.reviewedNodeId()
        );
        if (
            reviewedOpt.isEmpty() || !(reviewedOpt.get() instanceof EditorNode)
        ) {
            log.warn(
                "Reviewed node {} missing for revision",
                reviewNode.reviewedNodeId()
            );
            return;
        }

        EditorNode original = (EditorNode) reviewedOpt.get();
        EditorNode revisionNode = new EditorNode(
            newNodeId(),
            original.title() + " (Revision)",
            original.goal() + "\n\nRevision Feedback:\n" + feedback,
            GraphNode.NodeStatus.READY,
            original.parentNodeId(),
            new ArrayList<>(),
            new HashMap<>(
                Map.of(
                    "revision_of",
                    original.nodeId(),
                    "review_feedback",
                    feedback,
                    "discovery_context",
                    extractDiscoveryContext(original),
                    "planning_context",
                    extractPlanningContext(original)
                )
            ),
            Instant.now(),
            Instant.now(),
            original.mainWorktreeId(),
            original.submoduleWorktreeIds(),
            original.completedSubtasks(),
            original.totalSubtasks(),
            original.agentType(),
            "",
            true,
            0
        );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            original.parentNodeId(),
            revisionNode
        );
    }

    // ===== HELPER METHODS =====

    /**
     * Collects output from all sibling nodes of a given type.
     */
    @SuppressWarnings("unchecked")
    private <T extends GraphNode> String collectSiblingOutputs(
        GraphNode node,
        Class<T> siblingType
    ) {
        StringBuilder collected = new StringBuilder();
        Optional<GraphNode> parentOpt = computationGraphOrchestrator.getNode(
            node.parentNodeId()
        );

        if (parentOpt.isPresent()) {
            GraphNode parent = parentOpt.get();
            for (String childId : parent.childNodeIds()) {
                Optional<GraphNode> childOpt =
                    computationGraphOrchestrator.getNode(childId);
                if (
                    childOpt.isPresent() &&
                    siblingType.isInstance(childOpt.get())
                ) {
                    GraphNode child = childOpt.get();
                    if (child instanceof Viewable<?> viewable) {
                        collected.append(viewable.getView()).append("\n");
                    }
                }
            }
        }
        return collected.toString();
    }

    private List<String> parseDivisionStrategy(String strategy) {
        if (strategy == null || strategy.isBlank()) {
            return List.of();
        }
        return Arrays.stream(strategy.split("[\\r\\n]+"))
            .map(String::trim)
            .map(line -> line.replaceFirst("^[\\-*•\\d\\.\\s]+", ""))
            .filter(s -> !s.isBlank())
            .collect(Collectors.toList());
    }

    private <T extends GraphNode> boolean allChildrenCompletedOfType(
        GraphNode parent,
        Class<T> type
    ) {
        List<GraphNode> children = computationGraphOrchestrator.getChildNodes(
            parent.nodeId()
        );
        List<GraphNode> matching = children
            .stream()
            .filter(type::isInstance)
            .toList();
        if (matching.isEmpty()) {
            return false;
        }
        return matching
            .stream()
            .allMatch(c -> c.status() == GraphNode.NodeStatus.COMPLETED);
    }

    private void ensureDiscoveryCollector(DiscoveryOrchestratorNode parent) {
        boolean exists = computationGraphOrchestrator
            .getChildNodes(parent.nodeId())
            .stream()
            .anyMatch(DiscoveryCollectorNode.class::isInstance);
        if (exists) {
            return;
        }
        DiscoveryCollectorNode collector = new DiscoveryCollectorNode(
            newNodeId(),
            "Discovery Collector",
            parent.goal(),
            GraphNode.NodeStatus.READY,
            parent.nodeId(),
            new ArrayList<>(),
            new HashMap<>(),
            Instant.now(),
            Instant.now(),
            "",
            0,
            0
        );
        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            parent.nodeId(),
            collector
        );
    }

    private void ensurePlanningCollector(PlanningOrchestratorNode parent) {
        boolean exists = computationGraphOrchestrator
            .getChildNodes(parent.nodeId())
            .stream()
            .anyMatch(PlanningCollectorNode.class::isInstance);
        if (exists) {
            return;
        }
        PlanningCollectorNode collector = new PlanningCollectorNode(
            newNodeId(),
            "Planning Collector",
            parent.goal(),
            GraphNode.NodeStatus.READY,
            parent.nodeId(),
            new ArrayList<>(),
            new HashMap<>(),
            Instant.now(),
            Instant.now(),
            new ArrayList<>(),
            parent.planContent(),
            parent.estimatedSubtasks(),
            parent.completedSubtasks()
        );
        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            parent.nodeId(),
            collector
        );
    }

    /**
     * Extracts discovery context from parent chain.
     */
    private String extractDiscoveryContext(GraphNode node) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt =
                computationGraphOrchestrator.getNode(parentId);
            if (parentOpt.isEmpty()) {
                break;
            }
            GraphNode parent = parentOpt.get();
            if (parent instanceof DiscoveryCollectorNode collector) {
                return collector.getView();
            }
            if (parent.metadata().containsKey("discovery_context")) {
                return parent.metadata().get("discovery_context");
            }
            parentId = parent.parentNodeId();
        }
        return "";
    }

    /**
     * Extracts planning context from parent chain.
     */
    private String extractPlanningContext(GraphNode node) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt =
                computationGraphOrchestrator.getNode(parentId);
            if (parentOpt.isEmpty()) {
                break;
            }
            GraphNode parent = parentOpt.get();
            if (parent instanceof PlanningCollectorNode collector) {
                return collector.planContent();
            }
            if (parent.metadata().containsKey("planning_context")) {
                return parent.metadata().get("planning_context");
            }
            parentId = parent.parentNodeId();
        }
        return "";
    }

    /**
     * Extracts tickets from planning context.
     */
    private String extractTicketsFromContext(String planningContext) {
        List<String> tickets = parseTickets(planningContext);
        return String.join("\n", tickets);
    }

    private List<String> parseTickets(String planningContext) {
        return parseDivisionStrategy(planningContext);
    }

    public String doPerformMerge(MergeNode mergeNode) {
        return performMerge(mergeNode);
    }

    private String performMerge(MergeNode mergeNode) {
        return mergerAgent.performMerge(
            mergeNode.nodeId(),
            MERGER_AGENT_START_MESSAGE,
            mergeNode.goal()
        );
    }

    private String newNodeId() {
        return UUID.randomUUID().toString();
    }

    // ===== STATE MANAGEMENT & EVENT EMISSION =====

    /**
     * Marks a node as RUNNING and emits status change event.
     * Called before agent execution begins.
     */
    private <T extends GraphNode> T markNodeRunning(T node) {
        GraphNode runningNode = updateNodeStatus(
            node,
            GraphNode.NodeStatus.RUNNING
        );
        graphRepository.save(runningNode);
        computationGraphOrchestrator.emitStatusChangeEvent(
            node.nodeId(),
            GraphNode.NodeStatus.READY,
            GraphNode.NodeStatus.RUNNING,
            "Agent execution started"
        );
        log.debug("Node marked RUNNING: {} ({})", node.title(), node.nodeId());
        return (T) runningNode;
    }

    /**
     * Marks a node as COMPLETED and emits status change event.
     * Called after successful agent execution.
     */
    private <T extends GraphNode> T markNodeCompleted(T node) {
        GraphNode completedNode = updateNodeStatus(
            node,
            GraphNode.NodeStatus.COMPLETED
        );
        graphRepository.save(completedNode);
        computationGraphOrchestrator.emitStatusChangeEvent(
            node.nodeId(),
            GraphNode.NodeStatus.RUNNING,
            GraphNode.NodeStatus.COMPLETED,
            "Agent execution completed successfully"
        );
        log.info("Node marked COMPLETED: {} ({})", node.title(), node.nodeId());
        return (T) completedNode;
    }

    /**
     * Marks a node as FAILED and emits error event.
     * Called when agent execution throws an exception.
     */
    private <T extends GraphNode> T markNodeFailed(T node, Exception error) {
        GraphNode failedNode = updateNodeStatus(
            node,
            GraphNode.NodeStatus.FAILED
        );
        failedNode.metadata().put("error_message", error.getMessage());
        failedNode
            .metadata()
            .put("error_type", error.getClass().getSimpleName());
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
        log.error(
            "Node marked FAILED: {} ({}) - {}",
            node.title(),
            node.nodeId(),
            error.getMessage()
        );
        return (T) failedNode;
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
    private <T extends GraphNode> T updateNodeStatus(
        T node,
        GraphNode.NodeStatus newStatus
    ) {
        return (T) node.withStatus(newStatus);
    }

    /**
     * Finds the root OrchestratorNode from any descendant node by traversing up the parent chain.
     */
    private Optional<GraphNode> findRootOrchestrator(GraphNode node) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt =
                computationGraphOrchestrator.getNode(parentId);
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

    private String buildReviewCriteria() {
        return "Code quality, tests, requirements compliance";
    }

    private String resolveTargetWorktree(ReviewNode reviewNode, GraphNode reviewed) {
        if (reviewNode.metadata().containsKey(META_PARENT_WORKTREE)) {
            return reviewNode.metadata().get(META_PARENT_WORKTREE);
        }
        Optional<EditorNode> orchestratorOpt =
            findAncestorTicketOrchestrator(reviewNode);
        if (orchestratorOpt.isPresent()) {
            return orchestratorOpt.get().mainWorktreeId();
        }
        if (reviewed instanceof EditorNode editor) {
            return editor.mainWorktreeId();
        }
        return "";
    }

    private String summarizeMerge(MergeResult mergeResult) {
        String conflicts = mergeResult
            .conflicts()
            .stream()
            .map(MergeResult.MergeConflict::filePath)
            .collect(Collectors.joining(", "));
        String submodules = mergeResult
            .submoduleUpdates()
            .stream()
            .map(MergeResult.SubmodulePointerUpdate::submoduleName)
            .collect(Collectors.joining(", "));
        return String.format(
            "Merge %s. Parent: %s Child: %s Commit: %s Conflicts: %s Submodules: %s",
            mergeResult.successful() ? "succeeded" : "failed",
            mergeResult.parentWorktreeId(),
            mergeResult.childWorktreeId(),
            mergeResult.mergeCommitHash(),
            conflicts.isBlank() ? "none" : conflicts,
            submodules.isBlank() ? "none" : submodules
        );
    }

    private void updateWorktreeStatus(
        String worktreeId,
        WorktreeContext.WorktreeStatus status
    ) {
        worktreeRepository
            .findById(worktreeId)
            .ifPresent(wt -> {
                WorktreeContext updated =
                    wt instanceof com.hayden.multiagentide.model.worktree.MainWorktreeContext main
                        ? main.withStatus(status)
                        : ((com.hayden.multiagentide.model.worktree.SubmoduleWorktreeContext) wt)
                            .withStatus(status);
                worktreeRepository.save(updated);
            });
    }

    private Optional<EditorNode> findAncestorTicketOrchestrator(GraphNode node) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt =
                computationGraphOrchestrator.getNode(parentId);
            if (parentOpt.isEmpty()) {
                break;
            }
            GraphNode parent = parentOpt.get();
            if (parent instanceof EditorNode editor && isTicketOrchestrator(editor)) {
                return Optional.of(editor);
            }
            parentId = parent.parentNodeId();
        }
        return Optional.empty();
    }

    private EditorNode persistTicketQueue(
        EditorNode node,
        List<String> tickets
    ) {
        Map<String, String> metadata = new ConcurrentHashMap<>(node.metadata());
        metadata.put(META_TICKET_QUEUE, String.join("\n", tickets));
        metadata.put(META_TICKET_POINTER, "0");
        return new EditorNode(
            node.nodeId(),
            node.title(),
            node.goal(),
            node.status(),
            node.parentNodeId(),
            node.childNodeIds(),
            metadata,
            node.createdAt(),
            Instant.now(),
            node.mainWorktreeId(),
            node.submoduleWorktreeIds(),
            node.completedSubtasks(),
            node.totalSubtasks(),
            node.agentType(),
            node.workOutput(),
            node.mergeRequired(),
            node.streamingTokenCount()
        );
    }

    private List<String> loadTicketQueue(EditorNode orchestratorNode) {
        String raw = orchestratorNode.metadata().getOrDefault(
            META_TICKET_QUEUE,
            ""
        );
        if (raw.isBlank()) {
            return List.of();
        }
        return Arrays.stream(raw.split("\\n"))
            .map(String::trim)
            .filter(s -> !s.isBlank())
            .toList();
    }

    private int currentTicketPointer(EditorNode orchestratorNode) {
        try {
            return Integer.parseInt(orchestratorNode.metadata().getOrDefault(META_TICKET_POINTER, "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void updateTicketPointer(EditorNode orchestratorNode, int pointer) {
        orchestratorNode
            .metadata()
            .put(META_TICKET_POINTER, Integer.toString(pointer));
        graphRepository.save(
            new EditorNode(
                orchestratorNode.nodeId(),
                orchestratorNode.title(),
                orchestratorNode.goal(),
                orchestratorNode.status(),
                orchestratorNode.parentNodeId(),
                orchestratorNode.childNodeIds(),
                orchestratorNode.metadata(),
                orchestratorNode.createdAt(),
                Instant.now(),
                orchestratorNode.mainWorktreeId(),
                orchestratorNode.submoduleWorktreeIds(),
                orchestratorNode.completedSubtasks(),
                orchestratorNode.totalSubtasks(),
                orchestratorNode.agentType(),
                orchestratorNode.workOutput(),
                orchestratorNode.mergeRequired(),
                orchestratorNode.streamingTokenCount()
            )
        );
    }

    private void kickOffFinalReview(
        EditorNode orchestratorNode,
        String discoveryContext,
        String planningContext
    ) {
        String allContext =
            discoveryContext +
            "\n\n" +
            planningContext +
            "\n\n" +
            "Ticket orchestration output:\n" +
            orchestratorNode.workOutput();

        String parentWorktreeId = orchestratorNode.metadata().getOrDefault(
            META_PARENT_WORKTREE,
            orchestratorNode.mainWorktreeId()
        );

        ReviewNode reviewNode = new ReviewNode(
            newNodeId(),
            "Final Review",
            orchestratorNode.goal(),
            GraphNode.NodeStatus.READY,
            orchestratorNode.nodeId(),
            new ArrayList<>(),
            new HashMap<>(
                Map.of(
                    META_FINAL_REVIEW,
                    "true",
                    META_PARENT_WORKTREE,
                    parentWorktreeId
                )
            ),
            Instant.now(),
            Instant.now(),
            orchestratorNode.nodeId(),
            allContext,
            false,
            false,
            "",
            "agent-review",
            null
        );

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
            orchestratorNode.nodeId(),
            reviewNode
        );
    }

    private void finalizeWorkflow(EditorNode orchestratorNode) {
        markNodeCompleted(orchestratorNode);
        findRootOrchestrator(orchestratorNode)
            .ifPresent(root -> {
                GraphNode completed = updateNodeStatus(
                    root,
                    GraphNode.NodeStatus.COMPLETED
                );
                graphRepository.save(completed);
                computationGraphOrchestrator.emitStatusChangeEvent(
                    root.nodeId(),
                    GraphNode.NodeStatus.RUNNING,
                    GraphNode.NodeStatus.COMPLETED,
                    "Goal completed"
                );
            });
    }

    private String shortId(String id) {
        return id.length() <= 8 ? id : id.substring(0, 8);
    }
}
