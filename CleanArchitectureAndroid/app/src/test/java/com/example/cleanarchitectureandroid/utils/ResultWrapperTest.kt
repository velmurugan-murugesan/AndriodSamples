package com.example.cleanarchitectureandroid.utils

import com.example.cleanarchitectureandroid.data.Movie
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.verify


class ResultWrapperTest {


    @Test
    fun `result wrapper with success test`() {

        val resultWrapper: ResultWrapper<Movie> = ResultWrapper.Success(Movie("","",""))

        assertEquals(resultWrapper, ResultWrapper.Success(Movie("","","")))

    }

    @Test
    fun `result wrapper with error test`() {
        val resultWrapper = ResultWrapper.GenericError(code = 404,error = "Not Found")
        assertEquals(resultWrapper.error, "Not Found")
    }

    @Test
    fun `reult wrapper with network error test`() {
        val resultWrapper = ResultWrapper.NetworkError
        assertEquals(resultWrapper, ResultWrapper.NetworkError)

    }

}