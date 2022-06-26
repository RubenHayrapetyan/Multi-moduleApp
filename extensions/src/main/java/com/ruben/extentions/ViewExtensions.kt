package com.ruben.extentions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

val ViewGroup.layoutInflater: LayoutInflater
  get() = LayoutInflater.from(context)

fun View.visible() {
  visibility = View.VISIBLE
}

fun View.gone() {
  visibility = View.GONE
}