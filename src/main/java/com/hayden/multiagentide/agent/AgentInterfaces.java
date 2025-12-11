package com.hayden.multiagentide.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * LangChain4j @Agent interfaces for multi-agent IDE.
 * These interfaces define the contract for LLM-powered agents.
 */
public class AgentInterfaces {

    /**
     * Planning agent that decomposes goals into work items.
     */
    public interface PlanningAgent {
        @Agent(value = "Decomposes high-level goals into structured work items with clear tasks and dependencies")
        @UserMessage("""
                Analyze the following goal and break it down into 3 work items:
                1. Architecture & Setup - Design foundational structure
                2. Implementation - Core functionality
                3. Testing & Validation - Tests and validation
                
                Goal: {{goal}}
                
                Provide a structured plan with clear sections for each work item.
                """)
        String decomposePlanAndCreateWorkItems(@V("goal") String goal);
    }

    /**
     * Editor agent that generates code based on specifications.
     */
    public interface EditorAgent {
        @Agent(value = "Generates code implementations based on goals and context from specifications")
        @UserMessage("""
                Generate code implementation for the following work item:
                
                Goal: {{goal}}
                
                Context from spec:
                {{context}}
                
                Provide complete, production-ready code with proper structure and error handling.
                """)
        String generateCode(@V("goal") String goal, @V("context") String context);
    }

    /**
     * Merger agent that determines merge strategies and resolves conflicts.
     */
    public interface MergerAgent {
        @Agent(value = "Analyzes code changes and determines optimal merge strategies")
        @UserMessage("""
                Determine the best merge strategy for combining these changes:
                
                Child goal: {{childGoal}}
                Parent goal: {{parentGoal}}
                
                Consider:
                - Code overlap and conflicts
                - Dependency ordering
                - Feature compatibility
                
                Recommend a merge strategy: fast-forward, recursive, or manual.
                """)
        String determineMergeStrategy(@V("childGoal") String childGoal, @V("parentGoal") String parentGoal);

        @Agent(value = "Resolves merge conflicts based on strategy and context")
        @UserMessage("""
                Resolve the following merge conflicts using strategy: {{strategy}}
                
                Conflicting files: {{conflictFiles}}
                
                Provide resolution approach for each conflict.
                """)
        String resolveConflicts(@V("conflictFiles") String conflictFiles, @V("strategy") String strategy);
    }

    /**
     * Review agent that evaluates code quality and completeness.
     */
    public interface ReviewAgent {
        @Agent(value = "Evaluates content quality, completeness, and adherence to requirements")
        @UserMessage("""
                Review the following content against these criteria:
                
                Content:
                {{content}}
                
                Criteria: {{criteria}}
                
                Provide evaluation with:
                - Overall assessment (APPROVED/NEEDS_REVISION)
                - Specific feedback on quality
                - Suggestions for improvement
                """)
        String evaluateContent(@V("content") String content, @V("criteria") String criteria);
    }

    /**
     * Orchestrator agent that coordinates multi-agent workflows.
     */
    public interface OrchestratorAgent {
        @Agent(value = "Coordinates multiple agents to accomplish complex goals")
        @UserMessage("""
                Coordinate the following multi-agent workflow:
                
                Goal: {{goal}}
                Current phase: {{phase}}
                Available agents: planning, editor, merger, review
                
                Determine:
                1. Next agent to invoke
                2. Input for that agent
                3. Success criteria
                """)
        String coordinateWorkflow(@V("goal") String goal, @V("phase") String phase);
    }
}
