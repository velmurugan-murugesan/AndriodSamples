package com.example.daggermvvm.di.component

import com.example.daggermvvm.MvvmApplication
import com.example.daggermvvm.TestApplication
import com.example.daggermvvm.di.module.ActivityModule
import com.example.daggermvvm.di.module.FragmentModule
import com.example.daggermvvm.di.module.NetworkModule
import com.example.daggermvvm.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ViewModelModule::class, ActivityModule::class, FragmentModule::class])
interface TestAppComponent : AndroidInjector<TestApplication> {

    @Component.Builder
    interface Builder {

        fun build() : TestAppComponent

    }
}