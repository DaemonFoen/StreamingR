package com.nsu.runtime.model.factory;

import com.nsu.runtime.model.Channel;
import com.nsu.runtime.model.node.RuntimeNode;
import java.util.Map;

public interface RuntimeNodeFactory {

    String kind();

    boolean supports(
            Map<String, Class<?>> inputTypes,
            Map<String, Class<?>> outputTypes
    );

    RuntimeNode<?, ?> create(
            String id,
            Map<String, Channel<?>> inputs,
            Map<String, Channel<?>> outputs,
            Map<String, Object> config
    );
}
