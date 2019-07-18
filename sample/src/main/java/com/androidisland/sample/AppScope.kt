package com.androidisland.sample

import android.content.Context
import androidx.lifecycle.*

class VMProviders : ViewModelProviders() {
    companion object {
    }
}

class Provider(store: ViewModelStore, factory: Factory) : ViewModelProvider(store, factory) {

}

fun VMProviders.Companion.go() {

}

class V {
    companion object {}
}

fun V.Companion.doMagic(context: Context) {}

fun x() {
}

