package com.wayyan.currency.extension

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.wayyan.currency.databinding.DialogPromptBinding

fun Context.getDialog(layout: Int): Dialog {
  val dialog = Dialog(this)
  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
  dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
  dialog.setContentView(layout)
  dialog.setCancelable(false)
  return dialog
}

fun Context.getDialog(view: View, cancelable: Boolean): Dialog {
  val dialog = Dialog(this)
  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
  dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
  dialog.setContentView(view)
  dialog.setCancelable(cancelable)
  return dialog
}

fun Dialog?.shouldDismiss() {
  if (this != null && this.isShowing)
    this.dismiss()
}

fun Dialog?.shouldShow() {
  if (this != null && !this.isShowing)
    this.show()
}

fun Context.getScreenWidth(): Int {
  return Resources.getSystem().displayMetrics.widthPixels
}

fun Context.getScreenHeight(): Int {
  return Resources.getSystem().displayMetrics.heightPixels
}

fun Context.showPromptDialog(msg: String, btnText: String? = null) {
  val binding = DialogPromptBinding.inflate(LayoutInflater.from(this))
  val dialog = this.getDialog(binding.root, false)
  dialog.window?.setLayout(
    (this.getScreenWidth() * 0.9).toInt(),
    WindowManager.LayoutParams.WRAP_CONTENT
  )
  binding.tvPrompt.text = msg
  btnText?.let {
    binding.tvOk.text = it
  }
  binding.tvOk.setOnClickListener {
    dialog.shouldDismiss()
  }
  dialog.shouldShow()
}

fun Context.showActionPromptDialog(msg: String, btnText: String? = null, action: () -> Unit) {
  val binding = DialogPromptBinding.inflate(LayoutInflater.from(this))
  val dialog = this.getDialog(binding.root, false)
  dialog.window?.setLayout(
    (this.getScreenWidth() * 0.9).toInt(),
    WindowManager.LayoutParams.WRAP_CONTENT
  )
  binding.tvPrompt.text = msg
  btnText?.let {
    binding.tvOk.text = it
  }
  binding.tvOk.setOnClickListener {
    action()
    dialog.shouldDismiss()
  }
  dialog.shouldShow()
}

// fun Context.showActionPromptDialog(
//     msg: String,
//     btnTextOne: String? = null,
//     btnTextTwo: String? = null,
//     actionOne: () -> Unit,
//     actionTwo: () -> Unit
// ) {
//     val binding = DialogPromptDualActionBinding.inflate(LayoutInflater.from(this))
//     val dialog = this.getDialog(binding.root, false)
//     dialog.window?.setLayout(
//         (this.getScreenWidth() * 0.9).toInt(),
//         WindowManager.LayoutParams.WRAP_CONTENT
//     )
//     binding.tvPrompt.text = msg
//     btnTextOne?.let {
//         binding.tvOne.text = it
//     }
//
//     btnTextTwo?.let {
//         binding.tvTwo.text = it
//     }
//
//     binding.tvOne.setOnClickListener {
//         actionOne()
//         dialog.shouldDismiss()
//     }
//
//     binding.tvTwo.setOnClickListener {
//         actionTwo()
//         dialog.shouldDismiss()
//     }
//     dialog.shouldShow()
// }