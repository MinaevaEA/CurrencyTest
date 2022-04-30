package com.example.currencytest.currency

import com.example.currencytest.currency_list.CurrencyDetail
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.db.Currency
import com.example.currencytest.retrofit.RetrofitServices

class CurrencyInteract(
    private val dataConcreteCurrency: RetrofitServices,
    private val database: AppDatabase
) {
    suspend fun concreteCurrencyInteract(valute: String): CurrencyDetail {
        return dataConcreteCurrency.getAboutCurrency(valute)
    }

    suspend fun insertCurrencyInteractor(currency: Currency): Long {
        return database.currencyDao().insertAll(currency)
    }
}