package com.ruben.extentions

fun <E>MutableList<E>.add(vararg elements: E) {
  elements.forEach(::add)
}