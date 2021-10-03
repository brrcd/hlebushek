package com.example.hlebushek.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponse(
    val trackingId: String?,
    val status: String?,
    val payload: Payload?,
) : Parcelable