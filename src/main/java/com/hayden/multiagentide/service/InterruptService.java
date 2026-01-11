package com.hayden.multiagentide.service;

import com.embabel.agent.api.common.OperationContext;
import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.utilitymodule.acp.events.Events;
import com.hayden.multiagentidelib.model.nodes.GraphNode;
import com.hayden.multiagentidelib.model.nodes.InterruptContext;
import com.hayden.multiagentidelib.model.nodes.InterruptNode;
import com.hayden.multiagentidelib.model.nodes.Interruptible;
import com.hayden.multiagentidelib.model.nodes.ReviewNode;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterruptService {

    private static final String REVIEW_CRITERIA =
            "Review for correctness and completeness. Reply with approved if correct, otherwise explain issues.";

    private final PermissionGate permissionGate;

    public <T> Optional<T> handleReviewInterrupt(
            OperationContext context,
            AgentModels.InterruptDescriptor request,
            GraphNode originNode,
            Supplier<String> promptSupplier,
            Class<T> routingClass,
            String reviewPromptTemplate
    ) {
        if (request == null || request.type() == null) {
            return Optional.empty();
        }
        return switch (request.type()) {
            case HUMAN_REVIEW, AGENT_REVIEW -> {
                String feedback = resolveInterruptFeedback(context, request, originNode, reviewPromptTemplate);
                String basePrompt = promptSupplier != null ? promptSupplier.get() : null;
                if (basePrompt == null || basePrompt.isBlank()) {
                    yield Optional.empty();
                }
                String prompt = appendInterruptFeedback(basePrompt, feedback);
                T routing = context.ai()
                        .withDefaultLlm()
                        .createObject(prompt, routingClass);
                yield Optional.ofNullable(routing);
            }
            default -> Optional.empty();
        };
    }

    private String resolveInterruptFeedback(
            OperationContext context,
            AgentModels.InterruptDescriptor request,
            GraphNode originNode,
            String reviewPromptTemplate
    ) {
        InterruptContext interruptContext = resolveInterruptContext(originNode);
        String reviewContent = firstNonBlank(
                request != null ? request.reason() : null,
                interruptContext != null ? interruptContext.resultPayload() : null
        );
        String interruptId = firstNonBlank(
                interruptContext != null ? interruptContext.interruptNodeId() : null,
                originNode != null ? originNode.nodeId() : null
        );
        if (interruptId.isBlank()) {
            return reviewContent;
        }
        permissionGate.publishInterrupt(
                interruptId,
                originNode != null ? originNode.nodeId() : interruptId,
                request.type(),
                reviewContent
        );
        if (request.type() == Events.InterruptType.HUMAN_REVIEW) {
            PermissionGate.InterruptResolution resolution =
                    permissionGate.awaitInterruptBlocking(interruptId);
            String feedback = resolution != null ? resolution.getResolutionNotes() : null;
            return firstNonBlank(feedback, reviewContent);
        }
        AgentModels.ReviewAgentResult reviewResult =
                runInterruptAgentReview(context, reviewPromptTemplate, reviewContent);
        String feedback = reviewResult != null ? reviewResult.output() : "";
        permissionGate.resolveInterrupt(interruptId, "agent-review", feedback, reviewResult);
        return feedback;
    }

    private AgentModels.ReviewAgentResult runInterruptAgentReview(
            OperationContext context,
            String reviewPromptTemplate,
            String reviewContent
    ) {
        if (reviewPromptTemplate == null || reviewPromptTemplate.isBlank()) {
            return null;
        }
        String prompt = AgentInterfaces.renderTemplate(
                reviewPromptTemplate,
                java.util.Map.of(
                        "content", Objects.toString(reviewContent, ""),
                        "criteria", REVIEW_CRITERIA,
                        "returnRoute", "interrupt"
                )
        );
        AgentModels.ReviewRouting routing = context.ai()
                .withDefaultLlm()
                .createObject(prompt, AgentModels.ReviewRouting.class);
        return routing != null ? routing.reviewResult() : null;
    }

    private InterruptContext resolveInterruptContext(GraphNode node) {
        if (node == null) {
            return null;
        }
        if (node instanceof Interruptible interruptible) {
            return interruptible.interruptibleContext();
        }
        if (node instanceof ReviewNode reviewNode) {
            return reviewNode.interruptContext();
        }
        if (node instanceof InterruptNode interruptNode) {
            return interruptNode.interruptContext();
        }
        return null;
    }

    private static String appendInterruptFeedback(String prompt, String feedback) {
        if (feedback == null || feedback.isBlank()) {
            return prompt;
        }
        return prompt + "\n\nReview feedback:\n" + feedback;
    }

    private static String firstNonBlank(String... values) {
        if (values == null) {
            return "";
        }
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return "";
    }
}
