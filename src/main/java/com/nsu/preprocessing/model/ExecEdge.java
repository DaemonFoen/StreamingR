package com.nsu.preprocessing.model;

public class ExecEdge {
    public final ExecNode fromNode;
    public final Port fromPort;
    public final ExecNode toNode;
    public final Port toPort;
    public final boolean isLoop;

    public ExecEdge(
            ExecNode fromNode,
            Port fromPort,
            ExecNode toNode,
            Port toPort,
            boolean isLoop
    ) {
        this.fromNode = fromNode;
        this.fromPort = fromPort;
        this.toNode = toNode;
        this.toPort = toPort;
        this.isLoop = isLoop;
    }
}
