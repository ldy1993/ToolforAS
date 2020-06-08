package com.example.function.study.A_了解JAVA.B_jdk中的设计模式.A_设计过程中的六个基本原则.单一职责原则SRP.方法级别不符合SRP;

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
public class NetworkMethod {
    public void getParam()
    {
        Log.e("ldy","以get方式上送param");
    }
    public void postParam(){
        Log.e("ldy","以post方式上送param");
    }
    public void postFile(){
        Log.e("ldy","以post方式上送file");
    }
}
