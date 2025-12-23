import { viewerRegistry } from "../plugins/registry";
import type { GraphNode } from "../state/graphStore";
import { requestInterrupt, requestPause, requestReview } from "../lib/agentControls";

type NodeDetailsPanelProps = {
  node?: GraphNode;
};

export const NodeDetailsPanel = ({ node }: NodeDetailsPanelProps) => {
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
      <p className="muted">{node.nodeType ?? "Node"} • {node.id}</p>
      <div className="event-list">
        {node.events.slice(0, 6).map((event) => (
          <div className="event-item" key={event.id}>
            <strong>{event.type}</strong>
            <div className="muted">{event.timestamp ?? "timestamp pending"}</div>
          </div>
        ))}
      </div>
      <div className="button-row">
        <button onClick={() => requestPause(node.id)}>Pause</button>
        <button className="secondary" onClick={() => requestInterrupt(node.id)}>Interrupt</button>
        <button className="secondary" onClick={() => requestReview(node.id)}>Request Review</button>
      </div>
      <div className="viewer">
        <h3>{viewer.name}</h3>
        {viewer.render({ node, events: node.events })}
      </div>
    </div>
  );
};
