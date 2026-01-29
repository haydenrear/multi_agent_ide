package com.hayden.multiagentide.integration;

import com.embabel.agent.api.common.PlannerType;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.ProcessOptions;
import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.WorkflowGraphService;
import com.hayden.multiagentide.artifacts.ArtifactEventListener;
import com.hayden.multiagentide.artifacts.ArtifactTreeBuilder;
import com.hayden.multiagentide.artifacts.ExecutionScopeService;
import com.hayden.multiagentide.artifacts.entity.ArtifactEntity;
import com.hayden.multiagentide.artifacts.repository.ArtifactRepository;
import com.hayden.multiagentide.gate.PermissionGate;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.LlmRunner;
import com.hayden.multiagentide.service.WorktreeService;
import com.hayden.multiagentide.support.AgentTestBase;
import com.hayden.multiagentide.support.QueuedLlmRunner;
import com.hayden.multiagentide.support.TestEventListener;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.multiagentidelib.prompt.ContextIdService;
import com.hayden.utilitymodule.acp.events.EventBus;
import com.hayden.utilitymodule.acp.events.Events;
import com.hayden.multiagentidelib.model.nodes.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.*;

/**
 * Integration tests for WorkflowAgent using queue-based LLM mocking.
 *
 * This approach:
 * 1. Mocks the LlmRunner with a queue of pre-defined responses
 * 2. Lets the real agent code execute (no need to mock individual actions)
 * 3. Verifies the workflow executes correctly with those responses
 *
 * Benefits over action-level mocking:
 * - Simpler test setup (just queue responses)
 * - Tests execute closer to production code
 * - No need to manually call registerAndHideInput
 * - More resilient to refactoring
 *
 * **Important Note**: all of these tests take about 5 min to complete, i.e. not tuned currently
 */
@Slf4j
@SpringBootTest
@Profile("test")
class WorkflowAgentQueuedTest extends AgentTestBase {

    @Autowired
    private AgentPlatform agentPlatform;

    @MockitoSpyBean
    private AgentInterfaces.WorkflowAgent workflowAgent;

    @MockitoSpyBean
    private AgentInterfaces.WorkflowAgent.DiscoveryDispatchSubagent discoveryDispatchSubagent;

    @MockitoSpyBean
    private AgentInterfaces.WorkflowAgent.PlanningDispatchSubagent planningDispatchSubagent;

    @MockitoSpyBean
    private AgentInterfaces.WorkflowAgent.TicketDispatchSubagent ticketDispatchSubagent;

    @Autowired
    private QueuedLlmRunner queuedLlmRunner;

    @MockitoSpyBean
    private WorkflowGraphService workflowGraphService;

    @MockitoSpyBean
    private ComputationGraphOrchestrator computationGraphOrchestrator;

    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private WorktreeRepository worktreeRepository;

    @MockitoBean
    private WorktreeService worktreeService;

    @Autowired
    private TestEventListener testEventListener;
    @Autowired
    private ContextIdService contextIdService;
    @Autowired
    private PermissionGate permissionGate;
    @Autowired
    private ArtifactRepository artifactRepository;

    @Autowired
    private ArtifactTreeBuilder artifactTreeBuilder;

    @MockitoSpyBean
    private EventBus eventBus;

    @MockitoSpyBean
    private ArtifactEventListener artifactEventListener;

    @MockitoSpyBean
    private ExecutionScopeService executionScopeService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        LlmRunner llmRunner() {
            return new QueuedLlmRunner();
        }

        @Bean
        QueuedLlmRunner queuedLlmRunner(LlmRunner llmRunner) {
            return (QueuedLlmRunner) llmRunner;
        }

        @Bean
        TestEventListener testEventListener() {
            return new TestEventListener();
        }
    }

    @BeforeEach
    void setUp() {
        testEventListener.clear();
        queuedLlmRunner.clear();
        graphRepository.clear();
        worktreeRepository.clear();
        reset(
                workflowAgent,
                discoveryDispatchSubagent,
                planningDispatchSubagent,
                ticketDispatchSubagent,
                workflowGraphService,
                computationGraphOrchestrator,
                worktreeService,
                eventBus
        );
        doThrow(new RuntimeException("worktree disabled"))
                .when(worktreeService)
                .branchWorktree(any(), any(), any());
        doThrow(new RuntimeException("worktree disabled"))
                .when(worktreeService)
                .branchSubmoduleWorktree(any(), any(), any());
    }

    @Nested
    class InterruptScenarios {

        @SneakyThrows
        @Test
        void orchestratorPause_workflowStops() {
            String contextId = ArtifactKey.createRoot().value();
            seedOrchestrator(contextId);
            queuedLlmRunner.enqueue(AgentModels.OrchestratorRouting.builder()
                    .interruptRequest(AgentModels.InterruptRequest.OrchestratorInterruptRequest.builder()
                            .type(Events.InterruptType.PAUSE)
                            .reason("User requested pause")
                            .build())
                    .build());

            CompletableFuture.runAsync(() -> {
                agentPlatform.runAgentFrom(
                        findWorkflowAgent(),
                        ProcessOptions.DEFAULT.withContextId(contextId).withPlannerType(PlannerType.GOAP),
                        Map.of("it", new AgentModels.OrchestratorRequest(new ArtifactKey(contextId), "Paused task", "DISCOVERY"))
                );
            });

            await().atMost(Duration.ofSeconds(300))
                    .until(() -> permissionGate.isInterruptPending(t -> t.getType() == Events.InterruptType.PAUSE && Objects.equals(t.getReason(), "User requested pause")));

            var pk = agentPlatform.getAgentProcess(contextId).kill();

            var output = permissionGate.getInterruptPending(t -> t.getType() == Events.InterruptType.PAUSE && Objects.equals(t.getReason(), "User requested pause"));

            assertThat(output).isNotNull();
            verify(workflowGraphService).handleOrchestratorInterrupt(any(),
                    argThat(req -> req.type() == Events.InterruptType.PAUSE));

            var ordered = inOrder(
                    workflowAgent
            );
            ordered.verify(workflowAgent).coordinateWorkflow(any(), any());
            ordered.verify(workflowAgent).handleOrchestratorInterrupt(any(), any());

            verify(computationGraphOrchestrator, atLeastOnce()).emitStatusChangeEvent(any(), any(), any(), any());
            queuedLlmRunner.assertAllConsumed();

            permissionGate.resolveInterrupt(output.getInterruptId(), "", "", null);
        }

        @SneakyThrows
        @Test
        void orchestratorPause_resolveInterruptContinues() {
            String contextId = "test-pause-continue-" + UUID.randomUUID();
            seedOrchestrator(contextId);
            queuedLlmRunner.enqueue(AgentModels.OrchestratorRouting.builder()
                    .interruptRequest(AgentModels.InterruptRequest.OrchestratorInterruptRequest.builder()
                            .type(Events.InterruptType.PAUSE)
                            .reason("User requested pause will continue")
                            .build())
                    .build());

            initialOrchestratorToDiscovery("Do that thing.");
            enqueueDiscoveryToEnd("Do that thing.");

            var res = CompletableFuture.supplyAsync(() -> agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId(contextId).withPlannerType(PlannerType.GOAP),
                    Map.of("it", new AgentModels.OrchestratorRequest(ArtifactKey.createRoot(), "Paused task", "DISCOVERY"))
            ));

            await().atMost(Duration.ofSeconds(300))
                    .until(() -> permissionGate.isInterruptPending(
                            t -> t.getType() == Events.InterruptType.PAUSE && Objects.equals(t.getReason(), "User requested pause will continue")));

            verify(workflowGraphService).handleOrchestratorInterrupt(any(),
                    argThat(req -> req.type() == Events.InterruptType.PAUSE));

            var output = permissionGate.getInterruptPending(t -> t.getType() == Events.InterruptType.PAUSE && Objects.equals(t.getReason(), "User requested pause will continue"));

            assertThat(output).isNotNull();

            permissionGate.resolveInterrupt(
                    output.getInterruptId(),
                    "",
                    "",
                    null);

            await().atMost(Duration.ofSeconds(300))
                    .until(res::isDone);

            var result = res.get();

            assertThat(result).isNotNull();
            assertThat(output).isNotNull();

            var ordered = inOrder(
                    workflowAgent,
                    discoveryDispatchSubagent,
                    planningDispatchSubagent,
                    ticketDispatchSubagent
            );
            ordered.verify(workflowAgent).coordinateWorkflow(any(), any());
            ordered.verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            ordered.verify(workflowAgent).dispatchDiscoveryAgentRequests(any(), any());
            ordered.verify(discoveryDispatchSubagent).runDiscoveryAgent(any(AgentModels.DiscoveryAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateDiscoveryFindings(any(), any());
            ordered.verify(workflowAgent).handleDiscoveryCollectorBranch(any(), any());
            ordered.verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
            ordered.verify(workflowAgent).dispatchPlanningAgentRequests(any(), any());
            ordered.verify(planningDispatchSubagent).runPlanningAgent(any(AgentModels.PlanningAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidatePlansIntoTickets(any(), any());
            ordered.verify(workflowAgent).handlePlanningCollectorBranch(any(), any());
            ordered.verify(workflowAgent).orchestrateTicketExecution(any(), any());
            ordered.verify(workflowAgent).dispatchTicketAgentRequests(any(), any());
            ordered.verify(ticketDispatchSubagent).runTicketAgent(any(AgentModels.TicketAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateTicketResults(any(), any());
            ordered.verify(workflowAgent).handleTicketCollectorBranch(any(), any());
            ordered.verify(workflowAgent).consolidateWorkflowOutputs(any(), any());

            verify(computationGraphOrchestrator, atLeastOnce()).emitStatusChangeEvent(any(), any(), any(), any());
            queuedLlmRunner.assertAllConsumed();
        }
    }

    @Nested
    class HappyPathWorkflows {

        @Test
        void fullWorkflow_discoveryToPlanningSingleAgentsToCompletion() {
            String contextId = "test-full-workflow-" + UUID.randomUUID();
            seedOrchestrator(contextId);
            enqueueHappyPath("Implement auth");

            // Act - Run the workflow with real agent code
            var output = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId(contextId).withPlannerType(PlannerType.GOAP),
                    Map.of("it", new AgentModels.OrchestratorRequest(ArtifactKey.createRoot(), "Implement auth", "DISCOVERY"))
            );

            // Assert
            assertThat(output.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Verify all queued responses were consumed
            queuedLlmRunner.assertAllConsumed();

            // Verify graph service calls happened
            verify(workflowGraphService).startOrchestrator(any());
            verify(workflowGraphService).startDiscoveryOrchestrator(any(), any());
            verify(workflowGraphService).startDiscoveryAgent(any(), any(), any(), any());
            verify(workflowGraphService).startPlanningOrchestrator(any(), any());
            verify(workflowGraphService).startPlanningAgent(any(), any(), any(), any());
            verify(workflowGraphService).startTicketOrchestrator(any(), any());
            verify(workflowGraphService).startTicketAgent(any(), any(), anyInt());
            verify(computationGraphOrchestrator, atLeastOnce()).addChildNodeAndEmitEvent(any(), any());
            verify(computationGraphOrchestrator, atLeastOnce()).emitStatusChangeEvent(any(), any(), any(), any());

            var ordered = inOrder(
                    workflowAgent,
                    discoveryDispatchSubagent,
                    planningDispatchSubagent,
                    ticketDispatchSubagent
            );
            ordered.verify(workflowAgent).coordinateWorkflow(any(), any());
            ordered.verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            ordered.verify(workflowAgent).dispatchDiscoveryAgentRequests(any(), any());
            ordered.verify(discoveryDispatchSubagent).runDiscoveryAgent(any(AgentModels.DiscoveryAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateDiscoveryFindings(any(), any());
            ordered.verify(workflowAgent).handleDiscoveryCollectorBranch(any(), any());
            ordered.verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
            ordered.verify(workflowAgent).dispatchPlanningAgentRequests(any(), any());
            ordered.verify(planningDispatchSubagent).runPlanningAgent(any(AgentModels.PlanningAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidatePlansIntoTickets(any(), any());
            ordered.verify(workflowAgent).handlePlanningCollectorBranch(any(), any());
            ordered.verify(workflowAgent).orchestrateTicketExecution(any(), any());
            ordered.verify(workflowAgent).dispatchTicketAgentRequests(any(), any());
            ordered.verify(ticketDispatchSubagent).runTicketAgent(any(AgentModels.TicketAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateTicketResults(any(), any());
            ordered.verify(workflowAgent).handleTicketCollectorBranch(any(), any());
            ordered.verify(workflowAgent).consolidateWorkflowOutputs(any(), any());
        }

        @Test
        void fullWorkflow_persistsArtifactTree() {
            String contextId = "test-artifact-tree-" + UUID.randomUUID();
            seedOrchestrator(contextId);
            enqueueHappyPath("Implement auth");

            Instant startedAt = Instant.now();

            var output = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId(contextId).withPlannerType(PlannerType.GOAP),
                    Map.of("it", new AgentModels.OrchestratorRequest(ArtifactKey.createRoot(), "Implement auth", "DISCOVERY"))
            );

            Instant finishedAt = Instant.now();

            assertThat(output.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);
            queuedLlmRunner.assertAllConsumed();

            // Verify we emitted artifacts and opened execution scope
            verify(artifactEventListener, atLeastOnce()).onEvent(isA(Events.ArtifactEvent.class));
            verify(executionScopeService, atLeastOnce()).startExecution(anyString(), any(ArtifactKey.class));

            String executionKey = findExecutionKeyForContext(contextId, startedAt, finishedAt);

            List<ArtifactEntity> persisted = artifactRepository.findByExecutionKeyOrderByArtifactKey(executionKey);
            assertThat(persisted).isNotEmpty();

            Set<String> keys = persisted.stream().map(ArtifactEntity::getArtifactKey).collect(java.util.stream.Collectors.toSet());

            ArtifactEntity root = persisted.stream()
                    .filter(entity -> entity.getParentKey() == null && "Execution".equals(entity.getArtifactType()))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Execution root not persisted"));

            assertThat(root.getArtifactKey()).isEqualTo(executionKey);
            assertThat(artifactRepository.existsByArtifactKey(executionKey)).isTrue();

            // Validate tree structure: all children reference existing parents
            persisted.stream()
                    .filter(entity -> entity.getParentKey() != null)
                    .forEach(entity -> assertThat(keys).contains(entity.getParentKey()));

            // Validate expected artifacts exist and are nested under rendered prompt
            List<ArtifactEntity> renderedPrompts = persisted.stream()
                    .filter(entity -> "RenderedPrompt".equals(entity.getArtifactType()))
                    .toList();
            assertThat(renderedPrompts).isNotEmpty();

            Set<String> renderedPromptKeys = renderedPrompts.stream()
                    .map(ArtifactEntity::getArtifactKey)
                    .collect(java.util.stream.Collectors.toSet());

            List<ArtifactEntity> templateArtifacts = persisted.stream()
                    .filter(entity -> "PromptTemplateVersion".equals(entity.getArtifactType()))
                    .toList();
            assertThat(templateArtifacts).isNotEmpty();

            templateArtifacts.forEach(template ->
                    assertThat(renderedPromptKeys).contains(template.getParentKey()));

            // Clean up test artifacts to keep DB tidy
            artifactRepository.deleteByExecutionKey(executionKey);
        }

        private String findExecutionKeyForContext(String contextId, Instant startedAt, Instant finishedAt) {
            List<String> executionKeys = artifactRepository.findExecutionKeysBetween(
                    startedAt.minusSeconds(2),
                    finishedAt.plusSeconds(5)
            );

            return executionKeys.stream()
                    .filter(key -> artifactTreeBuilder.loadExecution(key)
                            .filter(artifact -> artifact instanceof Artifact.ExecutionArtifact exec
                                    && contextId.equals(exec.workflowRunId()))
                            .isPresent())
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("No execution persisted for workflow run id: " + contextId));
        }

        @Test
        void skipDiscovery_startAtPlanning() {
            log.error("Haven't implemented!");
        }

        @Test
        void planningOrchestrator_toContextManager_toTicketOrchestrator() {
            log.error("Haven't implemented!");
        }

    }

    @Nested
    class LoopingScenarios {

        @Test
        void discoveryOrchestrator_toContextManager_backToDiscoveryOrchestrator() {
            log.error("Haven't implemented");
        }

        @Test
        void discoveryCollector_loopsBackForMoreInvestigation() {
            seedOrchestrator("test-discovery-loop");

            initialOrchestratorToDiscovery("Needs more discovery");

            queuedLlmRunner.enqueue(AgentModels.DiscoveryOrchestratorRouting.builder()
                    .agentRequests(AgentModels.DiscoveryAgentRequests.builder()
                            .requests(List.of(
                                    AgentModels.DiscoveryAgentRequest.builder()
                                            .goal("Needs more discovery")
                                            .subdomainFocus("Initial")
                                            .build()
                            ))
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryAgentRouting.builder()
                    .agentResult(AgentModels.DiscoveryAgentResult.builder()
                            .output("Initial findings - incomplete")
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryAgentDispatchRouting.builder()
                    .collectorRequest(AgentModels.DiscoveryCollectorRequest.builder()
                            .goal("Needs more discovery")
                            .discoveryResults("initial")
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryCollectorRouting.builder()
                    .collectorResult(AgentModels.DiscoveryCollectorResult.builder()
                            .consolidatedOutput("Need more discovery")
                            .collectorDecision(AgentModels.CollectorDecision.builder()
                                    .decisionType(Events.CollectorDecisionType.ROUTE_BACK)
                                    .rationale("Loop back to discovery")
                                    .requestedPhase("DISCOVERY")
                                    .build())
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryOrchestratorRouting.builder()
                    .agentRequests(AgentModels.DiscoveryAgentRequests.builder()
                            .requests(List.of(
                                    AgentModels.DiscoveryAgentRequest.builder()
                                            .goal("Needs more discovery")
                                            .subdomainFocus("Deeper")
                                            .build()
                            ))
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryAgentRouting.builder()
                    .agentResult(AgentModels.DiscoveryAgentResult.builder()
                            .output("Deeper findings - complete")
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryAgentDispatchRouting.builder()
                    .collectorRequest(AgentModels.DiscoveryCollectorRequest.builder()
                            .goal("Needs more discovery")
                            .discoveryResults("deeper")
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.DiscoveryCollectorRouting.builder()
                    .collectorResult(AgentModels.DiscoveryCollectorResult.builder()
                            .consolidatedOutput("Discovery complete")
                            .collectorDecision(AgentModels.CollectorDecision.builder()
                                    .decisionType(Events.CollectorDecisionType.ADVANCE_PHASE)
                                    .rationale("Advance to planning")
                                    .requestedPhase("PLANNING")
                                    .build())
                            .build())
                    .build());

            enqueuePlanningToCompletion("Needs more discovery");

            var output = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-discovery-loop").withPlannerType(PlannerType.GOAP),
                    Map.of("it", new AgentModels.OrchestratorRequest(ArtifactKey.createRoot(), "Needs more discovery", "DISCOVERY"))
            );

            assertThat(output.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);
            queuedLlmRunner.assertAllConsumed();
            verify(workflowAgent, times(2)).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(workflowAgent, times(2)).consolidateDiscoveryFindings(any(), any());
            verify(computationGraphOrchestrator, atLeastOnce()).emitStatusChangeEvent(any(), any(), any(), any());

            var ordered = inOrder(
                    workflowAgent,
                    discoveryDispatchSubagent,
                    planningDispatchSubagent,
                    ticketDispatchSubagent
            );
            ordered.verify(workflowAgent).coordinateWorkflow(any(), any());
            ordered.verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            ordered.verify(workflowAgent).dispatchDiscoveryAgentRequests(any(), any());
            ordered.verify(discoveryDispatchSubagent).runDiscoveryAgent(any(AgentModels.DiscoveryAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateDiscoveryFindings(any(), any());
            ordered.verify(workflowAgent).handleDiscoveryCollectorBranch(any(), any());
            ordered.verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            ordered.verify(workflowAgent).dispatchDiscoveryAgentRequests(any(), any());
            ordered.verify(discoveryDispatchSubagent).runDiscoveryAgent(any(AgentModels.DiscoveryAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateDiscoveryFindings(any(), any());
            ordered.verify(workflowAgent).handleDiscoveryCollectorBranch(any(), any());
            ordered.verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
            ordered.verify(workflowAgent).dispatchPlanningAgentRequests(any(), any());
            ordered.verify(planningDispatchSubagent).runPlanningAgent(any(AgentModels.PlanningAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidatePlansIntoTickets(any(), any());
            ordered.verify(workflowAgent).handlePlanningCollectorBranch(any(), any());
            ordered.verify(workflowAgent).orchestrateTicketExecution(any(), any());
            ordered.verify(workflowAgent).dispatchTicketAgentRequests(any(), any());
            ordered.verify(ticketDispatchSubagent).runTicketAgent(any(AgentModels.TicketAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateTicketResults(any(), any());
            ordered.verify(workflowAgent).handleTicketCollectorBranch(any(), any());
            ordered.verify(workflowAgent).consolidateWorkflowOutputs(any(), any());
        }

        @Test
        void planningCollector_loopsBackToDiscovery_needsMoreContext() {
            seedOrchestrator("test-planning-to-discovery");

            initialOrchestratorToDiscovery("Incomplete context");

            discoveryOnly("Do a goal");

            queuedLlmRunner.enqueue(AgentModels.PlanningOrchestratorRouting.builder()
                    .agentRequests(AgentModels.PlanningAgentRequests.builder()
                            .requests(List.of(
                                    AgentModels.PlanningAgentRequest.builder()
                                            .goal("Incomplete context")
                                            .build()
                            ))
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.PlanningAgentRouting.builder()
                    .agentResult(AgentModels.PlanningAgentResult.builder()
                            .output("Plan missing context")
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.PlanningAgentDispatchRouting.builder()
                    .planningCollectorRequest(AgentModels.PlanningCollectorRequest.builder()
                            .goal("Incomplete context")
                            .planningResults("plan")
                            .build())
                    .build());

            queuedLlmRunner.enqueue(AgentModels.PlanningCollectorRouting.builder()
                    .discoveryOrchestratorRequest(AgentModels.DiscoveryOrchestratorRequest.builder()
                            .goal("Incomplete context")
                            .build())
                    .build());


            enqueueDiscoveryToEnd("This goal.");

            var output = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-planning-to-discovery").withPlannerType(PlannerType.GOAP),
                    Map.of("it", new AgentModels.OrchestratorRequest(ArtifactKey.createRoot(), "Incomplete context", "PLANNING"))
            );

            assertThat(output.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            queuedLlmRunner.assertAllConsumed();

            verify(workflowAgent, times(2)).decomposePlanAndCreateWorkItems(any(), any());
            verify(workflowAgent, times(2)).consolidatePlansIntoTickets(any(), any());
            verify(workflowAgent, times(2)).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(computationGraphOrchestrator, atLeastOnce()).emitStatusChangeEvent(any(), any(), any(), any());

            var ordered = inOrder(
                    workflowAgent,
                    discoveryDispatchSubagent,
                    planningDispatchSubagent,
                    ticketDispatchSubagent
            );

            ordered.verify(workflowAgent).coordinateWorkflow(any(), any());
            ordered.verify(workflowAgent).consolidateDiscoveryFindings(any(), any());
            ordered.verify(workflowAgent).handleDiscoveryCollectorBranch(any(), any());
            ordered.verify(workflowAgent).dispatchPlanningAgentRequests(any(), any());
            ordered.verify(planningDispatchSubagent).runPlanningAgent(any(AgentModels.PlanningAgentRequest.class), any());
            ordered.verify(workflowAgent).dispatchDiscoveryAgentRequests(any(), any());
            ordered.verify(discoveryDispatchSubagent).runDiscoveryAgent(any(AgentModels.DiscoveryAgentRequest.class), any());
            ordered.verify(workflowAgent).handlePlanningCollectorBranch(any(), any());
            ordered.verify(workflowAgent).orchestrateTicketExecution(any(), any());
            ordered.verify(workflowAgent).dispatchTicketAgentRequests(any(), any());
            ordered.verify(ticketDispatchSubagent).runTicketAgent(any(AgentModels.TicketAgentRequest.class), any());
            ordered.verify(workflowAgent).consolidateTicketResults(any(), any());
            ordered.verify(workflowAgent).handleTicketCollectorBranch(any(), any());
            ordered.verify(workflowAgent).consolidateWorkflowOutputs(any(), any());
        }

//        @Test
        void orchestratorCollector_loopsBackMultipleTimes() {
        }
    }

    private com.embabel.agent.core.Agent findWorkflowAgent() {
        return agentPlatform.agents().stream()
                .filter(a -> a.getName().equals(AgentInterfaces.WORKFLOW_AGENT_NAME)
                        || a.getName().contains(AgentInterfaces.WorkflowAgent.class.getSimpleName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("WorkflowAgent not found"));
    }

    private void enqueueHappyPath(String goal) {
        initialOrchestratorToDiscovery(goal);
        enqueueDiscoveryToEnd(goal);
    }

    private void enqueueDiscoveryToEnd(String goal) {
        discoveryOnly(goal);
        enqueuePlanningToCompletion(goal);
    }

    private void initialOrchestratorToDiscovery(String goal) {
        queuedLlmRunner.enqueue(AgentModels.OrchestratorRouting.builder()
                .orchestratorRequest(AgentModels.DiscoveryOrchestratorRequest.builder()
                        .goal(goal)
                        .build())
                .build());
    }

    private void discoveryOnly(String goal) {
        discoveryOnly(goal, AgentModels.DiscoveryCollectorRouting.builder()
                .collectorResult(AgentModels.DiscoveryCollectorResult.builder()
                        .consolidatedOutput("Discovery complete")
                        .collectorDecision(AgentModels.CollectorDecision.builder()
                                .decisionType(Events.CollectorDecisionType.ADVANCE_PHASE)
                                .rationale("Advance to planning")
                                .requestedPhase("PLANNING")
                                .build())
                        .build())
                .build());
    }

    private void discoveryOnly(String goal, AgentModels.DiscoveryCollectorRouting build) {
        queuedLlmRunner.enqueue(AgentModels.DiscoveryOrchestratorRouting.builder()
                .agentRequests(AgentModels.DiscoveryAgentRequests.builder()
                        .requests(List.of(
                                AgentModels.DiscoveryAgentRequest.builder()
                                        .goal(goal)
                                        .subdomainFocus("Primary")
                                        .build()
                        ))
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.DiscoveryAgentRouting.builder()
                .agentResult(AgentModels.DiscoveryAgentResult.builder()
                        .output("Found stuff")
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.DiscoveryAgentDispatchRouting.builder()
                .collectorRequest(AgentModels.DiscoveryCollectorRequest.builder()
                        .goal(goal)
                        .discoveryResults("discovery-results")
                        .build())
                .build());

        queuedLlmRunner.enqueue(build);
    }

    private void enqueuePlanningToCompletion(String goal) {
        planningOnly(goal);

        ticketsOnly(goal);

        finalOrchestratorCollector();
    }

    private void finalOrchestratorCollector() {
        queuedLlmRunner.enqueue(AgentModels.OrchestratorCollectorRouting.builder()
                .collectorResult(AgentModels.OrchestratorCollectorResult.builder()
                        .consolidatedOutput("Workflow complete")
                        .collectorDecision(AgentModels.CollectorDecision.builder()
                                .decisionType(Events.CollectorDecisionType.ADVANCE_PHASE)
                                .rationale("All phases done")
                                .requestedPhase("COMPLETE")
                                .build())
                        .build())
                .build());
    }

    private void ticketsOnly(String goal) {
        queuedLlmRunner.enqueue(AgentModels.TicketOrchestratorRouting.builder()
                .agentRequests(AgentModels.TicketAgentRequests.builder()
                        .requests(List.of(
                                AgentModels.TicketAgentRequest.builder()
                                        .ticketDetails(goal)
                                        .ticketDetailsFilePath("ticket-1.md")
                                        .build()
                        ))
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.TicketAgentRouting.builder()
                .agentResult(AgentModels.TicketAgentResult.builder()
                        .output("Ticket output")
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.TicketAgentDispatchRouting.builder()
                .ticketCollectorRequest(AgentModels.TicketCollectorRequest.builder()
                        .goal(goal)
                        .ticketResults("ticket-results")
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.TicketCollectorRouting.builder()
                .collectorResult(AgentModels.TicketCollectorResult.builder()
                        .consolidatedOutput("Tickets complete")
                        .collectorDecision(AgentModels.CollectorDecision.builder()
                                .decisionType(Events.CollectorDecisionType.ADVANCE_PHASE)
                                .rationale("Advance to orchestrator collector")
                                .requestedPhase("COMPLETE")
                                .build())
                        .build())
                .build());
    }

    private void planningOnly(String goal) {
        queuedLlmRunner.enqueue(AgentModels.PlanningOrchestratorRouting.builder()
                .agentRequests(AgentModels.PlanningAgentRequests.builder()
                        .requests(List.of(
                                AgentModels.PlanningAgentRequest.builder()
                                        .goal(goal)
                                        .build()
                        ))
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.PlanningAgentRouting.builder()
                .agentResult(AgentModels.PlanningAgentResult.builder()
                        .output("Plan output")
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.PlanningAgentDispatchRouting.builder()
                .planningCollectorRequest(AgentModels.PlanningCollectorRequest.builder()
                        .goal(goal)
                        .planningResults("planning-results")
                        .build())
                .build());

        queuedLlmRunner.enqueue(AgentModels.PlanningCollectorRouting.builder()
                .collectorResult(AgentModels.PlanningCollectorResult.builder()
                        .consolidatedOutput("Planning complete")
                        .collectorDecision(AgentModels.CollectorDecision.builder()
                                .decisionType(Events.CollectorDecisionType.ADVANCE_PHASE)
                                .rationale("Advance to tickets")
                                .requestedPhase("TICKETS")
                                .build())
                        .build())
                .build());
    }

    private void seedOrchestrator(String contextId) {
        graphRepository.save(createMockOrchestratorNode(contextId));
    }

    private OrchestratorNode createMockOrchestratorNode(String nodeId) {
        return OrchestratorNode.builder()
                .nodeId(nodeId)
                .title("Orch")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .repositoryUrl("repo-url")
                .baseBranch("main")
                .mainWorktreeId("wt")
                .submoduleWorktreeIds(new ArrayList<>())
                .build();
    }

    private CollectorNode createMockCollectorNode() {
        return CollectorNode.builder()
                .nodeId("coll-1")
                .title("Collector")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .repositoryUrl("repo-url")
                .baseBranch("main")
                .mainWorktreeId("wt")
                .submoduleWorktreeIds(new ArrayList<>())
                .build();
    }

    private DiscoveryOrchestratorNode createMockDiscoveryOrchestratorNode() {
        return DiscoveryOrchestratorNode.builder()
                .nodeId("disc-orch")
                .title("DiscOrch")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private DiscoveryCollectorNode createMockDiscoveryCollectorNode() {
        return DiscoveryCollectorNode.builder()
                .nodeId("disc-coll")
                .title("DiscColl")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private DiscoveryNode createMockDiscoveryNode() {
        return DiscoveryNode.builder()
                .nodeId("disc")
                .title("Disc")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private PlanningOrchestratorNode createMockPlanningOrchestratorNode() {
        return PlanningOrchestratorNode.builder()
                .nodeId("plan-orch")
                .title("PlanOrch")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private PlanningCollectorNode createMockPlanningCollectorNode() {
        return PlanningCollectorNode.builder()
                .nodeId("plan-coll")
                .title("PlanColl")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private PlanningNode createMockPlanningNode() {
        return PlanningNode.builder()
                .nodeId("plan")
                .title("Plan")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private TicketOrchestratorNode createMockTicketOrchestratorNode() {
        return TicketOrchestratorNode.builder()
                .nodeId("tick-orch")
                .title("TickOrch")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .worktree(new HasWorktree.WorkTree("wt", null, new ArrayList<>()))
                .build();
    }

    private TicketCollectorNode createMockTicketCollectorNode() {
        return TicketCollectorNode.builder()
                .nodeId("tick-coll")
                .title("TickColl")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .build();
    }

    private TicketNode createMockTicketNode() {
        return TicketNode.builder()
                .nodeId("tick")
                .title("Tick")
                .goal("goal")
                .status(Events.NodeStatus.RUNNING)
                .metadata(new HashMap<>())
                .createdAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .worktree(new HasWorktree.WorkTree("wt", null, new ArrayList<>()))
                .build();
    }
}
