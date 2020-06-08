package com.ldy.apache.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;

/**
 * 生成KeyStore和TrustStore
 *
 * @author liuzy
 */
public class KsManager {
	private static String tag = "KeyStoreManager";

	/**
	 * @param ctx
	 * @param crtFile client.crt文件
	 * @param pemFile client.pem文件
	 * @param keyStorePwd 生成的KeyStore密码
	 * @return
	 */
	public static KeyStore getKeyStoreByCrtPem(Context ctx, String crtFile, String pemFile, String keyStorePwd) {
		InputStream pem = null;
		InputStreamReader inReader = null;
		PEMParser pemParser = null;
		InputStream crt = null;
		try {
			// 客户端私钥
			pem = ctx.getResources().getAssets().open(pemFile);
			inReader = new InputStreamReader(pem);
			pemParser = new PEMParser(inReader);
			PEMKeyPair kp = (PEMKeyPair) pemParser.readObject();
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
			PrivateKey privateKey = converter.getKeyPair(kp).getPrivate();

			// 客户端证书
			CertificateFactory cf = CertificateFactory.getInstance("x.509");
			crt = ctx.getResources().getAssets().open(crtFile);
			Certificate cer = cf.generateCertificate(crt);
			Certificate[] chain = new Certificate[] { cer };

			// 验证公钥和私钥
			verify(cer.getPublicKey(), privateKey);
			
			// 初始化
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null);
			keyStore.setKeyEntry("client", privateKey, keyStorePwd.toCharArray(), chain);
			
			// 打印KeyStore信息
			Util.show(keyStore, keyStorePwd);
			
			return keyStore;
		} catch (Exception e) {
			Log.e(tag, "创建服务器信任的证书仓库失败");
			e.printStackTrace();
		} finally {
			try {
				if (pem != null) {
					pem.close();
				}
				if (inReader != null) {
					inReader.close();
				}
				if (pemParser != null) {
					pemParser.close();
				}
				if (crt != null) {
					crt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 验证读取文件得到的公钥和私钥
	 * 
	 * @param publicKey
	 * @param privateKey
	 * @throws Exception
	 */
	@SuppressLint("TrulyRandom")
	private static void verify(PublicKey publicKey, PrivateKey privateKey) throws Exception {
		String msg = "测试字符串_test_string";
		Log.d("加密前", msg);
		Cipher cipher = Cipher.getInstance("RSA");
		// 公钥加密
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] mi = cipher.doFinal(msg.getBytes());
		// 私钥解密
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		String ming = new String(cipher.doFinal(mi));
		Log.d("解密后", ming);
		// 验证
		if (!msg.equals(ming)) {
			throw new RuntimeException("客户端公钥和私钥不匹配");
		}
	}

	/**
	 * @param ctx
	 * @param crtFile server.crt
	 * @return
	 */
	public static KeyStore getTrustStoreByCrt(Context ctx, String crtFile) {
		InputStream ctxIn = null;
		try {
			// 服务器证书
			ctxIn = ctx.getResources().getAssets().open(crtFile);
			CertificateFactory cf = CertificateFactory.getInstance("x.509");
			Certificate cer = cf.generateCertificate(ctxIn);

			// 初始化
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null);
			// 把服务器证书放入keystore中
			keyStore.setCertificateEntry("server", cer);

			// 打印KeyStore信息
			Util.show(keyStore, null);

			return keyStore;
		} catch (Exception e) {
			Log.e(tag, "创建客户端信任的服务器证书仓库失败");
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (ctxIn != null) {
					ctxIn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @param ctx
	 * @param p12File
	 * @param p12Pwd
	 * @return
	 */
	public static KeyStore getKeyStoreByP12(Context ctx, String p12File, String p12Pwd) {
		InputStream p12In = null;
		try {
			p12In = ctx.getResources().getAssets().open(p12File);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(p12In, p12Pwd.toCharArray());
			Util.show(keyStore, p12Pwd);
			return keyStore;
		} catch (Exception e) {
			Log.e(tag, "创建服务器信任的证书仓库失败");
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (p12In != null) {
					p12In.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @param ctx
	 * @param bksFile
	 * @return
	 */
	public static KeyStore getTrustStoreByBks(Context ctx, String bksFile, String bksPwd) {
		InputStream bksIn = null;
		try {
			bksIn = ctx.getResources().getAssets().open(bksFile);
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(bksIn, bksPwd.toCharArray());
			Util.show(keyStore, bksPwd);
			return keyStore;
		} catch (Exception e) {
			Log.e(tag, "创建客户端信任的服务器证书仓库失败");
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bksIn != null) {
					bksIn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}