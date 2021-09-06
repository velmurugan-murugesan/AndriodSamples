package com.velmurugan.androidmvvmtesting.di

import com.velmurugan.androidmvvmtesting.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideHomeActivity() : MainActivity
}