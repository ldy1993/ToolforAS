package com.ldy.function.sign.Calcauthorization;

import android.util.Log;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/5/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class md5new {
    public static String Calc(String sn, int num) {
        try {
            String md5 = MD5(sn);
            Log.e("ldy", "md5==" + md5);
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(md5);
            Log.e("ldy", "m.group()==" + m.replaceAll("").trim().substring(0, 4));
            return m.replaceAll("").trim().substring(0, num);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
//将字符串转换成字符串数组
        char[] pswdArray = str.toCharArray();
        byte[] pswdByte = new byte[pswdArray.length];
//将字符转换成字节
        for (int i = 0; i < pswdArray.length; i++) {
            pswdByte[i] = (byte) pswdArray[i];
        }
        byte[] digest = md5.digest(pswdByte);
//将得到的字节数组转换成十六进制数
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            int num = ((int) digest[i]) & 0xff;
            //如果不足16，加0填充
            if (num < 16) {
                buff.append("0");
            }
            buff.append(Integer.toHexString(num));
        }
        return buff.toString();
    }
}
