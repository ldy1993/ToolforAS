package com.github.ldy.httpserver.comm;

import android.app.Activity;
import android.util.Log;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.ICommEventListener;
import me.jerry.framework.comm.NetException;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/5/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class CommManager {
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
    public  static boolean commVerification(String sn,final Activity activity,ICommEventListener listener)
    {
        init();
        Communicate.getProvinces(sn,activity,listener);
        return false;
    }
}
