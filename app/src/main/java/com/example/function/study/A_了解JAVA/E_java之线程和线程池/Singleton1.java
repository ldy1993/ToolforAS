package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/12
 * 描    述：饱汉式单例
 * 修订历史：
 * ================================================
 */
class Singleton1 {
    private static final Singleton1 ourInstance = new Singleton1();

   public static Singleton1 getInstance() {
        return ourInstance;
    }

    private Singleton1() {
    }
}
