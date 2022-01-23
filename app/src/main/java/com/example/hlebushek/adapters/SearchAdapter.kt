package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.model.remote.Stock

class SearchAdapter(private val delegate: (stock: Stock) -> Unit) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var stockList = listOf<Stock>()

    fun setStockList(data: List<Stock>) {
        stockList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(stockList[position], delegate)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(stock: Stock, delegate: (stock: Stock) -> Unit) {
//            tvStockName.text = stock.name
//            ivAddStockToCurrentTrade.setOnClickListener {
//                delegate.invoke(stock)
//            }
        }
    }
}