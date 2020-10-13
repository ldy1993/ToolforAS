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
public class BThread extends Thread{
    public Handler handler;
    public BThread(Handler handler) {
        this.handler=handler;
    }

    @Override
    public void run() {
        super.run();
        //网络通信
        for (int j = 0; j < 100; j++) {
            Message msg=new Message();
            msg.obj="我是B线程"+j;
            handler.sendMessage(msg);
        }
    }
}
