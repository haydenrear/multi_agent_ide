import { useEffect, useMemo, useRef, useState } from "react";
import type { GraphEventRecord, GraphNode } from "../state/graphStore";
import { A2uiCore } from "../lib/a2uiBridge";
import type { A2uiServerMessage } from "../lib/a2uiMessageBuilder";

export type A2uiSurfaceRendererProps = {
  messages: A2uiServerMessage[];
  event?: GraphEventRecord;
  node?: GraphNode;
  onAction?: (action: {
    name: string;
    context: Record<string, string>;
  }) => void;
};

type SurfaceEntry = [string, A2uiCore.Types.Surface];

const resolveActionContext = (
  context?: { key: string; value: { literalString?: string } }[],
) => {
  if (!context) {
    return {};
  }
  return context.reduce<Record<string, string>>((acc, entry) => {
    if (entry.value.literalString) {
      acc[entry.key] = entry.value.literalString;
    }
    return acc;
  }, {});
};

const A2uiSurface = ({
  surfaceId,
  surface,
  processor,
  onAction,
}: {
  surfaceId: string;
  surface: A2uiCore.Types.Surface;
  processor: A2uiCore.Data.A2uiMessageProcessor;
  onAction?: (action: {
    name: string;
    context: Record<string, string>;
  }) => void;
}) => {
  const ref = useRef<HTMLElement | null>(null);

  useEffect(() => {
    const element = ref.current as
      | (HTMLElement & {
          surfaceId?: string;
          surface?: A2uiCore.Types.Surface;
          processor?: A2uiCore.Data.A2uiMessageProcessor;
        })
      | null;
    if (!element) {
      return;
    }
    element.surfaceId = surfaceId;
    element.surface = surface;
    element.processor = processor;
  }, [surfaceId, surface, processor]);

  useEffect(() => {
    const element = ref.current;
    if (!element || !onAction) {
      return;
    }
    const handler = (evt: Event) => {
      const detail = (evt as CustomEvent).detail as {
        action?: {
          name: string;
          context?: { key: string; value: { literalString?: string } }[];
        };
      };
      if (!detail?.action) {
        return;
      }
      onAction({
        name: detail.action.name,
        context: resolveActionContext(detail.action.context),
      });
    };
    element.addEventListener("a2uiaction", handler as EventListener);
    return () =>
      element.removeEventListener("a2uiaction", handler as EventListener);
  }, [onAction]);

  return <a2ui-surface ref={ref} />;
};

export const A2uiSurfaceRenderer = ({
  messages,
  onAction,
}: A2uiSurfaceRendererProps) => {
  const processor = useMemo(
    () => A2uiCore.Data.createSignalA2uiMessageProcessor(),
    [],
  );
  const [surfaces, setSurfaces] = useState<SurfaceEntry[]>([]);

  useEffect(() => {
    processor.clearSurfaces();
    processor.processMessages(messages);
    setSurfaces(Array.from(processor.getSurfaces().entries()));
  }, [messages, processor]);

  if (surfaces.length === 0) {
    return null;
  }

  return (
    <div className="a2ui-surface">
      {surfaces.map(([surfaceId, surface]) => (
        <A2uiSurface
          key={surfaceId}
          surfaceId={surfaceId}
          surface={surface}
          processor={processor}
          onAction={onAction}
        />
      ))}
    </div>
  );
};
