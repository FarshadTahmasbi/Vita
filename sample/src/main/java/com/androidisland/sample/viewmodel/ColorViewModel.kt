package com.androidisland.sample.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class ColorViewModel(app: Application) : BaseViewModel(app) {

    protected val colorObservable = MutableLiveData<Int>()

    fun observeColor(
        owner: LifecycleOwner,
        observer: Observer<Int>
    ) = colorObservable.observe(owner, observer)

    fun setColor(color: Int) {
        colorObservable.value = color
    }
}