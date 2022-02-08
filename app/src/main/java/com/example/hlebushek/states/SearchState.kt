package com.example.hlebushek.states

import com.example.hlebushek.model.local.Share

sealed class SearchState {
    data class Success(val listOfShares: List<Share>) : SearchState()
    data class Error(val errorMessage: String) : SearchState()
    object Loading : SearchState()
}
