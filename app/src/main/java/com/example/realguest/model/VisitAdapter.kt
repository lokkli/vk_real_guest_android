package com.example.realguest.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.databinding.VisitListItemBinding

class VisitAdapter : PagingDataAdapter<Visit, VisitAdapter.VisitViewHolder>(VisitDifferentiator) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        val binding =
            VisitListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VisitViewHolder(binding)
    }

    inner class VisitViewHolder(val binding: VisitListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val childRecyclerView: RecyclerView = binding.listCandidate
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val childLayoutManager =
            LinearLayoutManager(holder.childRecyclerView.context, RecyclerView.HORIZONTAL, false)
        val visit = getItem(position)!!

        with(holder.binding) {
            listCandidate.apply {
                layoutManager = childLayoutManager
                adapter = CandidatePhotoAdapter(visit.primary_suitable)
                setRecycledViewPool(viewPool)
            }
            itemTitle.text = visit.description.title
            visitTime.text = "09.02.2022 13:30"
            visitDescription.text = visit.description.portrait
        }
    }


    object VisitDifferentiator : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(oldItem: Visit, newItem: Visit): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Visit, newItem: Visit) = oldItem == newItem
    }
}