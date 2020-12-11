package com.example.cleanarchitectureandroid.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.MovieDetail
import com.example.cleanarchitectureandroid.data.Resource
import com.example.cleanarchitectureandroid.domain.usecases.GetAllMoviesUseCase
import com.example.cleanarchitectureandroid.domain.usecases.MovieDetailUseCase
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
class DetailViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieDetailUseCase: MovieDetailUseCase

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<MovieDetail>>



    @ExperimentalCoroutinesApi
    @Test
    fun `detailviewmodel test`() {

        testCoroutineRule.runBlockingTest {
            Mockito.`when`(movieDetailUseCase.execute()).thenReturn(MovieDetail("","","","",""))

            val movieDetailViewModel = DetailViewModel(movieDetailUseCase)

            movieDetailViewModel.getMovieDetails().observeForever(apiUsersObserver)

            movieDetailViewModel.fetchMovieDetail()

            verify(movieDetailUseCase).execute()

            verify(apiUsersObserver).onChanged(Resource.success(MovieDetail("","","","","")))


        }

    }

}