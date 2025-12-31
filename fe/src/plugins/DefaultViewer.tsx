import type { ViewerContext } from "./types";
import { A2uiEventViewer } from "./A2uiEventViewer";

export const DefaultViewer = ({ node, events }: ViewerContext) => {
  return (
    <div>
      <h3>Overview</h3>
      <p className="muted">
        Node {node.id} ({node.nodeType ?? "unknown"})
      </p>
      <p>{node.latestMessage ?? "No messages yet."}</p>
      <p className="muted">Recent events: {events.length}</p>
      {events.slice(0, 2).map((event) => (
        <A2uiEventViewer key={event.id} event={event} node={node} />
      ))}
    </div>
  );
};
