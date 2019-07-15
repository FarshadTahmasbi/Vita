package com.example.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kotlin.random.Random

class Adapter(private val rawString: String) : RecyclerView.Adapter<Adapter.Holder>() {

    private val list = mutableListOf<String>()

    init {
        val rand = Random(System.currentTimeMillis())
        repeat(30) {
            val length = (rand.nextInt(10) + 1) * 100
            list += rawString.substring(0, length)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.text.text = list[position]
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}