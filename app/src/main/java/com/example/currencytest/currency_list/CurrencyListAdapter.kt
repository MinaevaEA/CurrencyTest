package com.example.currencytest.currency_list

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.currencytest.R
import com.example.currencytest.databinding.ItemCurrencyEvenBinding
import com.example.currencytest.databinding.ItemCurrencyOddBinding
import java.util.ArrayList


class CurrencyListAdapter(
    private val currencyAdapterListener: CurrencyViewListener
) :
    RecyclerView.Adapter<CurrencyListAdapter.BaseCurrencyHolder>() {
    private var dataSet = ArrayList<DataCurrency>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<DataCurrency>) {
        dataSet.clear()
        dataSet.addAll(newList)
        Log.d("1","setData")
        notifyDataSetChanged()
    }

    abstract class BaseCurrencyHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(data: DataCurrency)
    }

    class OddCurrencyItemViewHolder(private val binding: ItemCurrencyOddBinding) :
        BaseCurrencyHolder(binding) {
        override fun bind(data: DataCurrency) {
            binding.title.text = data.valute
            binding.country.text = data.country
        }
    }

    class EvenCurrencyItemViewHolder(private val binding: ItemCurrencyEvenBinding) :
        BaseCurrencyHolder(binding) {
        override fun bind(data: DataCurrency) {
            binding.title.text = data.valute
            binding.country.text = data.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCurrencyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_ODD) {
            val binding = ItemCurrencyOddBinding.inflate(layoutInflater, parent, false)
            return OddCurrencyItemViewHolder(binding)
        }
        val binding = ItemCurrencyEvenBinding.inflate(layoutInflater, parent, false)
        return EvenCurrencyItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseCurrencyHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener {

            currencyAdapterListener.onCurrencyClicked(dataSet[position].valute)
        }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            VIEW_TYPE_ODD
        } else {
            VIEW_TYPE_EVEN
        }
    }

    companion object {
        const val VIEW_TYPE_ODD = 1
        const val VIEW_TYPE_EVEN = 2
    }
}

interface CurrencyViewListener {
    fun onCurrencyClicked(currencyPosition: String)
}
