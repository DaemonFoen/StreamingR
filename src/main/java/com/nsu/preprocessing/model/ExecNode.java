package com.nsu.preprocessing.model;

import java.util.*;

public class ExecNode {
    public final String id;
    public final String kind;
    public final Map<String, Port> inputs = new HashMap<>();
    public final Map<String, Port> outputs = new HashMap<>();
    public final Map<String, Object> config;
    public final int parallelism;

    public ExecNode(String id, OperatorDef def) {
        this.id = id;
        this.kind = def.kind;
        this.config = def.config != null ? def.config : Map.of();
        this.parallelism = def.parallelism != null ? def.parallelism : 1;

        if (def.inputs != null) {
            def.inputs.forEach((k, v) ->
                    inputs.put(k, new Port(k, v.type))
            );
        }
        if (def.outputs != null) {
            def.outputs.forEach((k, v) ->
                    outputs.put(k, new Port(k, v.type))
            );
        }
    }
}

