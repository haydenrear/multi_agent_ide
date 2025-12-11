package com.hayden.multiagentide.orchestration;

import com.hayden.multiagentide.agent.ExecutionContextImpl;
import com.hayden.multiagentide.agent.GraphAgentFactory;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.*;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.SpecRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.SpecService;
import com.hayden.multiagentide.service.WorktreeService;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Main orchestrator for the computation graph.
 * Manages node execution, event emission, and worktree/spec lifecycle.
 */
@Service
public class ComputationGraphOrchestrator {

    private final GraphRepository graphRepository;
    private final WorktreeRepository worktreeRepository;
    private final SpecRepository specRepository;
    private final EventBus eventBus;
    private final GraphAgentFactory agentFactory;
    private final WorktreeService worktreeService;
    private final SpecService specService;
    private final ExecutorService executorService;

    public ComputationGraphOrchestrator(
            GraphRepository graphRepository,
            WorktreeRepository worktreeRepository,
            SpecRepository specRepository,
            EventBus eventBus,
            GraphAgentFactory agentFactory,
            WorktreeService worktreeService,
            SpecService specService) {
        this.graphRepository = graphRepository;
        this.worktreeRepository = worktreeRepository;
        this.specRepository = specRepository;
        this.eventBus = eventBus;
        this.agentFactory = agentFactory;
        this.worktreeService = worktreeService;
        this.specService = specService;
        this.executorService = Executors.newFixedThreadPool(4);
    }

    /**
     * Initialize an orchestrator node with a goal.
     * Creates main worktree, submodule worktrees, and base spec.
     */
    public OrchestratorNode initializeOrchestrator(String repositoryUrl, String baseBranch,
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
                emitWorktreeCreatedEvent(subWorktree.worktreeId(), nodeId,
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
        emitNodeAddedEvent(orchestrator.nodeId(), orchestrator.title(),
                orchestrator.nodeType(), orchestrator.parentNodeId());
        emitWorktreeCreatedEvent(mainWorktree.worktreeId(), nodeId,
                mainWorktree.worktreePath().toString(), "main", null);

        // Save to repositories
        graphRepository.save(orchestrator);
        worktreeRepository.save(mainWorktree);
        specRepository.save(baseSpec);

        return orchestrator;
    }

    /**
     * Execute a node asynchronously.
     * Returns a future that completes with the updated node.
     */
    public CompletableFuture<GraphNode> executeNode(String nodeId) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<GraphNode> nodeOpt = graphRepository.findById(nodeId);
            if (nodeOpt.isEmpty()) {
                throw new RuntimeException("Node not found: " + nodeId);
            }

            GraphNode node = nodeOpt.get();
            Optional<GraphAgent> agentOpt = agentFactory.getAgentFor(node);

            if (agentOpt.isEmpty()) {
                throw new RuntimeException("No agent found for node: " + nodeId);
            }

            // Create execution context
            ExecutionContextImpl context = new ExecutionContextImpl(
                    worktreeRepository, specRepository, eventBus);

            // Emit running status
            emitStatusChangeEvent(nodeId, node.status(), GraphNode.NodeStatus.RUNNING, 
                    "Agent execution started");

            try {
                // Execute agent
                GraphNode updatedNode = agentOpt.get().execute(node, context);
                graphRepository.save(updatedNode);

                // Emit completed status
                emitStatusChangeEvent(nodeId, GraphNode.NodeStatus.RUNNING, 
                        updatedNode.status(), "Agent execution completed");

                return updatedNode;
            } catch (Exception e) {
                // Emit failed status
                emitStatusChangeEvent(nodeId, node.status(), GraphNode.NodeStatus.FAILED, 
                        "Error: " + e.getMessage());
                throw new RuntimeException("Node execution failed: " + e.getMessage(), e);
            }
        }, executorService);
    }

    /**
     * Get a node from the graph.
     */
    public Optional<GraphNode> getNode(String nodeId) {
        return graphRepository.findById(nodeId);
    }

    /**
     * Get all nodes in the graph.
     */
    public List<GraphNode> getAllNodes() {
        return graphRepository.findAll();
    }

    /**
     * Get child nodes of a parent.
     */
    public List<GraphNode> getChildNodes(String parentNodeId) {
        return graphRepository.findByParentId(parentNodeId);
    }

    /**
     * Add a child node to parent.
     */
    public void addChildNode(String parentNodeId, GraphNode childNode) {
        Optional<GraphNode> parentOpt = graphRepository.findById(parentNodeId);
        if (parentOpt.isEmpty()) {
            throw new RuntimeException("Parent node not found: " + parentNodeId);
        }

        GraphNode parent = parentOpt.get();
        List<String> childIds = new ArrayList<>(parent.childNodeIds());
        childIds.add(childNode.nodeId());

        // Update parent based on type
        GraphNode updatedParent = updateNodeChildren(parent, childIds);
        graphRepository.save(updatedParent);
        graphRepository.save(childNode);

        emitNodeAddedEvent(childNode.nodeId(), childNode.title(), childNode.nodeType(), parentNodeId);
    }

    /**
     * Get all worktrees.
     */
    public List<WorktreeContext> getAllWorktrees() {
        return worktreeRepository.findAll();
    }

    /**
     * Get worktrees for a node.
     */
    public List<WorktreeContext> getWorktreesForNode(String nodeId) {
        return worktreeRepository.findByNodeId(nodeId);
    }

    /**
     * Get all specs.
     */
    public List<Spec> getAllSpecs() {
        return specRepository.findAll();
    }

    /**
     * Detect goal completion.
     * Goal is complete when all leaf nodes are COMPLETED or PRUNED,
     * and all worktrees are merged or discarded.
     */
    public boolean isGoalComplete(String orchestratorNodeId) {
        Optional<GraphNode> orchestratorOpt = graphRepository.findById(orchestratorNodeId);
        if (orchestratorOpt.isEmpty()) {
            return false;
        }

        // Check all nodes in graph
        for (GraphNode node : graphRepository.findAll()) {
            if (node.status() == GraphNode.NodeStatus.RUNNING ||
                node.status() == GraphNode.NodeStatus.WAITING_REVIEW ||
                node.status() == GraphNode.NodeStatus.WAITING_INPUT ||
                node.status() == GraphNode.NodeStatus.PENDING) {
                return false;
            }
        }

        return true;
    }

    /**
     * Emit node added event.
     */
    private void emitNodeAddedEvent(String nodeId, String title, GraphNode.NodeType nodeType, String parentId) {
        Events.NodeAddedEvent event = new Events.NodeAddedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                title,
                nodeType,
                parentId
        );
        eventBus.publish(event);
    }

    /**
     * Emit status changed event.
     */
    private void emitStatusChangeEvent(String nodeId, GraphNode.NodeStatus oldStatus, 
                                      GraphNode.NodeStatus newStatus, String reason) {
        Events.NodeStatusChangedEvent event = new Events.NodeStatusChangedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                oldStatus,
                newStatus,
                reason
        );
        eventBus.publish(event);
    }

    /**
     * Emit worktree created event.
     */
    private void emitWorktreeCreatedEvent(String worktreeId, String nodeId, String path,
                                         String type, String submoduleName) {
        Events.WorktreeCreatedEvent event = new Events.WorktreeCreatedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                worktreeId,
                nodeId,
                path,
                type,
                submoduleName
        );
        eventBus.publish(event);
    }

    /**
     * Helper to update node children based on type.
     */
    private GraphNode updateNodeChildren(GraphNode parent, List<String> childIds) {
//        switch(parent) {
//            case AgentReviewNode agentReviewNode -> {
//            }
//            case HumanReviewNode humanReviewNode -> {
//            }
//            case OrchestratorNode orchestratorNode -> {
//            }
//            case PlanningNode planningNode -> {
//            }
//            case SummaryNode summaryNode -> {
//            }
//            case WorkNode workNode -> {
//            }
//        }
        if (parent instanceof OrchestratorNode p) {
            return new OrchestratorNode(
                    p.nodeId(), p.title(), p.goal(), p.status(), p.parentNodeId(),
                    childIds, p.metadata(), p.createdAt(), p.lastUpdatedAt(),
                    p.repositoryUrl(), p.baseBranch(), p.hasSubmodules(), p.submoduleNames(),
                    p.mainWorktreeId(), p.submoduleWorktreeIds(), p.specFileId(), p.orchestratorOutput(),
                    p.submodules()
            );
        }
        if (parent instanceof PlanningNode p ) {
            return new PlanningNode(
                    p.nodeId(), p.title(), p.goal(), p.status(), p.parentNodeId(),
                    childIds, p.metadata(), p.createdAt(), p.lastUpdatedAt(),
                    p.generatedTicketIds(), p.planContent(), p.specFileId(),
                    p.estimatedSubtasks(), p.completedSubtasks()
            );
        }
        if (parent instanceof WorkNode p ) {
            return new WorkNode(
                    p.nodeId(), p.title(), p.goal(), p.status(), p.parentNodeId(),
                    childIds, p.metadata(), p.createdAt(), p.lastUpdatedAt(),
                    p.mainWorktreeId(), p.submoduleWorktreeIds(), p.specFileId(),
                    p.completedSubtasks(), p.totalSubtasks(), p.agentType(),
                    p.workOutput(), p.mergeRequired(), p.streamingTokenCount()
            );
        }
        return parent;
    }

    /**
     * Shutdown orchestrator.
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
