package com.example.hlebushek.view

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.inputmethod.EditorInfo
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.*
import com.example.hlebushek.databinding.SettingsFragmentBinding
import com.example.hlebushek.states.SettingsState
import com.example.hlebushek.viewmodel.SettingsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingsFragment : DaggerFragment(R.layout.settings_fragment) {
    @Inject
    lateinit var viewModel: SettingsViewModel
    private val binding by viewBinding(SettingsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getSettings()

        setTaxRateSettingListener()
    }

    private fun renderData(appState: SettingsState) = with(binding) {
        when (appState) {
            is SettingsState.Success -> {
                loadingLayout.setGone()
                etTaxRate.text = SpannableStringBuilder(
                    appState.settings.taxRate.toString()
                )
            }
            is SettingsState.Error -> {
                loadingLayout.setGone()
            }
            is SettingsState.Loading -> {
                loadingLayout.setVisible()
            }
        }
    }

    private fun setTaxRateSettingListener() = with(binding) {
        etTaxRate.setOnEditorActionListener { view, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    viewModel.saveSettings(etTaxRate.text.toFloat())
                    hideKeyboard()
                    view.clearFocus()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}