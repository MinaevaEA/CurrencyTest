package com.example.currencytest.currency

import androidx.lifecycle.*
import com.example.currencytest.currency_list.CurrencyDetail
import com.example.currencytest.db.Currency
import com.example.currencytest.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val dataConcreteCurrency: CurrencyInteract,
    savedStateHandle: SavedStateHandle
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
    val argumentMain = MutableLiveData<String>()
    private  var argument: String = ""
    init {
        argument =
            savedStateHandle.getLiveData(CurrencyFragment.TAG_FOR_CURRENCY, "").value ?: ""
    }
    fun onCreate() {
        viewModelScope.launch {
            try {
                val currency = dataConcreteCurrency.concreteCurrency(argument)
                argumentMain.postValue(argument)
                dataCurrency.postValue(currency)
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
            val insertResult = dataConcreteCurrency.insertCurrency(
                Currency(
                    title,
                    price,
                    number,
                    date,
                    generalSumm = (price.toDouble() * number))
            )
            //Это значение, которое показывает была ли произведена транзакция в бд
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
    private val interact: CurrencyInteract, private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CurrencyViewModel(interact, savedStateHandle) as T
}