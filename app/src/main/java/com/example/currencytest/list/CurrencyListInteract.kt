package com.example.currencytest.list


import android.util.Log
import com.example.currencytest.retrofit.RetrofitServices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CurrencyListInteract(private val dataFromNetwork: RetrofitServices) {
    suspend fun getCurrenciesResponse(): List<DataCurrency> {
        val data = dataFromNetwork.getAllCurrency()
        val gson = Gson()
        val i = object :
            TypeToken<Map<String, String>>() {}.type // указание формата парсинга данных
        val list: Map<String, String> = gson.fromJson(data, i)
        // с помощью вспомогательного объекта gson конвертируем тело ответа сервера в нужном формате парсинга данных
        val list2 = list.map { i -> DataCurrency(i.key, i.value) }
        Log.d("111111111111111", "Interact")
        return list2
    }
}


