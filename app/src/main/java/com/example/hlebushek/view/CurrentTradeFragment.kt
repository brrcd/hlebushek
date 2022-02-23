package com.example.hlebushek.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.R
import com.example.hlebushek.adapters.CurrentTradeAdapter
import com.example.hlebushek.databinding.CurrentTradeFragmentBinding
import com.example.hlebushek.db.TradeDatabase
import com.example.hlebushek.setGone
import com.example.hlebushek.setVisible
import com.example.hlebushek.states.CurrentTradeState
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import com.example.hlebushek.viewmodel.MainActivityViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.*
import javax.inject.Inject

class CurrentTradeFragment : DaggerFragment(R.layout.current_trade_fragment) {
    @Inject
    lateinit var viewModel: CurrentTradeViewModel

    @Inject
    lateinit var database: TradeDatabase
    private val binding by viewBinding(CurrentTradeFragmentBinding::bind)
    private val adapter by lazy { CurrentTradeAdapter() }
    private var job: Job? = null
    private var activity: MainActivity = this.context as MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getSharesFromDB()

        initTestButtons()
    }

    private fun initTestButtons() = with(binding) {
        startTradeService.setOnClickListener {
            if (!MainActivityViewModel.isRunning)
                activity.viewModel.startMonitor()
            else
                Toast.makeText(requireContext(), "Already running.", Toast.LENGTH_SHORT).show()
        }
        stopTradeService.setOnClickListener {
            if (MainActivityViewModel.isRunning)
                activity.viewModel.stopMonitor()
            else
                Toast.makeText(requireContext(), "Nothing to stop.", Toast.LENGTH_SHORT).show()
        }
        testButton.setOnClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {
                database.clearAllTables()
            }
            viewModel.getSharesFromDB()
        }
    }

    private fun renderData(appState: CurrentTradeState) = with(binding) {
        when (appState) {
            is CurrentTradeState.InitSuccess -> {
                loadingLayout.setGone()
                adapter.setItems(appState.listOfShares)
            }
            is CurrentTradeState.UpdateSuccess -> {
                loadingLayout.setGone()
                adapter.updateItems(appState.listOfShares)
            }
            is CurrentTradeState.Error -> {
                loadingLayout.setGone()
                Toast.makeText(
                    requireContext(),
                    "Error!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is CurrentTradeState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }

    override fun onStop() {
        job?.cancel()
        super.onStop()
    }
}