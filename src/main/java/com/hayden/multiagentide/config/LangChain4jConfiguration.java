package com.hayden.multiagentide.config;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.LangChain4jAgentTools;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.planner.Action;
import dev.langchain4j.agentic.planner.Planner;
import dev.langchain4j.agentic.planner.PlanningContext;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilder;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.UUID;

/**
 * LangChain4j configuration for Chat Language Models and Agentic Services.
 * Configures OpenAI models and builds multi-agent system using langchain4j-agentic.
 * Uses before/after agent invocation handlers to manage node lifecycle in the computation graph.
 */
@Configuration
public class LangChain4jConfiguration {

    @Value("${langchain4j.openai.chat-model.api-key:}")
    private String apiKey;

    @Value("${langchain4j.openai.chat-model.model-name:gpt-4}")
    private String modelName;

    @Value("${langchain4j.openai.chat-model.temperature:0.7}")
    private Double temperature;

    @Value("${langchain4j.openai.chat-model.max-tokens:2000}")
    private Integer maxTokens;

    /**
     * Create OpenAI Chat Language Model for non-streaming calls.
     * Used by Planning, Merger, and Review agents.
     */
    @Bean
    @Primary
    public ChatModel chatLanguageModel() {
        if (apiKey == null || apiKey.isEmpty()) {
            // Return a no-op mock if no API key is configured
            return new MockChatLanguageModel();
        }

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .httpClientBuilder(new SpringRestClientBuilder())
                .build();
    }

    /**
     * Create OpenAI Streaming Chat Language Model for streaming responses.
     * Used by Editor agent for token-by-token code generation.
     */
    @Bean
    public StreamingChatModel streamingChatLanguageModel() {
        if (apiKey == null || apiKey.isEmpty()) {
            // Return a no-op mock if no API key is configured
            return new MockStreamingChatLanguageModel();
        }

        return OpenAiStreamingChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .temperature(temperature)
                .httpClientBuilder(new SpringRestClientBuilder())
                .maxTokens(maxTokens)
                .build();
    }


    /**
     * Build Discovery Orchestrator Agent using AgenticServices with lifecycle management.
     * Determines division strategy for discovery work across multiple agents.
     * Before invocation: registers discovery orchestrator node
     * After invocation: kicks off multiple discovery agents based on strategy
     */
    @Bean
    public AgentInterfaces.DiscoveryOrchestrator discoveryOrchestratorAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.DiscoveryOrchestrator.class)
                .chatModel(chatModel)
                .tools(tools)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforeDiscoveryOrchestratorInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterDiscoveryOrchestratorInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Discovery Agent using AgenticServices with lifecycle management.
     * Discovers and analyzes specific areas of the codebase.
     * Before invocation: registers discovery node
     * After invocation: updates node with discovery findings
     */
    @Bean
    public AgentInterfaces.DiscoveryAgent discoveryAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.DiscoveryAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforeDiscoveryAgentInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterDiscoveryAgentInvocation(
                            invocation.output().toString(),
                            null, null);
                })
                .build();
    }

    /**
     * Build Discovery Merger Agent using AgenticServices with lifecycle management.
     * Consolidates discovery findings from multiple agents into unified document.
     * Before invocation: registers discovery merger node
     * After invocation: updates node with merged discovery
     */
    @Bean
    public AgentInterfaces.DiscoveryMerger discoveryMergerAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.DiscoveryMerger.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforeDiscoveryMergerInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterDiscoveryMergerInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Planning Orchestrator Agent using AgenticServices with lifecycle management.
     * Decomposes goals into division strategy for multiple planning agents.
     * Before invocation: registers planning orchestrator node
     * After invocation: kicks off multiple planning agents based on strategy
     */
    @Bean
    public AgentInterfaces.PlanningOrchestrator planningOrchestratorAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.PlanningOrchestrator.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforePlanningOrchestratorInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterPlanningOrchestratorInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Planning Agent using AgenticServices with lifecycle management.
     * Decomposes goals into structured work items.
     * Before invocation: registers planning node
     * After invocation: updates node with plan results
     */
    @Bean
    public AgentInterfaces.PlanningAgent planningAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.PlanningAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforePlanningAgentInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterPlanningAgentInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Planning Merger Agent using AgenticServices with lifecycle management.
     * Consolidates planning results into structured tickets.
     * Before invocation: registers planning merger node
     * After invocation: updates node with merged tickets
     */
    @Bean
    public AgentInterfaces.PlanningMerger planningMergerAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.PlanningMerger.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforePlanningMergerInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterPlanningMergerInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Ticket Orchestrator Agent using AgenticServices with lifecycle management.
     * Orchestrates ticket-based implementation workflow.
     * Before invocation: registers ticket orchestrator node, creates worktrees
     * After invocation: kicks off ticket agents
     */
    @Bean
    public AgentInterfaces.TicketOrchestrator ticketOrchestratorAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.TicketOrchestrator.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforeTicketOrchestratorInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterTicketOrchestratorInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Ticket Agent using AgenticServices with lifecycle management.
     * Implements individual tickets with complete code generation.
     * Before invocation: registers ticket node, creates feature branch
     * After invocation: updates node with implementation summary
     */
    @Bean
    public AgentInterfaces.TicketAgent ticketAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.TicketAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    lifecycleHandler.beforeTicketAgentInvocation(
                            invocation.inputs().toString(),
                            null, UUID.randomUUID().toString());
                })
                .afterAgentInvocation(invocation -> {
                    lifecycleHandler.afterTicketAgentInvocation(
                            invocation.output().toString(),
                            null);
                })
                .build();
    }

    /**
     * Build Editor Agent using AgenticServices with lifecycle management.
     * Generates code based on specifications.
     * Before invocation: registers editor node
     * After invocation: updates node with generated code
     */
    @Bean
    public AgentInterfaces.TicketAgent editorAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler) {
        return AgenticServices.agentBuilder(AgentInterfaces.TicketAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    // Register editor node before agent executes
                    lifecycleHandler.beforeEditorAgentInvocation(
                            invocation.inputs().toString(),
                            "",
                            null,
                            null
                    );
                })
                .afterAgentInvocation(invocation -> {
                    // Update node with generated code after agent completes
                    lifecycleHandler.afterEditorAgentInvocation(
                            invocation.output().toString(),
                            null
                    );
                })
                .build();
    }

    /**
     * Build Merger Agent using AgenticServices with lifecycle management.
     * Determines merge strategies and resolves conflicts.
     * Before invocation: registers merger node
     * After invocation: updates node with merge strategy
     */
    @Bean
    public AgentInterfaces.MergerAgent mergerAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler) {
        return AgenticServices.agentBuilder(AgentInterfaces.MergerAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    // Register merger node before agent executes
                    lifecycleHandler.beforeMergerAgentInvocation(
                            invocation.inputs().toString(),
                            "",
                            null,
                            UUID.randomUUID().toString()
                    );
                })
                .afterAgentInvocation(invocation -> {
                    // Update node with merge strategy after agent completes
                    lifecycleHandler.afterMergerAgentInvocation(
                            invocation.output().toString(),
                            null
                    );
                })
                .build();
    }

    /**
     * Build Review Agent using AgenticServices with lifecycle management.
     * Evaluates code quality and completeness.
     * Before invocation: registers review node
     * After invocation: updates node with evaluation results
     */
    @Bean
    public AgentInterfaces.ReviewAgent reviewAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler) {
        return AgenticServices.agentBuilder(AgentInterfaces.ReviewAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .beforeAgentInvocation(invocation -> {
                    // Register review node before agent executes
                    lifecycleHandler.beforeReviewAgentInvocation(
                            invocation.inputs().toString(),
                            "",
                            null, UUID.randomUUID().toString()
                    );
                })
                .afterAgentInvocation(invocation -> {
                    // Update node with evaluation after agent completes
                    lifecycleHandler.afterReviewAgentInvocation(
                            invocation.output().toString(),
                            null
                    );
                })
                .build();
    }

    /**
     * Build Orchestrator Agent using AgenticServices.
     * Coordinates multi-agent workflows.
     */
    @Bean
    public AgentInterfaces.OrchestratorAgent orchestratorAgent(ChatModel chatModel,
                                                               LangChain4jAgentTools tools,
                                                                @Lazy AgentLifecycleHandler lifecycleHandler) {
        return AgenticServices.agentBuilder(AgentInterfaces.OrchestratorAgent.class)
                .beforeAgentInvocation(invocation -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforeOrchestrator(
                            invocation.inputs().toString(),
                            null, null, null);
                })
                .afterAgentInvocation(invocation -> {
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterOrchestrator(invocation.output().toString());
                })
                .chatModel(chatModel)
                .tools(tools)
                .build();
    }

    /**
     * Mock ChatLanguageModel for testing without OpenAI API key.
     */
    private static class MockChatLanguageModel implements ChatModel {
        @Override
        public String chat(String userMessage) {
            return "Mock response: " + userMessage;
        }

        @Override
        public ChatResponse chat(java.util.List<ChatMessage> messages) {
            return ChatResponse.builder()
                    .aiMessage(AiMessage.from("Mock response for " + messages.size() + " messages"))
                    .build();
        }
    }

    /**
     * Mock Streaming ChatLanguageModel for testing without OpenAI API key.
     */
    private static class MockStreamingChatLanguageModel implements StreamingChatModel {
        @Override
        public void chat(String userMessage, StreamingChatResponseHandler handler) {
            handler.onPartialResponse("Mock streaming response for: " + userMessage);
            handler.onCompleteResponse(ChatResponse.builder()
                    .aiMessage(AiMessage.from("Mock streaming response for: " + userMessage))
                    .build());
        }

        @Override
        public void chat(java.util.List<ChatMessage> messages, StreamingChatResponseHandler handler) {
            handler.onPartialResponse("Mock streaming response for " + messages.size() + " messages");
            handler.onCompleteResponse(ChatResponse.builder()
                    .aiMessage(AiMessage.from("Mock streaming response for " + messages.size() + " messages"))
                    .build());
        }
    }
}
