package com.srno.android.net.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HttpClient {
    private static final String DECOLLATOR = "------" + java.util.UUID.randomUUID().toString();
    private static final String LF = "\r\n";
    private static final String LF2 = LF + LF;
    private static final String DISPONSITION = "Content-Disposition: form-data; name=";
    private static final String HTTPS_TAG = DECOLLATOR + LF + DISPONSITION;
    private static final String CONTENT_TYPE = "Content-Type:application/json";

    private static final String TAG = "HttpClient";
    private final static String BOUNDARY = UUID.randomUUID().toString()
            .toLowerCase().replaceAll("-", "");// 边界标识
    private final static String PREFIX = "--";// 必须存在


    /**
     * HTTP GET 方式上送文件
     *
     * @param url 通讯url
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, String> map, Map<String, File> fileMap, int timeout ) throws Exception {
        URL localURL;
        try {
            localURL = new URL(url);
        } catch (MalformedURLException e1) {
            throw new Exception("URL不合法");
        }
        URLConnection connection;
        try {
            connection = localURL.openConnection();
        } catch (IOException e1) {
            throw new Exception("打开HTTP失败");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setConnectTimeout(30 * 1000);
        connection.setReadTimeout(timeout);

        try {
            httpURLConnection.setRequestMethod("GET");
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        }
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" +
                        DECOLLATOR);
        StringBuilder params = new StringBuilder();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            try {
                params.append(HTTPS_TAG + "\"" + key + "\"" + LF + CONTENT_TYPE + LF2 + map.get(key) + LF);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            DataOutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());

            os.writeBytes(params.toString());

            keys=fileMap.keySet();
            for (String key : keys) {
                File file=fileMap.get(key);
                os.writeBytes(HTTPS_TAG + "\"fileupload\"; filename=\""+ key+"\"" + LF +
                        "Content-Type:application/octet-stream" + LF2);
                InputStream in = new FileInputStream(file);
                int fileLen = (int) file.length();
                byte[] bytes = new byte[fileLen];
                Log.d("oyyf", " 文件长度" + bytes.length + " file.length()= " + file.length());
                int offset = 0;
                int realsend = 0;
                while (offset < fileLen) {
                    realsend = in.read(bytes, offset, fileLen);
                    os.write(bytes, offset, realsend);
                    offset += realsend;
                }
                os.writeBytes(DECOLLATOR);
                os.writeBytes(DECOLLATOR + "--");
            }


            os.flush();
            os.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } catch (IOException e) {
            throw new Exception("HTTP数据接收失败");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e2) {
                throw new Exception("HTTP，IO异常"+e2.toString());
            }

        }
        return resultBuffer.toString();
    }


    public static String doPost(String url, HashMap<String, String> paramsMap, File file,int timeOut) {

        Log.i(TAG, "uploadPic: ------开始上传"+file.length());
        HttpURLConnection conn = null;

        OutputStream os = null;
        BufferedReader br = null;
        String result=null;

        try {
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(timeOut);
            conn.setReadTimeout(timeOut);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.connect();


            // 往服务器端写内容 也就是发起http请求需要带的参数
            os = new DataOutputStream(conn.getOutputStream());
            // 请求参数部分
            writeParams(paramsMap, os);
            // 请求上传文件部分
            writeFile(file, os);
            // 请求结束标志
            String endTarget = PREFIX + BOUNDARY + PREFIX + LF;
            os.write(endTarget.getBytes());
            os.flush();

            if (conn.getResponseCode() == 200) {
                Log.i(TAG, "uploadPic: ----------result:" + result);
            } else {
                Log.e(TAG, "Post Error " + conn.getResponseCode());
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                    conn = null;
                }

                if (os != null) {
                    os.close();
                    os = null;
                }

                if (br != null) {
                    br.close();
                    br = null;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return null;

    }


    private static void writeParams(HashMap<String, String> paramsMap, OutputStream os) {
        try{
            String msg = "请求参数部分:\n";
            if (paramsMap == null || paramsMap.isEmpty()) {
                msg += "空";
            } else {
                StringBuilder requestParams = new StringBuilder();
                Set<Map.Entry<String, String>> set = paramsMap.entrySet();
                Iterator<Map.Entry<String, String>> it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    requestParams.append(PREFIX).append(BOUNDARY).append(LF);
                    requestParams.append("Content-Disposition: form-data; name=\"")
                            .append(entry.getKey()).append("\"").append(LF);
                    requestParams.append("Content-Type: text/plain; charset=utf-8")
                            .append(LF);
                    requestParams.append("Content-Transfer-Encoding: 8bit").append(
                            LF);
                    requestParams.append(LF);// 参数头设置完以后需要两个换行，然后才是参数内容
                    requestParams.append(entry.getValue());
                    requestParams.append(LF);
                }
                os.write(requestParams.toString().getBytes());
                os.flush();

                msg += requestParams.toString();
            }

            System.out.println(msg);
        }catch(Exception e){
            e.printStackTrace();

        }


    }


    private static void writeFile(File file, OutputStream os) {
        InputStream is = null;
        StringBuilder requestParams = new StringBuilder();
        try {
            requestParams.append(PREFIX).append(BOUNDARY).append(LF);
//            requestParams.append("Content-Disposition: form-data; name=\"")
//                    .append(file).append("\"; filename=\"")
//                    .append(file.getName()).append("\"")
//                    .append(LF);
            requestParams.append("Content-Disposition: form-data; name=\"file\"")
                    .append("; filename=\"")
                    .append(file.getName()).append("\"")
                    .append(LF);
            requestParams.append("Content-Type: ")
                    .append("image/png")
                    .append(LF);
            requestParams.append("Content-Transfer-Encoding: 8bit").append(
                    LF);
            requestParams.append(LF);// 参数头设置完以后需要两个换行，然后才是参数内容

            System.out.println(requestParams.toString());

            os.write(requestParams.toString().getBytes());

            is = new FileInputStream(file);

            byte[] buffer = new byte[1024 * 1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.write(LF.getBytes());
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
