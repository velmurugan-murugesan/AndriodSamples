package com.velmurugan.androidmvvmtesting.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.velmurugan.androidmvvmtesting.home.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds abstract fun bindsViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindHomeViewModel(mainViewModel: MainViewModel): ViewModel

}