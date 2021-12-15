package com.example.hlebushek.model.repository

import com.example.hlebushek.db.StockDatabase
import com.example.hlebushek.model.remote.Stock
import javax.inject.Inject

interface LocalRepository {
    fun addStockToCurrentTrade(stock: Stock)
    fun getStocksFromDB(): List<Stock>
    fun updateStock(stock: Stock)
}

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

    override fun updateStock(stock: Stock) =
        database
            .stockDao()
            .updateStock(stock)
}