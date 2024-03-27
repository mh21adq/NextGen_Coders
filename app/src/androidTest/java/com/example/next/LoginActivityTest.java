package com.example.next;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import com.example.next.ViewModel.Firebase.LoginActivity;

@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    //Test Case ID :1
    @Test
    public void testLoginWithValidCredentials() {
        try (ActivityScenario<LoginActivity> scenario =
                     ActivityScenario.launch(LoginActivity.class)) {
            // Initialize UI elements
            Espresso.onView(ViewMatchers.withId(R.id.email))
                    .perform(ViewActions.typeText("numanhm618@gmail.com"),
                            ViewActions.closeSoftKeyboard());
            Espresso.onView(ViewMatchers.withId(R.id.password))
                    .perform(ViewActions.typeText("Mdnnhn@624"),
                            ViewActions.closeSoftKeyboard());
            // Click the login button
            Espresso.onView(ViewMatchers.withId(R.id.login))
                    .perform(ViewActions.click());
            // Verify that the user is redirected to the homepage
            Espresso.onView(ViewMatchers.withId(R.id.navigation_home))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Test Case ID :2
    @Test
    public void testNavigateToResetPasswordActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.forget))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.remembered_pass))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    //Test Case ID :3
    @Test
    public void testNavigateToRegisterActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.create_account))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.have_account))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    //Test Case ID :4
    @Test
    public void testEmptyFieldsSubmission() {
        // Click the login button with empty fields
        Espresso.onView(ViewMatchers.withId(R.id.login))
                .perform(ViewActions.click());
        // Check for the email error message
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .check(ViewAssertions.matches(ViewMatchers.hasErrorText
                        ("Email is required.")));

    }
}
