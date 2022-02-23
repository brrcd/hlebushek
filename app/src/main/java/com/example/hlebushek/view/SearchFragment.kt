package com.example.hlebushek.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.states.SearchState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.SearchAdapter
import com.example.hlebushek.databinding.SearchFragmentBinding
import com.example.hlebushek.setGone
import com.example.hlebushek.setVisible
import com.example.hlebushek.toast
import com.example.hlebushek.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment(R.layout.search_fragment) {
    @Inject
    lateinit var viewModel: SearchViewModel
    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val adapter by lazy {
        SearchAdapter(delegate = { share ->
            viewModel.addShareToCurrentTrade(share)
        })
    }
    //TODO price chart on rv item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        setSearchInputListeners()
        recyclerView.adapter = adapter
    }

    private fun setSearchInputListeners() = with(binding) {
        searchInputLayout.setEndIconOnClickListener {
            viewModel.getSharesByName(searchStockInput.text.toString())
        }
        searchStockInput.setOnEditorActionListener { _, action, _ ->
            when (action) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.getSharesByName(searchStockInput.text.toString())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun renderData(appState: SearchState) = with(binding) {
        when (appState) {
            is SearchState.Success -> {
                loadingLayout.setGone()
                adapter.setShareList(appState.listOfShares)
            }
            is SearchState.Error -> {
                loadingLayout.setGone()
                toast(appState.errorMessage)
            }
            is SearchState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }
}