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
class MovieRepositoryImplTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiClient: ApiClient


    @ExperimentalCoroutinesApi
    @Test
    fun `get movie list from api test`() {

        testCoroutineRule.runBlockingTest {

            Mockito.`when`(apiClient.getAllMovies()).thenReturn(getMovieList())

            val movieRepositoryImpl = MovieRepositoryImpl(apiClient)

            val response = movieRepositoryImpl.getAllMovies()

            verify(apiClient).getAllMovies()

            assertEquals(response, getMovieList())


        }
    }

    private fun getMovieList(): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        movies.add(Movie("","",""))
        return movies
    }

}