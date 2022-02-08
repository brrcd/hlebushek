package com.example.hlebushek.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hlebushek.SETTINGS
import com.example.hlebushek.TAX_RATE
import com.google.gson.annotations.SerializedName

@Entity(tableName = SETTINGS)
data class Settings(
    @SerializedName(TAX_RATE)
    val taxRate: Float = 0f,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)