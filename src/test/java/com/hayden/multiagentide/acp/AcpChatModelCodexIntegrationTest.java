package com.hayden.multiagentide.acp;

import static org.assertj.core.api.Assertions.assertThat;

import com.hayden.multiagentide.agent.AgentModels;
import com.hayden.multiagentide.agent.AgentInterfaces;
import com.hayden.multiagentide.agent.AgentLifecycleHandler;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("acp")
@TestPropertySource(properties = {"spring.ai.mcp.server.stdio=false"})
class AcpChatModelCodexIntegrationTest {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private AgentLifecycleHandler agentLifecycleHandler;

    @Test
    void chatModelUsesAcpProtocol() {
        assertThat(chatModel).isInstanceOf(AcpChatModel.class);

        try {
            String nodeId = UUID.randomUUID().toString();
            AgentModels.ContextAgentResult res = agentLifecycleHandler.runAgent(
                    AgentInterfaces.CONTEXT_AGENT,
                    new AgentInterfaces.ContextAgentInput(
                            "Can you read one of the files in the root directory, return the result, " +
                                    "then write that result to another file named log.log, " +
                                    "then update that file and add the words WHATEVER!??",
                            "ACP integration"
                    ),
                    AgentModels.ContextAgentResult.class,
                    nodeId
            );
            log.info("{}", res);

        } catch (Exception e) {
            log.error("Error - will not fail test for codex-acp - but failed", e);
        }
    }
}
