package com.example.hlebushek.model

import com.example.hlebushek.model.remote.Stock
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class StockTypeDeserializer: JsonDeserializer<Stock> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Stock {
        TODO()
    }
}