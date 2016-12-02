package com.example.mockwebserver;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.RequestsVerifier;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.appflate.restmock.utils.RequestMatchers.pathEndsWith;
import static io.appflate.restmock.utils.RequestMatchers.pathStartsWith;

/**
 * Created by Anand on 08/11/2016.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Rule
    public IdlingResourceTestRule idlingResourceTestRule = new IdlingResourceTestRule();

    @Before
    public void reset() {
        RESTMockServer.reset();
    }

    @Test
    public void publicRepos() throws IOException {
        RESTMockServer.whenGET(pathEndsWith("sourcecode121"))
                .thenReturnFile("users/sourcecode121.json");

        activityTestRule.launchActivity(null);

        onView(withId(R.id.text_view)).check(matches(withText("38")));

        RequestsVerifier.verifyGET(pathStartsWith("/users/sourcecode121")).invoked();
    }

    @Test
    public void status404() throws IOException {
        RESTMockServer.whenGET(pathEndsWith("sourcecode121"))
                .thenReturnEmpty(404);

        activityTestRule.launchActivity(null);

        onView(withId(R.id.text_view)).check(matches(withText("404")));
    }
}