package com.hayden.multiagentide.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.ActionContext;
import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentidelib.prompt.ContextIdService;
import com.hayden.multiagentide.service.InterruptService;
import com.hayden.multiagentide.service.RequestEnrichment;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.multiagentidelib.agent.BlackboardHistory;
import com.hayden.multiagentidelib.agent.ContextId;
import com.hayden.multiagentidelib.agent.UpstreamContext;
import com.hayden.multiagentidelib.prompt.PromptAssembly;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.multiagentidelib.prompt.PromptContextFactory;
import com.hayden.utilitymodule.acp.events.EventBus;
import com.hayden.utilitymodule.acp.events.Events;
import com.hayden.multiagentidelib.model.nodes.*;
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
        return registerAndHideInput(context, actionName, input, null);
    }

    /**
     * Register an input in the blackboard history and hide it from the blackboard.
     * This overload allows enriching the input with ContextId and PreviousContext before storing.
     *
     * @param context The operation context
     * @param actionName The name of the action being executed
     * @param input The input object to register and hide
     * @param requestEnrichment Optional enrichment service to set ContextId and PreviousContext
     * @return Updated history with the new entry
     */
    static BlackboardHistory.History registerAndHideInput(
            OperationContext context,
            String actionName,
            Object input,
            RequestEnrichment requestEnrichment
    ) {
        // Get or create history from context
        BlackboardHistory.History history = context.last(BlackboardHistory.History.class);

        if (history == null) {
            history = new BlackboardHistory.History();
        }

        // Enrich the input with ContextId and PreviousContext if enrichment service is provided
        Object enrichedInput = input;
        if (requestEnrichment != null && input != null) {
            enrichedInput = requestEnrichment.enrich(input, context);
        }

        // Add the enriched input to history
        BlackboardHistory.History updatedHistory = history.withEntry(actionName, enrichedInput);

        if (enrichedInput != null) {
            context.getAgentProcess().clear();
        }

        // Add updated history back to context
        context.getAgentProcess().addObject(updatedHistory);

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
        private final ContextIdService contextIdService;
        private final PromptAssembly promptAssembly;
        private final PromptContextFactory promptContextFactory;
        private final RequestEnrichment requestEnrichment;

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
                - delegation fields: { schemaVersion, resultId, upstreamContextId, goal, delegationRationale, assignments, contextSelections, metadata }
                """;

        public static final String ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE = """
                Consolidate the workflow results and provide a routing decision.

                Return a routing object with exactly one of:
                - interruptRequest: { type, reason }
                - collectorResult: { schemaVersion, resultId, inputs, mergeStrategy, conflictResolutions, aggregatedMetrics, consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase }, upstreamContextChain, metadata, discoveryCollectorResult?, planningCollectorResult?, ticketCollectorResult? }
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
                - delegation fields: { schemaVersion, resultId, upstreamContextId, goal, delegationRationale, assignments, contextSelections, metadata }
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
                - agentResult: { schemaVersion, resultId, upstreamContextId, report, output }
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
                - collectorResult: { schemaVersion, resultId, inputs, mergeStrategy, conflictResolutions, aggregatedMetrics, consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase }, upstreamContextChain, metadata, unifiedCodeMap, recommendations, querySpecificFindings, discoveryCuration }
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
                - delegation fields: { schemaVersion, resultId, upstreamContextId, goal, delegationRationale, assignments, contextSelections, metadata }

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
                - agentResult: { schemaVersion, resultId, upstreamContextId, discoveryResultId, tickets, output }
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
                - collectorResult: { schemaVersion, resultId, inputs, mergeStrategy, conflictResolutions, aggregatedMetrics, consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase }, upstreamContextChain, metadata, finalizedTickets, dependencyGraph, discoveryResultId, planningCuration }
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
                - delegation fields: { schemaVersion, resultId, upstreamContextId, goal, delegationRationale, assignments, contextSelections, metadata }
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
                - agentResult: { schemaVersion, resultId, upstreamContextId, ticketId, discoveryResultId, implementationSummary, filesModified, testResults, commits, verificationStatus, upstreamContextChain, output }
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
                - collectorResult: { schemaVersion, resultId, inputs, mergeStrategy, conflictResolutions, aggregatedMetrics, consolidatedOutput, collectorDecision { decisionType, rationale, requestedPhase }, upstreamContextChain, metadata, completionStatus, followUps, ticketCuration }
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
                - reviewResult: { schemaVersion, resultId, upstreamContextId, assessmentStatus, feedback, suggestions, contentLinks, output }
                - one return route matching the provided returnTo*Collector field:
                  orchestratorCollectorRequest | discoveryCollectorRequest | planningCollectorRequest |
                  ticketCollectorRequest | contextCollectorRequest
                """;

//        TODO: the current contexts should be added to this as well - the merger agent must curate these over in the requests- and experiment with routing to the
//                  context agent which retrieves from blackboard history the contexts and the merger, and builds out the next request as a form of summarization
//                  and context curation
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
                - mergerResult: { schemaVersion, resultId, upstreamContextId, acceptability, conflictDetails, resolutionGuidance, output }
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

        private String resolveWorkflowRunId(OperationContext context) {
            String nodeId = resolveNodeId(context);
            if (nodeId == null || nodeId.isBlank()) {
                return "wf-unknown";
            }
            return nodeId.startsWith("wf-") ? nodeId : "wf-" + nodeId;
        }

        private ContextId resolveContextId(ContextId existing, OperationContext context, AgentType agentType) {
            if (existing != null) {
                return existing;
            }
            if (contextIdService == null) {
                return null;
            }
            return contextIdService.generate(resolveWorkflowRunId(context), agentType);
        }

        /**
         * Build prompt context by extracting upstream contexts from typed curation fields on the input.
         * Delegates to PromptContextFactory for pattern matching logic.
         */
        private PromptContext buildPromptContext(
                AgentType agentType,
                Object input,
                OperationContext context
        ) {
            BlackboardHistory.History history = context != null ? context.last(BlackboardHistory.History.class) : null;
            return promptContextFactory.build(agentType, input, history);
        }

        private String assemblePrompt(String basePrompt, PromptContext promptContext) {
            if (promptAssembly == null) {
                return basePrompt;
            }
            return promptAssembly.assemble(basePrompt, promptContext);
        }

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
            registerAndHideInput(context, "consolidateWorkflowOutputs", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-collector", context);
            CollectorNode running = workflowGraphService.startOrchestratorCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.ORCHESTRATOR_COLLECTOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            ), promptContext);
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
            registerAndHideInput(context, "consolidateDiscoveryFindings", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-collector", context);
            DiscoveryCollectorNode running = workflowGraphService.startDiscoveryCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_COLLECTOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    DISCOVERY_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "discoveryResults", input.discoveryResults())
            ), promptContext);
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
            registerAndHideInput(context, "consolidatePlansIntoTickets", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-collector", context);
            PlanningCollectorNode running = workflowGraphService.startPlanningCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_COLLECTOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    PLANNING_COLLECTOR_MESSAGE,
                    Map.of("goal", input.goal(), "planningResults", input.planningResults())
            ), promptContext);
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
            registerAndHideInput(context, "consolidateTicketResults", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-collector", context);
            TicketCollectorNode running = workflowGraphService.startTicketCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_COLLECTOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    TICKET_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "ticketResults", input.ticketResults())
            ), promptContext);
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
            registerAndHideInput(context, "coordinateWorkflow", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator", context);
            OrchestratorNode running = workflowGraphService.startOrchestrator(context);
            PromptContext promptContext = buildPromptContext(AgentType.ORCHESTRATOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            ), promptContext);
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
            registerAndHideInput(context, "handleOrchestratorInterrupt", request, requestEnrichment);
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
                    () -> {
                        PromptContext promptContext = buildPromptContext(AgentType.ORCHESTRATOR, lastRequest, context);
                        return assemblePrompt(renderTemplate(
                                ORCHESTRATOR_AGENT_START_MESSAGE,
                                Map.of("goal", lastRequest.goal(), "phase", lastRequest.phase())
                        ), promptContext);
                    },
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
            registerAndHideInput(context, "kickOffAnyNumberOfAgentsForCodeSearch", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context);
            DiscoveryOrchestratorNode running = workflowGraphService.startDiscoveryOrchestrator(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_ORCHESTRATOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                    Map.of("goal", input.goal())
            ), promptContext);
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
            registerAndHideInput(context, "dispatchDiscoveryAgentRequests", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-dispatch", context);
            List<AgentModels.DiscoveryAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null || requests.isEmpty()) {
                String goal = resolveDiscoveryGoal(context, List.of());
                requests = List.of(new AgentModels.DiscoveryAgentRequest(goal, "Repository overview"));
            }
            String goal = resolveDiscoveryGoal(context, requests);
            StringBuilder results = new StringBuilder();
            List<AgentModels.DiscoveryAgentResult> discoveryResults = new ArrayList<>();
            DiscoveryOrchestratorNode discoveryParent = workflowGraphService.requireDiscoveryOrchestrator(context);
            var discoveryDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_DISCOVERY_DISPATCH_SUBAGENT))
                    .findAny().orElse(null);
            for (AgentModels.DiscoveryAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                String focus = firstNonBlank(request.subdomainFocus(), "Discovery");
                // Enrich the request with ContextId and PreviousContext
                AgentModels.DiscoveryAgentRequest enrichedRequest = requestEnrichment.enrich(request, context);
                DiscoveryNode running = workflowGraphService.startDiscoveryAgent(
                        discoveryParent,
                        goal,
                        focus
                );
                AgentModels.DiscoveryAgentRouting response = runSubProcess(
                        context,
                        enrichedRequest,
                        discoveryDispatchAgent,
                        AgentModels.DiscoveryAgentRouting.class
                );
                AgentModels.DiscoveryAgentResult agentResult = response != null ? response.agentResult() : null;
                workflowGraphService.completeDiscoveryAgent(running, agentResult);
                if (agentResult != null) {
                    discoveryResults.add(agentResult);
                    appendSection(
                            results,
                            focus,
                            agentResult.output()
                    );
                }
            }
            // DiscoveryCollectorRequest no longer has upstreamContext field
            AgentModels.DiscoveryCollectorRequest collectorRequest =
                    new AgentModels.DiscoveryCollectorRequest(
                            resolveContextId(null, context, AgentType.DISCOVERY_COLLECTOR),
                            goal,
                            results.toString().trim(),
                            null // previousContext
                    );
            context.addObject(collectorRequest);
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_COLLECTOR, collectorRequest, context);
            String prompt = assemblePrompt(renderTemplate(
                    DISCOVERY_DISPATCH_MESSAGE,
                    Map.of("goal", collectorRequest.goal(), "discoveryResults", collectorRequest.discoveryResults())
            ), promptContext);
            AgentModels.DiscoveryAgentDispatchRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryAgentDispatchRouting.class);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-dispatch", context, routing);
            return routing;
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.DiscoveryOrchestratorRouting handleDiscoveryInterrupt(
                AgentModels.DiscoveryOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleDiscoveryInterrupt", request, requestEnrichment);
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
                    () -> {
                        PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_ORCHESTRATOR, lastRequest, context);
                        return assemblePrompt(renderTemplate(
                                DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                                Map.of("goal", lastRequest.goal())
                        ), promptContext);
                    },
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

        @Action(canRerun = true)
        public AgentModels.PlanningOrchestratorRouting decomposePlanAndCreateWorkItems(
                AgentModels.PlanningOrchestratorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "decomposePlanAndCreateWorkItems", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-orchestrator", context);
            PlanningOrchestratorNode running = workflowGraphService.startPlanningOrchestrator(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_ORCHESTRATOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    PLANNING_ORCHESTRATOR_MESSAGE,
                    Map.of("goal", input.goal())
            ), promptContext);
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
            registerAndHideInput(context, "dispatchPlanningAgentRequests", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-dispatch", context);
            List<AgentModels.PlanningAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolvePlanningGoal(context, requests);
            StringBuilder results = new StringBuilder();
            List<AgentModels.PlanningAgentResult> planningResults = new ArrayList<>();
            int index = 0;
            PlanningOrchestratorNode planningParent = workflowGraphService.requirePlanningOrchestrator(context);
            AgentModels.PlanningOrchestratorRequest orchestratorRequest = context.last(AgentModels.PlanningOrchestratorRequest.class);
            // Get discoveryCuration from orchestrator request (typed field)
            UpstreamContext.DiscoveryCollectorContext discoveryContext = orchestratorRequest != null 
                    ? orchestratorRequest.discoveryCuration() 
                    : null;
            var planningDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_PLANNING_DISPATCH_SUBAGENT))
                    .findAny().orElse(null);

            for (AgentModels.PlanningAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                index++;
                String title = "Plan segment " + index;
                // Pass through discoveryCuration from orchestrator if not set, then enrich with ContextId and PreviousContext
                AgentModels.PlanningAgentRequest requestWithCuration = request.discoveryCuration() == null && discoveryContext != null
                        ? request.toBuilder().discoveryCuration(discoveryContext).build()
                        : request;
                AgentModels.PlanningAgentRequest enrichedRequest = requestEnrichment.enrich(requestWithCuration, context);
                PlanningNode running = workflowGraphService.startPlanningAgent(
                        planningParent,
                        goal,
                        title
                );
                AgentModels.PlanningAgentRouting response = runSubProcess(
                        context,
                        enrichedRequest,
                        planningDispatchAgent,
                        AgentModels.PlanningAgentRouting.class
                );
                AgentModels.PlanningAgentResult agentResult = response != null ? response.agentResult() : null;
                workflowGraphService.completePlanningAgent(running, agentResult);
                if (agentResult != null) {
                    planningResults.add(agentResult);
                    appendSection(
                            results,
                            title,
                            agentResult.output()
                    );
                }
            }
            // PlanningCollectorRequest uses typed discoveryCuration field
            AgentModels.PlanningCollectorRequest collectorRequest =
                    new AgentModels.PlanningCollectorRequest(
                            resolveContextId(null, context, AgentType.PLANNING_COLLECTOR),
                            goal,
                            results.toString().trim(),
                            discoveryContext,
                            null // previousContext
                    );
            context.addObject(collectorRequest);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_COLLECTOR, collectorRequest, context);
            String prompt = assemblePrompt(renderTemplate(
                    PLANNING_DISPATCH_MESSAGE,
                    Map.of("goal", collectorRequest.goal(), "planningResults", collectorRequest.planningResults())
            ), promptContext);
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
            registerAndHideInput(context, "handlePlanningInterrupt", request, requestEnrichment);
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
                    () -> {
                        PromptContext promptContext = buildPromptContext(AgentType.PLANNING_ORCHESTRATOR, lastRequest, context);
                        return assemblePrompt(renderTemplate(
                                PLANNING_ORCHESTRATOR_MESSAGE,
                                Map.of("goal", lastRequest.goal())
                        ), promptContext);
                    },
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
            registerAndHideInput(context, "finalizeTicketOrchestrator", input, requestEnrichment);
            return new AgentModels.OrchestratorCollectorResult(
                    input.output(),
                    new AgentModels.CollectorDecision(Events.CollectorDecisionType.ADVANCE_PHASE, "", ""));
        }

        @Action(canRerun = true)
        public AgentModels.TicketOrchestratorRouting orchestrateTicketExecution(
                AgentModels.TicketOrchestratorRequest input,
                OperationContext context
        ) {
            registerAndHideInput(context, "orchestrateTicketExecution", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context);
            TicketOrchestratorNode running = workflowGraphService.startTicketOrchestrator(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_ORCHESTRATOR, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    TICKET_ORCHESTRATOR_START_MESSAGE,
                    Map.of(
                            "goal", input.goal(),
                            "tickets", input.planningCuration() != null ? input.planningCuration().prettyPrintTickets() : "",
                            "discoveryContext", input.discoveryCuration() != null ? input.discoveryCuration().prettyPrint() : "",
                            "planningContext", input.planningCuration() != null ? input.planningCuration().prettyPrint() : ""
                    )
            ), promptContext);
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
            registerAndHideInput(context, "dispatchTicketAgentRequests", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-dispatch", context);
            List<AgentModels.TicketAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolveTicketGoal(context);
            StringBuilder results = new StringBuilder();
            List<AgentModels.TicketAgentResult> ticketResults = new ArrayList<>();
            int index = 0;
            TicketOrchestratorNode ticketParent = workflowGraphService.requireTicketOrchestrator(context);
            AgentModels.TicketOrchestratorRequest orchestratorRequest = context.last(AgentModels.TicketOrchestratorRequest.class);
            // Get typed curation fields directly from orchestrator request
            UpstreamContext.DiscoveryCollectorContext discoveryCuration = orchestratorRequest != null 
                    ? orchestratorRequest.discoveryCuration() 
                    : null;
            UpstreamContext.PlanningCollectorContext planningCuration = orchestratorRequest != null 
                    ? orchestratorRequest.planningCuration() 
                    : null;
            
            var ticketDispatchAgent = context.agentPlatform().agents()
                    .stream().filter(a -> Objects.equals(a.getName(), AgentInterfaces.WORKFLOW_TICKET_DISPATCH_SUBAGENT))
                    .findAny().orElse(null);
            for (AgentModels.TicketAgentRequest request : requests) {
                if (request == null) {
                    continue;
                }
                index++;
                String heading = firstNonBlank(request.ticketDetailsFilePath(), "Ticket " + index);
                
                // Pass through curation fields from orchestrator if not set, then enrich with ContextId and PreviousContext
                AgentModels.TicketAgentRequest requestWithCurations = request.toBuilder()
                        .discoveryCuration(request.discoveryCuration() != null ? request.discoveryCuration() : discoveryCuration)
                        .planningCuration(request.planningCuration() != null ? request.planningCuration() : planningCuration)
                        .build();
                AgentModels.TicketAgentRequest enrichedRequest = requestEnrichment.enrich(requestWithCurations, context);

                TicketNode running = workflowGraphService.startTicketAgent(
                        ticketParent,
                        enrichedRequest,
                        index
                );
                AgentModels.TicketAgentRouting response = runSubProcess(
                        context,
                        enrichedRequest,
                        ticketDispatchAgent,
                        AgentModels.TicketAgentRouting.class
                );
                AgentModels.TicketAgentResult agentResult = response != null ? response.agentResult() : null;
                workflowGraphService.completeTicketAgent(running, agentResult);
                if (agentResult != null) {
                    ticketResults.add(agentResult);
                    appendSection(results, heading, agentResult.output());
                }
            }
            // Create collector request with typed curation fields
            AgentModels.TicketCollectorRequest collectorRequest =
                    new AgentModels.TicketCollectorRequest(
                            resolveContextId(null, context, AgentType.TICKET_COLLECTOR),
                            goal,
                            results.toString().trim(),
                            discoveryCuration,
                            planningCuration,
                            null
                    );
            context.addObject(collectorRequest);
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_COLLECTOR, collectorRequest, context);
            String prompt = assemblePrompt(renderTemplate(
                    TICKET_DISPATCH_MESSAGE,
                    Map.of("goal", collectorRequest.goal(), "ticketResults", collectorRequest.ticketResults())
            ), promptContext);
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
            registerAndHideInput(context, "handleTicketInterrupt", request, requestEnrichment);
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
                    () -> {
                        PromptContext promptContext = buildPromptContext(AgentType.TICKET_ORCHESTRATOR, lastRequest, context);
                        return assemblePrompt(renderTemplate(
                                TICKET_ORCHESTRATOR_START_MESSAGE,
                                Map.of(
                                        "goal", lastRequest.goal(),
                                        "tickets", lastRequest.planningCuration() != null ? lastRequest.planningCuration().prettyPrintTickets() : "",
                                        "discoveryContext", lastRequest.discoveryCuration() != null ? lastRequest.discoveryCuration().prettyPrint() : "",
                                        "planningContext", lastRequest.planningCuration() != null ? lastRequest.planningCuration().prettyPrint() : ""
                                )
                        ), promptContext);
                    },
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
            registerAndHideInput(context, "evaluateContent", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "review-agent", context);
            ReviewNode running = workflowGraphService.startReview(context, input);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector()
            );
            PromptContext promptContext = buildPromptContext(AgentType.REVIEW_AGENT, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    REVIEW_AGENT_START_MESSAGE,
                    Map.of(
                            "content", input.content(),
                            "criteria", input.criteria(),
                            "returnRoute", returnRoute
                    )
            ), promptContext);
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
            registerAndHideInput(context, "handleReviewInterrupt", request, requestEnrichment);
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
                        PromptContext promptContext = buildPromptContext(AgentType.REVIEW_AGENT, lastRequest, context);
                        return assemblePrompt(renderTemplate(
                                REVIEW_AGENT_START_MESSAGE,
                                Map.of(
                                        "content", lastRequest.content(),
                                        "criteria", lastRequest.criteria(),
                                        "returnRoute", returnRoute
                                )
                        ), promptContext);
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
            registerAndHideInput(context, "performMerge", input, requestEnrichment);
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-agent", context);
            MergeNode running = workflowGraphService.startMerge(context, input);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector()
            );
            PromptContext promptContext = buildPromptContext(AgentType.MERGER_AGENT, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    MERGER_AGENT_START_MESSAGE,
                    Map.of(
                            "mergeContext", input.mergeContext(),
                            "mergeSummary", input.mergeSummary(),
                            "conflictFiles", input.conflictFiles(),
                            "returnRoute", returnRoute
                    )
            ), promptContext);
            AgentModels.MergerRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.MergerRouting.class);
            AgentModels.MergerAgentResult mergeResult = routing != null ? routing.mergerResult() : null;
            String mergeOutput = mergeResult != null ? mergeResult.output() : "";
            String combinedSummary = firstNonBlank(input.mergeSummary(), mergeOutput);
            workflowGraphService.completeMerge(running, routing, combinedSummary);
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-agent", context, routing);
            return routing;
        }

        @Action(canRerun = true)
        public AgentModels.TicketCollectorRouting handleTicketCollectorBranch(
                AgentModels.TicketCollectorResult request,
                OperationContext context
        ) {
            registerAndHideInput(context, "handleTicketCollectorBranch", request, requestEnrichment);
            // Get upstream curations from context for routing back
            AgentModels.TicketOrchestratorRequest lastTicketOrchestratorRequest = 
                    context.last(AgentModels.TicketOrchestratorRequest.class);
            UpstreamContext.DiscoveryCollectorContext discoveryCuration = lastTicketOrchestratorRequest != null 
                    ? lastTicketOrchestratorRequest.discoveryCuration() 
                    : null;
            UpstreamContext.PlanningCollectorContext planningCuration = lastTicketOrchestratorRequest != null 
                    ? lastTicketOrchestratorRequest.planningCuration() 
                    : null;
            
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.TICKET_ORCHESTRATOR);
                    yield AgentModels.TicketCollectorRouting.builder()
                            .ticketRequest(new AgentModels.TicketOrchestratorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    discoveryCuration,
                                    planningCuration,
                                    null
                            ))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.ORCHESTRATOR_COLLECTOR);
                    yield AgentModels.TicketCollectorRouting.builder()
                            .orchestratorCollectorRequest(new AgentModels.OrchestratorCollectorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    request.collectorDecision().requestedPhase(),
                                    discoveryCuration,
                                    planningCuration,
                                    request.ticketCuration(),
                                    null
                            ))
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
            registerAndHideInput(context, "handleDiscoveryCollectorBranch", request, requestEnrichment);
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.DISCOVERY_ORCHESTRATOR);
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .discoveryRequest(new AgentModels.DiscoveryOrchestratorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    null
                            ))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.PLANNING_ORCHESTRATOR);
                    // Pass the discovery curation directly to planning orchestrator
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .planningRequest(new AgentModels.PlanningOrchestratorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    request.discoveryCuration(),
                                    null
                            ))
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
            registerAndHideInput(context, "handleOrchestratorCollectorBranch", request, requestEnrichment);
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.ORCHESTRATOR);
                    // Pass through curations from the collector result
                    yield AgentModels.OrchestratorCollectorRouting.builder()
                            .orchestratorRequest(new AgentModels.OrchestratorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    request.collectorDecision().requestedPhase(),
                                    request.discoveryCollectorResult() != null 
                                            ? request.discoveryCollectorResult().discoveryCuration() 
                                            : null,
                                    request.planningCollectorResult() != null 
                                            ? request.planningCollectorResult().planningCuration() 
                                            : null,
                                    request.ticketCollectorResult() != null 
                                            ? request.ticketCollectorResult().ticketCuration() 
                                            : null,
                                    null
                            ))
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
            registerAndHideInput(context, "handlePlanningCollectorBranch", request, requestEnrichment);
            // Get discovery curation from prior planning orchestrator request
            AgentModels.PlanningOrchestratorRequest lastPlanningOrchestratorRequest = 
                    context.last(AgentModels.PlanningOrchestratorRequest.class);
            UpstreamContext.DiscoveryCollectorContext discoveryCuration = lastPlanningOrchestratorRequest != null 
                    ? lastPlanningOrchestratorRequest.discoveryCuration() 
                    : null;
            
            return switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.PLANNING_ORCHESTRATOR);
                    // Pass discovery curation back when routing back
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .planningRequest(new AgentModels.PlanningOrchestratorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    discoveryCuration,
                                    null
                            ))
                            .build();
                }
                case ADVANCE_PHASE -> {
                    ContextId contextId = resolveContextId(null, context, AgentType.TICKET_ORCHESTRATOR);
                    // Pass both discovery and planning curations to ticket orchestrator
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .ticketOrchestratorRequest(new AgentModels.TicketOrchestratorRequest(
                                    contextId,
                                    request.consolidatedOutput(),
                                    discoveryCuration,
                                    request.planningCuration(),
                                    null
                            ))
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
            registerAndHideInput(context, "handleMergerInterrupt", request, requestEnrichment);
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
                        PromptContext promptContext = buildPromptContext(AgentType.MERGER_AGENT, lastRequest, context);
                        return assemblePrompt(renderTemplate(
                                MERGER_AGENT_START_MESSAGE,
                                Map.of(
                                        "mergeContext", lastRequest.mergeContext(),
                                        "mergeSummary", lastRequest.mergeSummary(),
                                        "conflictFiles", lastRequest.conflictFiles(),
                                        "returnRoute", returnRoute
                                )
                        ), promptContext);
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
        private final ContextIdService contextIdService;
        private final PromptAssembly promptAssembly;
        private final PromptContextFactory promptContextFactory;
        private final RequestEnrichment requestEnrichment;

        private String assemblePrompt(String basePrompt, PromptContext promptContext) {
            if (promptAssembly == null) {
                return basePrompt;
            }
            return promptAssembly.assemble(basePrompt, promptContext);
        }

        private PromptContext buildPromptContext(AgentType agentType, Object input, OperationContext context) {
            BlackboardHistory.History history = context != null ? context.last(BlackboardHistory.History.class) : null;
            return promptContextFactory.build(agentType, input, history);
        }

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
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_AGENT, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    WorkflowAgent.TICKET_AGENT_START_MESSAGE,
                    Map.of(
                            "ticketDetails", input.ticketDetails() != null ? input.ticketDetails() : "",
                            "ticketDetailsFilePath", input.ticketDetailsFilePath() != null ? input.ticketDetailsFilePath() : "",
                            "discoveryContext", input.discoveryCuration() != null ? input.discoveryCuration().prettyPrint() : "",
                            "planningContext", input.planningCuration() != null ? input.planningCuration().prettyPrint() : ""
                    )
            ), promptContext);
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
        private final ContextIdService contextIdService;
        private final PromptAssembly promptAssembly;
        private final PromptContextFactory promptContextFactory;
        private final RequestEnrichment requestEnrichment;

        private String assemblePrompt(String basePrompt, PromptContext promptContext) {
            if (promptAssembly == null) {
                return basePrompt;
            }
            return promptAssembly.assemble(basePrompt, promptContext);
        }

        private PromptContext buildPromptContext(AgentType agentType, Object input, OperationContext context) {
            BlackboardHistory.History history = context != null ? context.last(BlackboardHistory.History.class) : null;
            return promptContextFactory.build(agentType, input, history);
        }

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
            registerAndHideInput(context, "transitionToInterruptState", interruptRequest, requestEnrichment);
//                TODO: handles review, agent and human, waiting for human, agent
            throw new RuntimeException();
        }

        @Action(canRerun = true)
        public AgentModels.PlanningAgentRouting run(
                AgentModels.PlanningAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "planning-agent", context);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_AGENT, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    WorkflowAgent.PLANNING_AGENT_USER_MESSAGE,
                    Map.of("goal", input.goal())
            ), promptContext);
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
        private final ContextIdService contextIdService;
        private final PromptAssembly promptAssembly;
        private final PromptContextFactory promptContextFactory;
        private final RequestEnrichment requestEnrichment;

        private String assemblePrompt(String basePrompt, PromptContext promptContext) {
            if (promptAssembly == null) {
                return basePrompt;
            }
            return promptAssembly.assemble(basePrompt, promptContext);
        }

        private PromptContext buildPromptContext(AgentType agentType, Object input, OperationContext context) {
            BlackboardHistory.History history = context != null ? context.last(BlackboardHistory.History.class) : null;
            return promptContextFactory.build(agentType, input, history);
        }

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
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_AGENT, input, context);
            String prompt = assemblePrompt(renderTemplate(
                    WorkflowAgent.DISCOVERY_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "subdomainFocus", input.subdomainFocus())
            ), promptContext);
            AgentModels.DiscoveryAgentRouting routing = context.ai()
                    .withDefaultLlm()
                    .createObject(prompt, AgentModels.DiscoveryAgentRouting.class);
            emitActionCompleted(eventBus, "", "discovery-agent", context, routing);
            return routing;
        }
    }
}
