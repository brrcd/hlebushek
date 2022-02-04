package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.databinding.RvItemCurrentTradeBinding
import com.example.hlebushek.model.local.Share

class CurrentTradeAdapter : RecyclerView.Adapter<CurrentTradeAdapter.CurrentTradeViewHolder>() {

    private var _binding: RvItemCurrentTradeBinding? = null
    private val binding get() = _binding!!
    private var shareList = listOf<Share>()

    fun setShareList(data: List<Share>) {
        shareList = data
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
        holder.bind(shareList[position])
    }

    override fun getItemCount(): Int {
        return shareList.size
    }

    inner class CurrentTradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(share: Share) = with(binding) {
            tvStockName.text = share.name
            tvStockPurchasePrice.text = share.purchasePrice.toString()
//            tvStockPurchaseDate.text = share.purchaseDate
            tvStockLatestPrice.text = share.lastCheckedPrice.toString()
        }
    }
}
