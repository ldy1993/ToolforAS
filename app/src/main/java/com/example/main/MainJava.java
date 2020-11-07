package com.example.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.app.BaseApplication;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/10/14
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class MainJava {

    public static void test(Context context)
    {
        Intent intemt=  new Intent();
        context.startActivity(intemt);
          }
    public static void test(Activity activity)
    {
        Intent intemt=  new Intent();
        //成功
        activity.startActivity(intemt);
        //失败
        BaseApplication.mpp.startActivity(intemt);

        //成功
        activity.getSystemService("");
        //成功
        BaseApplication.mpp.getSystemService("");

    }
}
