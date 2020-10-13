package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

import android.os.Handler;
import android.os.Message;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/10
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class TestThread {

    public void testHandler()
    {
        System.out.println("开始testHandler");
      Handler handler=  new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("handleMessage");
                System.out.println(""+msg.obj);
            }
        };
        new BThread(handler).start();
    }
}
