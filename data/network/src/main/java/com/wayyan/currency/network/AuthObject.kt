package com.wayyan.currency.network

object AuthObject : AuthModel() {
  val accessKey = accessKey(BuildConfig.BUILD_TYPE)
}