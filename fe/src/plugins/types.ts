import type { ReactElement } from "react";
import type { GraphEventRecord, GraphNode } from "../state/graphStore";

export type ViewerContext = {
  node: GraphNode;
  events: GraphEventRecord[];
};

export type ViewerPlugin = {
  id: string;
  name: string;
  priority: number;
  matches: (context: ViewerContext) => boolean;
  render: (context: ViewerContext) => ReactElement;
};
