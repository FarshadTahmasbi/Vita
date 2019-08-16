package com.androidisland.sample.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.androidisland.sample.R
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita

class SimpleFragment : Fragment() {

    private val viewModel by vita.with(VitaOwner.Multiple(this)).viewModel<ColorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeColor(this, Observer {
            view.setBackgroundColor(it)
        })
        view.setOnClickListener {
            activity?.openColorDialog("SimpleFragment") {
                changeColor(it)
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)
}