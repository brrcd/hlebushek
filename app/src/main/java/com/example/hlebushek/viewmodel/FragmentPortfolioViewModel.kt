package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hlebushek.AppState
import com.example.hlebushek.model.Payload
import com.example.hlebushek.model.Portfolio
import com.example.hlebushek.model.Stock
import com.example.hlebushek.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FragmentPortfolioViewModel(private val repository: Repository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private var job: Job? = null

    fun getLiveData() = liveDataToObserve

    fun getPortfolio() {
        liveDataToObserve.value = AppState.Loading
        job = CoroutineScope(Dispatchers.Default).launch {
            val data = repository.getPortfolio()
            val positions: List<Stock>? = data?.payload?.positions
            var totalValue = 0.0
            positions?.forEach{
                totalValue += it.expectedYield?.value!!
                totalValue += (it.averagePositionPrice?.value?.times(it.lots!!)!!)
            }
            liveDataToObserve.postValue(data?.let { AppState.Success(Portfolio(totalValue, null)) })
        }
    }
}