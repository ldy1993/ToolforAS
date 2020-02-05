package com.example.function.study.A_了解JAVA.B_jdk中的设计模式.A_设计过程中的六个基本原则.接口隔离原则ISP.不符合;

import com.ldy.function.Log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/2
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class Bird implements Behavior {
    @Override
    public void fly() {
        Log.e("ldy","鸟会飞");
    }

    @Override
    public void running() {
        Log.e("ldy","鸟会跑");
    }

    @Override
    public void wave() {

    }
}
