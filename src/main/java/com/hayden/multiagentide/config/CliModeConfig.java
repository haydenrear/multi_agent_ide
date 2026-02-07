package com.hayden.multiagentide.config;

import com.hayden.multiagentide.cli.ArtifactKeyFormatter;
import com.hayden.multiagentide.cli.CliEventFormatter;
import com.hayden.multiagentide.cli.CliEventListener;
import com.hayden.multiagentide.cli.CliGoalRunner;
import com.hayden.multiagentide.cli.CliInteractionLoop;
import com.hayden.multiagentide.cli.CliOutputWriter;
import com.hayden.multiagentide.controller.OrchestrationController;
import com.hayden.multiagentide.gate.PermissionGateAdapter;
import com.hayden.utilitymodule.config.EnvConfigProps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cli")
public class CliModeConfig {

    @Bean
    public CliOutputWriter cliOutputWriter() {
        return new CliOutputWriter();
    }

    @Bean
    public ArtifactKeyFormatter artifactKeyFormatter() {
        return new ArtifactKeyFormatter();
    }

    @Bean
    public CliEventFormatter cliEventFormatter(ArtifactKeyFormatter artifactKeyFormatter) {
        return new CliEventFormatter(artifactKeyFormatter);
    }

    @Bean
    public CliEventListener cliEventListener(CliEventFormatter formatter, CliOutputWriter outputWriter) {
        return new CliEventListener(formatter, outputWriter);
    }

    @Bean
    public CliInteractionLoop cliInteractionLoop(PermissionGateAdapter permissionGateAdapter, CliOutputWriter outputWriter) {
        return new CliInteractionLoop(permissionGateAdapter, outputWriter);
    }

    @Bean
    public CliGoalRunner cliGoalRunner(
            OrchestrationController orchestrationController,
            EnvConfigProps envConfigProps,
            CliOutputWriter outputWriter,
            CliInteractionLoop interactionLoop,
            @Value("${multi-agent-ide.cli.repository-url:}") String repositoryUrl,
            @Value("${multi-agent-ide.cli.base-branch:main}") String baseBranch,
            @Value("${multi-agent-ide.cli.title:CLI Goal}") String title
    ) {
        return new CliGoalRunner(
                orchestrationController,
                envConfigProps,
                outputWriter,
                interactionLoop,
                repositoryUrl,
                baseBranch,
                title
        );
    }
}
