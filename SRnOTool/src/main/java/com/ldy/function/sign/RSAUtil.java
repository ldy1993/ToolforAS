package com.ldy.function.sign;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import android.util.Base64;
import android.util.Log;


/**
 * @author zeming.fan@swiftpass.cn
 *
 */
public class RSAUtil {
    private static final String TAG = "RSAUtil";
    public static enum SignatureSuite {
        //SHA1("SHA1WithRSA"), MD5("MD5WithRSA");
    	SHA1("SHA1WithRSA"), SHA256("SHA256WithRSA");
        private String suite;

        SignatureSuite(String suite) {
            this.suite = suite;
        }

        public String val() {
            return suite;
        }
    }

    //private final static Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    private static KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA","BC");
        } catch (NoSuchAlgorithmException e) {
            // 应该不会出现
            throw new RuntimeException("初始化RSA KeyFactory失败NoSuchAlgorithm");
        } catch (NoSuchProviderException e) {
            throw new RuntimeException("初始化RSA KeyFactory失败NoSuchProvider");
        }
    }

    public static byte[] sign(SignatureSuite suite, byte[] msgBuf, String privateKeyStr) {
        Signature signature = null;
        try {
            signature = Signature.getInstance(suite.val());
      } catch (Exception e) {
            // 上线运行时套件一定存在
            // 异常不往外抛
            e.printStackTrace();
        }

        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr,Base64.DEFAULT));
            Log.d(TAG, "sign: "+suite.val() );
            PrivateKey privateKey = getKeyFactory().generatePrivate(keySpec);
            signature.initSign(privateKey);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("INVALID_PRIKEY");
        }
        try {
            signature.update(msgBuf);
            return signature.sign();
        } catch (SignatureException e) {
            // 一般不会出现
            
            throw new RuntimeException(e.getMessage());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("update_失敗");
        }
    }

    public static boolean verifySign(SignatureSuite suite, byte[] msgBuf, byte[] sign, String publicKeyStr) {
        Signature signature = null;
        try {
            signature = Signature.getInstance(suite.val());
        } catch (Exception e) {
            // 上线运行时套件一定存在
            // 异常不往外抛
        }
        Log.d(TAG, "sign: "+suite.val() );
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyStr,Base64.DEFAULT));
            PublicKey publicKey = getKeyFactory().generatePublic(keySpec);
            signature.initVerify(publicKey);
        } catch(Exception e) {
           
            throw new RuntimeException("INVALID_PUBKEY");
        }
        try {
            signature.update(msgBuf);
            Log.e(TAG, "verifySign: " );
            return signature.verify(sign);
        } catch (SignatureException e) {
            // 一般不会出现
            
            throw new RuntimeException("签名格式不合法");
        }
    }
}
