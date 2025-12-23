import type { ViewerContext } from "./types";

export const StreamViewer = ({ node, events }: ViewerContext) => {
  const streamEvents = events.filter((event) =>
    event.type === "NODE_STREAM_DELTA" || event.type.includes("TEXT_MESSAGE")
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
            <div className="event-item" key={event.id}>
              <strong>{event.type}</strong>
              <div className="muted">{event.timestamp ?? "timestamp pending"}</div>
              {event.rawEvent && "deltaContent" in event.rawEvent ? (
                <div>{String((event.rawEvent as { deltaContent?: string }).deltaContent ?? "")}</div>
              ) : null}
            </div>
          ))
        )}
      </div>
    </div>
  );
};
