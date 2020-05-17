package com.example.function.comprehensive;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.ldy.study.R;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comm);
        context=this;
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
