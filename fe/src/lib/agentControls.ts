const baseUrl = process.env.NEXT_PUBLIC_CONTROL_API_URL ?? "";

const post = async (path: string) => {
  const response = await fetch(`${baseUrl}${path}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (!response.ok) {
    throw new Error(`Control request failed: ${response.status}`);
  }

  return response.json().catch(() => ({}));
};

export const requestPause = (nodeId: string) => post(`/api/agents/${nodeId}/pause`);
export const requestInterrupt = (nodeId: string) => post(`/api/agents/${nodeId}/interrupt`);
export const requestReview = (nodeId: string) => post(`/api/agents/${nodeId}/review-request`);
