package com.example.hlebushek.model.remote

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "stocks", primaryKeys = ["figi"])
@Parcelize
data class Stock(
    @SerializedName("figi")
    val figi: String,
    @SerializedName("instrumentType")
    val instrumentType: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ticker")
    val ticker: String?
) : Parcelable