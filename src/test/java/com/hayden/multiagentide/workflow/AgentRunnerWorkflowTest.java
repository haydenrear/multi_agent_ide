package com.hayden.multiagentide.workflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.infrastructure.AgentRunner;
import com.hayden.multiagentide.model.MergeResult;
import com.hayden.multiagentide.model.nodes.EditorNode;
import com.hayden.multiagentide.model.nodes.GraphNode;
import com.hayden.multiagentide.model.nodes.MergeNode;
import com.hayden.multiagentide.model.nodes.ReviewNode;
import com.hayden.multiagentide.model.worktree.MainWorktreeContext;
import com.hayden.multiagentide.model.worktree.WorktreeContext;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;
import com.hayden.multiagentide.support.AgentTestBase;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class AgentRunnerWorkflowTest extends AgentTestBase {

    @Autowired
    private AgentRunner agentRunner;

    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private WorktreeRepository worktreeRepository;

    @MockitoBean
    private WorktreeService worktreeService;

    @BeforeEach
    void setUp() {
        graphRepository.clear();
        worktreeRepository.clear();
    }

    @Test
    void reviewApprovalCreatesMergeNode() {
        EditorNode ticketOrchestrator = ticketOrchestrator("orchestrator-1", "parent-wt");
        EditorNode ticketNode = ticketNode("ticket-1", ticketOrchestrator.nodeId(), "child-wt");
        ReviewNode reviewNode = reviewNode("review-1", ticketNode.nodeId());

        graphRepository.save(ticketOrchestrator);
        graphRepository.save(ticketNode);
        graphRepository.save(reviewNode);

        when(reviewAgent.evaluateContent(
            anyString(),
            anyString(),
            anyString(),
            anyString()
        )).thenReturn(new AgentInterfaces.ReviewAgentResult(
            "approved",
            List.of()
        ));

        agentRunner.runReviewAgent(reviewNode, ticketNode);

        Optional<GraphNode> updatedReview = graphRepository.findById(reviewNode.nodeId());
        assertThat(updatedReview).isPresent();
        assertThat(updatedReview.get().status()).isEqualTo(GraphNode.NodeStatus.COMPLETED);

        List<GraphNode> children = graphRepository.findByParentId(reviewNode.nodeId());
        assertThat(children).hasSize(1);
        assertThat(children.get(0)).isInstanceOf(MergeNode.class);

        MergeNode mergeNode = (MergeNode) children.get(0);
        assertThat(mergeNode.metadata().get("child_worktree_id")).isEqualTo(ticketNode.mainWorktreeId());
        assertThat(mergeNode.metadata().get("target_worktree_id")).isEqualTo(ticketOrchestrator.mainWorktreeId());
        assertThat(mergeNode.metadata().get("merge_scope")).isEqualTo("ticket");
    }

    @Test
    void reviewRejectionCreatesRevisionNode() {
        EditorNode ticketOrchestrator = ticketOrchestrator("orchestrator-2", "parent-wt-2");
        EditorNode ticketNode = ticketNode("ticket-2", ticketOrchestrator.nodeId(), "child-wt-2");
        ReviewNode reviewNode = reviewNode("review-2", ticketNode.nodeId());

        graphRepository.save(ticketOrchestrator);
        graphRepository.save(ticketNode);
        graphRepository.save(reviewNode);

        when(reviewAgent.evaluateContent(
            anyString(),
            anyString(),
            anyString(),
            anyString()
        )).thenReturn(new AgentInterfaces.ReviewAgentResult(
            "needs revision",
            List.of()
        ));

        agentRunner.runReviewAgent(reviewNode, ticketNode);

        List<GraphNode> orchestratorChildren = graphRepository.findByParentId(ticketOrchestrator.nodeId());
        boolean hasRevision = orchestratorChildren.stream()
            .filter(EditorNode.class::isInstance)
            .map(EditorNode.class::cast)
            .anyMatch(node -> node.title().contains("(Revision)") &&
                node.metadata().containsKey("review_feedback"));

        assertThat(hasRevision).isTrue();
    }

    @Test
    void mergeConflictsMovesNodeToWaitingInput() throws Exception {
        String childWorktreeId = "child-wt-conflict";
        String parentWorktreeId = "parent-wt-conflict";
        Path worktreePath = Files.createTempDirectory("merge-conflict-wt");

        MainWorktreeContext childWorktree = new MainWorktreeContext(
            childWorktreeId,
            worktreePath,
            "main",
            WorktreeContext.WorktreeStatus.ACTIVE,
            parentWorktreeId,
            "node-1",
            Instant.now(),
            "abc123",
            "repo-url",
            false,
            new ArrayList<>(),
            new HashMap<>()
        );
        worktreeRepository.save(childWorktree);

        MergeNode mergeNode = new MergeNode(
            "merge-1",
            "Merge: Ticket 1",
            "Merge ticket",
            GraphNode.NodeStatus.READY,
            "review-1",
            new ArrayList<>(),
            Map.of(
                "child_worktree_id", childWorktreeId,
                "target_worktree_id", parentWorktreeId,
                "merge_scope", "ticket"
            ),
            Instant.now(),
            Instant.now(),
            "",
            0,
            0
        );
        graphRepository.save(mergeNode);

        MergeResult mergeResult = new MergeResult(
            UUID.randomUUID().toString(),
            childWorktreeId,
            parentWorktreeId,
            false,
            null,
            List.of(new MergeResult.MergeConflict("conflict.txt", "content", "", "", "")),
            List.of(),
            "conflicts",
            Instant.now()
        );
        when(worktreeService.mergeWorktrees(childWorktreeId, parentWorktreeId))
            .thenReturn(mergeResult);

        agentRunner.runMergeAgent(mergeNode, null);

        GraphNode updated = graphRepository.findById(mergeNode.nodeId()).orElseThrow();
        assertThat(updated.status()).isEqualTo(GraphNode.NodeStatus.WAITING_INPUT);

        WorktreeContext reloaded = worktreeRepository.findById(childWorktreeId).orElseThrow();
        assertThat(reloaded.status()).isEqualTo(WorktreeContext.WorktreeStatus.ACTIVE);
    }

    private EditorNode ticketOrchestrator(String nodeId, String mainWorktreeId) {
        return new EditorNode(
            nodeId,
            "Ticket Orchestrator",
            "Implement goal",
            GraphNode.NodeStatus.READY,
            null,
            new ArrayList<>(),
            new HashMap<>(),
            Instant.now(),
            Instant.now(),
            mainWorktreeId,
            new ArrayList<>(),
            0,
            0,
            "ticket-orchestrator",
            "",
            true,
            0
        );
    }

    private EditorNode ticketNode(String nodeId, String parentId, String mainWorktreeId) {
        return new EditorNode(
            nodeId,
            "Ticket 1",
            "Implement ticket",
            GraphNode.NodeStatus.READY,
            parentId,
            new ArrayList<>(),
            new HashMap<>(),
            Instant.now(),
            Instant.now(),
            mainWorktreeId,
            new ArrayList<>(),
            0,
            0,
            "ticket-agent",
            "",
            true,
            0
        );
    }

    private ReviewNode reviewNode(String nodeId, String reviewedNodeId) {
        return new ReviewNode(
            nodeId,
            "Review",
            "Review ticket",
            GraphNode.NodeStatus.READY,
            reviewedNodeId,
            new ArrayList<>(),
            new HashMap<>(),
            Instant.now(),
            Instant.now(),
            reviewedNodeId,
            "implementation",
            false,
            false,
            "",
            "agent-review",
            null
        );
    }
}
