package com.example.hlebushek.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trade(
    val tradeId: String?,
    val date: String?,
    val price: Double?,
    val quantity: Double?
) : Parcelable
