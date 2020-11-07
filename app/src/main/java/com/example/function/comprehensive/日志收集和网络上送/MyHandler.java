package com.example.function.comprehensive.日志收集和网络上送;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.function.comprehensive.FilesSizeUtils;
import com.ldy.view.dialog.NetProcessDialog;
import com.srno.android.net.http.HttpClient;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MyHandler extends Handler implements ConstantData {

    private Context context;

    public MyHandler(Context context) {
        this.context = context;
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SUCC:
                Toast.makeText(context, "上传成功,日志大小为" + msg.obj + "kb", Toast.LENGTH_LONG).show();
                break;
            case PACK_ERROR:
                Toast.makeText(context, "上传失败:PackError", Toast.LENGTH_SHORT).show();

                break;
            case WORKFLOW_ERROR:
                Toast.makeText(context, "上传失败:WorkFlowError", Toast.LENGTH_SHORT).show();

                break;
            case NET_ERROR:
                Toast.makeText(context, "上传失败:NetError", Toast.LENGTH_SHORT).show();

                break;
            case OBTAIN_LOG:
                File file = (File) msg.obj;
                upFile(file);
                break;
            case COMMUNICATION:
                Toast.makeText(context, "后台返回："+msg.obj , Toast.LENGTH_SHORT).show();

                break;
            default:

        }
    }

    private void upFile(File file) {
        double fileSize = FilesSizeUtils.getFileOrFilesSize(file, FilesSizeUtils.SIZETYPE_KB);
        UpLogActivity.executor.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type", "application/octet-stream");
                HashMap<String, String> params = new HashMap<String, String>();

                String sn = "1234567890";
                String posModel = "A910";
//                        String url=SRnO_SERVER+SRnO_LOG_SERVLET;
                String url = SRNO_SERVER + SRNO_LOG_STRUTS2;

                Log.e("ldy", "通讯开始");
//                        NetProcessWindow.getInstance(context).show();
                params.put("sn", sn);
//                        params.put("mid",sysParam.get(SysParam.MERCH_ID));
//                        params.put("pid",sysParam.get(SysParam.TERMINAL_ID));
                params.put("mid", "123456789012345");
                params.put("pid", "12345678");
                params.put("posModel", posModel);
                params.put("fileSize", fileSize + "kb");
                NetProcessDialog.getInstance(context).show();
                Log.e("ldy", "统一资源定位器：" + url);
                Log.e("ldy", "文件大小：" + fileSize);
                Map<String, File> fileParams = new HashMap<String, File>();
                fileParams.put("name",file);
                String data = null;
                try {
                    data = HttpClient.doPost(url, params, file,3000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                NetProcessDialog.getInstance(context).dismiss();
                if(data!=null) {
                    Log.e("ldy", "通讯成功:" + data);
                }
                Message msg = obtainMessage();
                msg.obj = data;
                msg.what = COMMUNICATION;
                sendMessage(msg);
            }
        });
    }
}