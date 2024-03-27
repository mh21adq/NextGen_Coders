package com.example.next;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.next.ViewModel.Firebase.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class RegisterActivityUITest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityScenarioRule =
            new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void registerActivityTest() {
        // Input Email
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.typeText("numanpj618@gmail.com"),
                        ViewActions.closeSoftKeyboard());
        // Input Password
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText("123456"), ViewActions.closeSoftKeyboard());
        // Confirm Password
        Espresso.onView(ViewMatchers.withId(R.id.confirm_password))
                .perform(ViewActions.typeText("123456"), ViewActions.closeSoftKeyboard());
        // Click Register Button
        Espresso.onView(ViewMatchers.withId(R.id.login))
                .perform(ViewActions.click());
        // Wait for a short time to allow the app to respond to the button click.
        try {
            Thread.sleep(2000); // Use IdlingResource in production code
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // in log in page
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
