package com.example.currencytest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencytest.currency.BuyCurrencyListActivity
import com.example.currencytest.databinding.ActivityMainBinding
import com.example.currencytest.list.CurrencyListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCurrency.setOnClickListener {
            val intent = CurrencyListActivity.getIntent(this)
            startActivity(intent)
            //TODO (соблюсти инкапсуляцию через getIntent)
        }
        binding.buy.setOnClickListener {
            val intent = Intent(this, BuyCurrencyListActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}