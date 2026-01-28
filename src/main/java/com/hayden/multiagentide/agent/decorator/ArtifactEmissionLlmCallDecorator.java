package com.hayden.multiagentide.agent.decorator;

import com.embabel.agent.api.common.nested.TemplateOperations;
import com.hayden.multiagentide.artifacts.ExecutionScopeService;
import com.hayden.multiagentide.artifacts.repository.ArtifactRepository;
import com.hayden.multiagentidelib.artifact.PromptTemplateVersion;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Decorator that emits artifact events for LLM interactions.
 * 
 * This decorator hooks into the LLM call pipeline to:
 * - Emit rendered prompt artifacts before LLM calls
 * - Could be extended to emit response artifacts after LLM calls
 * - Emit tool call artifacts during execution
 * 
 * Artifacts are added to the execution's AgentExecutionArtifacts group.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ArtifactEmissionLlmCallDecorator implements LlmCallDecorator {
    
    private final ExecutionScopeService executionScopeService;

    private final ArtifactRepository artifactRepository;

    @Override
    public int order() {
        return 10_000;
    }
    
    @Override
    public LlmCallContext decorate(LlmCallContext context) {
        if (context == null || context.promptContext() == null) {
            return context;
        }
        
        PromptContext promptContext = context.promptContext();
        TemplateOperations templateOps = context.templateOperations();
        emitRenderedPrompt(promptContext, templateOps, context);
        return context;
    }
    
    /**
     * Emits a rendered prompt artifact.
     */
    private void emitRenderedPrompt(
            PromptContext promptContext,
            TemplateOperations templateOps,
            LlmCallContext llmCallContext
    ) {
        try {
            // Get the artifact key for the agent execution group
            ArtifactKey groupKey = promptContext.currentRequest().artifactKey();

            // Create child key for this prompt
            ArtifactKey promptKey = groupKey.createChild();
            
            // Extract rendered text from template operations or prompt context
            String renderedText = extractRenderedText(templateOps, llmCallContext);

            if (renderedText == null)
                return;
            
            // Compute hash of the rendered text
            String hash = Artifact.HashContext.defaultHashContext().hash(renderedText);

//            artifactRepository.findByContentHash()
            
            // Create rendered prompt artifact
            Artifact.RenderedPromptArtifact promptArtifact = Artifact.RenderedPromptArtifact.builder()
                    .artifactKey(promptKey)
                    .renderedText(renderedText)
                    .hash(hash)
                    .metadata(Map.of(
                            "agentType", promptContext.agentType().toString(),
                            "templateName", promptContext.templateName()
                    ))
                    .children(List.of(
                            PromptTemplateVersion.builder()
                                    .templateArtifactKey(promptKey.createChild())
                                    .templateStaticId(promptContext.templateName())
                                    .build(),
                            Artifact.PromptArgsArtifact.builder()
                                    .artifactKey(promptKey.createChild())
                                    .args(llmCallContext.templateArgs())
//                                    .hash()
                                    .metadata(Map.of(
                                            "argCount", String.valueOf(llmCallContext.templateArgs().size())
                                    ))
                                    .build()
                    ))
                    .build();
            
            // Emit artifact
            executionScopeService.emitArtifact(promptArtifact, groupKey);

            log.debug("Emitted rendered prompt artifact: {} for agent type: {}", 
                    promptKey, promptContext.agentType());
            
        } catch (Exception e) {
            log.error("Failed to emit rendered prompt artifact", e);
        }
    }


    /**
     * Extracts the rendered text from template operations or builds it from prompt context.
     */
    private String extractRenderedText(TemplateOperations templateOps, LlmCallContext llmCallContext) {
        if (templateOps == null) {
            return null;
        }

        return templateOps.generateText(llmCallContext.templateArgs());
    }
}

