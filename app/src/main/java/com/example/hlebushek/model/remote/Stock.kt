package com.example.hlebushek.model.remote

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Stock(
    @PrimaryKey
    val figi: String?,
    val ticker: String?,
    val isin: String?,
    val instrumentType: String?,
    val balance: Double?,
    val blocked: Double?,
    val expectedYield: Currency?,
    val minPriceIncrement: Double?,
    val minQuantity: Double?,
    val lot: Double?,
    val lots: Double?,
    val averagePositionPrice: Currency?,
    val averagePositionPriceNoNkd: Currency?,
    val currency: String?,
    val name: String?,
    val type: String?,
) : Parcelable

//TODO deserializer or DTO