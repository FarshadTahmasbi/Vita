package com.androidisland.sample.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.androidisland.sample.Constants.Companion.TAG

open class BaseViewModel(app: Application) : AndroidViewModel(app) {
    @CallSuper
    override fun onCleared() {
        super.onCleared()
        Toast.makeText(getApplication(), "${this::class.java.simpleName}@${hashCode()} is cleared", Toast.LENGTH_SHORT)
            .show()
    }
}