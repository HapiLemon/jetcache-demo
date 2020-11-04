package com.ftl.demo.serialize;

import lombok.Data;

@Data
public class JsonCacheObject<V> {

    private String className;
    private V realObj;

    public JsonCacheObject(String className, V realObj) {
        this.className = className;
        this.realObj = realObj;
    }
}

