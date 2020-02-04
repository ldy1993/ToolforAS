package com.example.function.comm;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2020/1/7
 * 描    述：
 * 修订历史：
 * ================================================
 */


public class MyX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }

}
