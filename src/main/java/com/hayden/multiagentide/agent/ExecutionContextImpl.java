package com.hayden.multiagentide.agent;

import com.hayden.multiagentide.infrastructure.EventBus;
import com.hayden.multiagentide.model.Events;
import com.hayden.multiagentide.model.GraphAgent;
import com.hayden.multiagentide.model.Spec;
import com.hayden.multiagentide.model.WorktreeContext;
import com.hayden.multiagentide.repository.SpecRepository;
import com.hayden.multiagentide.repository.WorktreeRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of ExecutionContext for agents.
 * Provides access to worktrees, specs, and event publishing.
 */
public class ExecutionContextImpl implements GraphAgent.ExecutionContext {

    private final WorktreeRepository worktreeRepository;
    private final SpecRepository specRepository;
    private final EventBus eventBus;
    private final Map<String, String> metadata;

    public ExecutionContextImpl(WorktreeRepository worktreeRepository,
                              SpecRepository specRepository,
                              EventBus eventBus) {
        this.worktreeRepository = worktreeRepository;
        this.specRepository = specRepository;
        this.eventBus = eventBus;
        this.metadata = new HashMap<>();
    }

    @Override
    public WorktreeContext getWorktree(String worktreeId) {
        return worktreeRepository.findById(worktreeId)
                .orElseThrow(() -> new RuntimeException("Worktree not found: " + worktreeId));
    }

    @Override
    public Spec getSpec(String specId) {
        return specRepository.findById(specId)
                .orElseThrow(() -> new RuntimeException("Spec not found: " + specId));
    }

    @Override
    public void saveWorktree(WorktreeContext worktree) {
        worktreeRepository.save(worktree);
    }

    @Override
    public void saveSpec(Spec spec) {
        specRepository.save(spec);
    }

    @Override
    public void emitEvent(Events.GraphEvent event) {
        eventBus.publish(event);
    }

    @Override
    public String getMetadata(String key) {
        return metadata.get(key);
    }

    @Override
    public void setMetadata(String key, String value) {
        metadata.put(key, value);
    }

    /**
     * Get all metadata.
     */
    public Map<String, String> getMetadata() {
        return new HashMap<>(metadata);
    }

    /**
     * Factory for creating execution contexts.
     */
    @Service
    public static class Factory {
        private final WorktreeRepository worktreeRepository;
        private final SpecRepository specRepository;
        private final EventBus eventBus;

        public Factory(WorktreeRepository worktreeRepository,
                     SpecRepository specRepository,
                     EventBus eventBus) {
            this.worktreeRepository = worktreeRepository;
            this.specRepository = specRepository;
            this.eventBus = eventBus;
        }

        public ExecutionContextImpl create() {
            return new ExecutionContextImpl(worktreeRepository, specRepository, eventBus);
        }
    }
}
