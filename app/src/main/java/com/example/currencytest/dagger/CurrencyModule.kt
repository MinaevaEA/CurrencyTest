/*
package com.example.currencytest.dagger

import com.example.currencytest.currency.CurrencyFragment
import com.example.currencytest.currency.CurrencyFragment.Companion.TAG_FOR_CURRENCY
import com.example.currencytest.currency.CurrencyInteract
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides

@Module
class CurrencyModule {
    @Provides
    fun getCurrencyInteract(
        dataConcreteCurrency: RetrofitServices,
        database: AppDatabase
    ): CurrencyInteract {
        return CurrencyInteract(dataConcreteCurrency, database)
    }

    @Provides
    fun getBundle(currencyFragment: CurrencyFragment): String {
        val bundle =
            currencyFragment.arguments?.getString(TAG_FOR_CURRENCY, "") ?: ""
        return bundle
    }
    */
/*@Provides
    fun provideCurrencyFragmentView(currencyFragment: CurrencyFragment): CurrencyFragment {
        return currencyFragment
    }*//*

}*/
