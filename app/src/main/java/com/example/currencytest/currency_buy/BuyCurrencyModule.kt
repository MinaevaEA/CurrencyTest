package com.example.currencytest.currency_buy

import com.example.currencytest.currency.CurrencyInteract
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BuyCurrencyModule {
    @Provides
    fun getBuyCurrencyInteract(
        database: AppDatabase
    ): BuyCurrencyListInteractor {
        return BuyCurrencyListInteractor(database)
    }
}