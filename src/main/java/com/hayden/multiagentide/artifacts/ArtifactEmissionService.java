package com.hayden.multiagentide.artifacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.multiagentidelib.artifact.*;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.utilitymodule.acp.events.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Service for emitting artifacts during agent execution.
 * 
 * Provides methods to emit:
 * - AgentRequestArtifact
 * - AgentResultArtifact
 * - InterruptRequestArtifact
 * - InterruptResolutionArtifact
 * - CollectorDecisionArtifact
 * - ExecutionConfigArtifact
 * - OutcomeEvidenceArtifact
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ArtifactEmissionService {
    
    private final ExecutionScopeService executionScopeService;
    private final EventArtifactMapper eventArtifactMapper;
    private final ObjectMapper objectMapper;
    
    /**
     * Emits an AgentRequestArtifact for a request being processed.
     */
    public void emitAgentRequest(
            String workflowRunId,
            String nodeId,
            AgentType agentType,
            AgentModels.AgentRequest request
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId, 
                    ExecutionScopeService.GROUP_INPUT_ARTIFACTS
            );
            
            Map<String, Object> payload = toPayload(request);
            String contentHash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentRequestArtifact artifact = Artifact.AgentRequestArtifact.builder()
                    .artifactKey(key)
                    .agentType(agentType.name())
                    .nodeId(nodeId)
                    .interactionType(determineInteractionType(request))
                    .payloadJson(payload)
                    .hash(contentHash)
                    .metadata(Map.of("requestClass", request.getClass().getSimpleName()))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_INPUT_ARTIFACTS,
                    artifact
            );
            
            log.debug("Emitted AgentRequestArtifact: {} for {}", key, agentType);
        } catch (Exception e) {
            log.error("Failed to emit AgentRequestArtifact for {}: {}", agentType, e.getMessage());
        }
    }
    
    /**
     * Emits an AgentResultArtifact for a result being returned.
     */
    public void emitAgentResult(
            String workflowRunId,
            String nodeId,
            AgentType agentType,
            AgentModels.AgentResult result
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            Map<String, Object> payload = toPayload(result);
            String contentHash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentResultArtifact artifact = Artifact.AgentResultArtifact.builder()
                    .artifactKey(key)
                    .agentType(agentType.name())
                    .nodeId(nodeId)
                    .interactionType("RESULT")
                    .payloadJson(payload)
                    .hash(contentHash)
                    .metadata(Map.of("resultClass", result.getClass().getSimpleName()))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted AgentResultArtifact: {} for {}", key, agentType);
        } catch (Exception e) {
            log.error("Failed to emit AgentResultArtifact for {}: {}", agentType, e.getMessage());
        }
    }
    
    /**
     * Emits an InterruptRequestArtifact.
     */
    public void emitInterruptRequest(
            String workflowRunId,
            String nodeId,
            AgentType agentType,
            AgentModels.InterruptRequest interrupt
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            Map<String, Object> payload = toPayload(interrupt);
            String contentHash = ArtifactHashing.hashJson(payload);
            
            // Create as AgentRequestArtifact with interrupt interaction type
            Artifact.AgentRequestArtifact artifact = Artifact.AgentRequestArtifact.builder()
                    .artifactKey(key)
                    .agentType(agentType.name())
                    .nodeId(nodeId)
                    .interactionType("INTERRUPT_REQUEST")
                    .payloadJson(payload)
                    .hash(contentHash)
                    .metadata(Map.of(
                            "interruptType", interrupt.type().name(),
                            "requestClass", interrupt.getClass().getSimpleName()
                    ))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted InterruptRequestArtifact: {} for {}", key, agentType);
        } catch (Exception e) {
            log.error("Failed to emit InterruptRequestArtifact for {}: {}", agentType, e.getMessage());
        }
    }
    
    /**
     * Emits an InterruptResolutionArtifact.
     */
    public void emitInterruptResolution(
            String workflowRunId,
            String nodeId,
            AgentType agentType,
            AgentModels.InterruptResolution resolution
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            Map<String, Object> payload = toPayload(resolution);
            String contentHash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentResultArtifact artifact = Artifact.AgentResultArtifact.builder()
                    .artifactKey(key)
                    .agentType(agentType.name())
                    .nodeId(nodeId)
                    .interactionType("INTERRUPT_RESOLUTION")
                    .payloadJson(payload)
                    .hash(contentHash)
                    .metadata(Map.of())
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted InterruptResolutionArtifact: {} for {}", key, agentType);
        } catch (Exception e) {
            log.error("Failed to emit InterruptResolutionArtifact for {}: {}", agentType, e.getMessage());
        }
    }
    
    /**
     * Emits a CollectorDecisionArtifact.
     */
    public void emitCollectorDecision(
            String workflowRunId,
            String nodeId,
            AgentType agentType,
            AgentModels.CollectorDecision decision
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            Map<String, Object> payload = toPayload(decision);
            String contentHash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentResultArtifact artifact = Artifact.AgentResultArtifact.builder()
                    .artifactKey(key)
                    .agentType(agentType.name())
                    .nodeId(nodeId)
                    .interactionType("COLLECTOR_DECISION")
                    .payloadJson(payload)
                    .hash(contentHash)
                    .metadata(Map.of("decisionType", decision.decisionType().name()))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted CollectorDecisionArtifact: {} for {}", key, agentType);
        } catch (Exception e) {
            log.error("Failed to emit CollectorDecisionArtifact for {}: {}", agentType, e.getMessage());
        }
    }
    
    /**
     * Emits an ExecutionConfigArtifact with the configuration state.
     */
    public void emitExecutionConfig(
            String workflowRunId,
            String repositorySnapshotId,
            Map<String, Object> modelRefs,
            Map<String, Object> toolPolicy,
            Map<String, Object> routingPolicy
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_EXECUTION_CONFIG
            );
            
            Map<String, Object> configPayload = Map.of(
                    "repositorySnapshotId", repositorySnapshotId != null ? repositorySnapshotId : "",
                    "modelRefs", modelRefs != null ? modelRefs : Map.of(),
                    "toolPolicy", toolPolicy != null ? toolPolicy : Map.of(),
                    "routingPolicy", routingPolicy != null ? routingPolicy : Map.of()
            );
            String contentHash = ArtifactHashing.hashJson(configPayload);
            
            Artifact.ExecutionConfigArtifact artifact = Artifact.ExecutionConfigArtifact.builder()
                    .artifactKey(key)
                    .repositorySnapshotId(repositorySnapshotId)
                    .modelRefs(modelRefs != null ? modelRefs : Map.of())
                    .toolPolicy(toolPolicy != null ? toolPolicy : Map.of())
                    .routingPolicy(routingPolicy != null ? routingPolicy : Map.of())
                    .hash(contentHash)
                    .metadata(Map.of("createdAt", Instant.now().toString()))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_EXECUTION_CONFIG,
                    artifact
            );
            
            log.debug("Emitted ExecutionConfigArtifact: {}", key);
        } catch (Exception e) {
            log.error("Failed to emit ExecutionConfigArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits an OutcomeEvidenceArtifact.
     */
    public void emitOutcomeEvidence(
            String workflowRunId,
            String evidenceType,
            Object payload
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_OUTCOME_EVIDENCE
            );
            
            String payloadJson = objectMapper.writeValueAsString(payload);
            String contentHash = ArtifactHashing.hashText(payloadJson);
            
            Artifact.OutcomeEvidenceArtifact artifact = Artifact.OutcomeEvidenceArtifact.builder()
                    .artifactKey(key)
                    .evidenceType(evidenceType)
                    .payload(payloadJson)
                    .hash(contentHash)
                    .metadata(Map.of())
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_OUTCOME_EVIDENCE,
                    artifact
            );
            
            log.debug("Emitted OutcomeEvidenceArtifact: {} (type: {})", key, evidenceType);
        } catch (Exception e) {
            log.error("Failed to emit OutcomeEvidenceArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits a RenderedPromptArtifact for a fully rendered prompt.
     */
    public void emitRenderedPrompt(
            String workflowRunId,
            String templateName,
            String renderedText,
            Map<String, Object> templateArgs
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            String contentHash = ArtifactHashing.hashText(renderedText);
            
            Artifact.RenderedPromptArtifact artifact = Artifact.RenderedPromptArtifact.builder()
                    .artifactKey(key)
                    .renderedText(renderedText)
                    .hash(contentHash)
                    .metadata(Map.of(
                            "templateName", templateName != null ? templateName : "unknown",
                            "argCount", templateArgs != null ? String.valueOf(templateArgs.size()) : String.valueOf(0)
                    ))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted RenderedPromptArtifact: {} for template {}", key, templateName);
        } catch (Exception e) {
            log.error("Failed to emit RenderedPromptArtifact for {}: {}", templateName, e.getMessage());
        }
    }
    
    /**
     * Emits a PromptArgsArtifact for template arguments.
     */
    public void emitPromptArgs(
            String workflowRunId,
            String templateName,
            Map<String, Object> args
    ) {
        try {
            ArtifactKey key = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION
            );
            
            String contentHash = ArtifactHashing.hashJson(args != null ? args : Map.of());
            
            Artifact.PromptArgsArtifact artifact = Artifact.PromptArgsArtifact.builder()
                    .artifactKey(key)
                    .args(args != null ? args : Map.of())
                    .hash(contentHash)
                    .metadata(Map.of("templateName", templateName != null ? templateName : "unknown"))
                    .children(List.of())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_AGENT_EXECUTION,
                    artifact
            );
            
            log.debug("Emitted PromptArgsArtifact: {} for template {}", key, templateName);
        } catch (Exception e) {
            log.error("Failed to emit PromptArgsArtifact for {}: {}", templateName, e.getMessage());
        }
    }
    
    /**
     * Emits a GraphEvent as an EventArtifact.
     */
    public void emitGraphEvent(String workflowRunId, Events.GraphEvent event) {
        try {
            if (!eventArtifactMapper.shouldCapture(event)) {
                return;
            }
            
            ArtifactKey parentKey = executionScopeService.getGroupKey(workflowRunId, ExecutionScopeService.GROUP_AGENT_EXECUTION).orElse(null);
            
            if (parentKey == null) {
                log.warn("No parent key for GraphEvent emission in {}", workflowRunId);
                return;
            }
            
            Artifact artifact;
            if (eventArtifactMapper.isStreamEvent(event)) {
                artifact = eventArtifactMapper.mapToStreamArtifact(event, parentKey);
            } else if (event instanceof Events.ToolCallEvent toolCall) {
                artifact = eventArtifactMapper.mapToolCallEvent(toolCall, parentKey);
            } else {
                artifact = eventArtifactMapper.mapToEventArtifact(event, parentKey);
            }
            
            executionScopeService.emitArtifact(artifact, parentKey);
            
            log.debug("Emitted EventArtifact for: {}", event.eventType());
        } catch (Exception e) {
            log.error("Failed to emit EventArtifact for {}: {}", event.eventType(), e.getMessage());
        }
    }
    
    // ========== Private Helpers ==========
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> toPayload(Object obj) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            log.warn("Failed to convert object to payload: {}", e.getMessage());
            return Map.of("error", "Failed to serialize: " + e.getMessage());
        }
    }
    
    private String determineInteractionType(AgentModels.AgentRequest request) {
        if (request instanceof AgentModels.InterruptRequest) {
            return "INTERRUPT_REQUEST";
        }
        return "AGENT_REQUEST";
    }
}
