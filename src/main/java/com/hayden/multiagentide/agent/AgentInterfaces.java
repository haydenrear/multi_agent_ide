package com.hayden.multiagentide.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.annotation.EmbabelComponent;
import com.embabel.agent.api.common.ActionContext;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.agent.api.common.StuckHandler;
import com.embabel.agent.api.common.StuckHandlerResult;
import com.embabel.agent.api.common.StuckHandlingResultCode;
import com.embabel.agent.core.AgentProcess;
import com.embabel.agent.core.InjectedType;
import com.embabel.agent.core.Operation;
import com.hayden.multiagentidelib.events.DegenerateLoopException;
import com.hayden.multiagentide.service.InterruptService;
import com.hayden.multiagentide.tool.ToolAbstraction;
import com.hayden.multiagentide.tool.ToolContext;
import com.hayden.multiagentidelib.service.RequestEnrichment;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.multiagentidelib.agent.BlackboardHistory;
import com.hayden.multiagentidelib.agent.ContextManagerTools;
import com.hayden.multiagentidelib.agent.AgentContext;
import com.hayden.multiagentidelib.agent.UpstreamContext;
import com.hayden.multiagentidelib.prompt.PromptAssembly;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.multiagentidelib.prompt.PromptContextFactory;
import com.hayden.utilitymodule.acp.events.EventBus;
import com.hayden.utilitymodule.acp.events.Events;
import com.hayden.multiagentidelib.model.nodes.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;

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


    @EmbabelComponent(scan = false)
    @RequiredArgsConstructor
    class WorkflowAgent implements AgentInterfaces, StuckHandler {

        private final EventBus eventBus;
        private final WorkflowGraphService workflowGraphService;
        private final InterruptService interruptService;
        private final PromptAssembly promptAssembly;
        private final PromptContextFactory promptContextFactory;
        private final RequestEnrichment requestEnrichment;
        private final com.hayden.multiagentide.service.LlmRunner llmRunner;
        private final ContextManagerTools contextManagerTools;

        @Autowired(required = false)
        private List<ResultDecorator> resultDecorators;

        void emitActionStarted(EventBus eventBus, String agentName, String actionName, OperationContext context) {
            AgentInterfaces.emitActionStarted(eventBus, agentName, actionName, context);
        }

        @Override
        public String multiAgentAgentName() {
            return WORKFLOW_AGENT_NAME;
        }

        @Override
        public StuckHandlerResult handleStuck(AgentProcess agentProcess) {
            OperationContext context = buildStuckHandlerContext(agentProcess);
            emitActionStarted(eventBus, "stuck-handler", "handleStuck", context);
            BlackboardHistory history = BlackboardHistory.getEntireBlackboardHistory(context);
            String loopSummary = Optional.ofNullable(history)
                    .map(BlackboardHistory::summary)
                    .orElse("No history available");

            AgentModels.AgentRequest lastRequest = findLastRequest(
                            history,
                    a -> !(a instanceof AgentModels.InterruptRequest)
                            && !(a instanceof AgentModels.ContextManagerRequest)
                            && !(a instanceof AgentModels.ContextManagerRoutingRequest));

            AgentModels.ContextManagerRequest request = AgentModels.ContextManagerRequest.builder()
                    .reason("stuck-handler")
                    .build()
                    .addRequest(lastRequest);

            PromptContext promptContext = promptContextFactory.build(
                    AgentType.CONTEXT_MANAGER,
                    request,
                    history
            );

            AgentModels.ContextManagerResultRouting routing = llmRunner.runWithTemplate(
                    "workflow/context_manager",
                    promptContext,
                    Map.of("reason", loopSummary),
                    ToolContext.of(ToolAbstraction.fromToolCarrier(contextManagerTools)),
                    AgentModels.ContextManagerResultRouting.class,
                    context
            );

            if (agentProcess != null) {
                agentProcess.addObject(routing);
            }

            return new StuckHandlerResult(
                    "Context manager recovery invoked",
                    this,
                    StuckHandlingResultCode.REPLAN,
                    agentProcess
            );
        }

        @Action(canRerun = true, cost = 1)
        public AgentModels.ContextManagerResultRouting handleContextManagerInterrupt(
                @NotNull AgentModels.ContextManagerInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "context-manager-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handleContextManagerInterrupt", request, requestEnrichment);

            workflowGraphService.handleContextManagerInterrupt(context, request);

            AgentModels.ContextManagerRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.ContextManagerRequest.class);
            if (lastRequest == null) {
                lastRequest = AgentModels.ContextManagerRequest.builder()
                        .reason("interrupt-recovery")
                        .build();
            }

            PromptContext promptContext = buildPromptContext(AgentType.CONTEXT_MANAGER, lastRequest, context);
            String reason = firstNonBlank(
                    request.reason(),
                    request.contextForDecision(),
                    request.contextFindings()
            );

            AgentModels.ContextManagerResultRouting resumed = interruptService.handleInterrupt(
                    context,
                    request,
                    workflowGraphService.requireOrchestrator(context),
                    "workflow/context_manager_interrupt",
                    promptContext,
                    Map.of("reason", reason),
                    ToolContext.of(ToolAbstraction.fromToolCarrier(contextManagerTools)),
                    AgentModels.ContextManagerResultRouting.class
            );

            emitActionCompleted(eventBus, multiAgentAgentName(), "context-manager-interrupt", context, resumed);

            return AgentInterfaces.decorateRouting(resumed, context, resultDecorators);
        }

        @Action(canRerun = true, cost = 2)
        public AgentModels.ContextManagerRequest routeToContextManager(
                AgentModels.ContextManagerRoutingRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "context-manager-route", context);
            BlackboardHistory.registerAndHideInput(context, "routeToContextManager", request, requestEnrichment);

            BlackboardHistory history = BlackboardHistory.getEntireBlackboardHistory(context);
            AgentModels.AgentRequest lastRequest = findLastNonContextRequest(history);

            AgentModels.ContextManagerRequest contextManagerRequest = AgentModels.ContextManagerRequest.builder()
                    .reason(request != null ? request.reason() : "%s routed to context manager, but did not provide reason.".formatted(Optional.ofNullable(lastRequest).map(a -> a.getClass().getName()).orElse("Unknown agent")))
                    .type(Optional.ofNullable(request).flatMap(r -> Optional.ofNullable(r.type())).orElse(AgentModels.ContextManagerRequestType.INTROSPECT_AGENT_CONTEXT))
                    .build()
                    .addRequest(lastRequest);

            emitActionCompleted(eventBus, multiAgentAgentName(), "context-manager-route", context, contextManagerRequest);
            return contextManagerRequest;
        }

        private AgentModels.AgentRequest findLastRequest(BlackboardHistory bh,
                                                         Predicate<AgentModels.AgentRequest> r) {
            if (bh == null)
                return null;

            return bh.fromHistory(history -> {
                if (history == null || history.entries() == null) {
                    return null;
                }
                List<BlackboardHistory.Entry> entries = history.entries();
                for (int i = entries.size() - 1; i >= 0; i--) {
                    BlackboardHistory.Entry entry = entries.get(i);
                    if (entry == null) {
                        continue;
                    }
                    Object input = switch (entry) {
                        case BlackboardHistory.DefaultEntry defaultEntry -> defaultEntry.input();
                        case BlackboardHistory.MessageEntry ignored -> null;
                    };
                    if (input instanceof AgentModels.AgentRequest agentRequest && r.test(agentRequest)) {
                        return agentRequest;
                    }
                }
                return null;
            });
        }

        private AgentModels.AgentRequest findLastNonContextRequest(BlackboardHistory history) {
            return history.getValue(entry -> {
                        Object input = switch (entry) {
                            case BlackboardHistory.DefaultEntry defaultEntry ->
                                    defaultEntry.input();
                            case BlackboardHistory.MessageEntry ignored ->
                                    null;
                        };
                        if (input instanceof AgentModels.AgentRequest agentRequest
                                && !(agentRequest instanceof AgentModels.ContextManagerRoutingRequest)
                                && !(agentRequest instanceof AgentModels.ContextManagerRequest)) {
                            return Optional.of(agentRequest);
                        }
                        return Optional.empty();
                    })
                    .orElse(null);
        }

        private OperationContext buildStuckHandlerContext(AgentProcess agentProcess) {
            if (agentProcess == null) {
                return null;
            }
            Operation operation = InjectedType.Companion.named("stuck-handler");
            return OperationContext.Companion.invoke(
                    agentProcess.getProcessContext(),
                    operation,
                    Set.of()
            );
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
            BlackboardHistory history = BlackboardHistory.getEntireBlackboardHistory(context);
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
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-collector", context);
            BlackboardHistory.registerAndHideInput(context, "consolidateWorkflowOutputs", input, requestEnrichment);
            CollectorNode running = workflowGraphService.startOrchestratorCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.ORCHESTRATOR_COLLECTOR, input, context);

            var model = new HashMap<String, Object>();
            Optional.ofNullable(input.goal())
                    .ifPresent(g -> model.put("goal", g));
            Optional.ofNullable(input.phase())
                    .ifPresent(g -> model.put("phase", g));

            AgentModels.OrchestratorCollectorRouting routing = llmRunner.runWithTemplate(
                    "workflow/orchestrator_collector",
                    promptContext,
                    model,
                    ToolContext.empty(),
                    AgentModels.OrchestratorCollectorRouting.class,
                    context
            );

            workflowGraphService.completeOrchestratorCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-collector", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.DiscoveryCollectorRouting consolidateDiscoveryFindings(
                AgentModels.DiscoveryCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-collector", context);
            BlackboardHistory.registerAndHideInput(context, "consolidateDiscoveryFindings", input, requestEnrichment);
            DiscoveryCollectorNode running = workflowGraphService.startDiscoveryCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_COLLECTOR, input, context);

            AgentModels.DiscoveryCollectorRouting routing = llmRunner.runWithTemplate(
                    "workflow/discovery_collector",
                    promptContext,
                    Map.of("goal", input.goal(), "discoveryResults", input.discoveryResults()),
                    ToolContext.empty(),
                    AgentModels.DiscoveryCollectorRouting.class,
                    context
            );

            workflowGraphService.completeDiscoveryCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-collector", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.PlanningCollectorRouting consolidatePlansIntoTickets(
                AgentModels.PlanningCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-collector", context);
            BlackboardHistory.registerAndHideInput(context, "consolidatePlansIntoTickets", input, requestEnrichment);
            PlanningCollectorNode running = workflowGraphService.startPlanningCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_COLLECTOR, input, context);

            AgentModels.PlanningCollectorRouting routing = llmRunner.runWithTemplate(
                    "workflow/planning_collector",
                    promptContext,
                    Map.of("goal", input.goal(), "planningResults", input.planningResults()),
                    ToolContext.empty(),
                    AgentModels.PlanningCollectorRouting.class,
                    context
            );

            workflowGraphService.completePlanningCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-collector", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.TicketCollectorRouting consolidateTicketResults(
                AgentModels.TicketCollectorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-collector", context);
            BlackboardHistory.registerAndHideInput(context, "consolidateTicketResults", input, requestEnrichment);
            TicketCollectorNode running = workflowGraphService.startTicketCollector(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_COLLECTOR, input, context);

            AgentModels.TicketCollectorRouting routing = llmRunner.runWithTemplate(
                    "workflow/ticket_collector",
                    promptContext,
                    Map.of("goal", input.goal(), "ticketResults", input.ticketResults()),
                    ToolContext.empty(),
                    AgentModels.TicketCollectorRouting.class,
                    context
            );

            workflowGraphService.completeTicketCollector(context, running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-collector", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorRouting coordinateWorkflow(
                AgentModels.OrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator", context);
            BlackboardHistory.registerAndHideInput(context, "coordinateWorkflow", input, requestEnrichment);
            OrchestratorNode running = workflowGraphService.startOrchestrator(context);
            PromptContext promptContext = buildPromptContext(AgentType.ORCHESTRATOR, input, context);

            var model = new HashMap<String, Object>();
            Optional.ofNullable(input.goal())
                    .ifPresent(g -> model.put("goal", g));
            Optional.ofNullable(input.phase())
                    .ifPresent(g -> model.put("phase", g));

            AgentModels.OrchestratorRouting routing = llmRunner.runWithTemplate(
                    "workflow/orchestrator",
                    promptContext,
                    model,
                    ToolContext.empty(),
                    AgentModels.OrchestratorRouting.class,
                    context
            );

            workflowGraphService.completeOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }


        @Action(canRerun = true, cost = 1)
        public AgentModels.OrchestratorRouting handleOrchestratorInterrupt(
                AgentModels.OrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handleOrchestratorInterrupt", request, requestEnrichment);
            workflowGraphService.handleOrchestratorInterrupt(context, request);
            OrchestratorNode originNode = workflowGraphService.requireOrchestrator(context);
            AgentModels.OrchestratorRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.OrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Orchestrator request not found - creating default recovery request."
                );
                throw new DegenerateLoopException("Found strange situation where OrchestratorRequest not found. Impossible state.",
                        "handleOrchestratorInterrupt", AgentModels.OrchestratorInterruptRequest.class, 1);
            }
            PromptContext promptContext = buildPromptContext(AgentType.ORCHESTRATOR, lastRequest, context);
            var resumed = interruptService.handleInterrupt(
                    context,
                    request,
                    originNode,
                    "workflow/orchestrator",
                    promptContext,
                    Map.of("goal", lastRequest.goal(), "phase", lastRequest.phase()),
                    AgentModels.OrchestratorRouting.class
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "orchestrator-interrupt", context, resumed);
            return AgentInterfaces.decorateRouting(resumed, context, resultDecorators);
        }


        @Action(canRerun = true)
        public AgentModels.DiscoveryOrchestratorRouting kickOffAnyNumberOfAgentsForCodeSearch(
                AgentModels.DiscoveryOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context);
            BlackboardHistory.registerAndHideInput(context, "kickOffAnyNumberOfAgentsForCodeSearch", input, requestEnrichment);
            DiscoveryOrchestratorNode running = workflowGraphService.startDiscoveryOrchestrator(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_ORCHESTRATOR, input, context);

            AgentModels.DiscoveryOrchestratorRouting routing = llmRunner.runWithTemplate(
                    "workflow/discovery_orchestrator",
                    promptContext,
                    Map.of("goal", input.goal()),
                    ToolContext.empty(),
                    AgentModels.DiscoveryOrchestratorRouting.class,
                    context
            );

            workflowGraphService.completeDiscoveryOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-orchestrator", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.DiscoveryAgentDispatchRouting dispatchDiscoveryAgentRequests(
                @NotNull AgentModels.DiscoveryAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-dispatch", context);
            BlackboardHistory.registerAndHideInput(context, "dispatchDiscoveryAgentRequests", input, requestEnrichment);
            List<AgentModels.DiscoveryAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null || requests.isEmpty()) {
                String goal = resolveDiscoveryGoal(context, input);
                requests = List.of(new AgentModels.DiscoveryAgentRequest(goal, "Repository overview"));
            }
            String goal = resolveDiscoveryGoal(context, input);
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
                // Enrich the request with ArtifactKey and PreviousContext
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
                    discoveryResults.add(requestEnrichment.enrich(agentResult, context));
                }
            }

            var d = requestEnrichment.enrich(AgentModels.DiscoveryAgentResults.builder().result(discoveryResults).build(), context);

            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_AGENT_DISPATCH, d, context);

            AgentModels.DiscoveryAgentDispatchRouting routing = llmRunner.runWithTemplate(
                    "workflow/discovery_dispatch",
                    promptContext,
                    Map.of(
                            "goal",
                            goal,
                            "discoveryResults",
                            d.prettyPrint(new AgentContext.AgentSerializationCtx.ResultsSerialization())
                    ),
                    ToolContext.empty(),
                    AgentModels.DiscoveryAgentDispatchRouting.class,
                    context
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-dispatch", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.DiscoveryOrchestratorRouting handleDiscoveryInterrupt(
                AgentModels.DiscoveryOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "discovery-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handleDiscoveryInterrupt", request, requestEnrichment);
            workflowGraphService.handleDiscoveryInterrupt(context, request);
            DiscoveryOrchestratorNode originNode = workflowGraphService.requireDiscoveryOrchestrator(context);
            AgentModels.DiscoveryOrchestratorRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.DiscoveryOrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Discovery orchestrator request not found - creating default recovery request."
                );
                lastRequest = new AgentModels.DiscoveryOrchestratorRequest("Resume discovery");
            }
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_ORCHESTRATOR, lastRequest, context);
            var routing = interruptService.handleInterrupt(
                    context,
                    request,
                    originNode,
                    "workflow/discovery_orchestrator",
                    promptContext,
                    Map.of("goal", lastRequest.goal()),
                    AgentModels.DiscoveryOrchestratorRouting.class
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "discovery-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.PlanningOrchestratorRouting decomposePlanAndCreateWorkItems(
                AgentModels.PlanningOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-orchestrator", context);
            BlackboardHistory.registerAndHideInput(context, "decomposePlanAndCreateWorkItems", input, requestEnrichment);
            PlanningOrchestratorNode running = workflowGraphService.startPlanningOrchestrator(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_ORCHESTRATOR, input, context);

            AgentModels.PlanningOrchestratorRouting routing = llmRunner.runWithTemplate(
                    "workflow/planning_orchestrator",
                    promptContext,
                    Map.of("goal", input.goal()),
                    ToolContext.empty(),
                    AgentModels.PlanningOrchestratorRouting.class,
                    context
            );

            workflowGraphService.completePlanningOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-orchestrator", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.PlanningAgentDispatchRouting dispatchPlanningAgentRequests(
                @NotNull AgentModels.PlanningAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-dispatch", context);
            BlackboardHistory.registerAndHideInput(context, "dispatchPlanningAgentRequests", input, requestEnrichment);
            List<AgentModels.PlanningAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolvePlanningGoal(context, input);
            List<AgentModels.PlanningAgentResult> planningResults = new ArrayList<>();
            int index = 0;
            PlanningOrchestratorNode planningParent = workflowGraphService.requirePlanningOrchestrator(context);
            AgentModels.PlanningOrchestratorRequest orchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.PlanningOrchestratorRequest.class);
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
                // Pass through discoveryCuration from orchestrator if not set, then enrich with ArtifactKey and PreviousContext
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
                    planningResults.add(requestEnrichment.enrich(agentResult, context));
                }
            }

            AgentModels.PlanningAgentResults planningAgentResults = AgentModels.PlanningAgentResults.builder()
                    .planningAgentResults(planningResults)
                    .build();

            planningAgentResults = requestEnrichment.enrich(planningAgentResults, context);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_AGENT_DISPATCH, planningAgentResults, context);

            AgentModels.PlanningAgentDispatchRouting routing = llmRunner.runWithTemplate(
                    "workflow/planning_dispatch",
                    promptContext,
                    Map.of(
                            "goal",
                            goal,
                            "planningResults",
                            planningAgentResults.prettyPrint(new AgentContext.AgentSerializationCtx.ResultsSerialization())
                    ),
                    ToolContext.empty(),
                    AgentModels.PlanningAgentDispatchRouting.class,
                    context
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-dispatch", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.PlanningOrchestratorRouting handlePlanningInterrupt(
                AgentModels.PlanningOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "planning-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handlePlanningInterrupt", request, requestEnrichment);
            workflowGraphService.handlePlanningInterrupt(context, request);
            PlanningOrchestratorNode originNode = workflowGraphService.requirePlanningOrchestrator(context);
            AgentModels.PlanningOrchestratorRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.PlanningOrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Planning orchestrator request not found - creating default recovery request."
                );
                lastRequest = new AgentModels.PlanningOrchestratorRequest("Resume planning");
            }
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_ORCHESTRATOR, lastRequest, context);
            var routing = interruptService.handleInterrupt(
                    context,
                    request,
                    originNode,
                    "workflow/planning_orchestrator",
                    promptContext,
                    Map.of("goal", lastRequest.goal()),
                    AgentModels.PlanningOrchestratorRouting.class
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "planning-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorCollectorResult finalizeTicketOrchestrator(
                AgentModels.TicketOrchestratorResult input,
                OperationContext context
        ) {
            BlackboardHistory.registerAndHideInput(context, "finalizeTicketOrchestrator", input, requestEnrichment);
            return new AgentModels.OrchestratorCollectorResult(
                    input.output(),
                    new AgentModels.CollectorDecision(Events.CollectorDecisionType.ADVANCE_PHASE, "", ""));
        }

        @Action(canRerun = true)
        public AgentModels.TicketOrchestratorRouting orchestrateTicketExecution(
                AgentModels.TicketOrchestratorRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context);
            BlackboardHistory.registerAndHideInput(context, "orchestrateTicketExecution", input, requestEnrichment);
            TicketOrchestratorNode running = workflowGraphService.startTicketOrchestrator(context, input);
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_ORCHESTRATOR, input, context);

            AgentModels.TicketOrchestratorRouting routing = llmRunner.runWithTemplate(
                    "workflow/ticket_orchestrator",
                    promptContext,
                    Map.of(
                            "goal", input.goal()
                    ),
                    ToolContext.empty(),
                    AgentModels.TicketOrchestratorRouting.class,
                    context
            );

            workflowGraphService.completeTicketOrchestrator(running, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-orchestrator", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.TicketAgentDispatchRouting dispatchTicketAgentRequests(
                @NotNull AgentModels.TicketAgentRequests input,
                ActionContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-dispatch", context);
            BlackboardHistory.registerAndHideInput(context, "dispatchTicketAgentRequests", input, requestEnrichment);
            List<AgentModels.TicketAgentRequest> requests = input != null ? input.requests() : List.of();
            if (requests == null) {
                requests = List.of();
            }
            String goal = resolveTicketGoal(context, input);
            List<AgentModels.TicketAgentResult> ticketResults = new ArrayList<>();
            int index = 0;
            TicketOrchestratorNode ticketParent = workflowGraphService.requireTicketOrchestrator(context);
            AgentModels.TicketOrchestratorRequest orchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.TicketOrchestratorRequest.class);
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

                // Pass through curation fields from orchestrator if not set, then enrich with ArtifactKey and PreviousContext
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
                }
            }

            var ticketAgentResults = AgentModels.TicketAgentResults.builder()
                    .ticketAgentResults(ticketResults)
                    .build();

            ticketAgentResults = requestEnrichment.enrich(ticketAgentResults, context);

            PromptContext promptContext = buildPromptContext(AgentType.TICKET_AGENT_DISPATCH, ticketAgentResults, context);

            AgentModels.TicketAgentDispatchRouting routing = llmRunner.runWithTemplate(
                    "workflow/ticket_dispatch",
                    promptContext,
                    Map.of(
                            "goal",
                            goal,
                            "ticketResults",
                            ticketAgentResults.prettyPrint(new AgentContext.AgentSerializationCtx.ResultsSerialization())
                    ),
                    ToolContext.empty(),
                    AgentModels.TicketAgentDispatchRouting.class,
                    context
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-dispatch", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.TicketOrchestratorRouting handleTicketInterrupt(
                AgentModels.TicketOrchestratorInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "ticket-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handleTicketInterrupt", request, requestEnrichment);
            workflowGraphService.handleTicketInterrupt(context, request);
            TicketOrchestratorNode originNode = workflowGraphService.requireTicketOrchestrator(context);
            AgentModels.TicketOrchestratorRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.TicketOrchestratorRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Ticket orchestrator request not found - creating default recovery request."
                );
                lastRequest = new AgentModels.TicketOrchestratorRequest("Resume ticket execution");
            }
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_ORCHESTRATOR, lastRequest, context);
            var routing = interruptService.handleInterrupt(
                    context,
                    request,
                    originNode,
                    "workflow/ticket_orchestrator",
                    promptContext,
                    Map.of(
                            "goal", lastRequest.goal()
                    ),
                    AgentModels.TicketOrchestratorRouting.class
            );
            emitActionCompleted(eventBus, multiAgentAgentName(), "ticket-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.MergerRouting performMerge(
                AgentModels.MergerRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-agent", context);
            BlackboardHistory.registerAndHideInput(context, "performMerge", input, requestEnrichment);
            MergeNode running = workflowGraphService.startMerge(context, input);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector()
            );
            PromptContext promptContext = buildPromptContext(AgentType.MERGER_AGENT, input, context);

            AgentModels.MergerRouting routing = llmRunner.runWithTemplate(
                    "workflow/merger",
                    promptContext,
                    Map.of(
                            "mergeContext", input.mergeContext(),
                            "mergeSummary", input.mergeSummary(),
                            "conflictFiles", input.conflictFiles(),
                            "returnRoute", returnRoute
                    ),
                    ToolContext.empty(),
                    AgentModels.MergerRouting.class,
                    context
            );
            String combinedSummary = resolveMergeSummary(input, routing);
            workflowGraphService.completeMerge(running, routing, combinedSummary);
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-agent", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.ReviewRouting performReview(
                AgentModels.ReviewRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "review-agent", context);
            BlackboardHistory.registerAndHideInput(context, "evaluateContent", input, requestEnrichment);
            ReviewNode running = workflowGraphService.startReview(context, input);
            String returnRoute = renderReturnRoute(
                    input.returnToOrchestratorCollector(),
                    input.returnToDiscoveryCollector(),
                    input.returnToPlanningCollector(),
                    input.returnToTicketCollector()
            );
            PromptContext promptContext = buildPromptContext(AgentType.REVIEW_AGENT, input, context);

            AgentModels.ReviewRouting response = llmRunner.runWithTemplate(
                    "workflow/review",
                    promptContext,
                    Map.of(
                            "content", input.content(),
                            "criteria", input.criteria(),
                            "returnRoute", returnRoute
                    ),
                    ToolContext.empty(),
                    AgentModels.ReviewRouting.class,
                    context
            );
            workflowGraphService.completeReview(running, response);
            emitActionCompleted(eventBus, multiAgentAgentName(), "review-agent", context, response);
            return AgentInterfaces.decorateRouting(response, context, resultDecorators);
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.ReviewRouting handleReviewInterrupt(
                AgentModels.ReviewInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "review-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handleReviewInterrupt", request, requestEnrichment);
            workflowGraphService.handleReviewInterrupt(context, request);
            ReviewNode originNode = workflowGraphService.requireReviewNode(context);
            AgentModels.ReviewRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.ReviewRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Review request not found - creating default recovery request."
                );
                lastRequest = new AgentModels.ReviewRequest("Resume review", "default criteria", null, null, null, null);
            }
            String returnRoute = renderReturnRoute(
                    lastRequest.returnToOrchestratorCollector(),
                    lastRequest.returnToDiscoveryCollector(),
                    lastRequest.returnToPlanningCollector(),
                    lastRequest.returnToTicketCollector()
            );
            PromptContext promptContext = buildPromptContext(AgentType.REVIEW_AGENT, lastRequest, context);
            var routing = interruptService.handleInterrupt(
                    context,
                    request,
                    originNode,
                    "workflow/review",
                    promptContext,
                    Map.of(
                            "content", lastRequest.content(),
                            "criteria", lastRequest.criteria(),
                            "returnRoute", returnRoute
                    ),
                    AgentModels.ReviewRouting.class
            );
            workflowGraphService.completeReview(originNode, routing);
            emitActionCompleted(eventBus, multiAgentAgentName(), "review-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.TicketCollectorRouting handleTicketCollectorBranch(
                AgentModels.TicketCollectorResult request,
                OperationContext context
        ) {
            BlackboardHistory.registerAndHideInput(context, "handleTicketCollectorBranch", request, requestEnrichment);
            // Get upstream curations from context for routing back
            AgentModels.TicketOrchestratorRequest lastTicketOrchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.TicketOrchestratorRequest.class);
            UpstreamContext.DiscoveryCollectorContext discoveryCuration = lastTicketOrchestratorRequest != null
                    ? lastTicketOrchestratorRequest.discoveryCuration()
                    : null;
            UpstreamContext.PlanningCollectorContext planningCuration = lastTicketOrchestratorRequest != null
                    ? lastTicketOrchestratorRequest.planningCuration()
                    : null;

            AgentModels.TicketCollectorRouting routing = switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    AgentModels.TicketOrchestratorRequest ticketRequest = requestEnrichment.enrich(
                            AgentModels.TicketOrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .discoveryCuration(discoveryCuration)
                                    .planningCuration(planningCuration)
                                    .build(),
                            context
                    );
                    yield AgentModels.TicketCollectorRouting.builder()
                            .ticketRequest(ticketRequest)
                            .build();
                }
                case ADVANCE_PHASE -> {
                    AgentModels.OrchestratorCollectorRequest orchestratorCollectorRequest = requestEnrichment.enrich(
                            AgentModels.OrchestratorCollectorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                            .phase(request.collectorDecision().requestedPhase())
                                    .discoveryCuration(discoveryCuration)
                                    .planningCuration(planningCuration)
                                            .ticketCuration(request.ticketCuration())
                                    .build(),
                            context
                    );
                    yield AgentModels.TicketCollectorRouting.builder()
                            .orchestratorCollectorRequest(orchestratorCollectorRequest)
                            .build();
                }
                case STOP -> {
                    AgentModels.OrchestratorCollectorRequest orchestratorCollectorRequest = requestEnrichment.enrich(
                            AgentModels.OrchestratorCollectorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .phase(request.collectorDecision().requestedPhase())
                                    .discoveryCuration(discoveryCuration)
                                    .planningCuration(planningCuration)
                                    .ticketCuration(request.ticketCuration())
                                    .build(),
                            context
                    );
                    yield AgentModels.TicketCollectorRouting.builder()
                            .orchestratorCollectorRequest(orchestratorCollectorRequest)
                            .build();
                }
            };
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.DiscoveryCollectorRouting handleDiscoveryCollectorBranch(
                AgentModels.DiscoveryCollectorResult request,
                OperationContext context
        ) {
            BlackboardHistory.registerAndHideInput(context, "handleDiscoveryCollectorBranch", request, requestEnrichment);
            AgentModels.DiscoveryCollectorRouting routing = switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    AgentModels.DiscoveryOrchestratorRequest discoveryRequest = requestEnrichment.enrich(
                            AgentModels.DiscoveryOrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .build(),
                            context
                    );
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .discoveryRequest(discoveryRequest)
                            .build();
                }
                case ADVANCE_PHASE -> {
                    // Pass the discovery curation directly to planning orchestrator
                    AgentModels.PlanningOrchestratorRequest planningRequest = requestEnrichment.enrich(
                            AgentModels.PlanningOrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .discoveryCuration(request.discoveryCuration())
                                    .build(),
                            context
                    );
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .planningRequest(planningRequest)
                            .build();
                }
                case STOP -> {
                    AgentModels.OrchestratorRequest orchestratorRequest = requestEnrichment.enrich(
                            AgentModels.OrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .phase(request.collectorDecision().requestedPhase())
                                    .discoveryCuration(request.discoveryCuration())
                                    .build(),
                            context
                    );
                    yield AgentModels.DiscoveryCollectorRouting.builder()
                            .orchestratorRequest(orchestratorRequest)
                            .build();
                }
            };
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.OrchestratorCollectorRouting handleOrchestratorCollectorBranch(
                AgentModels.OrchestratorCollectorResult request,
                OperationContext context
        ) {
            BlackboardHistory.registerAndHideInput(context, "handleOrchestratorCollectorBranch", request, requestEnrichment);
            AgentModels.OrchestratorCollectorRouting routing = switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    AgentModels.OrchestratorRequest orchestratorRequest = requestEnrichment.enrich(
                            AgentModels.OrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .phase(request.collectorDecision().requestedPhase())
                                    .discoveryCuration(request.discoveryCollectorResult() != null
                                            ? request.discoveryCollectorResult().discoveryCuration()
                                            : null)
                                    .planningCuration(request.planningCollectorResult() != null
                                            ? request.planningCollectorResult().planningCuration()
                                            : null)
                                    .ticketCuration(request.ticketCollectorResult() != null
                                            ? request.ticketCollectorResult().ticketCuration()
                                            : null)
                                    .build(),
                            context
                    );
                    yield AgentModels.OrchestratorCollectorRouting.builder()
                            .orchestratorRequest(orchestratorRequest)
                            .build();
                }
                case ADVANCE_PHASE, STOP -> AgentModels.OrchestratorCollectorRouting.builder()
                        .collectorResult(new AgentModels.OrchestratorCollectorResult(request.consolidatedOutput(), request.collectorDecision()))
                        .build();
            };
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.PlanningCollectorRouting handlePlanningCollectorBranch(
                AgentModels.PlanningCollectorResult request,
                OperationContext context
        ) {
            BlackboardHistory.registerAndHideInput(context, "handlePlanningCollectorBranch", request, requestEnrichment);
            // Get discovery curation from prior planning orchestrator request
            AgentModels.PlanningOrchestratorRequest lastPlanningOrchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.PlanningOrchestratorRequest.class);
            UpstreamContext.DiscoveryCollectorContext discoveryCuration = lastPlanningOrchestratorRequest != null
                    ? lastPlanningOrchestratorRequest.discoveryCuration()
                    : null;

            AgentModels.PlanningCollectorRouting routing = switch(request.collectorDecision().decisionType()) {
                case ROUTE_BACK -> {
                    // Pass discovery curation back when routing back
                    AgentModels.PlanningOrchestratorRequest planningRequest = requestEnrichment.enrich(
                            AgentModels.PlanningOrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .discoveryCuration(discoveryCuration)
                                    .build(),
                            context
                    );
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .planningRequest(planningRequest)
                            .build();
                }
                case ADVANCE_PHASE -> {
                    // Pass both discovery and planning curations to ticket orchestrator
                    AgentModels.TicketOrchestratorRequest ticketRequest = requestEnrichment.enrich(
                            AgentModels.TicketOrchestratorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .discoveryCuration(discoveryCuration)
                                    .planningCuration(request.planningCuration())
                                    .build(),
                            context
                    );
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .ticketOrchestratorRequest(ticketRequest)
                            .build();
                }
                case STOP -> {
                    AgentModels.OrchestratorCollectorRequest orchestratorCollectorRequest = requestEnrichment.enrich(
                            AgentModels.OrchestratorCollectorRequest.builder()
                                    .goal(request.consolidatedOutput())
                                    .phase(request.collectorDecision().requestedPhase())
                                    .discoveryCuration(discoveryCuration)
                                    .planningCuration(request.planningCuration())
                                    .build(),
                            context
                    );
                    yield AgentModels.PlanningCollectorRouting.builder()
                            .orchestratorCollectorRequest(orchestratorCollectorRequest)
                            .build();
                }
            };
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true, cost = 1.0)
        public AgentModels.MergerRouting handleMergerInterrupt(
                AgentModels.MergerInterruptRequest request,
                OperationContext context
        ) {
            emitActionStarted(eventBus, multiAgentAgentName(), "merger-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "handleMergerInterrupt", request, requestEnrichment);
            workflowGraphService.handleMergerInterrupt(context, request);
            MergeNode originNode = workflowGraphService.requireMergeNode(context);
            AgentModels.MergerRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.MergerRequest.class);
            if (lastRequest == null) {
                workflowGraphService.emitErrorEvent(
                        originNode,
                        "Merger request not found - creating default recovery request."
                );
                lastRequest = new AgentModels.MergerRequest("Resume merge", "default summary", "", null, null, null, null);
            }
            String returnRoute = renderReturnRoute(
                    lastRequest.returnToOrchestratorCollector(),
                    lastRequest.returnToDiscoveryCollector(),
                    lastRequest.returnToPlanningCollector(),
                    lastRequest.returnToTicketCollector()
            );
            PromptContext promptContext = buildPromptContext(AgentType.MERGER_AGENT, lastRequest, context);
            var routing = interruptService.handleInterrupt(
                    context,
                    request,
                    originNode,
                    "workflow/merger",
                    promptContext,
                    Map.of(
                            "mergeContext", lastRequest.mergeContext(),
                            "mergeSummary", lastRequest.mergeSummary(),
                            "conflictFiles", lastRequest.conflictFiles(),
                            "returnRoute", returnRoute
                    ),
                    AgentModels.MergerRouting.class
            );
            String combinedSummary = resolveMergeSummary(lastRequest, routing);
            workflowGraphService.completeMerge(originNode, routing, combinedSummary);
            emitActionCompleted(eventBus, multiAgentAgentName(), "merger-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
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

        private static String resolveDiscoveryGoal(
                ActionContext context,
                AgentModels.DiscoveryAgentRequests input
        ) {
            String resolved = input != null
                    ? input.prettyPrint(new AgentContext.AgentSerializationCtx.GoalResolutionSerialization())
                    : "";
            if (resolved != null && !resolved.isBlank()) {
                return resolved;
            }
            AgentModels.DiscoveryOrchestratorRequest orchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.DiscoveryOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

        private static String resolvePlanningGoal(
                ActionContext context,
                AgentModels.PlanningAgentRequests input
        ) {
            String resolved = input != null
                    ? input.prettyPrint(new AgentContext.AgentSerializationCtx.GoalResolutionSerialization())
                    : "";
            if (resolved != null && !resolved.isBlank()) {
                return resolved;
            }
            AgentModels.PlanningOrchestratorRequest orchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.PlanningOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

        private static String resolveTicketGoal(
                ActionContext context,
                AgentModels.TicketAgentRequests input
        ) {
            String resolved = input != null
                    ? input.prettyPrint(new AgentContext.AgentSerializationCtx.GoalResolutionSerialization())
                    : "";
            if (resolved != null && !resolved.isBlank()) {
                return resolved;
            }
            AgentModels.TicketOrchestratorRequest orchestratorRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.TicketOrchestratorRequest.class);
            return firstNonBlank(orchestratorRequest != null ? orchestratorRequest.goal() : null);
        }

        private static String resolveMergeSummary(
                AgentModels.MergerRequest request,
                AgentModels.MergerRouting routing
        ) {
            String requestSummary = request != null
                    ? request.prettyPrint(new AgentContext.AgentSerializationCtx.MergeSummarySerialization())
                    : "";
            AgentModels.MergerAgentResult result = routing != null ? routing.mergerResult() : null;
            String resultSummary = result != null
                    ? result.prettyPrint(new AgentContext.AgentSerializationCtx.MergeSummarySerialization())
                    : "";
            return firstNonBlank(requestSummary, resultSummary);
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


    }

    @Agent(
            name = WORKFLOW_TICKET_DISPATCH_SUBAGENT,
            description = "Runs ticket agent request in a subprocess"
    )
    @RequiredArgsConstructor
    class TicketDispatchSubagent implements AgentInterfaces {
        private final EventBus eventBus;
        private final PromptContextFactory promptContextFactory;
        private final InterruptService interruptService;
        private final com.hayden.multiagentide.service.LlmRunner llmRunner;
        private final WorkflowGraphService workflowGraphService;

        @Autowired(required = false)
        private List<ResultDecorator> resultDecorators;

        @Override
        public String multiAgentAgentName() {
            return WORKFLOW_TICKET_DISPATCH_SUBAGENT;
        }

        void emitActionStarted(EventBus eventBus, String agentName, String actionName, OperationContext context) {
            AgentInterfaces.emitActionStarted(eventBus, agentName, actionName, context);
        }

        private PromptContext buildPromptContext(AgentType agentType, Object input, OperationContext context) {
            BlackboardHistory history = BlackboardHistory.getEntireBlackboardHistory(context);
            return promptContextFactory.build(agentType, input, history);
        }

        @Action
        @AchievesGoal(description = "Handle context agent request")
        public AgentModels.TicketAgentResult ranTicketAgentResult(
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
            emitActionStarted(eventBus, "", "ticket-agent-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "transitionToInterruptState", interruptRequest, null);
            AgentModels.TicketAgentRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.TicketAgentRequest.class);
            if (lastRequest == null) {
                lastRequest = new AgentModels.TicketAgentRequest("Resume ticket execution", null);
            }
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_AGENT, lastRequest, context);
            GraphNode originNode = workflowGraphService.findNodeForContext(context)
                    .orElseGet(() -> workflowGraphService.requireTicketOrchestrator(context));
            workflowGraphService.handleAgentInterrupt(originNode, interruptRequest);
            AgentModels.TicketAgentRouting routing = interruptService.handleInterrupt(
                    context,
                    interruptRequest,
                    originNode,
                    "workflow/ticket_agent",
                    promptContext,
                    Map.of(
                            "ticketDetails", lastRequest.ticketDetails() != null ? lastRequest.ticketDetails() : "",
                            "ticketDetailsFilePath", lastRequest.ticketDetailsFilePath() != null ? lastRequest.ticketDetailsFilePath() : ""
                    ),
                    AgentModels.TicketAgentRouting.class
            );
            emitActionCompleted(eventBus, "", "ticket-agent-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action
        public AgentModels.TicketAgentRouting runTicketAgent(
                AgentModels.TicketAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "ticket-agent", context);
            PromptContext promptContext = buildPromptContext(AgentType.TICKET_AGENT, input, context);

            AgentModels.TicketAgentRouting routing = llmRunner.runWithTemplate(
                    "workflow/ticket_agent",
                    promptContext,
                    Map.of(
                            "ticketDetails", input.ticketDetails() != null ? input.ticketDetails() : "",
                            "ticketDetailsFilePath", input.ticketDetailsFilePath() != null ? input.ticketDetailsFilePath() : ""
                    ),
                    ToolContext.empty(),
                    AgentModels.TicketAgentRouting.class,
                    context
            );

            emitActionCompleted(eventBus, "", "ticket-agent", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }
    }

    @Agent(
            name = WORKFLOW_PLANNING_DISPATCH_SUBAGENT,
            description = "Runs planning agent request in a subprocess"
    )
    @RequiredArgsConstructor
    class PlanningDispatchSubagent implements AgentInterfaces{

        private final EventBus eventBus;
        private final PromptContextFactory promptContextFactory;
        private final RequestEnrichment requestEnrichment;
        private final InterruptService interruptService;
        private final com.hayden.multiagentide.service.LlmRunner llmRunner;
        private final WorkflowGraphService workflowGraphService;

        @Autowired(required = false)
        private List<ResultDecorator> resultDecorators;

        @Override
        public String multiAgentAgentName() {
            return WORKFLOW_PLANNING_DISPATCH_SUBAGENT;
        }

        void emitActionStarted(EventBus eventBus, String agentName, String actionName, OperationContext context) {
            AgentInterfaces.emitActionStarted(eventBus, agentName, actionName, context);
        }

        private PromptContext buildPromptContext(AgentType agentType, Object input, OperationContext context) {
            BlackboardHistory history = BlackboardHistory.getEntireBlackboardHistory(context);
            return promptContextFactory.build(agentType, input, history);
        }

        @Action
        @AchievesGoal(description = "Handle context agent request")
        public AgentModels.PlanningAgentResult ranPlanningAgent(
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
            emitActionStarted(eventBus, "", "planning-agent-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "transitionToInterruptState", interruptRequest, requestEnrichment);
            AgentModels.PlanningAgentRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.PlanningAgentRequest.class);
            if (lastRequest == null) {
                lastRequest = new AgentModels.PlanningAgentRequest("Resume planning");
            }
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_AGENT, lastRequest, context);
            GraphNode originNode = workflowGraphService.findNodeForContext(context)
                    .orElseGet(() -> workflowGraphService.requirePlanningOrchestrator(context));
            workflowGraphService.handleAgentInterrupt(originNode, interruptRequest);
            AgentModels.PlanningAgentRouting routing = interruptService.handleInterrupt(
                    context,
                    interruptRequest,
                    originNode,
                    "workflow/planning_agent",
                    promptContext,
                    Map.of("goal", Objects.toString(lastRequest.goal(), "")),
                    AgentModels.PlanningAgentRouting.class
            );
            emitActionCompleted(eventBus, "", "planning-agent-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action(canRerun = true)
        public AgentModels.PlanningAgentRouting runPlanningAgent(
                AgentModels.PlanningAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "planning-agent", context);
            PromptContext promptContext = buildPromptContext(AgentType.PLANNING_AGENT, input, context);

            AgentModels.PlanningAgentRouting routing = llmRunner.runWithTemplate(
                    "workflow/planning_agent",
                    promptContext,
                    Map.of("goal", input.goal()),
                    ToolContext.empty(),
                    AgentModels.PlanningAgentRouting.class,
                    context
            );

            emitActionCompleted(eventBus, "", "planning-agent", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }
    }

    @Agent(
            name = WORKFLOW_DISCOVERY_DISPATCH_SUBAGENT,
            description = "Runs discovery agent request in a subprocess"
    )
    @RequiredArgsConstructor
    class DiscoveryDispatchSubagent implements AgentInterfaces{

        private final EventBus eventBus;
        private final PromptContextFactory promptContextFactory;
        private final InterruptService interruptService;
        private final com.hayden.multiagentide.service.LlmRunner llmRunner;
        private final WorkflowGraphService workflowGraphService;

        @Autowired(required = false)
        private List<ResultDecorator> resultDecorators;

        @Override
        public String multiAgentAgentName() {
            return WORKFLOW_DISCOVERY_DISPATCH_SUBAGENT;
        }

        void emitActionStarted(EventBus eventBus, String agentName, String actionName, OperationContext context) {
            AgentInterfaces.emitActionStarted(eventBus, agentName, actionName, context);
        }

        private PromptContext buildPromptContext(AgentType agentType, Object input, OperationContext context) {
            BlackboardHistory history = BlackboardHistory.getEntireBlackboardHistory(context);
            return promptContextFactory.build(agentType, input, history);
        }

        @Action
        @AchievesGoal(description = "Handle context agent request")
        public AgentModels.DiscoveryAgentResult ranDiscoveryAgent(
                AgentModels.DiscoveryAgentResult input,
                OperationContext context
        ) {
//            do an event emission of complete
            return input;
        }

        @Action
        public AgentModels.DiscoveryAgentRouting transitionToInterruptState(
                AgentModels.DiscoveryAgentInterruptRequest interruptRequest,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "discovery-agent-interrupt", context);
            BlackboardHistory.registerAndHideInput(context, "transitionToInterruptState", interruptRequest, null);
            AgentModels.DiscoveryAgentRequest lastRequest =
                    BlackboardHistory.getLastFromHistory(context, AgentModels.DiscoveryAgentRequest.class);
            if (lastRequest == null) {
                lastRequest = new AgentModels.DiscoveryAgentRequest("Resume discovery", "");
            }
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_AGENT, lastRequest, context);
            GraphNode originNode = workflowGraphService.findNodeForContext(context)
                    .orElseGet(() -> workflowGraphService.requireDiscoveryOrchestrator(context));
            workflowGraphService.handleAgentInterrupt(originNode, interruptRequest);
            AgentModels.DiscoveryAgentRouting routing = interruptService.handleInterrupt(
                    context,
                    interruptRequest,
                    originNode,
                    "workflow/discovery_agent",
                    promptContext,
                    Map.of(
                            "goal", Objects.toString(lastRequest.goal(), ""),
                            "subdomainFocus", Objects.toString(lastRequest.subdomainFocus(), "")
                    ),
                    AgentModels.DiscoveryAgentRouting.class
            );
            emitActionCompleted(eventBus, "", "discovery-agent-interrupt", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }

        @Action
        public AgentModels.DiscoveryAgentRouting runDiscoveryAgent(
                AgentModels.DiscoveryAgentRequest input,
                OperationContext context
        ) {
            emitActionStarted(eventBus, "", "discovery-agent", context);
            PromptContext promptContext = buildPromptContext(AgentType.DISCOVERY_AGENT, input, context);

            AgentModels.DiscoveryAgentRouting routing = llmRunner.runWithTemplate(
                    "workflow/discovery_agent",
                    promptContext,
                    Map.of("goal", input.goal(), "subdomainFocus", input.subdomainFocus()),
                    ToolContext.empty(),
                    AgentModels.DiscoveryAgentRouting.class,
                    context
            );

            emitActionCompleted(eventBus, "", "discovery-agent", context, routing);
            return AgentInterfaces.decorateRouting(routing, context, resultDecorators);
        }
    }

    static <T extends AgentModels.Routing> T decorateRouting(
            T routing,
            OperationContext context,
            List<ResultDecorator> decorators
    ) {
        if (routing == null || decorators == null || decorators.isEmpty()) {
            return routing;
        }
        T decorated = routing;
        for (ResultDecorator decorator : decorators) {
            if (decorator == null) {
                continue;
            }
            decorated = decorator.decorate(decorated, context);
        }
        return decorated;
    }

    static void emitActionStarted(EventBus eventBus, String agentName, String actionName, OperationContext context) {
        if (eventBus == null) {
            return;
        }
        BlackboardHistory.ensureSubscribed(
                eventBus, context,
                () -> com.hayden.multiagentidelib.agent.WorkflowGraphState.initial(resolveNodeId(context)));
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

        if (result instanceof AgentModels.OrchestratorCollectorResult) {
            BlackboardHistory.unsubscribe(eventBus, context);
        }
        if (result instanceof AgentModels.DiscoveryAgentRouting r
                && r.agentResult() != null) {
            BlackboardHistory.unsubscribe(eventBus, context);
        }
        if (result instanceof AgentModels.PlanningAgentRouting r
                && r.agentResult() != null) {
            BlackboardHistory.unsubscribe(eventBus, context);
        }
        if (result instanceof AgentModels.TicketAgentRouting r
                && r.agentResult() != null) {
            BlackboardHistory.unsubscribe(eventBus, context);
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

}
