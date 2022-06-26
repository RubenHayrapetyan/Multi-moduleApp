package com.ruben.components_ui.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerLineItemDecoration(private val divider: Drawable) : RecyclerView.ItemDecoration() {

  override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    super.onDrawOver(c, parent, state)
    val left = parent.paddingLeft
    val right = parent.width
    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child: View = parent.getChildAt(i)
      val params = child.layoutParams as RecyclerView.LayoutParams
      val top: Int = child.bottom + params.bottomMargin
      val bottom = top + divider.intrinsicHeight
      divider.setBounds(left, top, right, bottom)
      divider.draw(c)
    }
  }
}
