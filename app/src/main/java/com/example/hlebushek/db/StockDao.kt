package com.example.hlebushek.db

import androidx.room.*
import com.example.hlebushek.model.remote.Stock

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStockToCurrentTrade(stock: Stock)

    @Query("SELECT * FROM stocks")
    fun getStocksFromDB(): List<Stock>
}