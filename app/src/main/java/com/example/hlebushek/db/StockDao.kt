package com.example.hlebushek.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hlebushek.model.remote.Stock

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStockToCurrentTrade(stock: Stock)

    @Query("SELECT * FROM stocks")
    fun getStocksFromDB(): List<Stock>
}