import type { ViewerContext } from "./types";
import { A2uiSurfaceRenderer } from "../components/A2uiSurfaceRenderer";
import {
  buildEventMessages,
  extractA2uiMessages,
} from "../lib/a2uiMessageBuilder";

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
        <A2uiSurfaceRenderer
          key={event.id}
          messages={
            extractA2uiMessages(event.payload) ??
            buildEventMessages(event, true)
          }
          event={event}
          node={node}
        />
      ))}
    </div>
  );
};
