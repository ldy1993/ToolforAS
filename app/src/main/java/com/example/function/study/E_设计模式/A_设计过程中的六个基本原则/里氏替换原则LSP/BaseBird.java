package com.example.function.study.E_设计模式.A_设计过程中的六个基本原则.里氏替换原则LSP;

import com.ldy.log.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class BaseBird {
    protected void fly()
    {
        Log.e("ldy","会飞");
    }
    protected void running()
    {
        Log.e("ldy","会跑");
    }
}
