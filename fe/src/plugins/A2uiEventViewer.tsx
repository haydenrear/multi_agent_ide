import type { GraphEventRecord, GraphNode } from "../state/graphStore";
import React from "react";
import { A2uiSurfaceRenderer } from "../components/A2uiSurfaceRenderer";
import { extractA2uiMessages } from "../lib/a2uiMessageBuilder";
import { renderA2ui, resolveA2uiPayload } from "../lib/a2uiRegistry";
import {
  requestBranch,
  requestPause,
  requestPrune,
  requestResume,
  requestReview,
  requestStop,
} from "../lib/agentControls";

type A2uiEventViewerProps = {
  event: GraphEventRecord;
  node?: GraphNode;
  onFeedback?: (event: GraphEventRecord, message: string) => void;
  onRevert?: (event: GraphEventRecord) => void;
};

export const A2uiEventViewer = ({
  event,
  node,
  onFeedback,
  onRevert,
}: A2uiEventViewerProps) => {
  const messages = extractA2uiMessages(event.payload);
  if (messages) {
    return (
      <A2uiSurfaceRenderer
        messages={messages}
        event={event}
        node={node}
        onAction={(action) => {
          if (action.name.startsWith("control.")) {
            const nodeId = action.context.nodeId ?? event.nodeId;
            if (!nodeId) {
              return;
            }
            switch (action.name) {
              case "control.pause":
                requestPause(nodeId);
                return;
              case "control.resume":
                requestResume(nodeId);
                return;
              case "control.stop":
                requestStop(nodeId);
                return;
              case "control.review":
                requestReview(nodeId);
                return;
              case "control.prune":
                requestPrune(nodeId);
                return;
              case "control.branch":
                requestBranch(nodeId);
                return;
              default:
                return;
            }
          }
          if (action.name === "ui.feedback" && onFeedback) {
            const message = window.prompt("Feedback for this UI event?");
            if (message) {
              onFeedback(event, message);
            }
          }
          if (action.name === "ui.revert" && onRevert) {
            onRevert(event);
          }
        }}
      />
    );
  }

  return renderA2ui({
    payload: resolveA2uiPayload(event),
    event,
    node,
    onFeedback,
    onRevert,
  });
};
