package com.example.mockwebserver;

import android.app.Application;

/**
 * Created by Anand on 08/11/2016.
 */

public class MainApplication extends BaseApplication {

    @Override
    public String getBaseUrl() {
        return "https://api.github.com/";
    }
}
