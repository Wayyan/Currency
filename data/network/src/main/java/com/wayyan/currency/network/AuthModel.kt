package com.wayyan.currency.network

open class AuthModel {
  companion object {
    init {
      System.loadLibrary("native-lib")
    }
  }

  protected external fun accessKey(buildType: String): String
}