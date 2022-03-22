package com.example.currencytest.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.databinding.ListBuyCurrencyBinding


class AdapterBuyCurrency(
    private var dataBuyList: List<DataBuyCurrency>
) :
    RecyclerView.Adapter<AdapterBuyCurrency.BuyViewHolder>() {

    class BuyViewHolder(private val binding: ListBuyCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataBuyCurrency) {
            binding.title.text = data.title
            binding.price.text = data.price.toString()
            binding.number.text = data.number.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListBuyCurrencyBinding.inflate(layoutInflater, parent, false)
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bind(dataBuyList[position])

    }

    override fun getItemCount() = dataBuyList.size

}