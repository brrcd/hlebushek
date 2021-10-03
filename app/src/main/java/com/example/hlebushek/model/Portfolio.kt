package com.example.hlebushek.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Portfolio(
    val totalValue: Double?,
    val freeCurrencyValue: Double?
) : Parcelable