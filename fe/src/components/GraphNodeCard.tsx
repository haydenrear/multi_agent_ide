import React from "react";
import type { GraphProps } from "../state/graphStore";
import { useGraphNode } from "../state/graphStore";
import { renderA2ui } from "../lib/a2uiRegistry";

type GraphNodeCardProps = GraphProps & {
  isSelected: boolean;
  onSelect: (nodeId: string) => void;
};

export const GraphNodeCard = ({
  nodeId,
  isSelected,
  onSelect,
}: GraphNodeCardProps) => {
  const node = useGraphNode(nodeId);
  if (!node) {
    return null;
  }
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
      {renderA2ui({
        payload: {
          renderer: "node-summary",
          sessionId: node.id,
          props: { node },
        },
      })}
    </div>
  );
};
