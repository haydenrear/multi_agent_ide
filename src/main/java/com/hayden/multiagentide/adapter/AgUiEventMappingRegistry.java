package com.hayden.multiagentide.adapter;

import com.agui.core.types.BaseEvent;
import com.agui.core.types.CustomEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.multiagentide.model.events.Events;
import jakarta.annotation.PostConstruct;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class AgUiEventMappingRegistry {

    private static volatile AgUiEventMappingRegistry instance;

    private final ObjectMapper objectMapper;
    private final Json json = Json.Default;
    private final Map<String, Function<Events.GraphEvent, BaseEvent>> mappings = new ConcurrentHashMap<>();

    public AgUiEventMappingRegistry(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void registerDefaults() {
        instance = this;
        mappings.put("GUI_RENDER", event -> mapWithPayload(event, ((Events.GuiRenderEvent) event).payload()));
        mappings.put("UI_DIFF_APPLIED", event -> mapWithPayload(event, buildDiffPayload((Events.UiDiffAppliedEvent) event)));
        mappings.put("UI_DIFF_REVERTED", event -> mapWithPayload(event, buildRevertPayload((Events.UiDiffRevertedEvent) event)));
        mappings.put("UI_DIFF_REJECTED", event -> mapWithPayload(event, buildRejectPayload((Events.UiDiffRejectedEvent) event)));
        mappings.put("UI_FEEDBACK", event -> mapWithPayload(event, buildFeedbackPayload((Events.UiFeedbackEvent) event)));
    }

    public void registerMapping(String eventType, Function<Events.GraphEvent, BaseEvent> mapper) {
        mappings.put(eventType, mapper);
    }

    public static BaseEvent map(Events.GraphEvent event) {
        if (instance == null) {
            return null;
        }
        return instance.mapEvent(event);
    }

    private BaseEvent mapEvent(Events.GraphEvent event) {
        if (event instanceof Events.ToolCallEvent toolCallEvent) {
            return mapWithPayload(event, buildToolPayload(toolCallEvent));
        }
        if (event instanceof Events.NodeStreamDeltaEvent streamDeltaEvent) {
            return mapWithPayload(event, buildStreamPayload(streamDeltaEvent));
        }
        Function<Events.GraphEvent, BaseEvent> mapper = mappings.get(event.eventType());
        BaseEvent mapped = mapper != null ? mapper.apply(event) : mapWithPayload(event, null);
        return mapped;
    }

    private BaseEvent mapWithPayload(Events.GraphEvent event, Object payload) {
        JsonElement rawEvent = toJsonElement(event);
        JsonElement value = payload != null ? toJsonElement(payload) : JsonNull.INSTANCE;
        Instant timestamp = event.timestamp();
        Long timestampValue = timestamp != null ? timestamp.toEpochMilli() : null;
        return new CustomEvent(event.eventType(), value, timestampValue, rawEvent);
    }

    private Map<String, Object> buildDiffPayload(Events.UiDiffAppliedEvent event) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("renderTree", event.renderTree());
        payload.put("revision", event.revision());
        payload.put("summary", event.summary());
        return payload;
    }

    private Map<String, Object> buildRevertPayload(Events.UiDiffRevertedEvent event) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("renderTree", event.renderTree());
        payload.put("revision", event.revision());
        payload.put("sourceEventId", event.sourceEventId());
        return payload;
    }

    private Map<String, Object> buildRejectPayload(Events.UiDiffRejectedEvent event) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("errorCode", event.errorCode());
        payload.put("message", event.message());
        return payload;
    }

    private Map<String, Object> buildFeedbackPayload(Events.UiFeedbackEvent event) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("message", event.message());
        payload.put("snapshot", event.snapshot());
        payload.put("sourceEventId", event.sourceEventId());
        return payload;
    }

    private Map<String, Object> buildToolPayload(Events.ToolCallEvent event) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("sessionId", event.nodeId());
        payload.put("title", event.title());

        Map<String, Object> props = new LinkedHashMap<>();
        props.put("toolName", event.title());
        props.put("phase", event.phase());
        props.put("status", event.status());
        props.put("kind", event.kind());
        props.put("content", event.content());
        props.put("locations", event.locations());
        props.put("rawInput", event.rawInput());
        props.put("rawOutput", event.rawOutput());

        Map<String, Object> input = parseJsonObject(event.rawInput());
        Map<String, Object> output = parseJsonObject(event.rawOutput());
        String path = coerceString(input != null ? input.get("path") : null);
        if (path == null) {
            path = coerceString(output != null ? output.get("path") : null);
        }
        String content = coerceString(input != null ? input.get("content") : null);
        if (content == null) {
            content = coerceString(output != null ? output.get("content") : null);
        }
        Integer line = coerceInt(input != null ? input.get("line") : null);
        Integer limit = coerceInt(input != null ? input.get("limit") : null);

        if (path != null) {
            props.put("path", path);
        }
        if (content != null) {
            props.put("content", content);
        }
        if (line != null) {
            props.put("line", line);
        }
        if (limit != null) {
            props.put("limit", limit);
        }

        String renderer = resolveToolRenderer(event.title(), path, content, line, limit);
        payload.put("renderer", renderer);
        payload.put("props", props);

        Map<String, Object> fallback = new LinkedHashMap<>();
        fallback.put("rawInput", event.rawInput());
        fallback.put("rawOutput", event.rawOutput());
        payload.put("fallback", fallback);
        return payload;
    }

    private Map<String, Object> buildStreamPayload(Events.NodeStreamDeltaEvent event) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("renderer", "stream");
        payload.put("sessionId", event.nodeId());
        payload.put("title", "Streaming output");

        Map<String, Object> props = new LinkedHashMap<>();
        props.put("content", event.deltaContent());
        props.put("tokenCount", event.tokenCount());
        props.put("isFinal", event.isFinal());
        payload.put("props", props);
        return payload;
    }

    private Map<String, Object> parseJsonObject(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Map<?, ?> map) {
            Map<String, Object> output = new LinkedHashMap<>();
            map.forEach((key, val) -> {
                if (key != null) {
                    output.put(key.toString(), val);
                }
            });
            return output;
        }
        if (value instanceof String text) {
            try {
                return objectMapper.readValue(text, Map.class);
            } catch (JsonProcessingException ignored) {
                return null;
            }
        }
        return null;
    }

    private String coerceString(Object value) {
        return value instanceof String text ? text : null;
    }

    private Integer coerceInt(Object value) {
        if (value instanceof Integer integer) {
            return integer;
        }
        if (value instanceof Number number) {
            return number.intValue();
        }
        return null;
    }

    private String resolveToolRenderer(
            String title,
            String path,
            String content,
            Integer line,
            Integer limit
    ) {
        String normalized = title != null ? title.toLowerCase(Locale.ROOT) : "";
        boolean isWrite = normalized.contains("write") || normalized.contains("save")
                || normalized.contains("edit") || (path != null && content != null);
        boolean isRead = normalized.contains("read") || normalized.contains("open")
                || (path != null && (line != null || limit != null));
        if (isWrite) {
            return "tool-write";
        }
        if (isRead) {
            return "tool-read";
        }
        return "tool-generic";
    }

    private JsonElement toJsonElement(Object value) {
        if (value == null) {
            return JsonNull.INSTANCE;
        }
        try {
            String jsonString = objectMapper.writeValueAsString(value);
            return json.parseToJsonElement(jsonString);
        } catch (JsonProcessingException e) {
            return JsonNull.INSTANCE;
        }
    }
}
