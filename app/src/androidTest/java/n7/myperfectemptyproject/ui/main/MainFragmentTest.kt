package n7.myperfectemptyproject.ui.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import n7.myperfectemptyproject.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // local runner that handle all test in class
class MainFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java) // handles Activity lifecycles

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test(timeout = 1000)
    fun clickOnButton() {
        // onView(withId(R.id.b_test)).check(matches(withText("BUTTON")))
        // onView(withId(R.id.b_test)).perform(click())
        // onView(withId(R.id.b_test)).check(matches(withText("test")))
        // onView(withId(R.id.b_test)).check(matches(isDisplayed()))
    }
}
