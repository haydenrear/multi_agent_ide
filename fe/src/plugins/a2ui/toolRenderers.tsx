import type { A2uiRenderer } from "../../lib/a2uiTypes";

const renderJson = (value: unknown) => {
  if (!value) {
    return null;
  }
  return <pre className="code-block">{JSON.stringify(value, null, 2)}</pre>;
};

const FileWriteRenderer: A2uiRenderer = ({ payload }) => {
  const props = payload.props as Record<string, unknown> | undefined;
  const path = typeof props?.path === "string" ? props.path : undefined;
  const content = typeof props?.content === "string" ? props.content : undefined;
  return (
    <div>
      <div className="muted">File write</div>
      {path ? <div><strong>Path:</strong> {path}</div> : null}
      {content ? <pre className="code-block">{content}</pre> : renderJson(props)}
    </div>
  );
};

const FileReadRenderer: A2uiRenderer = ({ payload }) => {
  const props = payload.props as Record<string, unknown> | undefined;
  const path = typeof props?.path === "string" ? props.path : undefined;
  const line = typeof props?.line === "number" ? props.line : undefined;
  const limit = typeof props?.limit === "number" ? props.limit : undefined;
  return (
    <div>
      <div className="muted">File read</div>
      {path ? <div><strong>Path:</strong> {path}</div> : null}
      {line != null || limit != null ? (
        <div className="muted">
          {line != null ? `Line: ${line}` : null}
          {line != null && limit != null ? " · " : null}
          {limit != null ? `Limit: ${limit}` : null}
        </div>
      ) : null}
      {renderJson(props)}
    </div>
  );
};

const GenericToolRenderer: A2uiRenderer = ({ payload }) => {
  return (
    <div>
      <div className="muted">Tool call</div>
      {renderJson(payload.props ?? payload)}
    </div>
  );
};

export const registerToolRenderers = (
  register: (name: string, renderer: A2uiRenderer) => void,
) => {
  register("tool-write", FileWriteRenderer);
  register("tool-read", FileReadRenderer);
  register("tool-generic", GenericToolRenderer);
};
