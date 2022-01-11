package com.wayyan.currency.domain.di

import com.wayyan.currency.domain.currency.usecase.GetCurrencyData
import org.koin.dsl.module

val DomainModule = module {
  single {
    GetCurrencyData(get(), get())
  }
}