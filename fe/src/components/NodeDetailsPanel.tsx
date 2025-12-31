import React from "react";
import type { GraphProps } from "@/state/graphStore";
import {
  graphSelectors,
  useGraphNode,
  useGraphNodeEvents,
  useGraphStore,
} from "@/state/graphStore";
import { submitGuiFeedback, requestUiRevert } from "@/lib/guiActions";
import { renderA2ui } from "@/lib/a2uiRegistry";
import { A2uiEventViewer } from "@/plugins/A2uiEventViewer";
import { isDeltaEvent } from "@/lib/a2uiEventPolicy";

type NodeDetailsPanelProps = GraphProps;

export const NodeDetailsPanel = ({ nodeId }: NodeDetailsPanelProps) => {
  const node = useGraphNode(nodeId);
  const uiSnapshot = useGraphStore(graphSelectors.uiSnapshot);
  if (!node) {
    return null;
  }

  const nodeEvents = useGraphNodeEvents(node.id);
  const orderedEvents = [...nodeEvents].sort(
    (a, b) => (a.sortTime ?? 0) - (b.sortTime ?? 0),
  );
  const collapsedEvents = orderedEvents.filter((event) => !isDeltaEvent(event));
  const visibleEvents =
    collapsedEvents.length > 50
      ? collapsedEvents.slice(collapsedEvents.length - 50)
      : collapsedEvents;

  return (
    <div className="panel" data-node-id={node.id}>
      <h2>Node Details</h2>
      <p className="muted">
        {node.nodeType ?? "Node"} • {node.id}
      </p>
      <div className="event-list">
        {visibleEvents.map((event) => (
          <A2uiEventViewer
            key={event.id}
            event={event}
            node={node}
            onFeedback={(evt, message) => {
              submitGuiFeedback({
                eventId: evt.id,
                nodeId: node.id,
                message,
                snapshot: uiSnapshot,
              });
            }}
            onRevert={(evt) => {
              requestUiRevert({
                eventId: evt.id,
                nodeId: node.id,
              });
            }}
          />
        ))}
      </div>
      {renderA2ui({
        payload: {
          renderer: "control-actions",
          sessionId: node.id,
          props: { nodeId: node.id },
        },
      })}
    </div>
  );
};
