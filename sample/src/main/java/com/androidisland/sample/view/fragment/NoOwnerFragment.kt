package com.androidisland.sample.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.androidisland.sample.Constants.Companion.TAG
import com.androidisland.sample.R
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class NoOwnerFragment : Fragment() {

    private val viewModel by lazy {
        vita.with(VitaOwner.None).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observeColor(this, Observer {
            Log.d(TAG, "SingleOwnerFragment, Color changed")
            view?.setBackgroundColor(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_owner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            activity?.openColorDialog("SingleOwnerFragment") {
                changeColor(it)
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)
}