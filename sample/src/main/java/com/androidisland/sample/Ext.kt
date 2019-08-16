package com.androidisland.sample

import android.app.Activity
import android.graphics.Color
import petrov.kristiyan.colorpicker.ColorPicker

fun Activity.openColorDialog(title : String,
                             defaultColor: Int= Color.WHITE,
                             listener: (color: Int) -> Unit) {
    ColorPicker(this)
        .setTitle(title)
        .setDefaultColorButton(defaultColor)
        .setColors(R.array.colors)
        .setColumns(4)
        .disableDefaultButtons(true)
        .setOnFastChooseColorListener(object : ColorPicker.OnFastChooseColorListener {
            override fun setOnFastChooseColorListener(position: Int, color: Int) {
                listener(color)
            }

            override fun onCancel() {
            }

        })
        .show()
}