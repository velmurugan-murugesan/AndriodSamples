package com.velmurugan.mvvmdaggerretrofitkotlin.di

import com.velmurugan.mvvmdaggerretrofitkotlin.MyApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class])
interface AppComponents : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {

        fun build() : AppComponents

    }
}