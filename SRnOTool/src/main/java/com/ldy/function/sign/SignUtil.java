package com.ldy.function.sign;



import android.util.Base64;
import android.util.Log;

import com.ldy.Utils.conversion;
import com.ldy.function.sign.config.SwiftpassConfig;

import java.util.Map;



/**
 * @author zeming.fan@swiftpass.cn
 *
 */
public class SignUtil {
    //private final static Logger log = LoggerFactory.getLogger(SignUtil.class);
	
	//请求时根据不同签名方式去生成不同的sign
    public static String getSign(String signType,String preStr){
    	if("RSA_1_256".equals(signType)){
        	try {
        		return SignUtil.sign(preStr,"RSA_1_256", SwiftpassConfig.mchPrivateKey);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else{
        	return MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
        }
    	return null;
    }
	//请求时根据不同签名方式去生成不同的sign
	public static String getSign(String signType,String preStr,String Key){
		if("RSA_1_256".equals(signType)){
			try {
				return SignUtil.sign(preStr,"RSA_1_256", Key);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			return MD5.sign(preStr, "&key=" + Key, "utf-8");
		}
		return null;
	}
    //对返回参数的验证签名
    public static boolean verifySign(String sign,String signType,Map<String,String> resultMap) throws Exception{
    	if("RSA_1_256".equals(signType)){
    		Map<String,String> Reparams = SignUtils.paraFilter(resultMap);
            StringBuilder Rebuf = new StringBuilder((Reparams.size() +1) * 10);
            SignUtils.buildPayParams(Rebuf,Reparams,false);
            String RepreStr = Rebuf.toString();
            if(SignUtil.verifySign(RepreStr,sign, "RSA_1_256", SwiftpassConfig.platPublicKey)){
            	return true;
            }
    	}else if("MD5".equals(signType)){
    		if(SignUtils.checkParam(resultMap, SwiftpassConfig.key)){
    			return true;
    		}
    	}
    	return false;
    }
    public static boolean verifySign(String sign,String signType,Map<String,String> resultMap,String key) throws Exception{
        if("RSA_1_256".equals(signType)){
            Map<String,String> Reparams = SignUtils.paraFilter(resultMap);
            StringBuilder Rebuf = new StringBuilder((Reparams.size() +1) * 10);
            SignUtils.buildPayParams(Rebuf,Reparams,false);
            String RepreStr = Rebuf.toString();
            if(SignUtil.verifySign(RepreStr,sign, "RSA_1_256", key)){
                return true;
            }
        }else if("MD5".equals(signType)){
            if(SignUtils.checkParam(resultMap, key)){
                return true;
            }
        }
        return false;
    }
	public static boolean verifySign(String preStr,String sign,String signType, String platPublicKey) throws Exception {
		// 调用这个函数前需要先判断是MD5还是RSA
		// 商户的验签函数要同时支持MD5和RSA
		RSAUtil.SignatureSuite suite = null;
		
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        
		boolean result = RSAUtil.verifySign(suite, preStr.getBytes("UTF8"), Base64.decode(sign.getBytes("UTF8"),Base64.DEFAULT),
                platPublicKey);
        
		return result;
    }

    public static String sign(String preStr, String signType, String mchPrivateKey) throws Exception {
		RSAUtil.SignatureSuite suite = null;
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        byte[] signBuf = RSAUtil.sign(suite, preStr.getBytes("UTF8"),
                mchPrivateKey);
		byte[] signBase64=Base64.encode(signBuf,Base64.DEFAULT);
		Log.e("ldy", "sign: "+ conversion.bytes2HexString(signBase64));
		String sign= new String(signBase64, "UTF8");
		Log.e("ldy", "sign: "+ sign);
		return sign.replaceAll("[\\s*\t\n\r]", "");
    }
}
