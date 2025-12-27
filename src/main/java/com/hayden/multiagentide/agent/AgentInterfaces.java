package com.hayden.multiagentide.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * LangChain4j @Agent interfaces for multi-agent IDE.
 * These interfaces define the contract for LLM-powered agents.
 */
public class AgentInterfaces {

    public interface PlanningOrchestrator {

        String PLANNING_ORCHESTRATOR_MESSAGE = """
                Decompose the planning for the goal according to the results from discovery.
                Define tickets and update the spec file in .specify/.../spec.md.
                
                Then, for each ticket, return the information to be provided to the planning agent to
                plan for this ticket.
                
                Goal: {{goal}}
                """;

        @Agent(value = "Split the goal into tickets according to the discovery context.")
//       TODO: add discovery context, return
        AgentModels.PlanningOrchestratorResult decomposePlanAndCreateWorkItems(@MemoryId String memId,
                                                                               @UserMessage String msg,
                                                                               @V("goal") String goal);
    }

    /**
     * Merger agent that consolidates planning results from multiple agents into tickets.
     */
    public interface PlanningCollector {

        String PLANNING_COLLECTOR_MESSAGE = """
                Merge and consolidate the following planning results from multiple agents:

                Goal: {{goal}}
                Planning Results: {{planningResults}}

                Create structured tickets with:
                - Ticket ID and title
                - Clear implementation tasks
                - Dependencies between tickets
                - Acceptance criteria
                - Estimated effort

                Return merged tickets in structured format, and include a routing decision:
                - ROUTE_BACK to rerun planning
                - ADVANCE_PHASE to continue to ticket execution
                - STOP to halt the workflow
                """;

        @Agent(value = "Consolidates planning outputs into structured tickets")
        AgentModels.PlanningCollectorResult consolidatePlansIntoTickets(@MemoryId String memId,
                                                                        @UserMessage String msg,
                                                                        @V("goal") String goal,
                                                                        @V("planningResults") String planningResults);
    }

    /**
     * Planning agent that decomposes goals into work items.
     */
    public interface PlanningAgent {

        String PLANNING_AGENT_USER_MESSAGE = """
                Analyze the following goal and break it down into 3 work items:
                1. Architecture & Setup - Design foundational structure
                2. Implementation - Core functionality
                3. Testing & Validation - Tests and validation

                Goal: {{goal}}

                Provide a structured plan with clear sections for each work item.
                """;

        @Agent(value = "Decomposes high-level goals into structured work items with clear tasks and dependencies")
        AgentModels.PlanningAgentResult decomposePlanAndCreateWorkItems(@MemoryId String memId,
                                                                        @UserMessage String msg,
                                                                        @V("goal") String goal);
    }

    public interface DiscoveryOrchestrator {
        String DISCOVERY_ORCHESTRATOR_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}

                Based on this information and files you find on the repository, or information about the ticket,
                decide how to divide up the discovery phase of the workflow.

                For example, based on the ticket, you may need to divide up to retrieve information about multiple
                libraries, or modules, or the repository may be too big for one agent, so you decide how to divide
                up this work.

                Return how many agents to use to perform the discovery, and how to divide up the work, including an
                an addition to the goal to send that agent.
                """ ;

        @Agent(value = "")
        AgentModels.DiscoveryOrchestratorResult kickOffAnyNumberOfAgentsForCodeSearch(@MemoryId String memId,
                                                                                      @UserMessage String msg,
                                                                                      @V("goal") String goal);
    }

    /**
     * Merger agent that consolidates discovery findings from multiple agents.
     */
    public interface DiscoveryCollector {

        String DISCOVERY_COLLECTOR_START_MESSAGE = """
                Merge and consolidate the following discovery results from multiple agents:

                Goal: {{goal}}
                Discovery Results: {{discoveryResults}}

                Create a unified discovery document with:
                - Architecture overview
                - Key modules and components
                - Data flow and dependencies
                - Integration points
                - Technology stack summary
                - Test patterns and conventions
                - Critical files and entry points

                Return consolidated discovery in structured format suitable for planning phase, and include a routing decision:
                - ROUTE_BACK to rerun discovery
                - ADVANCE_PHASE to continue to planning
                - STOP to halt the workflow
                """;

        @Agent(value = "Consolidates discovery findings into unified codebase understanding")
        AgentModels.DiscoveryCollectorResult consolidateDiscoveryFindings(@MemoryId String memId,
                                                                          @UserMessage String msg,
                                                                          @V("goal") String goal,
                                                                          @V("discoveryResults") String discoveryResults);
    }

    /**
     * Discovery agent that analyzes specific areas of the codebase.
     */
    public interface DiscoveryAgent {

        String DISCOVERY_AGENT_START_MESSAGE = """
                Discover and analyze the codebase for the following subdomain:

                Overall Goal: {{goal}}
                Subdomain Focus: {{subdomainFocus}}

                Use your tools to:
                1. Search for relevant files and modules
                2. Analyze key source files
                3. Understand dependencies and imports
                4. Identify architectural patterns
                5. Document test patterns

                Generate comprehensive discovery findings including:
                - Module overview
                - Key classes and responsibilities
                - Data flow patterns
                - Integration points
                - Technology stack
                - Test patterns

                Return findings as structured document suitable for merging.
                """;

        @Agent(value = "Discovers and analyzes codebase structure for specific domains")
        AgentModels.DiscoveryAgentResult discoverCodebaseSection(@MemoryId String memId,
                                                                 @UserMessage String msg,
                                                                 @V("goal") String goal,
                                                                 @V("subdomainFocus") String subdomainFocus);
    }

    /**
     * Ticket orchestrator that coordinates ticket-based implementation.
     */
    public interface TicketOrchestrator {
        String TICKET_ORCHESTRATOR_START_MESSAGE = """
                Orchestrate ticket-based implementation for the following tickets:

                Goal: {{goal}}
                Tickets: {{tickets}}
                Discovery Context: {{discoveryContext}}
                Planning Context: {{planningContext}}

                Coordinate execution of each ticket:
                1. Determine ticket execution order and dependencies
                2. Create ticket work details for each ticket
                3. Prepare worktree context for ticket agents
                4. Monitor ticket completion and quality

                Return orchestration plan and ticket coordination strategy.
                """;

        @Agent(value = "Orchestrates ticket-based implementation workflow")
        @UserMessage()
        AgentModels.TicketOrchestratorResult orchestrateTicketExecution(@MemoryId String memId,
                                                                        @UserMessage String msg,
                                                                        @V("goal") String goal,
                                                                        @V("tickets") String tickets,
                                                                        @V("discoveryContext") String discoveryContext,
                                                                        @V("planningContext") String planningContext);
    }

    /**
     * Collector agent that consolidates ticket execution outputs.
     */
    public interface TicketCollector {
        String TICKET_COLLECTOR_START_MESSAGE = """
                Merge and consolidate the following ticket execution results:

                Goal: {{goal}}
                Ticket Results: {{ticketResults}}

                Produce a summary with:
                - Completed tickets
                - Failed tickets (with brief reasons)
                - Outstanding follow-ups

                Include a routing decision:
                - ROUTE_BACK to rerun ticket execution
                - ADVANCE_PHASE to continue to finalization
                - STOP to halt the workflow
                """;

        @Agent(value = "Consolidates ticket execution results into a unified summary")
        AgentModels.TicketCollectorResult consolidateTicketResults(@MemoryId String memId,
                                                                   @UserMessage String msg,
                                                                   @V("goal") String goal,
                                                                   @V("ticketResults") String ticketResults);
    }


    /**
     * Ticket agent that implements individual tickets.
     */
    public interface TicketAgent {

        String TICKET_AGENT_START_MESSAGE = """
                Implement the following ticket with complete, production-ready code:

                Ticket Details: {{ticketDetails}}
                Ticket File Path: {{ticketDetailsFilePath}}

                Discovery Context: {{discoveryContext}}
                Planning Context: {{planningContext}}

                Implementation requirements:
                1. Read ticket details from the provided file path
                2. Analyze discovery and planning context
                3. Use tools to read existing code and understand patterns
                4. Generate complete implementation
                5. Create/modify files in the worktree
                6. Run tests in the worktree
                7. Commit implementation to feature branch

                Return comprehensive implementation summary including:
                - Files modified/created
                - Key implementation decisions
                - Test results
                - Worktree state

                Provide complete, production-ready code with proper structure and error handling.
                """;

        @Agent(value = "Implements individual tickets with complete code generation")
        AgentModels.TicketAgentResult implementTicket(@MemoryId String memId,
                                                      @UserMessage String msg,
                                                      @V("ticketDetails") String ticketDetails,
                                                      @V("ticketDetailsFilePath") String ticketDetailsFilePath,
                                                      @V("discoveryContext") String discoveryContext,
                                                      @V("planningContext") String planningContext);
    }

    /**
     * Merger agent that determines merge strategies and resolves conflicts.
     */
    public interface MergerAgent {

        String MERGER_AGENT_START_MESSAGE = """
                Review the merge outcome and validate it is correct.

                Merge context:
                {{mergeContext}}

                Merge summary:
                {{mergeSummary}}

                Conflicting files:
                {{conflictFiles}}

                Confirm whether the merge is acceptable. If conflicts exist, outline resolution guidance.
                """;

        @Agent(value = "Resolves merge conflicts based on strategy and context")
        AgentModels.MergerAgentResult performMerge(@MemoryId String memId,
                                                   @UserMessage String msg,
                                                   @V("mergeContext") String mergeContext,
                                                   @V("mergeSummary") String mergeSummary,
                                                   @V("conflictFiles") String conflictFiles);
    }

    /**
     * Review agent that evaluates code quality and completeness.
     */
    public interface ReviewAgent {
        String REVIEW_AGENT_START_MESSAGE = """
                Review the following content against these criteria:

                Content:
                {{content}}

                Criteria: {{criteria}}

                Provide evaluation with:
                - Overall assessment (APPROVED/NEEDS_REVISION)
                - Specific feedback on quality
                - Suggestions for improvement
                
                Additionally, if you have further questions, return an indicator for whether a human should review.
                """;

        @Agent(value = "Evaluates content quality, completeness, and adherence to requirements")
        AgentModels.ReviewAgentResult evaluateContent(@MemoryId String memId,
                                                      @UserMessage String msg,
                                                      @V("content") String content,
                                                      @V("criteria") String criteria);
    }

    /**
     * Orchestrator agent that coordinates multi-agent workflows.
     */
    public interface OrchestratorAgent {

        String ORCHESTRATOR_AGENT_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}
                Current phase: {{phase}}
                Available agents: planning, editor, merger, review

                Determine:
                1. Next agent to invoke
                2. Input for that agent
                3. Success criteria
                """;

        @Agent(value = "Coordinates multiple agents to accomplish complex goals")
        AgentModels.OrchestratorAgentResult coordinateWorkflow(@MemoryId String memId,
                                                               @UserMessage String msg,
                                                               @V("goal") String goal,
                                                               @V("phase") String phase);
    }

//    TODO:
    public interface OrchestratorCollectorAgent {

        String ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE = """
                Consolidate the workflow results and provide a routing decision:
                - ROUTE_BACK to rerun the final collection
                - ADVANCE_PHASE to finalize and complete the goal
                - STOP to halt the workflow
                """;

        /**
         * Takes the results of the workflow, reviews everything as a final step, decides if it's valid.
         * It then massages the commits to make them more streamlined and easy to digest, adding any metadata,
         *  updating the workflow outputs in the repository for cross-cutting concerns or to fill in any cross-
         *  agent connections across the architecture.
         * It then runs all tests, integration tests in test_graph making sure everything looks good there,
         *  merging together all of those changes, massaging that commit.
         * Then, it searches through the contexts and refines the memory episode for the commits. All
         *  agents would have registered any context summary with the context agents, and they would have
         *  their documentations and commits, so at this point, the orchestrator collector needs to
         *  take all of that, and provide a memory episode that will be searchable and usable for future
         *  commits. This, along with the code and docs, is the primary "output" of the process, so it's very
         *  important - it will be searchable and retrievable for future commits.
         * @param memId
         * @param msg
         * @param goal
         * @param phase
         * @return
         */
        @Agent(value = "Validates the work of the orchestrator, collecting all the artifacts, reviews, and ensuring that it is correct.")
        AgentModels.OrchestratorCollectorResult coordinateWorkflow(@MemoryId String memId,
                                                                   @UserMessage String msg,
                                                                   @V("goal") String goal,
                                                                   @V("phase") String phase);
    }

    /**
     * Agent orchestrates as a check-in point for agents.
     * Provided with the context toolset, which includes pruning,
     *  saving, retrieving, etc., context items, and leaving an index/summary
     *  where they were.
     * This agent then delegates to multiple context agents to handle
     *  parts of the managing the context.
     * Agents will also call this agent when they need to expand a part of their context,
     *  or retrieve a part of the context that was saved, by providing the id
     *  that was left by these agents previously.
     */
    public interface ContextOrchestratorAgent {

        String CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE = """
//                TODO:
                """;

        @Agent(value = "Validates the work of the orchestrator, collecting all the artifacts, reviews, and ensuring that it is correct.")
        AgentModels.OrchestratorAgentResult coordinateWorkflow(@MemoryId String memId,
                                                               @UserMessage String msg,
                                                               @V("goal") String goal,
                                                               @V("phase") String phase);
    }

    /**
     * Handles reviewing context, pruning context - makes recommendations for
     *  operations, such as striking specific things from the tool calls,
     *  registering results and leaving indexes (to check later),
     *  summarizing specific pieces of the context, etc. It does this using
     *  the context toolset in LLM Context Manager.
     */
    public interface ContextAgent {

        String CONTEXT_AGENT_START_MESSAGE = """
//                TODO:
                """;

        @Agent(value = "Validates the work of the orchestrator, collecting all the artifacts, reviews, and ensuring that it is correct.")
        AgentModels.ContextAgentResult applyContextOperations(@MemoryId String memId,
                                                              @UserMessage String msg,
                                                              @V("goal") String goal,
                                                              @V("phase") String phase);
    }

    /**
     * Merges together the results from ContextAgents, taking the previous context
     *  and the actions that the ContextAgent's took and providing the next context
     *  that operates as the full context used by the agent - meaning then the context
     *  is reset for the agent calling this workflow to the item returned.
     */
    public interface ContextCollectorAgent {

        String CONTEXT_COLLECTOR_START_MESSAGE = """
//                TODO:
                """;

        @Agent(value = "Validates the work of the orchestrator, collecting all the artifacts, reviews, and ensuring that it is correct.")
        AgentModels.ContextCollectorResult coordinateWorkflow(@MemoryId String memId,
                                                              @UserMessage String msg,
                                                              @V("goal") String goal,
                                                              @V("phase") String phase);
    }
}
