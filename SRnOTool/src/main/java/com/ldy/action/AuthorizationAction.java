package com.ldy.action;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldy.function.Network.Instantiation.httpPost.httpPostServiceImpl;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.function.Network.Instantiation.customHttp.jerry.commEntityServiceImpl;
import com.ldy.function.sign.service.SignServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/5
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class AuthorizationAction {
    private static final String TAG = "AuthorizationAction";
    public final static String SRnO_SERVER = "http://121.199.47.32:8080/SRnOWeb";
    public final static String SRnO_SERVER_TEST = "http://192.168.3.211:8080/SRnOWeb";
    public final static String SRnO_AUTH_SERVLET = "/authorization_posAuth.action";

    private static httpPostServiceImpl httpPostServiceImpl=new httpPostServiceImpl();
    private static commEntityServiceImpl commEntityServiceImpl=new commEntityServiceImpl();
    private static SignServiceImpl signServiceImpl=new SignServiceImpl();

    /**
     * 以json方式上送sn号和RSA给后台，
     * @param sn
     * @param listener
     */
    public  static void commVerification(final String sn, final NetComplateListener listener)
    {
                try {
                    JSONObject jsonObject = new JSONObject();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    jsonObject.put("time", simpleDateFormat.format(new Date()));
                    jsonObject.put("sn", sn);
                    String preStr = jsonObject.toString();
                    String sign = signServiceImpl.getSign("RSA_1_256", preStr);
                    jsonObject.put("sign",sign);
                    httpPostServiceImpl.PostString(jsonObject.toString(),
                            SRnO_SERVER + SRnO_AUTH_SERVLET,
                            20000, 30000,
                            new NetComplateListener() {
                                @Override
                                public void onNetComplate(String data) {
                                    try {
                                        //这里处理返回业务逻辑
                                        JSONObject returnData = JSON.parseObject(data);

                                        if (returnData.get("rspCode").toString().equals("00")) {
                                            String sign=(String) returnData.get("sign");
                                            String time=(String) returnData.get("time");
                                            String rspCode=(String) returnData.get("rspCode");
                                            String rspMsg=(String) returnData.get("rspMsg");
                                            if(sign==null||sign.isEmpty())
                                            {
                                                System.out.println("签名数据为空");
                                                listener.onWorkFlowError("-6","签名数据为空");
                                            }
                                            else {
                                                JSONObject preStr=new JSONObject();
                                                preStr.put("rspCode", rspCode);
                                                preStr.put("rspMsg", rspMsg);
                                                preStr.put("sn", sn);
                                                preStr.put("time", time);
                                                if(signServiceImpl.verifySign(preStr.toString(),sign,"RSA_1_256"))
                                                {
                                                    listener.onNetComplate(returnData.get("rspMsg") + "");
                                                }
                                                else
                                                {
                                                    listener.onWorkFlowError("-8","签名错");
                                                }
                                            }
                                        } else {
                                           listener.onWorkFlowError(returnData.get("rspCode").toString(), returnData.get("rspMsg") + "");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        listener.onWorkFlowError("-7", "终端处理收到数据异常");
                                    }
                                }

                                @Override
                                public void onNetError(int errorCode, String errorMessage) {
                                    listener.onNetError(errorCode,errorMessage);
                                }

                                @Override
                                public void onWorkFlowError(String errorCode, String errorMessage) {
                                    listener.onWorkFlowError(errorCode,errorMessage);
                                }

                                @Override
                                public void onPackError(int errorCode, String errorMessage) {
                                    listener.onPackError(errorCode,errorMessage);
                                }
                            });
               } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    /**
     * 耿浩的框架需要先初始化
     */
    public static void initJerryComm()
    {
        commEntityServiceImpl.init();
    }
    /**
     * 以鍵值對方式上送
     * 以json方式上送sn号和key给后台
     * @param sn
     */
    public  static void commVerificationFromJerry(final String sn,final NetComplateListener listener) {


        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("value", "{\"sn\":\"" + sn + "\",\"key\":\"" + com.ldy.function.sign.Calcauthorization.md5new.MD5(sn) + "\"}");
            for (String s : params.keySet()) {
                System.out.println("key:" + s);
                System.out.println("values:" + params.get(s));
            }
            commEntityServiceImpl.PostParamsOrFile(null,params, SRnO_SERVER + SRnO_AUTH_SERVLET, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
