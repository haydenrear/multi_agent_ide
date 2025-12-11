package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.model.mixins.GraphAgent;
import com.hayden.multiagentide.model.mixins.GraphNode;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Factory for creating and managing graph agents.
 * Matches agent types to node types for execution.
 */
@Service
public class GraphAgentFactory {

    private final Map<String, GraphAgent> agentsById = new HashMap<>();
    private final List<GraphAgent> agents = new ArrayList<>();

    /**
     * Register an agent.
     */
    public void registerAgent(GraphAgent agent) {
        agents.add(agent);
        agentsById.put(agent.agentId(), agent);
    }

    /**
     * Get agent for a node.
     * Returns the first agent that supports the node.
     */
    public Optional<GraphAgent> getAgentFor(GraphNode node) {
        return agents.stream()
                .filter(agent -> agent.supports(node))
                .findFirst();
    }

    /**
     * Get all agents.
     */
    public List<GraphAgent> getAllAgents() {
        return new ArrayList<>(agents);
    }

    /**
     * Get agent by ID.
     */
    public Optional<GraphAgent> getAgent(String agentId) {
        return Optional.ofNullable(agentsById.get(agentId));
    }

    /**
     * Get agents by type name.
     */
    public List<GraphAgent> getAgentsByType(String agentType) {
        return agents.stream()
                .filter(agent -> agent.agentType().equals(agentType))
                .toList();
    }

    /**
     * Check if agent type exists.
     */
    public boolean hasAgentType(String agentType) {
        return agents.stream().anyMatch(agent -> agent.agentType().equals(agentType));
    }

    /**
     * Get count of registered agents.
     */
    public int agentCount() {
        return agents.size();
    }

    /**
     * Clear all agents.
     */
    public void clear() {
        agents.clear();
        agentsById.clear();
    }
}
