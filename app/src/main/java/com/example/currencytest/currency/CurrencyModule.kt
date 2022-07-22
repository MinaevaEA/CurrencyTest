package com.example.currencytest.currency

import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CurrencyModule {
    @Provides
    fun getCurrencyInteract(
        dataConcreteCurrency: RetrofitServices,
        database: AppDatabase
    ): CurrencyInteract {
        return CurrencyInteract(dataConcreteCurrency, database)
    }
}