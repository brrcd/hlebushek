package com.example.hlebushek.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val taxRate: Float = 0f,
)