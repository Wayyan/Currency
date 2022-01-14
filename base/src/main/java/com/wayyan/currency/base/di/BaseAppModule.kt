package com.wayyan.currency.base.di

import com.wayyan.currency.base.DefaultDispatcherProvider
import com.wayyan.currency.domain.DispatcherProvider
import org.koin.dsl.module

val BaseAppModule = module {
  single<DispatcherProvider> {
    DefaultDispatcherProvider()
  }
}