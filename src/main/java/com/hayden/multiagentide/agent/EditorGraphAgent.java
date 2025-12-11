package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.mixins.GraphAgent;
import com.hayden.multiagentide.model.mixins.GraphNode;
import com.hayden.multiagentide.model.Spec;
import com.hayden.multiagentide.model.mixins.WorkNode;
import com.hayden.multiagentide.repository.SpecRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Editor agent that generates code with LangChain4j Agentic and streams output events.
 */
@Component
public class EditorGraphAgent extends BaseGraphAgent {

    private final AgentInterfaces.EditorAgent editorAgent;
    private final SpecRepository specRepository;

    public EditorGraphAgent(EventBus eventBus,
                             AgentInterfaces.EditorAgent editorAgent,
                             SpecRepository specRepository) {
        super(eventBus);
        this.editorAgent = editorAgent;
        this.specRepository = specRepository;
    }

    @Override
    public String agentType() {
        return "editor";
    }

    @Override
    public boolean supports(GraphNode node) {
        return node instanceof WorkNode && ((WorkNode) node).agentType().equals("editor");
    }

    @Override
    public GraphNode execute(GraphNode node, GraphAgent.ExecutionContext context) throws Exception {
        WorkNode workNode = (WorkNode) node;

        emitStatusChangeEvent(workNode.nodeId(), GraphNode.NodeStatus.READY,
                GraphNode.NodeStatus.RUNNING, "Code generation started", context);

        String specContext = specRepository.findById(workNode.specFileId())
                .map(Spec::plan)
                .orElse("No prior plan available.");

        String generatedCode = editorAgent.generateCode(workNode.goal(), specContext);

        int totalTokens = streamGeneratedContent(workNode, generatedCode, context);

        if (workNode.specFileId() != null) {
            specRepository.findById(workNode.specFileId()).ifPresent(spec -> {
                String specUpdate = "## Plan\n\nGenerated implementation:\n```\n" + generatedCode + "\n```";
                Spec updated = spec.withPlan(specUpdate);
                specRepository.save(updated);
            });
        }

        emitStatusChangeEvent(workNode.nodeId(), GraphNode.NodeStatus.RUNNING,
                GraphNode.NodeStatus.COMPLETED, "Code generation completed", context);

        return new WorkNode(
                workNode.nodeId(),
                workNode.title(),
                workNode.goal(),
                GraphNode.NodeStatus.COMPLETED,
                workNode.parentNodeId(),
                workNode.childNodeIds(),
                workNode.metadata(),
                workNode.createdAt(),
                Instant.now(),
                workNode.mainWorktreeId(),
                workNode.submoduleWorktreeIds(),
                workNode.specFileId(),
                1,
                1,
                workNode.agentType(),
                generatedCode,
                true,
                totalTokens
        );
    }

    private int streamGeneratedContent(WorkNode workNode, String generatedCode, GraphAgent.ExecutionContext context) {
        int chunkSize = 120;
        int tokensSent = 0;
        for (int start = 0; start < generatedCode.length(); start += chunkSize) {
            int end = Math.min(start + chunkSize, generatedCode.length());
            String chunk = generatedCode.substring(start, end);
            int chunkTokens = Math.max(1, chunk.length() / 4);
            tokensSent += chunkTokens;
            emitStreamDeltaEvent(workNode.nodeId(), chunk, chunkTokens, end >= generatedCode.length(), context);
        }
        return tokensSent;
    }
}
