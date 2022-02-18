package com.example.realguest.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class VisitAdapter : PagingDataAdapter<Visit, VisitAdapter.VisitViewHolder>(VisitDifferentiator) {

    class VisitViewHolder(view: View) : RecyclerView.ViewHolder(view)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val listItem = getItem(position)!!
        holder.itemView.item_title.text = listItem.description.title
        holder.itemView.visit_time.text = "09.02.2022 13:30"
        val suitable = listItem.suitable.primary.firstOrNull()
        suitable.let {
            Picasso.get().load(suitable?.photo_100)
                .into(holder.itemView.visit_photo_container.candidate_1)
        }
        holder.itemView.visit_description.text = listItem.description.devices +
                "\n\n${listItem.description.portrait}" +
                "\n\n${listItem.description.filtering_interval}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        return VisitViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    object VisitDifferentiator : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(oldItem: Visit, newItem: Visit) =
            oldItem.description == newItem.description

        override fun areContentsTheSame(oldItem: Visit, newItem: Visit) = oldItem == newItem
    }
}