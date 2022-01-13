package com.wayyan.currency.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayyan.currency.base.helper.AsyncViewStateLiveData
import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.usecase.GetCurrencyData
import com.wayyan.currency.domain.currency.usecase.GetCurrencyNames
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val getCurrencyNames: GetCurrencyNames,
  private val getCurrencyData: GetCurrencyData
) : ViewModel() {
  val currencyNamesLiveData = AsyncViewStateLiveData<List<String>>()
  val currencyDataLiveData = AsyncViewStateLiveData<List<CurrencyModel>>()
  val selectedCurrencyLiveData = AsyncViewStateLiveData<String>()

  var selectedCurrency = "USD"
    set(value) {
      selectedCurrencyLiveData.postSuccess(value)
      getCurrencyData(value)
      field = value
    }

  fun getCurrencyNames() {
    currencyNamesLiveData.postLoading()
    viewModelScope.launch {
      val result = kotlin.runCatching {
        val data = getCurrencyNames.execute(Unit)
        currencyNamesLiveData.postSuccess(data)
      }

      result.exceptionOrNull()?.let {
        currencyNamesLiveData.postError(it, it.message ?: "Error Getting Currency Data!")
      }
    }
  }

  fun getCurrencyData(name: String = selectedCurrency) {
    currencyDataLiveData.postLoading()
    viewModelScope.launch {
      val result = kotlin.runCatching {
        val data = getCurrencyData.execute(name)
        currencyDataLiveData.postSuccess(data)
      }

      result.exceptionOrNull()?.let {
        currencyDataLiveData.postError(it, it.message ?: "Error Getting Currency Data!")
      }
    }
  }
}