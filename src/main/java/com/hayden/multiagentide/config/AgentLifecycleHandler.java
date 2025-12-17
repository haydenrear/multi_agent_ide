package com.hayden.multiagentide.config;

import com.hayden.multiagentide.model.MainWorktreeContext;
import com.hayden.multiagentide.model.Spec;
import com.hayden.multiagentide.model.SubmoduleNode;
import com.hayden.multiagentide.model.SubmoduleWorktreeContext;
import com.hayden.multiagentide.model.mixins.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.SpecRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.SpecService;
import com.hayden.multiagentide.service.WorktreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final SpecRepository specRepository;
    private final WorktreeService worktreeService;
    private final SpecService specService;

    public void beforeOrchestrator(String repositoryUrl, String baseBranch,
                                   String goal, String title) {
        initializeOrchestrator(repositoryUrl, baseBranch, goal, title);
    }

    public void afterOrchestrator(String s) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Initialize an orchestrator node with a goal.
     * Creates main worktree, submodule worktrees, and base spec.
     */
    public void initializeOrchestrator(String repositoryUrl, String baseBranch,
                                       String goal, String title) {
        String nodeId = UUID.randomUUID().toString();

        // Create main worktree
        MainWorktreeContext mainWorktree = worktreeService.createMainWorktree(
                repositoryUrl, baseBranch, nodeId);

        // Create orchestrator node
        OrchestratorNode orchestrator = new OrchestratorNode(
                nodeId,
                title,
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
                null,
                new ArrayList<>()
        );

        // Create spec
        Spec baseSpec = specService.createSpec(
                mainWorktree.worktreeId(),
                mainWorktree.worktreePath().resolve("SPEC.md"),
                goal
        );

        // Update orchestrator with spec ID
        orchestrator = new OrchestratorNode(
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
                orchestrator.submoduleWorktreeIds(),
                baseSpec.specId(),
                orchestrator.orchestratorOutput(),
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
                        nodeId
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
                    orchestrator.specFileId(),
                    orchestrator.orchestratorOutput()
            );

            orchestrator.submodules().add(submodule);
        }

        // Emit events
        this.orchestrator.emitNodeAddedEvent(orchestrator.nodeId(), orchestrator.title(),
                orchestrator.nodeType(), orchestrator.parentNodeId());
        this.orchestrator.emitWorktreeCreatedEvent(mainWorktree.worktreeId(), nodeId,
                mainWorktree.worktreePath().toString(), "main", null);

        // Save to repositories
        graphRepository.save(orchestrator);
        worktreeRepository.save(mainWorktree);
        specRepository.save(baseSpec);
    }

    /**
     * Handle before-agent-invocation for Planning Agent.
     * Registers a planning node in the computation graph.
     */
    /**
     * Handle before-agent-invocation for Discovery Orchestrator.
     * Registers a discovery orchestrator node in the computation graph.
     */
    public void beforeDiscoveryOrchestratorInvocation(String goal, String parentNodeId, String nodeId) {
        DiscoveryOrchestratorNode discoveryOrchestratorNode = new DiscoveryOrchestratorNode(
                nodeId,
                "Discover & Divide",
                goal,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                "",
                0,
                0,
                null
        );

        orchestrator.addChildNode(parentNodeId, discoveryOrchestratorNode);
        log.info("Discovery orchestrator node {} registered for goal: {}", nodeId, goal);
    }

    /**
     * Handle after-agent-invocation for Discovery Orchestrator.
     * Updates discovery orchestrator node and may kick off multiple discovery agents.
     */
    public void afterDiscoveryOrchestratorInvocation(String divisionStrategy, String nodeId) {
        if (nodeId == null) {
            log.warn("No discovery orchestrator node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof DiscoveryOrchestratorNode) {
            DiscoveryOrchestratorNode node = (DiscoveryOrchestratorNode) nodeOpt.get();
            DiscoveryOrchestratorNode updated = new DiscoveryOrchestratorNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    node.status(),
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    "",
                    node.totalTasksCompleted(),
                    node.totalTasksFailed(),
                    node.specFileId()
            );
            // Division strategy parsing and agent kickoff would happen here
            // For now, just update the node
            orchestrator.emitNodeAddedEvent(updated.nodeId(), updated.title(),
                    updated.nodeType(), updated.parentNodeId());
            log.info("Discovery orchestrator node {} updated with division strategy", nodeId);
        }
    }

    /**
     * Handle before-agent-invocation for Discovery Agent.
     * Registers a discovery node in the computation graph.
     */
    public void beforeDiscoveryAgentInvocation(String goal, String subdomainFocus, String nodeId) {
        DiscoveryNode discoveryNode = new DiscoveryNode(
                nodeId,
                "Discover: " + (subdomainFocus != null ? subdomainFocus : "codebase"),
                goal,
                GraphNode.NodeStatus.RUNNING,
                null,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                "",
                0,
                0,
                null
        );

        graphRepository.save(discoveryNode);
        log.info("Discovery node {} registered for subdomain focus: {}", nodeId, subdomainFocus);
    }

    /**
     * Handle after-agent-invocation for Discovery Agent.
     * Updates discovery node with findings and marks as completed.
     */
    public void afterDiscoveryAgentInvocation(String discoveryFindings, String metadata, String nodeId) {
        if (nodeId == null) {
            log.warn("No discovery node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof DiscoveryNode node) {
            DiscoveryNode updated = new DiscoveryNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    discoveryFindings,
                    node.totalTasksCompleted(),
                    node.totalTasksFailed(),
                    node.specFileId()
            );

            graphRepository.save(updated);
            
            // Emit status changed event for workflow orchestration
            orchestrator.emitStatusChangeEvent(
                    updated.nodeId(),
                    GraphNode.NodeStatus.RUNNING,
                    GraphNode.NodeStatus.COMPLETED,
                    "Discovery findings acquired"
            );
            
            log.info("Discovery node {} updated with findings", nodeId);
        }
    }

    /**
     * Handle before-agent-invocation for Discovery Merger.
     * Registers a discovery merger node in the computation graph.
     */
    public void beforeDiscoveryMergerInvocation(String allDiscoveryFindings, String parentNodeId, String nodeId) {
        SkillArtifactMergeNode mergerNode = new SkillArtifactMergeNode(
                nodeId,
                "Merge Discovery",
                "Consolidate discovery findings",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                "",
                0,
                0,
                null
        );

        orchestrator.addChildNode(parentNodeId, mergerNode);
        log.info("Discovery merger node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Discovery Merger.
     * Updates discovery merger node with merged findings and marks as completed.
     */
    public void afterDiscoveryMergerInvocation(String mergedDiscoveryFile, String nodeId) {
        if (nodeId == null) {
            log.warn("No discovery merger node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof SkillArtifactMergeNode node) {
            SkillArtifactMergeNode updated = new SkillArtifactMergeNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    mergedDiscoveryFile,
                    node.totalTasksCompleted(),
                    node.totalTasksFailed(),
                    node.specFileId()
            );
            graphRepository.save(updated);
            log.info("Discovery merger node {} updated with merged findings", nodeId);
        }
    }

    public void beforePlanningAgentInvocation(String goal, String parentNodeId, String nodeId) {

        PlanningNode planningNode = new PlanningNode(
                nodeId,
                "Plan & Decompose",
                goal,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                "",
                null,
                0,
                0
        );

        orchestrator.addChildNode(parentNodeId, planningNode);
        log.info("Planning node {} registered for goal: {}", nodeId, goal);
    }

    /**
     * Handle after-agent-invocation for Planning Agent.
     * Updates planning node with results and marks as completed.
     */
    public void afterPlanningAgentInvocation(String planContent, String nodeId) {
        if (nodeId == null) {
            log.warn("No planning node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof PlanningNode node) {
            PlanningNode updated = new PlanningNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.generatedTicketIds(),
                    planContent,
                    node.specFileId(),
                    node.estimatedSubtasks(),
                    node.completedSubtasks()
            );
            // Save updated node to repository (would be done through orchestrator)
            log.info("Planning node {} updated with plan content", nodeId);
        }

    }

    /**
     * Handle before-agent-invocation for Editor Agent.
     * Registers an editor node in the computation graph.
     */
    /**
     * Handle before-agent-invocation for Planning Orchestrator.
     * Registers a planning orchestrator node in the computation graph.
     */
    public void beforePlanningOrchestratorInvocation(String goal, String parentNodeId, String nodeId) {
        PlanningOrchestratorNode planningOrchestratorNode = new PlanningOrchestratorNode(
                nodeId,
                "Plan & Divide",
                goal,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                "",
                null,
                0,
                0
        );

        orchestrator.addChildNode(parentNodeId, planningOrchestratorNode);
        log.info("Planning orchestrator node {} registered for goal: {}", nodeId, goal);
    }

    /**
     * Handle after-agent-invocation for Planning Orchestrator.
     * Updates planning orchestrator node and kicks off multiple planning agents.
     */
    public void afterPlanningOrchestratorInvocation(String divisionStrategy, String nodeId) {
        if (nodeId == null) {
            log.warn("No planning orchestrator node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof PlanningOrchestratorNode) {
            PlanningOrchestratorNode node = (PlanningOrchestratorNode) nodeOpt.get();
            PlanningOrchestratorNode updated = new PlanningOrchestratorNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    node.status(),
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.generatedTicketIds(),
                    node.planContent(),
                    node.specFileId(),
                    node.estimatedSubtasks(),
                    node.completedSubtasks()
            );
            orchestrator.emitNodeAddedEvent(updated.nodeId(), updated.title(),
                    updated.nodeType(), updated.parentNodeId());
            log.info("Planning orchestrator node {} updated with division strategy", nodeId);
        }
    }

    /**
     * Handle before-agent-invocation for Planning Merger.
     * Registers a planning merger node in the computation graph.
     */
    public void beforePlanningMergerInvocation(String allPlanningResults, String parentNodeId, String nodeId) {
        SkillArtifactMergeNode mergerNode = new SkillArtifactMergeNode(
                nodeId,
                "Merge Planning",
                "Consolidate planning results into tickets",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                "",
                0,
                0,
                null
        );

        orchestrator.addChildNode(parentNodeId, mergerNode);
        log.info("Planning merger node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Planning Merger.
     * Updates planning merger node with merged tickets and marks as completed.
     */
    public void afterPlanningMergerInvocation(String ticketsFile, String nodeId) {
        if (nodeId == null) {
            log.warn("No planning merger node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof SkillArtifactMergeNode) {
            SkillArtifactMergeNode node = (SkillArtifactMergeNode) nodeOpt.get();
            SkillArtifactMergeNode updated = new SkillArtifactMergeNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    ticketsFile,
                    node.totalTasksCompleted(),
                    node.totalTasksFailed(),
                    node.specFileId()
            );
            graphRepository.save(updated);
            log.info("Planning merger node {} updated with merged tickets", nodeId);
        }
    }

    public void beforeEditorAgentInvocation(String goal, String context, String parentNodeId, String nodeId) {

        EditorNode editorNode = new EditorNode(
                nodeId,
                "Code Generation",
                goal,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                null,
                new ArrayList<>(),
                null,
                0,
                1,
                "editor",
                "",
                false,
                0
        );

        orchestrator.addChildNode(parentNodeId, editorNode);
        log.info("Editor node {} registered for goal: {}", nodeId, goal);
    }

    /**
     * Handle after-agent-invocation for Editor Agent.
     * Updates editor node with generated code and marks as completed.
     */
    public void afterEditorAgentInvocation(String generatedCode, String nodeId) {
        if (nodeId == null) {
            log.warn("No editor node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof EditorNode node) {
            EditorNode updated = new EditorNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.mainWorktreeId(),
                    node.submoduleWorktreeIds(),
                    node.specFileId(),
                    1,
                    1,
                    node.agentType(),
                    generatedCode,
                    node.mergeRequired(),
                    0
            );
            this.graphRepository.save(updated);
            this.orchestrator.emitNodeAddedEvent(updated.nodeId(), updated.title(),
                    updated.nodeType(), updated.parentNodeId());
            log.info("Editor node {} updated with generated code", nodeId);
        }

    }

    /**
     * Handle before-agent-invocation for Merger Agent.
     * Registers a merger node in the computation graph.
     */
    public void beforeMergerAgentInvocation(String childGoal, String parentGoal, String parentNodeId, String nodeId) {

        MergeNode mergerNode = new MergeNode(
                nodeId,
                "Code Merge",
                "Merge and integrate changes",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                null,
                0,
                1,
                "merger"
        );

        orchestrator.addChildNode(parentNodeId, mergerNode);
        log.info("Merger node {} registered for merge", nodeId);
    }

    /**
     * Handle after-agent-invocation for Merger Agent.
     * Updates merger node with merge strategy and marks as completed.
     */
    public void afterMergerAgentInvocation(String mergeStrategy, String nodeId) {
        if (nodeId == null) {
            log.warn("No merger node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof EditorNode node) {
            EditorNode updated = new EditorNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.mainWorktreeId(),
                    node.submoduleWorktreeIds(),
                    node.specFileId(),
                    1,
                    1,
                    node.agentType(),
                    mergeStrategy,
                    node.mergeRequired(),
                    0
            );
            log.info("Merger node {} updated with merge strategy", nodeId);
        }
    }

    /**
     * Handle before-agent-invocation for Review Agent.
     * Registers a review node in the computation graph.
     */
    /**
     * Handle before-agent-invocation for Ticket Orchestrator.
     * Registers a ticket orchestrator node and creates worktrees for ticket implementation.
     */
    public void beforeTicketOrchestratorInvocation(String tickets, String parentNodeId, String nodeId) {
        EditorNode ticketOrchestratorNode = new EditorNode(
                nodeId,
                "Execute Tickets",
                "Orchestrate ticket-based implementation",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                null,
                new ArrayList<>(),
                null,
                0,
                0,
                "ticket_orchestrator",
                "",
                false,
                0
        );

        orchestrator.addChildNode(parentNodeId, ticketOrchestratorNode);
        log.info("Ticket orchestrator node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Ticket Orchestrator.
     * Updates ticket orchestrator node and may kick off first TicketAgent.
     */
    public void afterTicketOrchestratorInvocation(String orchestrationPlan, String nodeId) {
        if (nodeId == null) {
            log.warn("No ticket orchestrator node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof EditorNode) {
            EditorNode node = (EditorNode) nodeOpt.get();
            EditorNode updated = new EditorNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    node.status(),
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.mainWorktreeId(),
                    node.submoduleWorktreeIds(),
                    node.specFileId(),
                    node.completedSubtasks(),
                    node.totalSubtasks(),
                    node.agentType(),
                    orchestrationPlan,
                    node.mergeRequired(),
                    0
            );
            orchestrator.emitNodeAddedEvent(updated.nodeId(), updated.title(),
                    updated.nodeType(), updated.parentNodeId());
            log.info("Ticket orchestrator node {} updated with orchestration plan", nodeId);
        }
    }

    /**
     * Handle before-agent-invocation for Ticket Agent.
     * Registers a ticket node in the computation graph and creates feature branch.
     */
    public void beforeTicketAgentInvocation(String ticketDetails, String parentNodeId, String nodeId) {
        EditorNode ticketNode = new EditorNode(
                nodeId,
                "Implement Ticket",
                ticketDetails,
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                null,
                new ArrayList<>(),
                null,
                0,
                0,
                "ticket_agent",
                "",
                false,
                0
        );

        orchestrator.addChildNode(parentNodeId, ticketNode);
        log.info("Ticket node {} registered for implementation", nodeId);
    }

    /**
     * Handle after-agent-invocation for Ticket Agent.
     * Updates ticket node with implementation summary and commits changes.
     */
    public void afterTicketAgentInvocation(String implementationSummary, String nodeId) {
        if (nodeId == null) {
            log.warn("No ticket node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof EditorNode) {
            EditorNode node = (EditorNode) nodeOpt.get();
            EditorNode updated = new EditorNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.mainWorktreeId(),
                    node.submoduleWorktreeIds(),
                    node.specFileId(),
                    node.completedSubtasks(),
                    node.totalSubtasks(),
                    node.agentType(),
                    implementationSummary,
                    true,
                    0
            );
            graphRepository.save(updated);
            orchestrator.emitNodeAddedEvent(updated.nodeId(), updated.title(),
                    updated.nodeType(), updated.parentNodeId());
            log.info("Ticket node {} updated with implementation summary", nodeId);
        }
    }

    public void beforeReviewAgentInvocation(String content, String criteria, String parentNodeId, String nodeId) {
        ReviewNode reviewNode = new ReviewNode(
                nodeId,
                "Code Review",
                "Review and evaluate work",
                GraphNode.NodeStatus.RUNNING,
                parentNodeId,
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                nodeId,
                content,
                false,
                false,
                "",
                "agent",
                null,
                null
        );

        orchestrator.addChildNode(parentNodeId, reviewNode);
        log.info("Review node {} registered", nodeId);
    }

    /**
     * Handle after-agent-invocation for Review Agent.
     * Updates review node with evaluation and marks as completed.
     */
    public void afterReviewAgentInvocation(String evaluation, String nodeId) {
        if (nodeId == null) {
            log.warn("No review node ID found in context");
            return;
        }

        Optional<GraphNode> nodeOpt = orchestrator.getNode(nodeId);
        if (nodeOpt.isPresent() && nodeOpt.get() instanceof ReviewNode node) {
            boolean approved = evaluation.toLowerCase().contains("approved") ||
                    evaluation.toLowerCase().contains("pass");
//            TODO: implement this better
            boolean humanFeedbackRequested = false;
            ReviewNode updated = new ReviewNode(
                    node.nodeId(),
                    node.title(),
                    node.goal(),
                    GraphNode.NodeStatus.COMPLETED,
                    node.parentNodeId(),
                    node.childNodeIds(),
                    node.metadata(),
                    node.createdAt(),
                    Instant.now(),
                    node.reviewedNodeId(),
                    node.reviewContent(),
                    approved,
                    humanFeedbackRequested,
                    evaluation,
                    node.reviewerAgentType(),
                    Instant.now(),
                    node.specFileId()
            );

            graphRepository.save(updated);
            orchestrator.emitStatusChangeEvent(node.nodeId(), node.status(), updated.status(), "Review node changed to completed.");
            log.info("Review node {} updated with evaluation", nodeId);
        }

    }
}
