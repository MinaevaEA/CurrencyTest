package com.example.currencytest.list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.example.currencytest.currency.DataBuyCurrency
import com.example.currencytest.databinding.ActivityAboutCurrencyBinding

class CurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutCurrencyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataBase = (applicationContext as SubApplication).provideDataBase()
        val info = intent.getParcelableExtra<DataCurrency>(TAG_FOR_CURRENCY)

        binding.title.text = info?.title
        binding.content.text = info?.price.toString()
        binding.currency.text = info?.number.toString()

        binding.buyButton.setOnClickListener {
            val numberOfBuy = binding.numberOfBuy.text.toString().toIntOrNull() ?: 0
            val price = binding.content.text.toString().toDoubleOrNull() ?: 0.0
            Toast.makeText(applicationContext,"Покупка произведена!",Toast.LENGTH_SHORT).show()
            val buyCurrency = DataBuyCurrency(
                title = info?.title ?: " ",
                price = info?.price ?: 0.0,
                number = numberOfBuy, generalSumm = price * numberOfBuy.toDouble()
            )
            dataBase.addDataBuyCurrency(buyCurrency)
        }
        Log.d("2", "onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("2", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("2", "onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.d("2", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("2", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("2", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("2", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("2", "onDestroy")
    }

    companion object {
        const val TAG_FOR_CURRENCY = "TAG_FOR_CURRENCY"
        fun getIntent(context: Context, dataCurrency: DataCurrency): Intent =
            Intent(context, CurrencyActivity::class.java).apply {
                putExtra(TAG_FOR_CURRENCY, dataCurrency)
            }
    }
}