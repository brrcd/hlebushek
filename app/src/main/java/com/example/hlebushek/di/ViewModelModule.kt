package com.example.hlebushek.di

import com.example.hlebushek.model.repository.MainRepositoryImpl
import com.example.hlebushek.model.repository.RemoteRepositoryImpl
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import com.example.hlebushek.viewmodel.MarketViewModel
import com.example.hlebushek.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideMarketViewModel(repository: MainRepositoryImpl): MarketViewModel =
        MarketViewModel(repository)

    @Provides
    fun provideSearchViewModel(repository: MainRepositoryImpl): SearchViewModel =
        SearchViewModel(repository)

    @Provides
    fun provideCurrentTradeViewModel(repository: MainRepositoryImpl): CurrentTradeViewModel =
        CurrentTradeViewModel(repository)
}