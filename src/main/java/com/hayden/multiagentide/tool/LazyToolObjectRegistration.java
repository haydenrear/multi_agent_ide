package com.hayden.multiagentide.tool;

import com.embabel.agent.api.common.ToolObject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class LazyToolObjectRegistration implements EmbabelToolObjectRegistry.ToolObjectRegistration {

    private final EmbabelToolObjectRegistry.ToolRegistration underlying;

    List<ToolObject> setValues;

    @Override
    public Optional<List<ToolObject>> computeToolObject() {
        return underlying.computeToolObject();
    }

    @Override
    public Optional<List<ToolObject>> prev() {
        return Optional.ofNullable(setValues);
    }

    @Override
    public void set(List<ToolObject> values) {
        this.setValues = values;
    }
}
