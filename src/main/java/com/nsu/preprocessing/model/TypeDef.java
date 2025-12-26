package com.nsu.preprocessing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class TypeDef {
    @JsonProperty("extends")
    public String extendsType;
    public String javaType;
    public Map<String, String> fields;
}
