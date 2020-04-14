package n7.myperfectemptyproject.ui.main

import androidx.test.rule.ActivityTestRule
import n7.myperfectemptyproject.MainActivity
import n7.myperfectemptyproject.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
