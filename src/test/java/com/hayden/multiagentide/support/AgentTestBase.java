package com.hayden.multiagentide.support;

import com.hayden.multiagentide.agent.AgentInterfaces;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public abstract class AgentTestBase {

    @MockitoBean
    protected AgentInterfaces.DiscoveryAgent discoveryAgent;

    @MockitoBean
    protected AgentInterfaces.DiscoveryOrchestrator discoveryOrchestrator;

    @MockitoBean
    protected AgentInterfaces.DiscoveryCollector discoveryCollector;

    @MockitoBean
    protected AgentInterfaces.PlanningAgent planningAgent;

    @MockitoBean
    protected AgentInterfaces.PlanningOrchestrator planningOrchestrator;

    @MockitoBean
    protected AgentInterfaces.PlanningCollector planningCollector;

    @MockitoBean
    protected AgentInterfaces.TicketOrchestrator ticketOrchestrator;

    @MockitoBean
    protected AgentInterfaces.TicketAgent ticketAgent;

    @MockitoBean
    protected AgentInterfaces.TicketCollector ticketCollector;

    @MockitoBean
    protected AgentInterfaces.ReviewAgent reviewAgent;

    @MockitoBean
    protected AgentInterfaces.MergerAgent mergerAgent;

    @MockitoBean
    protected AgentInterfaces.OrchestratorAgent orchestratorAgent;
}
