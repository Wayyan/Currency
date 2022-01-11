package com.wayyan.currency.base

import com.wayyan.currency.domain.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider : DispatcherProvider {
  override fun main(): CoroutineDispatcher = Dispatchers.Main
  override fun io(): CoroutineDispatcher = Dispatchers.IO
  override fun default(): CoroutineDispatcher = Dispatchers.Default
  override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}