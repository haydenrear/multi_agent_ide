package com.hayden.multiagentide.tool;

import com.embabel.agent.api.common.ToolObject;
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
import java.util.function.Predicate;

@Slf4j
@Component
public class EmbabelToolObjectRegistry implements EmbabelToolObjectProvider {

    Map<String, ToolObjectRegistration> toolObjectMap = new ConcurrentHashMap<>();

    public interface ToolRegistration {
        Optional<List<ToolObject>> computeToolObject();
    }

    public interface ToolObjectRegistration extends ToolRegistration {

        Optional<List<ToolObject>> prev();

        void set(List<ToolObject> values);

        default Optional<List<ToolObject>> compute() {
            if (prev().isPresent())
               return prev();

            var g = computeToolObject();
            g.ifPresent(this::set);
            return g;
        }
    }

    public void register(String name, ToolObjectRegistration toolObject) {
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
                            .flatMap(ToolObjectRegistration::compute)
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
