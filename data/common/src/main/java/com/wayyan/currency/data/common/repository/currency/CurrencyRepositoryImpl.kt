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
        it.currencyName.replaceFirst("USD", "")
      }
    }
  }

  override fun getCurrencyData(name: String): List<CurrencyModel> {
    val cacheData = cacheSource.getCurrencyData().map {
      CurrencyModel(
        currencyName = it.currencyName.replaceFirst("USD", ""),
        currencyValue = it.currencyValue
      )
    }
    val selectedCurrency = cacheData.find {
      it.currencyName.equals(name, true)
    }

    selectedCurrency?.let {
      val multiplier = 1 / it.currencyValue

      val excludeData = cacheData.filter { m ->
        !m.currencyName.equals(name, true)
      }

      return excludeData.map { m ->
        CurrencyModel(
          currencyName = m.currencyName,
          currencyValue = m.currencyValue * multiplier
        )
      }
    }
    return cacheData
  }
}