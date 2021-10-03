package com.example.hlebushek

import com.example.hlebushek.model.ApiResponse
import com.example.hlebushek.model.Portfolio

sealed class AppState {
    data class Success(val portfolio: Portfolio) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
