package com.example.currencytest.currency_buy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.databinding.ItemBuyCurrencyBinding
import com.example.currencytest.db.Currency


class AdapterBuyCurrency(
    private var dataBuyList: List<Currency>
) :
    RecyclerView.Adapter<AdapterBuyCurrency.BuyViewHolder>() {

    class BuyViewHolder(private val binding: ItemBuyCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Currency) {
            binding.title.text = data.title
            binding.price.text = data.price
            binding.number.text = data.number.toString()
            binding.generalCurrency.text = data.generalSumm.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBuyCurrencyBinding.inflate(layoutInflater, parent, false)
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bind(dataBuyList[position])

    }

    override fun getItemCount() = dataBuyList.size

}