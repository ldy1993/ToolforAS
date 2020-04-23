package com.ldy.function.compos.model;

import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/7/24
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Bean {
    private String id;
    private String name;
    private List<Property> propertyList;
    private Map<String, ConstructorArg> constructorArgMap;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }


    public Map<String, ConstructorArg> getConstructorArgMap() {
        return constructorArgMap;
    }

    public void setConstructorArgMap(Map<String, ConstructorArg> constructorArgMap) {
        this.constructorArgMap = constructorArgMap;
    }

}
