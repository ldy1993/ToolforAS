package com.ldy.function.Network;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * 双向认证HTTPS工具-安卓版(使用HttpClient)
 * 
 * @author liuzy
 */
public class HttpClientTool {
	private static final int connectionTimeout = 8000;
	private static final int readTimeout = 8000;

	private static HttpClient httpClient = new DefaultHttpClient();

	/**
	 * 初始化HttpClient连接，只需要一次，如果你信任所有服务器，trustStore传入null即可！
	 * 
	 * @param keyStore
	 * @param keyStorePwd
	 * @param trustStore
	 */
	public static void init(KeyStore keyStore, String keyStorePwd, KeyStore trustStore) {
		try {
			SSLSocketFactory sf = new MySSLSocketFactory(keyStore, keyStorePwd, trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "UTF-8");

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

			HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
			HttpConnectionParams.setSoTimeout(params, readTimeout);
			httpClient = new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class MySSLSocketFactory extends SSLSocketFactory {
		final SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore keystore, String keystorePassword, KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
			super(keystore, keystorePassword, truststore);
			try {
				// 服务器信任的客户端证书
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				keyManagerFactory.init(keystore, keystorePassword.toCharArray());
				KeyManager[] km = keyManagerFactory.getKeyManagers();

				// 客户端信任的服务器证书
				TrustManager[] tm = null;
				if (truststore == null) {
					// 信任所有服务器
					tm = new TrustManager[] { new X509TrustManager() {
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return new X509Certificate[] {};
						}

						@Override
						public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						}

						@Override
						public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						}
					} };
				} else {
					TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
					trustManagerFactory.init(truststore);
					tm = trustManagerFactory.getTrustManagers();
				}
				sslContext.init(km, tm, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}

	public static String doGET(String url, Map<String, String> urlMap) {
		url = url + toUrlStr(urlMap);
		HttpGet req = new HttpGet(url);
		return request(req);
	}

	public static String doGET(String url, Map<String, String> urlMap, Map<String, String> headerMap) {
		url = url + toUrlStr(urlMap);
		HttpGet req = new HttpGet(url);
		addHeader(req, headerMap);
		return request(req);
	}

	public static String doPOST(String url, Map<String, String> bodyMap) {
		HttpPost req = new HttpPost(url);
		addEntity(req, bodyMap);
		return request(req);
	}

	public static String doPOST(String url, Map<String, String> headerMap, Map<String, String> bodyMap) {
		HttpPost req = new HttpPost(url);
		addHeader(req, headerMap);
		addEntity(req, bodyMap);
		return request(req);
	}
	public static String doPOST(String url, Map<String, String> headerMap,  Map<String, String> bodyMap,File file) {
		HttpPost req = new HttpPost(url);
		addHeader(req, headerMap);
		addEntity(req, bodyMap);
		addEntity(req, file);
		return request(req);
	}
	public static String doPOST(String url, Map<String, String> headerMap, File file) {
		HttpPost req = new HttpPost(url);
		addHeader(req, headerMap);
		addEntity(req, file);
		return request(req);
	}
	public static String doPOST(String url, Map<String, String> headerMap,String body) {
		HttpPost req = new HttpPost(url);
		addHeader(req, headerMap);
		addEntity(req, body);
		return request(req);
	}
	public static String doPOST(String url, Map<String, String> urlMap, Map<String, String> headerMap, Map<String, String> bodyMap) {
		url = url + toUrlStr(urlMap);
		HttpPost req = new HttpPost(url);
		addHeader(req, headerMap);
		addEntity(req, bodyMap);
		return request(req);
	}
	private static void addEntity(HttpPost req, Map<String, String> bodyMap) {
		if (bodyMap != null && !bodyMap.isEmpty()) {
			try {
				List<BasicNameValuePair> list = new LinkedList<BasicNameValuePair>();
				for (String key : bodyMap.keySet()) {
					list.add(new BasicNameValuePair(key, bodyMap.get(key)));
				}
				req.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void addEntity(HttpPost req, File file) {
		if (file != null) {
			try {
				req.setEntity(new FileEntity(file, ContentType.MULTIPART_FORM_DATA));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void addEntity(HttpPost req,String body) {
		if (body != null&&!body.isEmpty()) {
			try {
				req.setEntity(new StringEntity(body));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void addHeader(HttpRequestBase req, Map<String, String> headerMap) {
		if (headerMap != null && !headerMap.isEmpty()) {
			for (String key : headerMap.keySet()) {
				req.setHeader(key, headerMap.get(key));
			}
		}
	}

	private static String toUrlStr(Map<String, String> urlMap) {
		if (urlMap == null || urlMap.isEmpty()) {
			return "";
		}
		List<BasicNameValuePair> list = new LinkedList<BasicNameValuePair>();
		for (String key : urlMap.keySet()) {
			list.add(new BasicNameValuePair(key, urlMap.get(key)));
		}
		return "?" + URLEncodedUtils.format(list, "UTF-8");
	}

	private static String request(HttpRequestBase req) {
		try {
			HttpResponse response = httpClient.execute(req);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity, "UTF-8");
			Log.d("RESULT", res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
