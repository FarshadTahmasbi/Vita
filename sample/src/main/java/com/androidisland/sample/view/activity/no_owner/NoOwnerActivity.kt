package com.androidisland.sample.view.activity.no_owner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.androidisland.sample.R
import com.androidisland.sample.databinding.ActivityNoOwnerBinding
import com.androidisland.sample.view.activity.ViewBindingActivity
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class NoOwnerActivity : ViewBindingActivity<ActivityNoOwnerBinding>() {

    private val viewModel by lazy {
        vita.with(VitaOwner.None).getViewModel<ColorViewModel>()
    }

    override fun provideViewBinding(inflater: LayoutInflater) =
        ActivityNoOwnerBinding.inflate(inflater)
}
