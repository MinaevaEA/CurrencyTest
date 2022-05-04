package com.example.currencytest.currency_buy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.databinding.ItemBuyCurrencyBinding
import com.example.currencytest.db.Currency
import java.util.ArrayList


class AdapterBuyCurrency:
    RecyclerView.Adapter<AdapterBuyCurrency.BuyViewHolder>() {
    private var dataSetCurrency = ArrayList<Currency>()

    @SuppressLint("NotifyDataSetChanged")
    fun setDataCurrency(newList: List<Currency>) {
        dataSetCurrency.clear()
        dataSetCurrency = newList as ArrayList<Currency>
        notifyDataSetChanged()
    }

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
        holder.bind(dataSetCurrency[position])

    }

    override fun getItemCount() = dataSetCurrency.size

}