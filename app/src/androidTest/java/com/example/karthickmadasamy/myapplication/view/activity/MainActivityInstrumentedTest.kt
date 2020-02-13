package com.example.karthickmadasamy.myapplication.view.activity

import org.junit.After
import com.example.karthickmadasamy.myapplication.R;
import android.support.test.espresso.Espresso.onView
import android.content.Intent
import android.support.test.InstrumentationRegistry.getInstrumentation
import com.squareup.okhttp.mockwebserver.MockResponse
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.Before
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import junit.framework.Assert.assertEquals
import org.hamcrest.Matcher
import org.hamcrest.Matchers.endsWith
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest  {

    @Rule
    @JvmField var mActivityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {

        server = MockWebServer()
        server!!.start()
        server!!.url("/").toString()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.karthickmadasamy.myapplication", appContext.packageName)
    }
    @Test
    @Throws(Exception::class)
    fun testTitleBarIsShown(){
        val intent = Intent()
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    @Throws(Exception::class)
    fun testSwipeRefreshLayout(){
        val intent = Intent()
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.swipeToRefresh))
                .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(5)))

    }

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testListViewIsShown() {
        val fileName = "feeder_success_response.json"
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(TestServiceHelper.getStringFromFile(getInstrumentation().context, fileName)))

        val intent = Intent()
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.feeder_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    @Throws(Exception::class)
    fun testRefreshButtonIsShown() {
        val intent = Intent()
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.refresh)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.refresh)).perform(click())
    }

    @Test
    @Throws(Exception::class)
    fun testListViewItemClickRow() {
        val fileName = "feeder_success_response.json"
        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(TestServiceHelper.getStringFromFile(getInstrumentation().context, fileName)))
        val intent = Intent()
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.feeder_view)).check(matches(hasDescendant(withText(endsWith("Beavers")))))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server!!.shutdown()
    }
}


