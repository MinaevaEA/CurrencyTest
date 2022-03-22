package com.example.currencytest.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.databinding.ActivityBuyCurrencyListBinding
import com.example.currencytest.list.SubApplication

class BuyCurrencyListActivity : AppCompatActivity(){
    private lateinit var  binding: ActivityBuyCurrencyListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyCurrencyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        binding.recyclerView2.adapter = AdapterBuyCurrency((applicationContext as SubApplication).provideDataBase().getCurrencyBuyList())
        Log.d("1", "onCreateList")
    }

}