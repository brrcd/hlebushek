package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.databinding.RvItemCurrentTradeBinding
import com.example.hlebushek.model.remote.Stock

class CurrentTradeAdapter : RecyclerView.Adapter<CurrentTradeAdapter.CurrentTradeViewHolder>() {

    private var _binding: RvItemCurrentTradeBinding? = null
    private val binding get() = _binding!!
    private var stockList = listOf<Stock>()

    fun setStockList(data: List<Stock>) {
        stockList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrentTradeViewHolder {
        _binding = RvItemCurrentTradeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CurrentTradeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CurrentTradeViewHolder, position: Int) {
        holder.bind(stockList[position])
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    inner class CurrentTradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(stock: Stock) = with(binding) {
            tvStockName.text = stock.name
        }
    }
}
