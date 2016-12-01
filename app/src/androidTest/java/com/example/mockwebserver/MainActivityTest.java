package com.example.mockwebserver;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by Anand on 08/11/2016.
 */
public class MainActivityTest {

    private static final String PUBLIC_REPOS = "{ \"public_repos\" : 38 }";

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
    public void publicRepos() throws IOException, InterruptedException {
//        mockWebServerTestRule.mockWebServer.enqueue(new MockResponse().setBody(PUBLIC_REPOS));

        // Determine the response for the request
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
                String path = recordedRequest.getPath();
                String[] parts = path.split("/");
                String userName = parts[parts.length - 1];
                return new MockResponse().setBody(PUBLIC_REPOS);
            }
        };
        mockWebServerTestRule.mockWebServer.setDispatcher(dispatcher);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.text_view)).check(matches(withText("38")));

        RecordedRequest recordedRequest = mockWebServerTestRule.mockWebServer.takeRequest();
        assertEquals("/users/sourcecode121", recordedRequest.getPath());
    }

    @Test
    public void responseCode404() throws IOException {
        mockWebServerTestRule.mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.text_view)).check(matches(withText("404")));
    }

    @Test
    public void malformedJson() throws IOException {
        mockWebServerTestRule.mockWebServer.enqueue(new MockResponse().setBody("Malformed Json"));

        activityTestRule.launchActivity(null);

        onView(withId(R.id.text_view)).check(matches(withText("IOException")));
    }
}