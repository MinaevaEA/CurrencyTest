package com.example.currencytest.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.currencytest.databinding.ListCurrency2Binding
import com.example.currencytest.databinding.ListCurrencyBinding


class AdapterCurrency(
    private var dataSet: List<DataCurrency>,
    private val currencyAdapterListener: CurrencyViewListener
) :
    RecyclerView.Adapter<AdapterCurrency.BaseHolder>() {

    abstract class BaseHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(data: DataCurrency)
    }

    class View1ViewHolder(private val binding: ListCurrencyBinding) : BaseHolder(binding) {
        override fun bind(data: DataCurrency) {
            binding.title.text = data.title
            binding.price.text = data.price.toString()
            binding.number.text = data.number.toString()

        }
    }

    class View2ViewHolder(private val binding: ListCurrency2Binding) : BaseHolder(binding) {
        override fun bind(data: DataCurrency) {
            binding.title.text = data.title
            binding.price.text = data.price.toString()
            binding.number.text = data.number.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_ONE) {
            val binding = ListCurrencyBinding.inflate(layoutInflater, parent, false)
            return View1ViewHolder(binding)
        }
        val binding = ListCurrency2Binding.inflate(layoutInflater, parent, false)
        return View2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (holder is View1ViewHolder) {
            holder.bind(dataSet[position])
        } else if (holder is View2ViewHolder) {
            holder.bind(dataSet[position])
        }

        holder.itemView.setOnClickListener {
            currencyAdapterListener.openCurrency(dataSet[position])
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
//TODO (сделать разметку у четных и нечетных холдеров разные)