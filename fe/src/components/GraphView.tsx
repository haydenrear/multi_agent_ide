import type { GraphEventRecord, GraphNode } from "../state/graphStore";
import { GraphNodeCard } from "./GraphNodeCard";
import { A2uiSurfaceRenderer } from "./A2uiSurfaceRenderer";
import {
  buildEventMessages,
  extractA2uiMessages,
} from "../lib/a2uiMessageBuilder";

type GraphViewProps = {
  nodes: GraphNode[];
  selectedNodeId?: string;
  unknownEvents: GraphEventRecord[];
  filters: { nodeId?: string; worktree?: string };
  onFilterChange: (next: { nodeId?: string; worktree?: string }) => void;
  onSelectNode: (nodeId: string) => void;
};

export const GraphView = ({
  nodes,
  selectedNodeId,
  unknownEvents,
  filters,
  onFilterChange,
  onSelectNode,
}: GraphViewProps) => {
  return (
    <div className="panel">
      <h2>Graph Timeline</h2>
      <p className="muted">Live updates as agents move through the workflow.</p>
      <div className="filter-row">
        <input
          placeholder="Filter by node id"
          value={filters.nodeId ?? ""}
          onChange={(event) => onFilterChange({ nodeId: event.target.value })}
        />
        <input
          placeholder="Filter by worktree"
          value={filters.worktree ?? ""}
          onChange={(event) => onFilterChange({ worktree: event.target.value })}
        />
      </div>
      <div className="node-grid">
        {nodes.length === 0 ? (
          <div className="event-item">Waiting for events...</div>
        ) : (
          nodes.map((node) => (
            <GraphNodeCard
              key={node.id}
              node={node}
              isSelected={selectedNodeId === node.id}
              onSelect={onSelectNode}
            />
          ))
        )}
      </div>
      {unknownEvents.length > 0 ? (
        <div className="viewer">
          <h3>Unknown events</h3>
          <div className="event-list">
            {unknownEvents.map((event) => (
              <A2uiSurfaceRenderer
                key={event.id}
                messages={
                  extractA2uiMessages(event.payload) ??
                  buildEventMessages(event, true)
                }
                event={event}
              />
            ))}
          </div>
        </div>
      ) : null}
    </div>
  );
};
