import type { ViewerContext } from "./types";
import { A2uiEventViewer } from "./A2uiEventViewer";

export const StreamViewer = ({ node, events }: ViewerContext) => {
  const streamEvents = events.filter(
    (event) =>
      event.type === "NODE_STREAM_DELTA" || event.type.includes("TEXT_MESSAGE"),
  );

  return (
    <div>
      <h3>Streaming Output</h3>
      <p className="muted">Latest output for node {node.id}.</p>
      <div className="event-list">
        {streamEvents.length === 0 ? (
          <div className="event-item">No streaming output captured.</div>
        ) : (
          streamEvents.map((event) => (
            <A2uiEventViewer key={event.id} event={event} node={node} />
          ))
        )}
      </div>
    </div>
  );
};
