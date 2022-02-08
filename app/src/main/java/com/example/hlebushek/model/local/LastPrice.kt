package com.example.hlebushek.model.local

data class LastPrice(
    val figi: String,
    val price: Double = 0.0,
    val time: Long
)