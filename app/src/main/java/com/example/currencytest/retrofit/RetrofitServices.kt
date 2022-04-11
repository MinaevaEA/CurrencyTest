package com.example.currencytest.retrofit

import com.example.currencytest.list.CurrencyDetail
import com.example.currencytest.list.DataCurrency
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("latest/currencies.json")
    fun getCurrencyList(): Call<ResponceTest>

    @GET("latest/currencies.json")
    fun getAll(): Call<JsonObject>

    @GET("latest/currencies/{valute}/rub.json")
    fun getConcreteCurrency(@Path("valute") valute: String): Call<CurrencyDetail>
}