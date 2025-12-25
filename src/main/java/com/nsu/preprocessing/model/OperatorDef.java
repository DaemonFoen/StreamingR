package com.nsu.preprocessing.model;

import java.util.Map;

public class OperatorDef {
    public String kind;
    public Map<String, String> inputs;
    public Map<String, String> outputs;
    public Map<String, Object> config;
    public Integer parallelism;
}
