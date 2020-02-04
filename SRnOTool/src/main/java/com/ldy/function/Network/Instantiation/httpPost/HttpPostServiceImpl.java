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
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

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
public class HttpPostServiceImpl implements ICommunicationManager {
    private String url;
    private String data;
    private int connectTimeout;
    private int requestTimeout;
    public void execute(String data, String url, int connectTimeout, int requestTimeout, final NetComplateListener listener)
    {
        initData(null,null,data);
        initComManager(url,connectTimeout,requestTimeout);
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
        this.connectTimeout=connectTimeout;
        this.requestTimeout=requestTimeout;
    }
    @Override
    public void post(final NetComplateListener listener) {
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
                        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
                        //读取超时
                        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, requestTimeout);
                        response = httpclient.execute(httpPost);
                        if (response != null && response.getEntity() != null) {
                         HttpEntity entity = response.getEntity();
                              BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(entity.getContent()));
                            String result = reader.readLine();
                         try {
                                JSONObject returnData = JSON.parseObject(result);

                                if ("00".equals(returnData.get("rspCode").toString())) {

                                    listener.onNetComplate(result);
                                } else {
                                    Log.e("ldy", "json=" + returnData.toString());
                                    listener.onWorkFlowError(returnData.get("rspCode").toString(), result);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                listener.onNetError(-4, "未知异常");
                            }
                        } else {
                            res = "操作失败!";
                            listener.onPackError(-2, res);
                        }
                    } catch (HttpHostConnectException e) {
                        e.printStackTrace();
                        res = "服务器连接异常";
                        listener.onWorkFlowError("-3", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                        res = "操作失败";
                        listener.onWorkFlowError("-3", res);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

    }



}
