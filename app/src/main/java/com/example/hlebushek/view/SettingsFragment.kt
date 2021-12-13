package com.example.hlebushek.view

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hlebushek.R
import com.example.hlebushek.databinding.SettingsFragmentBinding
import com.example.hlebushek.hideKeyboard
import com.example.hlebushek.services.Event
import com.example.hlebushek.toFloat
import org.greenrobot.eventbus.EventBus

class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private val binding by viewBinding(SettingsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        initTaxRate()
    }

    private fun initTaxRate() = with(binding) {
        val prefs = activity?.getSharedPreferences(TAX_RATE_TAG, MODE_PRIVATE)

        etTaxRate.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    prefs?.edit()
                        ?.putFloat(TAX_RATE_TAG, etTaxRate.text.toFloat())
                        ?.apply()

                    EventBus.getDefault().post(Event(5f))
                    hideKeyboard()
                    etTaxRate.clearFocus()
                    true
                }
                else -> {
                    false
                }
            }
        }

        etTaxRate.text = SpannableStringBuilder(
            prefs?.getFloat(TAX_RATE_TAG, 0.0f).toString()
        )
    }

    companion object {
        const val TAX_RATE_TAG = "tax_rate"
    }
}