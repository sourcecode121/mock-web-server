package com.example.mockwebserver;

import okhttp3.OkHttpClient;

/**
 * Created by Anand on 08/11/2016.
 */

public class OkHttp {
    private static OkHttpClient instance = null;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            instance = new OkHttpClient();
        }
        return instance;
    }
}
