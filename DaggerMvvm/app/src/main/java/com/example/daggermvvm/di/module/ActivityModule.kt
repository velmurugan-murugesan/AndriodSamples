package com.example.daggermvvm.di.module

import com.example.daggermvvm.ui.detail.DetailActivity
import com.example.daggermvvm.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity() :DetailActivity

}