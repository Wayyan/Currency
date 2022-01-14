package com.wayyan.currency.domain.currency.usecase

import com.wayyan.currency.domain.CoroutineUseCase
import com.wayyan.currency.domain.DispatcherProvider
import com.wayyan.currency.domain.currency.model.CurrencyModel
import com.wayyan.currency.domain.currency.repository.CurrencyRepository

class GetCurrencyNames constructor(
  private val repository: CurrencyRepository,
  dispatcherProvider: DispatcherProvider
) : CoroutineUseCase<Unit, List<String>>(dispatcherProvider) {
  override suspend fun provide(input: Unit): List<String> {
    return repository.getCurrencyNames()
  }
}