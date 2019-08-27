package com.example.function.study;

import java.util.Map;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/8/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Transdata {
    public String getName() {
        return name;
    }

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String value;
    private Map<String,String> stringMap;
}
