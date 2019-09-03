package com.ldy.function.Network.okhttp;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ldy.function.Network.CommManager;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/8/30
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class okhttpAction {
    /**
     * 上送文件和参数
     * @param file
     * @param params
     * @param url
     * @param listener
     */
    public static void PostFile(File file, Map<String, String> params, String url, final CommManager.NetComplateListener<String> listener) {
        /*****************************************/
//        //一种：参数请求体
        FormBody.Builder formBody = new FormBody.Builder();
        for (String s : params.keySet()) {
            System.out.println("key:" + s);
            System.out.println("values:" + params.get(s));
            formBody.add(s,  params.get(s));
        }

        FormBody paramsBody = formBody.build();
        //二种：文件请求体
        MediaType type = MediaType.parse("text/plain");//"text/xml;charset=utf-8"
        if(!file.exists()) {
            Log.e("ldy","文件不存在");
            listener.onNetError(-1, "日志文件不存在");
            return;
        }
        RequestBody fileBody = RequestBody.create(type, file);
        //三种：混合参数和文件请求

        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.ALTERNATIVE)
                //一样的效果
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"params\"")
                        , paramsBody)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"file\"; filename="+file.getName())
                        , fileBody) .build();


        final Request request = new Request.Builder().url(url)
                .addHeader("User-Agent", "android")
                .header("Content-Type", "text/html; charset=utf-8;")
                .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
                .build();

        /****************************************************/
//        //创建Request
        final OkHttpClient mOkHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        final Call call = mOkHttpClient.newBuilder().readTimeout(50, TimeUnit.SECONDS).connectTimeout(50, TimeUnit.SECONDS).writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
                listener.onNetError(-1, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();

                    try {
                        com.alibaba.fastjson.JSONObject returnData = JSON.parseObject(string);
                        Log.e("ldy","resultCode="+returnData.get("resultCode"));

                        if (returnData.get("resultCode").toString().equals("0")) {
                            listener.onNetComplate(returnData.get("resultMsg")+"");
                        } else {
                            Log.e("ldy","json="+returnData.toString());
                            listener.onWorkFlowError("-3",returnData.get("resultMsg")+"");
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        listener.onWorkFlowError("-4","未知异常");
                    }
                } else {
                    listener.onNetError(-2, response.message());
                }
            }
        });


    }
}
