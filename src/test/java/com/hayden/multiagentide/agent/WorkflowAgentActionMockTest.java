package com.hayden.multiagentide.agent;

import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.ProcessOptions;
import com.hayden.multiagentide.agent.WorkflowGraphService;
import com.hayden.multiagentide.service.InterruptService;
import com.hayden.multiagentide.support.AgentTestBase;
import com.hayden.multiagentide.support.TestEventListener;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.infrastructure.EventBus;
import com.hayden.multiagentidelib.model.nodes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Integration tests for WorkflowAgent that mock the action methods directly.
 * 
 * This allows testing complex routing scenarios, interrupts, loops, and edge cases
 * without needing to craft JSON responses from the LLM.
 * 
 * We spy on the WorkflowAgent and stub the action methods to return specific routing decisions.
 */
@SpringBootTest
class WorkflowAgentActionMockTest extends AgentTestBase {

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

    @MockitoBean
    private WorkflowGraphService workflowGraphService;

    @MockitoBean
    private InterruptService interruptService;

    @MockitoBean
    private EventBus eventBus;

    @Autowired
    private TestEventListener testEventListener;

    @TestConfiguration
    static class TestConfig {
        @Bean
        TestEventListener testEventListener() {
            return new TestEventListener();
        }
    }

    @BeforeEach
    void setUp() {
        testEventListener.clear();
        reset(workflowAgent, discoveryDispatchSubagent, planningDispatchSubagent, ticketDispatchSubagent, 
              workflowGraphService, interruptService, eventBus);
        
        // Setup default mock returns for graph service
        when(workflowGraphService.startOrchestrator(any())).thenReturn(createMockOrchestratorNode());
        when(workflowGraphService.startOrchestratorCollector(any(), any())).thenReturn(createMockCollectorNode());
        when(workflowGraphService.startDiscoveryOrchestrator(any(), any())).thenReturn(createMockDiscoveryOrchestratorNode());
        when(workflowGraphService.startDiscoveryCollector(any(), any())).thenReturn(createMockDiscoveryCollectorNode());
        when(workflowGraphService.startPlanningOrchestrator(any(), any())).thenReturn(createMockPlanningOrchestratorNode());
        when(workflowGraphService.startPlanningCollector(any(), any())).thenReturn(createMockPlanningCollectorNode());
        when(workflowGraphService.startTicketOrchestrator(any(), any())).thenReturn(createMockTicketOrchestratorNode());
        when(workflowGraphService.startTicketCollector(any(), any())).thenReturn(createMockTicketCollectorNode());
        when(workflowGraphService.requireDiscoveryOrchestrator(any())).thenReturn(createMockDiscoveryOrchestratorNode());
        when(workflowGraphService.requirePlanningOrchestrator(any())).thenReturn(createMockPlanningOrchestratorNode());
        when(workflowGraphService.requireTicketOrchestrator(any())).thenReturn(createMockTicketOrchestratorNode());
        when(workflowGraphService.startDiscoveryAgent(any(), any(), any())).thenReturn(createMockDiscoveryNode());
        when(workflowGraphService.startPlanningAgent(any(), any(), any())).thenReturn(createMockPlanningNode());
        when(workflowGraphService.startTicketAgent(any(), any(), anyInt())).thenReturn(createMockTicketNode());
//        when(workflowGraphService.startReviewNode(any())).thenReturn(createMockReviewNode());
//        when(workflowGraphService.startMergeNode(any())).thenReturn(createMockMergeNode());
    }

    @Nested
    class HappyPathWorkflows {

        @Test
        void fullWorkflow_discoveryToPlanningSingleAgentsToCompletion() {
            // 1. Orchestrator routes to collector
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Implement auth", "DISCOVERY")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // 2. Collector routes to Discovery
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Implement auth"),
                    null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "DISCOVERY".equals(req.phase())),
                    any()
            );

            // 3. Discovery orchestrator creates single agent request
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Implement auth", "Auth modules")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            // 4. Discovery subagent returns result
            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Found AuthService, UserService")
            )).when(discoveryDispatchSubagent).run(any(AgentModels.DiscoveryAgentRequest.class), any());

            // 5. Discovery dispatch routes to collector
            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Implement auth", "All findings")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(any(AgentModels.DiscoveryAgentRequests.class), any());

            // 6. Discovery collector routes to Planning
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    null, null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Implement auth"),
                    null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(any(AgentModels.DiscoveryCollectorRequest.class), any());

            // 7. Planning orchestrator creates single agent request
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    null,
                    new AgentModels.PlanningAgentRequests(List.of(
                            new AgentModels.PlanningAgentRequest("Implement auth")
                    )),
                    null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            // 8. Planning subagent returns result
            doReturn(new AgentModels.PlanningAgentRouting(
                    null,
                    new AgentModels.PlanningAgentResult("Plan: Add JWT, Update UserService")
            )).when(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());

            // 9. Planning dispatch routes to collector
            doReturn(new AgentModels.PlanningAgentDispatchRouting(
                    null,
                    new AgentModels.PlanningCollectorRequest("Implement auth", "All plans")
            )).when(workflowAgent).dispatchPlanningAgentRequests(any(AgentModels.PlanningAgentRequests.class), any());

            // 10. Planning collector routes back to orchestrator collector with result
            doReturn(new AgentModels.PlanningCollectorRouting(
                    null,
                    new AgentModels.PlanningCollectorResult(
                            "Plans consolidated",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Planning complete",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null
            )).when(workflowAgent).consolidatePlansIntoTickets(any(AgentModels.PlanningCollectorRequest.class), any());

            // 11. Orchestrator collector returns final result  
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Workflow complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "All phases done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "Implement auth".equals(req.goal())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-full-workflow"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Implement auth", "DISCOVERY"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Verify action methods called in correct order
            verify(workflowAgent).coordinateWorkflow(any(), any());
            verify(workflowAgent, atLeastOnce()).consolidateWorkflowOutputs(
                    any(AgentModels.OrchestratorCollectorRequest.class),
                    any()
            );
            verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(discoveryDispatchSubagent).run(any(AgentModels.DiscoveryAgentRequest.class), any());
            verify(workflowAgent).dispatchDiscoveryAgentRequests(any(), any());
            verify(workflowAgent).consolidateDiscoveryFindings(any(), any());
            verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
            verify(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());
            verify(workflowAgent).dispatchPlanningAgentRequests(any(), any());
            verify(workflowAgent).consolidatePlansIntoTickets(any(), any());

            // Verify graph service calls
            verify(workflowGraphService).startOrchestrator(any());
            verify(workflowGraphService).startDiscoveryOrchestrator(any(), any());
            verify(workflowGraphService).startDiscoveryAgent(any(), any(), any());
            verify(workflowGraphService).startDiscoveryCollector(any(), any());
            verify(workflowGraphService).startPlanningOrchestrator(any(), any());
            verify(workflowGraphService).startPlanningAgent(any(), any(), any());
            verify(workflowGraphService).startPlanningCollector(any(), any());
        }

        @Test
        void skipDiscovery_startAtPlanning() {
            // 1. Orchestrator routes to collector with PLANNING phase
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Quick task", "PLANNING")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // 2. Collector routes directly to Planning (skips Discovery)
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Quick task"),
                    null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "TICKETS".equals(req.phase())),
                    any()
            );

            // 3. Planning orchestrator creates request
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    null,
                    new AgentModels.PlanningAgentRequests(List.of(
                            new AgentModels.PlanningAgentRequest("Quick task")
                    )),
                    null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            // 4. Planning subagent returns result
            doReturn(new AgentModels.PlanningAgentRouting(
                    null,
                    new AgentModels.PlanningAgentResult("Simple plan")
            )).when(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());

            // 5. Planning dispatch routes to collector
            doReturn(new AgentModels.PlanningAgentDispatchRouting(
                    null,
                    new AgentModels.PlanningCollectorRequest("Quick task", "plan")
            )).when(workflowAgent).dispatchPlanningAgentRequests(any(AgentModels.PlanningAgentRequests.class), any());

            // 6. Planning collector completes
            doReturn(new AgentModels.PlanningCollectorRouting(
                    null,
                    new AgentModels.PlanningCollectorResult(
                            "Done",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Complete",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null
            )).when(workflowAgent).consolidatePlansIntoTickets(any(AgentModels.PlanningCollectorRequest.class), any());

            // 7. Orchestrator collector completes
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-skip-discovery"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Quick task", "PLANNING"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Verify Discovery was skipped
            verify(workflowAgent, never()).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(discoveryDispatchSubagent, never()).run(any(AgentModels.DiscoveryAgentRequest.class), any());
            verify(workflowGraphService, never()).startDiscoveryOrchestrator(any(), any());

            // Verify Planning was executed
            verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
            verify(workflowGraphService).startPlanningOrchestrator(any(), any());
        }

        @Test
        void skipToTickets_directlyFromOrchestrator() {
            // 1. Orchestrator routes to collector with TICKETS phase
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Direct to tickets", "TICKETS")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // 2. Collector routes directly to Tickets
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null, null,
                    new AgentModels.TicketOrchestratorRequest("Direct to tickets", "", "", ""),
                    null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "DISCOVERY".equals(req.phase())),
                    any()
            );

            // 3. Ticket orchestrator creates requests
            doReturn(new AgentModels.TicketOrchestratorRouting(
                    null,
                    new AgentModels.TicketAgentRequests(List.of(
                            new AgentModels.TicketAgentRequest("Direct to tickets", "", "", "")
                    )),
                    null
            )).when(workflowAgent).orchestrateTicketExecution(any(AgentModels.TicketOrchestratorRequest.class), any());

            // 4. Ticket subagent returns result
            doReturn(new AgentModels.TicketAgentRouting(
                    null,
                    new AgentModels.TicketAgentResult("Ticket completed")
            )).when(ticketDispatchSubagent).run(any(AgentModels.TicketAgentRequest.class), any());

            // 5. Ticket dispatch routes to collector
            doReturn(new AgentModels.TicketAgentDispatchRouting(
                    null,
                    new AgentModels.TicketCollectorRequest("Direct to tickets", "results")
            )).when(workflowAgent).dispatchTicketAgentRequests(any(AgentModels.TicketAgentRequests.class), any());

            // 6. Ticket collector completes
            doReturn(new AgentModels.TicketCollectorRouting(
                    null,
                    new AgentModels.TicketCollectorResult(
                            "Tickets done",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Complete",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null
            )).when(workflowAgent).consolidateTicketResults(any(AgentModels.TicketCollectorRequest.class), any());

            // 7. Orchestrator collector completes
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-direct-tickets"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Direct to tickets", "TICKETS"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Verify Discovery and Planning were skipped
            verify(workflowGraphService, never()).startDiscoveryOrchestrator(any(), any());
            verify(workflowGraphService, never()).startPlanningOrchestrator(any(), any());

            // Verify Tickets was executed
            verify(workflowAgent).orchestrateTicketExecution(any(AgentModels.TicketOrchestratorRequest.class), any());
            verify(workflowGraphService).startTicketOrchestrator(any(), any());
        }
    }

    @Nested
    class MultipleAgentScenarios {

        @Test
        void multipleDiscoveryAgents_allComplete() {
            // 1. Orchestrator routes to collector
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Complex discovery", "DISCOVERY")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // 2. Collector routes to Discovery
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Complex discovery"),
                    null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "PLANNING".equals(req.phase())),
                    any()
            );

            // 3. Discovery orchestrator creates MULTIPLE agent requests
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Complex discovery", "Frontend"),
                            new AgentModels.DiscoveryAgentRequest("Complex discovery", "Backend"),
                            new AgentModels.DiscoveryAgentRequest("Complex discovery", "Database")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            // 4. Each discovery subagent returns results
            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Frontend findings")
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Frontend")),
                    any()
            );

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Backend findings")
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Backend")),
                    any()
            );

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Database findings")
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Database")),
                    any()
            );

            // 5. Discovery dispatch routes to collector
            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Complex discovery", "All findings")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(any(AgentModels.DiscoveryAgentRequests.class), any());

            // 6. Discovery collector completes
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    null,
                    new AgentModels.DiscoveryCollectorResult(
                            "Discovery complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(any(AgentModels.DiscoveryCollectorRequest.class), any());

            // 7. Orchestrator collector completes
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-multiple-discovery"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Complex discovery", "DISCOVERY"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Verify all 3 discovery agents were called
            verify(discoveryDispatchSubagent, times(3)).run(any(AgentModels.DiscoveryAgentRequest.class), any());
            verify(workflowGraphService, times(3)).startDiscoveryAgent(any(), any(), any());
        }

        @Test
        void multiplePlanningAgents_consolidatedIntoTickets() {
            // Setup: Start at planning with multiple agents
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Multi-plan", "PLANNING")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Multi-plan"),
                    null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "DISCOVERY".equals(req.phase())),
                    any()
            );

            // Create 4 planning agents
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    null,
                    new AgentModels.PlanningAgentRequests(List.of(
                            new AgentModels.PlanningAgentRequest("Multi-plan"),
                            new AgentModels.PlanningAgentRequest("Multi-plan"),
                            new AgentModels.PlanningAgentRequest("Multi-plan"),
                            new AgentModels.PlanningAgentRequest("Multi-plan")
                    )),
                    null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            // All planning agents return results
            doReturn(new AgentModels.PlanningAgentRouting(
                    null,
                    new AgentModels.PlanningAgentResult("Plan part")
            )).when(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());

            doReturn(new AgentModels.PlanningAgentDispatchRouting(
                    null,
                    new AgentModels.PlanningCollectorRequest("Multi-plan", "All plans")
            )).when(workflowAgent).dispatchPlanningAgentRequests(any(AgentModels.PlanningAgentRequests.class), any());

            doReturn(new AgentModels.PlanningCollectorRouting(
                    null,
                    new AgentModels.PlanningCollectorResult(
                            "Plans ready",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null
            )).when(workflowAgent).consolidatePlansIntoTickets(any(AgentModels.PlanningCollectorRequest.class), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-multiple-planning"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Multi-plan", "PLANNING"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);
            verify(planningDispatchSubagent, times(4)).run(any(AgentModels.PlanningAgentRequest.class), any());
            verify(workflowGraphService, times(4)).startPlanningAgent(any(), any(), any());
        }
    }

    @Nested
    class InterruptScenarios {

        @Test
        void orchestratorPause_workflowStops() {
            // Orchestrator immediately pauses
            doReturn(new AgentModels.OrchestratorRouting(
                    new AgentModels.OrchestratorInterruptRequest(
                            AgentModels.InterruptType.PAUSE,
                            "User requested pause"
                    ),
                    null
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-pause"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Paused task", "DISCOVERY"))
            );

            // Assert - workflow should be interrupted
            verify(workflowGraphService).handleOrchestratorInterrupt(any(),
                    argThat(req -> req.type() == AgentModels.InterruptType.PAUSE));
            verify(workflowAgent, never()).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());
        }

        @Test
        void orchestratorStop_workflowAborts() {
            // Orchestrator stops the workflow
            doReturn(new AgentModels.OrchestratorRouting(
                    new AgentModels.OrchestratorInterruptRequest(
                            AgentModels.InterruptType.STOP,
                            "Critical error detected"
                    ),
                    null
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-stop"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Stopped task", "DISCOVERY"))
            );

            // Assert
            verify(workflowGraphService).handleOrchestratorInterrupt(any(),
                    argThat(req -> req.type() == AgentModels.InterruptType.STOP));
            verify(workflowAgent, never()).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
        }

        @Test
        void collectorPause_afterDiscovery() {
            // Normal orchestrator
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Task", "DISCOVERY")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // Collector routes to discovery
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Task"),
                    null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());

            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Task", "subdomain")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Found stuff")
            )).when(discoveryDispatchSubagent).run(any(AgentModels.DiscoveryAgentRequest.class), any());

            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Task", "findings")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(any(AgentModels.DiscoveryAgentRequests.class), any());

            // Discovery collector PAUSES instead of continuing
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    new AgentModels.DiscoveryCollectorInterruptRequest(
                            AgentModels.InterruptType.PAUSE,
                            "Need human review"
                    ),
                    null, null, null, null, null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(any(AgentModels.DiscoveryCollectorRequest.class), any());

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-collector-pause"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Task", "DISCOVERY"))
            );

            // Assert
            verify(workflowGraphService).completeDiscoveryCollector(
                    any(),
                    any(DiscoveryCollectorNode.class),
                    argThat(routing -> routing.interruptRequest() != null
                            && routing.interruptRequest().type() == AgentModels.InterruptType.PAUSE)
            );
            // Should NOT proceed to planning
            verify(workflowAgent, never()).decomposePlanAndCreateWorkItems(any(), any());
        }

        @Test
        void discoveryAgentPause_oneOfMany() {
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Multi-agent pause", "DISCOVERY")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Multi-agent pause"),
                    null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());

            // Create 3 discovery agents
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Multi-agent pause", "Agent1"),
                            new AgentModels.DiscoveryAgentRequest("Multi-agent pause", "Agent2"),
                            new AgentModels.DiscoveryAgentRequest("Multi-agent pause", "Agent3")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            // Agent 1 succeeds
            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Agent 1 result")
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Agent1")),
                    any()
            );

            // Agent 2 PAUSES
            doReturn(new AgentModels.DiscoveryAgentRouting(
                    new AgentModels.DiscoveryAgentInterruptRequest(
                            AgentModels.InterruptType.PAUSE,
                            "Agent 2 needs input"
                    ),
                    null
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Agent2")),
                    any()
            );

            // Agent 3 succeeds
            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Agent 3 result")
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Agent3")),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-agent-pause"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Multi-agent pause", "DISCOVERY"))
            );

            // Assert - one agent paused
            // Note: interrupt handling is done by the graph service, not directly verified on action calls
            verify(discoveryDispatchSubagent, times(3)).run(any(AgentModels.DiscoveryAgentRequest.class), any());
        }

        @Test
        void planningOrchestratorHumanReview_needsApproval() {
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Review needed", "PLANNING")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Review needed"),
                    null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());

            // Planning orchestrator requests HUMAN_REVIEW
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    new AgentModels.PlanningOrchestratorInterruptRequest(
                            AgentModels.InterruptType.HUMAN_REVIEW,
                            "Plan needs approval before proceeding"
                    ),
                    null, null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-human-review"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Review needed", "PLANNING"))
            );

            // Assert
            // Note: Planning orchestrator interrupt is handled by WorkflowAgent.handlePlanningInterrupt
            verify(planningDispatchSubagent, never()).run(any(AgentModels.PlanningAgentRequest.class), any());
        }

        @Test
        void ticketAgentStop_criticalError() {
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Ticket error", "TICKETS")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null, null,
                    new AgentModels.TicketOrchestratorRequest("Direct to tickets", "", "", ""),
                    null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());

            doReturn(new AgentModels.TicketOrchestratorRouting(
                    null,
                    new AgentModels.TicketAgentRequests(List.of(
                            new AgentModels.TicketAgentRequest("Ticket error", "", "", "")
                    )),
                    null
            )).when(workflowAgent).orchestrateTicketExecution(any(), any());

            // Ticket agent encounters critical error and STOPS
            doReturn(new AgentModels.TicketAgentRouting(
                    new AgentModels.TicketAgentInterruptRequest(
                            AgentModels.InterruptType.STOP,
                            "Build failed, cannot continue"
                    ),
                    null
            )).when(ticketDispatchSubagent).run(any(AgentModels.TicketAgentRequest.class), any());

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-ticket-stop"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Ticket error", "TICKETS"))
            );

            // Assert
            verify(ticketDispatchSubagent).run(any(AgentModels.TicketAgentRequest.class), any());
        }
    }

    @Nested
    class LoopingScenarios {

        @Test
        void discoveryCollector_loopsBackForMoreInvestigation() {
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Needs more discovery", "DISCOVERY")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Needs more discovery"),
                    null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());

            // First discovery iteration
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Needs more discovery", "Initial")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Initial findings - incomplete")
            )).when(discoveryDispatchSubagent).run(any(AgentModels.DiscoveryAgentRequest.class), any());

            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Needs more discovery", "initial")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(any(AgentModels.DiscoveryAgentRequests.class), any());

            // Discovery collector decides to loop back to orchestrator for MORE discovery
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    null, null,
                    null,
                    new AgentModels.DiscoveryOrchestratorRequest("Needs more discovery"), // Loop back!
                    null, null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(
                    Mockito.<AgentModels.DiscoveryCollectorRequest>argThat(req -> req.discoveryResults().contains("initial")),
                    any()
            );

            // Second discovery iteration with different subdomain
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Needs more discovery", "Deeper")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(
                    Mockito.<AgentModels.DiscoveryOrchestratorRequest>argThat(req -> "Needs more discovery".equals(req.goal())),
                    any()
            );

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Deeper findings - complete")
            )).when(discoveryDispatchSubagent).run(
                    argThat((AgentModels.DiscoveryAgentRequest req) -> req.subdomainFocus().equals("Deeper")),
                    any()
            );

            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Needs more discovery", "deeper")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(
                    argThat(results -> results.requests().stream()
                            .anyMatch(r -> r.goal().contains("Deeper"))),
                    any()
            );

            // Second collector NOW advances to planning
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    null, null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Needs more discovery"),
                    null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(
                    Mockito.<AgentModels.DiscoveryCollectorRequest>argThat(req -> req.discoveryResults().contains("deeper")),
                    any()
            );

            // Rest of workflow
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    null,
                    new AgentModels.PlanningAgentRequests(List.of(
                            new AgentModels.PlanningAgentRequest("Needs more discovery")
                    )),
                    null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            doReturn(new AgentModels.PlanningAgentRouting(
                    null,
                    new AgentModels.PlanningAgentResult("Plan")
            )).when(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());

            doReturn(new AgentModels.PlanningAgentDispatchRouting(
                    null,
                    new AgentModels.PlanningCollectorRequest("Needs more discovery", "plan")
            )).when(workflowAgent).dispatchPlanningAgentRequests(any(AgentModels.PlanningAgentRequests.class), any());

            doReturn(new AgentModels.PlanningCollectorRouting(
                    null,
                    new AgentModels.PlanningCollectorResult(
                            "Done",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Complete",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null
            )).when(workflowAgent).consolidatePlansIntoTickets(any(AgentModels.PlanningCollectorRequest.class), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-discovery-loop"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Needs more discovery", "DISCOVERY"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Verify TWO discovery iterations
            verify(workflowAgent, times(2)).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(workflowAgent, times(2)).consolidateDiscoveryFindings(any(AgentModels.DiscoveryCollectorRequest.class), any());
            verify(workflowGraphService, times(2)).startDiscoveryOrchestrator(any(), any());

            // But planning only once
            verify(workflowAgent, times(1)).decomposePlanAndCreateWorkItems(any(), any());
        }

        @Test
        void planningCollector_loopsBackToDiscovery_needsMoreContext() {
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Incomplete context", "PLANNING")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Incomplete context"),
                    null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "PLANNING".equals(req.phase())),
                    any()
            );

            // Planning phase
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    null,
                    new AgentModels.PlanningAgentRequests(List.of(
                            new AgentModels.PlanningAgentRequest("Incomplete context")
                    )),
                    null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            doReturn(new AgentModels.PlanningAgentRouting(
                    null,
                    new AgentModels.PlanningAgentResult("Plan missing context")
            )).when(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());

            doReturn(new AgentModels.PlanningAgentDispatchRouting(
                    null,
                    new AgentModels.PlanningCollectorRequest("Incomplete context", "plan")
            )).when(workflowAgent).dispatchPlanningAgentRequests(any(AgentModels.PlanningAgentRequests.class), any());

            // Planning collector decides to loop back to DISCOVERY for more context
            doReturn(new AgentModels.PlanningCollectorRouting(
                    null, null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Incomplete context"), // Loop back to discovery!
                    null, null
            )).when(workflowAgent).consolidatePlansIntoTickets(any(AgentModels.PlanningCollectorRequest.class), any());

            // Now do discovery
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Incomplete context", "Additional")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("Additional context found")
            )).when(discoveryDispatchSubagent).run(any(AgentModels.DiscoveryAgentRequest.class), any());

            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Incomplete context", "additional")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(any(AgentModels.DiscoveryAgentRequests.class), any());

            // Discovery collector advances to orchestrator collector with result
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    null,
                    new AgentModels.DiscoveryCollectorResult(
                            "Context complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(any(AgentModels.DiscoveryCollectorRequest.class), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-planning-to-discovery"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Incomplete context", "PLANNING"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Started at planning, then looped back to discovery
            verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
            verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(workflowGraphService).startPlanningOrchestrator(any(), any());
            verify(workflowGraphService).startDiscoveryOrchestrator(any(), any());
        }

        @Test
        void orchestratorCollector_loopsBackMultipleTimes() {
            // Orchestrator starts workflow
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Multi-loop", "DISCOVERY")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // First: route to discovery
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null,
                    new AgentModels.DiscoveryOrchestratorRequest("Multi-loop"),
                    null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "DISCOVERY".equals(req.phase())),
                    any()
            );

            // Discovery completes
            doReturn(new AgentModels.DiscoveryOrchestratorRouting(
                    null,
                    new AgentModels.DiscoveryAgentRequests(List.of(
                            new AgentModels.DiscoveryAgentRequest("Multi-loop", "First")
                    )),
                    null
            )).when(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());

            doReturn(new AgentModels.DiscoveryAgentRouting(
                    null,
                    new AgentModels.DiscoveryAgentResult("First discovery")
            )).when(discoveryDispatchSubagent).run(any(AgentModels.DiscoveryAgentRequest.class), any());

            doReturn(new AgentModels.DiscoveryAgentDispatchRouting(
                    null,
                    new AgentModels.DiscoveryCollectorRequest("Multi-loop", "first")
            )).when(workflowAgent).dispatchDiscoveryAgentRequests(any(AgentModels.DiscoveryAgentRequests.class), any());

            // Discovery collector loops back to ORCHESTRATOR COLLECTOR (not to planning)
            doReturn(new AgentModels.DiscoveryCollectorRouting(
                    null, null,
                    new AgentModels.OrchestratorRequest("Multi-loop", "PLANNING"), // Back to orchestrator!
                    null, null, null, null, null, null
            )).when(workflowAgent).consolidateDiscoveryFindings(any(AgentModels.DiscoveryCollectorRequest.class), any());

            // Second: orchestrator collector routes to planning
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null,
                    new AgentModels.PlanningOrchestratorRequest("Multi-loop"),
                    null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "PLANNING".equals(req.phase())),
                    any()
            );

            // Planning completes
            doReturn(new AgentModels.PlanningOrchestratorRouting(
                    null,
                    new AgentModels.PlanningAgentRequests(List.of(
                            new AgentModels.PlanningAgentRequest("Multi-loop")
                    )),
                    null
            )).when(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());

            doReturn(new AgentModels.PlanningAgentRouting(
                    null,
                    new AgentModels.PlanningAgentResult("Plan")
            )).when(planningDispatchSubagent).run(any(AgentModels.PlanningAgentRequest.class), any());

            doReturn(new AgentModels.PlanningAgentDispatchRouting(
                    null,
                    new AgentModels.PlanningCollectorRequest("Multi-loop", "plan")
            )).when(workflowAgent).dispatchPlanningAgentRequests(any(AgentModels.PlanningAgentRequests.class), any());

            // Planning collector ALSO loops back to orchestrator collector
            doReturn(new AgentModels.PlanningCollectorRouting(
                    null,
                    null,
                    null,
                    null,
                    new AgentModels.ReviewRequest(
                            "Multi-loop",
                            "Review criteria",
                            new AgentModels.OrchestratorCollectorRequest("Multi-loop", "TICKETS"),
                            null,
                            null,
                            null
                    ),
                    null
            )).when(workflowAgent).consolidatePlansIntoTickets(any(AgentModels.PlanningCollectorRequest.class), any());

            doReturn(new AgentModels.ReviewRouting(
                    null,
                    new AgentModels.ReviewAgentResult("approved"),
                    new AgentModels.OrchestratorCollectorRequest("Multi-loop", "TICKETS"),
                    null,
                    null,
                    null
            )).when(workflowAgent).evaluateContent(any(), any());

            // Third: orchestrator collector finally completes
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Finally complete",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Done",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "TICKETS".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-multi-loop"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Multi-loop", "DISCOVERY"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // consolidateWorkflowOutputs called 3 times (DISCOVERY, PLANNING, TICKETS phases)
            verify(workflowAgent, atLeast(3)).consolidateWorkflowOutputs(any(AgentModels.OrchestratorCollectorRequest.class), any());
            verify(workflowAgent).kickOffAnyNumberOfAgentsForCodeSearch(any(), any());
            verify(workflowAgent).decomposePlanAndCreateWorkItems(any(), any());
        }
    }

    @Nested
    class ReviewAndMergeWorkflows {

        // TODO: Re-enable these tests when Review and Merge actions are fully implemented in the refactored agent
        // The current AgentInterfaces.WorkflowAgent doesn't have complete Review/Merge dispatch implementation yet
        
        // @Test
        void ticketsToReview_thenMerge_thenComplete_DISABLED() {
            // Start at REVIEW phase
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Review flow", "REVIEW")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            // Orchestrator collector routes to Review
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null, null, null,
                    new AgentModels.ReviewRequest(
                            "Review flow",
                            "Review criteria",
                            new AgentModels.OrchestratorCollectorRequest("Review flow", "MERGE"),
                            null,
                            null,
                            null
                    ),
                    null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "REVIEW".equals(req.phase())),
                    any()
            );

            // Review action returns result routing to Merger
            doReturn(new AgentModels.ReviewRouting(
                    null,
                    new AgentModels.ReviewAgentResult("Review passed"),
                    new AgentModels.OrchestratorCollectorRequest("Review flow", "MERGE"),
                    null,
                    null,
                    null
            )).when(workflowAgent).evaluateContent(any(), any());

            // Merger completes
            doReturn(new AgentModels.MergerRouting(
                    null,
                    new AgentModels.MergerAgentResult("Merged successfully"),
                    new AgentModels.OrchestratorCollectorRequest("Review flow", "COMPLETE"),
                    null,
                    null,
                    null
            )).when(workflowAgent).performMerge(any(), any());

            // Orchestrator collector completes
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "All done",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Complete",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-review-merge"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Review flow", "REVIEW"))
            );

            // Assert
            // TODO: Re-enable when review/merge actions are fully implemented
            // assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);
        }

        // @Test
        void reviewFails_loopsBackToTickets_DISABLED() {
            doReturn(new AgentModels.OrchestratorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorRequest("Review fail", "REVIEW")
            )).when(workflowAgent).coordinateWorkflow(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null, null, null,
                    new AgentModels.ReviewRequest(
                            "Review fail",
                            "Review criteria",
                            new AgentModels.OrchestratorCollectorRequest("Review fail", "MERGE"),
                            null,
                            null,
                            null
                    ),
                    null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "REVIEW".equals(req.phase())),
                    any()
            );

            // Review FAILS - routes back to orchestrator collector to retry tickets
            doReturn(new AgentModels.ReviewRouting(
                    null,
                    new AgentModels.ReviewAgentResult("Review failed - tests broken"),
                    new AgentModels.OrchestratorCollectorRequest("Review fail", "TICKETS"), // Back to tickets!
                    null,
                    null,
                    null
            )).when(workflowAgent).evaluateContent(any(), any());

            // Now back to tickets phase
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null, null,
                    new AgentModels.TicketOrchestratorRequest("Direct to tickets", "", "", ""),
                    null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "TICKETS".equals(req.phase())),
                    any()
            );

            // Tickets execute
            doReturn(new AgentModels.TicketOrchestratorRouting(
                    null,
                    new AgentModels.TicketAgentRequests(List.of(
                            new AgentModels.TicketAgentRequest("Review fail", "", "", "")
                    )),
                    null
            )).when(workflowAgent).orchestrateTicketExecution(any(), any());

            doReturn(new AgentModels.TicketAgentRouting(
                    null,
                    new AgentModels.TicketAgentResult("Fixed tests")
            )).when(ticketDispatchSubagent).run(any(AgentModels.TicketAgentRequest.class), any());

            doReturn(new AgentModels.TicketAgentDispatchRouting(
                    null,
                    new AgentModels.TicketCollectorRequest("Review fail", "fixed")
            )).when(workflowAgent).dispatchTicketAgentRequests(any(AgentModels.TicketAgentRequests.class), any());

            // Ticket collector now advances to REVIEW again
            doReturn(new AgentModels.TicketCollectorRouting(
                    null,
                    null,
                    null,
                    new AgentModels.ReviewRequest(
                            "Review fail",
                            "Review criteria",
                            new AgentModels.OrchestratorCollectorRequest("Review fail", "REVIEW"),
                            null,
                            null,
                            null
                    ),
                    null
            )).when(workflowAgent).consolidateTicketResults(any(AgentModels.TicketCollectorRequest.class), any());

            // Second review attempt
            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null, null, null, null, null,
                    new AgentModels.ReviewRequest(
                            "Review fail",
                            "Review criteria",
                            new AgentModels.OrchestratorCollectorRequest("Review fail", "MERGE"),
                            null,
                            null,
                            null
                    ),
                    null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "REVIEW".equals(req.phase())),
                    any()
            );

            // Second review passes
            doReturn(new AgentModels.ReviewRouting(
                    null,
                    new AgentModels.ReviewAgentResult("Review passed"),
                    new AgentModels.OrchestratorCollectorRequest("Review fail", "MERGE"),
                    null,
                    null,
                    null
            )).when(workflowAgent).evaluateContent(
                    Mockito.<AgentModels.ReviewRequest>argThat(req -> "Review fail".equals(req.content())),
                    any()
            );

            doReturn(new AgentModels.MergerRouting(
                    null,
                    new AgentModels.MergerAgentResult("Merged"),
                    new AgentModels.OrchestratorCollectorRequest("Review fail", "COMPLETE"),
                    null,
                    null,
                    null
            )).when(workflowAgent).performMerge(any(), any());

            doReturn(new AgentModels.OrchestratorCollectorRouting(
                    null,
                    new AgentModels.OrchestratorCollectorResult(
                            "Done",
                            new AgentModels.CollectorDecision(
                                    AgentModels.CollectorDecisionType.ADVANCE_PHASE,
                                    "Complete",
                                    "COMPLETE"
                            )
                    ),
                    null, null, null, null, null
            )).when(workflowAgent).consolidateWorkflowOutputs(
                    Mockito.<AgentModels.OrchestratorCollectorRequest>argThat(req -> "COMPLETE".equals(req.phase())),
                    any()
            );

            // Act
            var result = agentPlatform.runAgentFrom(
                    findWorkflowAgent(),
                    ProcessOptions.DEFAULT.withContextId("test-review-loop"),
                    Map.of("it", new AgentModels.OrchestratorRequest("Review fail", "REVIEW"))
            );

            // Assert
            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);

            // Review called twice (failed once, passed second time)
            verify(workflowAgent, times(2)).evaluateContent(any(), any());
            // Tickets executed in the loop
            verify(workflowAgent).orchestrateTicketExecution(any(), any());
        }
    }

    // Helper methods
    private com.embabel.agent.core.Agent findWorkflowAgent() {
        return agentPlatform.agents().stream()
                .filter(a -> a.getName().equals(AgentInterfaces.WORKFLOW_AGENT_NAME))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("WorkflowAgent not found"));
    }

    // Mock node creation helpers
    private OrchestratorNode createMockOrchestratorNode() {
        return new OrchestratorNode(
                "orch-1", "Orch", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                "repo-url",
                "main",
                false,
                new ArrayList<>(),
                "wt",
                new ArrayList<>(),
                ""
        );
    }

    private CollectorNode createMockCollectorNode() {
        return new CollectorNode(
                "coll-1", "Collector", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                "repo-url",
                "main",
                false,
                new ArrayList<>(),
                "wt",
                new ArrayList<>(),
                ""
        );
    }

    private DiscoveryOrchestratorNode createMockDiscoveryOrchestratorNode() {
        return new DiscoveryOrchestratorNode(
                "disc-orch", "DiscOrch", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(), "", 0, 0
        );
    }

    private DiscoveryCollectorNode createMockDiscoveryCollectorNode() {
        return new DiscoveryCollectorNode(
                "disc-coll", "DiscColl", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(), "", 0, 0
        );
    }

    private DiscoveryNode createMockDiscoveryNode() {
        return new DiscoveryNode(
                "disc", "Disc", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(), "", 0, 0
        );
    }

    private PlanningOrchestratorNode createMockPlanningOrchestratorNode() {
        return new PlanningOrchestratorNode(
                "plan-orch", "PlanOrch", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                new ArrayList<>(),
                "",
                0,
                0
        );
    }

    private PlanningCollectorNode createMockPlanningCollectorNode() {
        return new PlanningCollectorNode(
                "plan-coll", "PlanColl", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                new ArrayList<>(),
                "",
                0,
                0
        );
    }

    private PlanningNode createMockPlanningNode() {
        return new PlanningNode(
                "plan", "Plan", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                new ArrayList<>(),
                "",
                0,
                0
        );
    }

    private TicketOrchestratorNode createMockTicketOrchestratorNode() {
        return new TicketOrchestratorNode(
                "tick-orch", "TickOrch", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                new HasWorktree.WorkTree("wt", null, new ArrayList<>()),
                0, 0, "agent", "", true, 0
        );
    }

    private TicketCollectorNode createMockTicketCollectorNode() {
        return new TicketCollectorNode(
                "tick-coll", "TickColl", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(), "", 0, 0
        );
    }

    private TicketNode createMockTicketNode() {
        return new TicketNode(
                "tick", "Tick", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                new HasWorktree.WorkTree("wt", null, new ArrayList<>()),
                0, 0, "agent", "", true, 0
        );
    }

    private ReviewNode createMockReviewNode() {
        return new ReviewNode(
                "review", "Review", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                "reviewed-1",
                "Review content",
                false,
                false,
                "",
                "human",
                Instant.now()
        );
    }

    private MergeNode createMockMergeNode() {
        return new MergeNode(
                "merge", "Merge", "goal", GraphNode.NodeStatus.RUNNING,
                null, new ArrayList<>(), new HashMap<>(),
                Instant.now(), Instant.now(),
                "", 0, 0
        );
    }
}
