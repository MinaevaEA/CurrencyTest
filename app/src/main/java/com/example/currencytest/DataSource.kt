package com.example.currencytest

data class DataCurrency(val title: String, val content: String, val currency: Int)

class DataSource {
    companion object {
        fun getCurrencyList(): List<DataCurrency> {
            return listOf(
                DataCurrency("title", "content", 25),
                DataCurrency("title2", "content2", 27),
                DataCurrency("title3", "content3", 595),
                DataCurrency("title4", "content4", 669)
            )
        }
    }
}