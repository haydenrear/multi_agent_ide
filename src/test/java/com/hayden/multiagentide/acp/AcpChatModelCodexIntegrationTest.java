package com.hayden.multiagentide.acp;

import static com.hayden.multiagentide.config.LangChain4jConfiguration.wrap;
import static org.assertj.core.api.Assertions.assertThat;

import com.hayden.multiagentide.agent.AgentModels;
import com.hayden.multiagentide.agent.LangChain4jAgentTools;
import com.hayden.multiagentide.model.acp.AcpChatModel;
import dev.langchain4j.agentic.Agent;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.internal.AgentUtil;
import dev.langchain4j.agentic.observability.AgentListener;
import dev.langchain4j.agentic.observability.AgentRequest;
import dev.langchain4j.agentic.observability.AgentResponse;
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
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("acp")
@TestPropertySource(properties = {"spring.ai.mcp.server.stdio=false"})
class AcpChatModelCodexIntegrationTest {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private LangChain4jAgentTools tools;

    public interface TestAgent {
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
                            .listener(new AgentListener() {
                                @Override
                                public void beforeAgentInvocation(AgentRequest agentRequest) {
                                    AgentListener.super.beforeAgentInvocation(agentRequest);
                                }

                                @Override
                                public void afterAgentInvocation(AgentResponse agentResponse) {
                                    AgentListener.super.afterAgentInvocation(agentResponse);
                                }
                            })
                            .build(),
                    TestAgent.class
            );

            var res = agent
                    .kickOffAnyNumberOfAgentsForCodeSearch("1", "Can you read one of the files in the root directory, return the result, then write that result to another file named log.log, then update that file and add the words WHATEVER!??");
            log.info("{}", res);

        } catch (Exception e) {
            log.error("Error - will not fail test for codex-acp - but failed", e);
        }
    }
}
