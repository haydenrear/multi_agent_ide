package com.hayden.multiagentide.adapter;

import com.hayden.multiagentide.infrastructure.EventAdapter;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.Events;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * WebSocket event adapter that streams events to connected clients in real-time.
 */
@Component
public class WebSocketEventAdapter extends EventAdapter {

    private final List<WebSocketSession> connectedClients = new CopyOnWriteArrayList<>();

    public WebSocketEventAdapter(EventBus eventBus) {
        super("websocket-adapter");
        eventBus.subscribe(this);
    }

    /**
     * Register a connected WebSocket client.
     */
    public void registerClient(WebSocketSession session) {
        connectedClients.add(session);
    }

    /**
     * Unregister a disconnected WebSocket client.
     */
    public void unregisterClient(WebSocketSession session) {
        connectedClients.remove(session);
    }

    /**
     * Get count of connected clients.
     */
    public int getConnectedClientCount() {
        return connectedClients.size();
    }

    @Override
    protected void adaptEvent(Events.GraphEvent event) {
        String eventJson = serializeEvent(event);

        for (WebSocketSession session : connectedClients) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(eventJson));
                } catch (IOException e) {
                    // If send fails, remove the client
                    unregisterClient(session);
                }
            }
        }
    }

    @Override
    protected void handleAdapterError(Events.GraphEvent event, Exception error) {
        System.err.println("WebSocket adapter error for event " + event.eventType() + ": " + error.getMessage());
    }

    @Override
    public String getAdapterType() {
        return "websocket";
    }

    /**
     * Serialize event to JSON.
     * In production, use Jackson or similar.
     */
    private String serializeEvent(Events.GraphEvent event) {
        // Simple JSON serialization
        return String.format("""
                {
                  "eventId": "%s",
                  "eventType": "%s",
                  "timestamp": "%s"
                }
                """, event.eventId(), event.eventType(), event.timestamp());
    }
}
