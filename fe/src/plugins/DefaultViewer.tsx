import type { ViewerContext } from "./types";

export const DefaultViewer = ({ node, events }: ViewerContext) => {
  return (
    <div>
      <h3>Overview</h3>
      <p className="muted">Node {node.id} ({node.nodeType ?? "unknown"})</p>
      <p>{node.latestMessage ?? "No messages yet."}</p>
      <p className="muted">Recent events: {events.length}</p>
    </div>
  );
};
