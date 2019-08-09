package com.androidisland.sample.viewmodel

class ViewModelWithFactory(private val message: String) : BaseViewModel() {

    override fun toString(): String {
        return "${super.toString()}, message:$message"
    }
}