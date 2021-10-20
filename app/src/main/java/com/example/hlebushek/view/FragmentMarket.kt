package com.example.hlebushek.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hlebushek.AppState
import com.example.hlebushek.adapters.StockMarketAdapter
import com.example.hlebushek.databinding.FragmentMarketBinding
import com.example.hlebushek.viewmodel.MarketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class FragmentMarket: Fragment() {
    private val viewModel: MarketViewModel by viewModel()
    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!
    private var adapter = StockMarketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it)} )
        viewModel.getListOfStockMarket()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    companion object{
        const val MARKET_TAG = "market frag"
        fun newInstance() = FragmentMarket()
    }

}