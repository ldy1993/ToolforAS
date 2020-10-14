package com.example.function.study.A_了解JAVA.H_java之注解.annotation.xml;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/14
 * 描    述：
 * 修订历史：
 * ================================================
 */
@XStreamRoot
class PackBodyXml {
    @XStreamAlias("EditView")
    private String arg;

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }
}
