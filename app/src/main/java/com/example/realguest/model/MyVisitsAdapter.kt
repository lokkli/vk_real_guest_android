package com.example.realguest.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.example.realguest.data.Visit
import kotlinx.android.synthetic.main.item_layout.view.*

class MyVisitsAdapter(private val context: Context, private val visits: MutableList<Visit>) :
    RecyclerView.Adapter<MyVisitsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.item_title

        fun bind(itemView: Visit) {
            itemTitle.setOnClickListener {
                makeText(it.context, "нажал на ${itemTitle.text}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = visits.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = visits[position]
        holder.bind(listItem)
        holder.itemTitle.text = visits[position].description.title
    }
}