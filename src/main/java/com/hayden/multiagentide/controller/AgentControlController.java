package com.hayden.multiagentide.controller;

import com.hayden.multiagentide.service.AgentControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentControlController {

    private final AgentControlService agentControlService;

    @PostMapping("/{nodeId}/pause")
    public ControlActionResponse pause(
            @PathVariable String nodeId,
            @RequestBody(required = false) ControlActionRequest request
    ) {
        String actionId = agentControlService.requestPause(nodeId, request != null ? request.message() : null);
        return new ControlActionResponse(actionId, "queued");
    }

    @PostMapping("/{nodeId}/stop")
    public ControlActionResponse stop(@PathVariable String nodeId) {
        String actionId = agentControlService.requestStop(nodeId);
        return new ControlActionResponse(actionId, "queued");
    }

    @PostMapping("/{nodeId}/resume")
    public ControlActionResponse resume(
            @PathVariable String nodeId,
            @RequestBody(required = false) ControlActionRequest request
    ) {
        String actionId = agentControlService.requestResume(nodeId, request != null ? request.message() : null);
        return new ControlActionResponse(actionId, "queued");
    }

    @PostMapping("/{nodeId}/prune")
    public ControlActionResponse prune(
            @PathVariable String nodeId,
            @RequestBody(required = false) ControlActionRequest request
    ) {
        String actionId = agentControlService.requestPrune(nodeId, request != null ? request.message() : null);
        return new ControlActionResponse(actionId, "queued");
    }

    @PostMapping("/{nodeId}/branch")
    public ControlActionResponse branch(
            @PathVariable String nodeId,
            @RequestBody(required = false) ControlActionRequest request
    ) {
        String actionId = agentControlService.requestBranch(nodeId, request != null ? request.message() : null);
        return new ControlActionResponse(actionId, "queued");
    }

    @PostMapping("/{nodeId}/review-request")
    public ControlActionResponse reviewRequest(
            @PathVariable String nodeId,
            @RequestBody(required = false) ControlActionRequest request
    ) {
        String actionId = agentControlService.requestReview(nodeId, request != null ? request.message() : null);
        return new ControlActionResponse(actionId, "queued");
    }

    public record ControlActionRequest(String message) {
    }

    public record ControlActionResponse(String actionId, String status) {
    }
}
