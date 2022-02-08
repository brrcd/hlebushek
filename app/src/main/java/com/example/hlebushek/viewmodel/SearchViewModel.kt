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
            lastPrices.forEach {
                if (it.figi == share.figi)
                    share.purchasePrice =
                        it.price.units.toDouble() + it.price.nano.convertToFraction()
            }
            repository.addShareToCurrentTrade(share)
        }
    }

    fun getSharesByName(name: String) {
        liveDataToObserve.value = SearchState.Loading

        // todo remove
        log(name)

        viewModelScope.launch(Dispatchers.IO) {
            val shares = repository.getListOfShares()
            val filteredShares = filterShares(shares, name)
            val mappedShares = mapShareOrBuilderToShare(filteredShares)
            liveDataToObserve.postValue(
                SearchState.Success(
                    mappedShares
                )
            )
        }
    }

    private fun filterShares(list: List<ShareOrBuilder>, name: String): List<ShareOrBuilder> =
        list.filter {
            it.name.lowercase().contains(name.lowercase())
        }

    private fun mapShareOrBuilderToShare(list: List<ShareOrBuilder>): List<Share> =
        list.map {
            Share(
                it.figi,
                it.name,
                it.ticker,
                it.lot
            )
        }
}