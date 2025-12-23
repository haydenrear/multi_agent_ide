import type { GraphEventRecord, GraphNode } from "../state/graphStore";
import { GraphNodeCard } from "./GraphNodeCard";

type GraphViewProps = {
  nodes: GraphNode[];
  selectedNodeId?: string;
  unknownEvents: GraphEventRecord[];
  onSelectNode: (nodeId: string) => void;
};

export const GraphView = ({ nodes, selectedNodeId, unknownEvents, onSelectNode }: GraphViewProps) => {
  return (
    <div className="panel">
      <h2>Graph Timeline</h2>
      <p className="muted">Live updates as agents move through the workflow.</p>
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
              <div className="event-item" key={event.id}>
                <strong>{event.type}</strong>
                <div className="muted">{event.timestamp ?? "timestamp pending"}</div>
              </div>
            ))}
          </div>
        </div>
      ) : null}
    </div>
  );
};
