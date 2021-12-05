package com.example.hlebushek.model.repository

import com.example.hlebushek.db.StockDatabase
import com.example.hlebushek.model.remote.Stock
import javax.inject.Inject

class LocalRepositoryImpl
@Inject constructor(
    private val database: StockDatabase
): LocalRepository {

    override fun addStockToCurrentTrade(stock: Stock) =
        database
            .stockDao()
            .addStockToCurrentTrade(stock)

    override fun getStocksFromDB() =
        database
            .stockDao()
            .getStocksFromDB()
}