package com.example.next;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.next.ui.home.HomeFragment;
import com.example.next.R;
import com.example.next.ui.home.HomeFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeFragmentUITest {

    @Rule
    public ActivityScenarioRule<NavigationActivity> activityRule = new ActivityScenarioRule<>(NavigationActivity.class);

    @Test
    public void testLearnJavaButtonNavigatesToLearnJavaActivity() {

        // Perform a click on the "Learn Java" button
        Espresso.onView(ViewMatchers.withId(R.id.button_java))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.chaptersSpinner))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
