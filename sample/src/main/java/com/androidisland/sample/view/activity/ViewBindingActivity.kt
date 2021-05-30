package com.androidisland.sample.view.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/*
Created by Farshad Tahmasbi on 30/05/2021
*/

abstract class ViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var viewBinding: VB
    abstract fun provideViewBinding(
        inflater: LayoutInflater
    ): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = provideViewBinding(layoutInflater)
        setContentView(viewBinding.root)
    }
}