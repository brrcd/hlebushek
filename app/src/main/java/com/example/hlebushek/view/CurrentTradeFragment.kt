package com.example.hlebushek.view

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.AppState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.CurrentTradeAdapter
import com.example.hlebushek.databinding.CurrentTradeFragmentBinding
import com.example.hlebushek.setGone
import com.example.hlebushek.setVisible
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CurrentTradeFragment : DaggerFragment(R.layout.current_trade_fragment) {
    @Inject
    lateinit var viewModel: CurrentTradeViewModel
    private val binding by viewBinding(CurrentTradeFragmentBinding::bind)
    private val adapter by lazy { CurrentTradeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getStocksFromD()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.setGone()
                appState.payloadDTO.stockList?.let { adapter.setStockList(it) }
            }
            is AppState.Error -> {

            }
            is AppState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }
}