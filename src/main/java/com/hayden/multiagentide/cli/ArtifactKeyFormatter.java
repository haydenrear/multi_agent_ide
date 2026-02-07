package com.hayden.multiagentide.cli;

import com.hayden.acp_cdc_ai.acp.events.ArtifactKey;

import java.util.Optional;

public class ArtifactKeyFormatter {

    public String formatHierarchy(String nodeId) {
        if (nodeId == null || nodeId.isBlank()) {
            return "hierarchy=unknown";
        }
        try {
            ArtifactKey key = new ArtifactKey(nodeId);
            String root = key.root().value();
            Optional<ArtifactKey> parent = key.parent();
            String parentValue = parent.map(ArtifactKey::value).orElse("none");
            int depth = key.depth();
            return "root=" + root + " depth=" + depth + " parent=" + parentValue;
        } catch (IllegalArgumentException ex) {
            return "hierarchy=unknown";
        }
    }
}
