package com.wayyan.currency.domain.currency.repository

import com.wayyan.currency.domain.currency.model.CurrencyModel

interface CurrencyRepository {
  fun getCurrencyNames(): List<String>
  fun getCurrencyData(name: String): List<CurrencyModel>
}