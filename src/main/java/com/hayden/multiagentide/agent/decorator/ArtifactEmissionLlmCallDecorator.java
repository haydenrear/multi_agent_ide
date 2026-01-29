package com.hayden.multiagentide.agent.decorator;

import com.embabel.agent.api.common.nested.TemplateOperations;
import com.google.common.collect.Lists;
import com.hayden.multiagentide.artifacts.ExecutionScopeService;
import com.hayden.multiagentide.artifacts.PromptContributionArtifactListener;
import com.hayden.multiagentide.artifacts.repository.ArtifactRepository;
import com.hayden.multiagentidelib.artifact.PromptTemplateVersion;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.multiagentidelib.prompt.PromptContributor;
import com.hayden.multiagentidelib.prompt.PromptContributorAdapter;
import com.hayden.multiagentidelib.prompt.SimplePromptContributor;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Decorator that emits artifact events for LLM interactions.
 * <p>
 * This decorator hooks into the LLM call pipeline to:
 * - Emit rendered prompt artifacts before LLM calls
 * - Could be extended to emit response artifacts after LLM calls
 * - Emit tool call artifacts during execution
 * <p>
 * Artifacts are added to the execution's AgentExecutionArtifacts group.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ArtifactEmissionLlmCallDecorator implements LlmCallDecorator {

    private final ExecutionScopeService executionScopeService;

    private final ArtifactRepository artifactRepository;

    private final PromptContributionArtifactListener promptContributionArtifactListener;

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
            String hash = promptContext.hashContext().hash(renderedText);

//            TODO: add retrieval of all the existing prompt entities
//            artifactRepository.findByContentHash()

            // Create rendered prompt artifact
            ArtifactKey promptTemplateVersionKey = promptKey.createChild();

            Artifact.RenderedPromptArtifact promptArtifact = Artifact.RenderedPromptArtifact.builder()
                    .artifactKey(promptKey)
                    .renderedText(renderedText)
                    .hash(hash)
                    .promptName(promptContext.templateName())
                    .metadata(Map.of(
                            "agentType", promptContext.agentType().toString(),
                            "templateName", promptContext.templateName()
                    ))
                    .children(Stream.concat(
                                    Lists.newArrayList(
                                                    PromptTemplateVersion.builder()
                                                            .templateArtifactKey(promptTemplateVersionKey)
                                                            .templateStaticId(promptContext.templateName())
                                                            .templateText()
                                                            .hash()
                                                            .sourceLocation()
                                                            .build(),
                                                    Artifact.PromptArgsArtifact.builder()
                                                            .metadata(new HashMap<>())
                                                            .artifactKey(promptKey.createChild())
                                                            .args(llmCallContext.templateArgs())
                                                            .hash(promptContext.hashContext().hashMap(llmCallContext.templateArgs()))
                                                            .metadata(Map.of(
                                                                    "argCount", String.valueOf(llmCallContext.templateArgs().size())
                                                            ))
                                                            .build()
                                            )
                                            .stream(),
                                    promptContext.promptContributors()
                                            .stream()
                                            .flatMap(context -> {
                                                if (context instanceof PromptContributorAdapter adapter) {
                                                    return Stream.of(adapter.getContributor());
                                                }

                                                log.error("Found prompt element {} that was unknown template and could not add artifact or versioning - {}: {}.",
                                                          context.getPromptContributionLocation(), promptContext.agentType(), promptContext.currentContextId());
                                                return Stream.empty();
                                            })
                                            .map(pc -> renderedPromptContributor(promptContext, pc, promptKey.createChild()))
                                            .collect(Collectors.toCollection(ArrayList::new))
                                            .stream()
                            )
                            .collect(Collectors.toCollection(ArrayList::new)))
                    .build();

            // Emit artifact
            executionScopeService.emitArtifact(promptArtifact, groupKey);

            log.debug("Emitted rendered prompt artifact: {} for agent type: {}",
                    promptKey, promptContext.agentType());

        } catch (Exception e) {
            log.error("Failed to emit rendered prompt artifact", e);
        }
    }

    private List<Artifact.ToolCallArtifact> toolCall(LlmCallContext llmCallContext) {
        return new ArrayList<>();
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


    private static Artifact.RenderedPromptArtifact renderedPromptContributor(PromptContext promptContext,
                                                                             PromptContributor pc,
                                                                             ArtifactKey key) {
        var promptTemplateVersionKey = key.createChild();
        var a = promptContributorArtifact(promptContext, pc, promptTemplateVersionKey.createChild());
        var t = promptContributorTemplate(promptContext, pc, promptTemplateVersionKey.createChild());

        var renderedText = pc.contribute(promptContext);

        Artifact.RenderedPromptArtifact artifact = Artifact.RenderedPromptArtifact.builder()
                .artifactKey(key)
                .renderedText(renderedText)
                .hash(promptContext.hashContext().hash(renderedText))
                .metadata(Map.of(
                        "agentType", promptContext.agentType().toString(),
                        "templateName", promptContext.templateName()
                ))
                .children(Lists.newArrayList(a, t))
                .promptName(pc.name())
                .build();
        return artifact;
    }

    private static Artifact.PromptContributionTemplate promptContributorArtifact(PromptContext context,
                                                                                 PromptContributor promptContributor,
                                                                                 ArtifactKey key) {
        var promptContributorName = promptContributor.name();
        var contributedText = promptContributor.template();

        Artifact.PromptContributionTemplate artifact = Artifact.PromptContributionTemplate.builder()
                .artifactKey(key)
                .contributorName(promptContributorName)
                .templateText(contributedText)
                .hash(context.hashContext().hash(contributedText))
                .metadata(Map.of(
                        "agentType", context.agentType() != null ? context.agentType().name() : "UNKNOWN"
                ))
                .children(Lists.newArrayList())
                .build();

        return artifact;
    }


    private static Artifact.PromptArgsArtifact promptContributorTemplate(PromptContext context,
                                                                         PromptContributor promptContributor,
                                                                         ArtifactKey key) {
        Artifact.PromptArgsArtifact artifact = Artifact.PromptArgsArtifact.builder()
                .hash(context.hashContext().hashMap(promptContributor.args()))
                .children(Lists.newArrayList())
                .artifactKey(key)
                .args(promptContributor.args())
                .metadata(Map.of(
                        "argCount", String.valueOf(promptContributor.args().size()),
                        "promptContributorName", promptContributor.name(),
                        "agentType", context.agentType() != null ? context.agentType().name() : "UNKNOWN"
                ))
                .build();
        return artifact;
    }
}

