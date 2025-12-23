import type { ViewerContext, ViewerPlugin } from "./types";
import { DefaultViewer } from "./DefaultViewer";
import { MergeReviewViewer } from "./MergeReviewViewer";
import { ToolCallViewer } from "./ToolCallViewer";
import { StreamViewer } from "./StreamViewer";

const plugins: ViewerPlugin[] = [
  {
    id: "merge-review",
    name: "Merge Review",
    priority: 30,
    matches: ({ node }) => node.nodeType === "MERGE" || node.nodeType === "REVIEW",
    render: (context) => MergeReviewViewer(context),
  },
  {
    id: "tool-call",
    name: "Tool Calls",
    priority: 20,
    matches: ({ events }) => events.some((event) => event.type.includes("TOOL_CALL")),
    render: (context) => ToolCallViewer(context),
  },
  {
    id: "stream",
    name: "Streaming",
    priority: 15,
    matches: ({ events }) => events.some((event) => event.type === "NODE_STREAM_DELTA" || event.type.includes("TEXT_MESSAGE")),
    render: (context) => StreamViewer(context),
  },
  {
    id: "default",
    name: "Overview",
    priority: 1,
    matches: () => true,
    render: (context) => DefaultViewer(context),
  },
];

export const viewerRegistry = {
  all: () => plugins,
  select: (context: ViewerContext) => {
    return [...plugins]
      .sort((a, b) => b.priority - a.priority)
      .find((plugin) => plugin.matches(context))!;
  },
};
