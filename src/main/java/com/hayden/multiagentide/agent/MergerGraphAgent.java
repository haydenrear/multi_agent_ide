package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.GraphAgent;
import com.hayden.multiagentide.model.GraphNode;
import com.hayden.multiagentide.model.MergeResult;
import com.hayden.multiagentide.model.SubmoduleWorktreeContext;
import com.hayden.multiagentide.model.WorkNode;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.service.SpecService;
import com.hayden.multiagentide.service.WorktreeService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;

/**
 * Merger agent that merges child worktrees into parent worktrees using LangChain4j Agentic guidance.
 */
@Component
public class MergerGraphAgent extends BaseGraphAgent {

    private final GraphRepository graphRepository;
    private final WorktreeService worktreeService;
    private final SpecService specService;
    private final AgentInterfaces.MergerAgent mergerAgent;

    public MergerGraphAgent(EventBus eventBus,
                            GraphRepository graphRepository,
                            WorktreeService worktreeService,
                            SpecService specService,
                            AgentInterfaces.MergerAgent mergerAgent) {
        super(eventBus);
        this.graphRepository = graphRepository;
        this.worktreeService = worktreeService;
        this.specService = specService;
        this.mergerAgent = mergerAgent;
    }

    @Override
    public String agentType() {
        return "merger";
    }

    @Override
    public boolean supports(GraphNode node) {
        return node instanceof WorkNode && node.status() == GraphNode.NodeStatus.COMPLETED;
    }

    @Override
    public GraphNode execute(GraphNode node, GraphAgent.ExecutionContext context) throws Exception {
        WorkNode workNode = (WorkNode) node;

        emitStatusChangeEvent(workNode.nodeId(), GraphNode.NodeStatus.COMPLETED,
                GraphNode.NodeStatus.RUNNING, "Merge initiated", context);

        String parentNodeId = workNode.parentNodeId();
        if (parentNodeId == null) {
            return workNode;
        }

        var parentOpt = graphRepository.findById(parentNodeId);
        if (parentOpt.isEmpty() || !(parentOpt.get() instanceof WorkNode)) {
            return workNode;
        }

        WorkNode parentNode = (WorkNode) parentOpt.get();

        String mergeStrategy = mergerAgent.determineMergeStrategy(
                workNode.goal(),
                parentNode.goal()
        );

        MergeResult mainMergeResult = worktreeService.mergeWorktrees(
                workNode.mainWorktreeId(),
                parentNode.mainWorktreeId()
        );

        if (!mainMergeResult.successful()) {
            mergerAgent.resolveConflicts(String.join(", ", mainMergeResult.conflicts().stream()
                    .map(MergeResult.MergeConflict::filePath).toList()), mergeStrategy);

            emitStatusChangeEvent(workNode.nodeId(), GraphNode.NodeStatus.RUNNING,
                    GraphNode.NodeStatus.WAITING_REVIEW, "Merge conflicts detected", context);

            return new WorkNode(
                    workNode.nodeId(),
                    workNode.title(),
                    workNode.goal(),
                    GraphNode.NodeStatus.WAITING_REVIEW,
                    workNode.parentNodeId(),
                    workNode.childNodeIds(),
                    workNode.metadata(),
                    workNode.createdAt(),
                    Instant.now(),
                    workNode.mainWorktreeId(),
                    workNode.submoduleWorktreeIds(),
                    workNode.specFileId(),
                    workNode.completedSubtasks(),
                    workNode.totalSubtasks(),
                    workNode.agentType(),
                    workNode.workOutput(),
                    workNode.mergeRequired(),
                    workNode.streamingTokenCount()
            );
        }

        for (String childSubmoduleId : workNode.submoduleWorktreeIds()) {
            var childSubWt = context.getWorktree(childSubmoduleId);
            if (childSubWt instanceof SubmoduleWorktreeContext childSub) {
                for (String parentSubmoduleId : parentNode.submoduleWorktreeIds()) {
                    var parentSubWt = context.getWorktree(parentSubmoduleId);
                    if (parentSubWt instanceof SubmoduleWorktreeContext parentSub &&
                            childSub.submoduleName().equals(parentSub.submoduleName())) {
                        MergeResult subMergeResult = worktreeService.mergeWorktrees(
                                childSubmoduleId, parentSubmoduleId);

                        if (!subMergeResult.successful()) {
                            emitStatusChangeEvent(workNode.nodeId(), GraphNode.NodeStatus.RUNNING,
                                    GraphNode.NodeStatus.WAITING_REVIEW,
                                    "Merge conflicts in submodule: " + childSub.submoduleName(), context);
                            return workNode;
                        }

                        worktreeService.updateSubmodulePointer(
                                parentNode.mainWorktreeId(),
                                childSub.submoduleName()
                        );
                        break;
                    }
                }
            }
        }

        if (workNode.specFileId() != null && parentNode.specFileId() != null) {
            specService.mergeSpecs(workNode.specFileId(), parentNode.specFileId());
        }

        emitWorktreeMergedEvent(workNode.mainWorktreeId(), parentNode.mainWorktreeId(),
                mainMergeResult.mergeCommitHash(), false, new ArrayList<>(), "main", context);

        emitStatusChangeEvent(workNode.nodeId(), GraphNode.NodeStatus.RUNNING,
                GraphNode.NodeStatus.COMPLETED, "Merge completed", context);

        return new WorkNode(
                workNode.nodeId(),
                workNode.title(),
                workNode.goal(),
                GraphNode.NodeStatus.COMPLETED,
                workNode.parentNodeId(),
                workNode.childNodeIds(),
                workNode.metadata(),
                workNode.createdAt(),
                Instant.now(),
                workNode.mainWorktreeId(),
                workNode.submoduleWorktreeIds(),
                workNode.specFileId(),
                workNode.completedSubtasks(),
                workNode.totalSubtasks(),
                workNode.agentType(),
                workNode.workOutput(),
                false,
                workNode.streamingTokenCount()
        );
    }
}
