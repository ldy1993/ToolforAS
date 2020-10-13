package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/10
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class AThread extends Thread{
   public Listener listener;
    public AThread(Listener listener) {
        this.listener=listener;
    }

    @Override
    public void run() {
        super.run();
        //网络通信
        listener.setData("我是A线程");
    }
}
