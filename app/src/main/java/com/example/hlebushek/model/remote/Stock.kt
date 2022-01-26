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
    @SerializedName("figi")
    val figi: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ticker")
    val ticker: String?,
    var purchasePrice: Double? = 0.0,
    var purchaseDate: String? = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable