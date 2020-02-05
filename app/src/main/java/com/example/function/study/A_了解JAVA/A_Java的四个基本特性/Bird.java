package com.example.function.study.A_了解JAVA.A_Java的四个基本特性;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/2
 * 描    述：鸟类的抽象类
 * 修订历史：
 * ================================================
 */
public abstract class Bird {
    /**
     * 鸟类翅膀数量，数据抽象；
     */
    public int windNub=2;
    /**
     * 鸟类吃方法，行为抽象；
     */
    public abstract void eat();
}
