package com.hayden.multiagentide.artifacts;

import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.multiagentidelib.artifact.ArtifactHashing;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.multiagentidelib.prompt.PromptContributionListener;
import com.hayden.multiagentidelib.prompt.PromptContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    @Override
    public void onContribution(
            String contributorName,
            int priority,
            Set<AgentType> applicableAgents,
            String contributedText,
            int orderIndex,
            PromptContext context
    ) {
        String workflowRunId = resolveWorkflowRunId(context);
        if (workflowRunId == null) {
            log.trace("Skipping contribution artifact - no active workflow context");
            return;
        }
        
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            String contentHash = ArtifactHashing.hashText(contributedText);
            List<String> agentTypeNames = applicableAgents != null
                    ? applicableAgents.stream().map(AgentType::name).toList()
                    : List.of();
            
            Artifact.PromptContributionArtifact artifact = Artifact.PromptContributionArtifact.builder()
                    .artifactKey(key)
                    .contributorName(contributorName)
                    .priority(priority)
                    .agentTypes(agentTypeNames)
                    .contributedText(contributedText)
                    .orderIndex(orderIndex)
                    .hash(contentHash)
                    .metadata(Map.of(
                            "agentType", context.agentType() != null ? context.agentType().name() : "UNKNOWN"
                    ))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted PromptContributionArtifact: {} from {} (order: {})", 
                    key, contributorName, orderIndex);
        } catch (Exception e) {
            log.warn("Failed to emit PromptContributionArtifact for {}: {}", 
                    contributorName, e.getMessage());
        }
    }
    
    @Override
    public void onAssemblyComplete(
            String basePrompt,
            String assembledPrompt,
            int contributionCount,
            PromptContext context
    ) {
        // Assembly completion is handled separately in DefaultLlmRunner
        // where we have access to template info for RenderedPromptArtifact
        log.trace("Assembly complete with {} contributions", contributionCount);
    }
    
    private String resolveWorkflowRunId(PromptContext context) {
        if (context == null || context.currentContextId() == null) {
            return null;
        }
        String contextId = context.currentContextId().value();
        if (contextId == null || contextId.isBlank()) {
            return null;
        }
        return contextId;
    }
}
