package com.example.hlebushek.view

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.PersistableBundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.composetest.ui.theme.ComposeTestTheme
import com.example.hlebushek.R
import com.example.hlebushek.hideKeyboard
import com.example.hlebushek.services.Event
import com.example.hlebushek.toFloat
import dagger.android.support.DaggerFragment
import org.greenrobot.eventbus.EventBus

class SettingsFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTaxRate()
    }

    private fun initTaxRate() {
//        val prefs = activity?.getSharedPreferences(TAX_RATE_TAG, MODE_PRIVATE)
//
//        etTaxRate.setOnEditorActionListener { view, actionId, _ ->
//            when (actionId) {
//                EditorInfo.IME_ACTION_DONE -> {
//                    prefs?.edit()
//                        ?.putFloat(TAX_RATE_TAG, etTaxRate.text.toFloat())
//                        ?.apply()
//
//                    hideKeyboard()
//                    view.clearFocus()
//                    EventBus.getDefault().post(Event.CheckPrice)
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }
//
//        etTaxRate.text = SpannableStringBuilder(
//            prefs?.getFloat(TAX_RATE_TAG, 0.0f).toString()
//        )
    }

    companion object {
        const val TAX_RATE_TAG = "tax_rate"
    }
}

@Composable
fun TaxRateText() {
    Text(text = "Buy/sell tax rate %")
}

@Composable
fun TaxRateSetting() {
    TextField(value = "0,0%",
        onValueChange = {},
        placeholder = { Text("0,0%") }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeTestTheme {

    }
}