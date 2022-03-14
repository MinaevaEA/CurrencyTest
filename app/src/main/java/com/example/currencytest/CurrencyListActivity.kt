package com.example.currencytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CurrencyListActivity : AppCompatActivity(), CurrencyView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterCurrency(DataSource.getCurrencyList(), this)
        Log.d("1","onCreateList")
    }
    override fun onStart() {
        super.onStart()
        Log.d("1","onStartList")
    }

    override fun onResume() {
        super.onResume()
        Log.d("1","onResumeList")
    }

    override fun onPause() {
        super.onPause()
        Log.d("1","onPauseList")
    }

    override fun onStop() {
        super.onStop()
        Log.d("1","onStopList")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("1","onDestroyList")
    }

    override fun openCurrency(dataCurrency: DataCurrency) {
        Log.d("11111", "11111")
        intent = Intent(this, CurrencyActivity::class.java)
        intent.putExtra("123", dataCurrency)
        startActivity(intent)
    }
}