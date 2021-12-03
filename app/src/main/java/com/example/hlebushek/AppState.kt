package com.example.hlebushek

import com.example.hlebushek.model.local.PayloadDTO
import com.example.hlebushek.model.local.Portfolio

sealed class AppState {
    data class Success(val payloadDTO: PayloadDTO) : AppState()
    data class Error(val errorMessage: String) : AppState()
    object Loading : AppState()
}
