import type { GraphEventRecord, GraphNode } from "../state/graphStore";

export type A2uiPayload = {
  renderer: string;
  sessionId?: string;
  title?: string;
  props?: Record<string, unknown>;
  fallback?: unknown;
  eventId?: string;
  sourceEventType?: string;
};

export type A2uiRendererProps = {
  payload: A2uiPayload;
  event?: GraphEventRecord;
  node?: GraphNode;
  onFeedback?: (event: GraphEventRecord, message: string) => void;
  onRevert?: (event: GraphEventRecord) => void;
};

export type A2uiRenderer = (props: A2uiRendererProps) => JSX.Element;

export type A2uiRenderContext = {
  payload: A2uiPayload;
  event?: GraphEventRecord;
  node?: GraphNode;
  onFeedback?: (event: GraphEventRecord, message: string) => void;
  onRevert?: (event: GraphEventRecord) => void;
};
