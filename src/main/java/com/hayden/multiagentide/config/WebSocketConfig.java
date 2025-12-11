package com.hayden.multiagentide.config;

import com.hayden.multiagentide.adapter.WebSocketEventAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

/**
 * WebSocket configuration for real-time event streaming.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketEventAdapter eventAdapter;

    public WebSocketConfig(WebSocketEventAdapter eventAdapter) {
        this.eventAdapter = eventAdapter;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new EventStreamHandler(eventAdapter), "/ws/events")
                .setAllowedOrigins("*");
    }

    /**
     * WebSocket handler for event streaming.
     */
    @Component
    public static class EventStreamHandler extends TextWebSocketHandler {

        private final WebSocketEventAdapter eventAdapter;

        public EventStreamHandler(WebSocketEventAdapter eventAdapter) {
            this.eventAdapter = eventAdapter;
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            eventAdapter.registerClient(session);
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
            eventAdapter.unregisterClient(session);
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            // Echo back for now; can implement command handling here
            session.sendMessage(new TextMessage("Event stream connected"));
        }
    }
}
