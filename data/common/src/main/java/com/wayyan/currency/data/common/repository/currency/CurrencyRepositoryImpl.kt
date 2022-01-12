package com.wayyan.currency.data.common.repository.currency

import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.repository.CurrencyRepository

class CurrencyRepositoryImpl constructor(
  private val networkSource: CurrencyNetworkSource,
  private val cacheSource: CurrencyCacheSource
) : CurrencyRepository {
  override fun getCurrencyNames(): List<String> {
    val cacheData = cacheSource.getCurrencyData()

    if (cacheData.isEmpty()) {
      val currencyData = networkSource.fetchCurrencyData()
      cacheSource.setCurrencyData(currencyData)
      return currencyData.map {
        it.currencyName
      }
    } else {
      return cacheData.map {
        it.currencyName
      }
    }
  }
}