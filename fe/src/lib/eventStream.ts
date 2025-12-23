import type { AgUiEventEnvelope } from "../state/graphStore";

type EventStreamOptions = {
  url: string;
  onEvent: (event: AgUiEventEnvelope) => void;
  onError?: (error: Event) => void;
};

export const connectEventStream = ({ url, onEvent, onError }: EventStreamOptions) => {
  const source = new EventSource(url);

  source.onmessage = (message) => {
    try {
      const parsed = JSON.parse(message.data) as AgUiEventEnvelope;
      onEvent(parsed);
    } catch (error) {
      console.error("Failed to parse event stream payload", error);
    }
  };

  source.onerror = (event) => {
    if (onError) {
      onError(event);
    }
  };

  return () => {
    source.close();
  };
};
