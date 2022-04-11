package com.example.currencytest.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope


import com.example.currencytest.databinding.FragmentAboutCurrencyBinding
import com.example.currencytest.db.Currency
import com.example.currencytest.retrofit.Currency.retrofitService
import com.example.currencytest.retrofit.RetrofitServices
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyFragment : Fragment() {
    private lateinit var binding: FragmentAboutCurrencyBinding
    private lateinit var mService: RetrofitServices
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAboutCurrencyBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBase =
            (requireContext().applicationContext as SubApplication).provideDataBase().currencyDao()
        val currency = arguments?.getString(TAG_FOR_CURRENCY, "") ?: ""
        mService = retrofitService
        binding.title.text = currency
        mService.getConcreteCurrency(currency).enqueue(object : Callback<CurrencyDetail> {
            override fun onResponse(
                call: Call<CurrencyDetail>,
                response: Response<CurrencyDetail>
            ) {
                Log.d("3", "DataCurrency ${response.body()}")
                binding.price.text = response.body()?.rub.toString()
                binding.date.text = response.body()?.date
            }

            override fun onFailure(call: Call<CurrencyDetail>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        binding.buyButton.setOnClickListener {
            val title = binding.title.text
            val numberOfBuy = binding.numberOfBuy.text.toString().toIntOrNull() ?: 0
            val price = binding.price.text.toString().toDoubleOrNull() ?: 0.0
            val date = binding.date
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
