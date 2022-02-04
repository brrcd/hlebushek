package com.example.hlebushek.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "shares")
@Parcelize
data class Share(
    @SerializedName("figi")
    val figi: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ticker")
    val ticker: String?,
    @SerializedName("purchasePrice")
    var purchasePrice: Double? = 0.0,
    @SerializedName("purchaseDate")
    var purchaseDate: String? = "",
    @SerializedName("lastCheckedPrice")
    var lastCheckedPrice: Double? = 0.0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable