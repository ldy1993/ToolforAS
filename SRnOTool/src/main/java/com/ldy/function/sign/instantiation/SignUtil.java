package com.ldy.function.sign.instantiation;



import android.util.Base64;

import com.keyTool.KEY;


/**
 * @author zeming.fan@swiftpass.cn
 *
 */
public class SignUtil {
    //private final static Logger log = LoggerFactory.getLogger(SignUtil.class);
	
	//请求时根据不同签名方式去生成不同的sign
    public static String getSign(String signType, String preStr){
    	if("RSA_1_256".equals(signType)){
        	try {
        		return SignUtil.sign(preStr,"RSA_1_256");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else{
        	return MD5.sign(preStr, "&key=" + "", "utf-8");
        }
    	return null;
    }

	public static boolean getVerifySign(String preStr, String sign, String signType) throws Exception {
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
		byte[] msgBuf=preStr.getBytes("UTF8");
		byte[] Sign=Base64.decode(sign.getBytes("UTF8"),Base64.DEFAULT);
		boolean result = verifySign(suite,msgBuf,Sign);
        
		return result;
    }

    private static String sign(String preStr, String signType) throws Exception {
		RSAUtil.SignatureSuite suite = null;
		if ("RSA_1_1".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA1;
		} else if ("RSA_1_256".equals(signType)) {
			suite = RSAUtil.SignatureSuite.SHA256;
		} else {
			throw new Exception("不支持的签名方式");
		}
        byte[] signBuf = RSAUtil.sign(suite, preStr.getBytes("UTF8"),
				KEY.getMchPrivateKey());
		byte[] signBase64=Base64.encode(signBuf,Base64.DEFAULT);
		String sign= new String(signBase64, "UTF8");
		return sign.replaceAll("[\\s*\t\n\r]", "");
    }
	private static boolean verifySign(RSAUtil.SignatureSuite suite, byte[] msgBuf, byte[] sign) throws Exception {

		boolean result = RSAUtil.verifySign(suite, msgBuf, sign,KEY.getPlatPublicKey());

		return result;
	}
}
