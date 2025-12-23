package com.hayden.multiagentide.service;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.events.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgentControlService {

    private final EventBus eventBus;

    public String requestPause(String nodeId, String message) {
        String eventId = UUID.randomUUID().toString();
        String note = message != null ? message : "Pause requested";
        eventBus.publish(new Events.PauseEvent(eventId, Instant.now(), nodeId, note));
        return eventId;
    }

    public String requestInterrupt(String nodeId) {
        String eventId = UUID.randomUUID().toString();
        eventBus.publish(new Events.InterruptAgentEvent(eventId, Instant.now(), nodeId));
        return eventId;
    }

    public String requestReview(String nodeId, String message) {
        String eventId = UUID.randomUUID().toString();
        String note = message != null ? message : "Review requested";
        eventBus.publish(new Events.NodeReviewRequestedEvent(
                eventId,
                Instant.now(),
                nodeId,
                nodeId,
                "human",
                note
        ));
        return eventId;
    }
}
