package com.hayden.multiagentide.artifacts;

import com.hayden.multiagentidelib.prompt.PromptContributor;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.multiagentidelib.prompt.PromptContributionListener;
import com.hayden.multiagentidelib.prompt.PromptContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Listener that emits PromptContributionArtifact for each prompt contribution.
 * 
 * Captures individual contributions during prompt assembly for artifact tracking.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PromptContributionArtifactListener implements PromptContributionListener {
    
    private final ExecutionScopeService executionScopeService;
    
    public void onContribution(
            PromptContext context,
            PromptContributor promptContributor
    ) {
        var promptContributorName = promptContributor.name();
        String workflowRunId = context.currentRequest().key().value();
        if (workflowRunId == null) {
            log.trace("Skipping contribution artifact - no active workflow context");
            return;
        }

        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );

            Artifact.PromptContributionTemplate artifact = promptContributorArtifact(context, promptContributor, key, promptContributorName);

            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );

        } catch (Exception e) {
            log.warn("Failed to emit PromptContributionArtifact for {}: {}",
                    promptContributorName, e.getMessage());
        }
    }

}
