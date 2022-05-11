package com.example.currencytest.currency_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.currencytest.utils.SingleLiveEvent
import kotlinx.coroutines.launch


class CurrencyListViewModel(private val storageDataNetwork: DataNetworkInteract) : ViewModel() {
    val loadingListCurrency = MutableLiveData<List<DataCurrency>>()
    val progressBarVisibility = MutableLiveData<Boolean>()
    val errorTextViewVisibility = MutableLiveData<Boolean>()
    val currencyListAdapterVisibility = MutableLiveData<Boolean>()
    val onCurrencyClickedEvent = SingleLiveEvent<String>()
    var fullCurrenciesList = ArrayList<DataCurrency>()
    private fun setDataNetwork() {
        viewModelScope.launch {
            try {
                val listCurrenciesResponse = storageDataNetwork.currencyListInteractor()
                fullCurrenciesList = listCurrenciesResponse as ArrayList<DataCurrency>
                loadingListCurrency.postValue(fullCurrenciesList)
                currencyListAdapterVisibility.postValue(true)

            } catch (e: Exception) {
                progressBarVisibility.postValue(false)
                errorTextViewVisibility.postValue(false)
            }
        }
    }

    fun onCurrencyClicked(currencyPosition: String) {
        onCurrencyClickedEvent.postValue(currencyPosition)
    }

    fun searchNotes(query: String?) {
        val filteredList = ArrayList<DataCurrency>()
        if (query?.isNotEmpty() == true) {
            fullCurrenciesList.filter {
                it.valute.contains(
                    other = query,
                    ignoreCase = false
                ) //or it.country.contains(other = query, ignoreCase = false)
            }
                .forEach { filteredList.add(it) }
            fullCurrenciesList = filteredList
            Log.d("listDebug", "searchNotes: ${fullCurrenciesList.size}")
            loadingListCurrency.value = fullCurrenciesList

        } else {

            setDataNetwork()
        }
    }

    init {
        setDataNetwork()
    }
}

@Suppress("UNCHECKED_CAST")
class CurrencyListViewModelFactory(
    private val interact: DataNetworkInteract
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CurrencyListViewModel(interact) as T
}