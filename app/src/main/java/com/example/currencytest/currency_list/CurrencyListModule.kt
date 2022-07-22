package com.example.currencytest.currency_list

import com.example.currencytest.currency.CurrencyInteract
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CurrencyListModule {
    @Provides
    fun getDataNetworkInteract(
        dataConcreteCurrency: RetrofitServices
    ): DataNetworkInteract {
        return DataNetworkInteract(dataConcreteCurrency)
    }
    @Provides
    fun getListAdapter(currencyAdapterListener: CurrencyViewListener): CurrencyListAdapter{
        return CurrencyListAdapter(currencyAdapterListener)
    }
}