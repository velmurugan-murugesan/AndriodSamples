package com.example.daggermvvm.di.module

import com.example.daggermvvm.ui.fragments.OneFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindOneFragment() : OneFragment

}