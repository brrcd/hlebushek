package com.example.hlebushek.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.NonNull
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.App.Companion.TAG
import com.example.hlebushek.AppState
import com.example.hlebushek.R
import com.example.hlebushek.adapters.SearchAdapter
import com.example.hlebushek.databinding.SearchFragmentBinding
import com.example.hlebushek.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment(R.layout.search_fragment) {
    @Inject
    lateinit var viewModel: SearchViewModel
    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val adapter by lazy {
        SearchAdapter(delegate = { item ->
            TODO()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        setSearchInputListeners()
        recyclerView.adapter = adapter
    }

    private fun setSearchInputListeners() = with(binding) {
        searchInputLayout.setEndIconOnClickListener {
            viewModel.getListOfStocksByName(searchStockInput.text.toString())
        }
        searchStockInput.setOnEditorActionListener { _, action, _ ->
            when (action) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.getListOfStocksByName(searchStockInput.text.toString())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.visibility = View.GONE
                appState.payloadDTO.stockList?.let {
                    adapter.setStockList(
                        it
                    )
                }
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    appState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val SEARCH_TAG = "search frag"
        fun newInstance() = SearchFragment()
    }
}