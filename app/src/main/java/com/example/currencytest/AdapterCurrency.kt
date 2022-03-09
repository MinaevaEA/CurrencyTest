package com.example.currencytest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCurrency(
    private val dataSet: List<DataCurrency>,
   private val currencyAdapterListener: CurrencyView
) :
    RecyclerView.Adapter<AdapterCurrency.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        private val content: TextView = view.findViewById(R.id.content)
        private val currency: TextView = view.findViewById(R.id.currency)

        fun bind(data: DataCurrency) {
            title.text = data.title
            content.text = data.content
            currency.text = data.currency.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_currency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.title.setOnClickListener {
            Log.d("key", "value")
            currencyAdapterListener.openCurrency(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size
}