package com.example.hlebushek.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.hlebushek.AppState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.SearchAdapter
import com.example.hlebushek.setGone
import com.example.hlebushek.setVisible
import com.example.hlebushek.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment(R.layout.search_fragment) {
    @Inject
    lateinit var viewModel: SearchViewModel
    private val adapter by lazy {
        SearchAdapter(delegate = { stock ->
            viewModel.addStockToDB(stock)
            //TODO get last price according to successfulness of order
            viewModel.getLastPrice(stock)
        })
    }
    //TODO price chart on rv item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        setSearchInputListeners()
//        recyclerView.adapter = adapter
    }

    private fun setSearchInputListeners() {
//        searchInputLayout.setEndIconOnClickListener {
//            viewModel.getListOfStocksByName(searchStockInput.text.toString())
//        }
//        searchStockInput.setOnEditorActionListener { _, action, _ ->
//            when (action) {
//                EditorInfo.IME_ACTION_SEARCH -> {
//                    viewModel.getListOfStocksByName(searchStockInput.text.toString())
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }
    }

    private fun renderData(appState: AppState) {
//        when (appState) {
//            is AppState.Success -> {
//                loadingLayout.setGone()
//                appState.payloadDTO.stockList?.let {
//                    adapter.setStockList(
//                        it
//                    )
//                }
//            }
//            is AppState.Error -> {
//                loadingLayout.setGone()
//                Toast.makeText(
//                    requireContext(),
//                    appState.errorMessage,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            is AppState.Loading -> {
//                loadingLayout.setVisible()
//            }
//        }
    }

    companion object {
        const val SEARCH_TAG = "search frag"
        fun newInstance() = SearchFragment()
    }
}