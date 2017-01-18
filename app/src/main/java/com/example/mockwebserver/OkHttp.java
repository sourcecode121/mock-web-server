package com.example.mockwebserver;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;

/**
 * Created by Anand on 08/11/2016.
 */

public class OkHttp {
    private static OkHttpClient instance = null;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .certificatePinner(createCertificatePinner());
            instance = builder.build();
        }
        return instance;
    }

    private static CertificatePinner createCertificatePinner() {
        String host = "api.github.com";
        return new CertificatePinner.Builder()
                .add(host, "sha256/6wJsqVDF8K19zxfLxV5DGRneLyzso9adVdUN/exDacw=")
                .add(host, "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=")
                .add(host, "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
                .build();
    }
}
