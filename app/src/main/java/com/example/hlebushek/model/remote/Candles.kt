package com.example.hlebushek.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Candles(
    val figi: String?,
    val interval: String?,
    val o: String?,
    val c: String?,
    val h: String?,
    val l: String?,
    val v: String?,
    val time: String?
) : Parcelable