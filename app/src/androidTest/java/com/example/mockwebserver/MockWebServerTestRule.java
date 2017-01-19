package com.example.mockwebserver;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import okhttp3.internal.tls.SslClient;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by Anand on 09/11/2016.
 */

public class MockWebServerTestRule implements TestRule {

    public MockWebServer mockWebServer = new MockWebServer();

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                mockWebServer.useHttps(SslClient.localhost().socketFactory, false);
                mockWebServer.start();
                base.evaluate();
                mockWebServer.shutdown();
            }
        };
    }
}
