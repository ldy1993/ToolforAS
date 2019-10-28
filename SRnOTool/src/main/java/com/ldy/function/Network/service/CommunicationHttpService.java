package com.ldy.function.Network.service;
import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/5
 * 描    述：网络通讯接口，分别是
 * 1、post 键值对参数或文件
 * 2、post String
 * 修订历史：
 * ================================================
 */
public interface CommunicationHttpService {
    void PostString(String data, String url, int connecttimeout, int requesttimeout, final NetComplateListener listener) ;

    void PostParamsOrFile(File file, Map<String, Object> params, String url, final NetComplateListener listener) ;

    }
