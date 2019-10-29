package com.ldy.action;

import android.content.Context;
import android.widget.Toast;

import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.function.Network.Instantiation.httpPost.CGBAggregateCommImpl;
import com.ldy.function.Network.Instantiation.httpPost.HttpPostServiceImpl;
import com.ldy.function.Network.service.NetComplateListener;

import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/10/28
 * 描    述：广发银行聚合支付与后台交互专用action
 * 修订历史：
 * ================================================
 */
public class CGBAggregatePayAction {
    private static CGBAggregateCommImpl cgbAggregateCommImpl = new CGBAggregateCommImpl();
    private static int connectTimeout = 30000,
            requestTimeout = 30000;
    private static Context context;

    public static void handshake(Context ct) {
        context = ct;
        try {
            String url = "http://218.13.4.200:10194/app-front/position/save-biz-address/handMerchAll";
//                    url="http://218.13.4.200:10194/app-front/position/save-biz-address/SaveBusinessAddressReqVo/handMerchAll";
            //公钥
            String privateKey = "";
            //商户自定义私钥
            String sikey = "";
            byte[] data = sikey.getBytes();
//            byte[] public_key = Util.hexToByte(privateKey);
//            String context_value = SM2Utils.encrypt(public_key, data);
            JSONObject json = new JSONObject();
            json.put("cr", "11111");
            //type+分配的序号
            //1为分配给商户是序号
            json.put("type", "type1");
            String jsonString = json.toString();
            NetProcessDialog.getInstance(context).show();
            cgbAggregateCommImpl.postString(jsonString, url, connectTimeout, requestTimeout, new NetComplateListener() {
                @Override
                public void onNetComplate(String data) {
                    NetProcessDialog.getInstance(context).dismiss();
//                    Toast.makeText(context, "data=" + data, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNetError(int errorCode, String errorMessage) {
                    NetProcessDialog.getInstance(context).dismiss();
                }

                @Override
                public void onWorkFlowError(String errorCode, String errorMessage) {
                    NetProcessDialog.getInstance(context).dismiss();
                }

                @Override
                public void onPackError(int errorCode, String errorMessage) {
                    NetProcessDialog.getInstance(context).dismiss();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
