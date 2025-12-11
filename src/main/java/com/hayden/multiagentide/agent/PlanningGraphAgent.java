package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.mixins.GraphNode;
import com.hayden.multiagentide.model.MainWorktreeContext;
import com.hayden.multiagentide.model.mixins.OrchestratorNode;
import com.hayden.multiagentide.model.mixins.PlanningNode;
import com.hayden.multiagentide.model.Spec;
import com.hayden.multiagentide.model.SubmoduleWorktreeContext;
import com.hayden.multiagentide.model.mixins.WorkNode;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.SpecService;
import com.hayden.multiagentide.service.WorktreeService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Planning agent that breaks goals into work tickets using LangChain4j Agentic.
 */
@Component
public class PlanningGraphAgent extends BaseGraphAgent {

    private final GraphRepository graphRepository;
    private final WorktreeRepository worktreeRepository;
    private final WorktreeService worktreeService;
    private final SpecService specService;
    private final AgentInterfaces.PlanningAgent planningAgent;

    public PlanningGraphAgent(EventBus eventBus,
                              GraphRepository graphRepository,
                              WorktreeRepository worktreeRepository,
                              WorktreeService worktreeService,
                              SpecService specService,
                              AgentInterfaces.PlanningAgent planningAgent) {
        super(eventBus);
        this.graphRepository = graphRepository;
        this.worktreeRepository = worktreeRepository;
        this.worktreeService = worktreeService;
        this.specService = specService;
        this.planningAgent = planningAgent;
    }

    @Override
    public String agentType() {
        return "planning";
    }

    @Override
    public boolean supports(GraphNode node) {
        return node instanceof OrchestratorNode;
    }

    @Override
    public GraphNode execute(GraphNode node, ExecutionContext context) throws Exception {
        OrchestratorNode orchestrator = (OrchestratorNode) node;

        String decomposedPlan = planningAgent.decomposePlanAndCreateWorkItems(orchestrator.goal());

        PlanningNode planningNode = createPlanningNode(orchestrator, decomposedPlan);
        graphRepository.save(planningNode);
        emitNodeAddedEvent(planningNode.nodeId(), planningNode.title(),
                planningNode.nodeType(), orchestrator.nodeId(), context);

        List<String> workNodeIds = new ArrayList<>();
        String[] workTitles = {"Architecture & Setup", "Implementation", "Testing & Validation"};

        for (String workTitle : workTitles) {
            WorkNode workNode = createWorkNode(orchestrator, planningNode, workTitle, orchestrator.goal());
            graphRepository.save(workNode);
            workNodeIds.add(workNode.nodeId());
            emitNodeAddedEvent(workNode.nodeId(), workNode.title(),
                    workNode.nodeType(), planningNode.nodeId(), context);
        }

        planningNode = new PlanningNode(
                planningNode.nodeId(),
                planningNode.title(),
                planningNode.goal(),
                GraphNode.NodeStatus.COMPLETED,
                planningNode.parentNodeId(),
                workNodeIds,
                planningNode.metadata(),
                planningNode.createdAt(),
                Instant.now(),
                workNodeIds,
                planningNode.planContent(),
                planningNode.specFileId(),
                workNodeIds.size(),
                workNodeIds.size()
        );

        graphRepository.save(planningNode);
        emitStatusChangeEvent(planningNode.nodeId(), GraphNode.NodeStatus.RUNNING,
                GraphNode.NodeStatus.COMPLETED, "Planning completed", context);

        OrchestratorNode updated = new OrchestratorNode(
                orchestrator.nodeId(),
                orchestrator.title(),
                orchestrator.goal(),
                orchestrator.status(),
                orchestrator.parentNodeId(),
                List.of(planningNode.nodeId()),
                orchestrator.metadata(),
                orchestrator.createdAt(),
                Instant.now(),
                orchestrator.repositoryUrl(),
                orchestrator.baseBranch(),
                orchestrator.hasSubmodules(),
                orchestrator.submoduleNames(),
                orchestrator.mainWorktreeId(),
                orchestrator.submoduleWorktreeIds(),
                orchestrator.specFileId(),
                orchestrator.orchestratorOutput()
        );

        graphRepository.save(updated);
        return updated;
    }

    private PlanningNode createPlanningNode(OrchestratorNode orchestrator, String planContent) {
        String planningNodeId = UUID.randomUUID().toString();

        PlanningNode node = new PlanningNode(
                planningNodeId,
                "Plan & Decompose",
                "Break down goal into work tickets",
                GraphNode.NodeStatus.RUNNING,
                orchestrator.nodeId(),
                new ArrayList<>(),
                new HashMap<>(),
                Instant.now(),
                Instant.now(),
                new ArrayList<>(),
                planContent,
                null,
                3,
                0
        );

        MainWorktreeContext mainWt = worktreeRepository.findById(orchestrator.mainWorktreeId())
                .map(wt -> (MainWorktreeContext) wt)
                .orElseThrow(() -> new RuntimeException("Main worktree not found"));

        Spec planningSpec = specService.createSpec(
                mainWt.worktreeId(),
                mainWt.worktreePath().resolve("planning-SPEC.md"),
                "Decompose goal into work items"
        );

        return new PlanningNode(
                planningNodeId,
                node.title(),
                node.goal(),
                node.status(),
                node.parentNodeId(),
                node.childNodeIds(),
                node.metadata(),
                node.createdAt(),
                node.lastUpdatedAt(),
                node.generatedTicketIds(),
                node.planContent(),
                planningSpec.specId(),
                node.estimatedSubtasks(),
                node.completedSubtasks()
        );
    }

    private WorkNode createWorkNode(OrchestratorNode orchestrator, PlanningNode planning,
                                    String title, String goal) {
        String workNodeId = UUID.randomUUID().toString();

        MainWorktreeContext mainWt = worktreeRepository.findById(orchestrator.mainWorktreeId())
                .map(wt -> (MainWorktreeContext) wt)
                .orElseThrow(() -> new RuntimeException("Main worktree not found"));

        MainWorktreeContext childWorktree = worktreeService.branchWorktree(
                mainWt.worktreeId(),
                "work-" + UUID.randomUUID().toString().substring(0, 8),
                workNodeId
        );

        List<String> submoduleWorktreeIds = new ArrayList<>();
        for (String submoduleName : orchestrator.submoduleNames()) {
            Optional<SubmoduleWorktreeContext> parentSubmodule = worktreeRepository.findByNodeId(orchestrator.nodeId())
                    .stream()
                    .filter(wt -> wt instanceof SubmoduleWorktreeContext)
                    .map(wt -> (SubmoduleWorktreeContext) wt)
                    .filter(wt -> wt.submoduleName().equals(submoduleName))
                    .findFirst();

            if (parentSubmodule.isPresent()) {
                SubmoduleWorktreeContext childSubmodule = worktreeService.branchSubmoduleWorktree(
                        parentSubmodule.get().worktreeId(),
                        "work-" + UUID.randomUUID().toString().substring(0, 8),
                        workNodeId
                );
                submoduleWorktreeIds.add(childSubmodule.worktreeId());
            }
        }

        Spec workSpec = specService.createSpec(
                childWorktree.worktreeId(),
                childWorktree.worktreePath().resolve("SPEC.md"),
                goal
        );

        return new WorkNode(
                workNodeId,
                title,
                goal,
                GraphNode.NodeStatus.READY,
                planning.nodeId(),
                new ArrayList<>(),
                Map.of("orchestratorGoal", orchestrator.goal()),
                Instant.now(),
                Instant.now(),
                childWorktree.worktreeId(),
                submoduleWorktreeIds,
                workSpec.specId(),
                0,
                1,
                "editor",
                null,
                false,
                0
        );
    }
}
