package com.hayden.multiagentide.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.annotation.support.AgentMetadataReader;
import com.embabel.agent.api.common.ActionContext;
import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.infrastructure.EventBus;
import com.hayden.multiagentidelib.model.events.Events;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Embabel @Agent definition for multi-agent IDE.
 * Single agent with all workflow actions.
 */
public interface AgentInterfaces {

    String multiAgentAgentName();

    String WORKFLOW_AGENT_NAME = "WorkflowAgent";

    WorkflowAgent WORKFLOW_AGENT = new WorkflowAgent(null);
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

    static void addRequestsToBlackboard(OperationContext context, List<?> requests) {
        if (context == null || requests == null || requests.isEmpty()) {
            return;
        }
        List<Object> toAdd = new ArrayList<>(requests.size());
        for (Object request : requests) {
            if (request != null) {
                toAdd.add(request);
            }
        }
        if (!toAdd.isEmpty()) {
            context.addAll(toAdd);
        }
    }

    static String renderReturnRoute(
            AgentModels.OrchestratorCollectorRequest orchestratorCollector,
            AgentModels.DiscoveryCollectorRequest discoveryCollector,
            AgentModels.PlanningCollectorRequest planningCollector,
            AgentModels.TicketCollectorRequest ticketCollector,
            AgentModels.ContextCollectorRequest contextCollector
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
        if (contextCollector != null) {
            return "contextCollectorRequest(goal=" + contextCollector.goal() + ", phase=" + contextCollector.phase() + ")";
        }
        return "none";
    }

    @Agent(name = WORKFLOW_AGENT_NAME, description = "Coordinates multi-agent workflow")
    final class WorkflowAgent implements AgentInterfaces {

        private final EventBus eventBus;

        WorkflowAgent(EventBus eventBus) {
            this.eventBus = eventBus;
        }

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
        public AgentModels.OrchestratorState transitionToOrchestratorState(
                AgentModels.OrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-orchestrator", context);
            AgentModels.OrchestratorState state = new AgentModels.OrchestratorState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-orchestrator", context, state);
            return state;
        }

        @Action
        public AgentModels.DiscoveryState transitionToDiscoveryState(
                AgentModels.DiscoveryOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-discovery", context);
            AgentModels.DiscoveryState state = new AgentModels.DiscoveryState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-discovery", context, state);
            return state;
        }

        @Action
        public AgentModels.PlanningState transitionToPlanningState(
                AgentModels.PlanningOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-planning", context);
            AgentModels.PlanningState state = new AgentModels.PlanningState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-planning", context, state);
            return state;
        }

        @Action
        public AgentModels.TicketState transitionToTicketState(
                AgentModels.TicketOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-ticket", context);
            AgentModels.TicketState state = new AgentModels.TicketState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-ticket", context, state);
            return state;
        }

        @Action
        public AgentModels.ReviewState transitionToReviewState(
                AgentModels.ReviewRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-review", context);
            AgentModels.ReviewState state = new AgentModels.ReviewState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-review", context, state);
            return state;
        }

        @Action
        public AgentModels.MergerState transitionToMergerState(
                AgentModels.MergerRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-merger", context);
            AgentModels.MergerState state = new AgentModels.MergerState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-merger", context, state);
            return state;
        }

        @Action
        public AgentModels.ContextState transitionToContextState(
                AgentModels.ContextOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-context", context);
            AgentModels.ContextState state = new AgentModels.ContextState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-context", context, state);
            return state;
        }

        @Action
        public AgentModels.InterruptState transitionToInterruptState(
                AgentModels.InterruptRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "transition-interrupt", context);
            AgentModels.InterruptState state = new AgentModels.InterruptState(input);
            emitActionCompleted(eventBus, multiAgentAgentName(), "transition-interrupt", context, state);
            return state;
        }

        @Action
        public AgentModels.OrchestratorRouting coordinateWorkflow(
                AgentModels.OrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator", context);
            String prompt = renderTemplate(
                    ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            AgentModels.OrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.OrchestratorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator", context, routing);
            return routing;
        }

        @Action
        @AchievesGoal(description = "Finished orchestrator collector")
        public AgentModels.OrchestratorCollectorResult consolidateWorkflowOutputs(
                AgentModels.OrchestratorCollectorResult input,
                OperationContext context
        ) {
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-collector", context, "finished");
            return input;
        }

        @Action
        public AgentModels.OrchestratorCollectorRouting consolidateWorkflowOutputs(
                AgentModels.OrchestratorCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-collector", context);
            String prompt = renderTemplate(
                    ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            AgentModels.OrchestratorCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.OrchestratorCollectorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-collector", context, routing);
            return routing;
        }

        @Action
        public AgentModels.OrchestratorRouting handleOrchestratorInterrupt(
                AgentModels.OrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context);
            AgentModels.OrchestratorRouting routing = new AgentModels.OrchestratorRouting(
                    request,
                    null
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context, routing);
            return routing;
        }

        @Action
        public AgentModels.DiscoveryOrchestratorRouting kickOffAnyNumberOfAgentsForCodeSearch(
                AgentModels.DiscoveryOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context);
            String prompt = renderTemplate(
                    DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                    Map.of("goal", input.goal())
            );
            AgentModels.DiscoveryOrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryOrchestratorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context, routing);
            return routing;
        }

        @Action
        public AgentModels.DiscoveryAgentDispatchRouting dispatchDiscoveryAgentRequests(
                AgentModels.DiscoveryAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-dispatch", context);
            List<AgentModels.DiscoveryAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null || requests.isEmpty()) {
                String goal = resolveDiscoveryGoal(context, List.of());
                requests = List.of(new AgentModels.DiscoveryAgentRequest(goal, "Repository overview"));
            }
            String goal = resolveDiscoveryGoal(context, requests);
            StringBuilder results = new StringBuilder();
            var discoveryDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> a.getName().equals(""))
                    .findAny().orElse(null);
            for (AgentModels.DiscoveryAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                AgentModels.DiscoveryAgentRouting response = runSubProcess(
                        context,
                        request,
                        discoveryDispatchAgent,
                        AgentModels.DiscoveryAgentRouting.class
                );
                AgentModels.DiscoveryAgentDispatchInterruptRequest interrupt =
                        toDiscoveryDispatchInterrupt(response);
                if (interrupt != null) {
                    AgentModels.DiscoveryAgentDispatchRouting routing =
                            new AgentModels.DiscoveryAgentDispatchRouting(interrupt);
                    emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-dispatch", context, routing);
                    return routing;
                }
                if (response != null && response.agentResult() != null) {
                    appendSection(
                            results,
                            request.subdomainFocus(),
                            response.agentResult().output()
                    );
                }
            }
            AgentModels.DiscoveryCollectorRequest collectorRequest =
                    new AgentModels.DiscoveryCollectorRequest(goal, results.toString().trim());
            context.addObject(collectorRequest);
            AgentModels.DiscoveryAgentDispatchRouting routing =
                    new AgentModels.DiscoveryAgentDispatchRouting(null);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-dispatch", context, routing);
            return routing;
        }

        @Action
        public AgentModels.DiscoveryAgentRouting discoverCodebaseSection(
                AgentModels.DiscoveryAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-agent", context);
            String prompt = renderTemplate(
                    DISCOVERY_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "subdomainFocus", input.subdomainFocus())
            );
            AgentModels.DiscoveryAgentRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryAgentRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-agent", context, routing);
            return routing;
        }

        @Action
        public AgentModels.DiscoveryCollectorRouting consolidateDiscoveryFindings(
                AgentModels.DiscoveryCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-collector", context);
            String prompt = renderTemplate(
                    DISCOVERY_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "discoveryResults", input.discoveryResults())
            );
            AgentModels.DiscoveryCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryCollectorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-collector", context, routing);
            return routing;
        }

        @Action
        public AgentModels.DiscoveryOrchestratorRouting handleDiscoveryInterrupt(
                AgentModels.DiscoveryOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-interrupt", context);
            AgentModels.DiscoveryOrchestratorRouting routing =
                    new AgentModels.DiscoveryOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-interrupt", context, routing);
            return routing;
        }

        @Action
        public AgentModels.PlanningOrchestratorRouting decomposePlanAndCreateWorkItems(
                AgentModels.PlanningOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-orchestrator", context);
            String prompt = renderTemplate(
                    PLANNING_ORCHESTRATOR_MESSAGE,
                    Map.of("goal", input.goal())
            );
            AgentModels.PlanningOrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.PlanningOrchestratorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-orchestrator", context, routing);
            return routing;
        }

        @Action
        public AgentModels.PlanningAgentDispatchRouting dispatchPlanningAgentRequests(
                AgentModels.PlanningAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-dispatch", context);
            List<AgentModels.PlanningAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolvePlanningGoal(context, requests);
            StringBuilder results = new StringBuilder();
            int index = 0;
            var planningDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> a.getName().equals(""))
                    .findAny().orElse(null);
            for (AgentModels.PlanningAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                AgentModels.PlanningAgentRouting response = runSubProcess(
                        context,
                        request,
                        planningDispatchAgent,
                        AgentModels.PlanningAgentRouting.class
                );
                AgentModels.PlanningAgentDispatchInterruptRequest interrupt =
                        toPlanningDispatchInterrupt(response);
                if (interrupt != null) {
                    AgentModels.PlanningAgentDispatchRouting routing =
                            new AgentModels.PlanningAgentDispatchRouting(interrupt);
                    emitActionCompleted(eventBus, multiAgentAgentName(), "planning-dispatch", context, routing);
                    return routing;
                }
                if (response != null && response.agentResult() != null) {
                    index++;
                    appendSection(
                            results,
                            "Plan segment " + index,
                            response.agentResult().output()
                    );
                }
            }
            AgentModels.PlanningCollectorRequest collectorRequest =
                    new AgentModels.PlanningCollectorRequest(goal, results.toString().trim());
            context.addObject(collectorRequest);
            AgentModels.PlanningAgentDispatchRouting routing =
                    new AgentModels.PlanningAgentDispatchRouting(null);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-dispatch", context, routing);
            return routing;
        }

        @Action
        public AgentModels.PlanningCollectorRouting consolidatePlansIntoTickets(
                AgentModels.PlanningCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-collector", context);
            String prompt = renderTemplate(
                    PLANNING_COLLECTOR_MESSAGE,
                    Map.of("goal", input.goal(), "planningResults", input.planningResults())
            );
            AgentModels.PlanningCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.PlanningCollectorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-collector", context, routing);
            return routing;
        }

        @Action
        public AgentModels.PlanningOrchestratorRouting handlePlanningInterrupt(
                AgentModels.PlanningOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-interrupt", context);
            AgentModels.PlanningOrchestratorRouting routing =
                    new AgentModels.PlanningOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-interrupt", context, routing);
            return routing;
        }

        @Action
        public AgentModels.TicketOrchestratorRouting orchestrateTicketExecution(
                AgentModels.TicketOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context);
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
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context, routing);
            return routing;
        }

        @Action
        public AgentModels.TicketAgentDispatchRouting dispatchTicketAgentRequests(
                AgentModels.TicketAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-dispatch", context);
            List<AgentModels.TicketAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolveTicketGoal(context);
            StringBuilder results = new StringBuilder();
            int index = 0;
            var ticketDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> a.getName().equals(""))
                    .findAny().orElse(null);
            for (AgentModels.TicketAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                AgentModels.TicketAgentRouting response = runSubProcess(
                        context,
                        request,
                        ticketDispatchAgent,
                        AgentModels.TicketAgentRouting.class
                );
                AgentModels.TicketAgentDispatchInterruptRequest interrupt =
                        toTicketDispatchInterrupt(response);
                if (interrupt != null) {
                    AgentModels.TicketAgentDispatchRouting routing =
                            new AgentModels.TicketAgentDispatchRouting(interrupt);
                    emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-dispatch", context, routing);
                    return routing;
                }
                if (response != null && response.agentResult() != null) {
                    index++;
                    String heading = firstNonBlank(request.ticketDetailsFilePath(), "Ticket " + index);
                    appendSection(results, heading, response.agentResult().output());
                }
            }
            AgentModels.TicketCollectorRequest collectorRequest =
                    new AgentModels.TicketCollectorRequest(goal, results.toString().trim());
            context.addObject(collectorRequest);
            AgentModels.TicketAgentDispatchRouting routing =
                    new AgentModels.TicketAgentDispatchRouting(null);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-dispatch", context, routing);
            return routing;
        }

        @Action
        public AgentModels.TicketCollectorRouting consolidateTicketResults(
                AgentModels.TicketCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-collector", context);
            String prompt = renderTemplate(
                    TICKET_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "ticketResults", input.ticketResults())
            );
            AgentModels.TicketCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.TicketCollectorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-collector", context, routing);
            return routing;
        }

        @Action
        public AgentModels.TicketOrchestratorRouting handleTicketInterrupt(
                AgentModels.TicketOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-interrupt", context);
            AgentModels.TicketOrchestratorRouting routing =
                    new AgentModels.TicketOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-interrupt", context, routing);
            return routing;
        }

        @Action
        public AgentModels.ReviewRouting evaluateContent(
                AgentModels.ReviewRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "review-agent", context);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector(),
                    input.returnToContextCollector()
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
            boolean interrupted = response.interruptRequest() != null;
            AgentModels.ReviewRouting routing = new AgentModels.ReviewRouting(
                    response.interruptRequest(),
                    response.reviewResult(),
                    interrupted ? null : input.returnToOrchestratorCollector(),
                    interrupted ? null : input.returnToDiscoveryCollector(),
                    interrupted ? null : input.returnToPlanningCollector(),
                    interrupted ? null : input.returnToTicketCollector(),
                    interrupted ? null : input.returnToContextCollector()
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "review-agent", context, routing);
            return routing;
        }

        @Action
        public AgentModels.ReviewRouting handleReviewInterrupt(
                AgentModels.ReviewInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "review-interrupt", context);
            AgentModels.ReviewRouting routing =
                    new AgentModels.ReviewRouting(
                            request,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "review-interrupt", context, routing);
            return routing;
        }

        @Action
        public AgentModels.MergerRouting performMerge(
                AgentModels.MergerRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-agent", context);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector(),
                    input.returnToContextCollector()
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
            boolean interrupted = response.interruptRequest() != null;
            AgentModels.MergerRouting routing = new AgentModels.MergerRouting(
                    response.interruptRequest(),
                    response.mergerResult(),
                    interrupted ? null : input.returnToOrchestratorCollector(),
                    interrupted ? null : input.returnToDiscoveryCollector(),
                    interrupted ? null : input.returnToPlanningCollector(),
                    interrupted ? null : input.returnToTicketCollector(),
                    interrupted ? null : input.returnToContextCollector()
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-agent", context, routing);
            return routing;
        }

        @Action
        public AgentModels.MergerRouting handleMergerInterrupt(
                AgentModels.MergerInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-interrupt", context);
            AgentModels.MergerRouting routing =
                    new AgentModels.MergerRouting(
                            request,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-interrupt", context, routing);
            return routing;
        }

        @Action
        public AgentModels.ContextOrchestratorRouting coordinateWorkflow(
                AgentModels.ContextOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "context-orchestrator", context);
            String prompt = renderTemplate(
                    CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            AgentModels.ContextOrchestratorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.ContextOrchestratorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "context-orchestrator", context, routing);
            return routing;
        }

        @Action
        public AgentModels.ContextAgentDispatchRouting dispatchContextAgentRequests(
                AgentModels.ContextAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "context-dispatch", context);
            List<AgentModels.ContextAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolveContextGoal(context, requests);
            String phase = resolveContextPhase(context, requests);
            var contextDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> a.getName().equals(""))
                    .findAny().orElse(null);
            for (AgentModels.ContextAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                AgentModels.ContextAgentRouting response = runSubProcess(
                        context,
                        request,
                        contextDispatchAgent,
                        AgentModels.ContextAgentRouting.class
                );
                AgentModels.ContextAgentDispatchInterruptRequest interrupt =
                        toContextDispatchInterrupt(response);
                if (interrupt != null) {
                    AgentModels.ContextAgentDispatchRouting routing =
                            new AgentModels.ContextAgentDispatchRouting(interrupt);
                    emitActionCompleted(eventBus, multiAgentAgentName(), "context-dispatch", context, routing);
                    return routing;
                }
            }
            AgentModels.ContextCollectorRequest collectorRequest =
                    new AgentModels.ContextCollectorRequest(goal, phase);
            context.addObject(collectorRequest);
            AgentModels.ContextAgentDispatchRouting routing =
                    new AgentModels.ContextAgentDispatchRouting(null);
            emitActionCompleted(eventBus, multiAgentAgentName(), "context-dispatch", context, routing);
            return routing;
        }

        @Action
        public AgentModels.ContextCollectorRouting consolidateContextOperations(
                AgentModels.ContextCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "context-collector", context);
            String prompt = renderTemplate(
                    CONTEXT_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            AgentModels.ContextCollectorRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.ContextCollectorRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "context-collector", context, routing);
            return routing;
        }

        @Action
        public AgentModels.ContextOrchestratorRouting handleContextInterrupt(
                AgentModels.ContextOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "context-interrupt", context);
            AgentModels.ContextOrchestratorRouting routing =
                    new AgentModels.ContextOrchestratorRouting(
                            request,
                            null,
                            null
                    );
            emitActionCompleted(eventBus, multiAgentAgentName(), "context-interrupt", context, routing);
            return routing;
        }

        private static com.embabel.agent.core.Agent buildSubagent(
                AgentMetadataReader metadataReader,
                Object instance
        ) {
            var scope = metadataReader.createAgentMetadata(instance);
            if (scope instanceof com.embabel.agent.core.Agent agent) {
                return agent;
            }
            throw new IllegalStateException(
                    "Unable to create subagent for " + instance.getClass().getSimpleName()
            );
        }

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

        private static AgentModels.InterruptDescriptor resolveInterruptDescriptor(
                AgentModels.InterruptDescriptor directInterrupt,
                AgentModels.InterruptState interruptState
        ) {
            if (directInterrupt != null) {
                return directInterrupt;
            }
            if (interruptState != null && interruptState.request() != null) {
                return interruptState.request();
            }
            return null;
        }

        private static AgentModels.DiscoveryAgentDispatchInterruptRequest toDiscoveryDispatchInterrupt(
                AgentModels.DiscoveryAgentRouting routing
        ) {
            if (routing == null) {
                return null;
            }
            AgentModels.InterruptDescriptor interrupt = resolveInterruptDescriptor(
                    routing.interruptRequest(),
                    routing.interruptState()
            );
            if (interrupt == null) {
                return null;
            }
            return new AgentModels.DiscoveryAgentDispatchInterruptRequest(
                    interrupt.type(),
                    interrupt.reason()
            );
        }

        private static AgentModels.PlanningAgentDispatchInterruptRequest toPlanningDispatchInterrupt(
                AgentModels.PlanningAgentRouting routing
        ) {
            if (routing == null) {
                return null;
            }
            AgentModels.InterruptDescriptor interrupt = resolveInterruptDescriptor(
                    routing.interruptRequest(),
                    routing.interruptState()
            );
            if (interrupt == null) {
                return null;
            }
            return new AgentModels.PlanningAgentDispatchInterruptRequest(
                    interrupt.type(),
                    interrupt.reason()
            );
        }

        private static AgentModels.TicketAgentDispatchInterruptRequest toTicketDispatchInterrupt(
                AgentModels.TicketAgentRouting routing
        ) {
            if (routing == null) {
                return null;
            }
            AgentModels.InterruptDescriptor interrupt = resolveInterruptDescriptor(
                    routing.interruptRequest(),
                    routing.interruptState()
            );
            if (interrupt == null) {
                return null;
            }
            return new AgentModels.TicketAgentDispatchInterruptRequest(
                    interrupt.type(),
                    interrupt.reason()
            );
        }

        private static AgentModels.ContextAgentDispatchInterruptRequest toContextDispatchInterrupt(
                AgentModels.ContextAgentRouting routing
        ) {
            if (routing == null) {
                return null;
            }
            AgentModels.InterruptDescriptor interrupt = resolveInterruptDescriptor(
                    routing.interruptRequest(),
                    null
            );
            if (interrupt == null) {
                return null;
            }
            return new AgentModels.ContextAgentDispatchInterruptRequest(
                    interrupt.type(),
                    interrupt.reason()
            );
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

        private static String resolveContextGoal(
                ActionContext context,
                List<AgentModels.ContextAgentRequest> requests
        ) {
            if (requests != null) {
                for (AgentModels.ContextAgentRequest request : requests) {
                    if (request != null && request.goal() != null && !request.goal().isBlank()) {
                        return request.goal();
                    }
                }
            }
            AgentModels.ContextOrchestratorRequest orchestratorRequest =
                    context.last(AgentModels.ContextOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

        private static String resolveContextPhase(
                ActionContext context,
                List<AgentModels.ContextAgentRequest> requests
        ) {
            if (requests != null) {
                for (AgentModels.ContextAgentRequest request : requests) {
                    if (request != null && request.phase() != null && !request.phase().isBlank()) {
                        return request.phase();
                    }
                }
            }
            AgentModels.ContextOrchestratorRequest orchestratorRequest =
                    context.last(AgentModels.ContextOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.phase() : null);
        }

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
            if (builder.length() > 0) {
                builder.append("\n");
            }
            if (heading != null && !heading.isBlank()) {
                builder.append("## ").append(heading).append("\n");
            }
            builder.append(body.trim()).append("\n");
        }

        @Agent(
                name = "WorkflowDiscoveryDispatchSubagent",
                description = "Runs discovery agent request in a subprocess",
                scan = false
        )
        static final class DiscoveryDispatchSubagent {
            private final WorkflowAgent workflowAgent;

            DiscoveryDispatchSubagent(WorkflowAgent workflowAgent) {
                this.workflowAgent = workflowAgent;
            }

            @Action
            @AchievesGoal(description = "Handle discovery agent request")
            public AgentModels.DiscoveryAgentRouting run(
                    AgentModels.DiscoveryAgentRequest input,
                    OperationContext context
            ) {
                return workflowAgent.discoverCodebaseSection(input, context);
            }
        }

        @Agent(
                name = "WorkflowPlanningDispatchSubagent",
                description = "Runs planning agent request in a subprocess",
                scan = false
        )
        @RequiredArgsConstructor
        static final class PlanningDispatchSubagent {

            private final EventBus eventBus;

            @Action
            @AchievesGoal(description = "Handle planning agent request")
            public AgentModels.PlanningAgentRouting run(
                    AgentModels.PlanningAgentRequest input,
                    OperationContext context
            ) {
                emitActionStarted(eventBus, "", "planning-agent", context);
                String prompt = renderTemplate(
                        PLANNING_AGENT_USER_MESSAGE,
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
                name = "WorkflowTicketDispatchSubagent",
                description = "Runs ticket agent request in a subprocess",
                scan = false
        )
        @RequiredArgsConstructor
        static final class TicketDispatchSubagent {
            private final EventBus eventBus;

            @Action
            @AchievesGoal(description = "Handle ticket agent request")
            public AgentModels.TicketAgentRouting run(
                    AgentModels.TicketAgentRequest input,
                    OperationContext context
            ) {
                emitActionStarted(eventBus, "", "ticket-agent", context);
                String prompt = renderTemplate(
                        TICKET_AGENT_START_MESSAGE,
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
                name = "WorkflowContextDispatchSubagent",
                description = "Runs context agent request in a subprocess",
                scan = false
        )
        @RequiredArgsConstructor
        static final class ContextDispatchSubagent {

            private final EventBus eventBus;

            @Action
            @AchievesGoal(description = "Handle context agent request")
            public AgentModels.ContextAgentRouting run(
                    AgentModels.ContextAgentRequest input,
                    OperationContext context
            ) {
                emitActionStarted(eventBus, "", "context-agent", context);
                String prompt = renderTemplate(
                        CONTEXT_AGENT_START_MESSAGE,
                        Map.of("goal", input.goal(), "phase", input.phase())
                );
                AgentModels.ContextAgentRouting routing = context.ai()
                        .withDefaultLlm()
                        .createObject(prompt, AgentModels.ContextAgentRouting.class);
                emitActionCompleted(eventBus, "", "context-agent", context, routing);
                return routing;
            }
        }
    }
}
