package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.R
import com.example.hlebushek.databinding.MarketRecyclerViewItemBinding
import com.example.hlebushek.model.remote.Stock

class StockMarketAdapter : RecyclerView.Adapter<StockMarketAdapter.StockViewHolder>() {

    private var _binding: MarketRecyclerViewItemBinding? = null
    private val binding get() = _binding!!
    private var stockList = listOf<Stock>()

    fun setStockList(data: List<Stock>) {
        stockList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockMarketAdapter.StockViewHolder {
        _binding = MarketRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StockViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stockList[position])
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    inner class StockViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(stock: Stock) = with(binding) {
            stockName.text = stock.name
            currentSpreadAndCommission.text = "${stock.minPriceIncrement}"
            currentPrice.text = "${stock.minQuantity}"
        }
    }
}