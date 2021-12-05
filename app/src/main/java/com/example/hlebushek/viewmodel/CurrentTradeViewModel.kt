package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.AppState
import com.example.hlebushek.model.local.PayloadDTO
import com.example.hlebushek.model.repository.MainRepository
import com.example.hlebushek.model.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentTradeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getStocksFromD() {
        liveDataToObserve.value = AppState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getStocksFromDB()
            liveDataToObserve.postValue(
                AppState.Success(
                    PayloadDTO(stockList = data)
                )
            )
        }
    }
}