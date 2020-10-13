package com.example.function.study.A_了解JAVA.H_java之注解.annotation.xml;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/13
 * 描    述：
 * 修订历史：
 * ================================================
 */

@XStreamAlias("LinearLayout")
class packXml {
    @XStreamAlias("TextView")
    private String arg1;
    @XStreamAlias("Button")
    private String arg2;

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String toXml() {
        return null;
    }
}
