package com.example.currencytest.currency_buy

import com.example.currencytest.db.AppDatabase
import com.example.currencytest.db.Currency

class BuyCurrencyListInteractor(private val database: AppDatabase) {

    suspend fun BuyCurrencyInteractor(): List<Currency> {
        return database.currencyDao().getAll()
    }
}