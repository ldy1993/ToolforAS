package com.ldy.Utils;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/2/12
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class StringUtils {
    public StringUtils() {
    }

    public static String upTo_(String value) {
        String result = "";

        for (int i = 0; i < value.length(); ++i) {
            char ch = value.charAt(i);
            if (ch <= 'Z' && ch >= 'A') {
                if (i != 0) {
                    value = (new StringBuilder(value)).insert(i, "_").toString();
                }

                ++i;
            }
        }

        result = value.toLowerCase();
        return result;
    }

    public static String _ToUp(String value, boolean firstUp) {
        String[] ss = value.split("_");
        String result = "";
        boolean first = true;
        String[] var5 = ss;
        int var6 = ss.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            if (first && !firstUp) {
                result = s;
                first = false;
            } else {
                result = result + s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }

        return result;
    }
}
