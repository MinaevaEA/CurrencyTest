package com.example.currencytest.list


import android.util.Log
import com.example.currencytest.retrofit.RetrofitServices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataNetworkInteract(private val dataFromNetwork: RetrofitServices) {
    suspend fun getCurrenciesResponse(): List<DataCurrency> {
        val dataAllCurrency = dataFromNetwork.getAllCurrency()
        val gson = Gson()
        val dataObject = object :
            TypeToken<Map<String, String>>() {}.type // указание формата парсинга данных
        val listMap: Map<String, String> = gson.fromJson(dataAllCurrency, dataObject)
        // с помощью вспомогательного объекта gson конвертируем тело ответа сервера в нужном формате парсинга данных
        val listDataCurrency = listMap.map { i -> DataCurrency(i.key, i.value) }
        Log.d("111111111111111", "Interact")
        return listDataCurrency
    }
}



