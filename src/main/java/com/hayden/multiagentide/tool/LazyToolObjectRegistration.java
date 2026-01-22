package com.hayden.multiagentide.tool;

import com.embabel.agent.api.common.ToolObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.support.ToolUtils;
import org.springframework.ai.util.json.schema.JsonSchemaGenerator;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Slf4j
@RequiredArgsConstructor
public class LazyToolObjectRegistration {

    private final EmbabelToolObjectRegistry.ToolRegistration underlying;

    List<McpSyncClient> clients;

    List<ToolObject> setValues;

    String name;

    public Optional<List<ToolObject>> compute() {
        if (prev().isPresent())
            return prev();

        var g = computeToolObject(this);
        g.ifPresent(this::set);
        return g;
    }

    public Consumer<List<McpSchema.Tool>> toolsChangeConsumer() {
        return t -> {
            log.info("Received tool change notification - updating tools.");
            if (clients != null) {
                var tc = SyncMcpToolCallbackProvider.syncToolCallbacks(clients);
                this.setValues = EmbabelToolObjectProvider.parseToolObjects(name, tc);
            } else {
                log.error("Found tools change consumer execution but clients was null. Probably just a race thing before it was set.");
            }
        };
    }

    public Optional<List<ToolObject>> computeToolObject(LazyToolObjectRegistration toolObjectRegistration) {
        return underlying.computeToolObject(toolObjectRegistration);
    }

    public Optional<List<ToolObject>> prev() {
        return Optional.ofNullable(setValues);
    }

    public void set(List<ToolObject> values) {
        this.setValues = values;
    }
}
