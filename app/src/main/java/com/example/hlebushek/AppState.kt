package com.example.hlebushek

sealed class AppState {
    data class Success(val data: String) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
