package com.example.hlebushek.di

import com.example.hlebushek.repository.MainRepositoryImpl
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import com.example.hlebushek.viewmodel.MainActivityViewModel
import com.example.hlebushek.viewmodel.SearchViewModel
import com.example.hlebushek.viewmodel.SettingsViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModel(repository: MainRepositoryImpl): MainActivityViewModel =
        MainActivityViewModel(repository)

    @Provides
    fun provideSearchViewModel(repository: MainRepositoryImpl): SearchViewModel =
        SearchViewModel(repository)

    @Provides
    fun provideCurrentTradeViewModel(repository: MainRepositoryImpl): CurrentTradeViewModel =
        CurrentTradeViewModel(repository)

    @Provides
    fun provideSettingsViewModel(repository: MainRepositoryImpl): SettingsViewModel =
        SettingsViewModel(repository)
}