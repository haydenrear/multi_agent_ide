import { viewerRegistry } from "@/plugins/registry";
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

type NodeDetailsPanelProps = GraphProps;

export const NodeDetailsPanel = ({ nodeId }: NodeDetailsPanelProps) => {
  const node = useGraphNode(nodeId);
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
  const nodeEvents = useGraphNodeEvents(node.id);
  const matchingViewers = viewerRegistry
    .all()
    .filter((plugin) => plugin.matches({ node, events: node.events }));
  const orderedEvents = [...nodeEvents].sort(
    (a, b) => (a.sortTime ?? 0) - (b.sortTime ?? 0),
  );
  const visibleEvents =
    orderedEvents.length > 50
      ? orderedEvents.slice(orderedEvents.length - 50)
      : orderedEvents;

  return (
    <div className="panel">
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
      {matchingViewers.length > 0 ? (
        <div className="viewer">
          {matchingViewers.map((plugin) => (
            <div key={plugin.id}>
              <h3>{plugin.name}</h3>
              {plugin.render({ node, events: node.events })}
            </div>
          ))}
        </div>
      ) : (
        <div className="viewer">
          <h3>{viewer.name}</h3>
          {viewer.render({ node, events: node.events })}
        </div>
      )}
    </div>
  );
};
