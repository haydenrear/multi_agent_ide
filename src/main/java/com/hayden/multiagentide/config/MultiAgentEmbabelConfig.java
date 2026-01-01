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

    @Value("${langchain4j.openai.chat-model.api-key:}")
    private String apiKey;

    @Value("${langchain4j.chat-model.provider:http}")
    private String modelProvider;

    @Value("${spring.ai.openai.api-key:}")
    private String openAiApiKey;

    @Value("${spring.ai.openai.base-url:http://localhost:4545}")
    private String openAiBaseUrl;

    @Value("${spring.ai.openai.completions-path:/v1/chat/completions}")
    private String openAiCompletionsPath;

    @Value("${spring.ai.openai.embeddings-path:/v1/embeddings}")
    private String openAiEmbeddingsPath;

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
    @Profile("openai")
    @Primary
    public ChatModel openAiChatModel() {
        return OpenAiChatModel.builder()
                .openAiApi(OpenAiApi.builder()
                        .apiKey(new SimpleApiKey(openAiApiKey))
                        .baseUrl(openAiBaseUrl)
                        .restClientBuilder(RestClient.builder())
                        .completionsPath(openAiCompletionsPath)
                        .embeddingsPath(openAiEmbeddingsPath)
                        .build())
                .build();
    }

    /**
     * Create Streaming Chat Language Model for streaming responses.
     */
    @Bean
    @Profile("openai")
    public StreamingChatModel openAiStreamingChatLanguageModel(OpenAiChatModel chatModel) {
        return chatModel;
    }

    @Bean(name = "chatModel")
    @Primary
    @Profile("acp")
    public ChatModel acpChatModel(AcpChatModel chatModel) {
        return chatModel;
    }

    /**
     * Create Streaming Chat Language Model for streaming responses.
     */
    @Bean
    @Profile("acp")
    public StreamingChatModel streamingChatLanguageModel(AcpChatModel chatModel) {
        if ("acp".equalsIgnoreCase(modelProvider)) {
            return chatModel;
        }
        if (!StringUtils.hasText(apiKey)) {
            return new MockStreamingChatLanguageModel();
        }
        return new MockStreamingChatLanguageModel();
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
