package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

/**
 * 测试一些多线程下值的变化的工具库
 */
public class UtilTool {
    //不具备线程安全的值
    public static boolean flag;
    //不具备线程安全的方法
    public static int addOrSub(int arg1, int arg2)
    {
        if(flag)
        {
           return arg1-arg2;
        }else
        {
            return arg1+arg2;
        }
    }
}
