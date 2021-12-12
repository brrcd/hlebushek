package com.example.hlebushek.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload(
    val instruments: List<Stock>?,
    val positions: List<Stock>?,
    val currencies: List<Currency>?,
    val candles: List<Candles>?,
    val figi: String?,
    val depth: Int?,
    val tradeStatus: String?,
    val lastPrice: Double?,
    val message: String?,
    val code: String?
) : Parcelable