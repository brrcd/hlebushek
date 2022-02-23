package com.example.hlebushek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hlebushek.convertToFraction
import com.example.hlebushek.log
import com.example.hlebushek.model.local.Share
import com.example.hlebushek.states.SearchState
import com.example.hlebushek.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tinkoff.piapi.contract.v1.ShareOrBuilder
import javax.inject.Inject

class SearchViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<SearchState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun addShareToCurrentTrade(share: Share) {
        viewModelScope.launch(Dispatchers.IO) {
            val lastPrices = repository.getListOfLastPrices(listOf(share))
            lastPrices.forEach { lastPrice ->
                if (lastPrice.figi == share.figi) {
                    share.purchasePrice = lastPrice.price

                    // todo remove
                    log(lastPrice.time)
                }
            }
            repository.addShareToCurrentTrade(share)
        }
    }

    fun getSharesByName(name: String) {
        liveDataToObserve.value = SearchState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val shares = repository.getListOfShares()
            val filteredShares = filterShares(shares, name)
            liveDataToObserve.postValue(
                SearchState.Success(
                    filteredShares
                )
            )
        }
    }

    private fun filterShares(list: List<Share>, name: String): List<Share> =
        list.filter {
            it.name.lowercase().contains(name.lowercase())
        }
}