package com.example.hlebushek.db

import androidx.room.*
import com.example.hlebushek.model.local.Settings

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSettings(settings: Settings)

    @Query("SELECT * FROM settings")
    fun getSettings(): Settings?

    @Update
    fun updateSettings(settings: Settings)
}