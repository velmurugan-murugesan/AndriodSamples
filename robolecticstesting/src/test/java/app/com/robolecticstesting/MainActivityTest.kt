package app.com.robolecticstesting

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Robolectric
import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.widget.Button
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    lateinit var activity: Activity

    @Before
    fun Before(){
        activity = Robolectric.setupActivity(MainActivity::class.java!!)
    }

    @Test
    fun ButtonDisplayTest(){
        val button = activity.findViewById<Button>(R.id.button_add)
        button.performClick()

        val recyclerView = activity.findViewById<RecyclerView>(R.id.recyclerview)

        var adapterView = recyclerView.adapter
    }

}