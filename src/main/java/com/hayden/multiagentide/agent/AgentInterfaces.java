package com.hayden.multiagentide.agent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * Embabel @Agent definitions for multi-agent IDE.
 * Each agent currently exposes a single action.
 */
public sealed interface AgentInterfaces permits AgentInterfaces.ContextAgent, AgentInterfaces.ContextCollectorAgent, AgentInterfaces.ContextOrchestratorAgent, AgentInterfaces.DiscoveryAgent, AgentInterfaces.DiscoveryCollector, AgentInterfaces.DiscoveryOrchestrator, AgentInterfaces.MergerAgent, AgentInterfaces.OrchestratorAgent, AgentInterfaces.OrchestratorCollectorAgent, AgentInterfaces.PlanningAgent, AgentInterfaces.PlanningCollector, AgentInterfaces.ReviewAgent, AgentInterfaces.TicketAgent, AgentInterfaces.TicketCollector, AgentInterfaces.TicketOrchestrator, AgentInterfaces.PlanningOrchestrator {

    String multiAgentAgentName();

    String ORCHESTRATOR_AGENT_NAME = "OrchestratorAgent";
    String DISCOVERY_ORCHESTRATOR_AGENT_NAME = "DiscoveryOrchestrator";
    String DISCOVERY_AGENT_NAME = "DiscoveryAgent";
    String DISCOVERY_COLLECTOR_AGENT_NAME = "DiscoveryCollector";
    String PLANNING_ORCHESTRATOR_AGENT_NAME = "PlanningOrchestrator";
    String PLANNING_AGENT_NAME = "PlanningAgent";
    String PLANNING_COLLECTOR_AGENT_NAME = "PlanningCollector";
    String TICKET_ORCHESTRATOR_AGENT_NAME = "TicketOrchestrator";
    String TICKET_AGENT_NAME = "TicketAgent";
    String TICKET_COLLECTOR_AGENT_NAME = "TicketCollector";
    String MERGER_AGENT_NAME = "MergerAgent";
    String REVIEW_AGENT_NAME = "ReviewAgent";
    String ORCHESTRATOR_COLLECTOR_AGENT_NAME = "OrchestratorCollectorAgent";
    String CONTEXT_ORCHESTRATOR_AGENT_NAME = "ContextOrchestratorAgent";
    String CONTEXT_AGENT_NAME = "ContextAgent";
    String CONTEXT_COLLECTOR_AGENT_NAME = "ContextCollectorAgent";

    OrchestratorAgent ORCHESTRATOR_AGENT = new OrchestratorAgent();
    DiscoveryOrchestrator DISCOVERY_ORCHESTRATOR_AGENT = new DiscoveryOrchestrator();
    DiscoveryAgent DISCOVERY_AGENT = new DiscoveryAgent();
    DiscoveryCollector DISCOVERY_COLLECTOR_AGENT = new DiscoveryCollector();
    PlanningOrchestrator PLANNING_ORCHESTRATOR_AGENT = new PlanningOrchestrator();
    PlanningAgent PLANNING_AGENT = new PlanningAgent();
    PlanningCollector PLANNING_COLLECTOR_AGENT = new PlanningCollector();
    TicketOrchestrator TICKET_ORCHESTRATOR_AGENT = new TicketOrchestrator();
    TicketAgent TICKET_AGENT = new TicketAgent();
    TicketCollector TICKET_COLLECTOR_AGENT = new TicketCollector();
    MergerAgent MERGER_AGENT = new MergerAgent();
    ReviewAgent REVIEW_AGENT = new ReviewAgent ();
    OrchestratorCollectorAgent ORCHESTRATOR_COLLECTOR_AGENT = new OrchestratorCollectorAgent();
    ContextOrchestratorAgent CONTEXT_ORCHESTRATOR_AGENT = new ContextOrchestratorAgent();
    ContextAgent CONTEXT_AGENT = new ContextAgent();
    ContextCollectorAgent CONTEXT_COLLECTOR_AGENT = new ContextCollectorAgent();

    record OrchestratorInput(String goal, String phase) {
    }

    record DiscoveryOrchestratorInput(String goal) {
    }

    record DiscoveryAgentInput(String goal, String subdomainFocus) {
    }

    record DiscoveryCollectorInput(String goal, String discoveryResults) {
    }

    record PlanningOrchestratorInput(String goal) {
    }

    record PlanningAgentInput(String goal) {
    }

    record PlanningCollectorInput(String goal, String planningResults) {
    }

    record TicketOrchestratorInput(
            String goal,
            String tickets,
            String discoveryContext,
            String planningContext
    ) {
    }

    record TicketAgentInput(
            String ticketDetails,
            String ticketDetailsFilePath,
            String discoveryContext,
            String planningContext
    ) {
    }

    record TicketCollectorInput(String goal, String ticketResults) {
    }

    record MergerAgentInput(String mergeContext, String mergeSummary, String conflictFiles) {
    }

    record ReviewAgentInput(String content, String criteria) {
    }

    record OrchestratorCollectorInput(String goal, String phase) {
    }

    record ContextOrchestratorInput(String goal, String phase) {
    }

    record ContextAgentInput(String goal, String phase) {
    }

    record ContextCollectorInput(String goal, String phase) {
    }

    public static String renderTemplate(String template, Map<String, String> values) {
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
    record OrchestratorAgent() implements AgentInterfaces {

        public String multiAgentAgentName() {
            return ORCHESTRATOR_AGENT_NAME;
        }

        public static final String ORCHESTRATOR_AGENT_START_MESSAGE = """
                Coordinate the following multi-agent workflow:

                Goal: {{goal}}
                Current phase: {{phase}}

                Determine:
                1. Next agent to invoke
                2. Input for that agent
                3. Success criteria
                """;

        @Action
        @AchievesGoal(description = "Coordinate workflow for the current phase")
        public com.hayden.multiagentidelib.agent.AgentModels.OrchestratorAgentResult coordinateWorkflow(
                OrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.OrchestratorAgentResult.class);
        }
    }

    @Agent(name = DISCOVERY_COLLECTOR_AGENT_NAME, description = "Consolidates discovery findings into unified codebase understanding")
    record DiscoveryCollector() implements AgentInterfaces {

        public String multiAgentAgentName() {
            return DISCOVERY_COLLECTOR_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.DiscoveryCollectorResult consolidateDiscoveryFindings(
                DiscoveryCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    DISCOVERY_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "discoveryResults", input.discoveryResults())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.DiscoveryCollectorResult.class);
        }
    }

    @Agent(name = PLANNING_AGENT_NAME, description = "Decomposes high-level goals into structured work items")
    record PlanningAgent() implements AgentInterfaces {
        @Override
        public String multiAgentAgentName() {
            return PLANNING_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.PlanningAgentResult decomposePlanAndCreateWorkItems(
                PlanningAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    PLANNING_AGENT_USER_MESSAGE,
                    Map.of("goal", input.goal())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.PlanningAgentResult.class);
        }
    }

    @Agent(name = PLANNING_COLLECTOR_AGENT_NAME, description = "Consolidates planning outputs into structured tickets")
    record PlanningCollector() implements AgentInterfaces {
        @Override
        public String multiAgentAgentName() {
            return PLANNING_COLLECTOR_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.PlanningCollectorResult consolidatePlansIntoTickets(
                PlanningCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    PLANNING_COLLECTOR_MESSAGE,
                    Map.of("goal", input.goal(), "planningResults", input.planningResults())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.PlanningCollectorResult.class);
        }
    }

    @Agent(name = TICKET_ORCHESTRATOR_AGENT_NAME, description = "Orchestrates ticket-based implementation workflow")
    record TicketOrchestrator() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return TICKET_ORCHESTRATOR_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.TicketOrchestratorResult orchestrateTicketExecution(
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
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.TicketOrchestratorResult.class);
        }
    }

    @Agent(name = TICKET_AGENT_NAME, description = "Implements individual tickets with complete code generation")
    record TicketAgent() implements AgentInterfaces {

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
        public com.hayden.multiagentidelib.agent.AgentModels.TicketAgentResult implementTicket(
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
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.TicketAgentResult.class);
        }

        @Override
        public String multiAgentAgentName() {
            return TICKET_AGENT_NAME;
        }
    }

    @Agent(name = TICKET_COLLECTOR_AGENT_NAME, description = "Consolidates ticket execution results into a unified summary")
    record TicketCollector() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return TICKET_COLLECTOR_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.TicketCollectorResult consolidateTicketResults(
                TicketCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    TICKET_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "ticketResults", input.ticketResults())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.TicketCollectorResult.class);
        }
    }

    @Agent(name = MERGER_AGENT_NAME, description = "Resolves merge conflicts based on strategy and context")
    record MergerAgent() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return MERGER_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.MergerAgentResult performMerge(
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
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.MergerAgentResult.class);
        }
    }

    @Agent(name = REVIEW_AGENT_NAME, description = "Evaluates content quality, completeness, and adherence to requirements")
    record ReviewAgent() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return REVIEW_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.ReviewAgentResult evaluateContent(
                ReviewAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    REVIEW_AGENT_START_MESSAGE,
                    Map.of("content", input.content(), "criteria", input.criteria())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.ReviewAgentResult.class);
        }
    }

    @Agent(
            name = ORCHESTRATOR_COLLECTOR_AGENT_NAME,
            description = "Validates the work of the orchestrator, collecting all the artifacts, reviews, and ensuring that it is correct."
    )
    record OrchestratorCollectorAgent() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return ORCHESTRATOR_COLLECTOR_AGENT_NAME;
        }

        public static final String ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE = """
                Consolidate the workflow results and provide a routing decision:
                - ROUTE_BACK to rerun the final collection
                - ADVANCE_PHASE to finalize and complete the goal
                - STOP to halt the workflow
                """;

        @Action
        @AchievesGoal(description = "Consolidate orchestrator workflow outputs")
        public com.hayden.multiagentidelib.agent.AgentModels.OrchestratorCollectorResult coordinateWorkflow(
                OrchestratorCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    ORCHESTRATOR_COLLECTOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.OrchestratorCollectorResult.class);
        }
    }

    @Agent(
            name = CONTEXT_ORCHESTRATOR_AGENT_NAME,
            description = "Orchestrates context operations for the workflow."
    )
    record ContextOrchestratorAgent() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return CONTEXT_ORCHESTRATOR_AGENT_NAME;
        }

        public static final String CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE = """
//                TODO:
                """;

        @Action
        @AchievesGoal(description = "Coordinate context operations")
        public com.hayden.multiagentidelib.agent.AgentModels.OrchestratorAgentResult coordinateWorkflow(
                ContextOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    CONTEXT_ORCHESTRATOR_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.OrchestratorAgentResult.class);
        }
    }

    @Agent(
            name = CONTEXT_AGENT_NAME,
            description = "Handles reviewing and pruning context."
    )
    record ContextAgent() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return CONTEXT_AGENT_NAME;
        }

        public static final String CONTEXT_AGENT_START_MESSAGE = """
//                TODO:
                """;

        @Action
        @AchievesGoal(description = "Apply context operations")
        public com.hayden.multiagentidelib.agent.AgentModels.ContextAgentResult applyContextOperations(
                ContextAgentInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    CONTEXT_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.ContextAgentResult.class);
        }
    }

    @Agent(
            name = CONTEXT_COLLECTOR_AGENT_NAME,
            description = "Merges context operations into a consolidated context."
    )
    record ContextCollectorAgent() implements AgentInterfaces {

        @Override
        public String multiAgentAgentName() {
            return CONTEXT_COLLECTOR_AGENT_NAME;
        }

        public static final String CONTEXT_COLLECTOR_START_MESSAGE = """
//                TODO:
                """;

        @Action
        @AchievesGoal(description = "Consolidate context operations")
        public com.hayden.multiagentidelib.agent.AgentModels.ContextCollectorResult coordinateWorkflow(
                ContextCollectorInput input,
                OperationContext context
        ) {
            String prompt = renderTemplate(
                    CONTEXT_COLLECTOR_START_MESSAGE,
                    Map.of("goal", input.goal(), "phase", input.phase())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.ContextCollectorResult.class);
        }
    }

    @Agent(name = DISCOVERY_AGENT_NAME, description = "Discovers and analyzes codebase structure for specific domains")
    record DiscoveryAgent() implements AgentInterfaces {
        public String multiAgentAgentName() {
            return DISCOVERY_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.DiscoveryAgentResult discoverCodebaseSection(
                DiscoveryAgentInput input,
                OperationContext context
        ) {
            String prompt = AgentInterfaces.renderTemplate(
                    DISCOVERY_AGENT_START_MESSAGE,
                    Map.of("goal", input.goal(), "subdomainFocus", input.subdomainFocus())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.DiscoveryAgentResult.class);
        }
    }

    @Agent(name = DISCOVERY_ORCHESTRATOR_AGENT_NAME, description = "Coordinates discovery work across agents")
    record DiscoveryOrchestrator() implements AgentInterfaces {

        public String multiAgentAgentName() {
            return DISCOVERY_ORCHESTRATOR_AGENT_NAME;
        }

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
        public com.hayden.multiagentidelib.agent.AgentModels.DiscoveryOrchestratorResult kickOffAnyNumberOfAgentsForCodeSearch(
                DiscoveryOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = AgentInterfaces.renderTemplate(
                    DISCOVERY_ORCHESTRATOR_START_MESSAGE,
                    Map.of("goal", input.goal())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.DiscoveryOrchestratorResult.class);
        }
    }

    @Agent(name = PLANNING_ORCHESTRATOR_AGENT_NAME,
            description = "Split the goal into tickets according to the discovery context")
    @Component(PLANNING_ORCHESTRATOR_AGENT_NAME)
    record PlanningOrchestrator() implements AgentInterfaces {


        public static final String PLANNING_ORCHESTRATOR_MESSAGE = """
                Decompose the planning for the goal according to the results from discovery.
                Define tickets and update the spec file in .specify/.../spec.md.
                
                Then, for each ticket, return the information to be provided to the planning agent to
                plan for this ticket.
                
                Goal: {{goal}}
                """;

        @Action
        @AchievesGoal(description = "Create planning delegation plan")
        public com.hayden.multiagentidelib.agent.AgentModels.PlanningOrchestratorResult decomposePlanAndCreateWorkItems(
                PlanningOrchestratorInput input,
                OperationContext context
        ) {
            String prompt = AgentInterfaces.renderTemplate(
                    PLANNING_ORCHESTRATOR_MESSAGE,
                    Map.of("goal", input.goal())
            );
            return context.ai().withDefaultLlm().createObject(prompt, com.hayden.multiagentidelib.agent.AgentModels.PlanningOrchestratorResult.class);
        }

        @Override
        public String multiAgentAgentName() {
            return PLANNING_ORCHESTRATOR_AGENT_NAME;
        }
    }
}
