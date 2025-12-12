package com.hayden.multiagentide.infrastructure;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.model.mixins.MergeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgentRunner {

    private final AgentInterfaces.DiscoveryAgent discoveryAgent;

    private final AgentInterfaces.PlanningAgent planningAgent;

    private final AgentInterfaces.DiscoveryOrchestrator discoveryOrchestrator;

    private final AgentInterfaces.MergerAgent mergerAgent;

    public String doPerformMerge(MergeNode mergeNode) {

        return null;
    }


}
