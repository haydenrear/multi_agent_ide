package com.hayden.multiagentide.prompt.contributor;

import com.google.common.collect.Lists;
import com.hayden.acp_cdc_ai.acp.events.Events;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.multiagentidelib.prompt.PromptContributor;
import com.hayden.multiagentidelib.prompt.PromptContributorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Contributes review guidance when the current request is an interrupt
 * with AGENT_REVIEW type. Provides the LLM with instructions on how
 * to perform the review, including the interrupt reason and context.
 */
@Component
public class InterruptResolvedPromptContributorFactory implements PromptContributorFactory {

    @Override
    public List<PromptContributor> create(PromptContext context) {
        if (context == null || context.currentRequest() == null) {
            return List.of();
        }

        if (!(context.currentRequest() instanceof AgentModels.InterruptRequest interruptRequest)) {
            return List.of();
        }
        if (!context.model().containsKey("interruptFeedback")) {
            return List.of();
        }

        return Lists.newArrayList(new InterruptReviewPromptContributor(interruptRequest, context.model()));
    }

    public record InterruptReviewPromptContributor(AgentModels.InterruptRequest interruptRequest,
                                                   Map<String, Object> interruptFeedback) implements PromptContributor {

        @Override
        public String name() {
            return "interrupt-review-resolution";
        }

        @Override
        public boolean include(PromptContext promptContext) {
            return true;
        }

        @Override
        public String contribute(PromptContext context) {
            String reason = interruptRequest.reason() != null ? interruptRequest.reason() : "";
            String contextForDecision = interruptRequest.contextForDecision() != null
                    ? interruptRequest.contextForDecision() : "";

            return template()
                    .replace("{{interrupt_reason}}", reason)
                    .replace("{{context_for_decision}}", contextForDecision)
                    .replace("{{interrupt_feedback}}", String.valueOf(interruptFeedback.get("interruptFeedback")));
        }

        @Override
        public String template() {
            return """
                    ## Interrupt Review Resolution
                    
                    You are performing the routing after an interrupt resolution from a user. Your job is to route back to the appropriate agent
                    that requested the review.
                    
                    ### Review Context
                    - **Interrupt Reason**: {{interrupt_reason}}
                    - **Decision Context**: {{context_for_decision}}
                    - **Decision**: {{interrupt_feedback}}
                   
                    Now that we have resolution, please route to the appropriate agent accordingly.
                    """;
        }

        @Override
        public int priority() {
            return 50;
        }
    }
}
