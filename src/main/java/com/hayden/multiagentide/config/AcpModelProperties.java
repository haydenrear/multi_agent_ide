package com.hayden.multiagentide.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for ACP model provider.
 */
@ConfigurationProperties(prefix = "multi-agent-embabel.acp")
@Data
public class AcpModelProperties {

    private String transport = "stdio";
    private String command;
    private String args;
    private String workingDirectory;
    private String endpoint;
    private String apiKey;
    private String authMethod;

}
