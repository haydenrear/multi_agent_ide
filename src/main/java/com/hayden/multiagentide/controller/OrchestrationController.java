package com.hayden.multiagentide.controller;

import com.hayden.multiagentide.agent.AgentInterfaces;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * REST controller for orchestration operations.
 * Provides endpoints for initializing goals, executing nodes, and checking status.
 */
@RequiredArgsConstructor
@Component
public class OrchestrationController {

    private final AgentInterfaces.OrchestratorAgent orchestrator;


}
