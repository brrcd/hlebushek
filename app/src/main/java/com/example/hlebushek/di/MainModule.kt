package com.example.hlebushek.di

import com.example.hlebushek.model.repository.*
import com.example.hlebushek.services.TraderService
import com.example.hlebushek.view.CurrentTradeFragment
import com.example.hlebushek.view.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class,
        DBModule::class]
)
interface MainModule {

    @ContributesAndroidInjector
    fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    fun bindCurrentTradeFragment(): CurrentTradeFragment

    @ContributesAndroidInjector
    fun bindTraderService(): TraderService

    @Binds
    fun bindMainRepository(
        mainRepository: MainRepositoryImpl
    ): MainRepository

    @Binds
    fun bindLocalRepository(
        localRepository: LocalRepositoryImpl
    ): LocalRepository

    @Binds
    fun bindRemoteRepository(
        remoteRepository: RemoteRepositoryImpl
    ): RemoteRepository
}