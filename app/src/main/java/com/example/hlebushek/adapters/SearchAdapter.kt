package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.databinding.RvItemSearchBinding
import com.example.hlebushek.model.remote.Stock

class SearchAdapter(private val delegate: (stock: Stock) -> Unit) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var _binding: RvItemSearchBinding? = null
    private val binding get() = _binding!!
    private var stockList = listOf<Stock>()

    fun setStockList(data: List<Stock>) {
        stockList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        _binding = RvItemSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SearchViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(stockList[position], delegate)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(stock: Stock, delegate: (stock: Stock) -> Unit) = with(binding) {
            tvStockName.text = stock.name
            ivAddStockToCurrentTrade.setOnClickListener {
                delegate.invoke(stock)
            }
        }
    }
}