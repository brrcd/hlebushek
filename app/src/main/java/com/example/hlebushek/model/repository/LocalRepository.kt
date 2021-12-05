package com.example.hlebushek.model.repository

import com.example.hlebushek.model.remote.Stock

interface LocalRepository {
    fun addStockToCurrentTrade(stock: Stock)
    fun getStocksFromDB(): List<Stock>
}