package com.example.realguest.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.example.realguest.databinding.CandidatePhotoItemBinding
import com.example.realguest.ui.visitDetail.VisitDetailFragment
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class CandidatePhotoAdapter(private val listCandidate: List<User>) :
    RecyclerView.Adapter<CandidatePhotoAdapter.CandidatePhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatePhotoHolder {
        val binding =
            CandidatePhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CandidatePhotoHolder(
            binding
        )
    }

    inner class CandidatePhotoHolder(val binding: CandidatePhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ShapeableImageView = binding.candidateImage
    }


    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: CandidatePhotoHolder, position: Int) {
        val candidate = listCandidate[position]
        with(holder.binding) {
            Picasso.get()
                .load(candidate.photo_50)
                ?.into(candidateImage)
            candidateName.text = candidate.first_name
        }

        holder.itemView.setOnClickListener {
            (holder.imageView.context as AppCompatActivity).supportFragmentManager.commit {
                add(R.id.fragment_container_view, VisitDetailFragment())
            }
        }
    }

    override fun getItemCount() = listCandidate.size
}