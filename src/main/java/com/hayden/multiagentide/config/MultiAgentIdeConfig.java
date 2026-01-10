package com.hayden.multiagentide.config;

import com.hayden.multiagentidelib.acp.AcpChatModel;
import com.hayden.multiagentidelib.adapter.AgUiSerdes;
import com.hayden.multiagentidelib.config.McpProperties;
import com.hayden.multiagentidelib.model.acp.DefaultChatMemoryContext;
import com.hayden.multiagentidelib.service.UiStateService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AgUiSerdes.class, McpProperties.class, UiStateService.class, AcpChatModel.class, DefaultChatMemoryContext.class})
public class MultiAgentIdeConfig {
}
