package com.wayyan.currency.extension

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType.CENTER_INSIDE
import android.widget.ImageView.ScaleType.FIT_CENTER
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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

fun ImageView.network(
  url: String,
  placeHolder: Int,
  error: Int
) {
  this.scaleType = CENTER_INSIDE
  Glide.with(this)
    .load(url)
    .fitCenter()
    .placeholder(placeHolder)
    .error(error)
    .fallback(error)
    .listener(object : RequestListener<Drawable> {
      override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
      ): Boolean {

        return true
      }

      override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
      ): Boolean {
        this@network.scaleType = FIT_CENTER
        return false
      }
    })
    .into(this)
}