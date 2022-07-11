package com.example.currencytest.dagger


import com.example.currencytest.currency.CurrencyFragment
import dagger.Subcomponent

@Subcomponent(modules = [CurrencyModule::class])
interface CurrencyComponent {
    fun injectCurrencyFragment(CurrencyFragment: CurrencyFragment)
}