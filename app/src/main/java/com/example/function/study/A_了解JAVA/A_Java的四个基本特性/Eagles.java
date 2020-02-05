package com.example.function.study.A_了解JAVA.A_Java的四个基本特性;

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
public class Eagles extends Bird {
    public String name="老鹰";
    /**
     *  重写
     */
    @Override
    public void eat() {
        Log.i("ldy","吃肉");
    }
    /**
     *  重载
     */
    public void eat(String type) {
        Log.i("ldy",name+"中的"+type+"吃腐肉");
    }
}
