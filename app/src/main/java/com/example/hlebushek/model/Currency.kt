package com.example.hlebushek.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val currency: String?,
    val value: Double?,
    val balance: Double?,
    val blocked: Double?
) : Parcelable
