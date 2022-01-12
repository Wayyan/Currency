package com.wayyan.currency.data.common.repository.currency

import com.wayyan.currency.domain.currency.model.CurrencyModel
import java.util.Date

interface CurrencyCacheSource {
  fun setCurrencyData(data: List<CurrencyModel>)
  fun getCurrencyData(): List<CurrencyModel>
  fun setExpireTime(time: Long)
  fun getExpireTime(): Long
}