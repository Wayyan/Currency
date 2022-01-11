package com.wayyan.currency.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayyan.currency.base.helper.AsyncViewStateLiveData
import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.usecase.GetCurrencyData
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val getCurrencyData: GetCurrencyData
) : ViewModel() {
  val currencyLiveData = AsyncViewStateLiveData<List<CurrencyModel>>()

  fun getCurrencyData() {
    currencyLiveData.postLoading()
    viewModelScope.launch {
      val result = kotlin.runCatching {
        val data = getCurrencyData.execute(Unit)
        currencyLiveData.postSuccess(data)
      }

      result.exceptionOrNull()?.let {
        currencyLiveData.postError(it, it.message ?: "Error Getting Currency Data!")
      }
    }
  }
}