package com.wayyan.currency.domain.currency.repository

import com.wayyan.currency.domain.currency.model.CurrencyModel

interface CurrencyRepository {
  fun getCurrencyData(): List<CurrencyModel>
}