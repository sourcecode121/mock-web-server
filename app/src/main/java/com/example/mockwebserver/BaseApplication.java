package com.example.mockwebserver;

import android.app.Application;

/**
 * Created by Anand on 08/11/2016.
 */

public abstract class BaseApplication extends Application {
    public abstract String getBaseUrl();
}
