package com.hayden.multiagentide.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hayden.commitdiffcontext.cdc_config.SkipInSchemaFilter;
import com.hayden.commitdiffcontext.cdc_config.SkipSessionSchema;
import com.hayden.commitdiffcontext.mcp.ToolCarrier;
import com.hayden.commitdiffmodel.config.DisableGraphQl;
import com.hayden.utilitymodule.schema.DelegatingSchemaReplacer;
import com.hayden.utilitymodule.schema.SpecialJsonSchemaGenerator;
import com.hayden.utilitymodule.schema.SpecialMethodToolCallbackProviderFactory;
import io.modelcontextprotocol.server.*;
import io.modelcontextprotocol.server.transport.WebMvcSseServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpServerTransportProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.mcp.server.autoconfigure.McpServerProperties;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Configuration
@Import({DelegatingSchemaReplacer.class, SpecialJsonSchemaGenerator.class, SpecialMethodToolCallbackProviderFactory.class,
         SkipSessionSchema.class, SkipInSchemaFilter.class, DisableGraphQl.class})
@Slf4j
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        SecurityFilterAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
})
@EnableConfigurationProperties({McpServerProperties.class})
public class SpringMcpConfig {

    @Bean
    public ToolCallbackProvider tools(List<ToolCarrier> codeSearchMcpTools,
                                      SpecialMethodToolCallbackProviderFactory specialMethodToolCallbackProvider,
                                      SpecialJsonSchemaGenerator specialJsonSchemaGenerator,
                                      DelegatingSchemaReplacer schemaReplacer,
                                      ApplicationContext ctx) {
        return specialMethodToolCallbackProvider.createToolCallbackProvider(
                codeSearchMcpTools.stream()
                        .filter(SpringMcpConfig::hasToolMethod)
                        .map(tc -> (Object) tc)
                        .toList(),
                specialJsonSchemaGenerator,
                schemaReplacer,
                ctx);
    }

    public static boolean hasToolMethod(Object obj) {
        var objP = AopProxyUtils.getSingletonTarget(obj);
        return Arrays.stream((objP == null ? obj : objP).getClass().getDeclaredMethods())
                .anyMatch(m -> m.isAnnotationPresent(Tool.class));
    }

    @Bean
    public RouterFunction<ServerResponse> mvcMcpRouterFunction(WebMvcSseServerTransportProvider transportProvider) {
        return transportProvider.getRouterFunction();
    }

    @Bean
    @ConditionalOnMissingBean
    public McpSchema.ServerCapabilities.Builder capabilitiesBuilder() {
        return McpSchema.ServerCapabilities.builder();
    }

    @SneakyThrows
    @Bean("mcpSyncServer")
    @Primary
    public McpSyncServer mcpSyncServer(McpServerTransportProvider transportProvider,
                                       McpSchema.ServerCapabilities.Builder capabilitiesBuilder,
                                       McpServerProperties serverProperties,
                                       ObjectProvider<List<McpServerFeatures.SyncToolSpecification>> tools,
                                       ObjectProvider<List<McpServerFeatures.SyncResourceSpecification>> resources,
                                       ObjectProvider<List<McpServerFeatures.SyncPromptSpecification>> prompts,
                                       ObjectProvider<List<McpServerFeatures.SyncCompletionSpecification>> completions,
                                       ObjectProvider<BiConsumer<McpSyncServerExchange, List<McpSchema.Root>>> rootsChangeConsumers,
                                       List<ToolCallbackProvider> toolCallbackProvider) {
        McpSchema.Implementation serverInfo = new McpSchema.Implementation(serverProperties.getName(),
                serverProperties.getVersion());

        // Create the server with both tool and resource capabilities
        IdeMcpServer.SyncSpecification serverBuilder = IdeMcpServer.sync(transportProvider).serverInfo(serverInfo);

        // Tools
        if (serverProperties.getCapabilities().isTool()) {
            log.info("Enable tools capabilities, notification: " + serverProperties.isToolChangeNotification());
            capabilitiesBuilder.tools(serverProperties.isToolChangeNotification());

            List<McpServerFeatures.SyncToolSpecification> toolSpecifications = new ArrayList<>(
                    tools.stream().flatMap(List::stream).toList());

            List<ToolCallback> providerToolCallbacks = toolCallbackProvider.stream()
                    .map(pr -> List.of(pr.getToolCallbacks()))
                    .flatMap(List::stream)
                    .filter(fc -> fc instanceof ToolCallback)
                    .map(fc -> (ToolCallback) fc)
                    .toList();

            toolSpecifications.addAll(this.toSyncToolSpecifications(providerToolCallbacks, serverProperties));

            if (!CollectionUtils.isEmpty(toolSpecifications)) {
                serverBuilder.tools(toolSpecifications);
                log.info("Registered tools: " + toolSpecifications.size());
            }
        }

        // Resources
        if (serverProperties.getCapabilities().isResource()) {
            log.info(
                    "Enable resources capabilities, notification: " + serverProperties.isResourceChangeNotification());
            capabilitiesBuilder.resources(false, serverProperties.isResourceChangeNotification());

            List<McpServerFeatures.SyncResourceSpecification> resourceSpecifications = resources.stream().flatMap(List::stream).toList();
            if (!CollectionUtils.isEmpty(resourceSpecifications)) {
                serverBuilder.resources(resourceSpecifications);
                log.info("Registered resources: " + resourceSpecifications.size());
            }
        }

        // Prompts
        if (serverProperties.getCapabilities().isPrompt()) {
            log.info("Enable prompts capabilities, notification: " + serverProperties.isPromptChangeNotification());
            capabilitiesBuilder.prompts(serverProperties.isPromptChangeNotification());

            List<McpServerFeatures.SyncPromptSpecification> promptSpecifications = prompts.stream().flatMap(List::stream).toList();
            if (!CollectionUtils.isEmpty(promptSpecifications)) {
                serverBuilder.prompts(promptSpecifications);
                log.info("Registered prompts: " + promptSpecifications.size());
            }
        }

        // Completions
        if (serverProperties.getCapabilities().isCompletion()) {
            log.info("Enable completions capabilities");
            capabilitiesBuilder.completions();

            List<McpServerFeatures.SyncCompletionSpecification> completionSpecifications = completions.stream()
                    .flatMap(List::stream)
                    .toList();
            if (!CollectionUtils.isEmpty(completionSpecifications)) {
                serverBuilder.completions(completionSpecifications);
                log.info("Registered completions: " + completionSpecifications.size());
            }
        }

        rootsChangeConsumers.ifAvailable(consumer -> {
            serverBuilder.rootsChangeHandler((exchange, roots) -> consumer.accept(exchange, roots));
            log.info("Registered roots change consumer");
        });

        serverBuilder.capabilities(capabilitiesBuilder.build());

        serverBuilder.instructions(serverProperties.getInstructions());

        serverBuilder.requestTimeout(serverProperties.getRequestTimeout());

        var builtServer = serverBuilder.build();

        return builtServer;
    }

    @Bean
    public WebMvcSseServerTransportProvider httpProvider(
            ObjectMapper om, McpServerProperties sseProperties) {
        return new WebMvcSseServerTransportProvider(om, sseProperties.getBaseUrl(),
                sseProperties.getSseMessageEndpoint(), sseProperties.getSseEndpoint());
    }

    @Bean
    public List<McpServerFeatures.SyncToolSpecification> syncTools(ObjectProvider<List<ToolCallback>> toolCalls,
                                                                   List<ToolCallback> toolCallbacksList,
                                                                   McpServerProperties serverProperties) {

        List<ToolCallback> tools = new ArrayList<>(toolCalls.stream().flatMap(List::stream).toList());

        if (!CollectionUtils.isEmpty(toolCallbacksList)) {
            tools.addAll(toolCallbacksList);
        }

        return this.toSyncToolSpecifications(tools, serverProperties);
    }

    private List<McpServerFeatures.SyncToolSpecification> toSyncToolSpecifications(List<ToolCallback> tools,
                                                                                   McpServerProperties serverProperties) {

        // De-duplicate tools by their name, keeping the first occurrence of each tool
        // name
        return tools.stream() // Key: tool name
                .collect(Collectors.toMap(tool -> tool.getToolDefinition().name(), tool -> tool, // Value:
                        // the
                        // tool
                        // itself
                        (existing, replacement) -> existing)) // On duplicate key, keep the
                // existing tool
                .values()
                .stream()
                .map(tool -> {
                    String toolName = tool.getToolDefinition().name();
                    MimeType mimeType = (serverProperties.getToolResponseMimeType().containsKey(toolName))
                            ? MimeType.valueOf(serverProperties.getToolResponseMimeType().get(toolName)) : null;
                    return McpToolUtils.toSyncToolSpecification(tool, mimeType);
                })
                .toList();
    }


}
