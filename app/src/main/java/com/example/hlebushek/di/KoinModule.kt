package com.example.hlebushek.di

import com.example.hlebushek.model.repository.Repository
import com.example.hlebushek.model.repository.RepositoryImpl
import com.example.hlebushek.viewmodel.FragmentMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
    //View models
    viewModel { FragmentMainViewModel(get()) }
}