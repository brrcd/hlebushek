package com.example.hlebushek.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asks(
    val price: Double? = 0.0,
    val quantity: Int? = 0
) : Parcelable