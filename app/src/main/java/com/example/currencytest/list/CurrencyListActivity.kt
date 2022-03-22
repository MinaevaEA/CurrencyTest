package com.example.currencytest.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencytest.databinding.ActivityCurrencyBinding

class CurrencyListActivity : AppCompatActivity(), CurrencyViewListener {
    private lateinit var binding: ActivityCurrencyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = AdapterCurrency((applicationContext as SubApplication).provideDataSource().getCurrencyList(), this)
        Log.d("1", "onCreateList")
    }

    override fun onStart() {
        super.onStart()
        Log.d("1", "onStartList")
    }

    override fun onResume() {
        super.onResume()
        Log.d("1", "onResumeList")
    }

    override fun onPause() {
        super.onPause()
        Log.d("1", "onPauseList")
    }

    override fun onStop() {
        super.onStop()
        Log.d("1", "onStopList")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("1", "onDestroyList")
    }

    override fun openCurrency(dataCurrency: DataCurrency) {
        Log.d("11111", "11111")
        val intent = CurrencyActivity.getIntent(this, dataCurrency)
        startActivity(intent)
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, CurrencyListActivity::class.java)
    }
}