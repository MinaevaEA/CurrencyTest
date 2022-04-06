package com.example.currencytest.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
data class DataCurrency(val valute: String, val country: String?) : Parcelable

@Parcelize
data class DataCurrencyNumber(val date: String?, val price: Double?) : Parcelable

@Parcelize
data class GlobalDataCurrency(val country: String, val date: String, val price: Double) : Parcelable

class DataSource {
    //TODO делаем из листа хэшмап, где валюта - ключ, и глобальный объект-значение состоящее из страны, даты, курса
    private val dataHashMap = hashMapOf(
        "RU" to GlobalDataCurrency("Russia", "20 / 11 / 2021", 2334.756),
        "US" to GlobalDataCurrency("USA", "20 / 11 / 2021", 2334.756),
        "EUR" to GlobalDataCurrency("Ukraine", "20 / 11 / 2021", 2334.756),
        "EUR" to GlobalDataCurrency("Polish", "20 / 11 / 2021", 2334.756),
        "EUR" to GlobalDataCurrency("Germany", "20 / 11 / 2021", 2334.756)
    )

    //TODO преобразовать DataCurrency (валюта страна) и изменить getCurrencyList(из хешмап получить лист с объектами DataCurrency)
    //TODO создать новую функцию с параметром валюта string(тип), которая возвращает Объект(дата, курс)

    fun getCurrencyList(): List<DataCurrency> {
        val keys = dataHashMap.keys
        val list = ArrayList<DataCurrency>()
        for (i in keys) {
            val country = dataHashMap[i]?.country
            list.add(DataCurrency(valute = i, country = country))
        }
        return list
    }

    fun getDataCurrencyNumber(valute: String): DataCurrencyNumber {
        val date = dataHashMap[valute]?.date
        val price = dataHashMap[valute]?.price
        return DataCurrencyNumber(date = date, price = price)
    }

}


