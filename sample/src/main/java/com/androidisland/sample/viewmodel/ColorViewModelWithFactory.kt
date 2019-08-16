package com.androidisland.sample.viewmodel

import android.app.Application

class ColorViewModelWithFactory(color : Int, app: Application) : ColorViewModel(app) {

    init {
        colorObservable.value = color
    }
}