package com.hayden.multiagentide.cli;

import com.hayden.acp_cdc_ai.acp.events.Events;

import java.util.List;
import java.util.Map;

public class CliEventFormatter {

    private static final int MAX_FIELD_LENGTH = 160;

    private final ArtifactKeyFormatter artifactKeyFormatter;

    public CliEventFormatter(ArtifactKeyFormatter artifactKeyFormatter) {
        this.artifactKeyFormatter = artifactKeyFormatter;
    }

    public String format(Events.GraphEvent event) {
        if (event == null) {
            return "[EVENT] null";
        }
        return switch (event) {
            case Events.ActionStartedEvent e -> formatActionStarted(e);
            case Events.ActionCompletedEvent e -> formatActionCompleted(e);
            case Events.ToolCallEvent e -> formatToolCall(e);
            case Events.NodeAddedEvent e -> formatNodeAdded(e);
            case Events.NodeStatusChangedEvent e -> formatNodeStatusChanged(e);
            case Events.NodeErrorEvent e -> formatNodeError(e);
            case Events.NodeBranchedEvent e -> formatNodeBranched(e);
            case Events.NodePrunedEvent e -> formatNodePruned(e);
            case Events.NodeReviewRequestedEvent e -> formatNodeReviewRequested(e);
            case Events.InterruptStatusEvent e -> formatInterruptStatus(e);
            case Events.PauseEvent e -> format("INTERRUPT", e, "message=" + summarize(e.toAddMessage()));
            case Events.ResumeEvent e -> format("INTERRUPT", e, "message=" + summarize(e.message()));
            case Events.ResolveInterruptEvent e -> format("INTERRUPT", e, "message=" + summarize(e.toAddMessage()));
            case Events.StopAgentEvent e -> format("ACTION", e, "node=" + e.nodeId());
            case Events.AddMessageEvent e -> format("MESSAGE", e, "message=" + summarize(e.toAddMessage()));
            case Events.WorktreeCreatedEvent e -> formatWorktreeCreated(e);
            case Events.WorktreeBranchedEvent e -> formatWorktreeBranched(e);
            case Events.WorktreeMergedEvent e -> formatWorktreeMerged(e);
            case Events.WorktreeDiscardedEvent e -> formatWorktreeDiscarded(e);
            case Events.NodeUpdatedEvent e -> format("NODE", e, "updates=" + countOf(e.updates()));
            case Events.NodeDeletedEvent e -> format("NODE", e, "reason=" + summarize(e.reason()));
            case Events.ChatSessionCreatedEvent e -> format("CHAT", e, "nodeId=" + summarize(e.nodeId()));
            case Events.ChatSessionClosedEvent e -> format("CHAT", e, "sessionId=" + summarize(e.sessionId()));
            case Events.NodeStreamDeltaEvent e -> format("STREAM", e, "tokens=" + e.tokenCount() + " final=" + e.isFinal());
            case Events.NodeThoughtDeltaEvent e -> format("THOUGHT", e, "tokens=" + e.tokenCount() + " final=" + e.isFinal());
            case Events.GuiRenderEvent e -> format("UI", e, "sessionId=" + e.sessionId());
            case Events.UiDiffAppliedEvent e -> format("UI", e, "revision=" + e.revision() + " summary=" + summarize(e.summary()));
            case Events.UiDiffRejectedEvent e -> format("UI", e, "error=" + summarize(e.errorCode()) + " message=" + summarize(e.message()));
            case Events.UiDiffRevertedEvent e -> format("UI", e, "revision=" + e.revision() + " source=" + summarize(e.sourceEventId()));
            case Events.UiFeedbackEvent e -> format("UI", e, "message=" + summarize(e.message()));
            case Events.NodeBranchRequestedEvent e -> format("NODE", e, "message=" + summarize(e.message()));
            case Events.PlanUpdateEvent e -> format("PLAN", e, "entries=" + countOf(e.entries()));
            case Events.UserMessageChunkEvent e -> format("MESSAGE", e, "content=" + summarize(e.content()));
            case Events.CurrentModeUpdateEvent e -> format("MODE", e, "mode=" + summarize(e.currentModeId()));
            case Events.AvailableCommandsUpdateEvent e -> format("MODE", e, "commands=" + countOf(e.commands()));
            case Events.PermissionRequestedEvent e -> format("PERMISSION", e, "requestId=" + e.requestId() + " toolCallId=" + e.toolCallId());
            case Events.PermissionResolvedEvent e -> format("PERMISSION", e, "requestId=" + e.requestId() + " outcome=" + summarize(e.outcome()));
            case Events.GoalCompletedEvent e -> format("GOAL", e, "workflowId=" + summarize(e.workflowId()));
            case Events.ArtifactEvent e -> format("ARTIFACT", e, "type=" + summarize(e.artifactType()) + " key=" + e.artifactKey());
        };
    }

    private String formatActionStarted(Events.ActionStartedEvent event) {
        String hierarchy = artifactKeyFormatter.formatHierarchy(event.nodeId());
        String details = "agent=" + event.agentName()
                + " action=" + event.actionName()
                + " " + hierarchy;
        return format("ACTION", event, details);
    }

    private String formatActionCompleted(Events.ActionCompletedEvent event) {
        String hierarchy = artifactKeyFormatter.formatHierarchy(event.nodeId());
        String details = "agent=" + event.agentName()
                + " action=" + event.actionName()
                + " outcome=" + summarize(event.outcomeType())
                + " " + hierarchy;
        return format("ACTION", event, details);
    }

    private String formatToolCall(Events.ToolCallEvent event) {
        String details = "tool=" + summarize(event.title())
                + " kind=" + summarize(event.kind())
                + " status=" + summarize(event.status())
                + " phase=" + summarize(event.phase())
                + " input=" + summarize(event.rawInput())
                + " output=" + summarize(event.rawOutput());
        return format("TOOL", event, details);
    }

    private String formatNodeAdded(Events.NodeAddedEvent event) {
        String details = "title=" + summarize(event.nodeTitle())
                + " type=" + event.nodeType()
                + " parent=" + summarize(event.parentNodeId());
        return format("NODE", event, details);
    }

    private String formatNodeStatusChanged(Events.NodeStatusChangedEvent event) {
        String details = "from=" + event.oldStatus() + " to=" + event.newStatus()
                + " reason=" + summarize(event.reason());
        return format("NODE", event, details);
    }

    private String formatNodeError(Events.NodeErrorEvent event) {
        String details = "title=" + summarize(event.nodeTitle())
                + " type=" + event.nodeType()
                + " message=" + summarize(event.message());
        return format("NODE", event, details);
    }

    private String formatNodeBranched(Events.NodeBranchedEvent event) {
        String details = "original=" + summarize(event.originalNodeId())
                + " branched=" + summarize(event.branchedNodeId())
                + " goal=" + summarize(event.newGoal());
        return format("NODE", event, details);
    }

    private String formatNodePruned(Events.NodePrunedEvent event) {
        String details = "reason=" + summarize(event.reason())
                + " worktrees=" + countOf(event.pruneWorktreeIds());
        return format("NODE", event, details);
    }

    private String formatNodeReviewRequested(Events.NodeReviewRequestedEvent event) {
        String details = "reviewNode=" + summarize(event.reviewNodeId())
                + " type=" + event.reviewType();
        return format("REVIEW", event, details);
    }

    private String formatInterruptStatus(Events.InterruptStatusEvent event) {
        String details = "type=" + summarize(event.interruptType())
                + " status=" + summarize(event.interruptStatus())
                + " origin=" + summarize(event.originNodeId());
        return format("INTERRUPT", event, details);
    }

    private String formatWorktreeCreated(Events.WorktreeCreatedEvent event) {
        String details = "worktreeId=" + summarize(event.worktreeId())
                + " path=" + summarize(event.worktreePath())
                + " type=" + summarize(event.worktreeType())
                + " submodule=" + summarize(event.submoduleName());
        return format("WORKTREE", event, details);
    }

    private String formatWorktreeBranched(Events.WorktreeBranchedEvent event) {
        String details = "branch=" + summarize(event.branchName())
                + " original=" + summarize(event.originalWorktreeId())
                + " branched=" + summarize(event.branchedWorktreeId());
        return format("WORKTREE", event, details);
    }

    private String formatWorktreeMerged(Events.WorktreeMergedEvent event) {
        String details = "child=" + summarize(event.childWorktreeId())
                + " parent=" + summarize(event.parentWorktreeId());
        return format("WORKTREE", event, details);
    }

    private String formatWorktreeDiscarded(Events.WorktreeDiscardedEvent event) {
        String details = "worktreeId=" + summarize(event.worktreeId())
                + " type=" + summarize(event.worktreeType());
        return format("WORKTREE", event, details);
    }

    private String formatFallback(Events.GraphEvent event) {
        String details = "node=" + summarize(event.nodeId());
        return format("EVENT", event, details);
    }

    private String format(String category, Events.GraphEvent event, String details) {
        String prefix = "[" + category + "]";
        return prefix + " " + event.eventType() + " node=" + summarize(event.nodeId())
                + " " + details;
    }

    private String summarize(Object value) {
        if (value == null) {
            return "none";
        }
        String text = String.valueOf(value);
        if (text.length() <= MAX_FIELD_LENGTH) {
            return text;
        }
        return text.substring(0, MAX_FIELD_LENGTH) + "...";
    }

    private int countOf(List<?> items) {
        return items == null ? 0 : items.size();
    }

    private int countOf(Map<?, ?> items) {
        return items == null ? 0 : items.size();
    }
}
