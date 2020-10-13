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
class workB extends work{
    private static final String TAG = "workB";
    private void workB() {
        //B线程执行
        //            obj.notify();
        synchronized (obj) {
            Log.d(TAG, "workB: 完成");
        }

    }
}
