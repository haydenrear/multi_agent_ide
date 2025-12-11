package com.hayden.multiagentide.repository;

import com.hayden.multiagentide.model.Spec;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory implementation of SpecRepository using ConcurrentHashMap for thread safety.
 */
@Repository
public class InMemorySpecRepository implements SpecRepository {

    private final ConcurrentHashMap<String, Spec> specs = new ConcurrentHashMap<>();

    @Override
    public void save(Spec spec) {
        specs.put(spec.specId(), spec);
    }

    @Override
    public Optional<Spec> findById(String specId) {
        return Optional.ofNullable(specs.get(specId));
    }

    @Override
    public List<Spec> findAll() {
        return new ArrayList<>(specs.values());
    }

    @Override
    public List<Spec> findByWorktreeId(String worktreeId) {
        return specs.values().stream()
                .filter(spec -> worktreeId.equals(spec.worktreeId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spec> findByFilePath(String filePath) {
        return specs.values().stream()
                .filter(spec -> filePath.equals(spec.filePath()))
                .findFirst();
    }

    @Override
    public void delete(String specId) {
        specs.remove(specId);
    }

    @Override
    public boolean exists(String specId) {
        return specs.containsKey(specId);
    }

    @Override
    public long count() {
        return specs.size();
    }

    @Override
    public void clear() {
        specs.clear();
    }
}
