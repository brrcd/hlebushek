package com.example.hlebushek.model.repository

import com.example.hlebushek.model.remote.ApiResponse

interface RemoteRepository {
    fun getListOfStockMarket(): ApiResponse?
    fun getCandleByFigi(figi: String, from: String, to: String, interval: String): ApiResponse?
    fun getOrderbook(figi: String, depth: Int): ApiResponse?
}