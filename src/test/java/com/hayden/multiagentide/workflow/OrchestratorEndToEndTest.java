package com.hayden.multiagentide.workflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import com.hayden.multiagentide.infrastructure.AgentEventListener;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.MergeResult;
import com.hayden.multiagentide.model.events.Events;
import com.hayden.multiagentide.model.nodes.GraphNode;
import com.hayden.multiagentide.model.nodes.OrchestratorNode;
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
            .thenReturn("orchestrator-ok");
        when(discoveryOrchestrator.kickOffAnyNumberOfAgentsForCodeSearch(anyString(), anyString(), anyString()))
            .thenReturn("- discovery");
        when(discoveryAgent.discoverCodebaseSection(anyString(), anyString(), anyString(), anyString()))
            .thenReturn("discovery-results");
        when(discoveryCollector.consolidateDiscoveryFindings(anyString(), anyString(), anyString(), anyString()))
            .thenReturn("discovery-summary");
        when(planningOrchestrator.decomposePlanAndCreateWorkItems(anyString(), anyString(), anyString()))
            .thenReturn("- plan-segment");
        when(planningAgent.decomposePlanAndCreateWorkItems(anyString(), anyString(), anyString()))
            .thenReturn("- plan-segment");
        when(planningCollector.consolidatePlansIntoTickets(anyString(), anyString(), anyString(), anyString()))
            .thenReturn("- Ticket 1");
        when(ticketOrchestrator.orchestrateTicketExecution(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn("ticket-orchestration");
        when(ticketAgent.implementTicket(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn("implementation");
        when(reviewAgent.evaluateContent(anyString(), anyString(), anyString(), anyString()))
            .thenReturn("approved");
        when(mergerAgent.performMerge(anyString(), anyString(), anyString()))
            .thenReturn("merged");
    }

    @Test
    void orchestratorSucceedsWithoutSubmodules() {
        stubWorktreeService(false);

        lifecycleHandler.initializeOrchestrator(
            "repo-url",
            "main",
            "Test goal",
            "Test Orchestrator"
        );

        OrchestratorNode root = loadRootOrchestrator().orElseThrow();
        assertThat(root.status()).isEqualTo(GraphNode.NodeStatus.COMPLETED);

        assertThat(graphRepository.findAll())
            .allMatch(node -> node.status() == GraphNode.NodeStatus.COMPLETED);

        List<Events.WorktreeCreatedEvent> worktreeEvents =
            testEventListener.eventsOfType(Events.WorktreeCreatedEvent.class);
        assertThat(worktreeEvents)
            .anyMatch(event -> "main".equals(event.worktreeType()))
            .noneMatch(event -> "submodule".equals(event.worktreeType()));

        List<WorktreeContext> worktrees = worktreeRepository.findAll();
        assertThat(worktrees)
            .filteredOn(wt -> wt.parentWorktreeId() != null)
            .allMatch(wt -> wt.status() == WorktreeContext.WorktreeStatus.MERGED);
    }

    @Test
    void orchestratorSucceedsWithSubmodules() {
        stubWorktreeService(true);

        lifecycleHandler.initializeOrchestrator(
            "repo-url",
            "main",
            "Test goal",
            "Test Orchestrator"
        );

        OrchestratorNode root = loadRootOrchestrator().orElseThrow();
        assertThat(root.status()).isEqualTo(GraphNode.NodeStatus.COMPLETED);

        List<Events.WorktreeCreatedEvent> worktreeEvents =
            testEventListener.eventsOfType(Events.WorktreeCreatedEvent.class);
        assertThat(worktreeEvents)
            .anyMatch(event -> "main".equals(event.worktreeType()))
            .anyMatch(event -> "submodule".equals(event.worktreeType()));
    }

    private Optional<OrchestratorNode> loadRootOrchestrator() {
        return graphRepository.findAll().stream()
            .filter(OrchestratorNode.class::isInstance)
            .map(OrchestratorNode.class::cast)
            .findFirst();
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
            .thenAnswer(invocation -> Path.of("modules").resolve(invocation.getArgument(1)));

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
            .thenAnswer(invocation -> new MergeResult(
                UUID.randomUUID().toString(),
                invocation.getArgument(0),
                invocation.getArgument(1),
                true,
                "merge-commit",
                List.of(),
                List.of(),
                "merge successful",
                Instant.now()
            ));

        when(worktreeService.detectMergeConflicts(anyString(), anyString()))
            .thenReturn(List.of());
    }
}
