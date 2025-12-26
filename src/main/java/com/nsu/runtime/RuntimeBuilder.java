package com.nsu.runtime;

import com.nsu.preprocessing.model.ExecEdge;
import com.nsu.preprocessing.model.ExecNode;
import com.nsu.preprocessing.model.ExecutionGraph;
import com.nsu.runtime.model.Channel;
import com.nsu.runtime.model.node.RuntimeNode;
import java.util.*;

public final class RuntimeBuilder {

    public static RuntimeGraph build(
            ExecutionGraph graph,
            Map<String, Object> implementations
    ) {
        RuntimeGraph runtime = new RuntimeGraph();

        Map<String, Channel<?>> channels = new HashMap<>();

        // создаём каналы
        for (ExecEdge e : graph.edges) {
            String key = edgeKey(e);
            channels.put(key, new Channel<>());
        }

        // создаём узлы
        for (ExecNode n : graph.nodes.values()) {

            Map<String,Channel<?>> ins = new HashMap<>();
            Map<String, Channel<?>> outs = new HashMap<>();

            for (ExecEdge e : graph.edges) {
                if (e.toNode == n) {
                    ins.put(
                            e.toPort.name,
                            channels.get(edgeKey(e))
                    );
                }
                if (e.fromNode == n) {
                    outs.put(
                            e.fromPort.name,
                            channels.get(edgeKey(e))
                    );
                }
            }

            RuntimeNode node = (RuntimeNode) implementations.get(n.id);
            runtime.addNode(node);
        }

        return runtime;
    }

    private static String edgeKey(ExecEdge e) {
        return e.fromNode.id + "." + e.fromPort.name +
                "->" +
                e.toNode.id + "." + e.toPort.name;
    }
}
