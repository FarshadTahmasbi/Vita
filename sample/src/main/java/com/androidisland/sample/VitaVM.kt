package com.androidisland.sample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VitaVM : ViewModel() {
    val live = MutableLiveData<String>()
    fun setData(data : String) {
        live.value = data
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("test", "vita vm cleared!")
    }
}