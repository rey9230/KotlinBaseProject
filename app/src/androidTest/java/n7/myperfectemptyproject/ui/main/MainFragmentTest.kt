package n7.myperfectemptyproject.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.main_fragment.view.*
import n7.myperfectemptyproject.MainActivity
import n7.myperfectemptyproject.R
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MainFragmentTest {

    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

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