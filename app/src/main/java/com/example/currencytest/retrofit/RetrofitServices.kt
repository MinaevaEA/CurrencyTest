package com.example.currencytest.retrofit

import com.example.currencytest.currency_list.CurrencyDetail
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("latest/currencies.json")
    suspend fun getAllCurrency(): JsonObject

    @GET("latest/currencies/{valute}/rub.json")
    suspend fun getAboutCurrency(@Path("valute") valute: String): CurrencyDetail

    companion object {
        private const val baseUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/"
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}