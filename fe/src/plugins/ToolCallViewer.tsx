import type { ViewerContext } from "./types";
import { A2uiEventViewer } from "./A2uiEventViewer";

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
            <A2uiEventViewer key={event.id} event={event} />
          ))
        )}
      </div>
    </div>
  );
};
