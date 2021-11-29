package com.example.hlebushek.di

import android.content.Context
import com.example.hlebushek.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, MainModule::class])
interface HlebushekComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun withContext(context: Context): Builder

        fun build(): HlebushekComponent
    }
}