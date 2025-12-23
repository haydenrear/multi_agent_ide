package com.hayden.multiagentide.adapter;

import com.agui.core.types.BaseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AgUiSerdes {

    private final ObjectMapper objectMapper;

    public String serializeEvent(@Nullable BaseEvent baseEvent) {
        if (baseEvent == null) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(baseEvent);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize ag-ui event.", e);
            return "{}";
        }
    }

}
