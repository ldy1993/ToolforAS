package com.github.ldy.httpserver.comm;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.github.ldy.Calcauthorization.CalcAuthorization;
import com.github.ldy.Utils.JSONUtil;
import com.github.ldy.data.Province;
import com.github.ldy.window.NetProcessWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.CommEntity.ERequestType;
import me.jerry.framework.comm.CommEntity.RequestBean;
import me.jerry.framework.comm.CommManager;
import me.jerry.framework.comm.ICommEventListener;
import me.jerry.framework.comm.NetException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class Communicate {
    public final static String CONFIG_TOOL_SERVER = "http://106.75.143.164:8181/PayfeeServer";
    public final static String SRnO_SERVER = "http://192.168.43.60:8080/SRnOWeb";
    public final static String SRnO_AUTH_SERVLET = "/AuthorizationInterface";
    public final static String SRnO_LOG_SERVLET = "/LogServlet";
    public final static String CONFIG_FILE_URL = CONFIG_TOOL_SERVER + "/menuconfig";
    public final static String CONFIG_PROVINCE_URL = CONFIG_TOOL_SERVER + "/province";
    static byte[] recv = null;
    public static final int EXCEPTION = 400;
    public static final int SENDINGDATA = 203;
    public static final int ONRECVINGDATA = 201;
    public static final int ONCONNECTSERVER = 202;
    public static Context mContext;
    public static NetComplateListener mListener;
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SENDINGDATA:
                    NetProcessWindow.getInstance(mContext).show();
                    break;
                case ONRECVINGDATA:
                    break;
                case ONCONNECTSERVER:
                    NetProcessWindow.getInstance(mContext).show();
                    break;
                case EXCEPTION:
                    NetProcessWindow.getInstance(mContext).dismiss();
                    mListener.onNetError(EXCEPTION, (String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    public static void sendMessage(String message) {
        Message msg = mHandler.obtainMessage();
        msg.obj = message;
        msg.what = EXCEPTION;
        mHandler.sendMessage(msg);
        Log.e("onConnectServer", message);
    }

    public static interface NetComplateListener<T> {
        public void onNetComplate(T data);

        public void onNetError(int errorCode, String errorMessage);

        public void onWorkFlowError(String errorCode, String errorMessage);

        public void onPackError(int errorCode, String errorMessage);
    }


    public static void uploadConfigFile(final Context context, String data, String id, int provinceId, final NetComplateListener<String> listener) {
        CommEntity entity = new CommEntity();
        RequestBean requestBean = new RequestBean();
        try {
            requestBean.url = CONFIG_FILE_URL + "/" + URLEncoder.encode(id, "UTF-8") + ".do";
        } catch (UnsupportedEncodingException e) {
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xml", data);
        params.put("provinceId", provinceId);
        requestBean.params = params;
        Log.i("Communicate", "url:" + requestBean.url);
        for (String s : params.keySet()) {
            System.out.println("key:" + s);
            System.out.println("values:" + params.get(s));
        }
        entity.setRequestBean(requestBean);
        entity.setRequestType(ERequestType.REQUEST_TYPE_POST);
        final Handler handler = new Handler(Looper.getMainLooper());
        entity.setEventListener(new ICommEventListener() {
            @Override
            public void onStart(CommEntity arg0) {
                NetProcessWindow.getInstance(context).show();
            }

            @Override
            public void onError(final NetException arg0, CommEntity arg1) {
                // upload error
                NetProcessWindow.getInstance(context).dismiss();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onNetError(arg0.getErrCode(), arg0.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onEnd(CommEntity entity) {
                NetProcessWindow.getInstance(context).dismiss();
                final String rsp = new String(entity.getResponseBean().body);
                // show id
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onNetComplate(rsp);
                        }
                    }
                });
            }
        });
        CommManager.getInstance().sendMessage(entity);
    }

    public static void PostFile(File file,Map<String, String> params,String url, final NetComplateListener<String> listener) {
        /*****************************************/
//        //一种：参数请求体
        FormBody.Builder formBody = new FormBody.Builder();
        for (String s : params.keySet()) {
            System.out.println("key:" + s);
            System.out.println("values:" + params.get(s));
            formBody.add(s,  params.get(s));
        }

        FormBody paramsBody = formBody.build();
        //二种：文件请求体
        MediaType type = MediaType.parse("text/plain");//"text/xml;charset=utf-8"
        if(!file.exists()) {
           Log.e("ldy","文件不存在");
           listener.onNetError(-1, "日志文件不存在");
          return;
       }
        RequestBody fileBody = RequestBody.create(type, file);
        //三种：混合参数和文件请求

        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.ALTERNATIVE)
                //一样的效果
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"params\"")
                        , paramsBody)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"file\"; filename="+file.getName())
                        , fileBody) .build();


        final Request request = new Request.Builder().url(url)
                .addHeader("User-Agent", "android")
                .header("Content-Type", "text/html; charset=utf-8;")
                .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
                .build();

        /****************************************************/
//        //创建Request
        final OkHttpClient mOkHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        final Call call = mOkHttpClient.newBuilder().readTimeout(50, TimeUnit.SECONDS).connectTimeout(50, TimeUnit.SECONDS).writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
                listener.onNetError(-1, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();

                   try {
                       com.alibaba.fastjson.JSONObject returnData = JSON.parseObject(string);
                       Log.e("ldy","resultCode="+returnData.get("resultCode"));

                       if (returnData.get("resultCode").toString().equals("0")) {
                           listener.onNetComplate(returnData.get("resultMsg")+"");
                       } else {
                           Log.e("ldy","json="+returnData.toString());
                           listener.onWorkFlowError("-3",returnData.get("resultMsg")+"");
                       }
                   }catch (Exception e)
                   {
                       e.printStackTrace();
                       listener.onWorkFlowError("-4","未知异常");
                   }
                } else {
                    listener.onNetError(-2, response.message());
                }
            }
        });


    }

    public static void PostParams(Map<String, Object> params, String url, ICommEventListener listener) {
        CommEntity entity = new CommEntity();
        RequestBean requestBean = new RequestBean();
        try {
            requestBean.url = url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        requestBean.params = params;
        Log.i("Communicate", "url:" + requestBean.url);
        entity.setRequestBean(requestBean);
        entity.setRequestType(ERequestType.REQUEST_TYPE_POST);
        entity.setEventListener(listener);
        CommManager.getInstance().sendMessage(entity);
    }

    public static void deleteCofigFile(final Context context, final String id, final NetComplateListener<String> listener) {

        CommEntity entity = new CommEntity();
        RequestBean requestBean = new RequestBean();
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            requestBean.url = CONFIG_FILE_URL + "/" + URLEncoder.encode(id, "UTF-8") + ".do" + "?password=123456";
        } catch (UnsupportedEncodingException e1) {
        }
        Log.i("Communicate", "url:" + requestBean.url);
        entity.setRequestBean(requestBean);
        entity.setCacheable(false);
        entity.setRequestType(ERequestType.REQUEST_TYPE_DELETE);
        entity.setEventListener(new ICommEventListener() {
            @Override
            public void onStart(CommEntity arg0) {
                NetProcessWindow.getInstance(context).show();
            }

            @Override
            public void onError(final NetException arg0, CommEntity arg1) {
                NetProcessWindow.getInstance(context).dismiss();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onNetError(arg0.getErrCode(), arg0.getMessage());
                    }
                });
            }

            @Override
            public void onEnd(CommEntity entity) {
                NetProcessWindow.getInstance(context).dismiss();
                String rsp = new String(entity.getResponseBean().body);
                try {
                    JSONObject obj = new JSONObject(rsp);
                    String optString = obj.optString("errMsg");
                    if (optString == null || !optString.equals("success")) {
                        Log.e("ldy", optString);
                        return;
                    }
                    listener.onNetComplate(optString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        CommManager.getInstance().sendMessage(entity);
    }

//    public static void downloadConfigFile(final Context context, final String id, final NetComplateListener<String> listener) {
//        CommEntity entity = new CommEntity();
//        RequestBean requestBean = new RequestBean();
//        final Handler handler = new Handler(Looper.getMainLooper());
//        try {
//            requestBean.url = CONFIG_FILE_URL + "/" + URLEncoder.encode(id, "UTF-8") + ".do";
//        } catch (UnsupportedEncodingException e1) {
//        }
//        Log.i("Communicate", "url:" + requestBean.url);
//        entity.setRequestBean(requestBean);
//        entity.setCacheable(false);
//        entity.setRequestType(ERequestType.REQUEST_TYPE_GET);
//        entity.setEventListener(new ICommEventListener() {
//            @Override
//            public void onStart(CommEntity arg0) {
//                NetProcessWindow.getInstance(context).show();
//            }
//
//            @Override
//            public void onError(final NetException arg0, CommEntity arg1) {
//                // upload error
//                NetProcessWindow.getInstance(context).dismiss();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        listener.onNetError(arg0.getErrCode(), arg0.getMessage());
//                    }
//                });
//            }
//
//            @Override
//            public void onEnd(CommEntity entity) {
//                NetProcessWindow.getInstance(context).dismiss();
//                String rsp = new String(entity.getResponseBean().body);
//                if (rsp.startsWith("{errMsg:")) {
//                    ToastUtils.showTheSingle(context, rsp);
//                    return;
//                }
//                if (!id.startsWith(SysParams.CURRENT_COMPOS_FILE_ID) && !id.startsWith(SysParams.CURRENT_JSON_FILE_ID)) {
//                    // show id
//                    File file = new File(Constants.XML_STORATION + File.separator + TransMenu.FILE_NAME);
//                    if (!file.exists()) {
//                        try {
//                            file.createNewFile();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    listener.onWorkFlowError("-1", "配置文件创建失败");
//                                }
//                            });
//                        }
//                    }
//                    BufferedWriter writer = null;
//                    try {
//                        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
//                        try {
//                            writer.write(rsp);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        SysParams.getInstance(context).putString(SysParams.CURRENT_MENU_FILE_ID, id);
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                listener.onNetComplate(id);
//                            }
//                        });
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (writer != null) {
//                            try {
//                                writer.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                    TransMenu menu;
//                    try {
//                        menu = XmlParser.<TransMenu>parser(new FileInputStream(Constants.XML_STORATION + File.separator + TransMenu.FILE_NAME), TransMenu.class);
//                        if (menu.getInnerMenu() == null) {
//                            menu.setInnerMenu(new ArrayList<TransMenu>());
//                        }
//                        for (TransMenu m : menu.getInnerMenu()) {
//                            // 给WorkFlow设置唯一标识
//                            m.getWorkFlow().setMenuName(m.getName());
//                        }
//                        ((AppActivity) context).setRootMenu(menu);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    File file = new File(Constants.XML_STORATION + File.separator + TransMenu.FILE_COMPOS_NAME);
//                    if (!file.exists()) {
//                        try {
//                            file.createNewFile();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    listener.onWorkFlowError("-1", "配置文件创建失败");
//                                }
//                            });
//                        }
//                    }
//                    BufferedWriter writer = null;
//                    try {
//                        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
//                        try {
//                            writer.write(rsp);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        SysParams.getInstance(context).putString(SysParams.CURRENT_COMPOS_FILE_ID, id);
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                listener.onNetComplate(id);
//                            }
//                        });
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (writer != null) {
//                            try {
//                                writer.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }
//        });
//        CommManager.getInstance().sendMessage(entity);
//    }

    public static void getProvinces(String sn, final Context context, final ICommEventListener listener) {
        CommEntity entity = new CommEntity();
        RequestBean requestBean = new RequestBean();
//        requestBean.url = CONFIG_PROVINCE_URL + ".do";
        requestBean.url = SRnO_SERVER + SRnO_AUTH_SERVLET;
        Log.i("Communicate", "url:" + requestBean.url);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("value", "{\"sn\":\"" + sn + "\",\"key\":\"" + CalcAuthorization.MD5(sn) + "\"}");
        requestBean.params = params;
        Log.i("Communicate", "url:" + requestBean.url);
        for (String s : params.keySet()) {
            System.out.println("key:" + s);
            System.out.println("values:" + params.get(s));
        }
        entity.setRequestBean(requestBean);
        entity.setRequestType(ERequestType.REQUEST_TYPE_POST);
        entity.setEventListener(listener);
        CommManager.getInstance().sendMessage(entity);
    }

    public static void findProvince(final Context context, String name, final NetComplateListener<Province> listener) {
        CommEntity entity = new CommEntity();
        RequestBean requestBean = new RequestBean();
        if (!TextUtils.isEmpty(name)) {
            try {
                requestBean.url = CONFIG_PROVINCE_URL + "/" + URLEncoder.encode(name, "UTF-8") + ".do";
            } catch (UnsupportedEncodingException e1) {
            }

            Log.i("ldy", "url:" + requestBean.url);
            final Handler handler = new Handler(Looper.getMainLooper());
            entity.setRequestBean(requestBean);
            entity.setRequestType(ERequestType.REQUEST_TYPE_GET);
            entity.setEventListener(new ICommEventListener() {
                @Override
                public void onStart(CommEntity entity) {
                    Log.i("ldy", "beginfindProvince");
                    NetProcessWindow.getInstance(context).show();
                }

                @Override
                public void onError(final NetException exception, CommEntity entity) {
                    NetProcessWindow.getInstance(context).dismiss();
                    Log.i("ldy", "onError");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onNetError(exception.getErrCode(), exception.getMessage());
                        }
                    });
                }

                @Override
                public void onEnd(final CommEntity entity) {
                    NetProcessWindow.getInstance(context).dismiss();
                    Log.i("ldy", "onEnd");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                listener.onNetComplate((Province) JSONUtil.toObject(Province.class.getName(), new String(entity.getResponseBean().body)));
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            CommManager.getInstance().sendMessage(entity);
        } else {
            Log.e("ldy", "定位失败获取城市信息失败");
        }

    }

    public static void fetchFiles(final Context context, final int provinceId, final ICommEventListener listener) {
        CommEntity entity = new CommEntity();
        RequestBean requestBean = new RequestBean();
        requestBean.url = CONFIG_FILE_URL + "/province/" + provinceId + ".do";
        Log.i("Communicate", "url:" + requestBean.url);
        entity.setRequestBean(requestBean);
        entity.setRequestType(ERequestType.REQUEST_TYPE_GET);
        entity.setEventListener(listener);
        CommManager.getInstance().sendMessage(entity);
    }


}
