package com.example.cleanarchitectureandroid.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitectureandroid.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieDetailRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiClient: ApiClient

    @ExperimentalCoroutinesApi
    @Test
    fun `test movie detail repository with empty data`() {

        testCoroutineRule.runBlockingTest {
            Mockito.`when`(apiClient.getMovieDetail()).thenReturn(MovieDetail("","","","",""))

            val movieDetailRepositoryImpl = MovieDetailRepositoryImpl(apiClient)

            val response = movieDetailRepositoryImpl.getMovieDetail()

            verify(apiClient).getMovieDetail()

            assertEquals(response, MovieDetail("","","","",""))

        }


    }

}