package com.hayden.multiagentide.tool;

import com.agentclientprotocol.model.McpServer;
import com.embabel.agent.api.common.ToolObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.utilitymodule.MapFunctions;
import com.hayden.utilitymodule.acp.config.McpProperties;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.DelegatingHttpClientStreamableHttpTransport;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.json.jackson.JacksonMcpJsonMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class McpToolObjectRegistrar {

    private final EmbabelToolObjectRegistry embabelToolObjectRegistry;

    private final McpProperties mcpProperties;

    private ObjectMapper objectMapper;

    @PostConstruct
    public void initialize() {
        for (Map.Entry<String, McpServer> entry : mcpProperties.getCollected().entrySet()) {
            String name = entry.getKey();
            McpServer value = entry.getValue();


            embabelToolObjectRegistry.register(name, new LazyToolObjectRegistration(tor -> {
                var t = toToolObjects(tor, name, value);
                return Optional.of(t);
            }));
        }
    }

    private @NonNull List<ToolObject> toToolObjects(LazyToolObjectRegistration tor, String name, McpServer value) {
        var tc = resolveToolCallbacks(name, value, tor);
        return EmbabelToolObjectProvider.parseToolObjects(name, tc);
    }

    private @NonNull List<ToolCallback> resolveToolCallbacks(String name, McpServer value, LazyToolObjectRegistration toolsChangeConsumer) {

        try {
            switch (value) {
                case McpServer.Http http -> {
                    var m = McpClient.sync(
                                    DelegatingHttpClientStreamableHttpTransport
                                            .builder(http.getUrl())
                                            .build()
                            )
                            .toolsChangeConsumer(toolsChangeConsumer.toolsChangeConsumer())
                            .build();

                    List<McpSyncClient> m1 = List.of(m);
                    var tc = SyncMcpToolCallbackProvider.syncToolCallbacks(m1);
                    toolsChangeConsumer.clients = m1;
                    toolsChangeConsumer.name = name;
                    return tc;

                }
                case McpServer.Stdio stdio -> {
                        var m = McpClient.sync(
                                        new StdioClientTransport(
                                                ServerParameters.builder(stdio.getCommand())
                                                        .env(MapFunctions.CollectMap(stdio.getEnv().stream()
                                                                .map(e -> Map.entry(e.getName(), e.getValue()))))
                                                        .args(stdio.getArgs())
                                                        .build(),
                                                new JacksonMcpJsonMapper(this.objectMapper))
                                )
                                .build();

                    List<McpSyncClient> m1 = List.of(m);
                    toolsChangeConsumer.clients = m1;
                    toolsChangeConsumer.name = name;
                    var tc = SyncMcpToolCallbackProvider.syncToolCallbacks(m1);
                    return tc;
                }
                case McpServer.Sse sse -> {
                    log.error("Dont support SSE!");
                }
                default -> {

                }
            }

        } catch (Exception e) {
            log.error("Error attempting to build {}.", name);
        }

        return new ArrayList<>();
    }

}
