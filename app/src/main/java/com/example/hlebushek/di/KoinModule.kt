package com.example.hlebushek.di

import com.example.hlebushek.model.repository.RemoteRepository
import com.example.hlebushek.model.repository.RemoteRepositoryImpl
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import com.example.hlebushek.viewmodel.MarketViewModel
import com.example.hlebushek.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl() }
    //View models
    viewModel { PortfolioViewModel(get()) }
    viewModel { MarketViewModel(get()) }
    viewModel { CurrentTradeViewModel(get()) }
}