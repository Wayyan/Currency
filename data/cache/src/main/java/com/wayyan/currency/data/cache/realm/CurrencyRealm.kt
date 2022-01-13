package com.wayyan.currency.data.cache.realm

import io.realm.RealmObject

open class CurrencyRealm : RealmObject() {
  var name: String? = null
  var value: Double? = null
}