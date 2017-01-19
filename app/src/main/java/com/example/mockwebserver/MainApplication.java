package com.example.mockwebserver;

import android.app.Application;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Anand on 08/11/2016.
 */

public class MainApplication extends BaseApplication {

    @Override
    public String getBaseUrl() {
        return "https://api.github.com/";
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return null;
    }

    public X509TrustManager getX509TrustManager() {
        return null;
    }
}
