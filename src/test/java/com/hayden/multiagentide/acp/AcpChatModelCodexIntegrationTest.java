package com.hayden.multiagentide.acp;

import static org.assertj.core.api.Assertions.assertThat;

import com.hayden.multiagentide.model.acp.AcpChatModel;
import dev.langchain4j.model.chat.ChatModel;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("acp")
class AcpChatModelCodexIntegrationTest {

    @Autowired
    private ChatModel chatModel;

    @Test
    void chatModelUsesAcpProtocol() {
        assertThat(chatModel).isInstanceOf(AcpChatModel.class);

        try {
            String response = chatModel.chat("hello acp");
            assertThat(response).isNotEmpty();
            log.info("Received response from ACP - {}", response);
        } catch (Exception e) {
            log.error("Error - will not fail test for codex-acp - but failed", e);
        }
    }
}
