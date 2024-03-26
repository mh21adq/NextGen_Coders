package com.example.next;
import com.example.next.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationActivityUITest {

    @Rule
    public ActivityScenarioRule<NavigationActivity> activityRule =
            new ActivityScenarioRule<>(NavigationActivity.class);
    @Test
    public void navigateToHomeFragment() {
        // Click on the home navigation item
        Espresso.onView(ViewMatchers.withId(R.id.navigation_home))
                .perform(ViewActions.click());

        // Verify the Home fragment is displayed
        Espresso.onView(ViewMatchers.withId(R.id.button_java))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void navigateToExamplesFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_examples))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.example_item_1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void navigateToCompilerFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_compiler))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.runButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void navigateToAiAssistantFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_chat_bot))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.openChatGPTButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void navigateUserFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_user))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.logout))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }


}
