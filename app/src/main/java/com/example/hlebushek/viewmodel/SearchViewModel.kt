package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.AppState
import com.example.hlebushek.model.local.PayloadDTO
import com.example.hlebushek.model.remote.Stock
import com.example.hlebushek.model.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getListOfStocksByName(stockName: String) {
        liveDataToObserve.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getListOfStockMarket()?.payload?.instruments
                ?.filter {
                    it.name!!.lowercase().contains(stockName.lowercase())
                }

            if (data.isNullOrEmpty()) { // if filtered data is empty or null
                liveDataToObserve.postValue(
                    AppState.Error("Enter other stock name")
                )
            } else {
                liveDataToObserve.postValue(
                    AppState.Success(
                        PayloadDTO(
                            stockList = data
                        )
                    )
                )
            }
        }
    }

    fun getLastPrice(stock: Stock) {
        liveDataToObserve.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val lastPrice = repository.getOrderbookByFigi(stock.figi)?.payload?.lastPrice
            if (lastPrice == 0.0 || lastPrice == null){
                liveDataToObserve.postValue(
                    AppState.Error("Error while getting last price")
                )
            } else {
                stock.purchasePrice = lastPrice
                repository.addStockToCurrentTrade(stock)
                liveDataToObserve.postValue(AppState.Success(PayloadDTO()))
            }
        }
    }

    fun addStockToDB(stock: Stock) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStockToCurrentTrade(stock)
        }
    }
}