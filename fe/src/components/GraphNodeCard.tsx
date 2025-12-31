import type { GraphNode } from "../state/graphStore";
import { A2uiSurfaceRenderer } from "./A2uiSurfaceRenderer";
import { buildNodeSummaryMessages } from "../lib/a2uiMessageBuilder";

type GraphNodeCardProps = {
  node: GraphNode;
  isSelected: boolean;
  onSelect: (nodeId: string) => void;
};

export const GraphNodeCard = ({
  node,
  isSelected,
  onSelect,
}: GraphNodeCardProps) => {
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
      <A2uiSurfaceRenderer messages={buildNodeSummaryMessages(node)} />
    </div>
  );
};
