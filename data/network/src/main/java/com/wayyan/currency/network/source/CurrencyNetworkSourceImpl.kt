package com.wayyan.currency.network.source

import com.wayyan.currency.data.common.repository.currency.CurrencyNetworkSource
import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.network.api.CurrencyApiService
import com.wayyan.currency.network.exception.NoContentException
import com.wayyan.currency.network.extension.executeOrThrow

class CurrencyNetworkSourceImpl constructor(
  private val currencyApiService: CurrencyApiService
) : CurrencyNetworkSource {
  override fun fetchCurrencyData(): MutableList<CurrencyModel> {
    val rawResponse = currencyApiService.getCurrencyData().executeOrThrow()

    if (!rawResponse.status) {
      throw NoContentException()
    }

    val data = rawResponse.currencies.map {
      CurrencyModel(currencyName = it.key, currencyValue = it.value)
    }.toMutableList()
    data.add(0, CurrencyModel("USDUSD", 1.0))
    return data
  }
}