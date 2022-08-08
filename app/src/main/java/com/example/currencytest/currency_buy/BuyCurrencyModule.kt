package com.example.currencytest.currency_buy

import com.example.currencytest.db.AppDatabase
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
    ): BuyCurrencyListInteract {
        return BuyCurrencyListInteract(database)
    }
}