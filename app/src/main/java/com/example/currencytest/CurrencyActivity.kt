package com.example.currencytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class CurrencyActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var content: TextView
    private lateinit var currency: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_currency)
        title = findViewById(R.id.title)
        content = findViewById(R.id.content)
        currency = findViewById(R.id.currency)
        val info = intent.getParcelableExtra<DataCurrency>("123")
        title.text = info?.title
        content.text = info?.content
        currency.text = info?.currency.toString()
        Log.d("2","onCreate")

    }
    override fun onStart() {
        super.onStart()
        Log.d("2","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("2","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("2","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("2","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("2","onDestroy")
    }
}