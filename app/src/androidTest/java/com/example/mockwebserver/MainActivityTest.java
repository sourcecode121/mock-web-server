package com.example.mockwebserver;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Anand on 08/11/2016.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Rule
    public IdlingResourceTestRule idlingResourceTestRule = new IdlingResourceTestRule();

    @Rule
    public MockWebServerTestRule mockWebServerTestRule = new MockWebServerTestRule();

    @Before
    public void setUp() throws Exception {
        TestMainApplication application = (TestMainApplication)
                InstrumentationRegistry.getTargetContext().getApplicationContext();
        application.setBaseUrl(mockWebServerTestRule.mockWebServer.url("/").toString());
    }

    @Test
    public void publicRepos() throws IOException {
        mockWebServerTestRule.mockWebServer.enqueue(new MockResponse().setBody("{ \"public_repos\" : 38 }"));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.text_view)).check(matches(withText("38")));
    }

}