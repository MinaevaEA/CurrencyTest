package com.example.currencytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var buttonCurrency: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCurrency = findViewById(R.id.btn_currency)
        buttonCurrency.setOnClickListener {
            intent = Intent(this, CurrencyListActivity::class.java)
            startActivity(intent)
        }
    }
}