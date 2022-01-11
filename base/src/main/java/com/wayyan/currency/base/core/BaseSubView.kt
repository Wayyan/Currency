package com.wayyan.currency.base.core

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

abstract class BaseSubView<VB : ViewBinding> : ConstraintLayout {
  constructor(context: Context) : super(context)
  constructor(
    context: Context,
    attrs: AttributeSet?
  ) : super(context, attrs)

  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(
    context,
    attrs,
    defStyleAttr
  )

  abstract val binding: VB

  override fun onFinishInflate() {
    super.onFinishInflate()
    onBindView()
  }

  protected open fun onBindView() {
    initUi()
    initListener()
  }

  protected open fun initUi() {
  }

  protected open fun initListener() {
    observeLiveData(context as AppCompatActivity)
  }

  protected open fun observeLiveData(owner: LifecycleOwner) {}
}