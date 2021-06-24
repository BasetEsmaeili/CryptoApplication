package com.baset.crypto.trader.utils

import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sign

fun Double.round(digits: Int): Double {
    val factor = 10.0.pow(digits.toDouble())
    return (this * factor).roundToInt() / factor
}

fun Context.getColorCompat(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

fun Double.isNegative(): Boolean = this.sign == -1.0

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}