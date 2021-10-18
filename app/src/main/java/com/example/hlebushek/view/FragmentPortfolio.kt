package com.example.hlebushek.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hlebushek.AppState
import com.example.hlebushek.databinding.FragmentPortfolioBinding
import com.example.hlebushek.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentPortfolio : Fragment() {
    private val viewModel: PortfolioViewModel by viewModel()
    private var _binding: FragmentPortfolioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        binding.buttonRegistration.setOnClickListener {
            viewModel.getPortfolio()
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                textViewPortfolioTotalValue.text = appState.portfolio.totalValue.toString()
            }
            is AppState.Error -> {

            }
            is AppState.Loading -> {

            }
        }
    }

    companion object{
        const val PORTFOLIO_TAG = "portfolio frag"
        fun newInstance() = FragmentPortfolio()
    }
}