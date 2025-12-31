import React from "react";
import { act, render, screen, waitFor } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import { buildControlMessages } from "@/lib/a2uiMessageBuilder";

vi.mock("@/lib/agentControls", () => ({
  requestPause: vi.fn(),
  requestResume: vi.fn(),
  requestStop: vi.fn(),
  requestReview: vi.fn(),
  requestPrune: vi.fn(),
  requestBranch: vi.fn(),
}));

const setupGraphView = async () => {
  vi.resetModules();
  const [{ GraphView }, store] = await Promise.all([
    import("@/components/GraphView"),
    import("@/state/graphStore"),
  ]);
  const { useGraphNodes, useGraphStore, graphSelectors, graphActions } = store;

  const Harness = () => {
    const nodes = useGraphNodes();
    const filters = useGraphStore(graphSelectors.filters);
    const selectedNodeId = useGraphStore((state) => state.selectedNodeId);
    const unknownEvents = useGraphStore((state) => state.unknownEvents);
    return (
      <GraphView
        nodes={nodes}
        selectedNodeId={selectedNodeId}
        unknownEvents={unknownEvents}
        filters={filters}
        onFilterChange={graphActions.setFilters}
        onSelectNode={graphActions.selectNode}
      />
    );
  };

  const utils = render(<Harness />);
  return { ...utils, graphActions };
};

describe("GraphView", () => {
  it("renders structure and updates when nodes arrive", async () => {
    const { container, graphActions } = await setupGraphView();

    expect(screen.getByText("Graph Timeline")).toBeInTheDocument();
    expect(container.querySelector(".panel")).toBeTruthy();
    expect(container.querySelector(".filter-row")).toBeTruthy();
    expect(container.querySelector(".node-grid")).toBeTruthy();
    expect(screen.getByText("Waiting for events...")).toBeInTheDocument();

    act(() => {
      graphActions.applyEvent({
        type: "NODE_ADDED",
        rawEvent: {
          nodeId: "node-1",
          nodeTitle: "Node One",
          nodeType: "WORK",
          timestamp: "2024-01-01T00:00:00Z",
        },
      });
      graphActions.applyEvent({
        type: "NODE_ADDED",
        rawEvent: {
          nodeId: "node-2",
          nodeTitle: "Node Two",
          nodeType: "REVIEW",
          timestamp: "2024-01-01T00:00:01Z",
        },
      });
    });

    await waitFor(() => {
      expect(container.querySelectorAll(".node-card").length).toBe(2);
    });
  });

  it("renders unknown events and forwards control actions", async () => {
    const { container, graphActions } = await setupGraphView();
    const agentControls = await import("@/lib/agentControls");

    act(() => {
      graphActions.applyEvent({
        type: "GUI_RENDER",
        payload: {
          a2uiMessages: buildControlMessages("node-1"),
        },
        rawEvent: {
          timestamp: "2024-01-01T00:00:02Z",
        },
      });
      graphActions.applyEvent({
        type: "TEXT_MESSAGE",
        payload: {
          content: "hello",
        },
        rawEvent: {
          timestamp: "2024-01-01T00:00:03Z",
        },
      });
    });

    await waitFor(() => {
      expect(screen.getByText("Unknown events")).toBeInTheDocument();
    });
    await waitFor(() => {
      expect(container.querySelectorAll("a2ui-surface").length).toBeGreaterThan(
        1,
      );
    });

    const surface = container.querySelector("a2ui-surface");
    surface?.dispatchEvent(
      new CustomEvent("a2uiaction", {
        detail: {
          action: {
            name: "control.pause",
            context: [
              {
                key: "nodeId",
                value: { literalString: "node-1" },
              },
            ],
          },
        },
      }),
    );

    expect(agentControls.requestPause).toHaveBeenCalledWith("node-1");
  });
});
