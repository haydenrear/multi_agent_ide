package com.hayden.multiagentide.agent;

import com.hayden.commitdiffcontext.mcp.ToolCarrier;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Tool definitions for LangChain4j agents.
 * These tools are available to all agents via the agent execution framework.
 */
@Component
@RequiredArgsConstructor
public class LangChain4jAgentTools implements ToolCarrier {


    /**
     * Should push an event to the socket to be displayed by the frontend
     * @return
     */
    @Tool("Emit gui event to user")
    @org.springframework.ai.tool.annotation.Tool(description = "Emit gui event to user")
    public String emitGuiEvent() {
        return null;
    }

}
