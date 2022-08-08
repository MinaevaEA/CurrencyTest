package com.example.currencytest.retrofit

import com.example.currencytest.currency_list.CurrencyDetail
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("latest/currencies.json")
    suspend fun getAllCurrency(): JsonObject

    @GET("latest/currencies/{valute}/rub.json")
    suspend fun getAboutCurrency(@Path("valute") valute: String): CurrencyDetail

}