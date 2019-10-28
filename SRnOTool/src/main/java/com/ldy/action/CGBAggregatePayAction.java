package com.ldy.action;

import com.ldy.function.Network.Instantiation.httpPost.CGBAggregateCommImpl;
import com.ldy.function.Network.Instantiation.httpPost.HttpPostServiceImpl;

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
    private static CGBAggregateCommImpl cgbAggregateCommImpl=new CGBAggregateCommImpl();
   private int connectTimeout=30000,requestTimeout=30000;
    public void handshake()
    {
        try {
            String url="http://218.13.4.200:10194/app-front/position/save-biz-address/handMerchAll";
//                    url="http://218.13.4.200:10194/app-front/position/save-biz-address/SaveBusinessAddressReqVo/handMerchAll";
            //公钥
            String privateKey ="";
            //商户自定义私钥
            String sikey =  "";
            byte[] data = sikey.getBytes();
//            byte[] public_key = Util.hexToByte(privateKey);
//            String context_value = SM2Utils.encrypt(public_key, data);
            JSONObject json = new JSONObject();
            json.put("cr", "11111");
            //type+分配的序号
            //1为分配给商户是序号
            json.put("type", "type1");
            String jsonString = json.toString();
            cgbAggregateCommImpl.postString(jsonString,url,connectTimeout,requestTimeout,null );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
