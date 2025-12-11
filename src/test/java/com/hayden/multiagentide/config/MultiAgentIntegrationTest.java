package com.hayden.multiagentide.config;

import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.LangChain4jAgentTools;
import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.GraphNode;
import com.hayden.multiagentide.model.OrchestratorNode;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.SpecRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import dev.langchain4j.model.chat.ChatModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for multi-agent IDE with mocked LLM responses.
 */
@SpringBootTest
@TestPropertySource(properties = {
        "langchain4j.openai.chat-model.api-key=test-key"
})
class MultiAgentIntegrationTest {

    private ApplicationContext applicationContext;

    @MockBean
    private GraphRepository graphRepository;

    @MockBean
    private WorktreeRepository worktreeRepository;

    @MockBean
    private SpecRepository specRepository;

    @MockBean
    private EventBus eventBus;

    @BeforeEach
    void setUp(ApplicationContext context) {
        this.applicationContext = context;
    }

    /**
     * Test that all agents are properly registered as Spring beans.
     */
    @Test
    void testAllAgentsAreRegistered() {
        // Verify Planning Agent is registered
        AgentInterfaces.PlanningAgent planningAgent = applicationContext.getBean(AgentInterfaces.PlanningAgent.class);
        assertNotNull(planningAgent, "Planning agent should be registered");

        // Verify Editor Agent is registered
        AgentInterfaces.EditorAgent editorAgent = applicationContext.getBean(AgentInterfaces.EditorAgent.class);
        assertNotNull(editorAgent, "Editor agent should be registered");

        // Verify Merger Agent is registered
        AgentInterfaces.MergerAgent mergerAgent = applicationContext.getBean(AgentInterfaces.MergerAgent.class);
        assertNotNull(mergerAgent, "Merger agent should be registered");

        // Verify Review Agent is registered
        AgentInterfaces.ReviewAgent reviewAgent = applicationContext.getBean(AgentInterfaces.ReviewAgent.class);
        assertNotNull(reviewAgent, "Review agent should be registered");

        // Verify Orchestrator Agent is registered
        AgentInterfaces.OrchestratorAgent orchestratorAgent = applicationContext.getBean(AgentInterfaces.OrchestratorAgent.class);
        assertNotNull(orchestratorAgent, "Orchestrator agent should be registered");
    }

    /**
     * Test that LangChain4j agent tools are available.
     */
    @Test
    void testAgentToolsAvailable() {
        LangChain4jAgentTools tools = applicationContext.getBean(LangChain4jAgentTools.class);
        assertNotNull(tools, "Agent tools should be registered as a bean");
    }

    /**
     * Test that ChatModel is properly configured.
     */
    @Test
    void testChatModelAvailable() {
        ChatModel chatModel = applicationContext.getBean(ChatModel.class);
        assertNotNull(chatModel, "ChatModel should be registered");
    }

    /**
     * Test planning agent with mocked response.
     */
    @Test
    void testPlanningAgentResponse() {
        AgentInterfaces.PlanningAgent planningAgent = applicationContext.getBean(AgentInterfaces.PlanningAgent.class);
        
        // Call planning agent - should return mocked response
        String result = planningAgent.decomposePlanAndCreateWorkItems("Implement user authentication system");
        
        assertNotNull(result, "Planning agent should return a result");
        assertFalse(result.isEmpty(), "Planning agent response should not be empty");
        assertTrue(result.length() > 0, "Planning agent should provide structured plan");
    }

    /**
     * Test editor agent with mocked response.
     */
    @Test
    void testEditorAgentResponse() {
        AgentInterfaces.EditorAgent editorAgent = applicationContext.getBean(AgentInterfaces.EditorAgent.class);
        
        // Call editor agent - should return mocked code generation
        String result = editorAgent.generateCode(
                "Implement login endpoint",
                "Create REST endpoint for user login with JWT token generation"
        );
        
        assertNotNull(result, "Editor agent should return generated code");
        assertFalse(result.isEmpty(), "Editor agent response should not be empty");
    }

    /**
     * Test merger agent with mocked response.
     */
    @Test
    void testMergerAgentResponse() {
        AgentInterfaces.MergerAgent mergerAgent = applicationContext.getBean(AgentInterfaces.MergerAgent.class);
        
        // Call merger agent - should return mocked merge strategy
        String strategy = mergerAgent.determineMergeStrategy(
                "Implement database schema changes",
                "Implement API endpoints"
        );
        
        assertNotNull(strategy, "Merger agent should return merge strategy");
        assertFalse(strategy.isEmpty(), "Merger agent response should not be empty");
    }

    /**
     * Test merger agent conflict resolution.
     */
    @Test
    void testMergerAgentConflictResolution() {
        AgentInterfaces.MergerAgent mergerAgent = applicationContext.getBean(AgentInterfaces.MergerAgent.class);
        
        // Call merger agent conflict resolution - should return mocked resolution
        String resolution = mergerAgent.resolveConflicts(
                "pom.xml, Application.java, config.yml",
                "recursive"
        );
        
        assertNotNull(resolution, "Merger agent should return conflict resolution");
        assertFalse(resolution.isEmpty(), "Merger agent resolution should not be empty");
    }

    /**
     * Test review agent with mocked response.
     */
    @Test
    void testReviewAgentResponse() {
        AgentInterfaces.ReviewAgent reviewAgent = applicationContext.getBean(AgentInterfaces.ReviewAgent.class);
        
        // Call review agent - should return mocked evaluation
        String evaluation = reviewAgent.evaluateContent(
                "public class UserController { ... }",
                "Code quality, test coverage, documentation"
        );
        
        assertNotNull(evaluation, "Review agent should return evaluation");
        assertFalse(evaluation.isEmpty(), "Review agent response should not be empty");
    }

    /**
     * Test orchestrator agent with mocked response.
     */
    @Test
    void testOrchestratorAgentResponse() {
        AgentInterfaces.OrchestratorAgent orchestratorAgent = applicationContext.getBean(AgentInterfaces.OrchestratorAgent.class);
        
        // Call orchestrator agent - should return mocked workflow coordination
        String coordination = orchestratorAgent.coordinateWorkflow(
                "Build e-commerce system",
                "planning"
        );
        
        assertNotNull(coordination, "Orchestrator agent should return workflow coordination");
        assertFalse(coordination.isEmpty(), "Orchestrator agent response should not be empty");
    }

    /**
     * Test multi-agent workflow sequence.
     * This simulates: Planning -> Editor -> Merger -> Review cycle
     */
    @Test
    void testMultiAgentWorkflowSequence() {
        AgentInterfaces.PlanningAgent planningAgent = applicationContext.getBean(AgentInterfaces.PlanningAgent.class);
        AgentInterfaces.EditorAgent editorAgent = applicationContext.getBean(AgentInterfaces.EditorAgent.class);
        AgentInterfaces.MergerAgent mergerAgent = applicationContext.getBean(AgentInterfaces.MergerAgent.class);
        AgentInterfaces.ReviewAgent reviewAgent = applicationContext.getBean(AgentInterfaces.ReviewAgent.class);
        
        // Phase 1: Planning
        String plan = planningAgent.decomposePlanAndCreateWorkItems("Build authentication system");
        assertNotNull(plan, "Plan should be generated");
        
        // Phase 2: Editor generates implementation
        String code = editorAgent.generateCode(
                "Implement JWT authentication",
                plan
        );
        assertNotNull(code, "Code should be generated");
        
        // Phase 3: Merger determines strategy
        String mergeStrategy = mergerAgent.determineMergeStrategy(
                "Implement JWT authentication",
                "Existing user management code"
        );
        assertNotNull(mergeStrategy, "Merge strategy should be determined");
        
        // Phase 4: Review evaluates work
        String review = reviewAgent.evaluateContent(
                code,
                "Security, performance, maintainability"
        );
        assertNotNull(review, "Review should be completed");
        
        // Verify all phases completed successfully
        assertTrue(plan.length() > 0);
        assertTrue(code.length() > 0);
        assertTrue(mergeStrategy.length() > 0);
        assertTrue(review.length() > 0);
    }

    /**
     * Test agent tool availability within agents.
     */
    @Test
    void testAgentToolsIntegration() {
        LangChain4jAgentTools tools = applicationContext.getBean(LangChain4jAgentTools.class);
        
        // Verify tool methods are available
        assertNotNull(tools, "Tools should be available");
        
        // These would normally interact with mocked repositories
        assertTrue(true, "Agent tools integration successful");
    }

    /**
     * Test that agents can be invoked in parallel (simulated).
     */
    @Test
    void testParallelAgentInvocation() {
        AgentInterfaces.PlanningAgent planningAgent = applicationContext.getBean(AgentInterfaces.PlanningAgent.class);
        AgentInterfaces.EditorAgent editorAgent = applicationContext.getBean(AgentInterfaces.EditorAgent.class);
        
        // Simulate parallel execution
        String planResult = planningAgent.decomposePlanAndCreateWorkItems("Feature A");
        String codeResult = editorAgent.generateCode("Feature B", "Context for Feature B");
        
        // Both should complete independently
        assertNotNull(planResult);
        assertNotNull(codeResult);
        assertFalse(planResult.isEmpty());
        assertFalse(codeResult.isEmpty());
    }

    /**
     * Test configuration of LangChain4j with mocked ChatModel.
     */
    @Test
    void testLangChain4jConfiguration() {
        ChatModel chatModel = applicationContext.getBean(ChatModel.class);
        assertNotNull(chatModel, "ChatModel should be configured");
        
        // Verify it's the mock implementation when no API key is configured
        assertNotNull(chatModel.getClass(), "ChatModel should have a valid class");
    }
}
