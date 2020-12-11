package com.example.cleanarchitectureandroid.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.Resource
import com.example.cleanarchitectureandroid.domain.usecases.GetAllMoviesUseCase
import com.example.cleanarchitectureandroid.utils.TestCoroutineRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var moviesUseCase: GetAllMoviesUseCase

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<List<Movie>>>

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAllMovies() {

        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(emptyList<Movie>()).`when`(moviesUseCase).execute()

            val viewModel = HomeViewModel(moviesUseCase)
            viewModel.fetchAllMovies()
            viewModel.getAllMovies().observeForever(apiUsersObserver)
            verify(moviesUseCase).execute()
            verify(apiUsersObserver).onChanged(Resource.success(emptyList()))
            viewModel.getAllMovies().removeObserver(apiUsersObserver)
        }
    }
}