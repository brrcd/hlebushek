package com.example.hlebushek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hlebushek.*
import com.example.hlebushek.databinding.RvItemCurrentTradeBinding
import com.example.hlebushek.model.local.Share

class CurrentTradeAdapter : RecyclerView.Adapter<CurrentTradeAdapter.CurrentTradeViewHolder>() {

    private var _binding: RvItemCurrentTradeBinding? = null
    private val binding get() = _binding!!
    private var shareList = listOf<Share>()

    fun setItems(data: List<Share>) {
        shareList = data
        notifyDataSetChanged()
    }

    fun updateItems(data: List<Share>) {
        shareList = data
        notifyItemRangeChanged(0, itemCount)
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

    override fun onBindViewHolder(holder: CurrentTradeViewHolder, position: Int) =
        holder.bind(shareList[position])

    override fun getItemCount(): Int = shareList.size

    inner class CurrentTradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(share: Share) = with(binding) {
            log(share.currency)
            val currency: String = when (share.currency) {
                "rub" -> {
                    RUBLE
                }
                "usd" -> {
                    US_DOLLAR
                }
                "eur" -> {
                    EURO
                }
                else -> {
                    "CURRENCY"
                }
            }
            val purchasePrice = "${share.purchasePrice} $currency"
            val lastCheckedPrice = "${share.lastCheckedPrice} $currency"
            tvStockName.text = share.name
            tvStockPurchasePrice.text = purchasePrice
//            tvStockPurchaseDate.text = share.purchaseDate
            tvStockLatestPrice.text = lastCheckedPrice
            when {
                share.lastCheckedPrice > share.purchasePrice ->
                    ivPurchaseToCurrentRelation.setImageResource(R.drawable.ic_arrow_up)
                share.lastCheckedPrice < share.purchasePrice ->
                    ivPurchaseToCurrentRelation.setImageResource(R.drawable.ic_arrow_down)
                else -> ivPurchaseToCurrentRelation.setImageResource(R.drawable.ic_equals)
            }
        }
    }
}
