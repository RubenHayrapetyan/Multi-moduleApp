package com.ruben.feedback.util

import android.widget.SeekBar

fun SeekBar.doOnSeekBarChanged(onProgressChanged: (Int) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            onProgressChanged.invoke(progress)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) = Unit

        override fun onStopTrackingTouch(p0: SeekBar?) = Unit
    })
}