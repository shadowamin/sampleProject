package com.hannibalprojects.sampleproject.presentation.di

import android.content.Context
import com.hannibalprojects.sampleproject.SampleApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataModule::class,
        UsersListModule::class,
        UserDetailsModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<SampleApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}