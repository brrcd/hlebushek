package com.example.hlebushek.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.R
import com.example.hlebushek.adapters.CurrentTradeAdapter
import com.example.hlebushek.databinding.CurrentTradeFragmentBinding
import com.example.hlebushek.db.TradeDatabase
import com.example.hlebushek.eventbus.Event
import com.example.hlebushek.services.TraderService
import com.example.hlebushek.setGone
import com.example.hlebushek.setVisible
import com.example.hlebushek.states.CurrentTradeState
import com.example.hlebushek.toast
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

        initTestButtons()
    }

    private fun initTestButtons() = with(binding) {
        startTradeService.setOnClickListener {
            if (!TraderService.isRunning)
                activity?.startService(Intent(activity, TraderService::class.java))
            else
                toast("Already running")
        }
        stopTradeService.setOnClickListener {
            if (TraderService.isRunning)
                activity?.stopService(Intent(activity, TraderService::class.java))
            else
                toast("Nothing to stop")
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
                toast("Error")
            }
            is CurrentTradeState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event) {
        when (event) {
            is Event.UpdatePrice -> {
                viewModel.updatePrices()
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