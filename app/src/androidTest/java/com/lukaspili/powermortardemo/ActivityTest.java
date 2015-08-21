package com.lukaspili.powermortardemo;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.lukaspili.powermortardemo.ui.activity.RootActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by nelsonramirez on 8/20/15.
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
    public void checkHello() {
        onView(withText("Hello")).check(matches(isDisplayed()));

    }

    @Test
    public void checkLogin() {
        onView(withText("Login screen. Click me to login.")).check(matches(isDisplayed()));
    }

    @Test
    public void clickHello() {
        onView(withText("Hello")).perform(click());
    }


    @Test
    public void clickRecyclerView() throws InterruptedException {
//        onData(allOf(is(instanceOf(String.class)), is("qui est esse"))).perform(click());
        View recyclerView = mActivityRule.getActivity().findViewById(R.id.recycler_view);
        while (recyclerView.getVisibility() != View.VISIBLE) {

        }
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }
}
