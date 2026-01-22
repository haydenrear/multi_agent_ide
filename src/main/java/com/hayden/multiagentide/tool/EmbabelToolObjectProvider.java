package com.hayden.multiagentide.tool;

import com.embabel.agent.api.common.ToolObject;

import java.util.List;
import java.util.Optional;

public interface EmbabelToolObjectProvider {

    Optional<List<ToolObject>> tool(String name);

}
