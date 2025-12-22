package com.hayden.multiagentide.agent;

import dev.langchain4j.agent.tool.Tool;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Tool definitions for LangChain4j agents.
 * These tools are available to all agents via the agent execution framework.
 */
@Component
@RequiredArgsConstructor
public class LangChain4jAgentTools {


    @Tool("Emit gui event to user")
    public String emitGuiEvent() {
        return null;
    }

}
