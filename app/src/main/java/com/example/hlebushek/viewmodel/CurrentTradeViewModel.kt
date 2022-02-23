package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.repository.MainRepository
import com.example.hlebushek.states.CurrentTradeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CurrentTradeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<CurrentTradeState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getSharesFromDB() {
        liveDataToObserve.value = CurrentTradeState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getListOfSharesFromDB()
            liveDataToObserve.postValue(CurrentTradeState.InitSuccess(data))
        }
    }

    fun updatePrices(){
        liveDataToObserve.value = CurrentTradeState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getListOfSharesFromDB()
            liveDataToObserve.postValue(CurrentTradeState.UpdateSuccess(data))
        }
    }

    fun getMarginAttributes(){

        viewModelScope.launch(Dispatchers.IO) {
            var id = ""
            runBlocking {
                val accounts =repository.getAccountId()
                accounts.forEach {
                    println(it.id)
                }
                println(accounts.size)
                id = accounts[0].id
            }
            val data = repository.getPortfolio(id)
            val totalValue =
                (data.totalAmountShares.units +
                        data.totalAmountBonds.units +
                        data.totalAmountCurrencies.units +
                        data.totalAmountEtf.units).toInt()
            liveDataToObserve.postValue(CurrentTradeState.InitPortfolio(totalValue))
        }
    }
}