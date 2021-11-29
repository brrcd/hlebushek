package com.example.hlebushek.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hlebushek.AppState
import com.example.hlebushek.adapters.SearchAdapter
import com.example.hlebushek.databinding.SearchFragmentBinding
import com.example.hlebushek.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment() {
    @Inject
    lateinit var viewModel: SearchViewModel
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with (binding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        searchInputLayout.setEndIconOnClickListener {
            viewModel.getListOfStocks(searchStockInput.text.toString())
        }
        recyclerView.adapter = adapter
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