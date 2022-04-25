package com.example.currencytest.currency


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.currencytest.MainFragment
import com.example.currencytest.R
import com.example.currencytest.databinding.FragmentAboutCurrencyBinding
import com.example.currencytest.db.Currency
import com.example.currencytest.list.CurrencyDetail
import com.example.currencytest.list.SubApplication
import com.example.currencytest.retrofit.RetrofitServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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
        mService = (requireContext().applicationContext as SubApplication).provideDataFromNetwork()
            .create()
        binding.title.text = currency
        mService.getConcreteCurrency(currency).enqueue(object : Callback<CurrencyDetail> {
            override fun onResponse(
                call: Call<CurrencyDetail>,
                response: Response<CurrencyDetail>
            ) {
                Log.d("3", "DataCurrency ${response.body()}")
                binding.price.text = response.body()?.rub.toString()
                binding.date.text = response.body()?.date
                if (response.code() == 200) {
                    successLoadingCurrency()
                } else {
                    errorLoadingCurrency()
                }
            }

            override fun onFailure(call: Call<CurrencyDetail>, t: Throwable) {
                errorLoadingCurrency()
            }
        })
        binding.buyButton.setOnClickListener {
            val title = binding.title.text
            val numberOfBuy = binding.numberOfBuy.text.toString().toIntOrNull() ?: 0
            val price = binding.price.text.toString().toDoubleOrNull() ?: 0.0
            val date = binding.date
            Snackbar.make(it, "Покупка произведена!", Snackbar.LENGTH_LONG)
                .setAction("Вернуться в меню", OnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.activity_main, MainFragment.newInstance())
                        .commit()
                }).show()
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

    fun successLoadingCurrency() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.price.visibility = View.VISIBLE
        binding.date.visibility = View.VISIBLE
        binding.title.visibility = View.VISIBLE
        binding.numberOfBuy.visibility = View.VISIBLE
        binding.buyButton.visibility = View.VISIBLE
    }

    fun errorLoadingCurrency() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.errorMsg.visibility = View.VISIBLE
    }

    companion object {
        const val TAG_FOR_CURRENCY = "TAG_FOR_CURRENCY"
        fun newInstance(currency: String): CurrencyFragment = CurrencyFragment().apply {
            arguments = Bundle().apply { putString(TAG_FOR_CURRENCY, currency) }
        }
    }
}
