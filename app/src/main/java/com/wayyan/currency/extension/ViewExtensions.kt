package com.wayyan.currency.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.layoutInflater(): LayoutInflater {
  return this.context.layoutInflater()
}

fun View.shouldGone() {
  if (this.visibility != View.GONE)
    this.visibility = View.GONE
}

fun View.shouldVisible() {
  if (this.visibility != View.VISIBLE)
    this.visibility = View.VISIBLE
}

fun View.isShowing(): Boolean {
  return this.visibility == View.VISIBLE
}

fun View.adjustVisible(state: Boolean) {
  if (state)
    this.shouldVisible()
  else
    this.shouldGone()
}

fun View.shouldInvisible() {
  if (this.visibility != View.INVISIBLE)
    this.visibility = View.INVISIBLE
}