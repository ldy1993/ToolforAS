package com.example.function.study.了解JAVA.设计过程中的六个基本原则.接口隔离原则ISP.不符合;

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
public class Person implements Behavior {
    @Override
    public void fly(){
    }

    @Override
    public void running() {
        Log.e("ldy","人会跑");
    }

    @Override
    public void wave() {
        Log.e("ldy","人会跑");
    }
}
