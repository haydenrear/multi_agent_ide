import React from "react";
import type { GraphEventRecord, GraphNode } from "@/state/graphStore";
import { GraphNodeCard } from "./GraphNodeCard";
import { A2uiEventViewer } from "@/plugins/A2uiEventViewer";

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
  const filteredNodes = nodes.filter((node) => {
    if (filters.nodeId && !node.id.includes(filters.nodeId)) {
      return false;
    }
    if (filters.worktree) {
      const match = node.worktrees.some(
        (wt) =>
          wt.id.includes(filters.worktree ?? "") ||
          (wt.path ?? "").includes(filters.worktree ?? ""),
      );
      if (!match) {
        return false;
      }
    }
    return true;
  });
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
        {filteredNodes.length === 0 ? (
          <div className="event-item">Waiting for events...</div>
        ) : (
          filteredNodes.map((node) => (
            <GraphNodeCard
              key={node.id}
              nodeId={node.id}
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
              <A2uiEventViewer key={event.id} event={event} />
            ))}
          </div>
        </div>
      ) : null}
    </div>
  );
};
