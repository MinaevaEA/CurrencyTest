package com.example.currencytest.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataCurrency(val title: String, val price: Double, val number: Int = 0) : Parcelable

class DataSource {
    private val dataList = listOf(
        DataCurrency("EUR", 200.9524, ),
        DataCurrency("USD", 400.9524, ),
        DataCurrency("USD", 822.9524, ),
        DataCurrency("USD", 477.9524, ),
        DataCurrency("EUR", 200.9524, ),
        DataCurrency("USD", 400.9524, ),
        DataCurrency("USD", 822.9524, ),
        DataCurrency("USD", 477.9524, ),
        DataCurrency("EUR", 200.9524, ),
        DataCurrency("USD", 400.9524, ),
        DataCurrency("USD", 822.9524, ),
        DataCurrency("USD", 477.9524, )
    )

    fun getCurrencyList(): List<DataCurrency> {
        return dataList
    }
}