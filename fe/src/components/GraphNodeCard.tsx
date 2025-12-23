import type { GraphNode } from "../state/graphStore";

type GraphNodeCardProps = {
  node: GraphNode;
  isSelected: boolean;
  onSelect: (nodeId: string) => void;
};

export const GraphNodeCard = ({ node, isSelected, onSelect }: GraphNodeCardProps) => {
  return (
    <div
      className={`node-card${isSelected ? " active" : ""}`}
      onClick={() => onSelect(node.id)}
      role="button"
      tabIndex={0}
      onKeyDown={(event) => {
        if (event.key === "Enter") {
          onSelect(node.id);
        }
      }}
    >
      <div className="badge">{node.status ?? "UNKNOWN"}</div>
      <h3>{node.title ?? node.id}</h3>
      <p className="muted">{node.nodeType ?? "Node"}</p>
      {node.worktrees.length > 0 ? (
        <p className="muted">Worktrees: {node.worktrees.length}</p>
      ) : null}
    </div>
  );
};
