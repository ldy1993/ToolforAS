package com.ldy.function.Network.httpPost;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ldy.function.Network.CommManager;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


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
    public static void PostParams(String data ,String url ,int connecttimeout,int requesttimeout, final CommManager.NetComplateListener<String> listener) {
        {

            try {

                Log.e("ldy", "reqUrl:" + url);

                HttpResponse response = null;
                HttpClient httpclient = null;
                String res;
                try {
                    HttpPost httpPost = new HttpPost(url);
                    //NameValuePair对象代表了一个需要发往服务器的键值对
                    NameValuePair pair1 = new BasicNameValuePair("value", data);
                    //将准备好的键值对对象放置在一个List当中
                    ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
                    pairs.add(pair1);
                    //创建代表请求体的对象（注意，是请求体）
                    HttpEntity requestEntity = new UrlEncodedFormEntity(pairs);
//                        HttpEntity requestEntity = new UrlEncodedFormEntity(requestEntity);
                    //将请求体放置在请求对象当中
                    httpPost.setEntity(requestEntity);
//                    StringEntity entityParams = new StringEntity(pairs);
//                    httpPost.setEntity(entityParams);

                    httpPost.setHeader("Content-Type", "text/html;charset=UTF-8");
                    httpclient = new DefaultHttpClient();
                    //请求超时
                    httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connecttimeout);
                    //读取超时
                    httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, requesttimeout);
                    response = httpclient.execute(httpPost);
                    if (response != null && response.getEntity() != null) {
                        Log.e("ldy", "收到返回: " );
                        HttpEntity entity = response.getEntity();
//                        Log.e("ldy", "收到返回: " +  EntityUtils.toByteArray(entity));
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(entity.getContent()));
                        String result = reader.readLine();
                        Log.e("ldy", "PostParams: "+ result );
                        try {
                            JSONObject returnData = JSON.parseObject(result);
                            Log.e("ldy","rspCode="+returnData.get("rspCode"));

                            if (returnData.get("rspCode").toString().equals("00")) {
                                listener.onNetComplate(returnData.get("rspMsg")+"");
                            } else {
                                Log.e("ldy","json="+returnData.toString());
                                listener.onWorkFlowError(returnData.get("rspCode").toString(),returnData.get("rspMsg")+"");
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            listener.onWorkFlowError("-4","未知异常");
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
