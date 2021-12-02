package com.velmurugan.kotlincoroutinesexample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val vinNumber = "aa111 a111111"
        val res = vinNumber.matches(Regex("[a-zA-Z0-9]+ [0-9]+"))
        assertEquals(true, res)


    }


}