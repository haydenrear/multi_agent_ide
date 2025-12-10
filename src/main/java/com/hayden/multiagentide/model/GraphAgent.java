package com.hayden.multiagentide.model;

/**
 * Base interface for graph agents that operate on nodes.
 * Agents use LangChain4j for LLM interactions and handle specific node types.
 */
public interface GraphAgent {

    /**
     * Get the unique identifier for this agent.
     */
    String agentId();

    /**
     * Get the type/name of this agent.
     */
    String agentType();

    /**
     * Check if this agent can handle a specific node.
     */
    boolean supports(GraphNode node);

    /**
     * Execute one logical step for the given node.
     * Returns updated node state.
     */
    GraphNode execute(GraphNode node, ExecutionContext context) throws Exception;

    /**
     * Execution context passed to agents.
     */
    interface ExecutionContext {
        /**
         * Get a worktree context by ID.
         */
        WorktreeContext getWorktree(String worktreeId);

        /**
         * Get a spec by ID.
         */
        Spec getSpec(String specId);

        /**
         * Store a worktree context.
         */
        void saveWorktree(WorktreeContext worktree);

        /**
         * Store a spec.
         */
        void saveSpec(Spec spec);

        /**
         * Emit an event.
         */
        void emitEvent(Events.GraphEvent event);

        /**
         * Get metadata for the execution.
         */
        String getMetadata(String key);

        /**
         * Set metadata for the execution.
         */
        void setMetadata(String key, String value);
    }
}

/**
 * Specific agent types.
 */
class AgentTypes {
    public static final String PLANNING = "planning";
    public static final String EDITOR = "editor";
    public static final String HUMAN_REVIEW = "human_review";
    public static final String AGENT_REVIEW = "agent_review";
    public static final String MERGER = "merger";
    public static final String SUMMARIZER = "summarizer";
}
