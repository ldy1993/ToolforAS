package com.ldy.function.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.function.Network.customHttp.jerry.CommEntityAction;
import com.ldy.function.Network.httpPost.httpPostAction;
import com.ldy.function.sign.Calcauthorization.CalcAuthorization;
import com.ldy.function.sign.SignUtil;
import com.ldy.function.sign.config.SwiftpassConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.ICommEventListener;
import me.jerry.framework.comm.NetException;
import okhttp3.OkHttpClient;

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

    public final static String SRnO_SERVER = "http://121.199.47.32:8080/SRnOWeb";
    public final static String SRnO_SERVER_TEST = "http://192.168.3.211:8080/SRnOWeb";
    public final static String SRnO_AUTH_SERVLET = "/authorization_posAuth.action";

    public  static void init(Context conTent)
    {
        context=conTent;
    }
    /**
     * 以json方式上送sn号和key给后台，
     * @param sn
     * @param listener
     */
    public  static void commVerification(final String sn, final NetComplateListener<String> listener)
    {
       new Thread(new Runnable() {
           @Override
           public void run() {

               try {
                   JSONObject jsonObject = new JSONObject();
                   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                   jsonObject.put("time", simpleDateFormat.format(new Date()));
                   jsonObject.put("sn", sn);
                   String preStr = jsonObject.toString();
                   String sign = SignUtil.getSign("RSA_1_256", preStr, SwiftpassConfig.mchPrivateKey);

                   jsonObject.put("sign",sign);
                   Log.e(TAG, "sign: "+ sign);
                   Log.e(TAG, "jsonObject: "+jsonObject.toString());
               httpPostAction.PostParams(jsonObject.toString(),SRnO_SERVER + SRnO_AUTH_SERVLET,20000,30000,listener);
//                   okhttpAction.PostParam(map,SRnO_SERVER_TEST + SRnO_AUTH_SERVLET,listener);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }
    public static interface NetComplateListener<T> {
        public void onNetComplate(T data);

        public void onNetError(int errorCode, String errorMessage);

        public void onWorkFlowError(String errorCode, String errorMessage);

        public void onPackError(int errorCode, String errorMessage);
    }

    /**
     * 耿浩框架，需要初始化一下通讯设备
     *
     */
    public  static  void initJerryComm()
    {
        // 初始化通讯管理器
        me.jerry.framework.comm.CommManager.getInstance().init(4, null, new ICommEventListener() {
            @Override
            public void onStart(CommEntity entity) {
            }

            @Override
            public void onError(NetException exception, CommEntity entity) {
                Log.e("可以输出的日志，校验人ldy：", "初始化通讯管理器错误");
            }

            @Override
            public void onEnd(CommEntity entity) {
                Log.e("可以输出的日志，校验人ldy：", "初始化通讯管理器成功");
            }
        });
    }
    /**
     * 以鍵值對方式上送
     * 以json方式上送sn号和key给后台
     * @param sn
     */
    public  static void commVerificationFromJerry(final String sn)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("value", "{\"sn\":\""+sn+"\",\"key\":\""+ CalcAuthorization.MD5(sn)+"\"}");
                    for (String s : params.keySet()) {
                        System.out.println("key:" + s);
                        System.out.println("values:" + params.get(s));
                    }
                    CommEntityAction.PostParams(params, SRnO_SERVER+SRnO_AUTH_SERVLET, new ICommEventListener() {
                        @Override
                        public void onStart(CommEntity arg0) {
                            Log.e("ldy","通讯开始");
                            NetProcessDialog.getInstance(context).show();
                        }

                        @Override
                        public void onError(NetException arg0, CommEntity arg1) {
                            // upload error
                            NetProcessDialog.getInstance(context).dismiss();
                            Log.e("ldy","通讯失败");
                            Toast.makeText(context,"通讯失败",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onEnd(CommEntity entity) {
                            NetProcessDialog.getInstance(context).dismiss();
                            Log.e("ldy","通讯成功:"+new String(entity.getResponseBean().body));
                            try
                            {
                                JSONObject rsp = JSON.parseObject(new String(entity.getResponseBean().body));
                                if(rsp.get("rspCode").equals("00"))
                                {
                                    Toast.makeText(context,"解锁成功",Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(context,"通讯失败，返回码为："+rsp.get("rspCode")+"\n返回描述为："+rsp.get("rspMsg"),Toast.LENGTH_LONG).show();
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                                return;
                            }


                        }
                    });
//                   okhttpAction.PostParam(map,SRnO_SERVER_TEST + SRnO_AUTH_SERVLET,listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
