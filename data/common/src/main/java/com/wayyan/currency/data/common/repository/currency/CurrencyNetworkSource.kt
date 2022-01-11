package com.wayyan.currency.data.common.repository.currency

import com.wayyan.currency.domain.currency.model.CurrencyModel

interface CurrencyNetworkSource {
  fun fetchCurrencyData(): MutableList<CurrencyModel>
}