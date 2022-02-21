package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.log
import com.example.hlebushek.states.SearchState
import com.example.hlebushek.repository.MainRepository
import com.example.hlebushek.states.CurrentTradeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentTradeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<CurrentTradeState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getSharesFromDB() {
        liveDataToObserve.value = CurrentTradeState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getListOfSharesFromDB()
            liveDataToObserve.postValue(
                CurrentTradeState.InitSuccess(
                    data
                )
            )
        }
    }

    fun updatePrices(){
        liveDataToObserve.value = CurrentTradeState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getListOfSharesFromDB()
            liveDataToObserve.postValue(
                CurrentTradeState.UpdateSuccess(
                    data
                )
            )
        }
    }

    fun getAccountId(){
        // todo redo
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getAccountId()
            data.forEach {
                log(it.id)
            }
        }
    }

    fun getMarginAttributes(accountId: String){

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getMarginAttributes(accountId)
            data.liquidPortfolio.units.let{
                log(it)
            }
        }
    }
}