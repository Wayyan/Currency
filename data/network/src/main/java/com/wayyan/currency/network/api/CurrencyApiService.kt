package com.wayyan.currency.network.api

import com.wayyan.currency.domain.K
import com.wayyan.currency.network.AuthObject
import com.wayyan.currency.network.api.response.currency.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
  @GET(K.Routes.GET_CURRENCY)
  fun getCurrencyData(
    @Query(K.Routes.QUERY_ACCESS_KEY) accessKey: String = AuthObject.accessKey
  ): Call<CurrencyResponse>
}