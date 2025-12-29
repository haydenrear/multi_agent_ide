package com.hayden.multiagentide.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;

import java.util.Map;
import java.util.Objects;

/**
 * Embabel @Agent definitions for multi-agent IDE.
 * Each agent currently exposes a single action.
 */
public final class AgentInterfaces {

    public static final String ORCHESTRATOR_AGENT_NAME = "OrchestratorAgent";
    public static final String DISCOVERY_ORCHESTRATOR_AGENT_NAME = "DiscoveryOrchestrator";
    public static final String DISCOVERY_AGENT_NAME = "DiscoveryAgent";
    public static final String DISCOVERY_COLLECTOR_AGENT_NAME = "DiscoveryCollector";
    public static final String PLANNING_ORCHESTRATOR_AGENT_NAME = "PlanningOrchestrator";
    public static final String PLANNING_AGENT_NAME = "PlanningAgent";
    public static final String PLANNING_COLLECTOR_AGENT_NAME = "PlanningCollector";
    public static final String TICKET_ORCHESTRATOR_AGENT_NAME = "TicketOrchestrator";
    public static final String TICKET_AGENT_NAME = "TicketAgent";
    public static final String TICKET_COLLECTOR_AGENT_NAME = "TicketCollector";
    public static final String MERGER_AGENT_NAME = "MergerAgent";
    public static final String REVIEW_AGENT_NAME = "ReviewAgent";
    public static final String ORCHESTRATOR_COLLECTOR_AGENT_NAME = "OrchestratorCollectorAgent";
    public static final String CONTEXT_ORCHESTRATOR_AGENT_NAME = "ContextOrchestratorAgent";
    public static final String CONTEXT_AGENT_NAME = "ContextAgent";
    public static final String CONTEXT_COLLECTOR_AGENT_NAME = "ContextCollectorAgent";

    private AgentInterfaces() {
    }

    public record OrchestratorInput(String goal, String phase) {
    }

    public record DiscoveryOrchestratorInput(String goal) {
    }

    public record DiscoveryAgentInput(String goal, String subdomainFocus) {
    }

    public record DiscoveryCollectorInput(String goal, String discoveryResults) {
    }

    public record PlanningOrchestratorInput(String goal) {
    }

    public record PlanningAgentInput(String goal) {
    }

    public record PlanningCollectorInput(String goal, String planningResults) {
    }

    public record TicketOrchestratorInput(
            String goal,
            String tickets,
            String discoveryContext,
            String planningContext
    ) {
    }

    public record TicketAgentInput(
            String ticketDetails,
            String ticketDetailsFilePath,
            String discoveryContext,
            String planningContext
    ) {
    }

    public record TicketCollectorInput(String goal, String ticketResults) {
    }

    public record MergerAgentInput(String mergeContext, String mergeSummary, String conflictFiles) {
    }

    public record ReviewAgentInput(String content, String criteria) {
    }

    public record OrchestratorCollectorInput(String goal, String phase) {
    }

    public record ContextOrchestratorInput(String goal, String phase) {
    }

    public record ContextAgentInput(String goal, String phase) {
    }

    public record ContextCollectorInput(String goal, String phase) {
    }

    private static String renderTemplate(String template, Map<String, String> values) {
        String rendered = template;
        for (var entry : values.entrySet()) {
            rendered = rendered.replace(
                    "{{" + entry.getKey() + "}}",
                    Objects.toString(entry.getValue(), "")
            );
        }
        return rendered;
    }

    @Agent(name = ORCHESTRATOR_AGENT_NAME, description = "Coordinates multiple agents to accomplish complex goals")
    public static class OrchestratorAgent {

        public static final String ORCHESTRATOR_AGENT_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}
                Current phase: {{phase}}
                Available agents: planning, editor, merger, review

                Determine:
                1. Next agent to invoke
                2. Input for that agent
                3. Success criteria
                """;

        @Action
        @AchievesGoal(description = "Coordinate workflow for the current phase")
        public AgentModels.OrchestratorAgentResult coordinateWorkflow(
                OrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.OrchestratorAgentResult.class);
        }
    }

    @Agent(name = DISCOVERY_ORCHESTRATOR_AGENT_NAME, description = "Coordinates discovery work across agents")
    public static class DiscoveryOrchestrator {

        public static final String DISCOVERY_ORCHESTRATOR_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}

                Based on this information and files you find on the repository, or information about the ticket,
                decide how to divide up the discovery phase of the workflow.

                For example, based on the ticket, you may need to divide up to retrieve information about multiple
                libraries, or modules, or the repository may be too big for one agent, so you decide how to divide
                up this work.

                Return how many agents to use to perform the discovery, and how to divide up the work, including an
                an addition to the goal to send that agent.
                """;

        @Action
        @AchievesGoal(description = "Create a discovery delegation plan")
        public AgentModels.DiscoveryOrchestratorResult kickOffAnyNumberOfAgentsForCodeSearch(
                DiscoveryOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                    Map.of("goal", input.goal())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.DiscoveryOrchestratorResult.class);
        }
    }

    @Agent(name = DISCOVERY_AGENT_NAME, description = "Discovers and analyzes codebase structure for specific domains")
    public static class DiscoveryAgent {

        public static final String DISCOVERY_AGENT_START_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Discover codebase section")
        public AgentModels.DiscoveryAgentResult discoverCodebaseSection(
                DiscoveryAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    DISCOVERY_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "subdomainFocus", input.subdomainFocus())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.DiscoveryAgentResult.class);
        }
    }

    @Agent(name = DISCOVERY_COLLECTOR_AGENT_NAME, description = "Consolidates discovery findings into unified codebase understanding")
    public static class DiscoveryCollector {

        public static final String DISCOVERY_COLLECTOR_START_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Consolidate discovery findings")
        public AgentModels.DiscoveryCollectorResult consolidateDiscoveryFindings(
                DiscoveryCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    DISCOVERY_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "discoveryResults", input.discoveryResults())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.DiscoveryCollectorResult.class);
        }
    }

    @Agent(name = PLANNING_ORCHESTRATOR_AGENT_NAME, description = "Split the goal into tickets according to the discovery context")
    public static class PlanningOrchestrator {

        public static final String PLANNING_ORCHESTRATOR_MESSAGE = """
                Decompose the planning for the goal according to the results from discovery.
                Define tickets and update the spec file in .specify/.../spec.md.

                Then, for each ticket, return the information to be provided to the planning agent to
                plan for this ticket.

                Goal: {{goal}}
                """;

        @Action
        @AchievesGoal(description = "Create planning delegation plan")
        public AgentModels.PlanningOrchestratorResult decomposePlanAndCreateWorkItems(
                PlanningOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    PLANNING_ORCHESTRATOR_MESSAGE,
                    Map.of("goal", input.goal())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.PlanningOrchestratorResult.class);
        }
    }

    @Agent(name = PLANNING_AGENT_NAME, description = "Decomposes high-level goals into structured work items")
    public static class PlanningAgent {

        public static final String PLANNING_AGENT_USER_MESSAGE = """
                Analyze the following goal and break it down into 3 work items:
                1. Architecture & Setup - Design foundational structure
                2. Implementation - Core functionality
                3. Testing & Validation - Tests and validation

                Goal: {{goal}}

                Provide a structured plan with clear sections for each work item.
                """;

        @Action
        @AchievesGoal(description = "Create structured plan")
        public AgentModels.PlanningAgentResult decomposePlanAndCreateWorkItems(
                PlanningAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    PLANNING_AGENT_USER_MESSAGE,
                    Map.of("goal", input.goal())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.PlanningAgentResult.class);
        }
    }

    @Agent(name = PLANNING_COLLECTOR_AGENT_NAME, description = "Consolidates planning outputs into structured tickets")
    public static class PlanningCollector {

        public static final String PLANNING_COLLECTOR_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Consolidate planning results into tickets")
        public AgentModels.PlanningCollectorResult consolidatePlansIntoTickets(
                PlanningCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    PLANNING_COLLECTOR_MESSAGE,
                    Map.of("goal", input.goal(), "planningResults", input.planningResults())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.PlanningCollectorResult.class);
        }
    }

    @Agent(name = TICKET_ORCHESTRATOR_AGENT_NAME, description = "Orchestrates ticket-based implementation workflow")
    public static class TicketOrchestrator {

        public static final String TICKET_ORCHESTRATOR_START_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Orchestrate ticket execution")
        public AgentModels.TicketOrchestratorResult orchestrateTicketExecution(
                TicketOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    TICKET_ORCHESTRATOR_START_MESSAGE,
                    Map.of(
                            "goal", input.goal(),
                            "tickets", input.tickets(),
                            "discoveryContext", input.discoveryContext(),
                            "planningContext", input.planningContext()
                    )
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.TicketOrchestratorResult.class);
        }
    }

    @Agent(name = TICKET_AGENT_NAME, description = "Implements individual tickets with complete code generation")
    public static class TicketAgent {

        public static final String TICKET_AGENT_START_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Implement a ticket")
        public AgentModels.TicketAgentResult implementTicket(
                TicketAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    TICKET_AGENT_START_MESSAGE,
                    Map.of(
                            "ticketDetails", input.ticketDetails(),
                            "ticketDetailsFilePath", input.ticketDetailsFilePath(),
                            "discoveryContext", input.discoveryContext(),
                            "planningContext", input.planningContext()
                    )
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.TicketAgentResult.class);
        }
    }

    @Agent(name = TICKET_COLLECTOR_AGENT_NAME, description = "Consolidates ticket execution results into a unified summary")
    public static class TicketCollector {

        public static final String TICKET_COLLECTOR_START_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Consolidate ticket results")
        public AgentModels.TicketCollectorResult consolidateTicketResults(
                TicketCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    TICKET_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "ticketResults", input.ticketResults())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.TicketCollectorResult.class);
        }
    }

    @Agent(name = MERGER_AGENT_NAME, description = "Resolves merge conflicts based on strategy and context")
    public static class MergerAgent {

        public static final String MERGER_AGENT_START_MESSAGE = """
                Review the merge outcome and validate it is correct.

                Merge context:
                {{mergeContext}}

                Merge summary:
                {{mergeSummary}}

                Conflicting files:
                {{conflictFiles}}

                Confirm whether the merge is acceptable. If conflicts exist, outline resolution guidance.
                """;

        @Action
        @AchievesGoal(description = "Review merge result")
        public AgentModels.MergerAgentResult performMerge(
                MergerAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    MERGER_AGENT_START_MESSAGE,
                    Map.of(
                            "mergeContext", input.mergeContext(),
                            "mergeSummary", input.mergeSummary(),
                            "conflictFiles", input.conflictFiles()
                    )
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.MergerAgentResult.class);
        }
    }

    @Agent(name = REVIEW_AGENT_NAME, description = "Evaluates content quality, completeness, and adherence to requirements")
    public static class ReviewAgent {

        public static final String REVIEW_AGENT_START_MESSAGE = """
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

        @Action
        @AchievesGoal(description = "Evaluate content quality")
        public AgentModels.ReviewAgentResult evaluateContent(
                ReviewAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    REVIEW_AGENT_START_MESSAGE,
                    Map.of("content", input.content(), "criteria", input.criteria())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.ReviewAgentResult.class);
        }
    }

    @Agent(
            name = ORCHESTRATOR_COLLECTOR_AGENT_NAME,
            description = "Validates the work of the orchestrator, collecting all the artifacts, reviews, and ensuring that it is correct."
    )
    public static class OrchestratorCollectorAgent {

        public static final String ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE = """
                Consolidate the workflow results and provide a routing decision:
                - ROUTE_BACK to rerun the final collection
                - ADVANCE_PHASE to finalize and complete the goal
                - STOP to halt the workflow
                """;

        @Action
        @AchievesGoal(description = "Consolidate orchestrator workflow outputs")
        public AgentModels.OrchestratorCollectorResult coordinateWorkflow(
                OrchestratorCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.OrchestratorCollectorResult.class);
        }
    }

    @Agent(
            name = CONTEXT_ORCHESTRATOR_AGENT_NAME,
            description = "Orchestrates context operations for the workflow."
    )
    public static class ContextOrchestratorAgent {

        public static final String CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE = """
//                TODO:
                """;

        @Action
        @AchievesGoal(description = "Coordinate context operations")
        public AgentModels.OrchestratorAgentResult coordinateWorkflow(
                ContextOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.OrchestratorAgentResult.class);
        }
    }

    @Agent(
            name = CONTEXT_AGENT_NAME,
            description = "Handles reviewing and pruning context."
    )
    public static class ContextAgent {

        public static final String CONTEXT_AGENT_START_MESSAGE = """
//                TODO:
                """;

        @Action
        @AchievesGoal(description = "Apply context operations")
        public AgentModels.ContextAgentResult applyContextOperations(
                ContextAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    CONTEXT_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.ContextAgentResult.class);
        }
    }

    @Agent(
            name = CONTEXT_COLLECTOR_AGENT_NAME,
            description = "Merges context operations into a consolidated context."
    )
    public static class ContextCollectorAgent {

        public static final String CONTEXT_COLLECTOR_START_MESSAGE = """
//                TODO:
                """;

        @Action
        @AchievesGoal(description = "Consolidate context operations")
        public AgentModels.ContextCollectorResult coordinateWorkflow(
                ContextCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    CONTEXT_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, AgentModels.ContextCollectorResult.class);
        }
    }
}
