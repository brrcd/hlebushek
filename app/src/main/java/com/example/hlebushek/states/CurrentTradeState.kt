package com.example.hlebushek.states

import com.example.hlebushek.model.local.Share

sealed class CurrentTradeState {
    data class InitSuccess(val listOfShares: List<Share>) : CurrentTradeState()
    data class UpdateSuccess(val listOfShares: List<Share>) : CurrentTradeState()
    data class Error(val errorMessage: String) : CurrentTradeState()
    object Loading : CurrentTradeState()
}
