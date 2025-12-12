package com.hayden.multiagentide.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * LangChain4j @Agent interfaces for multi-agent IDE.
 * These interfaces define the contract for LLM-powered agents.
 */
public class AgentInterfaces {

    public interface PlanningOrchestrator {
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

    public interface PlanningSummarizer {
        @Agent(value = "")
        @UserMessage("""
                """)
        String decomposePlanAndCreateWorkItems(@V("goal") String goal);
    }

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

    public interface DiscoveryOrchestrator {
        @Agent(value = "")
        @UserMessage("""
                Coordinate the following multi-agent workflow:
                
                Goal: {{goal}}
                
                Based on this information and files you find on the repository, or information about the ticket,
                decide how to divide up the discovery phase of the workflow.
                
                For example, based on the ticket, you may need to divide up to retrieve information about multiple
                libraries, or modules, or the repository may be too big for one agent, so you decide how to divide
                up this work.
                
                Return how many agents to use to perform the discovery, and how to divide up the work, including an
                an addition to the goal to send that agent.
                """)
        String kickOffAnyNumberOfAgentsForCodeSearch(@V("goal") String goal);
    }

    public interface DiscoveryMerger {
        @Agent(value = "Decomposes high-level goals into structured work items with clear tasks and dependencies")
        @UserMessage("""
                Coordinate the merging of the multi-agent workflow from discovery:
                
                Goal: {{goal}}
                Agent Results: {{agentic_results}}
                
                Based on this information and files you find on the repository, or information about the ticket,
                decide how to divide up the discovery phase of the workflow.
                
                For example, based on the ticket, you may need to divide up to retrieve information about multiple
                libraries, or modules, or the repository may be too big for one agent, so you decide how to divide
                up this work.
                
                Return how many agents to use to perform the discovery, and how to divide up the work, including an
                an addition to the goal to send that agent.
                
                Please use your tools to merge the skills file.
                """)
        String kickOffAnyNumberOfAgentsForCodeSearch(@V("goal") String goal, @V("agentic_results") String agenticResults);
    }

    public interface DiscoveryAgent {
        @Agent(value = "Decomposes high-level goals into structured work items with clear tasks and dependencies")
        @UserMessage("""
                Analyze the following goal and break it down into 3 work items:
                1. Architecture & Setup - Design foundational structure
                2. Implementation - Core functionality
                3. Testing & Validation - Tests and validation
                
                Goal: {{goal}}
                
                Provide a structured plan with clear sections for each work item.
                
                Please use your tools to create a skills file with name {{skeelz}}.
                Please return the name of the skeelz file and any additional information or metadata
                you'd like to add with regards to merging this skeelz file.
                """)
        String searchThroughTheCodeBase(@V("goal") String goal, @V("skeelz") String skeelz);
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

        @Agent(value = "Resolves merge conflicts based on strategy and context")
        @UserMessage("""
                Resolve the following merge conflicts
                
                Conflicting files: {{conflictFiles}}
                
                Provide resolution approach for each conflict.
                """)
        String performMerge(@V("conflictFiles") String conflictFiles);
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
