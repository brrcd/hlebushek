package com.example.hlebushek.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.remote.Stock

@Database(
    entities = [Stock::class, Settings::class],
    version = 6,
    exportSchema = false
)
abstract class StockDatabase : RoomDatabase(){

    abstract fun stockDao(): StockDao
    abstract fun settingsDao(): SettingsDao
}