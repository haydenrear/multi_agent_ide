package com.hayden.multiagentide.agent;

import com.hayden.commitdiffcontext.mcp.ToolCarrier;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.infrastructure.McpRequestContext;
import com.hayden.multiagentide.model.events.Events;
import com.hayden.multiagentide.model.ui.GuiEmissionResult;
import com.hayden.multiagentide.model.ui.GuiEventPayload;
import com.hayden.multiagentide.model.ui.UiDiffRequest;
import com.hayden.multiagentide.model.ui.UiDiffResult;
import com.hayden.multiagentide.model.ui.UiStateSnapshot;
import com.hayden.multiagentide.service.UiStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Tool definitions for Embabel agents.
 * These tools are available to all agents via the agent execution framework.
 */
@Component
@RequiredArgsConstructor
public class AgentTools implements ToolCarrier {

    public static final String UI_SESSION_HEADER = "X-AG-UI-SESSION";

    private final EventBus eventBus;
    private final UiStateService uiStateService;

    /**
     * Should push an event to the socket to be displayed by the frontend
     * @return
     */
    @org.springframework.ai.tool.annotation.Tool(description = "Emit gui event to user")
    public GuiEmissionResult emitGuiEvent(GuiEventPayload payload) {
        if (payload == null) {
            return new GuiEmissionResult("rejected", "invalid_payload", "Payload is required.", true);
        }
        String sessionId = resolveSessionId(payload.sessionId());
        if (!StringUtils.hasText(sessionId)) {
            return new GuiEmissionResult("rejected", "missing_session", "Session id is required.", true);
        }
        Object renderTree = payload.renderTree() != null ? payload.renderTree() : payload.a2uiMessages();
        if (renderTree == null) {
            return new GuiEmissionResult("rejected", "missing_render_tree", "Render tree or a2uiMessages required.", true);
        }
        UiStateSnapshot snapshot = uiStateService.captureSnapshot(sessionId, renderTree);

        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("sessionId", sessionId);
        payloadMap.put("renderer", payload.renderer());
        payloadMap.put("title", payload.title());
        payloadMap.put("props", payload.props());
        payloadMap.put("a2uiMessages", payload.a2uiMessages());
        payloadMap.put("renderTree", renderTree);
        payloadMap.put("summary", payload.summary());
        if (snapshot != null) {
            payloadMap.put("revision", snapshot.revision());
        }

        String eventId = UUID.randomUUID().toString();
        eventBus.publish(new Events.GuiRenderEvent(
                eventId,
                Instant.now(),
                sessionId,
                sessionId,
                payloadMap
        ));
        return new GuiEmissionResult("accepted", null, "Gui event emitted.", false);
    }

    @org.springframework.ai.tool.annotation.Tool(description = "Retrieve the current GUI snapshot for a session")
    public UiStateSnapshot retrieveGui(String sessionId) {
        String resolvedSessionId = resolveSessionId(sessionId);
        String safeSessionId = StringUtils.hasText(resolvedSessionId) ? resolvedSessionId : "unknown";
        UiStateSnapshot snapshot = uiStateService.getSnapshot(safeSessionId);
        if (snapshot != null) {
            return snapshot;
        }
        return new UiStateSnapshot(safeSessionId, null, Instant.now(), Map.of());
    }

    @org.springframework.ai.tool.annotation.Tool(description = "Apply a UI diff to the current GUI state")
    public UiDiffResult performUiDiff(UiDiffRequest request) {
        if (request == null) {
            return new UiDiffResult("rejected", null, "invalid_payload", "Diff payload is required.");
        }
        String sessionId = resolveSessionId(request.sessionId());
        if (!StringUtils.hasText(sessionId)) {
            return new UiDiffResult("rejected", null, "missing_session", "Session id is required.");
        }
        UiDiffRequest resolved = new UiDiffRequest(
                sessionId,
                request.baseRevision(),
                request.diff(),
                request.summary()
        );
        UiDiffResult result = uiStateService.applyDiff(resolved);
        UiStateSnapshot snapshot = uiStateService.getSnapshot(sessionId);

        if ("applied".equalsIgnoreCase(result.status()) && snapshot != null) {
            eventBus.publish(new Events.UiDiffAppliedEvent(
                    UUID.randomUUID().toString(),
                    Instant.now(),
                    sessionId,
                    sessionId,
                    snapshot.revision(),
                    snapshot.renderTree(),
                    request.summary()
            ));
        } else if (!"applied".equalsIgnoreCase(result.status())) {
            eventBus.publish(new Events.UiDiffRejectedEvent(
                    UUID.randomUUID().toString(),
                    Instant.now(),
                    sessionId,
                    sessionId,
                    result.errorCode(),
                    result.message()
            ));
        }
        return result;
    }

    private String resolveSessionId(String sessionId) {
        if (StringUtils.hasText(sessionId)) {
            return sessionId;
        }
        return McpRequestContext.getHeader(UI_SESSION_HEADER);
    }

}
