package com.example.currencytest.currency_list


import com.example.currencytest.retrofit.RetrofitServices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataNetworkInteract(private val dataFromNetwork: RetrofitServices) {
    suspend fun currencyListInteract(): List<DataCurrency> {
        val dataAllCurrency = dataFromNetwork.getAllCurrency()
        val gson = Gson()
        val dataObject = object :
            TypeToken<Map<String, String>>() {}.type // указание формата парсинга данных
        val listMap: Map<String, String> = gson.fromJson(dataAllCurrency, dataObject)
        // с помощью вспомогательного объекта gson конвертируем тело ответа сервера в нужном формате парсинга данных
        return listMap.map { i -> DataCurrency(i.key, i.value) }
    }
}



