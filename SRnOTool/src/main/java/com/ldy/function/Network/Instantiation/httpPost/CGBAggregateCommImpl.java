package com.ldy.function.Network.Instantiation.httpPost;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldy.function.Network.service.ICommunicationManager;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.function.ThreadPool.ThreadPoolManager;

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
import java.util.concurrent.ExecutorService;

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


    private String url;
    private String data;
    public void execute(String data, String url, final NetComplateListener listener)
    {
        initData(null,null,data);
        initComManager(url,0,0);
        ExecutorService executorService = ThreadPoolManager.newSingleThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                post(listener);
            }
        });
    }
    @Override
    public void initData(File file, Map<String, Object> params, String data) {
        this.data=data;
    }

    @Override
    public void initComManager(String url, int connectTimeout, int requestTimeout) {
        this.url=url;
    }

    @Override
    public void post(NetComplateListener listener) {
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
}
