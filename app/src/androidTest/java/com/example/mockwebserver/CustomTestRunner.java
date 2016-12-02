package com.example.mockwebserver;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import io.appflate.restmock.android.RESTMockTestRunner;

/**
 * Created by Anand on 08/11/2016.
 */

public class CustomTestRunner extends RESTMockTestRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestMainApplication.class.getName(), context);
    }
}
