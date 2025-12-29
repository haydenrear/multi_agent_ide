package com.hayden.multiagentide.agent;

import com.hayden.commitdiffcontext.mcp.ToolCarrier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Tool definitions for Embabel agents.
 * These tools are available to all agents via the agent execution framework.
 */
@Component
@RequiredArgsConstructor
public class AgentTools implements ToolCarrier {


    /**
     * Should push an event to the socket to be displayed by the frontend
     * @return
     */
    @org.springframework.ai.tool.annotation.Tool(description = "Emit gui event to user")
    public String emitGuiEvent() {
        return null;
    }

}
