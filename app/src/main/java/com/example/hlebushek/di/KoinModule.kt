package com.example.hlebushek.di

import com.example.hlebushek.model.repository.RemoteRepository
import com.example.hlebushek.model.repository.RemoteRepositoryImpl
import com.example.hlebushek.viewmodel.FragmentMarketViewModel
import com.example.hlebushek.viewmodel.FragmentPortfolioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl() }
    //View models
    viewModel { FragmentPortfolioViewModel(get()) }
    viewModel { FragmentMarketViewModel(get()) }
}