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
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.SimpleApiKey;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
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

    @Value("${multi-agent-embabel.chat-model.provider:acp}")
    private String modelProvider;

    @Bean
    public ApplicationRunner deployAgents(List<AgentInterfaces> agentInterfaces,
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
    @Profile("openai")
    public ApplicationRunner agenticApplicationRunner() {
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
    public ChatModel acpChatModel(AcpChatModel chatModel) {
        return chatModel;
    }

    /**
     * Create Streaming Chat Language Model for streaming responses.
     */
    @Bean
    public StreamingChatModel streamingChatLanguageModel(AcpChatModel chatModel) {
        return chatModel;
    }

    @Bean
    public Llm llm(org.springframework.ai.chat.model.ChatModel chatModel) {
        return new Llm("acp-chat-model", modelProvider, chatModel)
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
