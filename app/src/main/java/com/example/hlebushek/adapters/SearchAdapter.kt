package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.R
import com.example.hlebushek.databinding.RvItemSearchBinding
import com.example.hlebushek.model.local.Share

class SearchAdapter(private val delegate: (share: Share) -> Unit) :
    RecyclerView.Adapter<SearchViewHolder>() {

    private var shareList = listOf<Share>()

    fun setShareList(data: List<Share>) {
        shareList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        return SearchViewHolder(
            RvItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(shareList[position], delegate)

    override fun getItemCount(): Int = shareList.size
}

class SearchViewHolder(private val binding: RvItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(share: Share, delegate: (share: Share) -> Unit) = with(binding) {
        tvStockName.text = share.name
        ivAddStockToCurrentTrade.setOnClickListener {
            delegate.invoke(share)
            ivAddStockToCurrentTrade.setImageResource(com.example.hlebushek.R.drawable.ic_check)
        }
    }
}