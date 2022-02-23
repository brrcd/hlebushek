package com.example.hlebushek.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hlebushek.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = SHARES)
@Parcelize
data class Share(
    @SerializedName(FIGI)
    val figi: String = "",
    @SerializedName(NAME)
    val name: String = "",
    @SerializedName(TICKER)
    val ticker: String = "",
    @SerializedName(LOT)
    val lot: Int = -1,
    @SerializedName(CURRENCY)
    val currency: String = "",
    @SerializedName(PURCHASE_PRICE)
    var purchasePrice: Double = 0.0,
    @SerializedName(PURCHASE_DATE)
    var purchaseDate: String? = "",
    @SerializedName(LAST_CHECKED_PRICE)
    var lastCheckedPrice: Double = 0.0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable