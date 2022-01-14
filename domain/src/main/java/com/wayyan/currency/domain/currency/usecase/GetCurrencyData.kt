package com.wayyan.currency.domain.currency.usecase

import com.wayyan.currency.domain.CoroutineUseCase
import com.wayyan.currency.domain.DispatcherProvider
import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.repository.CurrencyRepository

class GetCurrencyData constructor(
  private val repository: CurrencyRepository,
  dispatcherProvider: DispatcherProvider
) : CoroutineUseCase<String, List<CurrencyModel>>(dispatcherProvider) {
  override suspend fun provide(input: String): List<CurrencyModel> {
    return repository.getCurrencyData(input)
  }
}