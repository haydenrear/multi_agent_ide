package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.*;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Human review agent that handles human review nodes.
 * Waits for human approval/rejection decisions.
 */
@Component
public class HumanReviewGraphAgent extends BaseGraphAgent {

    public HumanReviewGraphAgent(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    public String agentType() {
        return "human_review";
    }

    @Override
    public boolean supports(GraphNode node) {
        return node instanceof HumanReviewNode;
    }

    @Override
    public GraphNode execute(GraphNode node, GraphAgent.ExecutionContext context) throws Exception {
        HumanReviewNode reviewNode = (HumanReviewNode) node;

        // Emit review requested event
        emitReviewRequestedEvent(reviewNode.nodeId(), "human", reviewNode.contentToReview(), context);

        emitStatusChangeEvent(reviewNode.nodeId(), GraphNode.NodeStatus.READY,
                GraphNode.NodeStatus.WAITING_INPUT, "Awaiting human review", context);

        // In a real implementation, this would wait for user input
        // For now, return node in waiting state
        return new HumanReviewNode(
                reviewNode.nodeId(),
                reviewNode.title(),
                reviewNode.goal(),
                GraphNode.NodeStatus.WAITING_INPUT,
                reviewNode.parentNodeId(),
                reviewNode.childNodeIds(),
                reviewNode.metadata(),
                reviewNode.createdAt(),
                Instant.now(),
                reviewNode.reviewedNodeId(),
                reviewNode.reviewContent(),
                false,
                null,
                null,
                reviewNode.specFileId()
        );
    }

    /**
     * Process review decision (called externally after human decision).
     */
    public HumanReviewNode processReviewDecision(HumanReviewNode reviewNode, boolean approved, String feedback) {
        GraphNode.NodeStatus newStatus = approved ? GraphNode.NodeStatus.COMPLETED : GraphNode.NodeStatus.FAILED;

        return new HumanReviewNode(
                reviewNode.nodeId(),
                reviewNode.title(),
                reviewNode.goal(),
                newStatus,
                reviewNode.parentNodeId(),
                reviewNode.childNodeIds(),
                reviewNode.metadata(),
                reviewNode.createdAt(),
                Instant.now(),
                reviewNode.reviewedNodeId(),
                reviewNode.reviewContent(),
                approved,
                feedback,
                Instant.now(),
                reviewNode.specFileId()
        );
    }

    /**
     * Helper to emit review requested event.
     */
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
