package com.ldy.function.Network.service;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/5
 * 描    述：通讯接听器接口类。
 * 修订历史：
 * ================================================
 */
public interface  NetComplateListener {
    public void onNetComplate(String data);

    public void onNetError(int errorCode, String errorMessage);

    public void onWorkFlowError(String errorCode, String errorMessage);

    public void onPackError(int errorCode, String errorMessage);
}
