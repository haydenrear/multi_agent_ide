import type { ViewerContext } from "./types";

export const MergeReviewViewer = ({ node, events }: ViewerContext) => {
  const mergeEvents = events.filter((event) => event.type.includes("MERGE"));
  return (
    <div>
      <h3>Merge Review</h3>
      <p className="muted">Tracking merge activity for node {node.id}.</p>
      <div className="event-list">
        {mergeEvents.length === 0 ? (
          <div className="event-item">No merge events captured.</div>
        ) : (
          mergeEvents.map((event) => (
            <div className="event-item" key={event.id}>
              <strong>{event.type}</strong>
              <div className="muted">{event.timestamp ?? "timestamp pending"}</div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};
