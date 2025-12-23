package com.hayden.multiagentide.adapter;

import com.agui.core.types.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AgUiSerdes {

    public String serializeEvent(@Nullable BaseEvent baseEvent) {
        log.error("Still haven't implemented serialization to ag-ui.");
        return null;
    }

}
