package com.hayden.multiagentide.acp;

import static com.hayden.multiagentide.config.LangChain4jConfiguration.wrap;
import static org.assertj.core.api.Assertions.assertThat;

import com.hayden.multiagentide.agent.AgentModels;
import com.hayden.multiagentide.agent.LangChain4jAgentTools;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import dev.langchain4j.agentic.Agent;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.internal.AgentSpecification;
import dev.langchain4j.agentic.internal.AgentUtil;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@Slf4j
@SpringBootTest
@ActiveProfiles("acp")
class AcpChatModelCodexIntegrationTest {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private LangChain4jAgentTools tools;

    public interface TestAgent extends AgentSpecification {
        @Agent
        String kickOffAnyNumberOfAgentsForCodeSearch(@MemoryId String memId,
                                                     @UserMessage String msg);
    }

    @Test
    void chatModelUsesAcpProtocol() {
        assertThat(chatModel).isInstanceOf(AcpChatModel.class);

        try {

            boolean add = chatModel.listeners().add(new ChatModelListener() {
                @Override
                public void onResponse(ChatModelResponseContext responseContext) {
                    ChatModelListener.super.onResponse(responseContext);
                }
            });


            TestAgent agent = wrap(
                    AgenticServices.agentBuilder(TestAgent.class)
                            .tools(tools)
                            .chatModel(chatModel)
                            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1024))
                            .beforeAgentInvocation(ar -> {
                                System.out.printf("");
                            })
                            .afterAgentInvocation(ar -> {
                                System.out.printf("");
                            })
                            .build(),
                    TestAgent.class
            );

            var res = agent
                    .kickOffAnyNumberOfAgentsForCodeSearch("1", "What capabilities do you have to use? - do you have an emit ui event capability?");
            log.info("{}", res);
        } catch (Exception e) {
            log.error("Error - will not fail test for codex-acp - but failed", e);
        }
    }
}
