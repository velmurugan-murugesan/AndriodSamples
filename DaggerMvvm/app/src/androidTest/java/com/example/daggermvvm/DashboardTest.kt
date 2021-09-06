package com.example.daggermvvm

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.daggermvvm.di.component.TestAppComponent
import com.example.daggermvvm.ui.fragments.OneFragment
import com.example.daggermvvm.ui.home.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var testAppComponent: TestAppComponent

    @Before
    fun init() {

    }

    @Test
    fun changeText_sameActivity() {
        /*val scenario = launchFragmentInContainer<OneFragment>()
        onView(withId(R.id.textview)).check(matches(isDisplayed()))*/
        onView(withId(R.id.container)).check(matches(isDisplayed()))


    }
}