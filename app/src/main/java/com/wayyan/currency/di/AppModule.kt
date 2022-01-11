package com.wayyan.currency.di

import com.wayyan.currency.feature.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
  viewModel {
    MainViewModel(get())
  }
}