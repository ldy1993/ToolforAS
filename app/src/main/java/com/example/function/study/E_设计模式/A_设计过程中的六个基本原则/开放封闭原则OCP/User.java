package com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.开放封闭原则OCP;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/5
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class User {
    private String name;
    private String pwd;
    private String account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
