package com.example.hlebushek.di

import com.example.hlebushek.model.repository.RemoteRepository
import com.example.hlebushek.model.repository.RemoteRepositoryImpl
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import com.example.hlebushek.viewmodel.MarketViewModel
import com.example.hlebushek.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideMarketViewModel(remoteRepository: RemoteRepositoryImpl): MarketViewModel =
        MarketViewModel(remoteRepository)

    @Provides
    fun provideSearchViewModel(remoteRepository: RemoteRepositoryImpl): SearchViewModel =
        SearchViewModel(remoteRepository)

    @Provides
    fun provideCurrentTradeViewModel(remoteRepository: RemoteRepositoryImpl): CurrentTradeViewModel =
        CurrentTradeViewModel(remoteRepository)
}