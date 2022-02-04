package com.example.hlebushek.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.local.Share

@Database(
    entities = [Share::class, Settings::class],
    version = 9,
    exportSchema = false
)
abstract class TradeDatabase : RoomDatabase(){

    abstract fun shareDao(): ShareDao
    abstract fun settingsDao(): SettingsDao
}