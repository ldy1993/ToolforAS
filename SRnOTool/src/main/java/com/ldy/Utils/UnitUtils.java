package com.ldy.Utils;

import android.content.res.Resources;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/4/9
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class UnitUtils {
    public static double px2dp(double value) {
        return value / Resources.getSystem().getDisplayMetrics().density;
    }
    public static double dp2px(double value) {
        return value * Resources.getSystem().getDisplayMetrics().density;
    }
    private static long lastClickTime;
    public static boolean is50msClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 50) {

            return true;
        }
        lastClickTime = time;
        return false;
    }
    public static boolean is500msClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {

            return true;
        }
        lastClickTime = time;
        return false;
    }
}
