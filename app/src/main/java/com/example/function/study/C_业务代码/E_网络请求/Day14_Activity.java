package com.example.function.study.C_业务代码.E_网络请求;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.function.comprehensive.FilesSizeUtils;
import com.ldy.apache.http.HttpClientTool;
import com.ldy.log.Log;
import com.ldy.log.LogcatHelper;
import com.ldy.task.SingleAsyncTask;
import com.ldy.view.CustomWidget.TimePicker.TimeUtils;
import com.ldy.view.dialog.NetProcessDialog;
import com.ldy.study.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Day14_Activity extends Activity {
    private NetProcessDialog npd;;
    private TextView tv1;
    private String URL;
    private Map<String, String> urlMap;
    private  Map<String, String> headerMap;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day14);
        HttpClientTool.init(null,null,null);
        Button bt1 = findViewById(R.id.bt1);
        Button bt2 = findViewById(R.id.bt2);
        Button bt5 = findViewById(R.id.bt5);
        tv1 = findViewById(R.id.day14_tv);
        bt1.setOnClickListener(doGetParamListener);
        bt2.setOnClickListener(doGetHeaderParamListener);
        bt5.setOnClickListener(dopostHeaderParamFileListener);

    }

    /**
     * 发送get请求，只设置参数的按钮监听
     */
    View.OnClickListener doGetParamListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new SingleAsyncTask<String>() {
                @Override
                protected void predeal() {
                    Log.e("ldy","预处理");
                    URL="https://www.ctrip.com/";
                    urlMap=new ArrayMap<>();
                    urlMap.put("allianceid","14666");
                    urlMap.put("sid","418533");
                    urlMap.put("ouid","000401app-");
                    urlMap.put("utm_medium","");
                    urlMap.put("utm_source","");
                    urlMap.put("isctrip","");
                  tv1.setText("清空之前数据"+"\n"+"----get请求，发送参数预处理----");
                    npd=new NetProcessDialog(Day14_Activity.this,"通讯中");
                    npd.show();
                }

                @Override
                protected <String> String doInBackgroud() {
                    return (String) HttpClientTool.doGET(URL,urlMap);
                }

                @Override
                protected void endDeal(String s) {

                    Log.e("ldy","通讯结束处理");
                    Log.e("ldy","通讯返回什么呢："+s);
                    String temp=  tv1.getText()+"\n"+"----通讯返回----"+"\n"+s;

                    tv1.setText( Html.fromHtml(temp));
                    npd.dismiss();
                }
            }.excute();
        }
    };
    /**
     * 发送get请求，设置参数和头的按钮监听
     */
    View.OnClickListener doGetHeaderParamListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new SingleAsyncTask<Spanned>() {
                @Override
                protected void predeal() {
                    Log.e("ldy","预处理");
                    URL="https://www.ctrip.com/";
                    urlMap=new ArrayMap<>();
                    urlMap.put("allianceid","14666");
                    urlMap.put("sid","418533");
                    urlMap.put("ouid","000401app-");
                    urlMap.put("utm_medium","");
                    urlMap.put("utm_source","");
                    urlMap.put("isctrip","");
                    headerMap=new ArrayMap<>();
                    headerMap.put("cache-control","no-cache");
                    headerMap.put("postman-token","ddc4ce09-42cf-8383-593f-474d20cd9fc6");
                 tv1.setText("清空之前数据"+"\n"+"----get请求，发送参数预处理----");
                    npd=new NetProcessDialog(Day14_Activity.this,"通讯中");
                    npd.show();
                }

                @Override
                protected <Spanned> Spanned doInBackgroud() {
                    String temp=  tv1.getText()+"\n"+"----通讯返回----"+"\n"+HttpClientTool.doGET(URL,urlMap,headerMap);
                    return (Spanned) Html.fromHtml(temp);
                }

                @Override
                protected void endDeal(Spanned s) {

                    Log.e("ldy","通讯结束处理");
                    Log.e("ldy","通讯返回什么呢："+s.toString());
                    tv1.setText(s);
                    npd.dismiss();
                }
            }.excute();
        }
    };
    /**
     * 发送post请求，设置参数，头，和文件的按钮监听
     */
    public final static String SRNO_SERVER = "http://121.199.47.32:8080/SRnOWeb";
    public final static String SRNO_LOG_STRUTS2 = "/logger_dealFromPos.action";
    View.OnClickListener dopostHeaderParamFileListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new SingleAsyncTask<String>() {
                @Override
                protected void predeal() {
                    Log.e("ldy", "开启日志服务");
                    LogcatHelper.getInstance(Day14_Activity.this).init(Day14_Activity.this);
                    LogcatHelper.getInstance(Day14_Activity.this).start();

                    TimeUtils.timePickerAlertDialog(null, Day14_Activity.this, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Day14_Activity.this, "取消", Toast.LENGTH_SHORT).show();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Time = TimeUtils.getTxtTime("", "", "", "", "", "");
                            android.util.Log.e("ldy", "Time=" + Time);
                             file = LogcatHelper.chooseFile(Time);
                            if (file == null) {
                                Toast.makeText(Day14_Activity.this, "没有找到对应log", Toast.LENGTH_SHORT).show();
                                return;
                            }
                           headerMap = new HashMap<String, String>();
                            headerMap.put("Content-Type", "application/octet-stream");
                            urlMap = new HashMap<String, String>();

                            String sn = "1234567890";
                            String posModel = "A910";
                            URL= SRNO_SERVER + SRNO_LOG_STRUTS2;

                            android.util.Log.e("ldy", "通讯开始");
                            double fileSize = FilesSizeUtils.getFileOrFilesSize(file, FilesSizeUtils.SIZETYPE_KB);
                            urlMap.put("sn", sn);
                            urlMap.put("mid", "123456789012345");
                            urlMap.put("pid", "12345678");
                            urlMap.put("posModel", posModel);
                            urlMap.put("fileSize", fileSize + "kb");
                            npd=new NetProcessDialog(Day14_Activity.this,"上传日志，大小为：" + fileSize + "kb");
                            npd.show();
                        }
                    });
                }

                @Override
                protected <String> String doInBackgroud() {
                    if(file==null||file.length()<0)
                    {
                        return (String)"";
                    }
                    Log.e("ldy", "开始通讯");
                    return (String)  HttpClientTool.doPOST(URL, headerMap, urlMap, file);

                }

                @Override
                protected void endDeal(String s) {
                    if(file==null||file.length()<0)
                    {
                        tv1.setText( "获取不到日志文件！！！");
                        return ;
                    }
                    npd.dismiss();
                    LogcatHelper.getInstance(Day14_Activity.this).stop();

                    Log.e("ldy","通讯结束处理");
                    Log.e("ldy","通讯返回什么呢："+s);
                    String temp=  tv1.getText()+"\n"+"----通讯返回----"+"\n"+s;
                    tv1.setText( Html.fromHtml(temp));

                }
            }.excute();
        }
    };
}
