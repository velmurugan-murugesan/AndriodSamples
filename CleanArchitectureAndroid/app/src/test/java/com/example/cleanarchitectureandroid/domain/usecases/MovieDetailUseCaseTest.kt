package com.example.cleanarchitectureandroid.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitectureandroid.data.ApiClient
import com.example.cleanarchitectureandroid.data.MovieDetail
import com.example.cleanarchitectureandroid.data.MovieDetailRepositoryImpl
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
class MovieDetailUseCaseTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieDetailRepositoryImpl: MovieDetailRepositoryImpl

    @ExperimentalCoroutinesApi
    @Test
    fun `movie detail usecase with empty data test`() {

        testCoroutineRule.runBlockingTest {

            Mockito.`when`(movieDetailRepositoryImpl.getMovieDetail()).thenReturn(MovieDetail("","","","",""))

            val response = MovieDetailUseCase(movieDetailRepositoryImpl).execute()

            verify(movieDetailRepositoryImpl).getMovieDetail()
            assertEquals(response, MovieDetail("","","","",""))
        }

    }

}