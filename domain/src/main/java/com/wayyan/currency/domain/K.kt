package com.wayyan.currency.domain

object K {
  const val BASE_URL = "http://api.currencylayer.com/"

  object Routes {
    const val GET_COUNTRY_LIST = "list"
    const val GET_CURRENCY = "live"

    const val QUERY_ACCESS_KEY = "access_key"
  }
}