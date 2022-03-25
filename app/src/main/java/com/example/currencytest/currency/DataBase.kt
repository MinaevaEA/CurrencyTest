package com.example.currencytest.currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataBuyCurrency(
    var title: String,
    val price: Double,
    val number: Int = 0,
    val generalSumm: Double? = 0.0
) : Parcelable

class DataBase {
    private val dataBaseList = mutableListOf(
        DataBuyCurrency("EUR", 103.9524),
        DataBuyCurrency("USD", 120.3996),
        DataBuyCurrency("EUR", 110.9524),
        DataBuyCurrency("USD", 114.3996)
    )

    fun addDataBuyCurrency(data: DataBuyCurrency) {
        dataBaseList.add(data)

    }

    fun getCurrencyBuyList(): List<DataBuyCurrency> {
        return dataBaseList
    }
}

