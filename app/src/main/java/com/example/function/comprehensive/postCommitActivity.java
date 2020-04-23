package com.example.function.comprehensive;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ldy.View.CustomWidget.TimePicker.TimeUtils;
import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.function.Log.LogcatHelper;
import com.ldy.function.Network.Instantiation.okhttp.OkHttpMultipartBodyImpl;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.study.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class postCommitActivity extends Activity {
    private Handler handler;
    private static Context context;
    private static double fileSize=0;
    public final static String SRNO_SERVER = "http://180.141.91.144:6699/SRnOWeb_GX_Log";
    public final static String SRNO_LOG_STRUTS2 = "/logger_dealFromPos.action";
    public final static int SUCC =0;
    public static final int PACK_ERROR = 1;
    public static final int WORKFLOW_ERROR = 2;
    public static final int NET_ERROR = 3;
    private static OkHttpMultipartBodyImpl okHttpMultipartBodyImpl =new OkHttpMultipartBodyImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comm);
        context=this;
    }
    public void upLogBt(View view)
    {
        okHttpMultipartBodyImpl.execute(file, params, url, new NetComplateListener() {

            @Override
            public void onNetComplate(String data) {
                NetProcessDialog.getInstance(context).dismiss();
                Log.e("ldy","通讯成功:"+data);
                Message msg = mHandler.obtainMessage();
                msg.obj = data;
                msg.what = SUCC;
                mHandler.sendMessage(msg);
//                                Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNetError(int errorCode, String s) {
                NetProcessDialog.getInstance(context).dismiss();
                Log.e("ldy","通讯失败"+errorCode);
                Message msg = mHandler.obtainMessage();
                msg.what = NET_ERROR;
                mHandler.sendMessage(msg);

            }

            @Override
            public void onWorkFlowError(String errorCode, String s1) {
                NetProcessDialog.getInstance(context).dismiss();
                Log.e("ldy","通讯失败"+errorCode);
                Message msg = mHandler.obtainMessage();
                msg.what = WORKFLOW_ERROR;
                mHandler.sendMessage(msg);

            }

            @Override
            public void onPackError(int errorCode, String s) {
                NetProcessDialog.getInstance(context).dismiss();
                Log.e("ldy","通讯失败"+errorCode);
                Message msg = mHandler.obtainMessage();
                msg.what = PACK_ERROR;
                mHandler.sendMessage(msg);

            }
        });

    }
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCC:
                    Toast.makeText(context,"上传成功,日志大小为"+fileSize+"kb",Toast.LENGTH_LONG).show();
                    break;
                case PACK_ERROR:
                    Toast.makeText(context,"上传失败:PackError",Toast.LENGTH_SHORT).show();

                    break;
                case WORKFLOW_ERROR:
                    Toast.makeText(context,"上传失败:WorkFlowError",Toast.LENGTH_SHORT).show();

                    break;
                case NET_ERROR:
                    Toast.makeText(context,"上传失败:NetError",Toast.LENGTH_SHORT).show();

                    break;
                default:
            }
        }
    };

}
