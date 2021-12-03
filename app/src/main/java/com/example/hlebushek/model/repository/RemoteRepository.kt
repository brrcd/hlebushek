package com.example.hlebushek.model.repository

import com.example.hlebushek.model.remote.ApiResponse

interface RemoteRepository {
    fun getPortfolio(): ApiResponse?
    fun getListOfStockMarket(): ApiResponse?
    fun getCandleByFigi(figi: String, from: String, to: String, interval: String): ApiResponse?
    fun getOrderbook(figi: String, depth: Int): ApiResponse?
}

//TODO database + network repositories