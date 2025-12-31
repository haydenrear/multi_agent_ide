import type { ViewerContext } from "./types";
import { A2uiSurfaceRenderer } from "../components/A2uiSurfaceRenderer";
import { buildEventMessages, extractA2uiMessages } from "../lib/a2uiMessageBuilder";

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
            <A2uiSurfaceRenderer
              key={event.id}
              messages={extractA2uiMessages(event.payload) ?? buildEventMessages(event, true)}
              event={event}
            />
          ))
        )}
      </div>
    </div>
  );
};
