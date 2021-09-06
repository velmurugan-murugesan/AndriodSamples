package com.subaru.instrumenttestingexample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun changeText_sameActivity() {
        // Check that the text was changed.
        Espresso.onView(withId(R.id.helloworld))
            .check(ViewAssertions.matches(ViewMatchers.withText("helloworld")))
    }


}