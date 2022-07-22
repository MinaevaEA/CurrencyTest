package com.example.currencytest.currency_buy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.currencytest.db.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyCurrencyListViewModel @Inject constructor(private val dataFromDataBase: BuyCurrencyListInteractor) :
    ViewModel() {
     val loadingListBuyCurrency = MutableLiveData<List<Currency>>()
    fun onViewCreatedBuy() {
        viewModelScope.launch {
            try {
                val listBuyResponse = dataFromDataBase.BuyCurrencyInteractor()
                loadingListBuyCurrency.postValue(listBuyResponse)
            } catch (e: Exception) {

            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class BuyCurrencyListViewModelFactory(
    private val interact: BuyCurrencyListInteractor
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        BuyCurrencyListViewModel(interact) as T
}