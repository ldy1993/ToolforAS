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
}
