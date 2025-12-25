package com.nsu.preprocessing.model;

import java.util.*;

public class ExecutionGraph {
    public final Map<String, ExecNode> nodes = new HashMap<>();
    public final List<ExecEdge> edges = new ArrayList<>();
}
