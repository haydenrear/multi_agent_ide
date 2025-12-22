package com.hayden.multiagentide.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for ACP model provider.
 */
@ConfigurationProperties(prefix = "langchain4j.acp")
public class AcpModelProperties {

    private String transport = "stdio";
    private String command;
    private String args;
    private String workingDirectory;
    private String endpoint;
    private String apiKey;

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
