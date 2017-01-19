package com.example.mockwebserver;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.internal.tls.SslClient;

/**
 * Created by Anand on 08/11/2016.
 */

public class TestMainApplication extends MainApplication {
    private String baseUrl;

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public SSLSocketFactory getSSLSocketFactory() {
        return SslClient.localhost().socketFactory;
    }

    @Override
    public X509TrustManager getX509TrustManager() {
        return SslClient.localhost().trustManager;
    }
}
