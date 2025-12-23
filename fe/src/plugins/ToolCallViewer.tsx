import type { ViewerContext } from "./types";

export const ToolCallViewer = ({ events }: ViewerContext) => {
  const toolEvents = events.filter((event) => event.type.includes("TOOL_CALL"));

  return (
    <div>
      <h3>Tool Calls</h3>
      <div className="event-list">
        {toolEvents.length === 0 ? (
          <div className="event-item">No tool call events recorded.</div>
        ) : (
          toolEvents.map((event) => (
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
