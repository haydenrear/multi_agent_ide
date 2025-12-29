package com.hayden.multiagentide.infrastructure;

import com.embabel.agent.api.event.AgentProcessEvent;
import com.embabel.agent.api.event.AgenticEventListener;
import com.embabel.agent.core.Agent;
import com.embabel.agent.core.AgentProcess;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.IoBinding;
import com.embabel.agent.core.ProcessOptions;
import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.AgentModels;
import com.hayden.multiagentide.model.MergeResult;
import com.hayden.multiagentide.model.events.Events;
import com.hayden.multiagentide.model.nodes.*;
import com.hayden.multiagentide.model.worktree.WorktreeContext;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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

    private final ComputationGraphOrchestrator computationGraphOrchestrator;
    private final GraphRepository graphRepository;
    private final WorktreeService worktreeService;
    private final WorktreeRepository worktreeRepository;

    private final AgentPlatform agentPlatform;

    private static final String META_TICKET_QUEUE = "ticket_queue";
    private static final String META_TICKET_POINTER = "ticket_pointer";
    private static final String META_PARENT_WORKTREE = "parent_worktree_id";
    private static final String META_FINAL_REVIEW = "final_review_requested";
    private static final String META_COLLECTOR_REVIEW_GATE = "collector_review_gate";
    private static final String META_COLLECTOR_REVIEW_DECISION = "collector_review_decision";

    public record AgentDispatchArgs(
            GraphNode self,
            @Nullable GraphNode parent,
            List<GraphNode> children,
            Events.AgentEvent agentEvent
    ) {
    }

    private Agent resolveAgent(String agentName) {
        return agentPlatform.agents().stream()
                .filter(agent -> agent.getName().equals(agentName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Agent not found: " + agentName));
    }

    private <T> T addMessageToAgent(String agentName, Object input, Class<T> outputClass, String nodeId) {
        AgentProcess process = agentPlatform.getAgentProcess(nodeId);

        if (process != null) {
            process.addObject(input);
            return process.run().resultOfType(outputClass);
        }

        return null;
    }

    private <T> T runAgent(String agentName, Object input, Class<T> outputClass, String nodeId) {
        Agent agent = resolveAgent(agentName);
        ProcessOptions processOptions = ProcessOptions.DEFAULT.withContextId(nodeId);
        AgentProcess process = agentPlatform.runAgentFrom(
                agent,
                processOptions,
                Map.of(IoBinding.DEFAULT_BINDING, input)
        );
        return process.resultOfType(outputClass);
    }

    public void runOnAgent(AgentDispatchArgs d) {
        var parent = d.parent;

        // TODO: wrap in an exception - propagate error event
        switch (d.agentEvent) {
            case Events.AddMessageEvent addMessageEvent ->
                    addMessageToAgent(
                            d,
                            addMessageEvent
                    );
            case Events.PauseEvent pauseEvent ->
                    handleUserInterrupt(
                            d,
                            AgentModels.InterruptType.PAUSE,
                            pauseEvent.toAddMessage()
                    );
            case Events.StopAgentEvent stopAgentEvent ->
                    handleUserInterrupt(
                            d,
                            AgentModels.InterruptType.STOP,
                            "User stop requested"
                    );
            case Events.NodeReviewRequestedEvent reviewRequestedEvent -> {
                if (d.self instanceof ReviewNode) {
                    log.error("Received request to review review node.");
                } else {
                    handleUserInterrupt(
                            d,
                            AgentModels.InterruptType.HUMAN_REVIEW,
                            reviewRequestedEvent.contentToReview()
                    );
                }
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
            case Events.GraphEvent ignored ->
                    graphEvent(d, parent);
        }
    }

    private void graphEvent(AgentDispatchArgs d, GraphNode parent) {
        if (d.agentEvent instanceof Events.NodeStatusChangedEvent statusChangedEvent) {
            handleInterruptStatusChange(d.self, statusChangedEvent);
        }
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
            case DiscoveryNode discoveryNode when isTerminalEvent(d.agentEvent) -> {
                this.handleDiscoveryNodeTerminal(discoveryNode, parent);
            }
            case DiscoveryCollectorNode discoveryCollectorNode when isNodeReady(discoveryCollectorNode) -> {
                this.runDiscoveryCollector(discoveryCollectorNode, parent);
            }
            case DiscoveryCollectorNode discoveryCollectorNode when isCompletionEvent(d.agentEvent) -> {
                this.handleDiscoveryCollectorCompleted(discoveryCollectorNode, parent);
            }
            case PlanningOrchestratorNode planningOrchestratorNode when isNodeReady(planningOrchestratorNode) -> {
                this.runPlanningOrchestratorAgent(planningOrchestratorNode, parent);
            }
            case PlanningNode planningNode when isNodeReady(planningNode) -> {
                this.runPlanningAgent(planningNode, parent);
            }
            case PlanningNode planningNode when isTerminalEvent(d.agentEvent) -> {
                this.handlePlanningNodeTerminal(planningNode, parent);
            }
            case PlanningCollectorNode planningCollectorNode when isNodeReady(planningCollectorNode) -> {
                this.runPlanningMergerAgent(planningCollectorNode, parent);
            }
            case PlanningCollectorNode planningCollectorNode when isCompletionEvent(d.agentEvent) -> {
                this.handlePlanningCollectorCompleted(planningCollectorNode, parent);
            }
            case TicketCollectorNode ticketCollectorNode when isNodeReady(ticketCollectorNode) -> {
                this.runTicketCollector(ticketCollectorNode, parent);
            }
            case TicketCollectorNode ticketCollectorNode when isNodeCompleted(ticketCollectorNode) && isCompletionEvent(d.agentEvent) -> {
                this.handleTicketCollectorCompleted(ticketCollectorNode, parent);
            }
            case TicketOrchestratorNode ticketNode when isNodeReady(ticketNode) -> {
                this.runTicketOrchestratorAgent(ticketNode, parent);
            }
            case TicketNode ticketNode when isNodeReady(ticketNode) -> {
                this.runTicketAgent(ticketNode, parent);
            }
            case ReviewNode reviewNode when isNodeReady(reviewNode) -> {
                this.runReviewAgent(reviewNode, parent);
            }
            case MergeNode mergeNode when isNodeReady(mergeNode) && isFinalMerge(mergeNode) -> {
                this.runFinalMerge(mergeNode, parent);
            }
            case MergeNode mergeNode when isNodeReady(mergeNode) -> {
                this.runMergeAgent(mergeNode, parent);
            }
            case ReviewNode reviewNode when isNodeCompleted(reviewNode) && isCompletionEvent(d.agentEvent) -> {
            }
            case CollectorNode collectorNode when isNodeCompleted(collectorNode) && isCompletionEvent(d.agentEvent) -> {
            }
            case MergeNode mergeNode when isNodeCompleted(mergeNode) && isCompletionEvent(d.agentEvent) -> {
                this.handleMergeCompleted(mergeNode);
            }
            case CollectorNode collectorNode -> {
            }
            case DiscoveryCollectorNode discoveryCollectorNode -> {
            }
            case DiscoveryNode discoveryNode -> {
            }
            case DiscoveryOrchestratorNode discoveryOrchestratorNode -> {
            }
            case TicketNode ticketNode -> {
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
            case TicketCollectorNode ticketCollectorNode -> {
            }
            case TicketOrchestratorNode ticketOrchestratorNode -> {
            }
            case InterruptNode interruptNode -> {
            }
        }
    }

    private boolean isFinalMerge(MergeNode mergeNode) {
        return mergeNode.isFinalMerge();
    }

    public void addMessageToAgent(
            AgentDispatchArgs dispatchArgs,
            Events.AddMessageEvent addMessageEvent
    ) {
        if (resumeInterruptedNode(dispatchArgs.self, addMessageEvent)) {
            return;
        }
        //        TODO: do this for the rest of the node types
        switch (dispatchArgs.self) {
            case DiscoveryNode node -> {
                runAgent(
                        AgentInterfaces.DISCOVERY_AGENT_NAME,
                        new AgentInterfaces.DiscoveryAgentInput(node.goal(), node.title()),
                        AgentModels.DiscoveryAgentResult.class,
                        node.nodeId()
                );
            }
            case CollectorNode node -> {

            }
            case DiscoveryCollectorNode node -> {
                AgentModels.CollectorDecisionType decision =
                        parseCollectorDecisionFromMessage(
                                addMessageEvent.toAddMessage()
                        );
                applyCollectorReviewDecision(
                        node,
                        dispatchArgs.parent,
                        decision
                );
            }
            case DiscoveryOrchestratorNode node -> {
            }
            case TicketNode node -> {
            }
            case MergeNode node -> {
            }
            case OrchestratorNode node -> {
            }
            case PlanningCollectorNode node -> {
                AgentModels.CollectorDecisionType decision =
                        parseCollectorDecisionFromMessage(
                                addMessageEvent.toAddMessage()
                        );
                applyCollectorReviewDecision(
                        node,
                        dispatchArgs.parent,
                        decision
                );
            }
            case PlanningNode node -> {
            }
            case PlanningOrchestratorNode node -> {
            }
            case ReviewNode node -> {
            }
            case SummaryNode node -> {
            }
            case TicketCollectorNode node -> {
                AgentModels.CollectorDecisionType decision =
                        parseCollectorDecisionFromMessage(
                                addMessageEvent.toAddMessage()
                        );
                applyCollectorReviewDecision(
                        node,
                        dispatchArgs.parent,
                        decision
                );
            }
            case TicketOrchestratorNode ticketOrchestratorNode -> {
            }
            case InterruptNode interruptNode -> {
            }
        }
    }

    private static boolean isNodeReady(GraphNode planningNode) {
        return planningNode.status() == GraphNode.NodeStatus.READY;
    }

    private static boolean isNodeCompleted(GraphNode agentReviewNode) {
        return agentReviewNode.status() == GraphNode.NodeStatus.COMPLETED;
    }

    private static boolean isTerminalStatus(GraphNode.NodeStatus status) {
        return status == GraphNode.NodeStatus.COMPLETED ||
                status == GraphNode.NodeStatus.FAILED ||
                status == GraphNode.NodeStatus.CANCELED ||
                status == GraphNode.NodeStatus.PRUNED;
    }

    private static boolean isTerminalEvent(Events.AgentEvent event) {
        return event instanceof Events.NodeStatusChangedEvent statusChanged &&
                isTerminalStatus(statusChanged.newStatus());
    }

    private static boolean isCompletionEvent(Events.AgentEvent event) {
        return event instanceof Events.NodeStatusChangedEvent statusChanged &&
                statusChanged.newStatus() == GraphNode.NodeStatus.COMPLETED;
    }

    private void handleUserInterrupt(
            AgentDispatchArgs dispatchArgs,
            AgentModels.InterruptType interruptType,
            String reason
    ) {
        GraphNode node = dispatchArgs.self;
        if (!(node instanceof Interruptible)) {
            log.warn("Ignoring interrupt for non-interruptible node {}", node.nodeId());
            return;
        }
        if (isTerminalStatus(node.status())
                && interruptType != AgentModels.InterruptType.STOP
                && interruptType != AgentModels.InterruptType.PRUNE) {
            log.warn(
                    "Ignoring interrupt for node {} in status {}",
                    node.nodeId(),
                    node.status()
            );
            return;
        }

        String interruptResult = sendInterruptSequence(node, interruptType, reason);
        GraphNode withInterrupt = applyInterruptContext(node, interruptType, reason, interruptResult);
        graphRepository.save(withInterrupt);
        computationGraphOrchestrator.emitInterruptStatusEvent(
                node.nodeId(),
                interruptType.name(),
                "RESULT_STORED",
                node.nodeId(),
                node.nodeId()
        );

        GraphNode.NodeStatus newStatus = switch (interruptType) {
            case PAUSE, HUMAN_REVIEW, BRANCH -> GraphNode.NodeStatus.WAITING_INPUT;
            case AGENT_REVIEW -> GraphNode.NodeStatus.WAITING_REVIEW;
            case STOP -> GraphNode.NodeStatus.CANCELED;
            case PRUNE -> GraphNode.NodeStatus.PRUNED;
        };

        GraphNode updated = updateNodeStatus(withInterrupt, newStatus);
        graphRepository.save(updated);
        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                GraphNode.NodeStatus.RUNNING,
                newStatus,
                "Interrupt requested: " + interruptType
        );
        computationGraphOrchestrator.emitInterruptStatusEvent(
                node.nodeId(),
                interruptType.name(),
                "STATUS_EMITTED",
                node.nodeId(),
                node.nodeId()
        );
    }

    private boolean handleAgentInterrupts(
            GraphNode node,
            List<AgentModels.InterruptType> interruptsRequested,
            String reason,
            String resultPayload
    ) {
        if (interruptsRequested == null || interruptsRequested.isEmpty()) {
            return false;
        }
        if (!(node instanceof Interruptible)) {
            log.warn("Ignoring interrupt for non-interruptible node {}", node.nodeId());
            return false;
        }
        AgentModels.InterruptType interruptType = interruptsRequested.get(0);
        GraphNode withInterrupt = applyInterruptContext(node, interruptType, reason, resultPayload);
        graphRepository.save(withInterrupt);
        computationGraphOrchestrator.emitInterruptStatusEvent(
                node.nodeId(),
                interruptType.name(),
                "RESULT_STORED",
                node.nodeId(),
                node.nodeId()
        );

        GraphNode.NodeStatus newStatus = switch (interruptType) {
            case PAUSE, HUMAN_REVIEW, BRANCH -> GraphNode.NodeStatus.WAITING_INPUT;
            case AGENT_REVIEW -> GraphNode.NodeStatus.WAITING_REVIEW;
            case STOP -> GraphNode.NodeStatus.CANCELED;
            case PRUNE -> GraphNode.NodeStatus.PRUNED;
        };

        GraphNode updated = updateNodeStatus(withInterrupt, newStatus);
        graphRepository.save(updated);
        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                GraphNode.NodeStatus.RUNNING,
                newStatus,
                "Agent interrupt requested: " + interruptType
        );
        computationGraphOrchestrator.emitInterruptStatusEvent(
                node.nodeId(),
                interruptType.name(),
                "STATUS_EMITTED",
                node.nodeId(),
                node.nodeId()
        );
        return true;
    }

    private void handleInterruptStatusChange(
            GraphNode node,
            Events.NodeStatusChangedEvent statusChangedEvent
    ) {
        if (statusChangedEvent.newStatus() == GraphNode.NodeStatus.RUNNING) {
            return;
        }
        if (!isInterruptStatus(statusChangedEvent.newStatus())) {
            return;
        }
        if (!(node instanceof Interruptible interruptible)) {
            return;
        }
        InterruptContext context = interruptible.interruptibleContext();
        if (context == null) {
            return;
        }
        if (context.interruptNodeId() != null && !context.interruptNodeId().isBlank()) {
            return;
        }

        String interruptNodeId = newNodeId();
        InterruptContext emittedContext = context
                .withStatus(InterruptContext.InterruptStatus.STATUS_EMITTED)
                .withInterruptNodeId(interruptNodeId);

        GraphNode interruptNode = switch (context.type()) {
            case HUMAN_REVIEW, AGENT_REVIEW -> buildReviewInterruptNode(
                    node,
                    emittedContext
            );
            default -> buildInterruptNode(
                    node,
                    emittedContext,
                    statusChangedEvent.newStatus()
            );
        };

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
                node.nodeId(),
                interruptNode
        );

        GraphNode updated = updateNodeInterruptibleContext(node, emittedContext);
        graphRepository.save(updated);
    }

    private boolean resumeInterruptedNode(GraphNode node, Events.AddMessageEvent addMessageEvent) {
        if (node.status() != GraphNode.NodeStatus.WAITING_INPUT
                && node.status() != GraphNode.NodeStatus.WAITING_REVIEW) {
            return false;
        }
        InterruptContext context = switch (node) {
            case InterruptRecord interruptNode -> interruptNode.interruptContext();
            case Interruptible interruptible -> interruptible.interruptibleContext();
            default -> null;
        };
        if (context == null) {
            return false;
        }

        Optional<GraphNode> originOpt = graphRepository.findById(context.originNodeId());
        if (originOpt.isEmpty()) {
            return false;
        }
        GraphNode origin = originOpt.get();

        if (context.type() == AgentModels.InterruptType.BRANCH) {
            createBranchNode(origin, addMessageEvent.toAddMessage());
        }

        if (origin instanceof Interruptible) {
            GraphNode updatedOrigin = updateNodeInterruptibleContext(
                    origin,
                    context.withStatus(InterruptContext.InterruptStatus.RESOLVED)
            );
            origin = updatedOrigin;
        }

        GraphNode resumed = updateNodeStatus(origin, GraphNode.NodeStatus.READY);
        graphRepository.save(resumed);
        computationGraphOrchestrator.emitStatusChangeEvent(
                origin.nodeId(),
                origin.status(),
                GraphNode.NodeStatus.READY,
                "Interrupt resolved: " + addMessageEvent.toAddMessage()
        );
        computationGraphOrchestrator.emitInterruptStatusEvent(
                origin.nodeId(),
                context.type().name(),
                "RESOLVED",
                context.originNodeId(),
                context.resumeNodeId()
        );

        return true;
    }

    private void createBranchNode(GraphNode origin, String reason) {
        String titleSuffix = reason != null && !reason.isBlank() ? " (branch: " + reason + ")" : " (branch)";
        GraphNode branched = switch (origin) {
            case DiscoveryNode n -> new DiscoveryNode(
                    newNodeId(),
                    n.title() + titleSuffix,
                    n.goal(),
                    GraphNode.NodeStatus.READY,
                    origin.nodeId(),
                    new ArrayList<>(),
                    new HashMap<>(n.metadata()),
                    Instant.now(),
                    Instant.now(),
                    "",
                    0,
                    0
            );
            case PlanningNode n -> new PlanningNode(
                    newNodeId(),
                    n.title() + titleSuffix,
                    n.goal(),
                    GraphNode.NodeStatus.READY,
                    origin.nodeId(),
                    new ArrayList<>(),
                    new HashMap<>(n.metadata()),
                    Instant.now(),
                    Instant.now(),
                    new ArrayList<>(),
                    "",
                    0,
                    0
            );
            case TicketNode n -> new TicketNode(
                    newNodeId(),
                    n.title() + titleSuffix,
                    n.goal(),
                    GraphNode.NodeStatus.READY,
                    origin.nodeId(),
                    new ArrayList<>(),
                    new HashMap<>(n.metadata()),
                    Instant.now(),
                    Instant.now(),
                    n.worktree(),
                    0,
                    0,
                    n.agentType(),
                    "",
                    n.mergeRequired(),
                    0
            );
            default -> null;
        };

        if (branched == null) {
            return;
        }

        computationGraphOrchestrator.addChildNodeAndEmitEvent(
                origin.nodeId(),
                branched
        );
    }

    private boolean isInterruptStatus(GraphNode.NodeStatus status) {
        return status == GraphNode.NodeStatus.WAITING_INPUT
                || status == GraphNode.NodeStatus.WAITING_REVIEW
                || status == GraphNode.NodeStatus.CANCELED
                || status == GraphNode.NodeStatus.PRUNED;
    }

    private GraphNode applyInterruptContext(
            GraphNode node,
            AgentModels.InterruptType interruptType,
            String reason,
            String result
    ) {
        InterruptContext context = new InterruptContext(
                interruptType,
                InterruptContext.InterruptStatus.RESULT_STORED,
                reason,
                node.nodeId(),
                node.nodeId(),
                null,
                result
        );
        return updateNodeInterruptibleContext(node, context);
    }

    private GraphNode updateNodeMetadata(GraphNode node, Map<String, String> metadata) {
        return switch (node) {
            case DiscoveryNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case DiscoveryCollectorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case DiscoveryOrchestratorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case PlanningNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case PlanningCollectorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case PlanningOrchestratorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case TicketNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case TicketCollectorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case TicketOrchestratorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case ReviewNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case MergeNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case OrchestratorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case CollectorNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case SummaryNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
            case InterruptNode n -> n.toBuilder().metadata(metadata).lastUpdatedAt(Instant.now()).build();
        };
    }

    private GraphNode updateNodeInterruptibleContext(GraphNode node, InterruptContext context) {
        return switch (node) {
            case DiscoveryNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case DiscoveryCollectorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case DiscoveryOrchestratorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case PlanningNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case PlanningCollectorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case PlanningOrchestratorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case TicketNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case TicketCollectorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case TicketOrchestratorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case MergeNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case OrchestratorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case CollectorNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            case SummaryNode n -> n.toBuilder().interruptibleContext(context).lastUpdatedAt(Instant.now()).build();
            default -> node;
        };
    }

    private String sendInterruptSequence(
            GraphNode node,
            AgentModels.InterruptType interruptType,
            String reason
    ) {
        try {
            return switch (node) {
                case DiscoveryNode n -> runAgent(
                        AgentInterfaces.DISCOVERY_AGENT_NAME,
                        new AgentInterfaces.DiscoveryAgentInput(n.goal(), n.title()),
                        AgentModels.DiscoveryAgentResult.class,
                        n.nodeId()
                ).output();
                case PlanningNode n -> runAgent(
                        AgentInterfaces.PLANNING_AGENT_NAME,
                        new AgentInterfaces.PlanningAgentInput(n.goal()),
                        AgentModels.PlanningAgentResult.class,
                        n.nodeId()
                ).output();
                case TicketNode n -> runAgent(
                        AgentInterfaces.TICKET_AGENT_NAME,
                        new AgentInterfaces.TicketAgentInput(
                                n.goal(),
                                "interrupt",
                                extractDiscoveryContext(n),
                                extractPlanningContext(n)
                        ),
                        AgentModels.TicketAgentResult.class,
                        n.nodeId()
                ).output();
                case OrchestratorNode n -> runAgent(
                        AgentInterfaces.ORCHESTRATOR_AGENT_NAME,
                        new AgentInterfaces.OrchestratorInput(n.goal(), "interrupt"),
                        AgentModels.OrchestratorAgentResult.class,
                        n.nodeId()
                ).output();
                default -> "Interrupt acknowledged";
            };
        } catch (Exception e) {
            log.error("Interrupt sequence failed for node {}", node.nodeId(), e);
            return "Interrupt sequence failed: " + e.getMessage();
        }
    }

    private InterruptNode buildInterruptNode(
            GraphNode node,
            InterruptContext context,
            GraphNode.NodeStatus status
    ) {
        return new InterruptNode(
                context.interruptNodeId(),
                "Interrupt: " + context.type(),
                node.goal(),
                status,
                node.nodeId(),
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                context
        );
    }

    private ReviewNode buildReviewInterruptNode(
            GraphNode node,
            InterruptContext context
    ) {
        boolean isHuman = context.type() == AgentModels.InterruptType.HUMAN_REVIEW;
        String reviewerAgentType = isHuman ? "human" : "agent-review";
        GraphNode.NodeStatus status = isHuman
                ? GraphNode.NodeStatus.WAITING_INPUT
                : GraphNode.NodeStatus.READY;
        return new ReviewNode(
                context.interruptNodeId(),
                "Review: " + node.title(),
                node.goal(),
                status,
                node.nodeId(),
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                context.originNodeId(),
                context.reason(),
                false,
                true,
                "",
                reviewerAgentType,
                null,
                null,
                context
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
            AgentModels.OrchestratorAgentResult result = runAgent(
                    AgentInterfaces.ORCHESTRATOR_AGENT_NAME,
                    new AgentInterfaces.OrchestratorInput(running.goal(), "DISCOVERY"),
                    AgentModels.OrchestratorAgentResult.class,
                    running.nodeId()
            );
            String output = result.output();
            log.info(
                    "OrchestratorAgent completed for goal: {}",
                    running.goal()
            );
            OrchestratorNode withResult = running.withResult(result);
            graphRepository.save(withResult.withOutput(output));

            if (handleAgentInterrupts(
                    withResult,
                    result.interruptsRequested(),
                    "Orchestrator interrupt requested",
                    output
            )) {
                return;
            }

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
            AgentModels.DiscoveryOrchestratorResult result = runAgent(
                    AgentInterfaces.DISCOVERY_ORCHESTRATOR_AGENT_NAME,
                    new AgentInterfaces.DiscoveryOrchestratorInput(running.goal()),
                    AgentModels.DiscoveryOrchestratorResult.class,
                    running.nodeId()
            );
            String divisionStrategy = resolveDelegationSummary(
                    result.delegation(),
                    result.output()
            );
            List<String> focusAreas = resolveDelegationSegments(
                    result.delegation(),
                    divisionStrategy
            );
            log.info(
                    "DiscoveryOrchestrator determined strategy: {}",
                    divisionStrategy
            );

            DiscoveryOrchestratorNode updated = running
                    .withResult(result)
                    .withContent(divisionStrategy);
            graphRepository.save(updated);

            if (handleAgentInterrupts(
                    updated,
                    result.interruptsRequested(),
                    "Discovery orchestrator interrupt requested",
                    divisionStrategy
            )) {
                return;
            }

            // Next: Kick off DiscoveryAgent(s) based on strategy
            kickOffDiscoveryAgents(updated, focusAreas);
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
            List<String> focusAreas
    ) {
        log.info(
                "Kicking off DiscoveryAgent(s) for orchestrator: {}",
                orchestratorNode.nodeId()
        );
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
     * Next: Discovery collector is inserted when all sibling discovery nodes
     * reach a terminal status.
     */
    public void runDiscoveryAgent(DiscoveryNode node, GraphNode parent) {
        log.info("Executing DiscoveryAgent for node: {}", node.nodeId());
        DiscoveryNode running = markNodeRunning(node);
        try {
            String subdomainFocus = running.title();
            AgentModels.DiscoveryAgentResult result = runAgent(
                    AgentInterfaces.DISCOVERY_AGENT_NAME,
                    new AgentInterfaces.DiscoveryAgentInput(running.goal(), subdomainFocus),
                    AgentModels.DiscoveryAgentResult.class,
                    running.nodeId()
            );
            String findings = result.output();
            log.info(
                    "DiscoveryAgent completed findings for subdomain: {}",
                    subdomainFocus
            );

            DiscoveryNode withContent = running
                    .withResult(result)
                    .withContent(findings);
            graphRepository.save(withContent);

            if (handleAgentInterrupts(
                    withContent,
                    result.interruptsRequested(),
                    "Discovery interrupt requested",
                    findings
            )) {
                return;
            }

            markNodeCompleted(withContent);
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
            List<CollectedNodeStatus> collectedNodes =
                    collectSiblingStatusSnapshots(running, DiscoveryNode.class);

            AgentModels.DiscoveryCollectorResult result = runAgent(
                    AgentInterfaces.DISCOVERY_COLLECTOR_AGENT_NAME,
                    new AgentInterfaces.DiscoveryCollectorInput(running.goal(), allDiscoveryFindings),
                    AgentModels.DiscoveryCollectorResult.class,
                    running.nodeId()
            );
            String mergedFindings = result.consolidatedOutput();
            log.info(
                    "DiscoveryMerger consolidated findings for goal: {}",
                    running.goal()
            );

            DiscoveryCollectorNode withContent = running
                    .withResult(result)
                    .withContent(mergedFindings)
                    .withCollectedNodes(collectedNodes);
            graphRepository.save(withContent);

            if (handleAgentInterrupts(
                    withContent,
                    result.interruptsRequested(),
                    "Discovery collector interrupt requested",
                    mergedFindings
            )) {
                return;
            }

            markNodeCompleted(withContent);

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

            AgentModels.PlanningOrchestratorResult result = runAgent(
                    AgentInterfaces.PLANNING_ORCHESTRATOR_AGENT_NAME,
                    new AgentInterfaces.PlanningOrchestratorInput(running.goal()),
                    AgentModels.PlanningOrchestratorResult.class,
                    running.nodeId()
            );
            String divisionStrategy = resolveDelegationSummary(
                    result.delegation(),
                    result.output()
            );
            List<String> planSegments = resolveDelegationSegments(
                    result.delegation(),
                    divisionStrategy
            );
            log.info(
                    "PlanningOrchestrator determined strategy: {}",
                    divisionStrategy
            );

            PlanningOrchestratorNode updated = running
                    .withResult(result)
                    .withPlanContent(divisionStrategy);
            graphRepository.save(updated);

            if (handleAgentInterrupts(
                    updated,
                    result.interruptsRequested(),
                    "Planning orchestrator interrupt requested",
                    divisionStrategy
            )) {
                return;
            }

            // Next: Kick off PlanningAgent(s) based on strategy
            kickOffPlanningAgents(updated, planSegments, discoveryContext);
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
    ) {
    }

    /**
     * Creates and kicks off multiple PlanningAgent instances based on division strategy.
     */
    private void kickOffPlanningAgents(
            PlanningOrchestratorNode orchestratorNode,
            List<String> planSegments,
            String discoveryContext
    ) {
        log.info(
                "Kicking off PlanningAgent(s) for orchestrator: {}",
                orchestratorNode.nodeId()
        );
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
     * Next: Planning collector is inserted when all sibling planning nodes
     * reach a terminal status.
     */
    public void runPlanningAgent(PlanningNode node, GraphNode parent) {
        log.info("Executing PlanningAgent for node: {}", node.nodeId());
        PlanningNode running = markNodeRunning(node);
        try {
            AgentModels.PlanningAgentResult result = runAgent(
                    AgentInterfaces.PLANNING_AGENT_NAME,
                    new AgentInterfaces.PlanningAgentInput(running.goal()),
                    AgentModels.PlanningAgentResult.class,
                    running.nodeId()
            );
            String plan = result.output();
            log.info("PlanningAgent completed plan for goal: {}", node.goal());

            PlanningNode withPlan = running
                    .withResult(result)
                    .withPlanContent(plan);
            graphRepository.save(withPlan);

            if (handleAgentInterrupts(
                    withPlan,
                    result.interruptsRequested(),
                    "Planning interrupt requested",
                    plan
            )) {
                return;
            }

            markNodeCompleted(withPlan);
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
            List<CollectedNodeStatus> collectedNodes =
                    collectSiblingStatusSnapshots(running, PlanningNode.class);

            AgentModels.PlanningCollectorResult result = runAgent(
                    AgentInterfaces.PLANNING_COLLECTOR_AGENT_NAME,
                    new AgentInterfaces.PlanningCollectorInput(running.goal(), allPlanningResults),
                    AgentModels.PlanningCollectorResult.class,
                    running.nodeId()
            );
            String tickets = result.consolidatedOutput();
            log.info(
                    "PlanningMerger consolidated tickets for goal: {}",
                    node.goal()
            );

            PlanningCollectorNode withPlan = running
                    .withResult(result)
                    .withPlanContent(tickets)
                    .withCollectedNodes(collectedNodes);
            graphRepository.save(withPlan);

            if (handleAgentInterrupts(
                    withPlan,
                    result.interruptsRequested(),
                    "Planning collector interrupt requested",
                    tickets
            )) {
                return;
            }

            markNodeCompleted(withPlan);

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
        String parentWorktreeId;

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
        List<HasWorktree.WorkTree> branchedSubmodules = new ArrayList<>();

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
                            new HasWorktree.WorkTree(worktreeService
                                    .branchSubmoduleWorktree(
                                            submoduleId,
                                            ticketBranchName,
                                            planningMergerNode.nodeId()
                                    )
                                    .worktreeId(),submoduleId, new ArrayList<>())
                    )
                    .toList();
        } catch (Exception e) {
            log.warn(
                    "Ticket orchestrator worktree branching failed, falling back to root worktree. reason={}",
                    e.getMessage()
            );
        }

        TicketOrchestratorNode ticketOrchestrator = new TicketOrchestratorNode(
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
                new HasWorktree.WorkTree(ticketMainWorktreeId, root.mainWorktreeId(), branchedSubmodules),
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
    public void runTicketOrchestratorAgent(TicketOrchestratorNode node, GraphNode parent) {
        log.info(
                "Executing TicketOrchestratorAgent for node: {}",
                node.nodeId()
        );
        try {
            TicketOrchestratorNode running = markNodeRunning(node);
            // Get contexts from parent chain
            String discoveryContext = extractDiscoveryContext(running);
            String planningContext = extractPlanningContext(running);

            String tickets = extractTicketsFromContext(planningContext);
            List<String> ticketList = parseTickets(tickets);

            AgentModels.TicketOrchestratorResult result = runAgent(
                    AgentInterfaces.TICKET_ORCHESTRATOR_AGENT_NAME,
                    new AgentInterfaces.TicketOrchestratorInput(
                            running.goal(),
                            tickets,
                            discoveryContext,
                            planningContext
                    ),
                    AgentModels.TicketOrchestratorResult.class,
                    running.nodeId()
            );
            String orchestrationPlan = result.output();
            log.info(
                    "TicketOrchestrator created orchestration plan: {}",
                    orchestrationPlan
            );

            TicketOrchestratorNode withPlan = running
                    .withTicketOrchestratorResult(result)
                    .withOutput(orchestrationPlan, 0);
            TicketOrchestratorNode withQueue = persistTicketQueue(withPlan, ticketList);
            graphRepository.save(withQueue);

            if (handleAgentInterrupts(
                    withQueue,
                    result.interruptsRequested(),
                    "Ticket orchestrator interrupt requested",
                    orchestrationPlan
            )) {
                return;
            }

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
            TicketOrchestratorNode orchestratorNode,
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
            TicketOrchestratorNode orchestratorNode,
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

        String branchedWorktree = null;
        List<HasWorktree.WorkTree> submoduleWorktrees =
                orchestratorNode.submoduleWorktreeIds();

        try {
            branchedWorktree = worktreeService
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
                            new HasWorktree.WorkTree(
                                    worktreeService
                                            .branchSubmoduleWorktree(
                                                    id.worktreeId(),
                                                    ticketBranchName,
                                                    orchestratorNode.nodeId()
                                            )
                                            .worktreeId(),
                                    id.worktreeId(),
                                    new ArrayList<>()
                            )
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
        TicketNode ticketNode = new TicketNode(
                newNodeId(),
                "Ticket " + (index + 1),
                ticketDetails,
                GraphNode.NodeStatus.READY,
                orchestratorNode.nodeId(),
                new ArrayList<>(),
                metadataMap,
                Instant.now(),
                Instant.now(),
                new HasWorktree.WorkTree(branchedWorktree, orchestratorNode.mainWorktreeId(), submoduleWorktrees),
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
    public void runTicketAgent(TicketNode node, GraphNode parent) {
        log.info("Executing TicketAgent for node: {}", node.nodeId());
        try {
            TicketNode running = markNodeRunning(node);
            // Get contexts from parent chain
            String discoveryContext = extractDiscoveryContext(running);
            String planningContext = extractPlanningContext(running);

            String ticketDetails = running.goal();
            String ticketDetailsFilePath = "/path/to/ticket/details.md"; // In real implementation, resolve from node

            AgentModels.TicketAgentResult result = runAgent(
                    AgentInterfaces.TICKET_AGENT_NAME,
                    new AgentInterfaces.TicketAgentInput(
                            ticketDetails,
                            ticketDetailsFilePath,
                            discoveryContext,
                            planningContext
                    ),
                    AgentModels.TicketAgentResult.class,
                    running.nodeId()
            );
            String implementation = result.output();
            log.info(
                    "TicketAgent completed implementation for ticket: {}",
                    running.nodeId()
            );

            TicketNode withOutput = running
                    .withTicketAgentResult(result)
                    .withOutput(implementation, 0);
            graphRepository.save(withOutput);

            if (handleAgentInterrupts(
                    withOutput,
                    result.interruptsRequested(),
                    "Ticket interrupt requested",
                    implementation
            )) {
                return;
            }

            markNodeCompleted(withOutput);

            // Next: Invoke ReviewAgent to review this ticket's implementation
            kickOffTicketReview(withOutput, implementation);
        } catch (Exception e) {
            log.error("TicketAgent failed for node: {}", node.nodeId(), e);
        }
    }

    public void runTicketCollector(TicketCollectorNode node, GraphNode parent) {
        log.info("Executing TicketCollector for node: {}", node.nodeId());
        TicketCollectorNode running = markNodeRunning(node);
        try {
            String allTicketResults = collectSiblingOutputs(
                    running,
                    TicketNode.class
            );
            List<CollectedNodeStatus> collectedNodes =
                    collectSiblingStatusSnapshots(running, TicketNode.class);

            AgentModels.TicketCollectorResult result = runAgent(
                    AgentInterfaces.TICKET_COLLECTOR_AGENT_NAME,
                    new AgentInterfaces.TicketCollectorInput(running.goal(), allTicketResults),
                    AgentModels.TicketCollectorResult.class,
                    running.nodeId()
            );
            String summary = result.consolidatedOutput();

            TicketCollectorNode withSummary = running
                    .withResult(result)
                    .withSummary(summary)
                    .withCollectedNodes(collectedNodes);
            graphRepository.save(withSummary);

            if (handleAgentInterrupts(
                    withSummary,
                    result.interruptsRequested(),
                    "Ticket collector interrupt requested",
                    summary
            )) {
                return;
            }

            markNodeCompleted(withSummary);
        } catch (Exception e) {
            markNodeFailed(running, e);
            log.error("TicketCollector failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Creates and kicks off ReviewAgent for a ticket implementation.
     */
    private void kickOffTicketReview(
            TicketNode ticketNode,
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
     * If NEEDS_REVISION: invoke TicketAgent again with feedback.
     */
    public void runReviewAgent(ReviewNode node, GraphNode parent) {
        log.info("Executing ReviewAgent for node: {}", node.nodeId());
        try {
            ReviewNode running = markNodeRunning(node);
            String content = running.reviewContent();
            String criteria = buildReviewCriteria();

            AgentModels.ReviewAgentResult result = runAgent(
                    AgentInterfaces.REVIEW_AGENT_NAME,
                    new AgentInterfaces.ReviewAgentInput(content, criteria),
                    AgentModels.ReviewAgentResult.class,
                    running.nodeId()
            );
            String evaluation = result.output();
            log.info(
                    "ReviewAgent evaluation for node: {}: {}",
                    running.nodeId(),
                    evaluation
            );

            boolean approved = evaluation.toLowerCase().contains("approved");
            boolean humanNeeded = evaluation.toLowerCase().contains("human");

            ReviewNode withDecision = running
                    .withResult(result)
                    .withReviewDecision(approved, evaluation);
            graphRepository.save(withDecision);

            if (handleAgentInterrupts(
                    withDecision,
                    result.interruptsRequested(),
                    "Review interrupt requested",
                    evaluation
            )) {
                return;
            }

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

        String childWorktreeId = reviewed instanceof HasWorktree editor && editor.worktree() != null
                ? editor.worktree().worktreeId()
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

    public void runFinalMerge(MergeNode node, GraphNode parent) {
        log.info("Executing MergerAgent for node: {}", node.nodeId());
        try {

            String childWorktreeId = node
                    .metadata()
                    .getOrDefault("child_worktree_id", "");
            String targetWorktreeId = node
                    .metadata()
                    .getOrDefault("target_worktree_id", "");

            doMergeAgent(node, childWorktreeId, targetWorktreeId);
        } catch (Exception e) {
            log.error("MergerAgent failed for node: {}", node.nodeId(), e);
        }
    }

    /**
     * Runs MergerAgent to merge changes.
     * Can merge:
     * - Ticket feature branch into TicketOrchestrator worktree (per-ticket merge)
     * - TicketOrchestrator worktree into main repository (final merge)
     * Output: merge result.
     * Next steps are handled in the merge-completed event handler.
     */
    public void runMergeAgent(MergeNode node, GraphNode parent) {
        log.info("Executing MergerAgent for node: {}", node.nodeId());
        try {

            String childWorktreeId = node
                    .metadata()
                    .getOrDefault("child_worktree_id", "");
            String targetWorktreeId = node
                    .metadata()
                    .getOrDefault("target_worktree_id", "");

            doMergeAgent(node, childWorktreeId, targetWorktreeId);
        } catch (Exception e) {
            log.error("MergerAgent failed for node: {}", node.nodeId(), e);
        }
    }

    private boolean doMergeAgent(MergeNode node, String childWorktreeId, String targetWorktreeId) {
        MergeNode running = markNodeRunning(node);

        if (childWorktreeId.isBlank() || targetWorktreeId.isBlank()) {
            log.info(
                    "Skipping merge; missing worktree identifiers for node: {}",
                    running.nodeId()
            );
            MergeNode noMerge = running.withContent(
                    "No merge performed: missing worktree identifiers."
            );
            graphRepository.save(noMerge);
            markNodeCompleted(noMerge);
            return true;
        }

        MergeResult mergeResult = null;
        if (!childWorktreeId.isBlank() && !targetWorktreeId.isBlank()) {
            mergeResult = worktreeService.mergeWorktrees(
                    childWorktreeId,
                    targetWorktreeId
            );
        }

        String mergeSummary = mergeResult != null
                ? summarizeMerge(mergeResult)
                : "Merge result unavailable (merge service returned null).";
        String conflictFiles = mergeResult != null
                ? mergeResult.conflicts().stream()
                .map(MergeResult.MergeConflict::filePath)
                .filter(path -> path != null && !path.isBlank())
                .distinct()
                .collect(Collectors.joining("\n"))
                : "";
        String mergeContext = buildMergeContext(
                running,
                childWorktreeId,
                targetWorktreeId,
                mergeResult
        );
        AgentModels.MergerAgentResult agentResult = runAgent(
                AgentInterfaces.MERGER_AGENT_NAME,
                new AgentInterfaces.MergerAgentInput(
                        mergeContext,
                        mergeSummary,
                        conflictFiles.isBlank() ? "None" : conflictFiles
                ),
                AgentModels.MergerAgentResult.class,
                running.nodeId()
        );
        String agentOutput = agentResult.output();
        String combinedSummary = mergeResult != null
                ? mergeSummary + "\n\nMergerAgent:\n" + agentOutput
                : agentOutput;

        log.info(
                "MergerAgent completed merge for node: {}",
                running.nodeId()
        );

        MergeNode withContent = running
                .withResult(agentResult)
                .withContent(combinedSummary);
        graphRepository.save(withContent);

        if (handleAgentInterrupts(
                withContent,
                agentResult.interruptsRequested(),
                "Merge interrupt requested",
                combinedSummary
        )) {
            return true;
        }

        if (agentResult.isNeedingAgentReview()) {
            throw new RuntimeException("Did nto implement yet!");
        } else if (agentResult.isNeedingHumanReview()) {
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
            return true;
        } else if (worktreeService.containsMergeConflicts(childWorktreeId, targetWorktreeId)) {
            log.error("Detected merge conflicts not fixed but also did not request any more review!");
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
            return true;
        }


        updateWorktreeStatus(
                childWorktreeId,
                WorktreeContext.WorktreeStatus.MERGED
        );

        markNodeCompleted(withContent);
        return false;
    }

    /**
     * Handles merge-completed events by advancing ticket or final review flow.
     */
    private void handleMergeCompleted(MergeNode mergeNode) {
        log.info("Determining next step after merge: {}", mergeNode.nodeId());
        Optional<TicketOrchestratorNode> orchestratorOpt = findAncestorTicketOrchestrator(
                mergeNode
        );
        if (orchestratorOpt.isEmpty()) {
            log.warn(
                    "No ticket orchestrator found for merge node {}",
                    mergeNode.nodeId()
            );
            return;
        }

        TicketOrchestratorNode orchestratorNode = orchestratorOpt.get();
        List<String> tickets = loadTicketQueue(orchestratorNode);
        int currentIndex = currentTicketPointer(orchestratorNode);
        int nextIndex = currentIndex + 1;
        updateTicketPointer(orchestratorNode, nextIndex);

        String discoveryContext = extractDiscoveryContext(orchestratorNode);
        String planningContext = extractPlanningContext(orchestratorNode);

        boolean isFinalMerge = "final".equals(mergeNode.metadata().get("merge_scope"));

        if (isFinalMerge) {
            ensureTicketCollector(orchestratorNode);
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
        if (reviewedOpt.isEmpty() || !(reviewedOpt.get() instanceof TicketNode)) {
            log.warn(
                    "Reviewed node {} missing for revision",
                    reviewNode.reviewedNodeId()
            );
            return;
        }

        TicketNode original = (TicketNode) reviewedOpt.get();

        TicketNode revisionNode = new TicketNode(
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
                original.worktree(),
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

    private <T extends GraphNode> List<CollectedNodeStatus> collectSiblingStatusSnapshots(
            GraphNode node,
            Class<T> siblingType
    ) {
        List<CollectedNodeStatus> collected = new ArrayList<>();
        Optional<GraphNode> parentOpt = computationGraphOrchestrator.getNode(
                node.parentNodeId()
        );
        if (parentOpt.isEmpty()) {
            return collected;
        }
        GraphNode parent = parentOpt.get();
        for (String childId : parent.childNodeIds()) {
            Optional<GraphNode> childOpt =
                    computationGraphOrchestrator.getNode(childId);
            if (childOpt.isEmpty() || !siblingType.isInstance(childOpt.get())) {
                continue;
            }
            GraphNode child = childOpt.get();
            collected.add(new CollectedNodeStatus(
                    child.nodeId(),
                    child.title(),
                    child.nodeType(),
                    child.status()
            ));
        }
        return collected;
    }

    private AgentModels.CollectorDecisionType resolveCollectorDecision(
            AgentModels.CollectorDecision decision
    ) {
        if (decision == null || decision.decisionType() == null) {
            return AgentModels.CollectorDecisionType.ADVANCE_PHASE;
        }
        return decision.decisionType();
    }

    private AgentModels.CollectorDecisionType parseCollectorDecisionOverride(
            GraphNode node
    ) {
        String value = node.metadata().get(META_COLLECTOR_REVIEW_DECISION);
        if (value == null || value.isBlank()) {
            return null;
        }
        String normalized = value.trim().toUpperCase();
        return switch (normalized) {
            case "ROUTE_BACK" -> AgentModels.CollectorDecisionType.ROUTE_BACK;
            case "STOP" -> AgentModels.CollectorDecisionType.STOP;
            case "ADVANCE_PHASE" -> AgentModels.CollectorDecisionType.ADVANCE_PHASE;
            default -> null;
        };
    }

    private AgentModels.CollectorDecisionType parseCollectorDecisionFromMessage(
            String message
    ) {
        if (message == null || message.isBlank()) {
            return null;
        }
        String lower = message.toLowerCase();
        if (lower.contains("rerun") || lower.contains("route back") || lower.contains("back")) {
            return AgentModels.CollectorDecisionType.ROUTE_BACK;
        }
        if (lower.contains("stop") || lower.contains("halt")) {
            return AgentModels.CollectorDecisionType.STOP;
        }
        if (lower.contains("advance") || lower.contains("next") || lower.contains("proceed")) {
            return AgentModels.CollectorDecisionType.ADVANCE_PHASE;
        }
        return null;
    }

    private boolean isCollectorReviewGateEnabled(GraphNode node) {
        return "true".equalsIgnoreCase(
                node.metadata().get(META_COLLECTOR_REVIEW_GATE)
        );
    }

    private void requestCollectorReview(GraphNode node) {
        GraphNode waiting = updateNodeStatus(
                node,
                GraphNode.NodeStatus.WAITING_INPUT
        );
        graphRepository.save(waiting);
        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                node.status(),
                GraphNode.NodeStatus.WAITING_INPUT,
                "Collector review requested"
        );
        String content = "";
        if (waiting instanceof Viewable<?> viewable && viewable.getView() != null) {
            content = viewable.getView().toString();
        }
        computationGraphOrchestrator.emitReviewRequestedEvent(
                waiting.nodeId(),
                waiting.nodeId(),
                "human",
                content
        );
    }

    private void applyCollectorReviewDecision(
            GraphNode node,
            GraphNode parent,
            AgentModels.CollectorDecisionType decisionType
    ) {
        if (decisionType == null) {
            return;
        }
        node.metadata().put(META_COLLECTOR_REVIEW_DECISION, decisionType.name());
        GraphNode updated = updateNodeStatus(
                node,
                GraphNode.NodeStatus.COMPLETED
        );
        graphRepository.save(updated);
        computationGraphOrchestrator.emitStatusChangeEvent(
                node.nodeId(),
                node.status(),
                GraphNode.NodeStatus.COMPLETED,
                "Collector review decision received"
        );

        if (updated instanceof DiscoveryCollectorNode discoveryCollector) {
            handleDiscoveryCollectorCompleted(discoveryCollector, parent);
        } else if (updated instanceof PlanningCollectorNode planningCollector) {
            handlePlanningCollectorCompleted(planningCollector, parent);
        } else if (updated instanceof TicketCollectorNode ticketCollectorNode) {
            handleTicketCollectorCompleted(ticketCollectorNode, parent);
        }
    }

    private void applyCollectorDecision(
            AgentModels.CollectorDecision decision,
            Runnable onAdvance,
            Runnable onRouteBack,
            Runnable onStop
    ) {
        switch (resolveCollectorDecision(decision)) {
            case ROUTE_BACK -> onRouteBack.run();
            case STOP -> onStop.run();
            case ADVANCE_PHASE -> onAdvance.run();
        }
    }

    private void triggerOrchestratorRerun(GraphNode parent, String reason) {
        GraphNode updated = updateNodeStatus(parent, GraphNode.NodeStatus.READY);
        graphRepository.save(updated);
        computationGraphOrchestrator.emitStatusChangeEvent(
                parent.nodeId(),
                parent.status(),
                GraphNode.NodeStatus.READY,
                reason
        );
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

    private String resolveDelegationSummary(
            AgentModels.DelegationPlan delegation,
            String fallback
    ) {
        if (delegation == null) {
            return fallback;
        }
        String summary = delegation.summary();
        return summary != null && !summary.isBlank() ? summary : fallback;
    }

    private List<String> resolveDelegationSegments(
            AgentModels.DelegationPlan delegation,
            String fallback
    ) {
        if (delegation != null && delegation.subAgentGoals() != null) {
            var values = delegation.subAgentGoals().values().stream()
                    .filter(value -> value != null && !value.isBlank())
                    .toList();
            if (!values.isEmpty()) {
                return values;
            }
        }
        return parseDivisionStrategy(fallback);
    }

    private <T extends GraphNode> boolean allChildrenTerminalOfType(
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
                .allMatch(c -> isTerminalStatus(c.status()));
    }

    private void handleDiscoveryNodeTerminal(
            DiscoveryNode node,
            GraphNode parent
    ) {
        if (
                parent instanceof DiscoveryOrchestratorNode discoveryParent &&
                        allChildrenTerminalOfType(discoveryParent, DiscoveryNode.class)
        ) {
            ensureDiscoveryCollector(discoveryParent);
        }
    }

    private void handlePlanningNodeTerminal(
            PlanningNode node,
            GraphNode parent
    ) {
        if (
                parent instanceof PlanningOrchestratorNode planningParent &&
                        allChildrenTerminalOfType(planningParent, PlanningNode.class)
        ) {
            ensurePlanningCollector(planningParent);
        }
    }

    private void handleDiscoveryCollectorCompleted(
            DiscoveryCollectorNode node,
            GraphNode parent
    ) {
        if (!(parent instanceof DiscoveryOrchestratorNode discoveryParent)) {
            return;
        }
        AgentModels.CollectorDecisionType overrideDecision =
                parseCollectorDecisionOverride(node);
        if (isCollectorReviewGateEnabled(node) && overrideDecision == null) {
            requestCollectorReview(node);
            return;
        }
        String mergedFindings = Optional.ofNullable(node.summaryContent()).orElse("");
        DiscoveryOrchestratorNode updatedParent =
                discoveryParent.withContent(mergedFindings);
        AgentModels.CollectorDecision decision =
                overrideDecision != null
                        ? new AgentModels.CollectorDecision(
                        overrideDecision,
                        "human review",
                        null
                )
                        : node.discoveryCollectorResult() != null
                        ? node.discoveryCollectorResult().collectorDecision()
                        : null;
        applyCollectorDecision(
                decision,
                () -> {
                    graphRepository.save(
                            updatedParent.withStatus(GraphNode.NodeStatus.COMPLETED)
                    );
                    kickOffPlanningPhase(node, mergedFindings);
                },
                () -> {
                    graphRepository.save(updatedParent);
                    triggerOrchestratorRerun(
                            updatedParent,
                            "Discovery collector requested rerun"
                    );
                },
                () -> graphRepository.save(
                        updatedParent.withStatus(GraphNode.NodeStatus.COMPLETED)
                )
        );
    }

    private void handlePlanningCollectorCompleted(
            PlanningCollectorNode node,
            GraphNode parent
    ) {
        if (!(parent instanceof PlanningOrchestratorNode planningParent)) {
            return;
        }
        AgentModels.CollectorDecisionType overrideDecision =
                parseCollectorDecisionOverride(node);
        if (isCollectorReviewGateEnabled(node) && overrideDecision == null) {
            requestCollectorReview(node);
            return;
        }
        String tickets = Optional.ofNullable(node.planContent()).orElse("");
        PlanningOrchestratorNode updatedParent =
                planningParent.withPlanContent(tickets);
        AgentModels.CollectorDecision decision =
                overrideDecision != null
                        ? new AgentModels.CollectorDecision(
                        overrideDecision,
                        "human review",
                        null
                )
                        : node.planningCollectorResult() != null
                        ? node.planningCollectorResult().collectorDecision()
                        : null;
        applyCollectorDecision(
                decision,
                () -> {
                    graphRepository.save(
                            updatedParent.withStatus(GraphNode.NodeStatus.COMPLETED)
                    );
                    kickOffTicketPhase(node, tickets);
                },
                () -> {
                    graphRepository.save(updatedParent);
                    triggerOrchestratorRerun(
                            updatedParent,
                            "Planning collector requested rerun"
                    );
                },
                () -> graphRepository.save(
                        updatedParent.withStatus(GraphNode.NodeStatus.COMPLETED)
                )
        );
    }

    private void handleTicketCollectorCompleted(
            TicketCollectorNode node,
            GraphNode parent
    ) {
        if (!(parent instanceof TicketOrchestratorNode t)) {
            return;
        }
        AgentModels.CollectorDecisionType overrideDecision =
                parseCollectorDecisionOverride(node);
        if (isCollectorReviewGateEnabled(node) && overrideDecision == null) {
            requestCollectorReview(node);
            return;
        }
        String summary = Optional.ofNullable(node.ticketSummary()).orElse("");
        TicketOrchestratorNode updatedParent = t.withOutput(
                summary,
                t.streamingTokenCount()
        );
        graphRepository.save(updatedParent);
        AgentModels.CollectorDecision decision =
                overrideDecision != null
                        ? new AgentModels.CollectorDecision(
                        overrideDecision,
                        "human review",
                        null
                )
                        : node.ticketCollectorResult() != null
                        ? node.ticketCollectorResult().collectorDecision()
                        : null;
        applyCollectorDecision(
                decision,
                () -> finalizeWorkflow(t),
                () -> triggerOrchestratorRerun(
                        updatedParent,
                        "Ticket collector requested rerun"
                ),
                () -> log.info(
                        "Ticket collector requested stop for node: {}",
                        node.nodeId()
                )
        );
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

    private void ensureTicketCollector(TicketOrchestratorNode parent) {
        boolean exists = computationGraphOrchestrator
                .getChildNodes(parent.nodeId())
                .stream()
                .anyMatch(TicketCollectorNode.class::isInstance);
        if (exists) {
            return;
        }
        TicketCollectorNode collector = new TicketCollectorNode(
                newNodeId(),
                "Ticket Collector",
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
        String childWorktreeId = mergeNode.metadata().getOrDefault(
                "child_worktree_id",
                ""
        );
        String targetWorktreeId = mergeNode.metadata().getOrDefault(
                "target_worktree_id",
                ""
        );
        String mergeContext = buildMergeContext(
                mergeNode,
                childWorktreeId,
                targetWorktreeId,
                null
        );
        return runAgent(
                AgentInterfaces.MERGER_AGENT_NAME,
                new AgentInterfaces.MergerAgentInput(
                        mergeContext,
                        "Merge summary unavailable.",
                        "None"
                ),
                AgentModels.MergerAgentResult.class,
                mergeNode.nodeId()
        ).output();
    }

    private String buildMergeContext(
            MergeNode node,
            String childWorktreeId,
            String targetWorktreeId,
            @Nullable MergeResult mergeResult
    ) {
        StringBuilder context = new StringBuilder();
        context.append("Merge scope: ")
                .append(node.metadata().getOrDefault("merge_scope", "unspecified"))
                .append("\n");
        context.append("Child worktree id: ").append(childWorktreeId).append("\n");
        context.append("Target worktree id: ").append(targetWorktreeId).append("\n");

        worktreeRepository.findById(childWorktreeId)
                .ifPresent(worktree -> context.append("Child worktree path: ")
                        .append(worktree.worktreePath())
                        .append("\n"));
        worktreeRepository.findById(targetWorktreeId)
                .ifPresent(worktree -> context.append("Target worktree path: ")
                        .append(worktree.worktreePath())
                        .append("\n"));

        if (mergeResult != null) {
            context.append("Merge status: ")
                    .append(mergeResult.successful() ? "SUCCESS" : "FAILED")
                    .append("\n");
            context.append("Merge commit: ")
                    .append(Optional.ofNullable(mergeResult.mergeCommitHash()).orElse(""))
                    .append("\n");
            context.append("Merge message: ")
                    .append(Optional.ofNullable(mergeResult.mergeMessage()).orElse(""))
                    .append("\n");
            if (!mergeResult.submoduleUpdates().isEmpty()) {
                context.append("Submodule updates:\n");
                for (MergeResult.SubmodulePointerUpdate update : mergeResult.submoduleUpdates()) {
                    context.append("- ")
                            .append(update.submoduleName())
                            .append(": ")
                            .append(Optional.ofNullable(update.oldCommitHash()).orElse(""))
                            .append(" -> ")
                            .append(Optional.ofNullable(update.newCommitHash()).orElse(""))
                            .append(" (needs resolution=")
                            .append(update.requiresResolution())
                            .append(")\n");
                }
            }
        } else {
            context.append("Merge status: NOT_AVAILABLE (merge service returned null)\n");
        }

        return context.toString().trim();
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

    private <T extends GraphNode> Optional<T> findParentOrchestrator(GraphNode node, Class<T> c) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt =
                    computationGraphOrchestrator.getNode(parentId);
            if (parentOpt.isPresent()) {
                GraphNode parent = parentOpt.get();
                if (parent.getClass().equals(c)) {
                    return Optional.of((T) parent);
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
        Optional<TicketOrchestratorNode> orchestratorOpt =
                findAncestorTicketOrchestrator(reviewNode);
        if (orchestratorOpt.isPresent()) {
            return orchestratorOpt.get().mainWorktreeId();
        }
        if (reviewed instanceof HasWorktree editor) {
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

    private Optional<TicketOrchestratorNode> findAncestorTicketOrchestrator(GraphNode node) {
        String parentId = node.parentNodeId();
        while (parentId != null) {
            Optional<GraphNode> parentOpt =
                    computationGraphOrchestrator.getNode(parentId);
            if (parentOpt.isEmpty()) {
                break;
            }
            GraphNode parent = parentOpt.get();
            if (parent instanceof TicketOrchestratorNode editor) {
                return Optional.of(editor);
            }
            parentId = parent.parentNodeId();
        }
        return Optional.empty();
    }

    private TicketOrchestratorNode persistTicketQueue(
            TicketOrchestratorNode node,
            List<String> tickets
    ) {
        Map<String, String> metadata = new ConcurrentHashMap<>(node.metadata());
        metadata.put(META_TICKET_QUEUE, String.join("\n", tickets));
        metadata.put(META_TICKET_POINTER, "0");
        return node.toBuilder()
                .metadata(metadata)
                .lastUpdatedAt(Instant.now())
                .build();
    }

    private List<String> loadTicketQueue(TicketOrchestratorNode orchestratorNode) {
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

    private int currentTicketPointer(TicketOrchestratorNode orchestratorNode) {
        try {
            return Integer.parseInt(orchestratorNode.metadata().getOrDefault(META_TICKET_POINTER, "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void updateTicketPointer(TicketOrchestratorNode orchestratorNode, int pointer) {
        orchestratorNode
                .metadata()
                .put(META_TICKET_POINTER, Integer.toString(pointer));
        graphRepository.save(
                orchestratorNode.toBuilder()
                        .lastUpdatedAt(Instant.now())
                        .build()
        );
    }

    private void kickOffFinalReview(
            TicketOrchestratorNode orchestratorNode,
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

    private void finalizeWorkflow(TicketOrchestratorNode orchestratorNode) {
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
