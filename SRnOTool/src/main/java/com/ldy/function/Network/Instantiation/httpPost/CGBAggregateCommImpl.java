package com.ldy.function.Network.Instantiation.httpPost;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldy.function.Network.service.ICommunicationManager;
import com.ldy.function.Network.service.NetComplateListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
public class CGBAggregateCommImpl implements ICommunicationManager {

    @Override
    public void postString(final String data, final String url, final int connectTimeout, final int requestTimeout, final NetComplateListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient client = null;
                    client= new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json; charset=utf-8");
                    StringEntity entity = new StringEntity(data);
                    httpPost.setEntity(entity);
                    HttpClientContext context = HttpClientContext.create();
                    HttpResponse response = client.execute(httpPost, context);
                    HttpEntity entityResult = response.getEntity();
                    String result = EntityUtils.toString(entityResult);
//                    Header[] aa = response.getHeaders("X-Auth-Token"); //session key
//                    String token = aa[0].getValue();//必须获取握手后的session
//                    JSONObject j = JSONObject.parseObject(result);
//                    result = j.getString("data");
//                    System.out.println("resul_keys=" + result);
//                    result = result.substring(result.indexOf(":OYANG:") + 7, result.indexOf(":OYANG:") + 23);
//                    System.out.println("resul_key=" + result);
                    Log.e("result", result);
                    listener.onNetComplate(result);
//                    Log.e("token", token);
//                    bb[0] = result;  // 握手成功之后的key
//                    bb[1] = token;   //返回的session
                } catch (Exception e) {
                    //响应超时
                    e.printStackTrace();
                    listener.onNetError(-1,e.getMessage());
                } finally {

                }
            }
        }).start();
    }

    @Override
    public void postParamsOrFile(File file, Map<String, Object> params, String url, NetComplateListener listener) {

    }


}
