package com.example.hlebushek.di

import com.example.hlebushek.model.repository.RemoteRepository
import com.example.hlebushek.model.repository.RemoteRepositoryImpl
import com.example.hlebushek.view.MarketFragment
import com.example.hlebushek.view.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class, ApiModule::class])
interface MainModule {

    @ContributesAndroidInjector
    fun bindMarketFragment(): MarketFragment

    @ContributesAndroidInjector
    fun bindSearchFragment(): SearchFragment

    @Binds
    fun bindRemoteRepository(
        remoteRepository: RemoteRepositoryImpl
    ): RemoteRepository
}