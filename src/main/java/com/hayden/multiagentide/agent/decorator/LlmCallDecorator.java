package com.hayden.multiagentide.agent.decorator;

import com.embabel.agent.api.common.nested.TemplateOperations;
import com.hayden.multiagentide.tool.ToolContext;
import com.hayden.multiagentidelib.prompt.PromptContext;

public interface LlmCallDecorator {

    default int order() {
        return 0;
    }

    record LlmCallContext(PromptContext promptContext, ToolContext tcc, TemplateOperations templateOperations) {}

    default LlmCallContext decorate(LlmCallContext promptContext) {
        return promptContext;
    }
}
