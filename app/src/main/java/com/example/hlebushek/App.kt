package com.example.hlebushek

import android.app.Application
import android.content.Context
import com.example.hlebushek.di.DaggerHlebushekComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerHlebushekComponent
            .builder()
            .withContext(applicationContext)
            .build()
}