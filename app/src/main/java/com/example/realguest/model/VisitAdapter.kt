package com.example.realguest.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
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
        val descriptionList: RecyclerView = binding.descriptionList
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val visit = getItem(position)!!

        with(holder.binding) {
            listCandidate.apply {
                layoutManager =
                    LinearLayoutManager(holder.childRecyclerView.context, HORIZONTAL, false)
                adapter = CandidatePhotoAdapter(visit.suitable)
                setRecycledViewPool(viewPool)
            }
            descriptionList.apply {
                layoutManager = LinearLayoutManager(holder.descriptionList.context, VERTICAL, false)
                adapter = DescriptionListAdapter(visit.description)
                setRecycledViewPool(RecyclerView.RecycledViewPool())
            }
            itemTitle.text = visit.title
        }
    }

    object VisitDifferentiator : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(oldItem: Visit, newItem: Visit): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Visit, newItem: Visit) = oldItem == newItem
    }
}