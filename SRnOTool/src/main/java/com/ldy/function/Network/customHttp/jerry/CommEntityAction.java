package com.ldy.function.Network.customHttp.jerry;

import android.util.Log;

import java.util.Map;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.CommManager;
import me.jerry.framework.comm.ICommEventListener;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/3
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class CommEntityAction {
    public static void PostParams(Map<String, Object> params, String url, ICommEventListener listener) {
        me.jerry.framework.comm.CommEntity entity = new me.jerry.framework.comm.CommEntity();
        me.jerry.framework.comm.CommEntity.RequestBean requestBean = new me.jerry.framework.comm.CommEntity.RequestBean();
        try {
            requestBean.url = url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        requestBean.params = params;
        Log.i("Communicate", "url:" + requestBean.url);
        entity.setRequestBean(requestBean);
        entity.setRequestType(CommEntity.ERequestType.REQUEST_TYPE_POST);
        entity.setEventListener(listener);
        CommManager.getInstance().sendMessage(entity);
    }
}
