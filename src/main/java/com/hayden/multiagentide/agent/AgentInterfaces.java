package com.hayden.multiagentide.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.ActionContext;
import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentide.service.InterruptService;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.BlackboardHistory;
import com.hayden.multiagentidelib.infrastructure.EventBus;
import com.hayden.multiagentidelib.model.events.Events;
import com.hayden.multiagentidelib.model.nodes.*;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Embabel @Agent definition for multi-agent IDE.
 * Single agent with all workflow actions.
 */
public interface AgentInterfaces {

    String WORKFLOW_DISCOVERY_DISPATCH_SUBAGENT = "WorkflowDiscoveryDispatchSubagent";
    String WORKFLOW_PLANNING_DISPATCH_SUBAGENT = "WorkflowPlanningDispatchSubagent";
    String WORKFLOW_TICKET_DISPATCH_SUBAGENT = "WorkflowTicketDispatchSubagent";
    String WORKFLOW_CONTEXT_DISPATCH_SUBAGENT = "WorkflowContextDispatchSubagent";

    String multiAgentAgentName();

    String WORKFLOW_AGENT_NAME = "WorkflowAgent";

    AgentInterfaces WORKFLOW_AGENT = () -> WORKFLOW_AGENT_NAME;
    AgentInterfaces ORCHESTRATOR_AGENT = WORKFLOW_AGENT;
    AgentInterfaces DISCOVERY_ORCHESTRATOR_AGENT = WORKFLOW_AGENT;
    AgentInterfaces PLANNING_ORCHESTRATOR_AGENT = WORKFLOW_AGENT;
    AgentInterfaces TICKET_ORCHESTRATOR_AGENT = WORKFLOW_AGENT;
    AgentInterfaces REVIEW_AGENT = WORKFLOW_AGENT;
    AgentInterfaces MERGER_AGENT = WORKFLOW_AGENT;
    AgentInterfaces CONTEXT_ORCHESTRATOR_AGENT = WORKFLOW_AGENT;

    static String renderTemplate(String template, Map<String, String> values) {
        String rendered = template;
        for (var entry : values.entrySet()) {
            rendered = rendered.replace(
                    "{{" + entry.getKey() + "}}",
                    Objects.toString(entry.getValue(), "")
            );
        }
        return rendered;
    }

    static void emitActionStarted(EventBus eventBus, String agentName, String actionName, OperationContext context) {
        if (eventBus == null) {
            return;
        }
        String nodeId = resolveNodeId(context);
        eventBus.publish(new Events.ActionStartedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                agentName,
                actionName
        ));
    }

    static void emitActionCompleted(EventBus eventBus, String agentName, String actionName, OperationContext context, Object result) {
        if (eventBus == null) {
            return;
        }
        String nodeId = resolveNodeId(context);
        String outcomeType = result != null ? result.getClass().getSimpleName() : "null";
        eventBus.publish(new Events.ActionCompletedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                agentName,
                actionName,
                outcomeType
        ));
    }

    static String resolveNodeId(OperationContext context) {
        if (context == null || context.getProcessContext() == null) {
            return "unknown";
        }
        var options = context.getProcessContext().getProcessOptions();
        if (options == null) {
            return "unknown";
        }
        String contextId = options.getContextIdString();
        return contextId != null ? contextId : "unknown";
    }

    static String renderReturnRoute(
            AgentModels.OrchestratorCollectorRequest orchestratorCollector,
            AgentModels.DiscoveryCollectorRequest discoveryCollector,
            AgentModels.PlanningCollectorRequest planningCollector,
            AgentModels.TicketCollectorRequest ticketCollector
    ) {
        if (orchestratorCollector != null) {
            return "orchestratorCollectorRequest(goal=" + orchestratorCollector.goal() + ", phase=" + orchestratorCollector.phase() + ")";
        }
        if (discoveryCollector != null) {
            return "discoveryCollectorRequest(goal=" + discoveryCollector.goal() + ")";
        }
        if (planningCollector != null) {
            return "planningCollectorRequest(goal=" + planningCollector.goal() + ")";
        }
        if (ticketCollector != null) {
            return "ticketCollectorRequest(goal=" + ticketCollector.goal() + ")";
        }
        return "none";
    }

    /**
     * Register an input in the blackboard history and hide it from the blackboard.
     * This should be called at the start of each action instead of using clearBlackboard=true.
     *
     * @param context The operation context
     * @param actionName The name of the action being executed
     * @param input The input object to register and hide
     * @return Updated history with the new entry
     */
    static BlackboardHistory.History registerAndHideInput(
            OperationContext context,
            String actionName,
            Object input
    ) {
        // Get or create history from context
        BlackboardHistory.History history = context.last(BlackboardHistory.History.class);
        if (history == null) {
            history = new BlackboardHistory.History();
        }

        // Add the input to history
        BlackboardHistory.History updatedHistory = history.withEntry(actionName, input);

        // Add updated history back to context
        context.addObject(updatedHistory);

        // Hide the input from the blackboard using context.hide()
        if (input != null) {
            context.hide(input);
        }

        return updatedHistory;
    }

    /**
     * Get a prompt provider for a specific input type based on history.
     * This can be used to augment prompts with retry/loop context.
     *
     * @param context The operation context
     * @param inputType The class of the input type
     * @return A PromptProvider that can augment prompts with historical context
     */
    static <T> BlackboardHistory.PromptProvider getPromptProvider(
            OperationContext context,
            Class<T> inputType
    ) {
        BlackboardHistory.History history = context.last(BlackboardHistory.History.class);
        if (history == null) {
            return BlackboardHistory.PromptProvider.identity();
        }
        return history.generatePromptProvider(inputType);
    }

    /**
     * Check if we're retrying a specific action.
     *
     * @param context The operation context
     * @param actionName The action name to check
     * @return true if this action has been attempted before
     */
    static boolean isRetry(OperationContext context, String actionName) {
        BlackboardHistory.History history = context.last(BlackboardHistory.History.class);
        return history != null && history.isRetry(actionName);
    }

    /**
     * Check if we're in a loop for a specific input type.
     *
     * @param context The operation context
     * @param inputType The input type to check
     * @param threshold The number of occurrences that indicates a loop
     * @return true if we've seen this input type threshold or more times
     */
    static boolean detectLoop(OperationContext context, Class<?> inputType, int threshold) {
        BlackboardHistory.History history = context.last(BlackboardHistory.History.class);
        return history != null && history.detectLoop(inputType, threshold);
    }

    @Agent(name = WORKFLOW_AGENT_NAME, description = "Coordinates multi-agent workflow")
    @RequiredArgsConstructor
    class WorkflowAgent implements AgentInterfaces {

        private final EventBus eventBus;
        private final WorkflowGraphService workflowGraphService;
        private final InterruptService interruptService;

        @Override
        public String multiAgentAgentName() {
            return WORKFLOW_AGENT_NAME;
        }

        public static final String ORCHESTRATOR_AGENT_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}
                Current phase: {{phase}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorRequest: { goal, phase }
                """;

        public static final String ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE = """
                Consolidate the workflow results and provide a routing decision.

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorResult: { consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase } }
                - orchestratorRequest: { goal, phase }
                - discoveryRequest: { goal }
                - discoveryState: { request: { goal } }
                - planningRequest: { goal }
                - ticketRequest: { goal, tickets, discoveryContext, planningContext }
                - contextRequest: { goal, phase }
                - reviewRequest: { content, criteria, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                - mergerRequest: { mergeContext, mergeSummary, conflictFiles, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                """;

        public static final String DISCOVERY_ORCHESTRATOR_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}

                Based on this information and files you find on the repository, or information about the ticket,
                decide how to divide up the discovery phase of the workflow.

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentRequests: { requests: [ { goal, subdomainFocus } ] }
                - collectorRequest: { goal, discoveryResults }
                """;

        public static final String DISCOVERY_AGENT_START_MESSAGE = """
                Discover and analyze the codebase for the following subdomain:

                Overall Goal: {{goal}}
                Subdomain Focus: {{subdomainFocus}}

                Use your tools to:
                1. Search for relevant files and modules
                2. Analyze key source files
                3. Understand dependencies and imports
                4. Identify architectural patterns
                5. Document test patterns

                Generate comprehensive discovery findings including:
                - Module overview
                - Key classes and responsibilities
                - Data flow patterns
                - Integration points
                - Technology stack
                - Test patterns

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentResult: { output }
                - interruptState: { request: { type, reason } }
                """;

        public static final String DISCOVERY_COLLECTOR_START_MESSAGE = """
                Merge and consolidate the following discovery results from multiple agents:

                Goal: {{goal}}
                Discovery Results: {{discoveryResults}}

                Create a unified discovery document with:
                - Architecture overview
                - Key modules and components
                - Data flow and dependencies
                - Integration points
                - Technology stack summary
                - Test patterns and conventions
                - Critical files and entry points

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorResult: { consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase } }
                - orchestratorRequest: { goal, phase }
                - discoveryRequest: { goal }
                - planningRequest: { goal }
                - planningState: { request: { goal } }
                - ticketRequest: { goal, tickets, discoveryContext, planningContext }
                - contextRequest: { goal, phase }
                - reviewRequest: { content, criteria, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                - mergerRequest: { mergeContext, mergeSummary, conflictFiles, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                """;

        public static final String DISCOVERY_DISPATCH_MESSAGE = """
                Dispatch the discovery workflow results to the next step.

                Goal: {{goal}}
                Discovery Results: {{discoveryResults}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorRequest: { goal, discoveryResults }
                """;

        public static final String PLANNING_ORCHESTRATOR_MESSAGE = """
                Decompose the planning for the goal according to the results from discovery.
                Define tickets and update the spec file in .specify/.../spec.md.

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentRequests: { requests: [ { goal } ] }
                - collectorRequest: { goal, planningResults }

                Goal: {{goal}}
                """;

        public static final String PLANNING_AGENT_USER_MESSAGE = """
                Analyze the following goal and break it down into 3 work items:
                1. Architecture & Setup - Design foundational structure
                2. Implementation - Core functionality
                3. Testing & Validation - Tests and validation

                Goal: {{goal}}

                Provide a structured plan with clear sections for each work item.

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentResult: { output }
                - interruptState: { request: { type, reason } }
                """;

        public static final String PLANNING_COLLECTOR_MESSAGE = """
                Merge and consolidate the following planning results from multiple agents:

                Goal: {{goal}}
                Planning Results: {{planningResults}}

                Create structured tickets with:
                - Ticket ID and title
                - Clear implementation tasks
                - Dependencies between tickets
                - Acceptance criteria
                - Estimated effort

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorResult: { consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase } }
                - orchestratorRequest: { goal, phase }
                - discoveryRequest: { goal }
                - planningRequest: { goal }
                - ticketRequest: { goal, tickets, discoveryContext, planningContext }
                - ticketState: { request: { goal, tickets, discoveryContext, planningContext } }
                - contextRequest: { goal, phase }
                - reviewRequest: { content, criteria, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                - mergerRequest: { mergeContext, mergeSummary, conflictFiles, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                """;

        public static final String PLANNING_DISPATCH_MESSAGE = """
                Dispatch the planning workflow results to the next step.

                Goal: {{goal}}
                Planning Results: {{planningResults}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - planningCollectorRequest: { goal, planningResults }
                """;

        public static final String TICKET_ORCHESTRATOR_START_MESSAGE = """
                Orchestrate ticket-based implementation for the following tickets:

                Goal: {{goal}}
                Tickets: {{tickets}}
                Discovery Context: {{discoveryContext}}
                Planning Context: {{planningContext}}

                Coordinate execution of each ticket:
                1. Determine ticket execution order and dependencies
                2. Create ticket work details for each ticket
                3. Prepare worktree context for ticket agents
                4. Monitor ticket completion and quality

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentRequests: { requests: [ { ticketDetails, ticketDetailsFilePath, discoveryContext, planningContext } ] }
                - collectorRequest: { goal, ticketResults }
                """;

        public static final String TICKET_AGENT_START_MESSAGE = """
                Implement the following ticket with complete, production-ready code:

                Ticket Details: {{ticketDetails}}
                Ticket File Path: {{ticketDetailsFilePath}}

                Discovery Context: {{discoveryContext}}
                Planning Context: {{planningContext}}

                Implementation requirements:
                1. Read ticket details from the provided file path
                2. Analyze discovery and planning context
                3. Use tools to read existing code and understand patterns
                4. Generate complete implementation
                5. Create/modify files in the worktree
                6. Run tests in the worktree
                7. Commit implementation to feature branch

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentResult: { output }
                - interruptState: { request: { type, reason } }
                """;

        public static final String TICKET_DISPATCH_MESSAGE = """
                Dispatch the ticket workflow results to the next step.

                Goal: {{goal}}
                Ticket Results: {{ticketResults}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - ticketCollectorRequest: { goal, ticketResults }
                """;

        public static final String TICKET_COLLECTOR_START_MESSAGE = """
                Merge and consolidate the following ticket execution results:

                Goal: {{goal}}
                Ticket Results: {{ticketResults}}

                Produce a summary with:
                - Completed tickets
                - Failed tickets (with brief reasons)
                - Outstanding follow-ups

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorResult: { consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase } }
                - orchestratorRequest: { goal, phase }
                - orchestratorState: { request: { goal, phase } }
                - discoveryRequest: { goal }
                - planningRequest: { goal }
                - ticketRequest: { goal, tickets, discoveryContext, planningContext }
                - contextRequest: { goal, phase }
                - reviewRequest: { content, criteria, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                - mergerRequest: { mergeContext, mergeSummary, conflictFiles, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                """;

        public static final String REVIEW_AGENT_START_MESSAGE = """
                Review the following content against these criteria:

                Content:
                {{content}}

                Criteria: {{criteria}}

                Return route: {{returnRoute}}

                Provide evaluation with:
                - Overall assessment (APPROVED/NEEDS_REVISION)
                - Specific feedback on quality
                - Suggestions for improvement

                Return a routing object with:
                - interruptRequest: { type, reason }
                - reviewResult: { output }
                - one return route matching the provided returnTo*Collector field:
                  orchestratorCollectorRequest | discoveryCollectorRequest | planningCollectorRequest |
                  ticketCollectorRequest | contextCollectorRequest
                """;

        public static final String MERGER_AGENT_START_MESSAGE = """
                Review the merge outcome and validate it is correct.

                Merge context:
                {{mergeContext}}

                Merge summary:
                {{mergeSummary}}

                Conflicting files:
                {{conflictFiles}}

                Return route: {{returnRoute}}

                Confirm whether the merge is acceptable. If conflicts exist, outline resolution guidance.

                Return a routing object with:
                - interruptRequest: { type, reason }
                - mergerResult: { output }
                - one return route matching the provided returnTo*Collector field:
                  orchestratorCollectorRequest | discoveryCollectorRequest | planningCollectorRequest |
                  ticketCollectorRequest | contextCollectorRequest
                """;

        public static final String CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE = """
                Coordinate context operations for the workflow.

                Goal: {{goal}}
                Phase: {{phase}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentRequests: { requests: [ { goal, phase } ] }
                - collectorRequest: { goal, phase }
                """;

        public static final String CONTEXT_AGENT_START_MESSAGE = """
                Apply context operations for the workflow.

                Goal: {{goal}}
                Phase: {{phase}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - agentResult: { output }
                """;

        public static final String CONTEXT_COLLECTOR_START_MESSAGE = """
                Consolidate context operations into a single context.

                Goal: {{goal}}
                Phase: {{phase}}

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorResult: { consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase } }
                - orchestratorRequest: { goal, phase }
                - discoveryRequest: { goal }
                - planningRequest: { goal }
                - ticketRequest: { goal, tickets, discoveryContext, planningContext }
                - contextRequest: { goal, phase }
                - reviewRequest: { content, criteria, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                - mergerRequest: { mergeContext, mergeSummary, conflictFiles, returnToOrchestratorCollector?, returnToDiscoveryCollector?, returnToPlanningCollector?, returnToTicketCollector?, returnToContextCollector? }
                """;

        @Action
        @AchievesGoal(description = "Finished orchestrator collector")
        public AgentModels.OrchestratorCollectorResult finalCollectorResult(
                AgentModels.OrchestratorCollectorResult input,
                OperationContext context
        ) {
            workflowGraphService.completeOrchestratorCollectorResult(context, input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-collector", context, "finished");
            return input;
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorCollectorRouting consolidateWorkflowOutputs(
                AgentModels.OrchestratorCollectorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "consolidateWorkflowOutputs", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-collector", context);
            CollectorNode running = workflowGraphService.startOrchestratorCollector(context, input);
            String prompt = renderTemplate(
                    ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            AgentModels.OrchestratorCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.OrchestratorCollectorRouting.class);
            workflowGraphService.completeOrchestratorCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-collector", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.DiscoveryCollectorRouting consolidateDiscoveryFindings(
                AgentModels.DiscoveryCollectorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "consolidateDiscoveryFindings", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-collector", context);
            DiscoveryCollectorNode running = workflowGraphService.startDiscoveryCollector(context, input);
            String prompt = renderTemplate(
                    DISCOVERY_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "discoveryResults", input.discoveryResults())
            );
            AgentModels.DiscoveryCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryCollectorRouting.class);
            workflowGraphService.completeDiscoveryCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-collector", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.PlanningCollectorRouting consolidatePlansIntoTickets(
                AgentModels.PlanningCollectorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "consolidatePlansIntoTickets", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-collector", context);
            PlanningCollectorNode running = workflowGraphService.startPlanningCollector(context, input);
            String prompt = renderTemplate(
                    PLANNING_COLLECTOR_MESSAGE,
                    Map.of("goal", input.goal(), "planningResults", input.planningResults())
            );
            AgentModels.PlanningCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.PlanningCollectorRouting.class);
            workflowGraphService.completePlanningCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-collector", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.TicketCollectorRouting consolidateTicketResults(
                AgentModels.TicketCollectorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "consolidateTicketResults", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-collector", context);
            TicketCollectorNode running = workflowGraphService.startTicketCollector(context, input);
            String prompt = renderTemplate(
                    TICKET_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "ticketResults", input.ticketResults())
            );
            AgentModels.TicketCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.TicketCollectorRouting.class);
            workflowGraphService.completeTicketCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-collector", context, routing);

            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorRouting coordinateWorkflow(
                AgentModels.OrchestratorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "coordinateWorkflow", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator", context);
            OrchestratorNode running = workflowGraphService.startOrchestrator(context);
            String prompt = renderTemplate(
                    ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            AgentModels.OrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.OrchestratorRouting.class);
            workflowGraphService.completeOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator", context, routing);
            return routing;
        }


        @Action(canRerun = true, cost = 1)
        public AgentModels.OrchestratorRouting handleOrchestratorInterrupt(
                AgentModels.OrchestratorInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleOrchestratorInterrupt", request);
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context);
            workflowGraphService.handleOrchestratorInterrupt(context, request);
            OrchestratorNode originNode = workflowGraphService.requireOrchestrator(context);
            AgentModels.OrchestratorRequest lastRequest =
                    context.last(AgentModels.OrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Missing orchestrator request while handling interrupt."
                );
                AgentModels.OrchestratorRouting routing = new AgentModels.OrchestratorRouting(
                        request,
                        null
                );
                emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context, routing);
                return routing;
            }
            var resumed = interruptService.handleReviewInterrupt(
                    context,
                    request,
                    originNode,
                    () -> renderTemplate(
                            ORCHESTRATOR_AGENT_START_MESSAGE,
                            Map.of("goal", lastRequest.goal(), "phase", lastRequest.phase())
                    ),
                    AgentModels.OrchestratorRouting.class,
                    REVIEW_AGENT_START_MESSAGE
            );
            if (resumed.isPresent()) {
                AgentModels.OrchestratorRouting routing = resumed.get();
                emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context, routing);
                return routing;
            }
            AgentModels.OrchestratorRouting routing = new AgentModels.OrchestratorRouting(
                    request,
                    null
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context, routing);
            return routing;
        }


        @Action(canRerun = true)
        public AgentModels.DiscoveryOrchestratorRouting kickOffAnyNumberOfAgentsForCodeSearch(
                AgentModels.DiscoveryOrchestratorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "kickOffAnyNumberOfAgentsForCodeSearch", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context);
            DiscoveryOrchestratorNode running = workflowGraphService.startDiscoveryOrchestrator(context, input);
            String prompt = renderTemplate(
                    DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                    Map.of("goal", input.goal())
            );
            AgentModels.DiscoveryOrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryOrchestratorRouting.class);
            workflowGraphService.completeDiscoveryOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context, routing);
            return routing;
        }


        @Action(canRerun = true)
        public AgentModels.DiscoveryAgentDispatchRouting dispatchDiscoveryAgentRequests(
                AgentModels.DiscoveryAgentRequests input,
                ActionContext context
        ) {
            registerAndHideInput(context, "dispatchDiscoveryAgentRequests", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-dispatch", context);
            List<AgentModels.DiscoveryAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null || requests.isEmpty()) {
                String goal = resolveDiscoveryGoal(context, List.of());
                requests = List.of(new AgentModels.DiscoveryAgentRequest(goal, "Repository overview"));
            }
            String goal = resolveDiscoveryGoal(context, requests);
            StringBuilder results = new StringBuilder();
            DiscoveryOrchestratorNode discoveryParent = workflowGraphService.requireDiscoveryOrchestrator(context);
            var discoveryDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_DISCOVERY_DISPATCH_SUBAGENT))
                    .findAny().orElse(null);
            for (AgentModels.DiscoveryAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                String focus = firstNonBlank(request.subdomainFocus(), "Discovery");
                DiscoveryNode running = workflowGraphService.startDiscoveryAgent(
                        discoveryParent,
                        goal,
                        focus
                );
                AgentModels.DiscoveryAgentRouting response = runSubProcess(
                        context,
                        request,
                        discoveryDispatchAgent,
                        AgentModels.DiscoveryAgentRouting.class
                );
                AgentModels.DiscoveryAgentResult agentResult = response != null ? response.agentResult() : null;
                workflowGraphService.completeDiscoveryAgent(running, agentResult);
                if (agentResult != null) {
                    appendSection(
                            results,
                            focus,
                            agentResult.output()
                    );
                }
            }
            AgentModels.DiscoveryCollectorRequest collectorRequest =
                    new AgentModels.DiscoveryCollectorRequest(goal, results.toString().trim());
            context.addObject(collectorRequest);
            String prompt = renderTemplate(
                    DISCOVERY_DISPATCH_MESSAGE,
                    Map.of("goal", collectorRequest.goal(), "discoveryResults", collectorRequest.discoveryResults())
            );
            AgentModels.DiscoveryAgentDispatchRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryAgentDispatchRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-dispatch", context, routing);
            return routing;
        }

//        AfterInterrupted ... for each, so these will be to add the context from the interruption and contain the original request
//        BackTo ... for each item you can jump back to, adds the additional context, removes the inner request, then, when you go
//                  back through the BackTo, then it can add the inner request back to the blackboard -- ContinuationWrapper, which
//                  can then accept the blackboard as a sort of visitor idea after and before, and based on it's inner, it operates
//                  specifically over the blackboard to remove, and then it saves those items, and then can be smart about adding them
//                  back - and there's one per - so specific to each, or one and case class over (maybe in a fn instead of visitor in one
//                  place).

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.DiscoveryOrchestratorRouting handleDiscoveryInterrupt(
                AgentModels.DiscoveryOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleDiscoveryInterrupt", request);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-interrupt", context);
            workflowGraphService.handleDiscoveryInterrupt(context, request);
            DiscoveryOrchestratorNode originNode = workflowGraphService.requireDiscoveryOrchestrator(context);
            AgentModels.DiscoveryOrchestratorRequest lastRequest =
                    context.last(AgentModels.DiscoveryOrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Missing discovery orchestrator request while handling interrupt."
                );
                AgentModels.DiscoveryOrchestratorRouting routing =
                        new AgentModels.DiscoveryOrchestratorRouting(
                                request,
                                null,
                                null
                        );
                emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-interrupt", context, routing);
                return routing;
            }
            var resumed = interruptService.handleReviewInterrupt(
                    context,
                    request,
                    originNode,
                    () -> renderTemplate(
                            DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                            Map.of("goal", lastRequest.goal())
                    ),
                    AgentModels.DiscoveryOrchestratorRouting.class,
                    REVIEW_AGENT_START_MESSAGE
            );
            if (resumed.isPresent()) {
                AgentModels.DiscoveryOrchestratorRouting routing = resumed.get();
                emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-interrupt", context, routing);
                return routing;
            }
            AgentModels.DiscoveryOrchestratorRouting routing =
                    new AgentModels.DiscoveryOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-interrupt", context, routing);
            return routing;
        }

//        add a conversation data model, add the stuff from the process into this, then clear everything else from the blackboard
//        add more args for finer matching
//        only clear blackboard when going backwards, then remove only items previous and add those to conversation
        @Action(canRerun = true)
        public AgentModels.PlanningOrchestratorRouting decomposePlanAndCreateWorkItems(
                AgentModels.PlanningOrchestratorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "decomposePlanAndCreateWorkItems", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-orchestrator", context);
            PlanningOrchestratorNode running = workflowGraphService.startPlanningOrchestrator(context, input);
            String prompt = renderTemplate(
                    PLANNING_ORCHESTRATOR_MESSAGE,
                    Map.of("goal", input.goal())
            );
            AgentModels.PlanningOrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.PlanningOrchestratorRouting.class);
            workflowGraphService.completePlanningOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-orchestrator", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.PlanningAgentDispatchRouting dispatchPlanningAgentRequests(
                AgentModels.PlanningAgentRequests input,
                ActionContext context
        ) {
            registerAndHideInput(context, "dispatchPlanningAgentRequests", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-dispatch", context);
            List<AgentModels.PlanningAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolvePlanningGoal(context, requests);
            StringBuilder results = new StringBuilder();
            int index = 0;
            PlanningOrchestratorNode planningParent = workflowGraphService.requirePlanningOrchestrator(context);
            var planningDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_PLANNING_DISPATCH_SUBAGENT))
                    .findAny().orElse(null);

            for (AgentModels.PlanningAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                index++;
                String title = "Plan segment " + index;
                PlanningNode running = workflowGraphService.startPlanningAgent(
                        planningParent,
                        goal,
                        title
                );
                AgentModels.PlanningAgentRouting response = runSubProcess(
                        context,
                        request,
                        planningDispatchAgent,
                        AgentModels.PlanningAgentRouting.class
                );
                AgentModels.PlanningAgentResult agentResult = response != null ? response.agentResult() : null;
                workflowGraphService.completePlanningAgent(running, agentResult);
                if (agentResult != null) {
                    appendSection(
                            results,
                            title,
                            agentResult.output()
                    );
                }
            }
            AgentModels.PlanningCollectorRequest collectorRequest =
                    new AgentModels.PlanningCollectorRequest(goal, results.toString().trim());
            context.addObject(collectorRequest);
            String prompt = renderTemplate(
                    PLANNING_DISPATCH_MESSAGE,
                    Map.of("goal", collectorRequest.goal(), "planningResults", collectorRequest.planningResults())
            );
            AgentModels.PlanningAgentDispatchRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.PlanningAgentDispatchRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-dispatch", context, routing);
            return routing;
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.PlanningOrchestratorRouting handlePlanningInterrupt(
                AgentModels.PlanningOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handlePlanningInterrupt", request);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-interrupt", context);
            workflowGraphService.handlePlanningInterrupt(context, request);
            PlanningOrchestratorNode originNode = workflowGraphService.requirePlanningOrchestrator(context);
            AgentModels.PlanningOrchestratorRequest lastRequest =
                    context.last(AgentModels.PlanningOrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Missing planning orchestrator request while handling interrupt."
                );
                AgentModels.PlanningOrchestratorRouting routing =
                        new AgentModels.PlanningOrchestratorRouting(
                                request,
                                null,
                                null
                        );
                emitActionCompleted(eventBus, multiAgentAgentName(), "planning-interrupt", context, routing);
                return routing;
            }
            var resumed = interruptService.handleReviewInterrupt(
                    context,
                    request,
                    originNode,
                    () -> renderTemplate(
                            PLANNING_ORCHESTRATOR_MESSAGE,
                            Map.of("goal", lastRequest.goal())
                    ),
                    AgentModels.PlanningOrchestratorRouting.class,
                    REVIEW_AGENT_START_MESSAGE
            );
            if (resumed.isPresent()) {
                AgentModels.PlanningOrchestratorRouting routing = resumed.get();
                emitActionCompleted(eventBus, multiAgentAgentName(), "planning-interrupt", context, routing);
                return routing;
            }
            AgentModels.PlanningOrchestratorRouting routing =
                    new AgentModels.PlanningOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-interrupt", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorCollectorResult finalizeTicketOrchestrator(
                AgentModels.TicketOrchestratorResult input,
                OperationContext context
        ) {
            registerAndHideInput(context, "finalizeTicketOrchestrator", input);
            return new AgentModels.OrchestratorCollectorResult(
                    input.output(),
                    new AgentModels.CollectorDecision(AgentModels.CollectorDecisionType.ADVANCE_PHASE, "", ""));
        }

        @Action(canRerun = true)
        public AgentModels.TicketOrchestratorRouting orchestrateTicketExecution(
                AgentModels.TicketOrchestratorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "orchestrateTicketExecution", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context);
            TicketOrchestratorNode running = workflowGraphService.startTicketOrchestrator(context, input);
            String prompt = renderTemplate(
                    TICKET_ORCHESTRATOR_START_MESSAGE,
                    Map.of(
                            "goal", input.goal(),
                            "tickets", input.tickets(),
                            "discoveryContext", input.discoveryContext(),
                            "planningContext", input.planningContext()
                    )
            );
            AgentModels.TicketOrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.TicketOrchestratorRouting.class);
            workflowGraphService.completeTicketOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.TicketAgentDispatchRouting dispatchTicketAgentRequests(
                AgentModels.TicketAgentRequests input,
                ActionContext context
        ) {
            registerAndHideInput(context, "dispatchTicketAgentRequests", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-dispatch", context);
            List<AgentModels.TicketAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolveTicketGoal(context);
            StringBuilder results = new StringBuilder();
            int index = 0;
            TicketOrchestratorNode ticketParent = workflowGraphService.requireTicketOrchestrator(context);
            var ticketDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_TICKET_DISPATCH_SUBAGENT))
                    .findAny().orElse(null);
            for (AgentModels.TicketAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                index++;
                String heading = firstNonBlank(request.ticketDetailsFilePath(), "Ticket " + index);
                TicketNode running = workflowGraphService.startTicketAgent(
                        ticketParent,
                        request,
                        index
                );
                AgentModels.TicketAgentRouting response = runSubProcess(
                        context,
                        request,
                        ticketDispatchAgent,
                        AgentModels.TicketAgentRouting.class
                );
                AgentModels.TicketAgentResult agentResult = response != null ? response.agentResult() : null;
                workflowGraphService.completeTicketAgent(running, agentResult);
                if (agentResult != null) {
                    appendSection(results, heading, agentResult.output());
                }
            }
            AgentModels.TicketCollectorRequest collectorRequest =
                    new AgentModels.TicketCollectorRequest(goal, results.toString().trim());
            context.addObject(collectorRequest);
            String prompt = renderTemplate(
                    TICKET_DISPATCH_MESSAGE,
                    Map.of("goal", collectorRequest.goal(), "ticketResults", collectorRequest.ticketResults())
            );
            AgentModels.TicketAgentDispatchRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.TicketAgentDispatchRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-dispatch", context, routing);
            return routing;
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.TicketOrchestratorRouting handleTicketInterrupt(
                AgentModels.TicketOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleTicketInterrupt", request);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-interrupt", context);
            workflowGraphService.handleTicketInterrupt(context, request);
            TicketOrchestratorNode originNode = workflowGraphService.requireTicketOrchestrator(context);
            AgentModels.TicketOrchestratorRequest lastRequest =
                    context.last(AgentModels.TicketOrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Missing ticket orchestrator request while handling interrupt."
                );
                AgentModels.TicketOrchestratorRouting routing =
                        new AgentModels.TicketOrchestratorRouting(
                                request,
                                null,
                                null
                        );
                emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-interrupt", context, routing);
                return routing;
            }
            var resumed = interruptService.handleReviewInterrupt(
                    context,
                    request,
                    originNode,
                    () -> renderTemplate(
                            TICKET_ORCHESTRATOR_START_MESSAGE,
                            Map.of(
                                    "goal", lastRequest.goal(),
                                    "tickets", lastRequest.tickets(),
                                    "discoveryContext", lastRequest.discoveryContext(),
                                    "planningContext", lastRequest.planningContext()
                            )
                    ),
                    AgentModels.TicketOrchestratorRouting.class,
                    REVIEW_AGENT_START_MESSAGE
            );
            if (resumed.isPresent()) {
                AgentModels.TicketOrchestratorRouting routing = resumed.get();
                emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-interrupt", context, routing);
                return routing;
            }
            AgentModels.TicketOrchestratorRouting routing =
                    new AgentModels.TicketOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-interrupt", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.ReviewRouting evaluateContent(
                AgentModels.ReviewRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "evaluateContent", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "review-agent", context);
            ReviewNode running = workflowGraphService.startReview(context, input);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector()
            );
            String prompt = renderTemplate(
                    REVIEW_AGENT_START_MESSAGE,
                    Map.of(
                            "content", input.content(),
                            "criteria", input.criteria(),
                            "returnRoute", returnRoute
                    )
            );
            AgentModels.ReviewRouting response = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.ReviewRouting.class);
            workflowGraphService.completeReview(running, response);
            boolean interrupted = response != null && response.interruptRequest() != null;
            AgentModels.ReviewRouting routing = new AgentModels.ReviewRouting(
                    response != null ? response.interruptRequest() : null,
                    response != null ? response.reviewResult() : null,
                    interrupted ? null : input.returnToOrchestratorCollector(),
                    interrupted ? null : input.returnToDiscoveryCollector(),
                    interrupted ? null : input.returnToPlanningCollector(),
                    interrupted ? null : input.returnToTicketCollector()
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "review-agent", context, routing);
            return routing;
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.ReviewRouting handleReviewInterrupt(
                AgentModels.ReviewInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleReviewInterrupt", request);
            emitActionStarted(eventBus, multiAgentAgentName(), "review-interrupt", context);
            workflowGraphService.handleReviewInterrupt(context, request);
            ReviewNode originNode = workflowGraphService.requireReviewNode(context);
            AgentModels.ReviewRequest lastRequest =
                    context.last(AgentModels.ReviewRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Missing review request while handling interrupt."
                );
                AgentModels.ReviewRouting routing =
                        new AgentModels.ReviewRouting(
                                request,
                                null,
                                null,
                                null,
                                null,
                                null
                        );
                emitActionCompleted(eventBus, multiAgentAgentName(), "review-interrupt", context, routing);
                return routing;
            }
            var resumed = interruptService.handleReviewInterrupt(
                    context,
                    request,
                    originNode,
                    () -> {
                        String returnRoute = renderReturnRoute(
                                lastRequest.returnToOrchestratorCollector(),
                                lastRequest.returnToDiscoveryCollector(),
                                lastRequest.returnToPlanningCollector(),
                                lastRequest.returnToTicketCollector()
                        );
                        return renderTemplate(
                                REVIEW_AGENT_START_MESSAGE,
                                Map.of(
                                        "content", lastRequest.content(),
                                        "criteria", lastRequest.criteria(),
                                        "returnRoute", returnRoute
                                )
                        );
                    },
                    AgentModels.ReviewRouting.class,
                    REVIEW_AGENT_START_MESSAGE
            );
            if (resumed.isPresent()) {
                AgentModels.ReviewRouting routing = resumed.get();
                workflowGraphService.completeReview(originNode, routing);
                emitActionCompleted(eventBus, multiAgentAgentName(), "review-interrupt", context, routing);
                return routing;
            }
            AgentModels.ReviewRouting routing =
                    new AgentModels.ReviewRouting(
                            request,
                            null,
                            null,
                            null,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "review-interrupt", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.MergerRouting performMerge(
                AgentModels.MergerRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "performMerge", input);
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-agent", context);
            MergeNode running = workflowGraphService.startMerge(context, input);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector()
            );
            String prompt = renderTemplate(
                    MERGER_AGENT_START_MESSAGE,
                    Map.of(
                            "mergeContext", input.mergeContext(),
                            "mergeSummary", input.mergeSummary(),
                            "conflictFiles", input.conflictFiles(),
                            "returnRoute", returnRoute
                    )
            );
            AgentModels.MergerRouting response = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.MergerRouting.class);
            AgentModels.MergerAgentResult mergeResult = response != null ? response.mergerResult() : null;
            String mergeOutput = mergeResult != null ? mergeResult.output() : "";
            String combinedSummary = firstNonBlank(input.mergeSummary(), mergeOutput);
            workflowGraphService.completeMerge(running, response, combinedSummary);
            boolean interrupted = response != null && response.interruptRequest() != null;
            AgentModels.MergerRouting routing = new AgentModels.MergerRouting(
                    response != null ? response.interruptRequest() : null,
                    response != null ? response.mergerResult() : null,
                    interrupted ? null : input.returnToOrchestratorCollector(),
                    interrupted ? null : input.returnToDiscoveryCollector(),
                    interrupted ? null : input.returnToPlanningCollector(),
                    interrupted ? null : input.returnToTicketCollector()
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-agent", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.TicketCollectorRouting handleTicketCollectorBranch(
                AgentModels.TicketCollectorResult request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleTicketCollectorBranch", request);
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    yield AgentModels.TicketCollectorRouting.builder()
                            .ticketRequest(new AgentModels.TicketOrchestratorRequest(request.consolidatedOutput(), "", "", ""))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    yield AgentModels.TicketCollectorRouting.builder()
                            .orchestratorCollectorRequest(new AgentModels.OrchestratorCollectorRequest(request.consolidatedOutput(), request.collectorDecision().requestedPhase()))
                            .build();
                }
                case STOP -> {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Action(canRerun = true)
        public AgentModels.DiscoveryCollectorRouting handleDiscoveryCollectorBranch(
                AgentModels.DiscoveryCollectorResult request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleDiscoveryCollectorBranch", request);
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .discoveryRequest(new AgentModels.DiscoveryOrchestratorRequest(request.consolidatedOutput()))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .planningRequest(new AgentModels.PlanningOrchestratorRequest(request.consolidatedOutput()))
                            .build();
                }
                case STOP -> {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorCollectorRouting handleOrchestratorCollectorBranch(
                AgentModels.OrchestratorCollectorResult request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleOrchestratorCollectorBranch", request);
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    yield AgentModels.OrchestratorCollectorRouting.builder()
                            .orchestratorRequest(new AgentModels.OrchestratorRequest(request.consolidatedOutput(), request.collectorDecision().requestedPhase()))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    yield AgentModels.OrchestratorCollectorRouting.builder()
                            .collectorResult(new AgentModels.OrchestratorCollectorResult(request.consolidatedOutput(), request.collectorDecision()))
                            .build();
                }
                case STOP -> {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Action(canRerun = true)
        public AgentModels.PlanningCollectorRouting handlePlanningCollectorBranch(
                AgentModels.PlanningCollectorResult request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handlePlanningCollectorBranch", request);
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .planningRequest(new AgentModels.PlanningOrchestratorRequest(request.consolidatedOutput()))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .ticketOrchestratorRequest(new AgentModels.TicketOrchestratorRequest("", "", "", ""))
                            .build();
                }
                case STOP -> {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.MergerRouting handleMergerInterrupt(
                AgentModels.MergerInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleMergerInterrupt", request);
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-interrupt", context);
            workflowGraphService.handleMergerInterrupt(context, request);
            MergeNode originNode = workflowGraphService.requireMergeNode(context);
            AgentModels.MergerRequest lastRequest =
                    context.last(AgentModels.MergerRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Missing merger request while handling interrupt."
                );
                AgentModels.MergerRouting routing =
                        new AgentModels.MergerRouting(
                                request,
                                null,
                                null,
                                null,
                                null,
                                null
                        );
                emitActionCompleted(eventBus, multiAgentAgentName(), "merger-interrupt", context, routing);
                return routing;
            }
            var resumed = interruptService.handleReviewInterrupt(
                    context,
                    request,
                    originNode,
                    () -> {
                        String returnRoute = renderReturnRoute(
                                lastRequest.returnToOrchestratorCollector(),
                                lastRequest.returnToDiscoveryCollector(),
                                lastRequest.returnToPlanningCollector(),
                                lastRequest.returnToTicketCollector()
                        );
                        return renderTemplate(
                                MERGER_AGENT_START_MESSAGE,
                                Map.of(
                                        "mergeContext", lastRequest.mergeContext(),
                                        "mergeSummary", lastRequest.mergeSummary(),
                                        "conflictFiles", lastRequest.conflictFiles(),
                                        "returnRoute", returnRoute
                                )
                        );
                    },
                    AgentModels.MergerRouting.class,
                    REVIEW_AGENT_START_MESSAGE
            );
            if (resumed.isPresent()) {
                AgentModels.MergerRouting routing = resumed.get();
                AgentModels.MergerAgentResult mergeResult =
                        routing != null ? routing.mergerResult() : null;
                String mergeOutput = mergeResult != null ? mergeResult.output() : "";
                String combinedSummary = firstNonBlank(
                        lastRequest != null ? lastRequest.mergeSummary() : null,
                        mergeOutput
                );
                workflowGraphService.completeMerge(originNode, routing, combinedSummary);
                emitActionCompleted(eventBus, multiAgentAgentName(), "merger-interrupt", context, routing);
                return routing;
            }
            AgentModels.MergerRouting routing =
                    new AgentModels.MergerRouting(
                            request,
                            null,
                            null,
                            null,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-interrupt", context, routing);
            return routing;
        }

//        @Action
//        public AgentModels.ContextOrchestratorRouting coordinateWorkflow(
//                AgentModels.ContextOrchestratorRequest input,
//                OperationContext context
//        ) {
//            emitActionStarted(eventBus, multiAgentAgentName(), "context-orchestrator", context);
//            String prompt = renderTemplate(
//                    CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE,
//                    Map.of("goal", input.goal(), "phase", input.phase())
//            );
//            AgentModels.ContextOrchestratorRouting routing = context.ai()
//                    .withDefaultLlm()
//                    .createObject(prompt, AgentModels.ContextOrchestratorRouting.class);
//            emitActionCompleted(eventBus, multiAgentAgentName(), "context-orchestrator", context, routing);
//            return routing;
//        }
//
//        @Action
//        public AgentModels.ContextAgentDispatchRouting dispatchContextAgentRequests(
//                AgentModels.ContextAgentRequests input,
//                ActionContext context
//        ) {
//            emitActionStarted(eventBus, multiAgentAgentName(), "context-dispatch", context);
//            List<AgentModels.ContextAgentRequest> requests = input != null ? input.requests() : List.of();
//            if (requests == null) {
//                requests = List.of();
//            }
//            String goal = resolveContextGoal(context, requests);
//            String phase = resolveContextPhase(context, requests);
//            var contextDispatchAgent = context.agentPlatform().agents()
//                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_CONTEXT_DISPATCH_SUBAGENT))
//                    .findAny().orElse(null);
//            for (AgentModels.ContextAgentRequest request : requests) {
//                if (request == null) {
//                    continue;
//                }
//                AgentModels.ContextAgentResult response = runSubProcess(
//                        context,
//                        request,
//                        contextDispatchAgent,
//                        AgentModels.ContextAgentResult.class
//                );
//            }
//            AgentModels.ContextCollectorRequest collectorRequest =
//                    new AgentModels.ContextCollectorRequest(goal, phase);
//            context.addObject(collectorRequest);
//            AgentModels.ContextAgentDispatchRouting routing =
//                    new AgentModels.ContextAgentDispatchRouting(null);
//            emitActionCompleted(eventBus, multiAgentAgentName(), "context-dispatch", context, routing);
//            return routing;
//        }
//
//        @Action
//        public AgentModels.ContextCollectorRouting consolidateContextOperations(
//                AgentModels.ContextCollectorRequest input,
//                OperationContext context
//        ) {
//            emitActionStarted(eventBus, multiAgentAgentName(), "context-collector", context);
//            String prompt = renderTemplate(
//                    CONTEXT_COLLECTOR_START_MESSAGE,
//                    Map.of("goal", input.goal(), "phase", input.phase())
//            );
//            AgentModels.ContextCollectorRouting routing = context.ai()
//                    .withDefaultLlm()
//                    .createObject(prompt, AgentModels.ContextCollectorRouting.class);
//            emitActionCompleted(eventBus, multiAgentAgentName(), "context-collector", context, routing);
//            return routing;
//        }
//
//        @Action
//        public AgentModels.ContextOrchestratorRouting handleContextInterrupt(
//                AgentModels.ContextOrchestratorInterruptRequest request,
//                OperationContext context
//        ) {
//            emitActionStarted(eventBus, multiAgentAgentName(), "context-interrupt", context);
//            AgentModels.ContextOrchestratorRouting routing =
//                    new AgentModels.ContextOrchestratorRouting(
//                            request,
//                            null,
//                            null
//                    );
//            emitActionCompleted(eventBus, multiAgentAgentName(), "context-interrupt", context, routing);
//            return routing;
//        }

        private <T> T runSubProcess(
                ActionContext context,
                Object request,
                com.embabel.agent.core.Agent agent,
                Class<T> outputClass
        ) {
            if (request == null) {
                return null;
            }
            context.addObject(request);
            T result = context.asSubProcess(outputClass, agent);
            context.hide(request);
            return result;
        }

        private static String resolveDiscoveryGoal(
                ActionContext context,
                List<AgentModels.DiscoveryAgentRequest> requests
        ) {
            if (requests != null) {
                for (AgentModels.DiscoveryAgentRequest request : requests) {
                    if (request != null && request.goal() != null && !request.goal().isBlank()) {
                        return request.goal();
                    }
                }
            }
            AgentModels.DiscoveryOrchestratorRequest orchestratorRequest =
                    context.last(AgentModels.DiscoveryOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

        private static String resolvePlanningGoal(
                ActionContext context,
                List<AgentModels.PlanningAgentRequest> requests
        ) {
            if (requests != null) {
                for (AgentModels.PlanningAgentRequest request : requests) {
                    if (request != null && request.goal() != null && !request.goal().isBlank()) {
                        return request.goal();
                    }
                }
            }
            AgentModels.PlanningOrchestratorRequest orchestratorRequest =
                    context.last(AgentModels.PlanningOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

        private static String resolveTicketGoal(ActionContext context) {
            AgentModels.TicketOrchestratorRequest orchestratorRequest =
                    context.last(AgentModels.TicketOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

//        private static String resolveContextGoal(
//                ActionContext context,
//                List<AgentModels.ContextAgentRequest> requests
//        ) {
//            if (requests != null) {
//                for (AgentModels.ContextAgentRequest request : requests) {
//                    if (request != null && request.goal() != null && !request.goal().isBlank()) {
//                        return request.goal();
//                    }
//                }
//            }
//            AgentModels.ContextOrchestratorRequest orchestratorRequest =
//                    context.last(AgentModels.ContextOrchestratorRequest.class);
//            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
//        }
//
//        private static String resolveContextPhase(
//                ActionContext context,
//                List<AgentModels.ContextAgentRequest> requests
//        ) {
//            if (requests != null) {
//                for (AgentModels.ContextAgentRequest request : requests) {
//                    if (request != null && request.phase() != null && !request.phase().isBlank()) {
//                        return request.phase();
//                    }
//                }
//            }
//            AgentModels.ContextOrchestratorRequest orchestratorRequest =
//                    context.last(AgentModels.ContextOrchestratorRequest.class);
//            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.phase() : null);
//        }

        private static String firstNonBlank(String... values) {
            if (values == null) {
                return "";
            }
            for (String value : values) {
                if (value != null && !value.isBlank()) {
                    return value;
                }
            }
            return "";
        }

        private static void appendSection(StringBuilder builder, String heading, String body) {
            if (body == null || body.isBlank()) {
                return;
            }
            if (!builder.isEmpty()) {
                builder.append("\n");
            }
            if (heading != null && !heading.isBlank()) {
                builder.append("## ").append(heading).append("\n");
            }
            builder.append(body.trim()).append("\n");
        }

        //        @Agent(
//                name = WORKFLOW_CONTEXT_DISPATCH_SUBAGENT,
//                description = "Runs context agent request in a subprocess"
//        )
//        @RequiredArgsConstructor
//        public static class ContextDispatchSubagent {
//
//            private final EventBus eventBus;
//
//            @Action
//            @AchievesGoal(description = "Handle context agent request")
//            public AgentModels.ContextAgentResult run(
//                    AgentModels.ContextAgentResult input,
//                    OperationContext context
//            ) {
//                return input;
//            }
//
//            @Action
//            public AgentModels.ContextAgentRouting transitionToInterruptState(
//                    AgentModels.ContextAgentInterruptRequest interruptRequest,
//                    OperationContext context
//            ) {
//                throw new RuntimeException();
//            }
//
//            @Action
//            public AgentModels.ContextAgentRouting run(
//                    AgentModels.ContextAgentRequest input,
//                    OperationContext context
//            ) {
//                emitActionStarted(eventBus, "", "context-agent", context);
//                String prompt = renderTemplate(
//                        CONTEXT_AGENT_START_MESSAGE,
//                        Map.of("goal", input.goal(), "phase", input.phase())
//                );
//                AgentModels.ContextAgentRouting routing = context.ai()
//                        .withDefaultLlm()
//                        .createObject(prompt, AgentModels.ContextAgentRouting.class);
//                emitActionCompleted(eventBus, "", "context-agent", context, routing);
//                return routing;
//            }
//        }

    }

    @Agent(
            name = WORKFLOW_TICKET_DISPATCH_SUBAGENT,
            description = "Runs ticket agent request in a subprocess"
    )
    @RequiredArgsConstructor
    class TicketDispatchSubagent {
        private final EventBus eventBus;

        @Action
        @AchievesGoal(description = "Handle context agent request")
        public AgentModels.TicketAgentResult run(
                AgentModels.TicketAgentResult input,
                OperationContext context
        ) {
            return input;
        }

        @Action
        public AgentModels.TicketAgentRouting transitionToInterruptState(
                AgentModels.TicketAgentInterruptRequest interruptRequest,
                OperationContext context
        ) {
//                TODO: handles review, agent and human, waiting for human, agent
            throw new RuntimeException();
        }

        @Action
        public AgentModels.TicketAgentRouting run(
                AgentModels.TicketAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "ticket-agent", context);
            String prompt = renderTemplate(
                    WorkflowAgent.TICKET_AGENT_START_MESSAGE,
                    Map.of(
                            "ticketDetails", input.ticketDetails(),
                            "ticketDetailsFilePath", input.ticketDetailsFilePath(),
                            "discoveryContext", input.discoveryContext(),
                            "planningContext", input.planningContext()
                    )
            );
            AgentModels.TicketAgentRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.TicketAgentRouting.class);
            emitActionCompleted(eventBus, "", "ticket-agent", context, routing);
            return routing;
        }
    }

    @Agent(
            name = WORKFLOW_PLANNING_DISPATCH_SUBAGENT,
            description = "Runs planning agent request in a subprocess"
    )
    @RequiredArgsConstructor
    class PlanningDispatchSubagent {

        private final EventBus eventBus;

        @Action
        @AchievesGoal(description = "Handle context agent request")
        public AgentModels.PlanningAgentResult run(
                AgentModels.PlanningAgentResult input,
                OperationContext context
        ) {
            return input;
        }

        @Action(canRerun = true)
        public AgentModels.PlanningAgentRouting transitionToInterruptState(
                AgentModels.PlanningAgentInterruptRequest interruptRequest,
                OperationContext context
        ) {
            registerAndHideInput(context, "transitionToInterruptState", interruptRequest);
//                TODO: handles review, agent and human, waiting for human, agent
            throw new RuntimeException();
        }

        @Action(canRerun = true)
        public AgentModels.PlanningAgentRouting run(
                AgentModels.PlanningAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "planning-agent", context);
            String prompt = renderTemplate(
                    WorkflowAgent.PLANNING_AGENT_USER_MESSAGE,
                    Map.of("goal", input.goal())
            );
            AgentModels.PlanningAgentRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.PlanningAgentRouting.class);
            emitActionCompleted(eventBus, "", "planning-agent", context, routing);
            return routing;
        }
    }

    @Agent(
            name = WORKFLOW_DISCOVERY_DISPATCH_SUBAGENT,
            description = "Runs discovery agent request in a subprocess"
    )
    @RequiredArgsConstructor
    class DiscoveryDispatchSubagent {

        private final EventBus eventBus;

        @Action
        @AchievesGoal(description = "Handle context agent request")
        public AgentModels.DiscoveryAgentResult run(
                AgentModels.DiscoveryAgentResult input,
                OperationContext context
        ) {
            return input;
        }

        @Action
        public AgentModels.DiscoveryAgentRouting transitionToInterruptState(
                AgentModels.DiscoveryAgentInterruptRequest interruptRequest,
                OperationContext context
        ) {
//                TODO: handles review, agent and human, waiting for human, agent
            throw new RuntimeException();
        }

        @Action
        public AgentModels.DiscoveryAgentRouting run(
                AgentModels.DiscoveryAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "discovery-agent", context);
            String prompt = renderTemplate(
                    WorkflowAgent.DISCOVERY_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "subdomainFocus", input.subdomainFocus())
            );
            AgentModels.DiscoveryAgentRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryAgentRouting.class);
            emitActionCompleted(eventBus, "", "discovery-agent", context, routing);
            return routing;
        }
    }
}
