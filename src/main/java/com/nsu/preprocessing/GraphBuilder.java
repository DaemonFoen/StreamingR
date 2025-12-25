package com.nsu.preprocessing;

import com.nsu.preprocessing.model.EdgeDef;
import com.nsu.preprocessing.model.ExecEdge;
import com.nsu.preprocessing.model.ExecNode;
import com.nsu.preprocessing.model.ExecutionGraph;
import com.nsu.preprocessing.model.GraphDefinition;
import com.nsu.preprocessing.model.Port;
import com.nsu.preprocessing.model.TypeDef;
import java.util.*;

public class GraphBuilder {

    public static ExecutionGraph build(GraphDefinition def) {
        ExecutionGraph graph = new ExecutionGraph();

        def.operators.forEach((id, opDef) -> {
            graph.nodes.put(id, new ExecNode(id, opDef));
        });

        for (EdgeDef e : def.edges) {
            String[] from = e.from.split("\\.");
            String[] to = e.to.split("\\.");

            ExecNode fromNode = graph.nodes.get(from[0]);
            ExecNode toNode = graph.nodes.get(to[0]);

            if (fromNode == null || toNode == null) {
                throw new IllegalStateException("Неизвестный узел в ребре");
            }

            Port outPort = fromNode.outputs.get(from[1]);
            Port inPort = toNode.inputs.get(to[1]);

            if (outPort == null || inPort == null) {
                throw new IllegalStateException("Неизвестный порт");
            }

            // 3. Проверка типов
            if (!isTypeCompatible(outPort.type, inPort.type, def.types)) {
                throw new IllegalStateException(
                        "Несовместимые типы: " + outPort.type + " -> " + inPort.type
                );
            }

            graph.edges.add(new ExecEdge(
                    fromNode,
                    outPort,
                    toNode,
                    inPort,
                    "loop".equals(e.control)
            ));
        }

        return graph;
    }

    private static boolean isTypeCompatible(
            String from,
            String to,
            Map<String, TypeDef> types
    ) {
        if (from.equals(to)) {
            return true;
        }

        TypeDef t = types.get(to);
        while (t != null && t.extendsType != null) {
            if (t.extendsType.equals(from)) {
                return true;
            }
            t = types.get(t.extendsType);
        }
        return false;
    }
}
