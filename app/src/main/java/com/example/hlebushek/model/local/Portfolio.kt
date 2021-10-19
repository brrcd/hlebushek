package com.example.hlebushek.model.local

import android.os.Parcelable
import com.example.hlebushek.model.remote.Stock
import kotlinx.parcelize.Parcelize

@Parcelize
data class Portfolio(
    val totalValue: Double? = null,
    val freeCurrencyValue: Double? = null,
    val stockList: List<Stock>? = listOf()
) : Parcelable