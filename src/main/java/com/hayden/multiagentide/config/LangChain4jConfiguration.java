package com.hayden.multiagentide.config;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import com.hayden.multiagentide.agent.AgentModels;
import com.hayden.multiagentide.agent.LangChain4jAgentTools;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import com.hayden.multiagentide.model.acp.AcpStreamingChatModel;
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
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

/**
 * LangChain4j configuration for Chat Language Models and Agentic Services.
 * Configures OpenAI models and builds multi-agent system using langchain4j-agentic.
 * Uses before/after agent invocation handlers to manage node lifecycle in the computation graph.
 */
@Configuration
@EnableConfigurationProperties(AcpModelProperties.class)
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
    private static final String OUTPUT_TICKET_COLLECTOR = "ticketCollectorSummary";
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

    @Value("${langchain4j.chat-model.provider:http}")
    private String modelProvider;

    private final ComputationGraphOrchestrator orchestrator;
    private volatile AcpChatModel acpChatModel;

    public LangChain4jConfiguration(ComputationGraphOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    /**
     * Create OpenAI Chat Language Model for non-streaming calls.
     * Used by Planning, Merger, and Review agents.
     */
    @Bean
    @Primary
    public ChatModel chatLanguageModel(AcpModelProperties acpModelProperties) {
        if ("acp".equalsIgnoreCase(modelProvider)) {
            return getAcpChatModel(acpModelProperties);
        }
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
    public StreamingChatModel streamingChatLanguageModel(AcpModelProperties acpModelProperties) {
        if ("acp".equalsIgnoreCase(modelProvider)) {
            return new AcpStreamingChatModel(getAcpChatModel(acpModelProperties));
        }
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
//                TODO: will have a listener for ai message - to for each item push to the event and the frontend
//                .listeners(List.of(new ChatModelListener(){ }))
                .build();
    }

    private AcpChatModel getAcpChatModel(AcpModelProperties acpModelProperties) {
        if (acpChatModel == null) {
            synchronized (this) {
                if (acpChatModel == null) {
                    acpChatModel = new AcpChatModel(acpModelProperties);
                }
            }
        }
        return acpChatModel;
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.DiscoveryOrchestrator.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_DISCOVERY_DIVISION)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeDiscoveryOrchestratorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.DiscoveryOrchestratorResult result =
                            toDiscoveryOrchestratorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterDiscoveryOrchestratorInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.DiscoveryOrchestrator.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.DiscoveryAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_DISCOVERY_RESULTS)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeDiscoveryAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.DiscoveryAgentResult result =
                            toDiscoveryAgentResult(response.output(), nodeId(response));
                    lifecycleHandler.afterDiscoveryAgentInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.DiscoveryAgent.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.DiscoveryCollector.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_DISCOVERY_CONTEXT)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeDiscoveryCollectorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.DiscoveryCollectorResult result =
                            toDiscoveryCollectorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterDiscoveryCollectorInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.DiscoveryCollector.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.PlanningOrchestrator.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_PLANNING_DIVISION)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforePlanningOrchestratorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.PlanningOrchestratorResult result =
                            toPlanningOrchestratorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterPlanningOrchestratorInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.PlanningOrchestrator.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.PlanningAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_PLANNING_RESULTS)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforePlanningAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.PlanningAgentResult result =
                            toPlanningAgentResult(response.output(), nodeId(response));
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterPlanningAgentInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.PlanningAgent.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.PlanningCollector.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_PLANNING_CONTEXT)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforePlanningCollectorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.PlanningCollectorResult result =
                            toPlanningCollectorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterPlanningCollectorInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.PlanningCollector.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.TicketOrchestrator.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_TICKET_ORCHESTRATION)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeTicketOrchestratorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.TicketOrchestratorResult result =
                            toTicketOrchestratorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterTicketOrchestratorInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.TicketOrchestrator.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.TicketAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_TICKET_IMPLEMENTATION)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeTicketAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.TicketAgentResult result =
                            toTicketAgentResult(response.output(), nodeId(response));
                    lifecycleHandler.afterTicketAgentInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.TicketAgent.class);
    }

    /**
     * Build Ticket Collector Agent using AgenticServices with lifecycle management.
     * Consolidates ticket execution results.
     * Before invocation: registers ticket collector node
     * After invocation: updates node with summary and routing decision
     */
    @Bean
    public AgentInterfaces.TicketCollector ticketCollectorAgent(
            ChatModel chatModel,
            LangChain4jAgentTools tools,
            @Lazy AgentLifecycleHandler lifecycleHandler
    ) {
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.TicketCollector.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_TICKET_COLLECTOR)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    lifecycleHandler.beforeTicketCollectorInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.TicketCollectorResult result =
                            toTicketCollectorResult(response.output(), nodeId(response));
                    lifecycleHandler.afterTicketCollectorInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.TicketCollector.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.MergerAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_MERGE_STRATEGY)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    // Register merger node before agent executes
                    lifecycleHandler.beforeMergerAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.MergerAgentResult result =
                            toMergerAgentResult(response.output(), nodeId(response));
                    // Update node with merge strategy after agent completes
                    lifecycleHandler.afterMergerAgentInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.MergerAgent.class);
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.ReviewAgent.class)
                .chatModel(chatModel)
                .tools(tools)
                .outputKey(OUTPUT_REVIEW_EVALUATION)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    // Register review node before agent executes
                    lifecycleHandler.beforeReviewAgentInvocation(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.ReviewAgentResult result =
                            toReviewAgentResult(response.output(), nodeId(response));
                    // Update node with evaluation after agent completes
                    lifecycleHandler.afterReviewAgentInvocation(result, nodeId(response));
                })
                .build(), AgentInterfaces.ReviewAgent.class);
    }

    public static <T> T wrap(Object t, Class<T> clazz) {
         return AgenticServices.sequenceBuilder(clazz)
                .subAgents(t)
                 .output(as -> {
                     return as.agentInvocations().getFirst().output();
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
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.OrchestratorAgent.class)
                .outputKey(OUTPUT_ORCHESTRATOR)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforeOrchestrator(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.OrchestratorAgentResult result =
                            toOrchestratorAgentResult(response.output(), nodeId(response));
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterOrchestrator(result, nodeId(response));
                })
                .chatModel(chatModel)
                .tools(tools)
                .build(), AgentInterfaces.OrchestratorAgent.class);
    }

    @Bean
    public AgentInterfaces.OrchestratorCollectorAgent orchestratorCollectorAgent(ChatModel chatModel,
                                                                                 LangChain4jAgentTools tools,
                                                                                 @Lazy AgentLifecycleHandler lifecycleHandler) {
        return wrap(AgenticServices.agentBuilder(AgentInterfaces.OrchestratorCollectorAgent.class)
                .outputKey(OUTPUT_ORCHESTRATOR_COLLECTOR)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                .beforeAgentInvocation(request -> {
                    // Register planning node before agent executes
                    lifecycleHandler.beforeOrchestratorCollector(nodeId(request));
                })
                .afterAgentInvocation(response -> {
                    AgentModels.OrchestratorCollectorResult result =
                            toOrchestratorCollectorResult(response.output(), nodeId(response));
                    // Update node with planning results after agent completes
                    lifecycleHandler.afterOrchestratorCollector(result, nodeId(response));
                })
                .chatModel(chatModel)
                .tools(tools)
                .build(), AgentInterfaces.OrchestratorCollectorAgent.class);
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

    private AgentModels.DiscoveryOrchestratorResult toDiscoveryOrchestratorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.DiscoveryOrchestratorResult.class);
    }

    private AgentModels.DiscoveryAgentResult toDiscoveryAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.DiscoveryAgentResult.class);
    }

    private AgentModels.DiscoveryCollectorResult toDiscoveryCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.DiscoveryCollectorResult.class);
    }

    private AgentModels.PlanningOrchestratorResult toPlanningOrchestratorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.PlanningOrchestratorResult.class);
    }

    private AgentModels.PlanningAgentResult toPlanningAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.PlanningAgentResult.class);
    }

    private AgentModels.PlanningCollectorResult toPlanningCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.PlanningCollectorResult.class);
    }

    private AgentModels.TicketOrchestratorResult toTicketOrchestratorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.TicketOrchestratorResult.class);
    }

    private AgentModels.TicketAgentResult toTicketAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.TicketAgentResult.class);
    }

    private AgentModels.TicketCollectorResult toTicketCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.TicketCollectorResult.class);
    }

    private AgentModels.MergerAgentResult toMergerAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.MergerAgentResult.class);
    }

    private AgentModels.ReviewAgentResult toReviewAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.ReviewAgentResult.class);
    }

    private AgentModels.OrchestratorAgentResult toOrchestratorAgentResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.OrchestratorAgentResult.class);
    }

    private AgentModels.OrchestratorCollectorResult toOrchestratorCollectorResult(Object output, String nodeId) {
        return castResult(output, nodeId, AgentModels.OrchestratorCollectorResult.class);
    }
}
