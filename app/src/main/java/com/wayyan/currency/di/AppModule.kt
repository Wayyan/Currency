package com.wayyan.currency.di

import com.wayyan.currency.adapter.CurrencyDataAdapter
import com.wayyan.currency.adapter.CurrencyNameAdapter
import com.wayyan.currency.feature.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
  viewModel {
    MainViewModel(get(), get())
  }

  //Adapters
  single {
    CurrencyDataAdapter(androidContext())
  }

  single {
    CurrencyNameAdapter(androidContext())
  }
}