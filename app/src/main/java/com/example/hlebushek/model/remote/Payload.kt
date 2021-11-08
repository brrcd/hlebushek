package com.example.hlebushek.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload(
    val instruments: List<Stock>?,
    val positions: List<Stock>?,
    val currencies: List<Currency>?,
    val operations: List<Operation>?,
    val candles: List<Candles>?,
    val figi: String?,
    val depth: Int?,
    val bids: List<Bids>,
    val asks: List<Asks>,
    val tradeStatus: String?,
    val minPriceIncrement: Double?,
    val faceValue: Double?,
    val lastPrice: Double?,
    val closePrice: Double?,
    val limitUp: Double?,
    val limitDown: Double?,
    val stock: Stock?,
    val message: String?,
    val code: String?
) : Parcelable