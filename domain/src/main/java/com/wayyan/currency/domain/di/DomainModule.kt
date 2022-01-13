package com.wayyan.currency.domain.di

import com.wayyan.currency.domain.currency.usecase.GetCurrencyData
import com.wayyan.currency.domain.currency.usecase.GetCurrencyNames
import org.koin.dsl.module

val DomainModule = module {
  single {
    GetCurrencyNames(get(), get())
  }
  single {
    GetCurrencyData(get(), get())
  }
}