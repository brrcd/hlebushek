package com.example.hlebushek.states

import com.example.hlebushek.model.local.Share

sealed class SearchAppState {
    data class Success(val listOfShares: List<Share>) : SearchAppState()
    data class Error(val errorMessage: String) : SearchAppState()
    object Loading : SearchAppState()
}
