package com.example.hlebushek.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    val taxRate: Float = 0f,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)