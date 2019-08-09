package com.androidisland.sample.viewmodel

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.androidisland.sample.Constants.Companion.TAG

open class BaseViewModel : ViewModel() {
    @CallSuper
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "${toString()} is cleared now!")
    }

    @CallSuper
    override fun toString(): String {
        return super.toString()
    }
}