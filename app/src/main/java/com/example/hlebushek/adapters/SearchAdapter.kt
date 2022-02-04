package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.R
import com.example.hlebushek.databinding.RvItemSearchBinding
import com.example.hlebushek.model.local.Share

class SearchAdapter(private val delegate: (share: Share) -> Unit) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var _binding: RvItemSearchBinding? = null
    private val binding get() = _binding!!
    private var shareList = listOf<Share>()

    fun setShareList(data: List<Share>) {
        shareList = data
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
        holder.bind(shareList[position], delegate)
    }

    override fun getItemCount(): Int {
        return shareList.size
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(share: Share, delegate: (share: Share) -> Unit) = with(binding) {
            tvStockName.text = share.name
            ivAddStockToCurrentTrade.setOnClickListener {
                delegate.invoke(share)
                ivAddStockToCurrentTrade.setImageResource(R.drawable.ic_check)
            }
        }
    }
}