package com.hayden.multiagentide.agent;

import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.ProcessOptions;
import com.hayden.multiagentide.agent.WorkflowGraphService;
import com.hayden.multiagentide.service.InterruptService;
import com.hayden.multiagentide.support.AgentTestBase;
import com.hayden.multiagentide.support.TestEventListener;
import com.hayden.multiagentidelib.acp.AcpChatModel;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.infrastructure.EventBus;
import com.hayden.multiagentidelib.model.nodes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

/**
 * Integration tests for WorkflowAgent that test complete workflow paths.
 * 
 * These tests:
 * 1. Mock AcpChatModel to return JSON responses that guide routing
 * 2. Run the real WorkflowAgent through AgentPlatform
 * 3. Verify the workflow executes correctly via:
 *    - WorkflowGraphService method calls (verify actions were invoked)
 *    - TestEventListener events
 *    - Final agent process status
 * 
 * The Embabel planner automatically routes between actions based on SomeOf return types.
 * We verify different workflow paths by controlling the AcpChatModel responses.
 */
@SpringBootTest
class WorkflowAgentIntegrationTest extends AgentTestBase {

    @Autowired
    private AgentPlatform agentPlatform;

    @MockitoBean
    private AcpChatModel acpChatModel;

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
        reset(acpChatModel, workflowGraphService, interruptService, eventBus);
        
        // Setup default mock returns for graph service
//        when(workflowGraphService.startOrchestrator(any())).thenReturn(createMockOrchestratorNode());
//        when(workflowGraphService.startOrchestratorCollector(any(), any())).thenReturn(createMockCollectorNode());
//        when(workflowGraphService.startDiscoveryOrchestrator(any(), any())).thenReturn(createMockDiscoveryOrchestratorNode());
//        when(workflowGraphService.startDiscoveryCollector(any(), any())).thenReturn(createMockDiscoveryCollectorNode());
//        when(workflowGraphService.startPlanningOrchestrator(any(), any())).thenReturn(createMockPlanningOrchestratorNode());
//        when(workflowGraphService.startPlanningCollector(any(), any())).thenReturn(createMockPlanningCollectorNode());
//        when(workflowGraphService.startTicketOrchestrator(any(), any())).thenReturn(createMockTicketOrchestratorNode());
//        when(workflowGraphService.startTicketCollector(any(), any())).thenReturn(createMockTicketCollectorNode());
//        when(workflowGraphService.requireDiscoveryOrchestrator(any())).thenReturn(createMockDiscoveryOrchestratorNode());
//        when(workflowGraphService.requirePlanningOrchestrator(any())).thenReturn(createMockPlanningOrchestratorNode());
//        when(workflowGraphService.requireTicketOrchestrator(any())).thenReturn(createMockTicketOrchestratorNode());
//        when(workflowGraphService.startDiscoveryAgent(any(), any(), any())).thenReturn(createMockDiscoveryNode());
//        when(workflowGraphService.startPlanningAgent(any(), any(), any())).thenReturn(createMockPlanningNode());
//        when(workflowGraphService.startTicketAgent(any(), any(), anyInt())).thenReturn(createMockTicketNode());
    }

//    @Nested
//    class CompleteWorkflowPaths {
//
//        @Test
//        void happyPath_discoveryThroughPlanningToCompletion() {
//            // Setup AcpChatModel to return specific JSON responses based on the prompts
//
//            // 1. coordinateWorkflow - routes to OrchestratorCollectorRequest
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Coordinate the following multi-agent workflow")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorRequest\": {\"goal\": \"Implement auth\", \"phase\": \"DISCOVERY\"}}"
//            ));
//
//            // 2. consolidateWorkflowOutputs - routes to DiscoveryOrchestratorRequest
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Consolidate the workflow results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": null, \"discoveryRequest\": {\"goal\": \"Implement auth\"}, \"planningRequest\": null, \"ticketRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            // 3. kickOffAnyNumberOfAgentsForCodeSearch - creates DiscoveryAgentRequests
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("divide up the discovery phase")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"agentRequests\": {\"requests\": [{\"goal\": \"Implement auth\", \"subdomainFocus\": \"Auth modules\"}]}, \"collectorRequest\": null}"
//            ));
//
//            // 4. Discovery sub-agent - returns DiscoveryAgentResult
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Discover and analyze the codebase")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"agentResult\": {\"output\": \"Found AuthService and UserService\"}}"
//            ));
//
//            // 5. dispatchDiscoveryAgentRequests - routes to DiscoveryCollectorRequest
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Dispatch the discovery workflow results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorRequest\": {\"goal\": \"Implement auth\", \"discoveryResults\": \"Findings: AuthService, UserService\"}}"
//            ));
//
//            // 6. consolidateDiscoveryFindings - routes to PlanningOrchestratorRequest
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Merge and consolidate the following discovery results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": null, \"orchestratorRequest\": null, \"discoveryRequest\": null, \"planningRequest\": {\"goal\": \"Implement auth\"}, \"ticketRequest\": null, \"contextRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            // 7. decomposePlanAndCreateWorkItems - creates PlanningAgentRequests
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Decompose the planning for the goal")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"agentRequests\": {\"requests\": [{\"goal\": \"Implement auth\"}]}, \"collectorRequest\": null}"
//            ));
//
//            // 8. Planning sub-agent - returns PlanningAgentResult
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Analyze the following goal and break it down")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"agentResult\": {\"output\": \"Plan: Add JWT tokens, Update UserService\"}}"
//            ));
//
//            // 9. dispatchPlanningAgentRequests - routes to PlanningCollectorRequest
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Dispatch the planning workflow results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"planningCollectorRequest\": {\"goal\": \"Implement auth\", \"planningResults\": \"Consolidated plan\"}}"
//            ));
//
//            // 10. consolidatePlansIntoTickets - returns result to complete (simplified for test)
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Merge and consolidate the following planning results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": {\"consolidatedOutput\": \"Tickets created\", \"collectorDecision\": {\"decisionType\": \"ADVANCE_PHASE\", \"rationale\": \"advance\", \"requestedPhase\": \"next\"}}, \"planningRequest\": null, \"discoveryOrchestratorRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            // 11. Back to OrchestratorCollectorRequest with final result
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Consolidate the workflow results") &&
//                prompt.getInstructions().get(0).getContent().contains("phase: next")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": {\"consolidatedOutput\": \"Workflow complete\", \"collectorDecision\": {\"decisionType\": \"ADVANCE_PHASE\", \"rationale\": \"done\", \"requestedPhase\": \"COMPLETE\"}}, \"discoveryRequest\": null, \"planningRequest\": null, \"ticketRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            // Act - Run the workflow
//            var result = agentPlatform.runAgentFrom(
//                    findWorkflowAgent(),
//                    ProcessOptions.builder()
//                            .withContextIdString("test-happy-path")
//                            .build(),
//                    Map.of("it", new AgentModels.OrchestratorRequest("Implement auth", "DISCOVERY"))
//            );
//
//            // Assert - Verify workflow executed correctly
//            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);
//
//            // Verify the actions were called in the correct order
//            verify(workflowGraphService).startOrchestrator(any());
//            verify(workflowGraphService).completeOrchestrator(any(),
//                    argThat((AgentModels.OrchestratorRouting r) -> r.collectorRequest() != null));
//
//            verify(workflowGraphService).startOrchestratorCollector(any(), any());
//            verify(workflowGraphService).completeOrchestratorCollector(any(), any(),
//                    argThat((AgentModels.OrchestratorCollectorRouting r) -> r.discoveryRequest() != null));
//
//            verify(workflowGraphService).startDiscoveryOrchestrator(any(), any());
//            verify(workflowGraphService).completeDiscoveryOrchestrator(any(),
//                    argThat((AgentModels.DiscoveryOrchestratorRouting r) -> r.agentRequests() != null));
//
//            verify(workflowGraphService).startDiscoveryAgent(any(), any(), any());
//            verify(workflowGraphService).completeDiscoveryAgent(any(), any());
//
//            verify(workflowGraphService).startDiscoveryCollector(any(), any());
//            verify(workflowGraphService).completeDiscoveryCollector(any(), any(),
//                    argThat((AgentModels.DiscoveryCollectorRouting r) -> r.planningRequest() != null));
//
//            verify(workflowGraphService).startPlanningOrchestrator(any(), any());
//            verify(workflowGraphService).completePlanningOrchestrator(any(), any());
//
//            verify(workflowGraphService).startPlanningAgent(any(), any(), any());
//            verify(workflowGraphService).completePlanningAgent(any(), any());
//
//            verify(workflowGraphService).startPlanningCollector(any(), any());
//            verify(workflowGraphService).completePlanningCollector(any(), any(), any());
//
//            // Verify final completion
//            verify(workflowGraphService, atLeast(1)).completeOrchestratorCollector(any(), any(),
//                    argThat((AgentModels.OrchestratorCollectorRouting r) ->
//                            r.collectorResult() != null &&
//                            r.collectorResult().consolidatedOutput().contains("complete")));
//
//            // Verify AcpChatModel was called the expected number of times
//            verify(acpChatModel, atLeast(10)).call(any(Prompt.class));
//        }
//
//        @Test
//        void skipDiscovery_directToPlanning() {
//            // Orchestrator routes directly to planning, skipping discovery
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Coordinate the following multi-agent workflow")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorRequest\": {\"goal\": \"Simple task\", \"phase\": \"PLANNING\"}}"
//            ));
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Consolidate the workflow results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": null, \"discoveryRequest\": null, \"planningRequest\": {\"goal\": \"Simple task\"}, \"ticketRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Decompose the planning")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"agentRequests\": {\"requests\": [{\"goal\": \"Simple task\"}]}, \"collectorRequest\": null}"
//            ));
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Analyze the following goal and break it down")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"agentResult\": {\"output\": \"Quick plan\"}}"
//            ));
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Dispatch the planning workflow")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"planningCollectorRequest\": {\"goal\": \"Simple task\", \"planningResults\": \"plan\"}}"
//            ));
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Merge and consolidate the following planning results")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": {\"consolidatedOutput\": \"Done\", \"collectorDecision\": {\"decisionType\": \"ADVANCE_PHASE\", \"rationale\": \"done\", \"requestedPhase\": \"COMPLETE\"}}, \"planningRequest\": null, \"discoveryOrchestratorRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Consolidate the workflow results") &&
//                prompt.getInstructions().get(0).getContent().contains("COMPLETE")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": null, \"collectorResult\": {\"consolidatedOutput\": \"Complete\", \"collectorDecision\": {\"decisionType\": \"ADVANCE_PHASE\", \"rationale\": \"done\", \"requestedPhase\": \"COMPLETE\"}}, \"discoveryRequest\": null, \"planningRequest\": null, \"ticketRequest\": null, \"reviewRequest\": null, \"mergerRequest\": null}"
//            ));
//
//            var result = agentPlatform.runAgentFrom(
//                    findWorkflowAgent(),
//                    ProcessOptions.builder().withContextIdString("test-skip-discovery").build(),
//                    Map.of("it", new AgentModels.OrchestratorRequest("Simple task", "PLANNING"))
//            );
//
//            assertThat(result.getStatus()).isEqualTo(com.embabel.agent.core.AgentProcessStatusCode.COMPLETED);
//
//            // Verify discovery was skipped
//            verify(workflowGraphService, never()).startDiscoveryOrchestrator(any(), any());
//
//            // Verify planning was executed
//            verify(workflowGraphService).startPlanningOrchestrator(any(), any());
//            verify(workflowGraphService).completePlanningOrchestrator(any(), any());
//        }
//    }
//
//    @Nested
//    class InterruptHandling {
//
//        @Test
//        void orchestratorPause_stopsWorkflow() {
//            // Orchestrator returns interrupt instead of routing
//            when(acpChatModel.call(argThat(prompt ->
//                prompt.getInstructions().get(0).getContent().contains("Coordinate the following multi-agent workflow")
//            ))).thenReturn(createChatResponse(
//                "{\"interruptRequest\": {\"type\": \"PAUSE\", \"reason\": \"User pause\"}, \"collectorRequest\": null}"
//            ));
//
//            var result = agentPlatform.runAgentFrom(
//                    findWorkflowAgent(),
//                    ProcessOptions.builder().withContextIdString("test-pause").build(),
//                    Map.of("it", new AgentModels.OrchestratorRequest("Paused task", "DISCOVERY"))
//            );
//
//            // Workflow should pause
//            assertThat(result.getStatus()).isIn(
//                    com.embabel.agent.core.AgentProcessStatusCode.WAITING,
//                    com.embabel.agent.core.AgentProcessStatusCode.PAUSED,
//                    com.embabel.agent.core.AgentProcessStatusCode.FAILED // Might fail due to unhandled interrupt
//            );
//
//            verify(workflowGraphService).handleOrchestratorInterrupt(any(),
//                    argThat((AgentModels.OrchestratorInterruptRequest r) ->
//                            r.type() == AgentModels.InterruptType.PAUSE));
//
//            // Should NOT proceed to discovery
//            verify(workflowGraphService, never()).startDiscoveryOrchestrator(any(), any());
//        }
//    }
//
//    // Helper methods
//    private com.embabel.agent.core.Agent findWorkflowAgent() {
//        return agentPlatform.agents().stream()
//                .filter(a -> a.getName().equals(AgentInterfaces.WORKFLOW_AGENT_NAME))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("WorkflowAgent not found"));
//    }
//
//    private ChatResponse createChatResponse(String jsonContent) {
//        return new ChatResponse(List.of(new Generation(new AssistantMessage(jsonContent))));
//    }
//
//    // Mock node creation helpers
//    private OrchestratorNode createMockOrchestratorNode() {
//        return new OrchestratorNode(
//                "orch-1", "Orch", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(),
//                new HasWorktree.WorkTree("wt", null, new ArrayList<>()),
//                0, 0, "agent", "", true, 0
//        );
//    }
//
//    private CollectorNode createMockCollectorNode() {
//        return new CollectorNode(
//                "coll-1", "Collector", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0
//        );
//    }
//
//    private DiscoveryOrchestratorNode createMockDiscoveryOrchestratorNode() {
//        return new DiscoveryOrchestratorNode(
//                "disc-orch", "DiscOrch", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0
//        );
//    }
//
//    private DiscoveryCollectorNode createMockDiscoveryCollectorNode() {
//        return new DiscoveryCollectorNode(
//                "disc-coll", "DiscColl", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0
//        );
//    }
//
//    private DiscoveryNode createMockDiscoveryNode() {
//        return new DiscoveryNode(
//                "disc", "Disc", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0, "agent", "", null, ""
//        );
//    }
//
//    private PlanningOrchestratorNode createMockPlanningOrchestratorNode() {
//        return new PlanningOrchestratorNode(
//                "plan-orch", "PlanOrch", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0
//        );
//    }
//
//    private PlanningCollectorNode createMockPlanningCollectorNode() {
//        return new PlanningCollectorNode(
//                "plan-coll", "PlanColl", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0
//        );
//    }
//
//    private PlanningNode createMockPlanningNode() {
//        return new PlanningNode(
//                "plan", "Plan", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0, "agent", "", null, ""
//        );
//    }
//
//    private TicketOrchestratorNode createMockTicketOrchestratorNode() {
//        return new TicketOrchestratorNode(
//                "tick-orch", "TickOrch", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(),
//                new HasWorktree.WorkTree("wt", null, new ArrayList<>()),
//                0, 0, "agent", "", true, 0
//        );
//    }
//
//    private TicketCollectorNode createMockTicketCollectorNode() {
//        return new TicketCollectorNode(
//                "tick-coll", "TickColl", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(), "", 0, 0
//        );
//    }
//
//    private TicketNode createMockTicketNode() {
//        return new TicketNode(
//                "tick", "Tick", "goal", GraphNode.NodeStatus.RUNNING,
//                null, new ArrayList<>(), new HashMap<>(),
//                Instant.now(), Instant.now(),
//                new HasWorktree.WorkTree("wt", null, new ArrayList<>()),
//                0, 0, "agent", "", true, 0
//        );
//    }
}
