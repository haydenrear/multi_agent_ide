import type { A2uiRenderer } from "../../lib/a2uiTypes";
import {
  requestBranch,
  requestPause,
  requestPrune,
  requestResume,
  requestReview,
  requestStop,
} from "../../lib/agentControls";

const resolveNodeId = (payload: { props?: Record<string, unknown> }) => {
  const props = payload.props as Record<string, unknown> | undefined;
  return typeof props?.nodeId === "string" ? props.nodeId : undefined;
};

const ControlActionsRenderer: A2uiRenderer = ({ payload, event, onFeedback, onRevert }) => {
  const nodeId = resolveNodeId(payload) ?? event?.nodeId;
  if (!nodeId) {
    return <div className="muted">No node selected for controls.</div>;
  }

  return (
    <div className="button-row">
      <button onClick={() => requestPause(nodeId)}>Pause</button>
      <button className="secondary" onClick={() => requestResume(nodeId)}>Resume</button>
      <button className="secondary" onClick={() => requestStop(nodeId)}>Stop</button>
      <button className="secondary" onClick={() => requestReview(nodeId)}>Request Review</button>
      <button className="secondary" onClick={() => requestPrune(nodeId)}>Prune</button>
      <button className="secondary" onClick={() => requestBranch(nodeId)}>Branch</button>
      {onFeedback && event ? (
        <button
          className="secondary"
          onClick={() => {
            const message = window.prompt("Feedback for this UI event?");
            if (message) {
              onFeedback(event, message);
            }
          }}
        >
          Feedback
        </button>
      ) : null}
      {onRevert && event ? (
        <button className="secondary" onClick={() => onRevert(event)}>
          Revert
        </button>
      ) : null}
    </div>
  );
};

export const registerControlRenderers = (
  register: (name: string, renderer: A2uiRenderer) => void,
) => {
  register("control-actions", ControlActionsRenderer);
};
