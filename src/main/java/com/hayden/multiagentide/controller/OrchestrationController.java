package com.hayden.multiagentide.controller;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.model.mixins.OrchestratorNode;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for orchestration operations.
 * Provides endpoints for initializing goals, executing nodes, and checking status.
 */
@RequiredArgsConstructor
@Component
public class OrchestrationController {

    private final AgentInterfaces.OrchestratorAgent orchestrator;


}
