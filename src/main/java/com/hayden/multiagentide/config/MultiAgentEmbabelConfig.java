package com.hayden.multiagentide.config;

import com.embabel.agent.api.annotation.support.AgentMetadataReader;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.spi.AgentProcessIdGenerator;
import com.embabel.common.ai.model.Llm;
import com.embabel.common.ai.model.LlmOptions;
import com.embabel.common.ai.model.OptionsConverter;
import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import com.hayden.multiagentide.model.acp.AcpChatRequestParameters;
import com.hayden.multiagentide.model.acp.ChatMemoryContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;
import reactor.util.annotation.NonNull;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

/**
 * Embabel configuration for chat models and LLM integration.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AcpModelProperties.class)
public class MultiAgentEmbabelConfig {

    @Value("${langchain4j.openai.chat-model.api-key:}")
    private String apiKey;

    @Value("${langchain4j.chat-model.provider:http}")
    private String modelProvider;

    private volatile AcpChatModel acpChatModel;

    @Bean
    public CommandLineRunner deployAgents(List<AgentInterfaces> agentInterfaces,
                                          AgentPlatform agentPlatform,
                                          AgentMetadataReader agentMetadataReader) {
        for (AgentInterfaces agentInterface : agentInterfaces) {
            Optional.ofNullable(agentMetadataReader.createAgentMetadata(agentInterface))
                    .ifPresentOrElse(agentPlatform::deploy, () -> log.error("Error deploying {} - could not create agent metadata.", agentInterface));

        }
        return args -> {
        };
    }

    @Bean
    @Primary
    public AgentProcessIdGenerator nodeIdAgentProcessIdGenerator() {
        return (agent, processOptions) -> Optional.ofNullable(processOptions.getContextIdString())
                .orElseGet(() -> AgentProcessIdGenerator.Companion.getRANDOM().createProcessId(agent, processOptions));
    }

    @Bean(name = "chatModel")
    @Primary
    public ChatModel acpChatModel(AcpModelProperties acpModelProperties,
                                  ChatMemoryContext chatMemoryContext) {
        return getAcpChatModel(acpModelProperties, chatMemoryContext);
    }

    /**
     * Create Streaming Chat Language Model for streaming responses.
     */
    @Bean
    public StreamingChatModel streamingChatLanguageModel(AcpModelProperties acpModelProperties,
                                                         ChatMemoryContext chatMemoryContext) {
        if ("acp".equalsIgnoreCase(modelProvider)) {
            return getAcpChatModel(acpModelProperties, chatMemoryContext);
        }
        if (!StringUtils.hasText(apiKey)) {
            return new MockStreamingChatLanguageModel();
        }
        return new MockStreamingChatLanguageModel();
    }

    private AcpChatModel getAcpChatModel(AcpModelProperties acpModelProperties,
                                         ChatMemoryContext chatMemoryContext) {
        if (acpChatModel == null) {
            synchronized (this) {
                if (acpChatModel == null) {
                    acpChatModel = new AcpChatModel(acpModelProperties, chatMemoryContext);
                }
            }
        }
        return acpChatModel;
    }

    @Bean
    public Llm llm(org.springframework.ai.chat.model.ChatModel chatModel) {
        return new Llm("acp-chat-model", "acp", chatModel)
                .withOptionsConverter(new OptionsConverter<>() {
                    @Override
                    public @NonNull ChatOptions convertOptions(@NonNull LlmOptions options) {
                        AcpChatRequestParameters.Builder<?> builder = AcpChatRequestParameters.builder()
                                .modelName(options.getModel())
                                .temperature(options.getTemperature())
                                .topP(options.getTopP())
                                .topK(options.getTopK())
                                .frequencyPenalty(options.getFrequencyPenalty())
                                .presencePenalty(options.getPresencePenalty())
                                .maxOutputTokens(options.getMaxTokens());

                        var process = AgentInterfaces.agentProcess.get();

                        if (process != null && process.id() != null) {
                            builder.memoryId(process.id());
                        } else {
                            log.error("Did not have process ID. Session will not be able to be created for ACP. Listener is not being set correctly.");
                        }

                        return builder.build();
                    }
                });
    }

    /**
     * Mock Streaming ChatLanguageModel for testing without OpenAI API key.
     */
    private static class MockStreamingChatLanguageModel implements StreamingChatModel {
        @Override
        public @org.jspecify.annotations.NonNull Flux<ChatResponse> stream(Prompt prompt) {
            return Flux.just(ChatResponse.builder().build());
        }
    }
}
