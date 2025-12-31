"use client";

import { useEffect } from "react";
import { connectEventStream } from "../lib/eventStream";
import { GraphView } from "../components/GraphView";
import { NodeDetailsPanel } from "../components/NodeDetailsPanel";
import {
  graphActions,
  graphSelectors,
  useGraphNodes,
  useGraphStore,
} from "@/state/graphStore";

export default function Home() {
  const nodes = useGraphNodes();
  const filters = useGraphStore(graphSelectors.filters);
  const selectedNodeId = useGraphStore((state) => state.selectedNodeId);
  const unknownEvents = useGraphStore((state) => state.unknownEvents);

  useEffect(() => {
    const streamUrl =
      process.env.NEXT_PUBLIC_EVENT_STREAM_URL ?? "/api/events/stream";
    const disconnect = connectEventStream({
      url: streamUrl,
      onEvent: graphActions.applyEvent,
      onError: () => {
        console.error("Event stream connection lost");
      },
    });

    return () => disconnect();
  }, []);

  return (
    <main>
      <header>
        <div>
          <h1>Agent Graph UI</h1>
          <p className="muted">Live signal from multi-agent orchestration.</p>
        </div>
        <span className="badge">{nodes.length} nodes</span>
      </header>
      <div className="layout">
        <GraphView
          nodes={nodes}
          selectedNodeId={selectedNodeId}
          unknownEvents={unknownEvents}
          filters={filters}
          onFilterChange={graphActions.setFilters}
          onSelectNode={graphActions.selectNode}
        />
        <div className="panel">
          <h2>Node Details</h2>
          <div className="event-list">
            {nodes.map((node) => (
              <NodeDetailsPanel key={node.id} nodeId={node.id} />
            ))}
          </div>
        </div>
      </div>
    </main>
  );
}
