package n7.myperfectemptyproject.ui.main

import android.content.Intent
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import n7.myperfectemptyproject.MainActivity
import n7.myperfectemptyproject.R
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(AndroidJUnit4::class) // local runner that handle all test in class
class MainFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java) // handles Activity lifecycles

    @get:Rule
    val testRule: CountingTaskExecutorRule = CountingTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    fun clickOnButton() {
        // onView(withId(R.id.b_test)).check(matches(withText("BUTTON")))
        // onView(withId(R.id.b_load_user)).perform(click())
        // onView(withId(R.id.b_test)).check(matches(withText("test")))
        // onView(withId(R.id.b_test)).check(matches(isDisplayed()))
    }

    /**
     * Simply sanity test to ensure that activity launches without any issues and shows some data.
     */
    @Test
    @Throws(InterruptedException::class, TimeoutException::class)
    fun showSomeResults() {

        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val activity = InstrumentationRegistry.getInstrumentation().startActivitySync(intent)
        testRule.drainTasks(10, TimeUnit.SECONDS)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        val recyclerView: RecyclerView = activity.findViewById(R.id.rv)
        waitForAdapterChange(recyclerView)
        MatcherAssert.assertThat(recyclerView.adapter, CoreMatchers.notNullValue())
        waitForAdapterChange(recyclerView)
        MatcherAssert.assertThat(recyclerView.adapter!!.itemCount > 0, CoreMatchers.`is`(true))
    }

    @Throws(InterruptedException::class)
    private fun waitForAdapterChange(recyclerView: RecyclerView) {
        val latch = CountDownLatch(1)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            recyclerView.adapter!!.registerAdapterDataObserver(
                object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        latch.countDown()
                    }

                    override fun onChanged() {
                        latch.countDown()
                    }
                })
        }
        if (recyclerView.adapter!!.itemCount > 0) {
            return  //already loaded
        }
        MatcherAssert.assertThat(latch.await(10, TimeUnit.SECONDS), CoreMatchers.`is`(true))
    }
}
