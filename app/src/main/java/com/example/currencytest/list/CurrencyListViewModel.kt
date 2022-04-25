package com.example.currencytest.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class CurrencyListViewModel(private val storageDataNetwork: DataNetworkInteract) : ViewModel() {
    val loadingListCurrency = MutableLiveData<List<DataCurrency>>()
    val progressBarVisibility = MutableLiveData<Boolean>()
    val errorTextViewVisibility = MutableLiveData<Boolean>()
    val listCurrencyVisibility = MutableLiveData<Boolean>()
    val onCurrencyClicked = SingleLiveEvent<String>()
    fun onViewCreated() {
        viewModelScope.launch {
            try {
                val listCurrenciesResponse = storageDataNetwork.getCurrenciesResponse()
                loadingListCurrency.postValue(listCurrenciesResponse)
                listCurrencyVisibility.postValue(true)
            } catch (e: Exception) {
                progressBarVisibility.postValue(false)
                errorTextViewVisibility.postValue(false)
            }
        }
    }

    fun onCurrencyClicked(currencyPosition: String) {
        onCurrencyClicked.postValue(currencyPosition)
    }
}

@Suppress("UNCHECKED_CAST")
class CurrencyViewModelFactory(
    private val interact: DataNetworkInteract
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CurrencyListViewModel(interact) as T
}