package com.example.hlebushek.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hlebushek.model.remote.Stock

@Database(
    entities = [Stock::class],
    version = 2,
    exportSchema = false
)
abstract class StockDatabase : RoomDatabase(){

    abstract fun stockDao(): StockDao
}