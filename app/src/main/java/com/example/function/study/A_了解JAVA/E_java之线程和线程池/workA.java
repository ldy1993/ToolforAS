package com.example.function.study.A_了解JAVA.E_java之线程和线程池;

import android.util.Log;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/10
 * 描    述：
 * 修订历史：
 * ================================================
 */
class workA extends work{
    private static final String TAG = "workA";

    private void workA() {
        //A线程执行
        synchronized (obj) {
                try {
                    obj.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            Log.d(TAG, "workA: 完成");
        }

    }
}
