package com.hayden.multiagentide.artifacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.multiagentidelib.artifact.ArtifactHashing;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.utilitymodule.acp.events.Events;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service for emitting artifacts during agent execution.
 * 
 * CRITICAL: Artifacts are emitted as children of the current agent's ArtifactKey (contextId),
 * NOT as children of the workflow run ID. This preserves the hierarchical tree structure
 * that mirrors the agent execution tree.
 * 
 * Provides methods to emit:
 * - AgentRequestArtifact
 * - AgentResultArtifact
 * - InterruptRequestArtifact
 * - InterruptResolutionArtifact
 * - CollectorDecisionArtifact
 * - ExecutionConfigArtifact
 * - OutcomeEvidenceArtifact
 * - RenderedPromptArtifact
 * - PromptArgsArtifact
 * - GraphEvent as EventArtifact
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
     * 
     * The artifact is created as a child of the request's own contextId (ArtifactKey),
     * preserving the hierarchical structure.
     * 
     * @param request The agent request to emit
     * @param agentType The type of agent processing this request
     * @param nodeId The workflow node ID (optional)
     */
    public void emitAgentRequest(
            AgentModels.AgentRequest request,
            AgentType agentType,
            String nodeId
    ) {
        if (request == null) {
            log.debug("Skipping null agent request emission");
            return;
        }
        
        try {
            // The parent key is the request's own contextId - artifacts for this
            // agent's execution are children of this request
            ArtifactKey parentKey = request.contextId();
            if (parentKey == null) {
                log.warn("AgentRequest has no contextId, cannot emit artifact: {}", 
                        request.getClass().getSimpleName());
                return;
            }
            
            ArtifactKey artifactKey = parentKey.createChild();
            Map<String, Object> payload = toPayload(request);
            String hash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentRequestArtifact artifact = Artifact.AgentRequestArtifact.builder()
                    .artifactKey(artifactKey)
                    .agentType(agentType != null ? agentType.name() : request.getClass().getSimpleName())
                    .nodeId(nodeId)
                    .interactionType(determineInteractionType(request))
                    .payloadJson(payload)
                    .hash(hash)
                    .metadata(Map.of(
                            "requestClass", request.getClass().getSimpleName()
                    ))
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted AgentRequestArtifact: {} under {}", artifactKey, parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit AgentRequestArtifact for {}: {}", 
                    request.getClass().getSimpleName(), e.getMessage());
        }
    }
    
    /**
     * Emits an AgentResultArtifact for a result being returned.
     * 
     * The artifact is created as a child of the result's own contextId,
     * which should match the original request's contextId.
     * 
     * @param result The agent result to emit
     * @param agentType The type of agent that produced this result
     * @param nodeId The workflow node ID (optional)
     */
    public void emitAgentResult(
            AgentModels.AgentResult result,
            AgentType agentType,
            String nodeId
    ) {
        if (result == null) {
            log.debug("Skipping null agent result emission");
            return;
        }
        
        try {
            ArtifactKey parentKey = result.resultId();
            if (parentKey == null) {
                log.warn("AgentResult has no resultId, cannot emit artifact: {}", 
                        result.getClass().getSimpleName());
                return;
            }
            
            ArtifactKey artifactKey = parentKey.createChild();
            Map<String, Object> payload = toPayload(result);
            String hash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentResultArtifact artifact = Artifact.AgentResultArtifact.builder()
                    .artifactKey(artifactKey)
                    .agentType(agentType != null ? agentType.name() : result.getClass().getSimpleName())
                    .nodeId(nodeId)
                    .interactionType("AGENT_RESULT")
                    .payloadJson(payload)
                    .hash(hash)
                    .metadata(Map.of(
                            "resultClass", result.getClass().getSimpleName()
                    ))
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted AgentResultArtifact: {} under {}", artifactKey, parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit AgentResultArtifact for {}: {}", 
                    result.getClass().getSimpleName(), e.getMessage());
        }
    }
    
    /**
     * Emits an InterruptRequestArtifact.
     * 
     * @param interruptRequest The interrupt request to emit
     * @param agentType The type of agent that raised the interrupt
     * @param nodeId The workflow node ID (optional)
     */
    public void emitInterruptRequest(
            AgentModels.InterruptRequest interruptRequest,
            AgentType agentType,
            String nodeId
    ) {
        if (interruptRequest == null) {
            log.debug("Skipping null interrupt request emission");
            return;
        }
        
        try {
            ArtifactKey parentKey = interruptRequest.contextId();
            if (parentKey == null) {
                log.warn("InterruptRequest has no contextId, cannot emit artifact");
                return;
            }
            
            ArtifactKey artifactKey = parentKey.createChild();
            Map<String, Object> payload = toPayload(interruptRequest);
            String hash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentRequestArtifact artifact = Artifact.AgentRequestArtifact.builder()
                    .artifactKey(artifactKey)
                    .agentType(agentType != null ? agentType.name() : "INTERRUPT")
                    .nodeId(nodeId)
                    .interactionType("INTERRUPT_REQUEST")
                    .payloadJson(payload)
                    .hash(hash)
                    .metadata(Map.of(
                            "interruptType", interruptRequest.type() != null ? 
                                    interruptRequest.type().name() : "UNKNOWN",
                            "interruptClass", interruptRequest.getClass().getSimpleName()
                    ))
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted InterruptRequestArtifact: {} under {}", artifactKey, parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit InterruptRequestArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits an InterruptResolutionArtifact when an interrupt is resolved.
     * 
     * @param parentKey The parent artifact key (typically the interrupt request's key)
     * @param resolution The resolution data
     * @param nodeId The workflow node ID (optional)
     */
    public void emitInterruptResolution(
            ArtifactKey parentKey,
            Object resolution,
            String nodeId
    ) {
        if (parentKey == null) {
            log.warn("Cannot emit interrupt resolution without parent key");
            return;
        }
        
        try {
            ArtifactKey artifactKey = parentKey.createChild();
            Map<String, Object> payload = toPayload(resolution);
            String hash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentResultArtifact artifact = Artifact.AgentResultArtifact.builder()
                    .artifactKey(artifactKey)
                    .agentType("INTERRUPT")
                    .nodeId(nodeId)
                    .interactionType("INTERRUPT_RESOLUTION")
                    .payloadJson(payload)
                    .hash(hash)
                    .metadata(Map.of())
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted InterruptResolutionArtifact: {} under {}", artifactKey, parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit InterruptResolutionArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits a CollectorDecisionArtifact for collector agent decisions.
     * 
     * Collector results include: DiscoveryCollectorResult, PlanningCollectorResult, 
     * TicketCollectorResult, OrchestratorCollectorResult.
     * 
     * @param collectorResult The collector result containing the decision (any AgentResult from a collector)
     * @param agentType The type of collector agent
     * @param nodeId The workflow node ID (optional)
     */
    public void emitCollectorDecision(
            AgentModels.AgentResult collectorResult,
            AgentType agentType,
            String nodeId
    ) {
        if (collectorResult == null) {
            log.debug("Skipping null collector decision emission");
            return;
        }
        
        try {
            ArtifactKey parentKey = collectorResult.resultId();
            if (parentKey == null) {
                log.warn("CollectorResult has no resultId, cannot emit artifact");
                return;
            }
            
            ArtifactKey artifactKey = parentKey.createChild();
            Map<String, Object> payload = toPayload(collectorResult);
            String hash = ArtifactHashing.hashJson(payload);
            
            Artifact.AgentResultArtifact artifact = Artifact.AgentResultArtifact.builder()
                    .artifactKey(artifactKey)
                    .agentType(agentType != null ? agentType.name() : "COLLECTOR")
                    .nodeId(nodeId)
                    .interactionType("COLLECTOR_DECISION")
                    .payloadJson(payload)
                    .hash(hash)
                    .metadata(Map.of(
                            "collectorClass", collectorResult.getClass().getSimpleName()
                    ))
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted CollectorDecisionArtifact: {} under {}", artifactKey, parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit CollectorDecisionArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits an ExecutionConfigArtifact with the configuration state.
     * 
     * This should be called at execution start to capture all configuration
     * needed for reproducibility.
     * 
     * @param workflowRunId The workflow run ID
     * @param repositorySnapshotId Git commit SHA or similar
     * @param modelRefs Model configurations
     * @param toolPolicy Tool availability and limits
     * @param routingPolicy Routing and loop parameters
     */
    public void emitExecutionConfig(
            String workflowRunId,
            String repositorySnapshotId,
            Map<String, Object> modelRefs,
            Map<String, Object> toolPolicy,
            Map<String, Object> routingPolicy
    ) {
        try {
            ArtifactKey artifactKey = executionScopeService.createChildKey(
                    workflowRunId, 
                    ExecutionScopeService.GROUP_EXECUTION_CONFIG
            );
            
            // Build a combined config map for hashing
            Map<String, Object> configMap = Map.of(
                    "repositorySnapshotId", repositorySnapshotId != null ? repositorySnapshotId : "",
                    "modelRefs", modelRefs != null ? modelRefs : Map.of(),
                    "toolPolicy", toolPolicy != null ? toolPolicy : Map.of(),
                    "routingPolicy", routingPolicy != null ? routingPolicy : Map.of()
            );
            String hash = ArtifactHashing.hashJson(configMap);
            
            Artifact.ExecutionConfigArtifact artifact = Artifact.ExecutionConfigArtifact.builder()
                    .artifactKey(artifactKey)
                    .repositorySnapshotId(repositorySnapshotId)
                    .modelRefs(modelRefs != null ? modelRefs : Map.of())
                    .toolPolicy(toolPolicy != null ? toolPolicy : Map.of())
                    .routingPolicy(routingPolicy != null ? routingPolicy : Map.of())
                    .hash(hash)
                    .metadata(Map.of())
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_EXECUTION_CONFIG,
                    artifact
            );
            log.debug("Emitted ExecutionConfigArtifact: {}", artifactKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit ExecutionConfigArtifact for {}: {}", workflowRunId, e.getMessage());
        }
    }
    
    /**
     * Emits an OutcomeEvidenceArtifact.
     * 
     * @param workflowRunId The workflow run ID
     * @param evidenceType Type of evidence (test-result, build-result, merge-result, human-ack)
     * @param payload The evidence payload
     */
    public void emitOutcomeEvidence(
            String workflowRunId,
            String evidenceType,
            Object payload
    ) {
        try {
            ArtifactKey artifactKey = executionScopeService.createChildKey(
                    workflowRunId,
                    ExecutionScopeService.GROUP_OUTCOME_EVIDENCE
            );
            
            String payloadJson = objectMapper.writeValueAsString(payload);
            String hash = ArtifactHashing.hashText(payloadJson);
            
            Artifact.OutcomeEvidenceArtifact artifact = Artifact.OutcomeEvidenceArtifact.builder()
                    .artifactKey(artifactKey)
                    .evidenceType(evidenceType)
                    .payload(payloadJson)
                    .hash(hash)
                    .metadata(Map.of())
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifactToGroup(
                    workflowRunId,
                    ExecutionScopeService.GROUP_OUTCOME_EVIDENCE,
                    artifact
            );
            log.debug("Emitted OutcomeEvidenceArtifact: {} type={}", artifactKey, evidenceType);
            
        } catch (Exception e) {
            log.warn("Failed to emit OutcomeEvidenceArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits an OutcomeEvidenceArtifact under a specific parent key.
     * 
     * @param parentKey The parent artifact key
     * @param evidenceType Type of evidence
     * @param payload The evidence payload
     */
    public void emitOutcomeEvidence(
            ArtifactKey parentKey,
            String evidenceType,
            Object payload
    ) {
        if (parentKey == null) {
            log.warn("Cannot emit outcome evidence without parent key");
            return;
        }
        
        try {
            Artifact.OutcomeEvidenceArtifact artifact = eventArtifactMapper.mapOutcomeEvent(
                    evidenceType, payload, parentKey);
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted OutcomeEvidenceArtifact: {} type={}", artifact.artifactKey(), evidenceType);
            
        } catch (Exception e) {
            log.warn("Failed to emit OutcomeEvidenceArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits a RenderedPromptArtifact for a fully rendered prompt.
     * 
     * IMPORTANT: The parentKey should be the current agent request's contextId,
     * so the prompt is properly attributed to that agent in the tree.
     * 
     * @param parentKey The parent artifact key (agent request's contextId)
     * @param renderedText The fully rendered prompt text
     * @param templateRef Optional reference to the template artifact key
     */
    public void emitRenderedPrompt(
            ArtifactKey parentKey,
            String renderedText,
            ArtifactKey templateRef
    ) {
        if (parentKey == null) {
            log.warn("Cannot emit rendered prompt without parent key");
            return;
        }
        
        if (renderedText == null || renderedText.isEmpty()) {
            log.debug("Skipping empty rendered prompt emission");
            return;
        }
        
        try {
            ArtifactKey artifactKey = parentKey.createChild();
            String hash = ArtifactHashing.hashText(renderedText);
            
            List<Artifact> children = new ArrayList<>();
            
            // Add template reference if provided
            if (templateRef != null) {
                children.add(com.hayden.utilitymodule.acp.events.RefArtifact.builder()
                        .artifactKey(artifactKey.createChild())
                        .targetArtifactKey(templateRef)
                        .relationType("USES_TEMPLATE")
                        .metadata(Map.of())
                        .children(List.of())
                        .build());
            }
            
            Artifact.RenderedPromptArtifact artifact = Artifact.RenderedPromptArtifact.builder()
                    .artifactKey(artifactKey)
                    .renderedText(renderedText)
                    .hash(hash)
                    .metadata(Map.of(
                            "textLength", String.valueOf(renderedText.length())
                    ))
                    .children(children)
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted RenderedPromptArtifact: {} ({} chars) under {}", 
                    artifactKey, renderedText.length(), parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit RenderedPromptArtifact: {}", e.getMessage());
        }
    }

    /**
     * Emits a PromptArgsArtifact for template arguments.
     * 
     * @param parentKey The parent artifact key (typically the RenderedPrompt's key)
     * @param args The template arguments map
     */
    public void emitPromptArgs(
            ArtifactKey parentKey,
            Map<String, Object> args
    ) {
        if (parentKey == null) {
            log.warn("Cannot emit prompt args without parent key");
            return;
        }
        
        if (args == null || args.isEmpty()) {
            log.debug("Skipping empty prompt args emission");
            return;
        }
        
        try {
            ArtifactKey artifactKey = parentKey.createChild();
            String hash = ArtifactHashing.hashJson(args);
            
            Artifact.PromptArgsArtifact artifact = Artifact.PromptArgsArtifact.builder()
                    .artifactKey(artifactKey)
                    .args(args)
                    .hash(hash)
                    .metadata(Map.of(
                            "argCount", String.valueOf(args.size())
                    ))
                    .children(new ArrayList<>())
                    .build();
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted PromptArgsArtifact: {} ({} args) under {}", 
                    artifactKey, args.size(), parentKey);
            
        } catch (Exception e) {
            log.warn("Failed to emit PromptArgsArtifact: {}", e.getMessage());
        }
    }
    
    /**
     * Emits a GraphEvent as an EventArtifact.
     * 
     * @param parentKey The parent artifact key
     * @param event The GraphEvent to capture
     */
    public void emitGraphEvent(
            ArtifactKey parentKey,
            Events.GraphEvent event
    ) {
        if (parentKey == null) {
            log.warn("Cannot emit graph event without parent key");
            return;
        }
        
        if (event == null) {
            log.debug("Skipping null graph event emission");
            return;
        }
        
        // Skip ArtifactEvents to avoid infinite recursion
        if (!eventArtifactMapper.shouldCapture(event)) {
            return;
        }
        
        try {
            Artifact artifact;
            if (eventArtifactMapper.isStreamEvent(event)) {
                artifact = eventArtifactMapper.mapToStreamArtifact(event, parentKey);
            } else {
                artifact = eventArtifactMapper.mapToEventArtifact(event, parentKey);
            }
            
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted EventArtifact: {} type={}", artifact.artifactKey(), event.eventType());
            
        } catch (Exception e) {
            log.warn("Failed to emit EventArtifact for {}: {}", event.eventType(), e.getMessage());
        }
    }
    
    /**
     * Emits a ToolCallArtifact for a tool invocation.
     * 
     * @param parentKey The parent artifact key (agent request's contextId)
     * @param toolCallEvent The tool call event
     */
    public void emitToolCall(
            ArtifactKey parentKey,
            Events.ToolCallEvent toolCallEvent
    ) {
        if (parentKey == null) {
            log.warn("Cannot emit tool call without parent key");
            return;
        }
        
        if (toolCallEvent == null) {
            log.debug("Skipping null tool call emission");
            return;
        }
        
        try {
            Artifact.ToolCallArtifact artifact = eventArtifactMapper.mapToolCallEvent(
                    toolCallEvent, parentKey);
            executionScopeService.emitArtifact(artifact, parentKey);
            log.debug("Emitted ToolCallArtifact: {} tool={}", 
                    artifact.artifactKey(), toolCallEvent.title());
            
        } catch (Exception e) {
            log.warn("Failed to emit ToolCallArtifact for {}: {}", 
                    toolCallEvent.title(), e.getMessage());
        }
    }
    
    // ========== Private Helpers ==========
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> toPayload(Object obj) {
        if (obj == null) {
            return Map.of();
        }
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
        if (request instanceof AgentModels.ContextManagerRequest) {
            return "CONTEXT_MANAGER_REQUEST";
        }
        if (request instanceof AgentModels.ContextManagerRoutingRequest) {
            return "CONTEXT_MANAGER_ROUTING";
        }
        if (request instanceof AgentModels.ReviewRequest) {
            return "REVIEW_REQUEST";
        }
        if (request instanceof AgentModels.MergerRequest) {
            return "MERGER_REQUEST";
        }
        // Check for collector requests
        if (request.getClass().getSimpleName().contains("Collector")) {
            return "COLLECTOR_REQUEST";
        }
        // Check for orchestrator requests
        if (request.getClass().getSimpleName().contains("Orchestrator")) {
            return "ORCHESTRATOR_REQUEST";
        }
        // Check for dispatch requests (plural agent requests)
        if (request.getClass().getSimpleName().endsWith("Requests")) {
            return "DISPATCH_REQUEST";
        }
        return "AGENT_REQUEST";
    }
}
