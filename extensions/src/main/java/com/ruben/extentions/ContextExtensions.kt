package com.ruben.extentions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getDrawableFromRes(@DrawableRes drawableResId: Int) = ContextCompat.getDrawable(this, drawableResId)

fun Context.getDimenFromRes(@DimenRes dimenResId: Int) = resources.getDimension(dimenResId)

fun Context.getColorFromRes(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)
