package com.ldy.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldy.function.Network.HttpClientTool;
import com.ldy.function.Network.service.NetComplateListener;
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
 * 描    述：该类为授权码通讯实现类，调用该类的方法，可以和后台SRnOWEB进行通讯
 * 修订历史：
 * ================================================
 */
public class AuthorizationAction {
    private static final String TAG = "AuthorizationAction";
    public final static String SRNO_SERVER = "http://121.199.47.32:8080/SRnOWeb";
    public final static String SRNO_SERVER_TEST = "http://192.168.3.211:8080/SRnOWeb";
    public final static String SRNO_AUTH_SERVLET = "/authorization_posAuth.action";

     private static SignServiceImpl signServiceImpl = new SignServiceImpl();

    /**
     * 以json方式上送sn号和RSA给后台，
     *
     * @param sn
     * @param listener
     */
    public static void commVerification(final String sn, final NetComplateListener listener) {
        try {
            JSONObject jsonObject = new JSONObject();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            jsonObject.put("time", simpleDateFormat.format(new Date()));
            jsonObject.put("sn", sn);
            String preStr = jsonObject.toString();
            String sign = signServiceImpl.getSign("RSA_1_256", preStr);
            jsonObject.put("sign", sign);
            Map<String, String> headerMap = new HashMap<>();
            Map<String, String> bodyMap = new HashMap<>();
            headerMap.put("Content-Type", "text/html;charset=UTF-8");
            bodyMap.put("data", jsonObject.toJSONString());
            String data =HttpClientTool.doPOST(SRNO_SERVER + SRNO_AUTH_SERVLET, headerMap, bodyMap);
            try {
                //这里处理返回业务逻辑
                JSONObject returnData = JSON.parseObject(data);

                if ("00".equals(returnData.get("rspCode").toString())) {
                    String sign1 = (String) returnData.get("sign");
                    String time = (String) returnData.get("time");
                    String rspCode = (String) returnData.get("rspCode");
                    String rspMsg = (String) returnData.get("rspMsg");
                    if (sign1 == null || sign1.isEmpty()) {
                        System.out.println("签名数据为空");
                        listener.onWorkFlowError("-6", "签名数据为空");
                    } else {
                        JSONObject preStr1 = new JSONObject();
                        preStr1.put("rspCode", rspCode);
                        preStr1.put("rspMsg", rspMsg);
                        preStr1.put("sn", sn);
                        preStr1.put("time", time);
                        if (signServiceImpl.verifySign(preStr1.toString(), sign1, "RSA_1_256")) {
                            listener.onNetComplate(returnData.get("rspMsg") + "");
                        } else {
                            listener.onWorkFlowError("-8", "签名错");
                        }
                    }
                } else {
                    listener.onWorkFlowError(returnData.get("rspCode").toString(), returnData.get("rspMsg") + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                listener.onWorkFlowError("-7", "终端发送数据异常");
            }

        } catch (Exception e) {
            e.printStackTrace();
            listener.onWorkFlowError("-7", "终端处理收到数据异常");
        }
    }


}
