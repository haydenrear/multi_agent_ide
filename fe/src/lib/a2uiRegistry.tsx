import type { GraphEventRecord, GraphNode } from "../state/graphStore";
import type { A2uiPayload, A2uiRenderContext, A2uiRenderer } from "./a2uiTypes";
import { registerToolRenderers } from "../plugins/a2ui/toolRenderers";
import { registerControlRenderers } from "../plugins/a2ui/controlRenderers";

const registry = new Map<string, A2uiRenderer>();
let defaultsRegistered = false;

export const registerA2uiRenderer = (name: string, renderer: A2uiRenderer) => {
  registry.set(name, renderer);
};

const registerCoreRenderers = () => {
  registerA2uiRenderer("event-default", ({ payload, event }) => (
    <div className="event-item">
      <strong>{payload.title ?? event?.type ?? payload.renderer}</strong>
      {event?.timestamp ? <div className="muted">{event.timestamp}</div> : null}
      {payload.props ? (
        <pre className="code-block">{JSON.stringify(payload.props, null, 2)}</pre>
      ) : null}
      {!payload.props && event?.rawEvent ? (
        <pre className="code-block">{JSON.stringify(event.rawEvent, null, 2)}</pre>
      ) : null}
    </div>
  ));

  registerA2uiRenderer("node-summary", ({ payload }) => {
    const node = payload.props?.node as GraphNode | undefined;
    return (
      <div>
        <div className="badge">{node?.status ?? "UNKNOWN"}</div>
        <h3>{node?.title ?? node?.id ?? payload.title ?? "Node"}</h3>
        <p className="muted">{node?.nodeType ?? "Node"}</p>
        {node?.worktrees?.length ? (
          <p className="muted">Worktrees: {node.worktrees.length}</p>
        ) : null}
      </div>
    );
  });

  registerA2uiRenderer("gui-payload", ({ payload }) => (
    <div>
      {payload.title ? <h4>{payload.title}</h4> : null}
      <pre className="code-block">{JSON.stringify(payload.props ?? payload.fallback, null, 2)}</pre>
    </div>
  ));
};

export const ensureDefaultA2uiRenderers = () => {
  if (defaultsRegistered) {
    return;
  }
  defaultsRegistered = true;
  registerCoreRenderers();
  registerToolRenderers(registerA2uiRenderer);
  registerControlRenderers(registerA2uiRenderer);
};

const resolveRendererName = (event: GraphEventRecord): string => {
  if (event.type.includes("TOOL_CALL")) {
    return "tool-generic";
  }
  if (event.type === "NODE_STREAM_DELTA" || event.type.includes("TEXT_MESSAGE")) {
    return "stream";
  }
  return "event-default";
};

const isPayloadWithRenderer = (payload: unknown): payload is A2uiPayload => {
  if (!payload || typeof payload !== "object") {
    return false;
  }
  return typeof (payload as { renderer?: unknown }).renderer === "string";
};

export const resolveA2uiPayload = (event: GraphEventRecord): A2uiPayload => {
  if (isPayloadWithRenderer(event.payload)) {
    return event.payload;
  }
  const renderer = resolveRendererName(event);
  return {
    renderer,
    sessionId: event.nodeId,
    title: event.type,
    props: {
      event,
    },
  };
};

export const renderA2ui = (context: A2uiRenderContext) => {
  ensureDefaultA2uiRenderers();
  const renderer = registry.get(context.payload.renderer) ?? registry.get("event-default");
  if (!renderer) {
    return (
      <pre className="code-block">{JSON.stringify(context.payload, null, 2)}</pre>
    );
  }
  return renderer(context);
};
