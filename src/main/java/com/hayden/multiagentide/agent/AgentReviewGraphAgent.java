package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.AgentReviewNode;
import com.hayden.multiagentide.model.Events;
import com.hayden.multiagentide.model.GraphAgent;
import com.hayden.multiagentide.model.GraphNode;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Agent review that evaluates content with LangChain4j Agentic.
 */
@Component
public class AgentReviewGraphAgent extends BaseGraphAgent {

    private final AgentInterfaces.ReviewAgent reviewAgent;

    public AgentReviewGraphAgent(EventBus eventBus,
                                 AgentInterfaces.ReviewAgent reviewAgent) {
        super(eventBus);
        this.reviewAgent = reviewAgent;
    }

    @Override
    public String agentType() {
        return "agent_review";
    }

    @Override
    public boolean supports(GraphNode node) {
        return node instanceof AgentReviewNode;
    }

    @Override
    public GraphNode execute(GraphNode node, GraphAgent.ExecutionContext context) throws Exception {
        AgentReviewNode reviewNode = (AgentReviewNode) node;

        emitStatusChangeEvent(reviewNode.nodeId(), GraphNode.NodeStatus.READY,
                GraphNode.NodeStatus.RUNNING, "Agent review in progress", context);

        String evaluation = reviewAgent.evaluateContent(
                reviewNode.contentToReview(),
                "Quality, completeness, and adherence to requirements"
        );

        boolean approved = evaluation.toLowerCase().contains("approved") ||
                evaluation.toLowerCase().contains("pass");
        String feedback = evaluation;

        emitReviewRequestedEvent(reviewNode.nodeId(), "agent", reviewNode.contentToReview(), context);

        emitStatusChangeEvent(reviewNode.nodeId(), GraphNode.NodeStatus.RUNNING,
                GraphNode.NodeStatus.COMPLETED, feedback, context);

        return new AgentReviewNode(
                reviewNode.nodeId(),
                reviewNode.title(),
                reviewNode.goal(),
                GraphNode.NodeStatus.COMPLETED,
                reviewNode.parentNodeId(),
                reviewNode.childNodeIds(),
                reviewNode.metadata(),
                reviewNode.createdAt(),
                Instant.now(),
                reviewNode.reviewedNodeId(),
                reviewNode.reviewContent(),
                approved,
                feedback,
                reviewNode.reviewerAgentType(),
                Instant.now(),
                reviewNode.specFileId()
        );
    }

    private void emitReviewRequestedEvent(String nodeId, String reviewType, String content,
                                          GraphAgent.ExecutionContext context) {
        Events.NodeReviewRequestedEvent event = new Events.NodeReviewRequestedEvent(
                java.util.UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                java.util.UUID.randomUUID().toString(),
                reviewType,
                content
        );
        eventBus.publish(event);
        context.emitEvent(event);
    }
}
