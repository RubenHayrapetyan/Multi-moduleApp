package com.ruben.components_ui.components.attributs

import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentArgumentsDelegate<A>(
  private val key: String,
  private val default: A,
) : ReadOnlyProperty<Fragment, A> {
  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Fragment, property: KProperty<*>): A =
    thisRef.arguments?.get(key) as? A ?: default
}

inline fun <reified A> Fragment.arguments(key: String, default: A) = FragmentArgumentsDelegate(
  key = key,
  default = default,
)
