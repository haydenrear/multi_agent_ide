package com.hayden.multiagentide.tool;

import com.embabel.agent.api.common.ToolObject;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
@Component
public class EmbabelToolObjectRegistry implements EmbabelToolObjectProvider {

    Map<String, LazyToolObjectRegistration> toolObjectMap = new ConcurrentHashMap<>();

    public interface ToolRegistration {

        Optional<List<ToolObject>> computeToolObject(LazyToolObjectRegistration toolObjectRegistration);

    }

    public void register(String name, LazyToolObjectRegistration toolObject) {
        toolObjectMap.put(name, toolObject);
    }

    @Override
    public Optional<List<ToolObject>> tool(String name) {
        var t = RetryTemplate.builder()
                .maxAttempts(5)
                .retryOn(RuntimeException.class)
                .fixedBackoff(Duration.ofSeconds(3))
                .build()
                .execute(r -> {
                    var f = Optional.ofNullable(toolObjectMap.get(name))
                            .flatMap(LazyToolObjectRegistration::compute)
                            .filter(Predicate.not(CollectionUtils::isEmpty));

                    if (f.isEmpty()) {
                        log.error("Failed to boot MCP server - {} number of retries.", r.getRetryCount());
                        throw new RuntimeException("Failed to retrieve");
                    }

                    return f;
                });

        return t;
    }
}
