package com.example.hlebushek.model.repository

import com.example.hlebushek.model.remote.ApiResponse

interface RemoteRepository {
    fun getPortfolio(): ApiResponse?
    fun getListOfStockMarket(): ApiResponse?
}