package com.ldy.function.Network.tool;

import android.util.Log;

import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * 打印KeyStore信息
 * 
 * @author liuzy
 */
public class Util {
	private static String tag = "test";

	public static void show(KeyStore ks, String pwd) throws Exception {
		try {
			Log.d(tag, "------------------------------------");
			Log.d(tag, "Provider : " + ks.getProvider().getName());
			Log.d(tag, "Type : " + ks.getType());
			Log.d(tag, "Size : " + ks.size());
			Enumeration<String> en = ks.aliases();
			while (en.hasMoreElements()) {
				String alias = en.nextElement();

				Certificate cer = ks.getCertificate(alias);
				if (cer != null) {
					Log.d(tag, "\nAlias: " + alias);
					byte[] cerb = cer.getEncoded();
					byte[] pkb = cer.getPublicKey().getEncoded();
					Log.d(tag, "Cer bytes:" + cerb.length);
					// Log.d(tag, new String(Base64.encode(cerb, Base64.DEFAULT)));
					Log.d(tag, "PublicKey bytes:" + pkb.length);
					// Log.d(tag, new String(Base64.encode(pkb,
					// Base64.DEFAULT)));
				}
				//TrustStore中，没有Key
				if (pwd != null) {
					Key k = ks.getKey(alias, pwd.toCharArray());
					if (k != null) {
						Log.d(tag, "\nAlias: " + alias);
						Log.d(tag, "Key Algorithm:" + k.getAlgorithm());
						Log.d(tag, "Key bytes:" + k.getEncoded().length);
						// Log.d(tag, new String(Base64.encode(k.getEncoded(), Base64.DEFAULT)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
