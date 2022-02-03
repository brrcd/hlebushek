package com.example.hlebushek.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "settings")
data class Settings(
    @SerializedName("taxRate")
    val taxRate: Float = 0f,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)