package com.ldy.function.compos.model;

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
public class Beans {
    private String type;
    private String beanSub;
    /**
     *  XML规则MAP
     */
    private Map<String,Bean> beanMap;
    /**
     * //存储数据MAP
     */
    private Map<String,BeanData> beanDataMap;


    public Map<String, BeanData> getBeanDataMap() {
        return beanDataMap;
    }

    public void setBeanDataMap(Map<String, BeanData> beanDataMap) {
        this.beanDataMap = beanDataMap;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getBeanSub() {
        return beanSub;
    }

    public void setBeanSub(String beanSub) {
        this.beanSub = beanSub;
    }

    public Map<String, Bean> getBeanMap() {
        return beanMap;
    }

    public void setBeanMap(Map<String, Bean> beanMap) {
        this.beanMap = beanMap;
    }

}
