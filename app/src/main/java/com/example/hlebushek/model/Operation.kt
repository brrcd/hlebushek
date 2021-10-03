package com.example.hlebushek.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Operation(
    val id: String?,
    val status: String?,
    val trades: List<Trade>?,
    val commission: Currency?,
    val currency: String?,
    val payment: Double?,
    val price: Double?,
    val quantity: Double?,
    val quantityExecuted: Double?,
    val figi: String?,
    val instrumentType: String?,
    val isMarginCall: Boolean?,
    val date: String?,
    val operationType: String?
) : Parcelable