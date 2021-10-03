package com.example.hlebushek.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload(
    val instruments: List<Stock>?,
    val positions: List<Stock>?,
    val currencies: List<Currency>?,
    val operations: List<Operation>?,
    val stock: Stock?,
    val message: String?,
    val code: String?
) : Parcelable