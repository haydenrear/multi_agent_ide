package com.hayden.multiagentide.agent;

import com.embabel.agent.api.event.AgentProcessCreationEvent;
import com.embabel.agent.api.event.AgentProcessEvent;
import com.embabel.agent.api.event.AgentProcessCompletedEvent;
import com.embabel.agent.api.event.AgentProcessFinishedEvent;
import com.embabel.agent.api.event.AgenticEventListener;
import com.embabel.agent.core.*;
import com.hayden.multiagentide.model.worktree.MainWorktreeContext;
import com.hayden.multiagentide.model.nodes.SubmoduleNode;
import com.hayden.multiagentide.model.worktree.SubmoduleWorktreeContext;
import com.hayden.multiagentide.model.nodes.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

/**
 * Handles agent lifecycle events for the multi-agent system.
 * Manages node creation, updates, and removal in the computation graph
 * when agents are invoked (including when invoked by other agents).
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AgentLifecycleHandler {


    private final ComputationGraphOrchestrator orchestrator;
    private final GraphRepository graphRepository;
    private final WorktreeRepository worktreeRepository;
    private final WorktreeService worktreeService;
    private final AgentPlatform agentPlatform;

    public Agent resolveAgent(String agentName) {
        List<Agent> agents = agentPlatform.agents();
        return agents.stream()
                .filter(agent -> agent.getName().equals(agentName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Agent not found: " + agentName));
    }


    public <T> T runAgent(AgentInterfaces agentInterface, Object input, Class<T> outputClass, String nodeId) {
        Agent agent = resolveAgent(agentInterface.multiAgentAgentName());
        ProcessOptions processOptions = ProcessOptions.DEFAULT.withContextId(nodeId)
                .withListener(new AgenticEventListener() {
                    @Override
                    public void onProcessEvent(@NotNull AgentProcessEvent event) {
//                      process ID is the same as Node ID
                        var processId = event.getProcessId();
                        if (event instanceof AgentProcessCreationEvent c) {
                            switch(agentInterface) {
                                case AgentInterfaces.ContextAgent contextAgent -> {
                                }
                                case AgentInterfaces.ContextCollectorAgent contextCollectorAgent -> {
                                }
                                case AgentInterfaces.ContextOrchestratorAgent contextOrchestratorAgent -> {
                                }
                                case AgentInterfaces.DiscoveryAgent discoveryAgent -> {
                                    beforeDiscoveryAgentInvocation(processId);
                                }
                                case AgentInterfaces.DiscoveryCollector discoveryCollector -> {
                                    beforeDiscoveryCollectorInvocation(processId);
                                }
                                case AgentInterfaces.DiscoveryOrchestrator discoveryOrchestrator -> {
                                    beforeDiscoveryOrchestratorInvocation(processId);
                                }
                                case AgentInterfaces.MergerAgent mergerAgent -> {
                                    beforeMergerAgentInvocation(processId);
                                }
                                case AgentInterfaces.OrchestratorAgent orchestratorAgent -> {
                                    beforeOrchestrator(processId);
                                }
                                case AgentInterfaces.OrchestratorCollectorAgent orchestratorCollectorAgent -> {
                                    beforeOrchestratorCollector(processId);
                                }
                                case AgentInterfaces.PlanningAgent planningAgent -> {
                                    beforePlanningAgentInvocation(processId);
                                }
                                case AgentInterfaces.PlanningCollector planningCollector -> {
                                    beforePlanningCollectorInvocation(processId);
                                }
                                case AgentInterfaces.PlanningOrchestrator planningOrchestrator -> {
                                    beforePlanningOrchestratorInvocation(processId);
                                }
                                case AgentInterfaces.ReviewAgent reviewAgent -> {
                                    beforeReviewAgentInvocation(processId);
                                }
                                case AgentInterfaces.TicketAgent ticketAgent -> {
                                    beforeTicketAgentInvocation(processId);
                                }
                                case AgentInterfaces.TicketCollector ticketCollector -> {
                                    beforeTicketCollectorInvocation(processId);
                                }
                                case AgentInterfaces.TicketOrchestrator ticketOrchestrator -> {
                                    beforeTicketOrchestratorInvocation(processId);
                                }
                            }

                        }

                        if (event instanceof AgentProcessFinishedEvent c) {
                            Object result = null;
                            if (event instanceof AgentProcessCompletedEvent completedEvent) {
                                result = completedEvent.getResult();
                            }
//                            process ID is the same as Node ID
                            switch(agentInterface) {
                                case AgentInterfaces.ContextAgent contextAgent -> {
                                }
                                case AgentInterfaces.ContextCollectorAgent contextCollectorAgent -> {
                                }
                                case AgentInterfaces.ContextOrchestratorAgent contextOrchestratorAgent -> {
                                }
                                case AgentInterfaces.DiscoveryAgent discoveryAgent -> {
                                    afterDiscoveryAgentInvocation(
                                            castResult(result, AgentModels.DiscoveryAgentResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.DiscoveryCollector discoveryCollector -> {
                                    afterDiscoveryCollectorInvocation(
                                            castResult(result, AgentModels.DiscoveryCollectorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.DiscoveryOrchestrator discoveryOrchestrator -> {
                                    afterDiscoveryOrchestratorInvocation(
                                            castResult(result, AgentModels.DiscoveryOrchestratorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.MergerAgent mergerAgent -> {
                                    afterMergerAgentInvocation(
                                            castResult(result, AgentModels.MergerAgentResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.OrchestratorAgent orchestratorAgent -> {
                                    afterOrchestrator(
                                            castResult(result, AgentModels.OrchestratorAgentResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.OrchestratorCollectorAgent orchestratorCollectorAgent -> {
                                    afterOrchestratorCollector(
                                            castResult(result, AgentModels.OrchestratorCollectorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.PlanningAgent planningAgent -> {
                                    afterPlanningAgentInvocation(
                                            castResult(result, AgentModels.PlanningAgentResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.PlanningCollector planningCollector -> {
                                    afterPlanningCollectorInvocation(
                                            castResult(result, AgentModels.PlanningCollectorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.PlanningOrchestrator planningOrchestrator -> {
                                    afterPlanningOrchestratorInvocation(
                                            castResult(result, AgentModels.PlanningOrchestratorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.ReviewAgent reviewAgent -> {
                                    afterReviewAgentInvocation(
                                            castResult(result, AgentModels.ReviewAgentResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.TicketAgent ticketAgent -> {
                                    afterTicketAgentInvocation(
                                            castResult(result, AgentModels.TicketAgentResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.TicketCollector ticketCollector -> {
                                    afterTicketCollectorInvocation(
                                            castResult(result, AgentModels.TicketCollectorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                                case AgentInterfaces.TicketOrchestrator ticketOrchestrator -> {
                                    afterTicketOrchestratorInvocation(
                                            castResult(result, AgentModels.TicketOrchestratorResult.class, processId, agentInterface),
                                            processId
                                    );
                                }
                            }
                        }
                    }
                });


        AgentProcess process = agentPlatform.runAgentFrom(
                agent,
                processOptions,
                Map.of(IoBinding.DEFAULT_BINDING, input)
        );
        return process.run().resultOfType(outputClass);
    }


    public void beforeOrchestrator(String nodeId) {
        Optional<OrchestratorNode> nodeOpt =
                findNode(nodeId, OrchestratorNode.class, "orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        OrchestratorNode node = nodeOpt.get();
        markNodeRunning(node);
        log.info("Orchestrator node {} ready for goal: {}", nodeId, node.goal());
    }

    public void afterOrchestrator(AgentModels.OrchestratorAgentResult result, String nodeId) {
        Optional<OrchestratorNode> nodeOpt =
                findNode(nodeId, OrchestratorNode.class, "orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        OrchestratorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No orchestrator result available for node {}", nodeId);
            return;
        }

        OrchestratorNode updated = node.toBuilder()
                .orchestratorOutput(result.output())
                .orchestratorResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Orchestrator node {} updated with output", nodeId);
    }

    public void beforeOrchestratorCollector(String nodeId) {
        Optional<CollectorNode> nodeOpt =
                findNode(nodeId, CollectorNode.class, "orchestrator collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        CollectorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Orchestrator collector node {} registered", nodeId);
    }

    public void afterOrchestratorCollector(AgentModels.OrchestratorCollectorResult result, String nodeId) {
        Optional<CollectorNode> nodeOpt =
                findNode(nodeId, CollectorNode.class, "orchestrator collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        CollectorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No orchestrator collector result available for node {}", nodeId);
            return;
        }

        CollectorNode updated = node.toBuilder()
                .orchestratorOutput(result.consolidatedOutput())
                .collectorResult(result)
                .collectorDecision(result.collectorDecision())
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Orchestrator collector node {} updated with output", nodeId);
    }

    public void initializeOrchestrator(String repositoryUrl, String baseBranch,
                                       String goal, String title) {
        initializeOrchestrator(repositoryUrl, baseBranch, goal, title, null);
    }

    /**
     * Initialize an orchestrator node with a goal.
     * Creates main worktree, submodule worktrees, and base spec.
     */
    public void initializeOrchestrator(String repositoryUrl, String baseBranch,
                                       String goal, String title, String nodeId) {
        String resolvedNodeId = nodeId != null ? nodeId : UUID.randomUUID().toString();
        if (nodeExists(resolvedNodeId)) {
            log.info("Orchestrator node {} already exists; skipping initialization", resolvedNodeId);
            return;
        }

        // Create main worktree
        MainWorktreeContext mainWorktree = worktreeService.createMainWorktree(
                repositoryUrl, baseBranch, resolvedNodeId);

        // Create orchestrator node
        OrchestratorNode orchestrator = new OrchestratorNode(
                resolvedNodeId,
                Optional.ofNullable(title).orElse("Orchestrator"),
                goal,
                GraphNode.NodeStatus.READY,
                null,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                repositoryUrl,
                baseBranch,
                mainWorktree.hasSubmodules(),
                worktreeService.getSubmoduleNames(mainWorktree.worktreePath()),
                mainWorktree.worktreeId(),
                new ArrayList<>(),
                null,
                new ArrayList<>()
        );

        // Create submodule worktrees
        if (mainWorktree.hasSubmodules()) {
            List<String> submoduleWorktreeIds = new ArrayList<>();
            for (String submoduleName : orchestrator.submoduleNames()) {
                Path submodulePath = worktreeService.getSubmodulePath(
                        mainWorktree.worktreePath(), submoduleName);
                SubmoduleWorktreeContext subWorktree = worktreeService.createSubmoduleWorktree(
                        submoduleName, submodulePath.toString(),
                        mainWorktree.worktreeId(), mainWorktree.worktreePath(),
                        resolvedNodeId
                );
                submoduleWorktreeIds.add(subWorktree.worktreeId());
                worktreeRepository.save(subWorktree);

                // Emit worktree created event
                this.orchestrator.emitWorktreeCreatedEvent(subWorktree.worktreeId(), nodeId,
                        subWorktree.worktreePath().toString(), "submodule", submoduleName);
            }

            // Update orchestrator with submodule worktree IDs
            var submodule = new SubmoduleNode(
                orchestrator.nodeId(),
                orchestrator.title(),
                orchestrator.goal(),
                    orchestrator.status(),
                    orchestrator.parentNodeId(),
                    orchestrator.childNodeIds(),
                    orchestrator.metadata(),
                    orchestrator.createdAt(),
                    orchestrator.lastUpdatedAt(),
                    orchestrator.repositoryUrl(),
                    orchestrator.baseBranch(),
                    orchestrator.hasSubmodules(),
                    orchestrator.submoduleNames(),
                    orchestrator.mainWorktreeId(),
                    submoduleWorktreeIds,
                    orchestrator.orchestratorOutput()
            );

            orchestrator.submodules().add(submodule);
        }


        // Save to repositories
        graphRepository.save(orchestrator);
        worktreeRepository.save(mainWorktree);

        // Emit events
        this.orchestrator.emitNodeAddedEvent(orchestrator.nodeId(), orchestrator.title(),
                orchestrator.nodeType(), orchestrator.parentNodeId());
        this.orchestrator.emitWorktreeCreatedEvent(mainWorktree.worktreeId(), resolvedNodeId,
                mainWorktree.worktreePath().toString(), "main", null);
    }

    /**
     * Handle before-agent-invocation for Planning Agent.
     * Registers a planning node in the computation graph.
     */
    /**
     * Handle before-agent-invocation for Discovery Orchestrator.
     * Registers a discovery orchestrator node in the computation graph.
     */
    public void beforeDiscoveryOrchestratorInvocation(String nodeId) {
        Optional<DiscoveryOrchestratorNode> nodeOpt =
                findNode(nodeId, DiscoveryOrchestratorNode.class, "discovery orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        DiscoveryOrchestratorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Discovery orchestrator node {} registered for goal: {}", nodeId, node.goal());
    }

    /**
     * Handle after-agent-invocation for Discovery Orchestrator.
     * Updates discovery orchestrator node and may kick off multiple discovery agents.
     */
    public void afterDiscoveryOrchestratorInvocation(AgentModels.DiscoveryOrchestratorResult result, String nodeId) {
        Optional<DiscoveryOrchestratorNode> nodeOpt =
                findNode(nodeId, DiscoveryOrchestratorNode.class, "discovery orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        DiscoveryOrchestratorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No discovery orchestrator result available for node {}", nodeId);
            return;
        }

        DiscoveryOrchestratorNode updated = node.toBuilder()
                .summaryContent(result.output())
                .discoveryOrchestratorResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Discovery orchestrator node {} updated with division strategy", nodeId);
    }

    /**
     * Handle before-agent-invocation for Discovery Agent.
     * Registers a discovery node in the computation graph.
     */
    public void beforeDiscoveryAgentInvocation(String nodeId) {
        Optional<DiscoveryNode> nodeOpt =
                findNode(nodeId, DiscoveryNode.class, "discovery");
        if (nodeOpt.isEmpty()) {
            return;
        }
        DiscoveryNode node = nodeOpt.get();
        markNodeRunning(node);
        String subdomainFocus = node.metadata()
                .getOrDefault("subdomainFocus", node.title());
        log.info("Discovery node {} registered for subdomain focus: {}", nodeId, subdomainFocus);
    }

    /**
     * Handle after-agent-invocation for Discovery Agent.
     * Updates discovery node with findings and marks as completed.
     */
    public void afterDiscoveryAgentInvocation(AgentModels.DiscoveryAgentResult result, String nodeId) {
        Optional<DiscoveryNode> nodeOpt =
                findNode(nodeId, DiscoveryNode.class, "discovery");
        if (nodeOpt.isEmpty()) {
            return;
        }
        DiscoveryNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No discovery result available for node {}", nodeId);
            return;
        }

        DiscoveryNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .summaryContent(result.output())
                .discoveryResult(result)
                .lastUpdatedAt(Instant.now())
                .build();

        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );

        log.info("Discovery node {} updated with findings", nodeId);
    }

    /**
     * Handle before-agent-invocation for Discovery Merger.
     * Registers a discovery merger node in the computation graph.
     */
    public void beforeDiscoveryCollectorInvocation(String nodeId) {
        Optional<DiscoveryCollectorNode> nodeOpt =
                findNode(nodeId, DiscoveryCollectorNode.class, "discovery collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        DiscoveryCollectorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Discovery merger node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Discovery Merger.
     * Updates discovery merger node with merged findings and marks as completed.
     */
    public void afterDiscoveryCollectorInvocation(AgentModels.DiscoveryCollectorResult result, String nodeId) {
        Optional<DiscoveryCollectorNode> nodeOpt =
                findNode(nodeId, DiscoveryCollectorNode.class, "discovery collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        DiscoveryCollectorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No discovery collector result available for node {}", nodeId);
            return;
        }

        DiscoveryCollectorNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .summaryContent(result.consolidatedOutput())
                .discoveryCollectorResult(result)
                .collectorDecision(result.collectorDecision())
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "Discovery collector status updated"
        );
        log.info("Discovery merger node {} updated with merged findings", nodeId);
    }

    public void beforePlanningAgentInvocation(String nodeId) {
        Optional<PlanningNode> nodeOpt =
                findNode(nodeId, PlanningNode.class, "planning");
        if (nodeOpt.isEmpty()) {
            return;
        }
        PlanningNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Planning node {} registered for goal: {}", nodeId, node.goal());
    }

    /**
     * Handle after-agent-invocation for Planning Agent.
     * Updates planning node with results and marks as completed.
     */
    public void afterPlanningAgentInvocation(AgentModels.PlanningAgentResult result, String nodeId) {
        Optional<PlanningNode> nodeOpt =
                findNode(nodeId, PlanningNode.class, "planning");
        if (nodeOpt.isEmpty()) {
            return;
        }
        PlanningNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No planning result available for node {}", nodeId);
            return;
        }

        PlanningNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .planContent(result.output())
                .planningResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );

        log.info("Planning node {} updated with plan content", nodeId);

    }

    /**
     * Handle before-agent-invocation for Editor Agent.
     * Registers an editor node in the computation graph.
     */
    /**
     * Handle before-agent-invocation for Planning Orchestrator.
     * Registers a planning orchestrator node in the computation graph.
     */
    public void beforePlanningOrchestratorInvocation(String nodeId) {
        Optional<PlanningOrchestratorNode> nodeOpt =
                findNode(nodeId, PlanningOrchestratorNode.class, "planning orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        PlanningOrchestratorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Planning orchestrator node {} registered for goal: {}", nodeId, node.goal());
    }

    /**
     * Handle after-agent-invocation for Planning Orchestrator.
     * Updates planning orchestrator node and kicks off multiple planning agents.
     */
    public void afterPlanningOrchestratorInvocation(AgentModels.PlanningOrchestratorResult result, String nodeId) {
        Optional<PlanningOrchestratorNode> nodeOpt =
                findNode(nodeId, PlanningOrchestratorNode.class, "planning orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        PlanningOrchestratorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No planning orchestrator result available for node {}", nodeId);
            return;
        }

        PlanningOrchestratorNode updated = node.toBuilder()
                .planContent(result.output())
                .planningOrchestratorResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Planning orchestrator node {} updated with division strategy", nodeId);
    }

    /**
     * Handle before-agent-invocation for Planning Merger.
     * Registers a planning merger node in the computation graph.
     */
    public void beforePlanningCollectorInvocation(String nodeId) {
        Optional<PlanningCollectorNode> nodeOpt =
                findNode(nodeId, PlanningCollectorNode.class, "planning collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        PlanningCollectorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Planning merger node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Planning Merger.
     * Updates planning merger node with merged tickets and marks as completed.
     */
    public void afterPlanningCollectorInvocation(AgentModels.PlanningCollectorResult result, String nodeId) {
        Optional<PlanningCollectorNode> nodeOpt =
                findNode(nodeId, PlanningCollectorNode.class, "planning collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        PlanningCollectorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No planning collector result available for node {}", nodeId);
            return;
        }

        PlanningCollectorNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .generatedTicketIds(new ArrayList<>())
                .planContent(result.consolidatedOutput())
                .planningCollectorResult(result)
                .collectorDecision(result.collectorDecision())
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Planning merger node {} updated with merged tickets", nodeId);
    }

    public void beforeTicketCollectorInvocation(String nodeId) {
        Optional<TicketCollectorNode> nodeOpt =
                findNode(nodeId, TicketCollectorNode.class, "ticket collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        TicketCollectorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Ticket collector node {} registered", nodeId);
    }

    public void afterTicketCollectorInvocation(AgentModels.TicketCollectorResult result, String nodeId) {
        Optional<TicketCollectorNode> nodeOpt =
                findNode(nodeId, TicketCollectorNode.class, "ticket collector");
        if (nodeOpt.isEmpty()) {
            return;
        }
        TicketCollectorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No ticket collector result available for node {}", nodeId);
            return;
        }

        TicketCollectorNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .ticketSummary(result.consolidatedOutput())
                .ticketCollectorResult(result)
                .collectorDecision(result.collectorDecision())
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "Ticket collector status updated"
        );
        log.info("Ticket collector node {} updated with summary", nodeId);
    }

    /**
     * Handle before-agent-invocation for Merger Agent.
     * Registers a merger node in the computation graph.
     */
    public void beforeMergerAgentInvocation(String nodeId) {
        Optional<MergeNode> nodeOpt =
                findNode(nodeId, MergeNode.class, "merger");
        if (nodeOpt.isEmpty()) {
            return;
        }
        MergeNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Merger node {} registered for merge", nodeId);
    }

    /**
     * Handle after-agent-invocation for Merger Agent.
     * Updates merger node with merge strategy and marks as completed.
     */
    public void afterMergerAgentInvocation(AgentModels.MergerAgentResult result, String nodeId) {
        Optional<MergeNode> nodeOpt =
                findNode(nodeId, MergeNode.class, "merger");
        if (nodeOpt.isEmpty()) {
            return;
        }
        MergeNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No merger result available for node {}", nodeId);
            return;
        }

        MergeNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .summaryContent(result.output())
                .mergerResult(result)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Merger node {} updated with merge strategy", nodeId);
    }

    /**
     * Handle before-agent-invocation for Review Agent.
     * Registers a review node in the computation graph.
     */
    /**
     * Handle before-agent-invocation for Ticket Orchestrator.
     * Registers a ticket orchestrator node and creates worktrees for ticket implementation.
     */
    public void beforeTicketOrchestratorInvocation(String nodeId) {
        Optional<TicketOrchestratorNode> nodeOpt =
                findNode(nodeId, TicketOrchestratorNode.class, "ticket orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        TicketOrchestratorNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Ticket orchestrator node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Ticket Orchestrator.
     * Updates ticket orchestrator node and may kick off first TicketAgent.
     */
    public void afterTicketOrchestratorInvocation(AgentModels.TicketOrchestratorResult result, String nodeId) {
        Optional<TicketOrchestratorNode> nodeOpt =
                findNode(nodeId, TicketOrchestratorNode.class, "ticket orchestrator");
        if (nodeOpt.isEmpty()) {
            return;
        }
        TicketOrchestratorNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No ticket orchestrator result available for node {}", nodeId);
            return;
        }

        TicketOrchestratorNode updated = node.toBuilder()
                .workOutput(result.output())
                .ticketOrchestratorResult(result)
                .streamingTokenCount(0)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Ticket orchestrator node {} updated with orchestration plan", nodeId);
    }

    /**
     * Handle before-agent-invocation for Ticket Agent.
     * Registers a ticket node in the computation graph and creates feature branch.
     */
    public void beforeTicketAgentInvocation(String nodeId) {
        Optional<TicketNode> nodeOpt =
                findNode(nodeId, TicketNode.class, "ticket");
        if (nodeOpt.isEmpty()) {
            return;
        }
        TicketNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Ticket node {} registered for implementation", nodeId);
    }

    /**
     * Handle after-agent-invocation for Ticket Agent.
     * Updates ticket node with implementation summary and commits changes.
     */
    public void afterTicketAgentInvocation(AgentModels.TicketAgentResult result, String nodeId) {
        Optional<TicketNode> nodeOpt =
                findNode(nodeId, TicketNode.class, "ticket");
        if (nodeOpt.isEmpty()) {
            return;
        }
        TicketNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No ticket agent result available for node {}", nodeId);
            return;
        }

        TicketNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .workOutput(result.output())
                .ticketAgentResult(result)
                .mergeRequired(true)
                .streamingTokenCount(0)
                .lastUpdatedAt(Instant.now())
                .build();
        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "Completed editor agent");
        log.info("Ticket node {} updated with implementation summary", nodeId);
    }

    public void beforeReviewAgentInvocation(String nodeId) {
        Optional<ReviewNode> nodeOpt =
                findNode(nodeId, ReviewNode.class, "review");
        if (nodeOpt.isEmpty()) {
            return;
        }
        ReviewNode node = nodeOpt.get();
        ensureNodeAttached(node);
        markNodeRunning(node);
        log.info("Review node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Review Agent.
     * Updates review node with evaluation and marks as completed.
     */
    public void afterReviewAgentInvocation(AgentModels.ReviewAgentResult result, String nodeId) {
        Optional<ReviewNode> nodeOpt =
                findNode(nodeId, ReviewNode.class, "review");
        if (nodeOpt.isEmpty()) {
            return;
        }
        ReviewNode node = nodeOpt.get();
        if (result == null) {
            log.warn("No review result available for node {}", nodeId);
            return;
        }

//            TODO: implement this better
        boolean approved = result.output().toLowerCase().contains("approved") ||
                result.output().toLowerCase().contains("pass");
        boolean humanFeedbackRequested = false;
        ReviewNode updated = node.toBuilder()
                .status(GraphNode.NodeStatus.COMPLETED)
                .approved(approved)
                .humanFeedbackRequested(humanFeedbackRequested)
                .agentFeedback(result.output())
                .reviewResult(result)
                .reviewCompletedAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .build();

        graphRepository.save(updated);
        orchestrator.emitStatusChangeEvent(
                updated.nodeId(),
                node.status(),
                updated.status(),
                "%s status updated".formatted(node.getClass().getSimpleName())
        );
        log.info("Review node {} updated with evaluation", nodeId);

    }

    private <T extends GraphNode> void markNodeRunning(T node) {
        if (node.status() == GraphNode.NodeStatus.RUNNING) {
            return;
        }
        GraphNode updated = node.withStatus(GraphNode.NodeStatus.RUNNING);
        graphRepository.save(updated);
    }

    private void ensureNodeAttached(GraphNode node) {
        String parentNodeId = node.parentNodeId();
        if (parentNodeId == null || parentNodeId.isBlank()) {
            return;
        }
        Optional<GraphNode> parentOpt = graphRepository.findById(parentNodeId);
        if (parentOpt.isEmpty()) {
            log.warn("Parent node {} not found for node {}", parentNodeId, node.nodeId());
            return;
        }
        GraphNode parent = parentOpt.get();
        if (parent.childNodeIds().contains(node.nodeId())) {
            return;
        }
        orchestrator.addChildNodeAndEmitEvent(parentNodeId, node);
    }

    private <T extends GraphNode> Optional<T> findNode(
            String nodeId,
            Class<T> expectedType,
            String label
    ) {
        if (nodeId == null) {
            log.warn("No {} node ID found in context", label);
            return Optional.empty();
        }
        Optional<GraphNode> nodeOpt = graphRepository.findById(nodeId);
        if (nodeOpt.isEmpty()) {
            log.warn("No {} node found for id {}", label, nodeId);
            orchestrator.emitErrorEvent(
                    nodeId,
                    "missing",
                    expectedType.getSimpleName(),
                    "Node not found in graph repository"
            );
            return Optional.empty();
        }
        GraphNode node = nodeOpt.get();
        if (!expectedType.isInstance(node)) {
            log.warn("Node {} is not a {} node", nodeId, label);
            return Optional.empty();
        }
        return Optional.of(expectedType.cast(node));
    }

    private boolean nodeExists(String nodeId) {
        return nodeId != null && orchestrator.getNode(nodeId).isPresent();
    }

    private <T> T castResult(Object result, Class<T> expectedType, String processId, AgentInterfaces agentInterface) {
        if (result == null) {
            log.warn("No result available for process {} ({})", processId, agentInterface.multiAgentAgentName());
            return null;
        }
        if (!expectedType.isInstance(result)) {
            log.warn(
                    "Unexpected result type for process {} ({}): expected {}, got {}",
                    processId,
                    agentInterface.multiAgentAgentName(),
                    expectedType.getSimpleName(),
                    result.getClass().getSimpleName()
            );
            return null;
        }
        return expectedType.cast(result);
    }
}
