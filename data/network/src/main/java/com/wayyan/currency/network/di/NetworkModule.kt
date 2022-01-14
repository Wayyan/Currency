package com.wayyan.currency.network.di

import com.wayyan.currency.data.common.repository.currency.CurrencyNetworkSource
import com.wayyan.currency.network.api.CurrencyApiService
import com.wayyan.currency.network.source.CurrencyNetworkSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val NetworkModule = module {
  single {
    RetrofitProvider.retrofit(androidContext()).create(CurrencyApiService::class.java)
  }

  single<CurrencyNetworkSource> {
    CurrencyNetworkSourceImpl(get())
  }
}