package com.nsu.preprocessing.model;

import java.util.*;

public class GraphDefinition {
    public Map<String, TypeDef> types;
    public Map<String, OperatorDef> operators;
    public List<EdgeDef> edges;
    public ControlDef control;
}
