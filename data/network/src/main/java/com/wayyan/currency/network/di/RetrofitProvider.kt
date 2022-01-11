package com.wayyan.currency.network.di

import android.content.Context
import com.wayyan.currency.domain.K
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
  private var retrofit: Retrofit? = null

  fun retrofit(context: Context): Retrofit {
    if (retrofit != null)
      return retrofit!!

    return Retrofit.Builder().baseUrl(K.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create())
      .client(OkHttpProvider.okHttpClient(context))
      .build()
  }
}