package com.wayyan.currency.network.api.response.currency

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
  @Json(name = "success")
  val status: Boolean,
  @Json(name = "quotes")
  val currencies: Map<String, Double>
)