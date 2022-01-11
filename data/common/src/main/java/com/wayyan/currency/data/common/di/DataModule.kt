package com.wayyan.currency.data.common.di

import com.wayyan.currency.data.common.repository.currency.CurrencyRepositoryImpl
import com.wayyan.currency.domain.currency.repository.CurrencyRepository
import org.koin.dsl.module

val DataModule = module {
  single<CurrencyRepository> {
    CurrencyRepositoryImpl(get())
  }
}