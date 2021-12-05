package com.example.hlebushek.model.remote

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "stocks")
@Parcelize
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("figi")
    val figi: String?,
    @SerializedName("instrumentType")
    val instrumentType: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ticker")
    val ticker: String?
) : Parcelable

//TODO deserializer or DTO