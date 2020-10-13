package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/12
 * 描    述：饿汉式的单例
 * 修订历史：
 * ================================================
 */
class Singleton {
    private volatile Singleton ourInstance;

    public  synchronized Singleton getInstance() {
        return new Singleton();
    }

    public  Singleton getInstance1() {
        if (ourInstance == null) {
            synchronized (Singleton.class) {
                if (ourInstance == null) {
                    //1、分配一个内存空间
                    //2、初始化一个对象。
                    //3、把分配好的内存空间地址赋值给对象
                    ourInstance= new Singleton();
                }
            }
        }
        return ourInstance;
    }

    private Singleton() {
    }
}
