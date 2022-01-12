package com.wayyan.currency.data.cache.di

import com.wayyan.currency.data.cache.source.CurrencyCacheSourceImpl
import com.wayyan.currency.data.common.repository.currency.CurrencyCacheSource
import org.koin.dsl.module

val CacheModule = module {
  single<CurrencyCacheSource> {
    CurrencyCacheSourceImpl(get())
  }
}