package com.example.mockwebserver;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import okhttp3.internal.tls.SslClient;

/**
 * Created by Anand on 08/11/2016.
 */

public class IdlingResourceTestRule implements TestRule {
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                IdlingResource idlingResource = OkHttp3IdlingResource.create("okhttp",
                        OkHttp.getInstance(
                                SslClient.localhost().socketFactory,
                                SslClient.localhost().trustManager
                        ));
                Espresso.registerIdlingResources(idlingResource);
                base.evaluate();
                Espresso.unregisterIdlingResources(idlingResource);
            }
        };
    }
}
