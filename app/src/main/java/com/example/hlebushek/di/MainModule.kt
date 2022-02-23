package com.example.hlebushek.di

import com.example.hlebushek.repository.*
import com.example.hlebushek.view.CurrentTradeFragment
import com.example.hlebushek.view.MainActivity
import com.example.hlebushek.view.SearchFragment
import com.example.hlebushek.view.SettingsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

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
    fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @Binds
    @Singleton
    fun bindMainRepository(
        mainRepository: MainRepositoryImpl
    ): MainRepository

    @Binds
    @Singleton
    fun bindLocalRepository(
        localRepository: LocalRepositoryImpl
    ): LocalRepository

    @Binds
    @Singleton
    fun bindRemoteRepository(
        remoteRepository: RemoteRepositoryImpl
    ): RemoteRepository
}