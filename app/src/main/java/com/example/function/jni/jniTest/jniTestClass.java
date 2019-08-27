package com.example.function.jni.jniTest;
public class jniTestClass {
    static {
        System.loadLibrary("native-lib");
//        System.loadLibrary("jniTestClass");
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     * 一个Native Method就是一个java调用非java代码的接口。一个Native Method是这样一个java的方法：该方法的实现由非java语言实现，比如C。这个特征并非java所特有，很多其它的编程语言都有这一机制，比如在C＋＋中，你可以用extern "C"告知C＋＋编译器去调用一个C的函数。
     */
    public static native String stringFromJNI();

    /**
     * 堆排序
     * @param data
     * @return
     */
    public static native int[] heapsort(int[] data);
}
