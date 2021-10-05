package com.example.kotlinscopefunctions

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
        assertEquals(4, 2 + 2)
    }

    //let
    //run
    //with
    //also
    //apply


    //let used for null check
    // referenced as it
    // return last statement

    @Test
    fun letCheck() {

        var name: String? = "Hello"
        val initial = name?.let {
            name.first()
        }
        println(initial)
    }

    //referenced by this
    // return last statement
    // used as extension function / function
    @Test
    fun runCheck() {

        val message = ""
        val numberOfCharacters = message.run {
            length
        }

        val num = kotlin.run {
            var name = "Hello"
            name.length
        }

    }

    //with referenced as this
    //return last statement
    //used to run multiple operation on object
    @Test
    fun withCheck() {

        data class User(var name: String, var address: String)

        val user = User("name","address")

        var name = with(user) {
            name = "Velmurugan"
            address = "Chennai"
            name
        }

        println(name)

    }

    //apply referenced as this
    //return current object
    //initialize and configure object
    @Test
    fun applyCheck() {
        data class User(var name: String, var address: String) {
        }

        val user = User("","").apply {
            name = "name"
            address= "add"
        }
        println(user)
    }

    //Also referenced as it
    //return same object
    //additional operation on object with altering the object
    @Test
    fun alsoCheck() {
        data class User(var name: String, var address: String)

        User("","").apply {

        }

        User("","").apply {
            name = "name"
            address= "add"
        }.also {
            println(it.name)
        }
    }


}