package com.example.hlebushek.model.repository

import com.example.hlebushek.model.remote.ApiResponse
import com.example.hlebushek.model.remote.Stock

interface MainRepository {
    fun getListOfStockMarket(): ApiResponse?
    fun getCandleByFigi(figi: String, from: String, to: String, interval: String): ApiResponse?
    fun getOrderbookByFigi(figi: String, depth: Int = 1): ApiResponse?
    fun addStockToCurrentTrade(stock: Stock)
    fun getStocksFromDB(): List<Stock>
}