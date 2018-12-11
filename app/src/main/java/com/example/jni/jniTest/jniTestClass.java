package com.example.jni.jniTest;
public class jniTestClass {
    static {
        System.loadLibrary("native-lib");
//        System.loadLibrary("jniTestClass");
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();

    public static native String getName();
}
