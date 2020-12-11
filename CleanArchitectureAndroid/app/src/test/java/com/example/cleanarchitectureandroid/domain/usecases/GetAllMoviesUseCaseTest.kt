package com.example.cleanarchitectureandroid.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.MovieRepositoryImpl
import com.example.cleanarchitectureandroid.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllMoviesUseCaseTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @ExperimentalCoroutinesApi
    @Test
    fun `test get all movie`() {

        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(emptyList<Movie>()).`when`(movieRepositoryImpl).getAllMovies()
            val moviesUseCase = GetAllMoviesUseCase(movieRepositoryImpl)
           val data = moviesUseCase.execute()

            verify(movieRepositoryImpl).getAllMovies()
            assertEquals(data, emptyList<Movie>())

        }

    }

}