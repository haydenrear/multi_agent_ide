package com.hayden.multiagentide.config;

import com.hayden.multiagentide.agent.GraphAgentFactory;
import com.hayden.multiagentide.model.mixins.GraphAgent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Spring configuration for Multi-Agent IDE.
 * Initializes agent factory registration.
 */
@Configuration
public class MultiAgentIdeConfiguration {

    @Bean
    public InitializingBean registerGraphAgents(GraphAgentFactory factory, List<GraphAgent> agents) {
        return () -> {
            factory.clear();
            agents.forEach(factory::registerAgent);
        };
    }
}
