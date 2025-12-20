package com.hayden.multiagentide.config;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import com.hayden.multiagentide.agent.LangChain4jAgentTools;
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator;
import dev.langchain4j.agentic.agent.AgentRequest;
import dev.langchain4j.agentic.agent.AgentResponse;
import dev.langchain4j.agentic.scope.AgenticScope;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilder;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.List;

/**
 * LangChain4j configuration for Chat Language Models and Agentic Services.
 * Configures OpenAI models and builds multi-agent system using langchain4j-agentic.
 * Uses before/after agent invocation handlers to manage node lifecycle in the computation graph.
 */
@Configuration
public class LangChain4jConfiguration {

    private static final String KEY_DISCOVERY_CONTEXT = "discoveryContext";
    private static final String KEY_DISCOVERY_RESULTS = "discoveryResults";

    private static final String KEY_PLANNING_CONTEXT = "planningContext";
    private static final String KEY_PLANNING_RESULTS = "planningResults";

    private static final String OUTPUT_DISCOVERY_DIVISION = "discoveryDivisionStrategy";
    private static final String OUTPUT_DISCOVERY_RESULTS = KEY_DISCOVERY_RESULTS;
    private static final String OUTPUT_DISCOVERY_CONTEXT = KEY_DISCOVERY_CONTEXT;
    private static final String OUTPUT_PLANNING_DIVISION = "planningDivisionStrategy";
    private static final String OUTPUT_PLANNING_RESULTS = KEY_PLANNING_RESULTS;
    private static final String OUTPUT_PLANNING_CONTEXT = KEY_PLANNING_CONTEXT;
    private static final String OUTPUT_TICKET_ORCHESTRATION = "ticketOrchestrationPlan";
    private static final String OUTPUT_TICKET_IMPLEMENTATION = "ticketImplementationSummary";
    private static final String OUTPUT_MERGE_STRATEGY = "mergeStrategy";
    private static final String OUTPUT_REVIEW_EVALUATION = "reviewEvaluation";
    private static final String OUTPUT_ORCHESTRATOR = "orchestratorOutput";
    private static final String OUTPUT_ORCHESTRATOR_COLLECTOR = "orchestratorCollectorOutput";

    @Value("${langchain4j.openai.chat-model.api-key:}")
    private String apiKey;

    @Value("${langchain4j.openai.chat-model.model-name:gpt-4}")
    private String modelName;

    @Value("${langchain4j.openai.chat-model.temperature:0.7}")
    private Double temperature;

    @Value("${langchain4j.openai.chat-model.max-tokens:2000}")
    private Integer maxTokens;

    private final ComputationGraphOrchestrator orchestrator;

    public LangChain4jConfiguration(ComputationGraphOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

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
                .outputKey(OUTPUT_DISCOVERY_DIVISION)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeDiscoveryOrchestratorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.DiscoveryOrchestratorResult result =
                            toDiscoveryOrchestratorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterDiscoveryOrchestratorInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_DISCOVERY_RESULTS)
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeDiscoveryAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.DiscoveryAgentResult result =
                            toDiscoveryAgentResult(response.output(), nodeId(response));
                    lifecycleHandler.afterDiscoveryAgentInvocation(result, nodeId(response));
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
    public AgentInterfaces.DiscoveryCollector discoveryMergerAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.DiscoveryCollector.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_DISCOVERY_CONTEXT)
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeDiscoveryCollectorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.DiscoveryCollectorResult result =
                            toDiscoveryCollectorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterDiscoveryCollectorInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_PLANNING_DIVISION)
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforePlanningOrchestratorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.PlanningOrchestratorResult result =
                            toPlanningOrchestratorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterPlanningOrchestratorInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_PLANNING_RESULTS)
                .beforeAgentInvocation(request -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforePlanningAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.PlanningAgentResult result =
                            toPlanningAgentResult(response.output(), nodeId(response));
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterPlanningAgentInvocation(result, nodeId(response));
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
    public AgentInterfaces.PlanningCollector planningMergerAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return AgenticServices.agentBuilder(AgentInterfaces.PlanningCollector.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_PLANNING_CONTEXT)
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforePlanningCollectorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.PlanningCollectorResult result =
                            toPlanningCollectorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterPlanningCollectorInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_TICKET_ORCHESTRATION)
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeTicketOrchestratorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.TicketOrchestratorResult result =
                            toTicketOrchestratorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterTicketOrchestratorInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_TICKET_IMPLEMENTATION)
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeTicketAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.TicketAgentResult result =
                            toTicketAgentResult(response.output(), nodeId(response));
                    lifecycleHandler.afterTicketAgentInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_MERGE_STRATEGY)
                .beforeAgentInvocation(request -> {
                    // Register merger node before agent executes
                    lifecycleHandler.beforeMergerAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.MergerAgentResult result =
                            toMergerAgentResult(response.output(), nodeId(response));
                    // Update node with merge strategy after agent completes
                    lifecycleHandler.afterMergerAgentInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_REVIEW_EVALUATION)
                .beforeAgentInvocation(request -> {
                    // Register review node before agent executes
                    lifecycleHandler.beforeReviewAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.ReviewAgentResult result =
                            toReviewAgentResult(response.output(), nodeId(response));
                    // Update node with evaluation after agent completes
                    lifecycleHandler.afterReviewAgentInvocation(result, nodeId(response));
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
                .outputKey(OUTPUT_ORCHESTRATOR)
                .beforeAgentInvocation(request -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforeOrchestrator(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.OrchestratorAgentResult result =
                            toOrchestratorAgentResult(response.output(), nodeId(response));
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterOrchestrator(result, nodeId(response));
                })
                .chatModel(chatModel)
                .tools(tools)
                .build();
    }

    @Bean
    public AgentInterfaces.OrchestratorCollectorAgent orchestratorCollectorAgent(ChatModel chatModel,
                                                                                 LangChain4jAgentTools tools,
                                                                                 @Lazy AgentLifecycleHandler lifecycleHandler) {
        return AgenticServices.agentBuilder(AgentInterfaces.OrchestratorCollectorAgent.class)
                .outputKey(OUTPUT_ORCHESTRATOR_COLLECTOR)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforeOrchestratorCollector(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentInterfaces.OrchestratorCollectorResult result =
                            toOrchestratorCollectorResult(response.output(), nodeId(response));
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterOrchestratorCollector(result, nodeId(response));
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

    private static String nodeId(AgentRequest request) {
        if (request == null) {
            return null;
        }
        return nodeId(request.agenticScope());
    }

    private static String nodeId(AgentResponse response) {
        if (response == null) {
            return null;
        }
        return nodeId(response.agenticScope());
    }

    private static String nodeId(AgenticScope scope) {
        if (scope == null || scope.memoryId() == null) {
            return null;
        }
        return scope.memoryId().toString();
    }

    private <T> T castResult(Object output, String nodeId, Class<T> expectedType) {
        if (output == null) {
            return null;
        }
        if (expectedType.isInstance(output)) {
            return expectedType.cast(output);
        }
        orchestrator.emitErrorEvent(
                nodeId,
                "result_cast",
                expectedType.getSimpleName(),
                "Expected " + expectedType.getSimpleName() + " but received " + output.getClass().getSimpleName()
        );
        return null;
    }

    private AgentInterfaces.DiscoveryOrchestratorResult toDiscoveryOrchestratorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.DiscoveryOrchestratorResult.class);
    }

    private AgentInterfaces.DiscoveryAgentResult toDiscoveryAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.DiscoveryAgentResult.class);
    }

    private AgentInterfaces.DiscoveryCollectorResult toDiscoveryCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.DiscoveryCollectorResult.class);
    }

    private AgentInterfaces.PlanningOrchestratorResult toPlanningOrchestratorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.PlanningOrchestratorResult.class);
    }

    private AgentInterfaces.PlanningAgentResult toPlanningAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.PlanningAgentResult.class);
    }

    private AgentInterfaces.PlanningCollectorResult toPlanningCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.PlanningCollectorResult.class);
    }

    private AgentInterfaces.TicketOrchestratorResult toTicketOrchestratorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.TicketOrchestratorResult.class);
    }

    private AgentInterfaces.TicketAgentResult toTicketAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.TicketAgentResult.class);
    }

    private AgentInterfaces.MergerAgentResult toMergerAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.MergerAgentResult.class);
    }

    private AgentInterfaces.ReviewAgentResult toReviewAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.ReviewAgentResult.class);
    }

    private AgentInterfaces.OrchestratorAgentResult toOrchestratorAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.OrchestratorAgentResult.class);
    }

    private AgentInterfaces.OrchestratorCollectorResult toOrchestratorCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentInterfaces.OrchestratorCollectorResult.class);
    }
}
