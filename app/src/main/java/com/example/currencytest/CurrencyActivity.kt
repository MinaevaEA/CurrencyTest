package com.example.currencytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CurrencyActivity : AppCompatActivity() {
    private lateinit var title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_currency)
        title = findViewById(R.id.title)
        val count = intent.getStringExtra("123")
        title.text = count
    }
}