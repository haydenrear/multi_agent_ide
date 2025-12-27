package com.hayden.multiagentide.workflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import com.hayden.multiagentide.agent.AgentModels;
import com.hayden.multiagentide.infrastructure.AgentEventListener;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.MergeResult;
import com.hayden.multiagentide.model.events.Events;
import com.hayden.multiagentide.model.nodes.DiscoveryCollectorNode;
import com.hayden.multiagentide.model.nodes.DiscoveryOrchestratorNode;
import com.hayden.multiagentide.model.nodes.TicketNode;
import com.hayden.multiagentide.model.nodes.GraphNode;
import com.hayden.multiagentide.model.nodes.MergeNode;
import com.hayden.multiagentide.model.nodes.OrchestratorNode;
import com.hayden.multiagentide.model.nodes.ReviewNode;
import com.hayden.multiagentide.model.nodes.TicketCollectorNode;
import com.hayden.multiagentide.model.worktree.MainWorktreeContext;
import com.hayden.multiagentide.model.worktree.SubmoduleWorktreeContext;
import com.hayden.multiagentide.model.worktree.WorktreeContext;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;
import com.hayden.multiagentide.support.AgentTestBase;
import com.hayden.multiagentide.support.TestEventListener;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class OrchestratorEndToEndTest extends AgentTestBase {

    @TestConfiguration
    static class TestConfig {
        @Bean
        TestEventListener testEventListener() {
            return new TestEventListener();
        }
    }

    @Autowired
    private AgentLifecycleHandler lifecycleHandler;

    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private WorktreeRepository worktreeRepository;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AgentEventListener agentEventListener;

    @Autowired
    private TestEventListener testEventListener;

    @MockitoBean
    private WorktreeService worktreeService;

    @BeforeEach
    void setUp() {
        graphRepository.clear();
        worktreeRepository.clear();
        testEventListener.clear();
        eventBus.clear();
        eventBus.subscribe(agentEventListener);
        eventBus.subscribe(testEventListener);

        when(orchestratorAgent.coordinateWorkflow(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.OrchestratorAgentResult(
                null,
                List.of(),
                "orchestrator-ok"
            ));
        when(discoveryOrchestrator.kickOffAnyNumberOfAgentsForCodeSearch(anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.DiscoveryOrchestratorResult(
                null,
                List.of(),
                "- discovery"
            ));
        when(discoveryAgent.discoverCodebaseSection(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.DiscoveryAgentResult(
                "discovery-results",
                List.of()
            ));
        AgentModels.CollectorDecision advanceDecision =
            new AgentModels.CollectorDecision(
                AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                "default",
                "next"
            );
        when(discoveryCollector.consolidateDiscoveryFindings(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.DiscoveryCollectorResult(
                "discovery-summary",
                List.of(),
                advanceDecision
            ));
        when(planningOrchestrator.decomposePlanAndCreateWorkItems(anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.PlanningOrchestratorResult(
                null,
                List.of(),
                "- plan-segment"
            ));
        when(planningAgent.decomposePlanAndCreateWorkItems(anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.PlanningAgentResult(
                "- plan-segment",
                List.of()
            ));
        when(planningCollector.consolidatePlansIntoTickets(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.PlanningCollectorResult(
                "- Ticket 1",
                List.of(),
                advanceDecision
            ));
        when(ticketOrchestrator.orchestrateTicketExecution(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.TicketOrchestratorResult(
                null,
                List.of(),
                "ticket-orchestration"
            ));
        when(ticketCollector.consolidateTicketResults(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.TicketCollectorResult(
                "ticket-summary",
                List.of(),
                advanceDecision
            ));
        when(ticketAgent.implementTicket(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.TicketAgentResult(
                "implementation",
                List.of()
            ));
        when(reviewAgent.evaluateContent(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.ReviewAgentResult(
                "approved",
                List.of()
            ));
        when(mergerAgent.performMerge(anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new AgentModels.MergerAgentResult(
                "merged",
                List.of()
            ));
    }

    @Test
    void orchestratorSucceedsWithoutSubmodules() {
        OrchestratorNode root = startOrchestration(false);
        assertThat(root.status()).isEqualTo(GraphNode.NodeStatus.COMPLETED);

        assertNoFailedNodes();
        assertExpectedCoreEvents();
        assertAgentsInvoked();

        List<Events.WorktreeCreatedEvent> worktreeEvents =
            testEventListener.eventsOfType(Events.WorktreeCreatedEvent.class);
        assertThat(worktreeEvents)
            .anyMatch(event -> "main".equals(event.worktreeType()))
            .noneMatch(event -> "submodule".equals(event.worktreeType()));

        assertThat(testEventListener.hasEventOfType(Events.NodeAddedEvent.class))
            .isTrue();

        boolean hasReviewNode = graphRepository.findAll().stream()
            .filter(ReviewNode.class::isInstance)
            .map(ReviewNode.class::cast)
            .anyMatch(node -> node.status() == GraphNode.NodeStatus.COMPLETED);
        assertThat(hasReviewNode).isTrue();

        boolean hasTicketCollector = graphRepository.findAll().stream()
            .filter(TicketCollectorNode.class::isInstance)
            .map(TicketCollectorNode.class::cast)
            .anyMatch(node -> node.status() == GraphNode.NodeStatus.COMPLETED);
        assertThat(hasTicketCollector).isTrue();

        assertThat(testEventListener.eventsOfType(
            Events.NodeStatusChangedEvent.class,
            event -> event.newStatus() == GraphNode.NodeStatus.COMPLETED
        )).isNotEmpty();

        List<WorktreeContext> worktrees = worktreeRepository.findAll();
        assertThat(worktrees)
            .filteredOn(wt -> wt.parentWorktreeId() != null)
            .allMatch(wt -> wt.status() == WorktreeContext.WorktreeStatus.MERGED);
    }

    @Test
    void orchestratorSucceedsWithSubmodules() {
        OrchestratorNode root = startOrchestration(true);
        assertThat(root.status()).isEqualTo(GraphNode.NodeStatus.COMPLETED);
        assertNoFailedNodes();
        assertExpectedCoreEvents();
        assertAgentsInvoked();

        List<Events.WorktreeCreatedEvent> worktreeEvents =
            testEventListener.eventsOfType(Events.WorktreeCreatedEvent.class);
        assertThat(worktreeEvents)
            .anyMatch(event -> "main".equals(event.worktreeType()))
            .anyMatch(event -> "submodule".equals(event.worktreeType()));

        assertThat(testEventListener.hasEventOfType(Events.NodeStatusChangedEvent.class))
            .isTrue();

        boolean hasMergeNode = graphRepository.findAll().stream()
            .filter(MergeNode.class::isInstance)
            .map(MergeNode.class::cast)
            .anyMatch(node -> node.status() == GraphNode.NodeStatus.COMPLETED);
        assertThat(hasMergeNode).isTrue();
    }

    @Test
    void orchestratorHandlesRevisionCycle() {
        stubWorktreeService(false);
        stubReviewSequence("needs revision", "approved");

        OrchestratorNode root = startOrchestration(false);
        assertThat(root.status()).isEqualTo(GraphNode.NodeStatus.COMPLETED);
        assertExpectedCoreEvents();

        boolean hasRevisionNode = graphRepository.findAll().stream()
            .filter(TicketNode.class::isInstance)
            .map(TicketNode.class::cast)
            .anyMatch(node -> node.title().contains("Revision"));
        assertThat(hasRevisionNode).isTrue();
        verify(reviewAgent, atLeast(2))
            .evaluateContent(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void collectorReviewGatePausesUntilDecision() {
        DiscoveryOrchestratorNode parent = new DiscoveryOrchestratorNode(
            "discovery-orch",
            "Discovery Orchestrator",
            "Test goal",
            GraphNode.NodeStatus.RUNNING,
            null,
            new ArrayList<>(List.of("discovery-collector")),
            new java.util.HashMap<>(),
            Instant.now(),
            Instant.now(),
            "",
            0,
            0
        );
        DiscoveryCollectorNode collector = new DiscoveryCollectorNode(
            "discovery-collector",
            "Discovery Collector",
            "Test goal",
            GraphNode.NodeStatus.RUNNING,
            parent.nodeId(),
            new ArrayList<>(),
            new java.util.HashMap<>(
                java.util.Map.of("collector_review_gate", "true")
            ),
            Instant.now(),
            Instant.now(),
            "",
            0,
            0
        );
        graphRepository.save(parent);
        graphRepository.save(collector);

        eventBus.publish(new Events.NodeStatusChangedEvent(
            UUID.randomUUID().toString(),
            Instant.now(),
            collector.nodeId(),
            GraphNode.NodeStatus.RUNNING,
            GraphNode.NodeStatus.COMPLETED,
            "completed"
        ));

        Optional<GraphNode> updated = graphRepository.findById(collector.nodeId());
        assertThat(updated).isPresent();
        assertThat(updated.orElseThrow().status())
            .isEqualTo(GraphNode.NodeStatus.WAITING_INPUT);
        assertThat(testEventListener.hasEventOfType(Events.NodeReviewRequestedEvent.class))
            .isTrue();
    }

    private void assertExpectedCoreEvents() {
        assertThat(testEventListener.hasEventOfType(Events.NodeAddedEvent.class))
            .isTrue();
        assertThat(testEventListener.hasEventOfType(Events.NodeStatusChangedEvent.class))
            .isTrue();
        assertThat(testEventListener.hasEventOfType(Events.WorktreeCreatedEvent.class))
            .isTrue();
    }

    private void assertAgentsInvoked() {
        verify(orchestratorAgent, atLeastOnce())
            .coordinateWorkflow(anyString(), anyString(), anyString(), anyString());
        verify(discoveryOrchestrator, atLeastOnce())
            .kickOffAnyNumberOfAgentsForCodeSearch(anyString(), anyString(), anyString());
        verify(discoveryAgent, atLeastOnce())
            .discoverCodebaseSection(anyString(), anyString(), anyString(), anyString());
        verify(discoveryCollector, atLeastOnce())
            .consolidateDiscoveryFindings(anyString(), anyString(), anyString(), anyString());
        verify(planningOrchestrator, atLeastOnce())
            .decomposePlanAndCreateWorkItems(anyString(), anyString(), anyString());
        verify(planningAgent, atLeastOnce())
            .decomposePlanAndCreateWorkItems(anyString(), anyString(), anyString());
        verify(planningCollector, atLeastOnce())
            .consolidatePlansIntoTickets(anyString(), anyString(), anyString(), anyString());
        verify(ticketOrchestrator, atLeastOnce())
            .orchestrateTicketExecution(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verify(ticketAgent, atLeastOnce())
            .implementTicket(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verify(ticketCollector, atLeastOnce())
            .consolidateTicketResults(anyString(), anyString(), anyString(), anyString());
        verify(reviewAgent, atLeastOnce())
            .evaluateContent(anyString(), anyString(), anyString(), anyString());
        verify(mergerAgent, atLeastOnce())
            .performMerge(anyString(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void orchestratorCapturesFailureMetadata() {
        stubWorktreeService(false);
        when(discoveryAgent.discoverCodebaseSection(anyString(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("discovery failed"));

        lifecycleHandler.initializeOrchestrator(
            "repo-url",
            "main",
            "Test goal",
            "Test Orchestrator"
        );

        Optional<GraphNode> failedNode = graphRepository.findAll().stream()
            .filter(node -> node.status() == GraphNode.NodeStatus.FAILED)
            .findFirst();

        assertThat(failedNode).isPresent();
        assertThat(failedNode.orElseThrow().metadata())
            .containsKey("error_message");
    }

    private Optional<OrchestratorNode> loadRootOrchestrator() {
        return graphRepository.findAll().stream()
            .filter(OrchestratorNode.class::isInstance)
            .map(OrchestratorNode.class::cast)
            .findFirst();
    }

    private void assertNoFailedNodes() {
        assertThat(graphRepository.findAll())
            .noneMatch(node -> node.status() == GraphNode.NodeStatus.FAILED);
    }

    private OrchestratorNode startOrchestration(boolean hasSubmodules) {
        stubWorktreeService(hasSubmodules);
        lifecycleHandler.initializeOrchestrator(
            "repo-url",
            "main",
            "Test goal",
            "Test Orchestrator"
        );
        return loadRootOrchestrator().orElseThrow();
    }

    private void stubReviewSequence(String... responses) {
        AtomicInteger counter = new AtomicInteger();
        when(reviewAgent.evaluateContent(anyString(), anyString(), anyString(), anyString()))
            .thenAnswer(invocation -> {
                int index = Math.min(counter.getAndIncrement(), responses.length - 1);
                return new AgentModels.ReviewAgentResult(
                    responses[index],
                    List.of()
                );
            });
    }

    private void stubWorktreeService(boolean hasSubmodules) {
        doAnswer(invocation -> {
            String nodeId = invocation.getArgument(2);
            MainWorktreeContext context = new MainWorktreeContext(
                UUID.randomUUID().toString(),
                Path.of("/tmp/worktrees/" + nodeId),
                "main",
                WorktreeContext.WorktreeStatus.ACTIVE,
                null,
                nodeId,
                Instant.now(),
                "commit-" + nodeId,
                invocation.getArgument(0),
                hasSubmodules,
                new ArrayList<>(),
                new java.util.HashMap<>()
            );
            worktreeRepository.save(context);
            return context;
        }).when(worktreeService).createMainWorktree(anyString(), anyString(), anyString());

        when(worktreeService.getSubmoduleNames(any()))
            .thenReturn(hasSubmodules ? List.of("submodule-a") : List.of());

        when(worktreeService.getSubmodulePath(any(), anyString()))
            .thenAnswer(invocation -> Path.of("modules").resolve(invocation.getArgument(1).toString()));

        doAnswer(invocation -> {
            SubmoduleWorktreeContext context = new SubmoduleWorktreeContext(
                UUID.randomUUID().toString(),
                Path.of("/tmp/worktrees/" + UUID.randomUUID()),
                "main",
                WorktreeContext.WorktreeStatus.ACTIVE,
                invocation.getArgument(2),
                invocation.getArgument(4),
                Instant.now(),
                "submodule-commit",
                invocation.getArgument(0),
                "file:///tmp/submodule",
                invocation.getArgument(2),
                new java.util.HashMap<>()
            );
            worktreeRepository.save(context);
            return context;
        }).when(worktreeService).createSubmoduleWorktree(anyString(), anyString(), anyString(), any(), anyString());

        doAnswer(invocation -> {
            MainWorktreeContext context = new MainWorktreeContext(
                UUID.randomUUID().toString(),
                Path.of("/tmp/worktrees/" + UUID.randomUUID()),
                invocation.getArgument(1),
                WorktreeContext.WorktreeStatus.ACTIVE,
                invocation.getArgument(0),
                invocation.getArgument(2),
                Instant.now(),
                "branch-commit",
                "repo-url",
                hasSubmodules,
                new ArrayList<>(),
                new java.util.HashMap<>()
            );
            worktreeRepository.save(context);
            return context;
        }).when(worktreeService).branchWorktree(anyString(), anyString(), anyString());

        doAnswer(invocation -> {
            SubmoduleWorktreeContext context = new SubmoduleWorktreeContext(
                UUID.randomUUID().toString(),
                Path.of("/tmp/worktrees/" + UUID.randomUUID()),
                invocation.getArgument(1),
                WorktreeContext.WorktreeStatus.ACTIVE,
                invocation.getArgument(0),
                invocation.getArgument(2),
                Instant.now(),
                "branch-submodule-commit",
                "submodule-a",
                "file:///tmp/submodule",
                invocation.getArgument(0),
                new java.util.HashMap<>()
            );
            worktreeRepository.save(context);
            return context;
        }).when(worktreeService).branchSubmoduleWorktree(anyString(), anyString(), anyString());

        when(worktreeService.mergeWorktrees(anyString(), anyString()))
            .thenAnswer(invocation -> {
                String childId = invocation.getArgument(0);
                String parentId = invocation.getArgument(1);
                if (childId == null || childId.isBlank()) {
                    childId = "child-worktree";
                }
                if (parentId == null || parentId.isBlank()) {
                    parentId = "parent-worktree";
                }
                return new MergeResult(
                    UUID.randomUUID().toString(),
                    childId,
                    parentId,
                    false,
                    "merge-commit",
                    List.of(new MergeResult.MergeConflict("", "", "", "", "")),
                    List.of(),
                    "merge successful",
                    Instant.now()
                );
            });

        when(worktreeService.detectMergeConflicts(anyString(), anyString()))
            .thenReturn(List.of());
    }
}
