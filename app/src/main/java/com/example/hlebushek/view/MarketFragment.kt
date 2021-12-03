package com.example.hlebushek.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.AppState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.StockMarketAdapter
import com.example.hlebushek.databinding.MarketFragmentBinding
import com.example.hlebushek.viewmodel.MarketViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MarketFragment : DaggerFragment(R.layout.market_fragment) {
    @Inject
    lateinit var viewModel: MarketViewModel
    private val binding by viewBinding(MarketFragmentBinding::bind)
    private val adapter = StockMarketAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
//        viewModel.getListOfStockMarket()
        viewModel.getOrderbook("BBG000BVPV84", 1)
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
//                appState.payloadDTO.stockList?.let { adapter.setStockList(it) }
//                recyclerView.adapter = adapter
                testText.text = appState.payloadDTO.currentPrice
            }
            is AppState.Error -> {

            }
            is AppState.Loading -> {

            }
        }
    }

    companion object {
        const val MARKET_TAG = "market frag"
        fun newInstance() = MarketFragment()
    }

}