package com.ldy.function.Network;

import android.content.Context;
import android.util.Log;

import com.ldy.function.Network.httpPost.httpPostAction;
import com.ldy.function.sign.SignUtil;
import com.ldy.function.sign.SignUtils;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/5/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class CommManager {
    private static final String TAG = "CommManager";
    public static Context context;
    public  static void init(Context conTent)
    {
        context=conTent;
    }

    /**
     * 以xml方式上送sn号和key给后台
     * @param sn
     * @param url
     * @param sendTime
     * @param recvTime
     * @param listener
     */
    public  static void commVerification(final String sn, final String url, final int sendTime, final int recvTime, final NetComplateListener<String> listener)
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               SortedMap<String, String> map = new TreeMap();
               String sign_type ="RSA_1_256";
               map.put("sn",sn);
               map.put("time", String.valueOf(new Date().getTime()));
               map.put("sign_type",sign_type);
               Map<String, String> params = SignUtils.paraFilter(map);
               StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
               SignUtils.buildPayParams(buf, params, false);
               String preStr = buf.toString();
               Log.e(TAG, "计算内容："+preStr );
                String sign = SignUtil.getSign(sign_type, preStr);
               map.put("sign",sign);
                Log.e(TAG, "sign的值： "+sign );
               
               httpPostAction.PostParams(map,url,sendTime,recvTime,listener);
           }
       }).start();
    }
    public static interface NetComplateListener<T> {
        public void onNetComplate(T data);

        public void onNetError(int errorCode, String errorMessage);

        public void onWorkFlowError(String errorCode, String errorMessage);

        public void onPackError(int errorCode, String errorMessage);
    }

}
