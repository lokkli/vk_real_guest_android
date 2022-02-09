package com.example.realguest.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class MyVisitsAdapter(private val context: Context, private val visits: MutableList<Visit>) :
    RecyclerView.Adapter<MyVisitsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.item_title
        val visitDescription: TextView = itemView.visit_description
        val visitDate: TextView = itemView.visit_time
        val visitPhotoContainer: LinearLayout = itemView.visit_photo_container

        fun bind(itemView: Visit) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = visits.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = visits[position]
        holder.bind(listItem)
        holder.itemTitle.text = listItem.description.title
        holder.visitDate.text = "09.02.2022 13:30"
        val listCandidate = listItem.suitable.primary.firstOrNull()
        if (listCandidate != null) {
            Picasso.get().load(listItem.suitable.primary[0].photo100)
                .into(holder.visitPhotoContainer.candidate_1)
            Picasso.get().load(listItem.suitable.primary.getOrNull(0)?.photo100)
                .into(holder.visitPhotoContainer.candidate_2)
        }
        holder.visitDescription.text =
            listItem.description.devices +
                    "\n\n${listItem.description.portrait}" +
                    "\n\n${listItem.description.filteringInterval}"
    }
}