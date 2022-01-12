package com.wayyan.currency.domain.currency.repository

interface CurrencyRepository {
  fun getCurrencyNames(): List<String>
}