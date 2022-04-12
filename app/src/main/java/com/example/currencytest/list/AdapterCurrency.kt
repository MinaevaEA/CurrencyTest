package com.example.currencytest.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.currencytest.databinding.ListCurrencyItem2Binding
import com.example.currencytest.databinding.ListCurrencyItemBinding
import java.util.ArrayList


class AdapterCurrency(
    private val currencyAdapterListener: CurrencyViewListener
) :
    RecyclerView.Adapter<AdapterCurrency.BaseHolder>() {
    private var dataSet = ArrayList<DataCurrency>()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<DataCurrency>) {
        dataSet.clear()
        dataSet = newList as ArrayList<DataCurrency>
        notifyDataSetChanged()
    }

    abstract class BaseHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(data: DataCurrency)
    }

    class View1ViewHolder(private val binding: ListCurrencyItemBinding) : BaseHolder(binding) {
        override fun bind(data: DataCurrency) {
            binding.title.text = data.valute
            binding.price.text = data.country.toString()
        }
    }

    class View2ViewHolder(private val binding: ListCurrencyItem2Binding) : BaseHolder(binding) {
        override fun bind(data: DataCurrency) {
            binding.title.text = data.valute
            binding.price.text = data.country.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_ONE) {
            val binding = ListCurrencyItemBinding.inflate(layoutInflater, parent, false)
            return View1ViewHolder(binding)
        }
        val binding = ListCurrencyItem2Binding.inflate(layoutInflater, parent, false)
        return View2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (holder is View1ViewHolder) {
            holder.bind(dataSet[position])
        } else if (holder is View2ViewHolder) {
            holder.bind(dataSet[position])
        }

        holder.itemView.setOnClickListener {
            currencyAdapterListener.openCurrency(dataSet[position].valute)
        }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            VIEW_TYPE_ONE
        } else {
            VIEW_TYPE_TWO
        }
    }

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }
}
