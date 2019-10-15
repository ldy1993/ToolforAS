package com.example.function.comprehensive;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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
import com.ldy.function.Network.Instantiation.okhttp.okHttpServiceImpl;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.study.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class upLogActivity extends Activity {
    private Handler handler;
    private static Context context;
    private static double fileSize=0;
    public final static String SRnO_SERVER = "http://180.141.91.144:6699/SRnOWeb_GX_Log";
    public final static String SRnO_LOG_STRUTS2 = "/logger_dealFromPos.action";
    public final static int SUCC =0;
    public static final int PackError = 1;
    public static final int WorkFlowError = 2;
    public static final int NetError = 3;
    private NetProcessDialog npw;
    private static okHttpServiceImpl okHttpServiceImpl=new okHttpServiceImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_log);
        context=this;
        //还需要申请权限。
        Log.e("ldy","开启日志服务");
        LogcatHelper.getInstance(upLogActivity.this).start();
    }
    public void upLogBt(View view)
    {
        //开启一个线程，在里面进行handler.post或者handler.sendMessage(msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                TimeUtils.timePickerAlertDialog(null, context, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"取消",Toast.LENGTH_SHORT).show();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Time=TimeUtils.getTxtTime("", "", "", "", "", "");
                        Log.e("ldy","Time="+Time);
                        File file= LogcatHelper.chooseFile(Time);
                        if(file==null)
                        {
                            Toast.makeText(context,"没有找到对应log",Toast.LENGTH_SHORT).show();
                            return;
                        }
//                        String data=LogcatHelper.readConfigFile(file);
//                        CommManager.init();
                        Map<String, Object> params = new HashMap<String, Object>();
//                        SysParam sysParam = FinancialApplication.sysParam;
//                        String sn= FinancialApplication.dal.getSys().getTermInfo().get(ETermInfoKey.SN);
//                        String posModel= FinancialApplication.dal.getSys().getTermInfo().get(ETermInfoKey.MODEL);
                        String sn="1234567890";
                        String posModel="A910";
//                        String url=SRnO_SERVER+SRnO_LOG_SERVLET;
                        String url=SRnO_SERVER+SRnO_LOG_STRUTS2;

                        Log.e("ldy","通讯开始");
//                        NetProcessWindow.getInstance(context).show();
                        fileSize=FilesSizeUtils.getFileOrFilesSize(file, FilesSizeUtils.SIZETYPE_KB);
                        params.put("sn",sn);
//                        params.put("mid",sysParam.get(SysParam.MERCH_ID));
//                        params.put("pid",sysParam.get(SysParam.TERMINAL_ID));
                        params.put("mid","123456789012345");
                        params.put("pid","12345678");
                        params.put("posModel",posModel);
                        params.put("fileSize",fileSize+"kb");
                        npw= new NetProcessDialog(context,"上传日志，大小为："+fileSize+"kb");
                        npw.getInstance(context).show();
                        Log.e("ldy","统一资源定位器："+url);
                        okHttpServiceImpl.PostParamsOrFile(file, params, url, new NetComplateListener() {

                            @Override
                            public void onNetComplate(String data) {
                                npw.getInstance(context).dismiss();
                                Log.e("ldy","通讯成功:"+data);
                                Message msg = mHandler.obtainMessage();
                                msg.obj = data;
                                msg.what = SUCC;
                                mHandler.sendMessage(msg);
//                                Toast.makeText(context,"上传成功",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNetError(int errorCode, String s) {
                                npw.getInstance(context).dismiss();
                                Log.e("ldy","通讯失败"+errorCode);
                                Message msg = mHandler.obtainMessage();
                                msg.what = NetError;
                                mHandler.sendMessage(msg);

                            }

                            @Override
                            public void onWorkFlowError(String errorCode, String s1) {
                                npw.getInstance(context).dismiss();
                                Log.e("ldy","通讯失败"+errorCode);
                                Message msg = mHandler.obtainMessage();
                                msg.what = WorkFlowError;
                                mHandler.sendMessage(msg);

                            }

                            @Override
                            public void onPackError(int errorCode, String s) {
                                npw.getInstance(context).dismiss();
                                Log.e("ldy","通讯失败"+errorCode);
                                Message msg = mHandler.obtainMessage();
                                msg.what = PackError;
                                mHandler.sendMessage(msg);

                            }
                        });
                    }
                });
                Looper.loop();
            }

        }).start();

    }
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCC:
                    Toast.makeText(context,"上传成功,日志大小为"+fileSize+"kb",Toast.LENGTH_LONG).show();
                    break;
                case PackError:
                    Toast.makeText(context,"上传失败:PackError",Toast.LENGTH_SHORT).show();

                    break;
                case WorkFlowError:
                    Toast.makeText(context,"上传失败:WorkFlowError",Toast.LENGTH_SHORT).show();

                    break;
                case NetError:
                    Toast.makeText(context,"上传失败:NetError",Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    };

}
