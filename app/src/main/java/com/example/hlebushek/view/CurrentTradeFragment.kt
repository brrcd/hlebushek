package com.example.hlebushek.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.AppState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.CurrentTradeAdapter
import com.example.hlebushek.databinding.CurrentTradeFragmentBinding
import com.example.hlebushek.db.StockDatabase
import com.example.hlebushek.eventbus.Event
import com.example.hlebushek.services.TraderService
import com.example.hlebushek.setGone
import com.example.hlebushek.setVisible
import com.example.hlebushek.viewmodel.CurrentTradeViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class CurrentTradeFragment : DaggerFragment(R.layout.current_trade_fragment) {
    @Inject
    lateinit var viewModel: CurrentTradeViewModel

    @Inject
    lateinit var database: StockDatabase
    private val binding by viewBinding(CurrentTradeFragmentBinding::bind)
    private val adapter by lazy { CurrentTradeAdapter() }
    private var job: Job? = null

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getStocksFromDB()

        binding.startTradeService.setOnClickListener {
            activity?.startService(Intent(activity, TraderService::class.java))
        }
        binding.stopTradeService.setOnClickListener {
            activity?.stopService(Intent(activity, TraderService::class.java))
        }
        binding.testButton.setOnClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {
                database.clearAllTables()
            }
            viewModel.getStocksFromDB()
        }

    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.setGone()
                appState.payloadDTO.stockList?.let { adapter.setStockList(it) }
            }
            is AppState.Error -> {
                loadingLayout.setGone()
                Toast.makeText(
                    requireContext(),
                    "Error!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is AppState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event) {
        when (event) {
            is Event.UpdatePrice -> {
                // todo fix issue with only last stock is updated
                viewModel.getStocksFromDB()
            }
            is Event.OtherOne -> {}
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        job?.cancel()
    }
}