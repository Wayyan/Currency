package com.wayyan.currency.base.core

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

  abstract val binding: VB

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    initUi()
    initListener()
    observeLiveData(this)
  }

  protected open fun initUi() {
  }

  protected open fun initListener() {
  }

  protected open fun observeLiveData(owner: LifecycleOwner) {}
}