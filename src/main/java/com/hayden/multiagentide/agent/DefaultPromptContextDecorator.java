package com.hayden.multiagentide.agent;

import com.hayden.multiagentidelib.prompt.PromptContext;
import org.springframework.stereotype.Component;

@Component
public class DefaultPromptContextDecorator implements PromptContextDecorator {

    @Override
    public PromptContext decorate(PromptContext promptContext, DecoratorContext context) {
        return promptContext;
    }
}
