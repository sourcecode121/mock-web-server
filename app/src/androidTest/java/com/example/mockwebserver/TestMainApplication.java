package com.example.mockwebserver;

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
}
