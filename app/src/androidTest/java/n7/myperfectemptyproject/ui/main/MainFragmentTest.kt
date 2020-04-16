package n7.myperfectemptyproject.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import n7.myperfectemptyproject.MainActivity
import n7.myperfectemptyproject.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // local runner that handle all test in class
class MainFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun clickOnButton() {
        onView(withId(R.id.b_test)).check(matches(withText("BUTTON")))
        onView(withId(R.id.b_test)).perform(click())
        onView(withId(R.id.b_test)).check(matches(withText("test")))
        onView(withId(R.id.b_test)).check(matches(isDisplayed()))
    }
}
