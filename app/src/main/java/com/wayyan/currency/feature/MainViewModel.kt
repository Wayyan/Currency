package com.wayyan.currency.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayyan.currency.base.helper.AsyncViewStateLiveData
import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.usecase.GetCurrencyNames
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val getCurrencyNames: GetCurrencyNames
) : ViewModel() {
  val currencyNamesLiveData = AsyncViewStateLiveData<List<String>>()

  fun getCurrencyData() {
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
}