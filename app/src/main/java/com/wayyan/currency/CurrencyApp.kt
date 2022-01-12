package com.wayyan.currency

import androidx.multidex.MultiDexApplication
import com.wayyan.currency.base.di.BaseAppModule
import com.wayyan.currency.data.cache.di.CacheModule
import com.wayyan.currency.data.common.di.DataModule
import com.wayyan.currency.di.AppModule
import com.wayyan.currency.domain.di.DomainModule
import com.wayyan.currency.network.di.NetworkModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import timber.log.Timber

class CurrencyApp : MultiDexApplication(), KoinComponent {
  override fun onCreate() {
    super.onCreate()
    setUpKoin()
    Realm.init(this)
    if (BuildConfig.DEBUG)
      Timber.plant(Timber.DebugTree())
  }

  private fun setUpKoin() {
    startKoin {
      androidContext(this@CurrencyApp)
      androidLogger()

      modules(
        listOf(
          DomainModule,
          DataModule,
          NetworkModule,
          CacheModule,
          BaseAppModule,
          AppModule
        )
      )
    }
  }
}