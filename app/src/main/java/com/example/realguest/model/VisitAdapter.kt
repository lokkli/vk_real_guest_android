package com.example.realguest.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import kotlinx.android.synthetic.main.item_layout.view.*

class VisitAdapter : PagingDataAdapter<Visit, VisitAdapter.VisitViewHolder>(VisitDifferentiator) {

    private val viewPool = RecyclerView.RecycledViewPool()

    class VisitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val childRecyclerView: RecyclerView = itemView.list_candidate
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val listItem = getItem(position)!!
        val childLayoutManager =
            LinearLayoutManager(holder.childRecyclerView.context, RecyclerView.HORIZONTAL, false)
        holder.itemView.list_candidate.apply {
            layoutManager = childLayoutManager
            adapter = CandidatePhotoAdapter(listItem.suitable.primary + listItem.suitable.secondary)
            setRecycledViewPool(viewPool)
        }
        holder.itemView.item_title.text = listItem.description.title
        holder.itemView.visit_time.text = "09.02.2022 13:30"
        holder.itemView.visit_description.text = listItem.description.portrait
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        return VisitViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    object VisitDifferentiator : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(oldItem: Visit, newItem: Visit): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Visit, newItem: Visit) = oldItem == newItem
    }
}