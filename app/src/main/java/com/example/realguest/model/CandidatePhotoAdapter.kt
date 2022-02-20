package com.example.realguest.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.example.realguest.ui.visitDetail.VisitDetailFragment
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.candidate_photo_item.view.*

class CandidatePhotoAdapter(private val listCandidate: List<User>) :
    RecyclerView.Adapter<CandidatePhotoAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ShapeableImageView = itemView.image_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.candidate_photo_item, parent, false)
        )
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get()
            .load(listCandidate[position].photo_100)
            ?.into(holder.imageView)
        holder.itemView.text_id.text = listCandidate[position].first_name

        holder.itemView.setOnClickListener {
            (holder.imageView.context as AppCompatActivity).supportFragmentManager.commit {
            add(R.id.fragment_container_view, VisitDetailFragment())
            }
        }
    }

    override fun getItemCount() = listCandidate.size
}