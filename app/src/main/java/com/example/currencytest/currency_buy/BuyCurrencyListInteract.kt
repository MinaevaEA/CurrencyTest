package com.example.currencytest.currency_buy

import com.example.currencytest.db.AppDatabase
import com.example.currencytest.db.Currency

class BuyCurrencyListInteract(private val database: AppDatabase) {
    suspend fun buyCurrencyInteract(): List<Currency> {
        return database.currencyDao().getAll()
    }
}