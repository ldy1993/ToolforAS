package com.ldy.sign.instantiation.SM4;

import android.util.Base64;

import com.ldy.sign.instantiation.Util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SM4Utils {
    /**
     *      //客户端随机数 ,截取下标4-16位
     */
    public static String secretKey = "1111111111111111111111111";
    /**
     *     服务端随机数,截取下标4-16位
     */
    private static String iv = "1111111111111111111111111";
    public static boolean hexString = false;
    private static Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
    public SM4Utils() {
    }

    public static String encryptData_ECB(String sm4Key,String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(sm4Key);
            } else {
                keyBytes = sm4Key.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            //之前是用new BASE64Encoder().encode(encrypted)
            String cipherText = Util.byteToHex(Base64.encode(encrypted,Base64.DEFAULT) );
            if (cipherText != null && cipherText.trim().length() > 0) {

                Matcher m = pattern.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptData_ECB(String sm4Key,String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(sm4Key);
            } else {
                keyBytes = sm4Key.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.decode(Util.hexStringToBytes(cipherText),Base64.DEFAULT));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptData_CBC(String secretKey, String iv, String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            String cipherText = Util.byteToHex(Base64.encode(encrypted,Base64.DEFAULT) );
            if (cipherText != null && cipherText.trim().length() > 0) {
                Matcher m = pattern.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return new String(cipherText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decryptData_CBC(String secretKey, String iv, String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.decode(Util.hexStringToBytes(cipherText),Base64.DEFAULT));
//			return new String(decrypted, "GBK");
            return decrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


	public static void sm4Test() throws IOException, NoSuchAlgorithmException{
		String plainText = "ererfeiisgod";
        SM4Utils.secretKey = "JeF8U9wHFOMfs2Y8";
        SM4Utils.hexString = false;
		
		/*String a="111111111111111111111111111111111sccc";
		a=MD5Util.byteToHexString(a.getBytes());
		a=a.substring(8,24);
		System.out.println(a);
		System.out.println("ECB模式");
		String cipherText = sm4.encryptData_ECB(a,plainText);
		System.out.println("密文: " + cipherText);
		System.out.println("");

		plainText = sm4.decryptData_ECB(a,cipherText);
		System.out.println("明文: " + plainText);
		System.out.println("");
		 
		System.out.println("CBC模式");
//		sm4.iv = "UISwD9fW6cFh9SNS";
		String cipherText1 = new String(sm4.encryptData_CBC("1234567890qwerty","1234567890asdfgh",plainText));
		System.out.println("密文: " + cipherText);
		System.out.println("");

		plainText = new String(sm4.decryptData_CBC("1234567890qwerty","1234567890asdfgh",cipherText1));
		System.out.println("明文: " + plainText);
		*/
		
		String b = "1234567890abcdef";
		String test = "testttttttaaee";
        System.out.println("加密");
		String mi = SM4Utils.encryptData_ECB(b,test);
		System.out.println(mi);
        System.out.println("解密");
		String ming = SM4Utils.decryptData_ECB(b, mi);
		System.out.println(ming);
		
	}
}
