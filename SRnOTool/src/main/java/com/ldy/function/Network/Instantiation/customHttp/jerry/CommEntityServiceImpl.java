package com.ldy.function.Network.Instantiation.customHttp.jerry;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ldy.function.Network.service.CommunicationHttpService;
import com.ldy.function.Network.service.NetComplateListener;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.CommManager;
import me.jerry.framework.comm.ICommEventListener;
import me.jerry.framework.comm.NetException;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/9/5
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class CommEntityServiceImpl implements CommunicationHttpService {
    /**
     * 耿浩框架，需要初始化一下通讯设备
     *
     */
    public  static  void init()
    {
        // 初始化通讯管理器
        me.jerry.framework.comm.CommManager.getInstance().init(4, null, new ICommEventListener() {
            @Override
            public void onStart(CommEntity entity) {
            }

            @Override
            public void onError(NetException exception, CommEntity entity) {
                Log.e("可以输出的日志，校验人ldy：", "初始化通讯管理器错误");
            }

            @Override
            public void onEnd(CommEntity entity) {
                Log.e("可以输出的日志，校验人ldy：", "初始化通讯管理器成功");
            }
        });
    }

    @Override
    public void PostString(String data, String url, int connecttimeout, int requesttimeout, NetComplateListener listener) {

    }

    @Override
    public void PostParamsOrFile(File file, Map<String, Object> params, String url, final NetComplateListener listener) {
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
        entity.setEventListener(new ICommEventListener() {
            @Override
            public void onStart(CommEntity commEntity) {
                Log.e("ldy", "通讯开始");

            }

            @Override
            public void onEnd(CommEntity commEntity) {
                listener.onNetComplate( new String(commEntity.getResponseBean().body));
            }

            @Override
            public void onError(NetException e, CommEntity commEntity) {

                listener.onNetError(e.getErrCode(),e.getMessage());
                Log.e("ldy", "通讯失败");
            }
        });
        CommManager.getInstance().sendMessage(entity);
    }




}
