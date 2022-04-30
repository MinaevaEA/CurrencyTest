package com.example.currencytest.currency

import androidx.lifecycle.*
import com.example.currencytest.currency_list.CurrencyDetail
import com.example.currencytest.db.Currency
import com.example.currencytest.utils.SingleLiveEvent
import kotlinx.coroutines.launch


class CurrencyViewModel(
    private val dataConcreteCurrency: CurrencyInteract,
    private val value: String
    ) :
    ViewModel() {
    val dataCurrency = MutableLiveData<CurrencyDetail>()
    val errorTextViewVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility = MutableLiveData<Boolean>()
    val showToast = MutableLiveData<String>()
    val showSnackBar = MutableLiveData<Unit>()
    val backToMain = SingleLiveEvent<Unit>()
    val priceVisibility = MutableLiveData<Boolean>()
    val dateVisibility = MutableLiveData<Boolean>()
    val titleVisibility = MutableLiveData<Boolean>()
    val numberOfBuyVisibility = MutableLiveData<Boolean>()
    val buyButtonVisibility = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            try {
                val currency = dataConcreteCurrency.concreteCurrencyInteract(value)
                dataCurrency.postValue(currency)
                progressBarVisibility.postValue(true)
                priceVisibility.postValue(true)
                dateVisibility.postValue(true)
                titleVisibility.postValue(true)
                numberOfBuyVisibility.postValue(true)
                buyButtonVisibility.postValue(true)
            } catch (e: Exception) {
                errorTextViewVisibility.postValue(false)
            }
        }
    }

    fun onClickedSnackBar() {
        backToMain.postValue(Unit)
    }

    fun onClickedBuyCurrency(
        title: String, price: String, date: String, number: Int
    ) {
        if (number == 0) {
            showToast.postValue("Ошибка")
            return
        }
        viewModelScope.launch {
            val insertResult = dataConcreteCurrency.insertCurrencyInteractor(
                Currency(title, price, number, date, generalSumm = price.toDouble() * number)
            )//Это значение, которое показывает была ли произведена транзакция в бд
            if (insertResult > 0) {
                showSnackBar.postValue(Unit)
            } else {
                showToast.postValue("")
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CurrencyViewModelFactory(
    private val interact: CurrencyInteract, private val value: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CurrencyViewModel(interact, value) as T
}