package com.wayyan.currency.extension

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

/**
 * Created by Vincent on 2/13/20
 */
fun Context.layoutInflater(): LayoutInflater {
    return LayoutInflater.from(this)
}

fun Context.showShortToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .show()
}

fun Context.showShortToast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT)
        .show()
}

fun Context.showShortToast(
    @StringRes resId: Int,
    vararg formatArgs: String
) {
    val text = getString(resId, formatArgs)
    Toast.makeText(this, text, Toast.LENGTH_SHORT)
        .show()
}

fun Context.showLongToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG)
        .show()
}

fun Context.showLongToast(
    @StringRes resId: Int,
    vararg formatArgs: String
) {
    val text = getString(resId, formatArgs)
    Toast.makeText(this, text, Toast.LENGTH_SHORT)
        .show()
}

fun Context.showLongToast(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG)
        .show()
}

fun Context.hideKeyBoard(editText: EditText) {
    val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputManager.isAcceptingText) {
        inputManager.hideSoftInputFromWindow(editText.windowToken, 0)
        editText.clearFocus()
    }
}

fun Context.onBackPressed() {
    (this as AppCompatActivity).onBackPressed()
}

fun Context.asActivity(): AppCompatActivity {
    return this as AppCompatActivity
}

fun Context.getVerticalLinearLayout(): LinearLayout {
    val layout = LinearLayout(this)
    layout.orientation = LinearLayout.VERTICAL
    layout.layoutParams =
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    return layout
}

fun Context.getHorizontalLinearLayout(): LinearLayout {
    val layout = LinearLayout(this)
    layout.orientation = LinearLayout.HORIZONTAL
    layout.layoutParams =
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    return layout
}


fun Context.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}

fun Context.spToPx(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
}

fun Context.pxToSp(px: Int): Int {
    val scaleDensity = resources.displayMetrics.scaledDensity
    return (px / scaleDensity).roundToInt()
}