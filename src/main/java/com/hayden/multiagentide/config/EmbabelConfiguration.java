package com.hayden.multiagentide.config;

import com.embabel.agent.core.AgentProcess;
import com.embabel.common.ai.model.Llm;
import com.embabel.common.ai.model.LlmOptions;
import com.embabel.common.ai.model.OptionsConverter;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import com.hayden.multiagentide.model.acp.AcpChatRequestParameters;
import com.hayden.multiagentide.model.acp.ChatMemoryContext;
import com.hayden.multiagentide.model.acp.DefaultChatMemoryContext;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;
import reactor.util.annotation.NonNull;
import reactor.core.publisher.Flux;

/**
 * Embabel configuration for chat models and LLM integration.
 */
@Configuration
@EnableConfigurationProperties(AcpModelProperties.class)
public class EmbabelConfiguration {

    @Value("${langchain4j.openai.chat-model.api-key:}")
    private String apiKey;

    @Value("${langchain4j.chat-model.provider:http}")
    private String modelProvider;

    private volatile AcpChatModel acpChatModel;

    @Bean
    public ChatMemoryContext chatMemoryContext() {
        return new DefaultChatMemoryContext();
    }

    @Bean
    @Primary
    public ChatModel chatModel(AcpModelProperties acpModelProperties,
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

                        AgentProcess process = AgentProcess.get();
                        if (process != null && process.getProcessOptions().getContextIdString() != null) {
                            builder.memoryId(process.getProcessOptions().getContextIdString());
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
        public Flux<ChatResponse> stream(Prompt prompt) {
            return Flux.just(ChatResponse.builder().build());
        }
    }
}
