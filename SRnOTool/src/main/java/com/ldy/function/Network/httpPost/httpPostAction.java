package com.ldy.function.Network.httpPost;

import android.util.Log;
import com.ldy.function.Network.CommManager;
import com.ldy.function.sign.SignUtil;
import com.ldy.function.sign.XmlUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;


/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/8/30
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class httpPostAction {
    public static void PostParams(SortedMap<String, String> map ,String url ,int connecttimeout,int requesttimeout, final CommManager.NetComplateListener<String> listener) {
        {

            try {

                Log.e("ldy", "reqUrl:" + url);

                HttpResponse response = null;
                HttpClient httpclient = null;
                String res;
                try {
                    HttpPost httpPost = new HttpPost(url);
                    StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
                    httpPost.setEntity(entityParams);

                    httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
                     httpclient = new DefaultHttpClient();

                    //请求超时
                    httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connecttimeout);
                    //读取超时
                    httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, requesttimeout);
                    response = httpclient.execute(httpPost);
                    if (response != null && response.getEntity() != null) {
                        Map<String, String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                        res = XmlUtils.toXml(resultMap);

                        System.out.println("请求结果：" + res);
                        String reSign = resultMap.get("sign");
                        String sign_type = resultMap.get("sign_type");
                        System.out.println("签名方式" + sign_type);
                        if (resultMap.containsKey("sign") && !SignUtil.verifySign(reSign, sign_type, resultMap)) {
                            res = "验证签名不通过";
                            listener.onNetError(-1, res);
                        } else {
                            String reCode = resultMap.get("code");
                            String reMsg = resultMap.get("msg");
                            if (!reCode.equals("00")) {
                                listener.onNetError(Integer.parseInt(reCode), reMsg);
                            } else {
                                listener.onNetComplate(reMsg);
                            }
                        }
                    } else {
                        res = "操作失败!";
                        listener.onPackError(-2, res);
                    }
                }catch (HttpHostConnectException e)
                {
                    e.printStackTrace();
                    res = "服务器连接异常";
                    listener.onWorkFlowError("-3", res);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    res = "操作失败";
                    listener.onWorkFlowError("-3", res);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
