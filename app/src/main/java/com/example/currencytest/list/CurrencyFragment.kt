package com.example.currencytest.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope


import com.example.currencytest.databinding.FragmentAboutCurrencyBinding
import com.example.currencytest.db.Currency
import kotlinx.coroutines.launch

class CurrencyFragment : Fragment() {
    private lateinit var binding: FragmentAboutCurrencyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAboutCurrencyBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBase = (requireContext().applicationContext as SubApplication).provideDataBase().currencyDao()
        val currency = arguments?.getString(TAG_FOR_CURRENCY,"")?: ""
        val price = (requireContext().applicationContext as SubApplication).provideDataSource().getDataCurrencyNumber(currency).price
        val date = (requireContext().applicationContext as SubApplication).provideDataSource().getDataCurrencyNumber(currency).date
        binding.title.text = currency
        binding.price.text = price.toString()
        binding.currency.text = date

        binding.buyButton.setOnClickListener {
            val title = binding.title.text
            val numberOfBuy = binding.numberOfBuy.text.toString().toIntOrNull() ?: 0
            val price = binding.price.text.toString().toDoubleOrNull() ?: 0.0
            Toast.makeText(requireContext(), "Покупка произведена!", Toast.LENGTH_SHORT).show()
            lifecycleScope.launch {
            val buyCurrency = Currency(
                title = title.toString(),
                price = price.toString(),
                date = date.toString(),
                number = numberOfBuy, generalSumm = price * numberOfBuy.toDouble()
            )
                dataBase.insertAll(buyCurrency)
            }
        }
    }

    companion object {
        const val TAG_FOR_CURRENCY = "TAG_FOR_CURRENCY"
        fun newInstance(currency: String): CurrencyFragment = CurrencyFragment().apply {
            arguments = Bundle().apply { putString(TAG_FOR_CURRENCY, currency) }
        }
    }
}
