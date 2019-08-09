package com.androidisland.vita

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class VitaProvider internal constructor(@PublishedApi internal val owner: VitaOwner) {
    /**
     * Use this method to get ViewModel, you can control the life of ViewModel by the owner you pass,
     * If you pass a Fragment or FragmentActivity as owner, the ViewModel is alive while owner is alive
     * (This is exactly same as it was before!)
     * But if you pass a ProcessLifecycleOwner, ViewModel will be created in app level and stays alive unless
     * the last owner is dead, this is useful when you want to share ViewModels between activities
     * @param owner object for ViewModel, it can be Single, Multiple or None
     * @return ViewModel object
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : ViewModel> getViewModel(
        noinline factoryFun: FactoryFun<T>? = null
    ): T {
        val factory = factoryFun?.let {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
            }
        }

        return when (owner) {
            is VitaOwner.Single -> owner.getViewModel(factoryFun)
            is VitaOwner.Multiple -> {
                //TODO not implemented
                throw IllegalArgumentException("Unsupported owner passed")
            }
            is VitaOwner.None -> {
                //TODO not implemented
                throw IllegalArgumentException("Unsupported owner passed")
            }
        }
    }

    /**
     * Same as getViewModel function but returns the result lazily
     */
    inline fun <reified T : ViewModel> viewModel(
        noinline factoryFun: FactoryFun<T>? = null
    ) = lazy {
        getViewModel(factoryFun)
    }

//    @Suppress("UNCHECKED_CAST")
//    private inline fun <reified T : ViewModel> VitaOwner.Single.getViewModel(noinline factoryFun: FactoryFun<T>? = null): T {
//        val factory = factoryFun?.let {
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel?> create(modelClass: Class<T>) = it() as T
//            }
//        }
//        return when (lifecycleOwner) {
//            is Fragment -> ViewModelProviders.of(lifecycleOwner, factory)[T::class.java]
//            is FragmentActivity -> ViewModelProviders.of(lifecycleOwner, factory)[T::class.java]
//            else -> throw IllegalArgumentException("Unsupported owner passed")
//        }
//    }
}