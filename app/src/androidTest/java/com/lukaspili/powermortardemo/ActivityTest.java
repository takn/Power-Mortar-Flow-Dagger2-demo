package com.lukaspili.powermortardemo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.lukaspili.powermortardemo.ui.activity.RootActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.EasyMock2Matchers.equalTo;

/**
 * Created by nelsonramirez on 8/20/15.
 * Copyright Maker Studios 2014-2015
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityTest {

    @Rule
    public ActivityTestRule<RootActivity> mActivityRule =
            new ActivityTestRule<RootActivity>(RootActivity.class);


    @Test
    public void canaryTest() {
        assertTrue("canary test failed", true);
    }

    @Test
    public void checkLogin() {
        onView(withText("Login screen. Click me to login.")).check(matches(isDisplayed()));

    }

    @Test
    public void clickData() {
        onData(withItemContent("que est esse")).perform(click());
    }


    public static Matcher<Object> withItemContent(String expectedText) {
        checkNotNull(expectedText);
        return withItemContent(equalTo(expectedText));
    }

}
