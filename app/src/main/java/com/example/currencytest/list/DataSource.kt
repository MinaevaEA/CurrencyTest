package com.example.currencytest.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataCurrency(val valute: String, val country: String?) : Parcelable

data class CurrencyDetail(val date: String, val rub: Double)




