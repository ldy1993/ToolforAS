package com.example.function.comprehensive.日志收集和网络上送;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.function.comprehensive.FilesSizeUtils;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.ldy.ThreadPool.ThreadPoolManager;
import com.ldy.view.CustomWidget.TimePicker.TimeUtils;
import com.ldy.view.dialog.NetProcessDialog;
import com.ldy.log.LogcatHelper;
import com.ldy.study.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum.有趣功能主菜单;
import static com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum.日志上传;


@MenuActivity(menu = 有趣功能主菜单, sonMenu = 日志上传)
public class UpLogActivity extends Activity implements ConstantData {
    private MyHandler mHandler;
    //开启一个线程，在里面进行handler.post或者handler.sendMessage(msg);
    public static Executor executor = ThreadPoolManager.newSingleThreadPool();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_log);
        mHandler = new MyHandler(this);
        //还需要申请权限。
        Log.e("ldy", "开启日志服务");
        LogcatHelper.getInstance(UpLogActivity.this).start();
        new NetProcessDialog(UpLogActivity.this, "准备上送日志");
    }

    public void upLogBt(View view) {
        TimeUtils.timePickerAlertDialog(null, UpLogActivity.this
                , CancelListener, ComfirmListener);
    }

    private View.OnClickListener CancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Message msg = mHandler.obtainMessage();
            msg.what = FINISH;
            msg.obj = "取消选择时间";
            mHandler.sendMessage(msg);
        }
    };
    private View.OnClickListener ComfirmListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String Time = TimeUtils.getTxtTime("", "", "", "", "", "");
            Log.e("ldy", "Time=" + Time);
            File file = LogcatHelper.chooseFile(Time);
            if (file == null) {
                Message msg = mHandler.obtainMessage();
                msg.what = FINISH;
                msg.obj = "没有找到对应log";
                mHandler.sendMessage(msg);
            } else {
                Message msg = mHandler.obtainMessage();
                msg.obj = file;
                msg.what = OBTAIN_LOG;
                mHandler.sendMessage(msg);
            }
        }
    };

}
