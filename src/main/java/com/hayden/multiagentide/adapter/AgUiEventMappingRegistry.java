package com.hayden.multiagentide.adapter;

import com.agui.core.types.BaseEvent;
import com.agui.core.types.CustomEvent;
import com.agui.core.types.StateDeltaEvent;
import com.agui.core.types.TextMessageContentEvent;
import com.hayden.multiagentide.model.events.Events;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class AgUiEventMappingRegistry {

    private static volatile AgUiEventMappingRegistry instance;

    private final Map<String, Function<Events.GraphEvent, BaseEvent>> mappings = new ConcurrentHashMap<>();

    @PostConstruct
    public void registerDefaults() {
        instance = this;
//        mappings.put("NODE_STATUS_CHANGED", this::mapStateDelta);
//        mappings.put("NODE_ADDED", this::mapStateDelta);
//        mappings.put("ADD_MESSAGE_EVENT", event -> mapMessageContent((Events.AddMessageEvent) event));
//        mappings.put("NODE_STREAM_DELTA", event -> mapMessageChunk((Events.NodeStreamDeltaEvent) event));
    }

    public void registerMapping(String eventType, Function<Events.GraphEvent, BaseEvent> mapper) {
        mappings.put(eventType, mapper);
    }

//    public BaseEvent mapEvent(Events.GraphEvent event) {
//        Function<Events.GraphEvent, BaseEvent> mapper = mappings.get(event.eventType());
//        BaseEvent mapped = mapper != null ? mapper.apply(event) : new CustomEvent();
//        Instant timestamp = event.timestamp();
//        if (timestamp != null) {
//            mapped.setTimestamp(timestamp.toEpochMilli());
//        }
//        mapped.setRawEvent(event);
//        return mapped;
//    }

    public static BaseEvent map(Events.GraphEvent event) {
//        if (instance == null) {
//            CustomEvent fallback = new CustomEvent();
//            fallback.setRawEvent(event);
//            return fallback;
//        }
//        return instance.mapEvent(event);
        return null;
    }

//    private BaseEvent mapStateDelta(Events.GraphEvent event) {
//        StateDeltaEvent mapped = new StateDeltaEvent();
//        mapped.setRawEvent(event);
//        return mapped;
//    }
//
//    private BaseEvent mapMessageContent(Events.AddMessageEvent event) {
//        TextMessageContentEvent mapped = new TextMessageContentEvent();
//        mapped.setMessageId(event.nodeId());
//        mapped.setDelta(event.toAddMessage());
//        return mapped;
//    }
//
//    private BaseEvent mapMessageChunk(Events.NodeStreamDeltaEvent event) {
//        TextMessageChunkEvent mapped = new TextMessageChunkEvent();
//        mapped.setMessageId(event.nodeId());
//        mapped.setRole("assistant");
//        mapped.setDelta(event.deltaContent());
//        return mapped;
//    }
}
