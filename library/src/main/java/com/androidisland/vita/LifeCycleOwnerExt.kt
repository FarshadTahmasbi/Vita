package com.androidisland.vita

import androidx.lifecycle.LifecycleOwner

val LifecycleOwner.Vita: VitaProvider
    get() = VitaProvider(com.androidisland.vita.Vita.getInstance(), this)