package com.example.realguest.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realguest.R
import com.example.realguest.data.DescriptionCode.DESKTOP
import com.example.realguest.databinding.DescriptionListItemBinding

class DescriptionListAdapter(private val listDescription: List<Description>) :
    RecyclerView.Adapter<DescriptionListAdapter.DescriptionListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionListHolder {
        val binding =
            DescriptionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DescriptionListHolder(binding)
    }

    override fun onBindViewHolder(holder: DescriptionListHolder, position: Int) {
        with(holder.binding) {
            itemText.text = listDescription[position].value
            when (listDescription[position].code) {
                DESKTOP.name -> descriptionIcon.setImageResource(R.drawable.ic_desktop)
            }
        }
    }

    inner class DescriptionListHolder(val binding: DescriptionListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = listDescription.size
}