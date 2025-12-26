package com.nsu.preprocessing.model;

import java.util.Map;

public class OperatorDef {
    public String kind;
    public Map<String, PortDef> inputs;
    public Map<String, PortDef> outputs;
    public Map<String, Object> config;
    public Integer parallelism;
}

