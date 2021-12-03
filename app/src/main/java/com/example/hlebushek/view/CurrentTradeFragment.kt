package com.example.hlebushek.view

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.AppState
import com.example.hlebushek.R
import com.example.hlebushek.databinding.CurrentTradeFragmentBinding
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CurrentTradeFragment : DaggerFragment(R.layout.current_trade_fragment) {
    @Inject
    lateinit var viewModel: CurrentTradeViewModel
    private val binding by viewBinding(CurrentTradeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
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
}