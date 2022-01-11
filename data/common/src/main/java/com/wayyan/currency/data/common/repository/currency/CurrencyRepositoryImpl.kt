package com.wayyan.currency.data.common.repository.currency

import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.repository.CurrencyRepository

class CurrencyRepositoryImpl constructor(
  private val networkSource: CurrencyNetworkSource
) : CurrencyRepository {
  override fun getCurrencyData(): List<CurrencyModel> {
    return networkSource.fetchCurrencyData()
  }
}