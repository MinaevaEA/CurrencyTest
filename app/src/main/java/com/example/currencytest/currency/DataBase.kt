package com.example.currencytest.currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataBuyCurrency(var title: String, val price: Double, val number: Int) : Parcelable

class DataBase {
    val dataBaseList = mutableListOf(
        DataBuyCurrency("EUR", 103.9524, 25),
        DataBuyCurrency("USD", 120.3996, 27),
        DataBuyCurrency("EUR", 110.9524, 595),
        DataBuyCurrency("USD", 114.3996, 669)
    )

    fun addDataBuyCurrency(data: DataBuyCurrency){
        dataBaseList.add(data)

    }
    fun getCurrencyBuyList(): List<DataBuyCurrency> {
        return dataBaseList
    }
}

