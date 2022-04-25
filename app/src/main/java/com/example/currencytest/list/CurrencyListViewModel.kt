package com.example.currencytest.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class CurrencyListViewModel(private val interactor: CurrencyListInteract) : ViewModel() {
    val loadingListCurrency = MutableLiveData<List<DataCurrency>>()
    val progressBarVisibility = MutableLiveData<Boolean>()
    val errorTextViewVisibility = MutableLiveData<Boolean>()
    val listCurrencyVisibility = MutableLiveData<Boolean>()
    fun onViewCreated() {
        viewModelScope.launch {
            try {
                val i = interactor.getCurrenciesResponse()
                loadingListCurrency.postValue(i)
                listCurrencyVisibility.postValue(true)
            } catch (e: Exception) {
                progressBarVisibility.postValue(false)
                errorTextViewVisibility.postValue(false)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CurrencyViewModelFactory(
    val interact: CurrencyListInteract
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CurrencyListViewModel(interact) as T
}