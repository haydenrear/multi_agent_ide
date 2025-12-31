import { viewerRegistry } from "../plugins/registry";
import { A2uiSurfaceRenderer } from "./A2uiSurfaceRenderer";
import type { GraphNode } from "../state/graphStore";
import { graphSelectors, useGraphStore } from "../state/graphStore";
import { submitGuiFeedback, requestUiRevert } from "../lib/guiActions";
import {
  requestPause,
  requestResume,
  requestReview,
  requestStop,
} from "../lib/agentControls";
import {
  buildEventMessages,
  extractA2uiMessages,
} from "../lib/a2uiMessageBuilder";

type NodeDetailsPanelProps = {
  node?: GraphNode;
};

export const NodeDetailsPanel = ({ node }: NodeDetailsPanelProps) => {
  const uiSnapshot = useGraphStore(graphSelectors.uiSnapshot);
  if (!node) {
    return (
      <div className="panel">
        <h2>Node Details</h2>
        <p className="muted">Select a node to inspect details and controls.</p>
      </div>
    );
  }

  const viewer = viewerRegistry.select({ node, events: node.events });

  return (
    <div className="panel">
      <h2>Node Details</h2>
      <p className="muted">
        {node.nodeType ?? "Node"} • {node.id}
      </p>
      <div className="event-list">
        {node.events.slice(0, 4).map((event) => {
          const messages =
            extractA2uiMessages(event.payload) ??
            buildEventMessages(event, true);
          return (
            <A2uiSurfaceRenderer
              key={event.id}
              messages={messages}
              event={event}
              node={node}
              onAction={(action) => {
                if (action.name === "ui.feedback") {
                  const message = window.prompt("Feedback for this UI event?");
                  if (message) {
                    submitGuiFeedback({
                      eventId: action.context.eventId ?? event.id,
                      nodeId: action.context.nodeId ?? node.id,
                      message,
                      snapshot: uiSnapshot,
                    });
                  }
                }
                if (action.name === "ui.revert") {
                  requestUiRevert({
                    eventId: action.context.eventId ?? event.id,
                    nodeId: action.context.nodeId ?? node.id,
                  });
                }
              }}
            />
          );
        })}
      </div>
      <div className="button-row">
        <button onClick={() => requestPause(node.id)}>Pause</button>
        <button className="secondary" onClick={() => requestResume(node.id)}>
          Resume
        </button>
        <button className="secondary" onClick={() => requestStop(node.id)}>
          Stop
        </button>
        <button className="secondary" onClick={() => requestReview(node.id)}>
          Request Review
        </button>
      </div>
      <div className="viewer">
        <h3>{viewer.name}</h3>
        {viewer.render({ node, events: node.events })}
      </div>
    </div>
  );
};
