package com.example.hlebushek.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hlebushek.AppState
import com.example.hlebushek.databinding.FragmentMarketBinding
import com.example.hlebushek.viewmodel.FragmentMarketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentMarket: Fragment() {
    private val viewModel: FragmentMarketViewModel by viewModel()
    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {

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