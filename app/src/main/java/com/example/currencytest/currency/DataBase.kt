package com.example.currencytest.currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataBuyCurrency(
    var title: String,
    val price: String,
    val number: Int = 0,
    val date : String,
    val generalSumm: Double? = 0.0
) : Parcelable

class DataBase {
    private val dataBaseList = mutableListOf(
        DataBuyCurrency("", "Russia",0,"")
    )

    fun addDataBuyCurrency(data: DataBuyCurrency) {
        dataBaseList.add(data)

    }

    fun getCurrencyBuyList(): List<DataBuyCurrency> {
        return dataBaseList
    }
}

