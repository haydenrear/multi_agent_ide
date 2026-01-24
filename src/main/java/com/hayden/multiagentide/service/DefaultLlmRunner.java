package com.hayden.multiagentide.service;

import com.embabel.agent.api.common.ContextualPromptElement;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.agent.api.common.PromptRunner;
import com.embabel.agent.api.common.ToolObject;
import com.embabel.common.ai.prompt.PromptElement;
import com.hayden.multiagentide.agent.AskUserQuestionToolAdapter;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.multiagentidelib.prompt.PromptContributorService;
import com.hayden.multiagentide.tool.ToolAbstraction;
import com.hayden.multiagentide.tool.ToolContext;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of LlmRunner using Embabel's native prompt contribution pattern.
 * 
 * This implementation:
 * 1. Retrieves applicable PromptContributors based on AgentType
 * 2. Converts them to Embabel's ContextualPromptElement
 * 3. Uses withPromptElements() to inject dynamic content
 * 4. Uses withTemplate() for Jinja template rendering
 * 5. Returns structured responses via createObject()
 */
@Service
@RequiredArgsConstructor
public class DefaultLlmRunner implements LlmRunner {
    
    private final PromptContributorService promptContributorService;
    private final AskUserQuestionToolAdapter askUserQuestionToolAdapter;

    @Override
    public <T> T runWithTemplate(
            String templateName,
            PromptContext promptContext,
            Map<String, Object> model,
            ToolContext toolContext,
            Class<T> responseClass,
            OperationContext context
    ) {
        // Get applicable prompt contributors using the full PromptContext
        PromptElement[] contributors = promptContributorService.getContributors(promptContext)
                .toArray(ContextualPromptElement[]::new);

        var aiQuery = context.ai()
                .withDefaultLlm()
                .withPromptElements(contributors);

        aiQuery = applyToolContext(aiQuery, toolContext);

        var aiQueryWithTemplate = aiQuery.withTemplate(templateName);

        // Execute and return
        return aiQueryWithTemplate.createObject(responseClass, model);
    }

    private PromptRunner applyToolContext(PromptRunner promptRunner, ToolContext toolContext) {
        ToolContext mergedContext = mergeToolContext(toolContext);
        if (mergedContext.tools().isEmpty()) {
            return promptRunner;
        }
        PromptRunner updated = promptRunner;
        for (ToolAbstraction tool : mergedContext.tools()) {
            updated = switch (tool) {
                case ToolAbstraction.SpringToolCallback value ->
                        updated.withToolObject(new ToolObject(value.toolCallback()));
                case ToolAbstraction.SpringToolCallbackProvider value ->
                        applyToolCallbacks(updated, value.toolCallbackProvider().getToolCallbacks());
                case ToolAbstraction.EmbabelTool value ->
                        updated.withTool(value.tool());
                case ToolAbstraction.EmbabelToolObject value ->
                        updated.withToolObject(value.toolObject());
                case ToolAbstraction.EmbabelToolGroup value ->
                        updated.withToolGroup(value.toolGroup());
                case ToolAbstraction.EmbabelToolGroupRequirement value ->
                        updated.withToolGroup(value.requirement());
                case ToolAbstraction.ToolGroupStrings value ->
                        updated.withToolGroups(value.toolGroups());
            };
        }
        return updated;
    }

    private ToolContext mergeToolContext(ToolContext toolContext) {
        List<ToolAbstraction> merged = new ArrayList<>();
        merged.add(ToolAbstraction.fromToolCarrier(askUserQuestionToolAdapter));
        if (toolContext != null && !toolContext.tools().isEmpty()) {
            merged.addAll(toolContext.tools());
        }
        return new ToolContext(merged);
    }

    private PromptRunner applyToolCallbacks(PromptRunner promptRunner, ToolCallback[] toolCallbacks) {
        PromptRunner updated = promptRunner;
        for (ToolCallback toolCallback : toolCallbacks) {
            updated = updated.withToolObject(new ToolObject(toolCallback));
        }
        return updated;
    }
}
