package com.nsu.runtime.model.factory;

import com.nsu.runtime.model.Channel;
import com.nsu.runtime.model.node.MapNode;
import com.nsu.runtime.model.node.RuntimeNode;
import java.util.Map;
import java.util.function.Function;

public final class MapNodeFactory<I, O>
        implements RuntimeNodeFactory {

    private final Class<I> inType;
    private final Class<O> outType;

    public MapNodeFactory(Class<I> inType, Class<O> outType) {
        this.inType = inType;
        this.outType = outType;
    }

    @Override
    public String kind() {
        return "map";
    }

    @Override
    public boolean supports(
            Map<String, Class<?>> in,
            Map<String, Class<?>> out
    ) {
        return in.size() == 1
                && out.size() == 1
                && inType.isAssignableFrom(in.get("in"))
                && out.get("out").isAssignableFrom(outType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public RuntimeNode<I, O> create(
            String id,
            Map<String, Channel<?>> inputs,
            Map<String, Channel<?>> outputs,
            Map<String, Object> config
    ) {
        Channel<I> in = (Channel<I>) inputs.get("in");
        Channel<O> out = (Channel<O>) outputs.get("out");

        Function<I, O> fn =
                (Function<I, O>) config.get("fn");

        return new MapNode<>(id, in, out, fn);
    }
}
