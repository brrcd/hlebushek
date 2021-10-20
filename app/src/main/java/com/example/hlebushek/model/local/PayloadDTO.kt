package com.example.hlebushek.model.local

import android.os.Parcelable
import com.example.hlebushek.model.remote.Stock
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayloadDTO(
    val portfolio: Portfolio? = null,
    val stockList: List<Stock>? = null,
    val currentPrice: String? = null
) : Parcelable
