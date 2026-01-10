package com.hayden.multiagentide.controller;

import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.infrastructure.EventBus;
import com.hayden.multiagentidelib.model.events.Events;
import com.hayden.multiagentide.service.AgentControlService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interrupts")
@RequiredArgsConstructor
public class InterruptController {

    private final AgentControlService agentControlService;
    private final EventBus eventBus;

    @PostMapping
    public InterruptStatusResponse requestInterrupt(@RequestBody InterruptRequest request) {
        String interruptId = UUID.randomUUID().toString();
        String reason = request.reason() != null ? request.reason() : "Interrupt requested";
        AgentModels.InterruptType type = request.type();
        switch (type) {
            case PAUSE -> agentControlService.requestPause(request.originNodeId(), reason);
            case STOP -> agentControlService.requestStop(request.originNodeId());
            case HUMAN_REVIEW -> agentControlService.requestReview(request.originNodeId(), reason);
            case PRUNE -> {
                eventBus.publish(new Events.NodePrunedEvent(
                        interruptId,
                        Instant.now(),
                        request.originNodeId(),
                        reason,
                        List.of()
                ));
            }
            default -> throw new IllegalArgumentException("Unsupported interrupt type: " + type);
        }
        return new InterruptStatusResponse(interruptId, "REQUESTED", request.originNodeId(), request.originNodeId());
    }

    @PostMapping("/{interruptId}/resolve")
    public InterruptStatusResponse resolveInterrupt(
            @PathVariable String interruptId,
            @RequestBody InterruptResolution request
    ) {
        String message = request.resolutionNotes() != null ? request.resolutionNotes() : "Interrupt resolved";
        eventBus.publish(new Events.ResolveInterruptEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                request.originNodeId(),
                message
        ));
        return new InterruptStatusResponse(interruptId, "RESOLVED", request.originNodeId(), request.originNodeId());
    }

    @GetMapping("/{interruptId}")
    public InterruptStatusResponse getStatus(@PathVariable String interruptId) {
        return new InterruptStatusResponse(interruptId, "UNKNOWN", null, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @SuppressWarnings("unused")
    public record InterruptError(String message) {
    }

    public record InterruptRequest(
            AgentModels.InterruptType type,
            String originNodeId,
            String reason
    ) {
    }

    public record InterruptResolution(
            String originNodeId,
            String resolutionType,
            String resolutionNotes
    ) {
    }

    public record InterruptStatusResponse(
            String interruptId,
            String status,
            String originNodeId,
            String resumeNodeId
    ) {
    }
}
