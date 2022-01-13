package com.wayyan.currency.data.cache.source

import android.content.Context
import com.wayyan.currency.data.cache.realm.CurrencyRealm
import com.wayyan.currency.data.common.repository.currency.CurrencyCacheSource
import com.wayyan.currency.domain.currency.model.CurrencyModel
import io.realm.Realm

class CurrencyCacheSourceImpl constructor(private val context: Context) : CurrencyCacheSource {
  private val basePreferences = context.getSharedPreferences("currency", Context.MODE_PRIVATE)

  companion object {
    private const val KEY_EXPIRE_TIME = "exp_time"
  }

  override fun setCurrencyData(data: List<CurrencyModel>) {
    val realmDB = Realm.getInstance(Realm.getDefaultConfiguration()!!)

    realmDB.beginTransaction()
    realmDB.where(CurrencyRealm::class.java)
      .findAll()
      .deleteAllFromRealm()
    realmDB.commitTransaction()

    data.forEach {
      realmDB.executeTransaction { realm ->
        val newTuple = realmDB.createObject(CurrencyRealm::class.java)
        newTuple.name = it.currencyName
        newTuple.value = it.currencyValue
        realmDB.insertOrUpdate(newTuple)
      }
    }

    val currentTimeMs = System.currentTimeMillis()
    val distanceMs = 1000 * 60 * 60 //60 mins
    setExpireTime(currentTimeMs + distanceMs)

    if (!realmDB.isClosed)
      realmDB.close()
  }

  override fun getCurrencyData(): List<CurrencyModel> {
    val currentTimeMs = System.currentTimeMillis()

    if (currentTimeMs < getExpireTime()) {
      val realmDB = Realm.getInstance(Realm.getDefaultConfiguration()!!)

      val data = realmDB.where(CurrencyRealm::class.java).findAll()
      return data.map {
        CurrencyModel(
          currencyName = it.name ?: "",
          currencyValue = it.value ?: 0.0
        )
      }
    } else {
      return emptyList()
    }
  }

  override fun setExpireTime(time: Long) {
    basePreferences.edit().putLong(KEY_EXPIRE_TIME, time).apply()
  }

  override fun getExpireTime(): Long {
    return basePreferences.getLong(KEY_EXPIRE_TIME, 0)
  }
}