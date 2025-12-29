package com.hayden.multiagentide.acp;

import static org.assertj.core.api.Assertions.assertThat;

import com.embabel.agent.core.AgentPlatform;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("acp")
@TestPropertySource(properties = {"spring.ai.mcp.server.stdio=false"})
class AcpChatModelCodexIntegrationTest {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private AgentPlatform agentPlatform;

    @Test
    void chatModelUsesAcpProtocol() {
        assertThat(chatModel).isInstanceOf(AcpChatModel.class);
        assertThat(agentPlatform).isNotNull();
    }
}
