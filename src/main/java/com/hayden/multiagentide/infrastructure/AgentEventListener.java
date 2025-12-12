package com.hayden.multiagentide.infrastructure;

import com.hayden.multiagentide.model.mixins.*;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AgentEventListener implements EventListener {

    private final ComputationGraphOrchestrator orchestrator;

    private final AgentRunner agentRunner;


    @Override
    public String listenerId() {
        return "";
    }

    public record AgentDispatch(GraphNode self, @Nullable GraphNode parent, List<GraphNode> children)  {

        public void runAgent(AgentRunner agentRunner) {
            switch(self) {
                case DiscoveryNode discoveryNode -> {
                }
                case DiscoveryOrchestratorNode discoveryOrchestratorNode -> {
                }
                case PlanningOrchestratorNode planningOrchestratorNode -> {
                }
                case AgentReviewNode agentReviewNode -> {
                }
                case HumanReviewNode humanReviewNode -> {
                }
                case MergeNode mergeNode -> {
                }
                case OrchestratorNode orchestratorNode -> {
                }
                case PlanningNode planningNode -> {
                }
                case SummaryNode summaryNode -> {
                }
                case EditorNode editorNode -> {
                }
            }
        }
    }

    @Override
    public void onEvent(Events.GraphEvent event) {
        switch(event) {
            case Events.NodeAddedEvent(String eventId,
                                       Instant timestamp,
                                       String nodeId,
                                       String nodeTitle,
                                       GraphNode.NodeType nodeType,
                                       String parentNodeId) -> {

                var n = orchestrator.getNode(nodeId);
                if (n.isPresent() && n.get().status() == GraphNode.NodeStatus.READY) {
//                TODO: use agentRunner
                    var p = orchestrator.getNode(parentNodeId);
                    var children = orchestrator.getChildNodes(nodeId);
                }
            }
            case Events.GoalCompletedEvent goalCompletedEvent -> {
            }
            case Events.NodeBranchedEvent nodeBranchedEvent -> {
            }
            case Events.NodeDeletedEvent nodeDeletedEvent -> {
            }
            case Events.NodePrunedEvent nodePrunedEvent -> {
            }
            case Events.NodeReviewRequestedEvent nodeReviewRequestedEvent -> {
            }
            case Events.NodeStatusChangedEvent nodeStatusChangedEvent -> {
            }
            case Events.NodeStreamDeltaEvent nodeStreamDeltaEvent -> {
            }
            case Events.NodeUpdatedEvent nodeUpdatedEvent -> {
            }
            case Events.SpecMergedEvent specMergedEvent -> {
            }
            case Events.SpecUpdatedEvent specUpdatedEvent -> {
            }
            case Events.SpecValidatedEvent specValidatedEvent -> {
            }
            case Events.WorktreeBranchedEvent worktreeBranchedEvent -> {
            }
            case Events.WorktreeCreatedEvent worktreeCreatedEvent -> {
            }
            case Events.WorktreeDiscardedEvent worktreeDiscardedEvent -> {
            }
            case Events.WorktreeMergedEvent worktreeMergedEvent -> {
            }
        }
    }

    @Override
    public boolean isInterestedIn(Events.GraphEvent eventType) {
        return eventType instanceof Events.NodeAddedEvent;
    }
}
