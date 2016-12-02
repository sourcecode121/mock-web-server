package com.example.mockwebserver;

import io.appflate.restmock.RESTMockServer;

/**
 * Created by Anand on 08/11/2016.
 */

public class TestMainApplication extends MainApplication {

    @Override
    public String getBaseUrl() {
        return RESTMockServer.getUrl();
    }
}
