package com.hayden.multiagentide.agent;

import dev.langchain4j.agent.tool.Tool;
import com.hayden.multiagentide.repository.GraphRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import com.hayden.multiagentide.service.WorktreeService;
import org.springframework.stereotype.Component;

/**
 * Tool definitions for LangChain4j agents.
 * These tools are available to all agents via the agent execution framework.
 */
@Component
public class LangChain4jAgentTools {

    private final GraphRepository graphRepository;
    private final WorktreeRepository worktreeRepository;
    private final WorktreeService worktreeService;

    public LangChain4jAgentTools(GraphRepository graphRepository,
                                WorktreeRepository worktreeRepository,
                                WorktreeService worktreeService) {
        this.graphRepository = graphRepository;
        this.worktreeRepository = worktreeRepository;
        this.worktreeService = worktreeService;
    }

    @Tool("Emit gui event to user")
    public String emitGuiEvent() {
        return null;
    }

    // ========== WORKTREE TOOLS ==========

    @Tool("Get current commit hash in a worktree")
    public String getCurrentCommitHash(String worktreeId) {
        return worktreeService.getCurrentCommitHash(worktreeId);
    }

    @Tool("Commit changes in a worktree with a message")
    public String commitChanges(String worktreeId, String message) {
        String commitHash = worktreeService.commitChanges(worktreeId, message);
        return "Committed changes: " + commitHash;
    }

    @Tool("Detect merge conflicts between worktrees")
    public String detectMergeConflicts(String childWorktreeId, String parentWorktreeId) {
        var conflicts = worktreeService.detectMergeConflicts(childWorktreeId, parentWorktreeId);
        if (conflicts.isEmpty()) {
            return "No merge conflicts detected";
        } else {
            return "Conflicts in files: " + String.join(", ", conflicts);
        }
    }

    @Tool("Update submodule pointer in main worktree after submodule changes")
    public String updateSubmodulePointer(String mainWorktreeId, String submoduleName) {
        worktreeService.updateSubmodulePointer(mainWorktreeId, submoduleName);
        return "Updated submodule pointer for " + submoduleName;
    }

    // ========== GRAPH TOOLS ==========

    @Tool("Get a node from the computation graph by ID")
    public String getNode(String nodeId) {
        var node = graphRepository.findById(nodeId);
        return node.map(n -> "Node: " + n.nodeId() + " (" + n.nodeType() + ") - " + n.title() + " - Status: " + n.status())
                .orElse("Node not found: " + nodeId);
    }

    @Tool("Get all child nodes of a parent node")
    public String getChildNodes(String parentNodeId) {
        var children = graphRepository.findByParentId(parentNodeId);
        if (children.isEmpty()) {
            return "No child nodes found for parent: " + parentNodeId;
        }
        StringBuilder sb = new StringBuilder("Child nodes:\n");
        for (var child : children) {
            sb.append("- ").append(child.nodeId()).append(" (").append(child.nodeType()).append(") ")
                    .append(child.title()).append("\n");
        }
        return sb.toString();
    }

    @Tool("Get worktrees for a specific node")
    public String getNodeWorktrees(String nodeId) {
        var worktrees = worktreeRepository.findByNodeId(nodeId);
        if (worktrees.isEmpty()) {
            return "No worktrees found for node: " + nodeId;
        }
        StringBuilder sb = new StringBuilder("Worktrees for node:\n");
        for (var wt : worktrees) {
            sb.append("- ").append(wt.worktreeId()).append(" (").append(wt.status()).append(") ")
                    .append(wt.worktreePath()).append("\n");
        }
        return sb.toString();
    }

    // ========== PLANNING TOOLS ==========

    @Tool("Break down a goal into specific work items/tickets")
    public String decomposeGoal(String goal) {
        // This is a template that LLM agents can use
        return """
                To decompose the goal: "%s"
                
                Consider breaking it into:
                1. Architecture & Setup - Design and set up the foundational structure
                2. Implementation - Implement core functionality
                3. Testing & Validation - Create tests and validate the implementation
                
                Each work item should be specific and actionable.
                """.formatted(goal);
    }

    @Tool("Generate code based on a work item description")
    public String generateCode(String workItemGoal, String context) {
        return """
                Generated code for: %s
                
                Context: %s
                
                ```java
                // Generated implementation
                public class Implementation {
                    public void execute() {
                        // Implementation for: %s
                    }
                }
                ```
                """.formatted(workItemGoal, context, workItemGoal);
    }

    @Tool("Review code for quality and adherence to requirements")
    public String reviewCode(String codeContent, String requirements) {
        // LLM will evaluate based on requirements
        return "Code review analysis requested for:\nCode: " + codeContent.substring(0, Math.min(100, codeContent.length())) + "...\nRequirements: " + requirements;
    }

    @Tool("Merge code from child worktree to parent worktree")
    public String mergeCode(String childWorktreeId, String parentWorktreeId) {
        return "Merge operation: child " + childWorktreeId + " -> parent " + parentWorktreeId;
    }

    @Tool("Create a branch for parallel work")
    public String createBranch(String sourceWorktreeId, String branchName) {
        return "Creating branch: " + branchName + " from worktree: " + sourceWorktreeId;
    }

    @Tool("Discard a worktree and all child worktrees")
    public String discardWorktree(String worktreeId) {
        worktreeService.discardWorktree(worktreeId);
        return "Discarded worktree: " + worktreeId;
    }
}
