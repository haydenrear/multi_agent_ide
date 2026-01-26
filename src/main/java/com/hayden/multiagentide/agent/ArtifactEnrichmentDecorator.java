package com.hayden.multiagentide.agent;

import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.service.RequestEnrichment;
import com.hayden.utilitymodule.acp.events.Artifact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtifactEnrichmentDecorator implements ResultDecorator {

    private final RequestEnrichment requestEnrichment;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AgentModels.Routing> T decorate(T t, OperationContext context) {
        if (t == null) {
            return null;
        }

        return switch (t) {
            case AgentModels.ContextManagerResultRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .orchestratorRequest(enrichRequest(routing.orchestratorRequest(), context))
                            .orchestratorCollectorRequest(enrichRequest(routing.orchestratorCollectorRequest(), context))
                            .discoveryOrchestratorRequest(enrichRequest(routing.discoveryOrchestratorRequest(), context))
                            .discoveryCollectorRequest(enrichRequest(routing.discoveryCollectorRequest(), context))
                            .planningOrchestratorRequest(enrichRequest(routing.planningOrchestratorRequest(), context))
                            .planningCollectorRequest(enrichRequest(routing.planningCollectorRequest(), context))
                            .ticketOrchestratorRequest(enrichRequest(routing.ticketOrchestratorRequest(), context))
                            .ticketCollectorRequest(enrichRequest(routing.ticketCollectorRequest(), context))
                            .reviewRequest(enrichRequest(routing.reviewRequest(), context))
                            .mergerRequest(enrichRequest(routing.mergerRequest(), context))
                            .planningAgentRequest(enrichRequest(routing.planningAgentRequest(), context))
                            .planningAgentRequests(enrichRequest(routing.planningAgentRequests(), context))
                            .planningAgentResults(enrichRequest(routing.planningAgentResults(), context))
                            .ticketAgentRequest(enrichRequest(routing.ticketAgentRequest(), context))
                            .ticketAgentRequests(enrichRequest(routing.ticketAgentRequests(), context))
                            .ticketAgentResults(enrichRequest(routing.ticketAgentResults(), context))
                            .discoveryAgentRequest(enrichRequest(routing.discoveryAgentRequest(), context))
                            .discoveryAgentRequests(enrichRequest(routing.discoveryAgentRequests(), context))
                            .discoveryAgentResults(enrichRequest(routing.discoveryAgentResults(), context))
                            .contextOrchestratorRequest(enrichRequest(routing.contextOrchestratorRequest(), context))
                            .build();
            case AgentModels.DiscoveryAgentDispatchRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .collectorRequest(enrichRequest(routing.collectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.DiscoveryAgentRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .agentResult(enrichResult(routing.agentResult(), context))
                            .planningOrchestratorRequest(enrichRequest(routing.planningOrchestratorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.DiscoveryCollectorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .collectorResult(enrichResult(routing.collectorResult(), context))
                            .orchestratorRequest(enrichRequest(routing.orchestratorRequest(), context))
                            .discoveryRequest(enrichRequest(routing.discoveryRequest(), context))
                            .planningRequest(enrichRequest(routing.planningRequest(), context))
                            .ticketRequest(enrichRequest(routing.ticketRequest(), context))
                            .reviewRequest(enrichRequest(routing.reviewRequest(), context))
                            .mergerRequest(enrichRequest(routing.mergerRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.DiscoveryOrchestratorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .agentRequests(enrichRequest(routing.agentRequests(), context))
                            .collectorRequest(enrichRequest(routing.collectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.MergerRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .mergerResult(enrichResult(routing.mergerResult(), context))
                            .orchestratorCollectorRequest(enrichRequest(routing.orchestratorCollectorRequest(), context))
                            .discoveryCollectorRequest(enrichRequest(routing.discoveryCollectorRequest(), context))
                            .planningCollectorRequest(enrichRequest(routing.planningCollectorRequest(), context))
                            .ticketCollectorRequest(enrichRequest(routing.ticketCollectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.OrchestratorCollectorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .collectorResult(enrichResult(routing.collectorResult(), context))
                            .orchestratorRequest(enrichRequest(routing.orchestratorRequest(), context))
                            .discoveryRequest(enrichRequest(routing.discoveryRequest(), context))
                            .planningRequest(enrichRequest(routing.planningRequest(), context))
                            .ticketRequest(enrichRequest(routing.ticketRequest(), context))
                            .reviewRequest(enrichRequest(routing.reviewRequest(), context))
                            .mergerRequest(enrichRequest(routing.mergerRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.OrchestratorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .collectorRequest(enrichRequest(routing.collectorRequest(), context))
                            .orchestratorRequest(enrichRequest(routing.orchestratorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.PlanningAgentDispatchRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .planningCollectorRequest(enrichRequest(routing.planningCollectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.PlanningAgentRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .agentResult(enrichResult(routing.agentResult(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.PlanningCollectorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .collectorResult(enrichResult(routing.collectorResult(), context))
                            .planningRequest(enrichRequest(routing.planningRequest(), context))
                            .discoveryOrchestratorRequest(enrichRequest(routing.discoveryOrchestratorRequest(), context))
                            .reviewRequest(enrichRequest(routing.reviewRequest(), context))
                            .mergerRequest(enrichRequest(routing.mergerRequest(), context))
                            .ticketOrchestratorRequest(enrichRequest(routing.ticketOrchestratorRequest(), context))
                            .orchestratorCollectorRequest(enrichRequest(routing.orchestratorCollectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.PlanningOrchestratorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .agentRequests(enrichRequest(routing.agentRequests(), context))
                            .collectorRequest(enrichRequest(routing.collectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.ReviewRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .reviewResult(enrichResult(routing.reviewResult(), context))
                            .orchestratorCollectorRequest(enrichRequest(routing.orchestratorCollectorRequest(), context))
                            .discoveryCollectorRequest(enrichRequest(routing.discoveryCollectorRequest(), context))
                            .planningCollectorRequest(enrichRequest(routing.planningCollectorRequest(), context))
                            .ticketCollectorRequest(enrichRequest(routing.ticketCollectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.TicketAgentDispatchRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .ticketCollectorRequest(enrichRequest(routing.ticketCollectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.TicketAgentRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .agentResult(enrichResult(routing.agentResult(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.TicketCollectorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .collectorResult(enrichResult(routing.collectorResult(), context))
                            .ticketRequest(enrichRequest(routing.ticketRequest(), context))
                            .orchestratorCollectorRequest(enrichRequest(routing.orchestratorCollectorRequest(), context))
                            .reviewRequest(enrichRequest(routing.reviewRequest(), context))
                            .mergerRequest(enrichRequest(routing.mergerRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
            case AgentModels.TicketOrchestratorRouting routing ->
                    (T) routing.toBuilder()
                            .interruptRequest(enrichRequest(routing.interruptRequest(), context))
                            .agentRequests(enrichRequest(routing.agentRequests(), context))
                            .collectorRequest(enrichRequest(routing.collectorRequest(), context))
                            .contextManagerRequest(enrichRequest(routing.contextManagerRequest(), context))
                            .build();
        };
    }

    private <T extends AgentModels.AgentResult> T enrichResult(T value, OperationContext context) {
        if (value == null) {
            return null;
        }

        return requestEnrichment.enrich(value, context);
    }

    private <T extends AgentModels.AgentRequest> T enrichRequest(T request, OperationContext context) {
        if (request == null) {
            return null;
        }

        return requestEnrichment.enrich(request, context);
    }
}
