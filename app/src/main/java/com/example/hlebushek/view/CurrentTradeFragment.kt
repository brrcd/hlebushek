package com.example.hlebushek.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.states.SearchAppState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.CurrentTradeAdapter
import com.example.hlebushek.databinding.CurrentTradeFragmentBinding
import com.example.hlebushek.db.TradeDatabase
import com.example.hlebushek.eventbus.Event
import com.example.hlebushek.log
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
    lateinit var database: TradeDatabase
    private val binding by viewBinding(CurrentTradeFragmentBinding::bind)
    private val adapter by lazy { CurrentTradeAdapter() }
    private var job: Job? = null

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getSharesFromDB()
        startTradeService.setOnClickListener {
            activity?.startService(Intent(activity, TraderService::class.java))
        }
        stopTradeService.setOnClickListener {
            activity?.stopService(Intent(activity, TraderService::class.java))
        }
        testButton.setOnClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {
                database.clearAllTables()
            }
            viewModel.getSharesFromDB()
        }
    }

    private fun renderData(appState: SearchAppState) = with(binding) {
        when (appState) {
            is SearchAppState.Success -> {
                loadingLayout.setGone()
                adapter.setShareList(appState.listOfShares)
            }
            is SearchAppState.Error -> {
                loadingLayout.setGone()
                Toast.makeText(
                    requireContext(),
                    "Error!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is SearchAppState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event) {
        when (event) {
            is Event.UpdatePrice -> {
                viewModel.getSharesFromDB()
            }
            is Event.OtherOne -> {}
        }
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        job?.cancel()
        super.onStop()
    }
}