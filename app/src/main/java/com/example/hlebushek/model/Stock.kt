package com.example.hlebushek.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stock(
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