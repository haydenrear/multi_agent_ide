import { useSyncExternalStore } from "react";

export type RawGraphEvent = {
  eventType?: string;
  nodeId?: string;
  timestamp?: string;
  [key: string]: unknown;
};

export type AgUiEventEnvelope = {
  type?: string | { name?: string };
  timestamp?: number;
  rawEvent?: RawGraphEvent;
  payload?: unknown;
  mapping?: Record<string, unknown>;
};

export type GraphEventRecord = {
  id: string;
  type: string;
  nodeId?: string;
  timestamp?: string;
  sortTime?: number;
  payload?: unknown;
  rawEvent?: RawGraphEvent;
};

export type WorktreeMeta = {
  id: string;
  path?: string;
  worktreeType?: string;
  submoduleName?: string;
};

export type GraphNode = {
  id: string;
  title?: string;
  nodeType?: string;
  status?: string;
  parentId?: string;
  worktrees: WorktreeMeta[];
  lastUpdatedAt?: string;
  events: GraphEventRecord[];
  latestMessage?: string;
};

export type GraphState = {
  nodes: Record<string, GraphNode>;
  selectedNodeId?: string;
  unknownEvents: GraphEventRecord[];
};

const subscribers = new Set<() => void>();
let state: GraphState = {
  nodes: {},
  selectedNodeId: undefined,
  unknownEvents: [],
};

const notify = () => {
  subscribers.forEach((callback) => callback());
};

const updateState = (next: GraphState) => {
  state = next;
  notify();
};

const ensureNode = (nodeId: string): GraphNode => {
  if (!state.nodes[nodeId]) {
    state.nodes[nodeId] = {
      id: nodeId,
      worktrees: [],
      events: [],
    };
  }
  return state.nodes[nodeId];
};

const extractEventType = (event: AgUiEventEnvelope): string => {
  if (event.rawEvent?.eventType) {
    return event.rawEvent.eventType;
  }
  if (typeof event.type === "string") {
    return event.type;
  }
  if (event.type && typeof event.type === "object") {
    return event.type.name ?? "UNKNOWN";
  }
  return "UNKNOWN";
};

const extractNodeId = (raw?: RawGraphEvent): string | undefined => {
  if (!raw) {
    return undefined;
  }
  return (
    (raw.nodeId as string | undefined) ??
    (raw as { associatedNodeId?: string }).associatedNodeId ??
    (raw as { branchedNodeId?: string }).branchedNodeId ??
    (raw as { originalNodeId?: string }).originalNodeId ??
    (raw as { orchestratorNodeId?: string }).orchestratorNodeId ??
    (raw as { reviewNodeId?: string }).reviewNodeId
  );
};

const pushEvent = (record: GraphEventRecord) => {
  if (!record.nodeId) {
    if (!state.unknownEvents.some((event) => event.id === record.id)) {
      state.unknownEvents = [record, ...state.unknownEvents]
        .sort((a, b) => (b.sortTime ?? 0) - (a.sortTime ?? 0))
        .slice(0, 50);
    }
    return;
  }
  const node = ensureNode(record.nodeId);
  if (node.events.some((event) => event.id === record.id)) {
    return;
  }
  node.events = [record, ...node.events]
    .sort((a, b) => (b.sortTime ?? 0) - (a.sortTime ?? 0))
    .slice(0, 100);
};

export const graphActions = {
  applyEvent(event: AgUiEventEnvelope) {
    const eventType = extractEventType(event);
    const nodeId = extractNodeId(event.rawEvent);
    const parsedTime = event.rawEvent?.timestamp
      ? Date.parse(event.rawEvent.timestamp)
      : NaN;
    const sortTime = Number.isNaN(parsedTime)
      ? typeof event.timestamp === "number"
        ? event.timestamp
        : Date.now()
      : parsedTime;
    const record: GraphEventRecord = {
      id: `${eventType}-${nodeId ?? "global"}-${sortTime}`,
      type: eventType,
      nodeId,
      timestamp: event.rawEvent?.timestamp,
      sortTime,
      payload: event.payload,
      rawEvent: event.rawEvent,
    };

    if (eventType === "NODE_ADDED" && event.rawEvent) {
      const node = ensureNode(nodeId ?? event.rawEvent.nodeId ?? "unknown");
      node.title =
        (event.rawEvent as { nodeTitle?: string }).nodeTitle ?? node.title;
      node.nodeType =
        (event.rawEvent as { nodeType?: string }).nodeType ?? node.nodeType;
      node.parentId =
        (event.rawEvent as { parentNodeId?: string }).parentNodeId ??
        node.parentId;
    }

    if (eventType === "NODE_STATUS_CHANGED" && event.rawEvent) {
      const node = ensureNode(nodeId ?? event.rawEvent.nodeId ?? "unknown");
      node.status =
        (event.rawEvent as { newStatus?: string }).newStatus ?? node.status;
      node.lastUpdatedAt = event.rawEvent.timestamp ?? node.lastUpdatedAt;
    }

    if (eventType === "WORKTREE_CREATED" && event.rawEvent) {
      const nodeRef = (event.rawEvent as { associatedNodeId?: string })
        .associatedNodeId;
      if (nodeRef) {
        const node = ensureNode(nodeRef);
        node.worktrees = [
          {
            id:
              (event.rawEvent as { worktreeId?: string }).worktreeId ??
              "worktree",
            path: (event.rawEvent as { worktreePath?: string }).worktreePath,
            worktreeType: (event.rawEvent as { worktreeType?: string })
              .worktreeType,
            submoduleName: (event.rawEvent as { submoduleName?: string })
              .submoduleName,
          },
          ...node.worktrees,
        ];
      }
    }

    if (eventType === "ADD_MESSAGE_EVENT" && event.rawEvent) {
      const node = ensureNode(nodeId ?? event.rawEvent.nodeId ?? "unknown");
      node.latestMessage =
        (event.rawEvent as { toAddMessage?: string }).toAddMessage ??
        node.latestMessage;
    }

    if (eventType === "NODE_STREAM_DELTA" && event.rawEvent) {
      const node = ensureNode(nodeId ?? event.rawEvent.nodeId ?? "unknown");
      node.latestMessage =
        (event.rawEvent as { deltaContent?: string }).deltaContent ??
        node.latestMessage;
    }

    pushEvent(record);
    updateState({ ...state, nodes: { ...state.nodes } });
  },
  selectNode(nodeId?: string) {
    updateState({ ...state, selectedNodeId: nodeId });
  },
};

export const useGraphStore = <T>(selector: (s: GraphState) => T): T => {
  return useSyncExternalStore(
    (callback) => {
      subscribers.add(callback);
      return () => subscribers.delete(callback);
    },
    () => selector(state),
    () => selector(state),
  );
};

export const graphSelectors = {
  nodesArray: (s: GraphState) => Object.values(s.nodes),
  selectedNode: (s: GraphState) =>
    s.selectedNodeId ? s.nodes[s.selectedNodeId] : undefined,
};
