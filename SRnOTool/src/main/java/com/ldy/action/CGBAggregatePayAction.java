package com.ldy.action;

import android.content.Context;
import android.widget.Toast;

import com.ldy.View.dialog.NetProcessDialog;
import com.ldy.function.Network.Instantiation.httpPost.CGBAggregateCommImpl;
import com.ldy.function.Network.Instantiation.httpPost.HttpPostServiceImpl;
import com.ldy.function.Network.service.NetComplateListener;
import com.ldy.function.sign.instantiation.SM.SM2Utils;
import com.ldy.function.sign.instantiation.SM.Util;

import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    public static void handshake(Context ct,NetComplateListener listener) {
        context = ct;
        try {
            String url = "http://218.13.4.200:10194/app-front/position/save-biz-address/handMerchAll";
//                    url="http://218.13.4.200:10194/app-front/position/save-biz-address/SaveBusinessAddressReqVo/handMerchAll";
         /*******************************************************/
            String sikey = "woyebuzhidaoxiesm";
            // 随便生成的公钥
            String pubk = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";
            String context_value =  SM2Utils.getCipherText(sikey ,pubk);
         /********************************************************/
            JSONObject json = new JSONObject();
            json.put("cr", context_value);
            //type+分配的序号
            //1为分配给商户是序号
            json.put("type", "type1");
            String jsonString = json.toString();
            NetProcessDialog.getInstance(context).show();
            cgbAggregateCommImpl.execute(jsonString, url, listener);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
