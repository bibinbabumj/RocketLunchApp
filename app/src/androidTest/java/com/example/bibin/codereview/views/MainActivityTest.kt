package com.example.bibin.codereview.views

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.bibin.codereview.MainActivity
import com.example.bibin.codereview.adapter.list_adapter.RocketLaunchListAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testListFragment() {

        //////// check list fragment is displayed
        Espresso.onView(withId(com.example.bibin.codereview.R.id.titleText)).check(ViewAssertions.matches(
            isDisplayed()))
        // delay 2 sec
        Thread.sleep(2000L)

        // Click on th 5th position , show the details in Fragment details page
        Espresso.onView(withId(com.example.bibin.codereview.R.id.rocketLaunchList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RocketLaunchListAdapter.ListItemViewHolder>(
                5,
                ViewActions.click()
            )
        )

        // delay 2 sec
        Thread.sleep(2000L)

        //////// check details fragment is displayed
        Espresso.onView(withId(com.example.bibin.codereview.R.id.filight_number_txt)).check(ViewAssertions.matches(
            isDisplayed()))

        Thread.sleep(2000L)
    }
}